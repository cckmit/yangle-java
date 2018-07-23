package com.ater.modules.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-04 15:42:00
 */
public class YFetalHeartEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//自增字段（知识ID）
	private String heartId;

	private String	moveId;

	//
	private String heartRate;
	//宫所
	private Double uterineContraction;
	//胎心时间 以秒为单位
	private int  heartTime;
	//删除状态，0：正常，1：已删除
	private Integer delStatus;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;

	/**
	 * 设置：自增字段（知识ID）
	 */
	public void setHeartId(String heartId) {
		this.heartId = heartId;
	}
	/**
	 * 获取：自增字段（知识ID）
	 */
	public String getHeartId() {
		return heartId;
	}

	/**
	 * 设置：
	 */
	public void setHeartRate(String heartRate) {
		this.heartRate = heartRate;
	}
	/**
	 * 获取：
	 */
	public String getHeartRate() {
		return heartRate;
	}
	/**
	 * 设置：宫所
	 */
	public void setUterineContraction(Double uterineContraction) {
		this.uterineContraction = uterineContraction;
	}
	/**
	 * 获取：宫所
	 */
	public Double getUterineContraction() {
		return uterineContraction;
	}
	/**
	 * 设置：胎心时间 以秒为单位
	 */
	public int getHeartTime() {
		return heartTime;
	}

	public void setHeartTime(int heartTime) {
		this.heartTime = heartTime;
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

	public String getMoveId() {
		return moveId;
	}

	public void setMoveId(String moveId) {
		this.moveId = moveId;
	}
}
