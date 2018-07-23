package com.ater.modules.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author
 * @email
 * @date
 */
public class TaskEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private Date createDate;
	//
	private String platform;
	//
	private int result;
	//
	private String relatedInfo;
	//
	private String mqid;
	//
	private String jobid;

    private String users;

    private String params;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * 设置：
	 */
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	/**
	 * 获取：
	 */
	public String getPlatform() {
		return platform;
	}
	/**
	 * 设置：
	 */
	public String getRelatedInfo() {
		return relatedInfo;
	}

	public void setRelatedInfo(String relatedInfo) {
		this.relatedInfo = relatedInfo;
	}

	public String getMqid() {
		return mqid;
	}

	public void setMqid(String mqid) {
		this.mqid = mqid;
	}

	/**
	 * 设置：
	 */
	public void setResult(int result) {
		this.result = result;
	}
	/**
	 * 获取：
	 */
	public int getResult() {
		return result;
	}
	/**
	 * 设置：
	 */
	public void setMQID(String mqid) {
		this.mqid = mqid;
	}
	/**
	 * 获取：
	 */
	public String getMQID() {
		return mqid;
	}

	/**
	 * 设置：
	 */
	public void setJobid(String jobid) {
		this.jobid = jobid;
	}
	/**
	 * 获取：
	 */
	public String getJobid() {
		return jobid;
	}

    /**
     * 设置：
     */
    public void setUsers(String users) {
        this.users = users;
    }
    /**
     * 获取：
     */
    public String getUsers() {
        return users;
    }

    /**
     * 设置：
     */
    public void setParams(String params) {
        this.params = params;
    }
    /**
     * 获取：
     */
    public String getParams() {
        return params;
    }

}
