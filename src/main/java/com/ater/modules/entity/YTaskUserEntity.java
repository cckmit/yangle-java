package com.ater.modules.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-08 16:16:00
 */
public class YTaskUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String taskUserId;
	//自增字段（知识ID）
	private String taskId;
	//
	private String userId;
	//完成状态 0：待解锁  1：未完成 2 已完成
	private Integer taskStatus;
	//删除状态，0：正常，1：已删除
	private Integer delStatus;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;
	//任务优先级
	private int taskPriority;

	public int getTaskPriority() {
		return taskPriority;
	}

	public void setTaskPriority(int taskPriority) {
		this.taskPriority = taskPriority;
	}

	/**
	 * 设置：
	 */
	public void setTaskUserId(String taskUserId) {
		this.taskUserId = taskUserId;
	}
	/**
	 * 获取：
	 */
	public String getTaskUserId() {
		return taskUserId;
	}
	/**
	 * 设置：自增字段（知识ID）
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	/**
	 * 获取：自增字段（知识ID）
	 */
	public String getTaskId() {
		return taskId;
	}
	/**
	 * 设置：
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：完成状态 0：待解锁  1：未完成 2 已完成
	 */
	public void setTaskStatus(Integer taskStatus) {
		this.taskStatus = taskStatus;
	}
	/**
	 * 获取：完成状态 0：待解锁  1：未完成 2 已完成
	 */
	public Integer getTaskStatus() {
		return taskStatus;
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
}
