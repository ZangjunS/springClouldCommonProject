package com.zangs.crow.api;

import com.zangs.crow.base.bean.BasicResult;
import com.zangs.crow.base.bean.member.MemberPO;
import com.zangs.crow.base.bean.member.MemberVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("database-provider")
public interface DataBaseOperationRemoteService {

    @RequestMapping("/retrieve/loign/acct/count")
    BasicResult<Integer> retrieveLoignAcctCount(@RequestParam("loginAcct") String loginAcct);

    @RequestMapping("/save/member/remote")
    BasicResult<String> saveMemberRemote(@RequestBody MemberPO memberPO);

    @RequestMapping("/retrieve/member/by/login/VO")
    BasicResult<MemberPO> retrieveMemberByVO(@RequestBody MemberVO memberVO);
}
