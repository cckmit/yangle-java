package com.ater.modules.entity;

import java.io.Serializable;
import java.util.Date;


public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    //自增字段（用户ID）
    private String userId;
    //设备Id
    private String equipmentId;
    //手机号码
    private String userAccount;
    //用户名称（昵称）
    private String userName;
    //登录密码
    private String userPassword;
    //头像
    private String userIcon;
    //用户角色 1孕妇 2机构
    private Integer userRole;
    //邮箱
    private String userEmail;
    //性别,1:男，2：女
    private Integer userSex;
    //地址
    private String userAddress;

    //第三方appId
    private String appId;
    //
    private String qqOpenId;
    //
    private String weixinOpenId;
    //
    private String weiboOpenId;

    private String token;
    //删除状态，0：正常，1：已删除
    private Integer delStatus;
    //创建时间
    private Date gmtCreate;
    //修改时间
    private Date gmtModified;

    private Date lastMenstruation;

    private Date  birthday;

    /**
     * 设置：自增字段（用户ID）
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * 获取：自增字段（用户ID）
     */
    public String getUserId() {
        return userId;
    }
    /**
     * 设置：设备Id
     */
    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }
    /**
     * 获取：设备Id
     */
    public String getEquipmentId() {
        return equipmentId;
    }
    /**
     * 设置：手机号码
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
    /**
     * 获取：手机号码
     */
    public String getUserAccount() {
        return userAccount;
    }
    /**
     * 设置：用户名称（昵称）
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * 获取：用户名称（昵称）
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 设置：登录密码
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    /**
     * 获取：登录密码
     */
    public String getUserPassword() {
        return userPassword;
    }
    /**
     * 设置：头像
     */
    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }
    /**
     * 获取：头像
     */
    public String getUserIcon() {
        return userIcon;
    }
    /**
     * 设置：用户角色 1孕妇 2机构
     */
    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }
    /**
     * 获取：用户角色 1孕妇 2机构
     */
    public Integer getUserRole() {
        return userRole;
    }
    /**
     * 设置：邮箱
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    /**
     * 获取：邮箱
     */
    public String getUserEmail() {
        return userEmail;
    }
    /**
     * 设置：性别,1:男，2：女
     */
    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }
    /**
     * 获取：性别,1:男，2：女
     */
    public Integer getUserSex() {
        return userSex;
    }
    /**
     * 设置：地址
     */
    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
    /**
     * 获取：地址
     */
    public String getUserAddress() {
        return userAddress;
    }

    /**
     * 设置：第三方appId
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }
    /**
     * 获取：第三方appId
     */
    public String getAppId() {
        return appId;
    }
    /**
     * 设置：
     */
    public void setQqOpenId(String qqOpenId) {
        this.qqOpenId = qqOpenId;
    }
    /**
     * 获取：
     */
    public String getQqOpenId() {
        return qqOpenId;
    }
    /**
     * 设置：
     */
    public void setWeixinOpenId(String weixinOpenId) {
        this.weixinOpenId = weixinOpenId;
    }
    /**
     * 获取：
     */
    public String getWeixinOpenId() {
        return weixinOpenId;
    }
    /**
     * 设置：
     */
    public void setWeiboOpenId(String weiboOpenId) {
        this.weiboOpenId = weiboOpenId;
    }
    /**
     * 获取：
     */
    public String getWeiboOpenId() {
        return weiboOpenId;
    }
    /**
     * 设置：删除状态，0：正常，1：已删除
     */
    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }
    /**
     * 获取：删除状态，0：正常，1：已删除
     */
    public Integer getDelStatus() {
        return delStatus;
    }
    /**
     * 设置：创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
    /**
     * 获取：创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }
    /**
     * 设置：修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }
    /**
     * 获取：修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLastMenstruation() {
        return lastMenstruation;
    }

    public void setLastMenstruation(Date lastMenstruation) {
        this.lastMenstruation = lastMenstruation;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
