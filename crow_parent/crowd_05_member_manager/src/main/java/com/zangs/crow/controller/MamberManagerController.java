package com.zangs.crow.controller;

import com.zangs.crow.api.DataBaseOperationRemoteService;
import com.zangs.crow.api.RedisOperationRemoteService;
import com.zangs.crow.base.bean.BasicResult;
import com.zangs.crow.base.bean.member.LoginVO;
import com.zangs.crow.base.bean.member.MemberPO;
import com.zangs.crow.base.bean.member.MemberVO;
import com.zangs.crow.common.constant.ConstString;
import com.zangs.crow.common.util.*;
import com.zangs.crow.shiro.MemberVoToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class MamberManagerController {

    @Value("${message.registryCodeRemindTime}")
    Long timeoutMinute;

    @Autowired
    private RedisOperationRemoteService redisRemoteService;

    @Autowired
    private DataBaseOperationRemoteService dataBaseOperationRemoteService;


    // Spring会根据@Value注解中的表达式读取yml或properties配置文件给成员变量设置对应的值
//    @Value("${crow.short.message.appCode}")
//    private String appcode;

    @RequestMapping("/member/manager/register")
    public BasicResult<String> register(@RequestBody MemberVO memberVO) {

        // 1.检查验证码是否有效
        String randomCode = memberVO.getRandomCode();
        if (!CrowdUtils.strEffectiveCheck(randomCode)) {
            return ResultUtil.failed(ConstString.MESSAGE_CODE_INVALID);
        }

        // 2.检查手机号是否有效
        String phoneNum = memberVO.getPhoneNum();
        if (!PhoneUtil.isMobileNO(phoneNum)) {
            return ResultUtil.failed(ConstString.MESSAGE_PHONE_NUM_INVALID);
        }

        // 3.拼接随机验证码的KEY
        String randomCodeKey = ConstString.Redis.REGISTER_CODE_PREFIX + phoneNum;

        // 4.远程调用redis-provider的方法获取对应的验证码值
        BasicResult<String> resultEntity = redisRemoteService.retrieveStringValueByStringKey(randomCodeKey);

        if (ResultUtil.FAILED.equals(resultEntity.getCode())) {
            return resultEntity;
        }

        String randomCodeRedis = resultEntity.getData();

        // 5.没有查询到值：返回失败信息，停止执行
        if (randomCodeRedis == null) {
            return ResultUtil.failed(ConstString.MESSAGE_CODE_NOT_EXISTS);
        }

        // 6.进行比较
        if (!Objects.equals(randomCode, randomCodeRedis)) {
            return ResultUtil.failed(ConstString.MESSAGE_CODE_NOT_MATCH);
        }

        // 7.从Redis中删除当前KEY对应的验证码
        BasicResult<String> resultEntityRemoveByKey = redisRemoteService.removeByKey(randomCodeKey);

        if (ResultUtil.FAILED.equals(resultEntityRemoveByKey.getCode())) {
            return resultEntityRemoveByKey;
        }

        // 8.远程调用database-provider方法检查登录账号是否被占用
        String loginacct = memberVO.getLoginacct();
        BasicResult<Integer> resultEntityRetrieveLoginAcctCount = dataBaseOperationRemoteService.retrieveLoignAcctCount(loginacct);

        if (ResultUtil.FAILED.equals(resultEntityRetrieveLoginAcctCount.getCode())) {
            return ResultUtil.failed(resultEntityRetrieveLoginAcctCount.getMsg());
        }

        Integer count = resultEntityRetrieveLoginAcctCount.getData();

        // 9.已经被占用：返回失败信息，停止执行
        if (count > 0) {
            return ResultUtil.failed(ConstString.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
        }

        // 10.密码加密
        String userpswd = memberVO.getUserpswd();
        String userpswdEncoded = PasswordUtils.encode(userpswd, randomCodeRedis);
        memberVO.setUserpswd(userpswdEncoded);

        // 11.远程调用database-provider方法执行保存操作

        MemberPO memberPO = new MemberPO();

        // 调用Spring提供的BeanUtils.copyProperties()工具类直接完成属性值注入
        BeanUtils.copyProperties(memberVO, memberPO);
        memberPO.setSalt(randomCodeRedis);
        return dataBaseOperationRemoteService.saveMemberRemote(memberPO);
    }

    @RequestMapping("/member/manager/send/code")
    public BasicResult<String> sendCode(@RequestParam("phoneNum") String phoneNum) {

        if (!PhoneUtil.isMobileNO(phoneNum)) {
            return ResultUtil.failed(ConstString.MESSAGE_PHONE_NUM_INVALID);
        }

        // 1.生成验证码
        int length = 4;
        String randomCode = CrowdUtils.randomCode(length);

        // 2.保存到Redis


        String normalKey = ConstString.Redis.REGISTER_CODE_PREFIX + phoneNum;

        BasicResult<String> resultEntity = redisRemoteService.saveNormalStringKeyValue(normalKey, randomCode, timeoutMinute);

        if (ResultUtil.FAILED.equals(resultEntity.getCode())) {
            return resultEntity;
        }

        // 3.发送验证码到用户手机
        // String appcode = "61f2eaa3c43e42ad82c26fbbe1061311";
        try {
            MessageSendUtil.sendRegister(phoneNum, randomCode);

            return ResultUtil.successNoData();
        } catch (Exception e) {
            e.printStackTrace();

            return ResultUtil.failed(e.getMessage());
        }

    }

    @PostMapping("/member/manager/login")
    public BasicResult<LoginVO> login(@RequestBody MemberVO memberVO) {

        MemberVoToken token = new MemberVoToken(memberVO);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            if (subject.isAuthenticated()) {
                LoginVO loginVO = new LoginVO();
                MemberPO memberPO = (MemberPO) subject.getPrincipal();
                BeanUtils.copyProperties(memberPO, loginVO);
                String accessToken = JwtTokenUtil.getAccessToken(memberVO.getLoginacct(), null);
                loginVO.setToken(accessToken);
                redisRemoteService.saveNormalStringKeyValue(ConstString.Redis.LOGIN_MEMBER_TOKEN_PREFIX + accessToken, memberPO.getId().toString(), ConstString.Redis.LOGIN_MEMBER_TOKENTIME);

                return ResultUtil.successWithData(loginVO);
            } else {
                return ResultUtil.failed(ConstString.MESSAGE_LOGINACCT_FAILED);
            }

        } catch (IncorrectCredentialsException e) {
            return ResultUtil.failed(ConstString.MESSAGE_LOGINACCT_FAILED);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.failed(e.getMessage());
        }

    }

    @PostMapping("/member/manager/logout")
    public BasicResult<LoginVO> logout(@RequestBody MemberVO memberVO) {

        SecurityUtils.getSubject().logout();

        return ResultUtil.successNoData();
    }

}
