package com.zangs.crow.database.service.api;


import com.zangs.crow.base.bean.member.MemberPO;
import com.zangs.crow.base.service.BaseServiceInf;

public interface MemberService extends BaseServiceInf<MemberPO> {

    int getLoginAcctCount(String loginAcct);

    void saveMemberPO(MemberPO memberPO);

}
