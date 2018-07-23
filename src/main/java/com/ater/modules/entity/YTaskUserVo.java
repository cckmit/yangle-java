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
public class YTaskUserVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String taskUserId;
	//自增字段（知识ID）
	private String taskId;
	//
	private String userId;
	//完成状态 0：待解锁  1：未完成 2 已完成

	private String taskContent;

	private String taskName;

	private Integer taskStatus;

	private String taskPicture;

	//任务优先级
	private int taskPriority;

	public String getTaskPicture() {
		return taskPicture;
	}

	public void setTaskPicture(String taskPicture) {
		this.taskPicture = taskPicture;
	}

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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getTaskContent() {
		return taskContent;
	}

	public void setTaskContent(String taskContent) {
		this.taskContent = taskContent;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
}
