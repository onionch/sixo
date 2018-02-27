package com.onionch.webapp.website.bean;

/**
 * Created by onionch on 4/24/17.
 */
public class User extends BaseBean {
    private String uId;
    private String realName;
    private String mailAddr;
    private String phoneNum;
    private String userName;
    private String userPassword;
    private String roleSerialNum;
    private Role userRole;
    private int deleted =0;

    public String getRoleSerialNum() {
        return roleSerialNum;
    }

    public void setRoleSerialNum(String roleSerialNum) {
        this.roleSerialNum = roleSerialNum;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMailAddr() {
        return mailAddr;
    }

    public void setMailAddr(String mailAddr) {
        this.mailAddr = mailAddr;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User [id=" + uId + ", name=" + userName + ", roleSerialNum=" + roleSerialNum + "]";
    }
}
