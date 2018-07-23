package com.ater.modules.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-07 18:00:25
 */
public class YPointEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//自增字段
	private String pointId;
	//
	private String userId;
	//积分数
	private Integer point;
	//积分类型 1：签到
	private Integer pointType;
	//删除状态，0:正常，1:已删除
	private Integer delStatus;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;

	/**
	 * 设置：自增字段
	 */
	public void setPointId(String pointId) {
		this.pointId = pointId;
	}
	/**
	 * 获取：自增字段
	 */
	public String getPointId() {
		return pointId;
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
	 * 设置：积分数
	 */
	public void setPoint(Integer point) {
		this.point = point;
	}
	/**
	 * 获取：积分数
	 */
	public Integer getPoint() {
		return point;
	}
	/**
	 * 设置：积分类型 1：签到
	 */
	public void setPointType(Integer pointType) {
		this.pointType = pointType;
	}
	/**
	 * 获取：积分类型 1：签到
	 */
	public Integer getPointType() {
		return pointType;
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
