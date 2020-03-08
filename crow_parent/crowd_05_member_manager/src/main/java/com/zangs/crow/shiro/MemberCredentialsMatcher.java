package com.zangs.crow.shiro;

import com.zangs.crow.base.bean.member.MemberPO;
import com.zangs.crow.base.bean.member.MemberVO;
import com.zangs.crow.common.util.PasswordUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;

public class MemberCredentialsMatcher implements CredentialsMatcher {
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        MemberPO MemberPO = (MemberPO) info.getPrincipals().getPrimaryPrincipal();
        MemberVO memberVO = (MemberVO) token.getPrincipal();
        return PasswordUtils.matches(MemberPO.getSalt(), memberVO.getUserpswd(), MemberPO.getUserpswd());
//        return false;
    }
}
