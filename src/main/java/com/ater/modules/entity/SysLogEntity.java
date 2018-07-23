package com.ater.modules.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 */
public class SysLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//ID
	private Long id;

	//操作人
	private String userName;

	//操作时间
	private Date operationTime;

	//操作内容
	private String operationContent;

	//执行时长(毫秒)
	private Long executePhase;

	//IP地址
	private String userIp;


	/**
	 * 设置：ID
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 获取：ID
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 设置：操作人
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * 获取：操作人
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 设置：操作时间
	 */
	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

	/**
	 * 获取：操作时间
	 */
	public Date getOperationTime() {
		return operationTime;
	}

	/**
	 * 设置：操作内容
	 */
	public void setOperationContent(String operationContent) {
		this.operationContent = operationContent;
	}

	/**
	 * 获取：操作内容
	 */
	public String getOperationContent() {
		return operationContent;
	}

	/**
	 * 设置：执行时长(毫秒)
	 */
	public void setExecutePhase(Long executePhase) {
		this.executePhase = executePhase;
	}

	/**
	 * 获取：执行时长(毫秒)
	 */
	public Long getExecutePhase() {
		return executePhase;
	}

	/**
	 * 设置：IP地址
	 */
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	/**
	 * 获取：IP地址
	 */
	public String getUserIp() {
		return userIp;
	}

}
