package com.ater.modules.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-07 17:24:47
 */
public class YSignInEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//自增字段
	private String id;
	//用户id
	private String userId;
	//签到时间
	private Date signTime;
	//标记
	private String remark;
	//累计签到次数
	private Integer signTotalTimes;
	//连续签到
	private Integer signSerialTimes;
	//删除状态，0:正常，1:已删除
	private Integer delStatus;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;

	/**
	 * 设置：自增字段
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：自增字段
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：用户id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：签到时间
	 */
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	/**
	 * 获取：签到时间
	 */
	public Date getSignTime() {
		return signTime;
	}
	/**
	 * 设置：标记
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取：标记
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * 设置：累计签到次数
	 */
	public void setSignTotalTimes(Integer signTotalTimes) {
		this.signTotalTimes = signTotalTimes;
	}
	/**
	 * 获取：累计签到次数
	 */
	public Integer getSignTotalTimes() {
		return signTotalTimes;
	}
	/**
	 * 设置：连续签到
	 */
	public void setSignSerialTimes(Integer signSerialTimes) {
		this.signSerialTimes = signSerialTimes;
	}
	/**
	 * 获取：连续签到
	 */
	public Integer getSignSerialTimes() {
		return signSerialTimes;
	}
	/**
	 * 设置：删除状态，0:正常，1:已删除
	 */
	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}
	/**
	 * 获取：删除状态，0:正常，1:已删除
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
