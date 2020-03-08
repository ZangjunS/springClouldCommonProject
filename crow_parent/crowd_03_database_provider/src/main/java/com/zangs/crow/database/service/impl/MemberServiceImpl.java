package com.zangs.crow.database.service.impl;

import com.zangs.crow.base.bean.member.MemberPO;
import com.zangs.crow.base.service.BaseServiceImpl;
import com.zangs.crow.database.service.api.MemberService;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.sql.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class MemberServiceImpl extends BaseServiceImpl<MemberPO> implements MemberService {

    @Autowired
    public MemberServiceImpl(Dao dao) {
        super(dao);
    }

    @Override
    public int getLoginAcctCount(String loginAcct) {
//
//        MemberPOExample example = new MemberPOExample();
//
//        example.createCriteria().andLoginacctEqualTo(loginAcct);


        return count(Cnd.where("loginacct", "=", loginAcct));
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void saveMemberPO(MemberPO memberPO) {
        insert(memberPO);
    }

}
