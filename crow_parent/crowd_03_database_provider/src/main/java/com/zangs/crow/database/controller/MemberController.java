package com.zangs.crow.database.controller;

import com.zangs.crow.base.bean.BasicResult;
import com.zangs.crow.base.bean.member.MemberPO;
import com.zangs.crow.base.bean.member.MemberVO;
import com.zangs.crow.common.constant.ConstString;
import com.zangs.crow.common.util.CrowdUtils;
import com.zangs.crow.common.util.ResultUtil;
import com.zangs.crow.database.service.api.MemberService;
import org.nutz.dao.Cnd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @RequestMapping("/retrieve/loign/acct/count")
    public BasicResult<Integer> retrieveLoignAcctCount(
            @RequestParam("loginAcct") String loginAcct) {

        if (!CrowdUtils.strEffectiveCheck(loginAcct)) {
            return ResultUtil.failed(ConstString.MESSAGE_LOGINACCT_INVALID);
        }

        try {
            int count = memberService.getLoginAcctCount(loginAcct);

            return ResultUtil.successWithData(count);

        } catch (Exception e) {
            e.printStackTrace();

            return ResultUtil.failed(e.getMessage());
        }
    }

    @RequestMapping("/save/member/remote")
    public BasicResult<String> saveMemberRemote(@RequestBody MemberPO memberPO) {

        try {
            // 执行保存
            memberService.saveMemberPO(memberPO);
        } catch (Exception e) {
            e.printStackTrace();

            return ResultUtil.failed(e.getMessage());
        }

        return ResultUtil.successNoData();

    }

    @RequestMapping("/retrieve/member/by/login/VO")
    public BasicResult<MemberPO> retrieveMemberByVO(@RequestBody MemberVO memberVO) {
        try {
            MemberPO memberPO = memberService.query(Cnd.where("loginacct", "=", memberVO.getLoginacct())).get(0);
            return ResultUtil.successWithData(memberPO);
        } catch (Exception e) {
            return ResultUtil.failed();
        }

    }


}
