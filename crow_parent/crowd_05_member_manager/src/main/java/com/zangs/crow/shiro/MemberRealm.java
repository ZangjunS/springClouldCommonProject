package com.zangs.crow.shiro;

import com.zangs.crow.api.DataBaseOperationRemoteService;
import com.zangs.crow.base.bean.BasicResult;
import com.zangs.crow.base.bean.member.MemberPO;
import com.zangs.crow.base.bean.member.MemberVO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberRealm extends AuthorizingRealm {
    @Autowired
    DataBaseOperationRemoteService dbService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        MemberVO memberVO = (MemberVO) token.getPrincipal();
        BasicResult<MemberPO> memberByVO = dbService.retrieveMemberByVO(memberVO);
        MemberPO memberPO = memberByVO.getData();
        if (memberPO == null) {
            return new SimpleAuthenticationInfo();
        }

        return new SimpleAuthenticationInfo(memberPO, memberPO.getUserpswd(), this.getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof MemberVoToken;
    }
}
