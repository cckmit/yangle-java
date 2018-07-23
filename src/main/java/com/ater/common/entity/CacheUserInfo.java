package com.ater.common.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * 缓存中的用户信息
 *
 * @author Onpu
 * @date 2017-04-01
 */
public class CacheUserInfo implements Serializable {
	
    private static final long serialVersionUID = 1L;
    //用户id
    private String userId;
    //token
    private String token;

    //过期时间
    private Date expireTime;

    public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	
}
