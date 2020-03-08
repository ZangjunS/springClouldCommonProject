package com.zangs.crow.base.bean.member;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Prev;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table("member")
public class MemberPO implements Serializable {
    @Id
    @Prev
    private Integer id;
    @Column
    private String loginacct;
    @Column
    private String userpswd;
    @Column

    private String phoneNum;
    @Column

    private String username;
    @Column
    private String salt;
    @Column
    private String email;
    @Column
    private Byte authstatus;
    @Column
    private Byte usertype;
    @Column
    private String realname;
    @Column
    private String cardnum;
    @Column
    private Byte accttype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginacct() {
        return loginacct;
    }

    public void setLoginacct(String loginacct) {
        this.loginacct = loginacct == null ? null : loginacct.trim();
    }

    public String getUserpswd() {
        return userpswd;
    }

    public void setUserpswd(String userpswd) {
        this.userpswd = userpswd == null ? null : userpswd.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {


        this.email = email == null ? null : email.trim();
    }

    public Byte getAuthstatus() {
        return authstatus;
    }

    public void setAuthstatus(Byte authstatus) {
        this.authstatus = authstatus;
    }

    public Byte getUsertype() {
        return usertype;
    }

    public void setUsertype(Byte usertype) {
        this.usertype = usertype;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getCardnum() {
        return cardnum;
    }

    public void setCardnum(String cardnum) {
        this.cardnum = cardnum == null ? null : cardnum.trim();
    }

    public Byte getAccttype() {
        return accttype;
    }

    public void setAccttype(Byte accttype) {
        this.accttype = accttype;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}