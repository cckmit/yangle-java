package com.ater.modules.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-05-31 19:02:50
 */
public class YPregnancyEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//自增字段（用户ID）
	private String perId;
    //设备I
	private String  equipmentId;
	//末次月经
	private Date lastMenstruation;
	//预产日期
	private Date pregnancyDate;
	//怀孕周数
	private String pregnancyWeek;
	//距离宝宝出生的天数
	private String babyBirth;
    //生日
	private Date birthday;
	//删除状态，0：正常，1：已删除
	private Integer delStatus;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;

	/**
	 * 设置：自增字段（用户ID）
	 */
	public void setPerId(String perId) {
		this.perId = perId;
	}
	/**
	 * 获取：自增字段（用户ID）
	 */
	public String getPerId() {
		return perId;
	}
	/**
	 * 设置：末次月经
	 */
	public void setLastMenstruation(Date lastMenstruation) {
		this.lastMenstruation = lastMenstruation;
	}
	/**
	 * 获取：末次月经
	 */
	public Date getLastMenstruation() {
		return lastMenstruation;
	}
	/**
	 * 设置：预产日期
	 */
	public void setPregnancyDate(Date pregnancyDate) {
		this.pregnancyDate = pregnancyDate;
	}
	/**
	 * 获取：预产日期
	 */
	public Date getPregnancyDate() {
		return pregnancyDate;
	}
	/**
	 * 设置：怀孕周数
	 */
	public void setPregnancyWeek(String pregnancyWeek) {
		this.pregnancyWeek = pregnancyWeek;
	}
	/**
	 * 获取：怀孕周数
	 */
	public String getPregnancyWeek() {
		return pregnancyWeek;
	}
	/**
	 * 设置：距离宝宝出生的天数
	 */
	public void setBabyBirth(String babyBirth) {
		this.babyBirth = babyBirth;
	}
	/**
	 * 获取：距离宝宝出生的天数
	 */
	public String getBabyBirth() {
		return babyBirth;
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

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}


	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
