package com.zangs.crow.shiro;

import com.zangs.crow.base.bean.member.MemberVO;
import org.apache.shiro.authc.UsernamePasswordToken;

public class MemberVoToken extends UsernamePasswordToken {
    MemberVO memberVO;

    public MemberVoToken(MemberVO memberVO) {
        this.memberVO = memberVO;
    }

    @Override
    public Object getPrincipal() {
        return memberVO;
    }

    @Override
    public Object getCredentials() {
        return memberVO.getUserpswd();
    }

    @Override
    public String getUsername() {
        return memberVO.getLoginacct();
    }

    @Override
    public char[] getPassword() {
        return memberVO.getUserpswd().toCharArray();
    }
}
