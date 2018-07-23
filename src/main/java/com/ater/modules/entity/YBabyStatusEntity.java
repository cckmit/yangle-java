package com.ater.modules.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-01 16:01:35
 */
public class YBabyStatusEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//自增字段（用户ID）
	private String badyId;

	private String badyPicture;
	//身长
	private Double babyLength;
	//胎重
	private Double fetalWeight;
	//双顶径
	private Double doubleTopDiameter;
	//胎儿发育
	private String developFetal;
	//妈妈变化
	private String motherChange;
	//删除状态，0：正常，1：已删除
	private Integer delStatus;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;

	private String pregnancyWeek;
	//距离宝宝出生的天数
	private long babyBirth;

	private String fetalDetail;

	/**
	 * 设置：自增字段（用户ID）
	 */
	public void setBadyId(String badyId) {
		this.badyId = badyId;
	}
	/**
	 * 获取：自增字段（用户ID）
	 */
	public String getBadyId() {
		return badyId;
	}
	/**
	 * 设置：身长
	 */
	public void setBabyLength(Double babyLength) {
		this.babyLength = babyLength;
	}
	/**
	 * 获取：身长
	 */
	public Double getBabyLength() {
		return babyLength;
	}
	/**
	 * 设置：胎重
	 */
	public void setFetalWeight(Double fetalWeight) {
		this.fetalWeight = fetalWeight;
	}
	/**
	 * 获取：胎重
	 */
	public Double getFetalWeight() {
		return fetalWeight;
	}
	/**
	 * 设置：双顶径
	 */
	public void setDoubleTopDiameter(Double doubleTopDiameter) {
		this.doubleTopDiameter = doubleTopDiameter;
	}
	/**
	 * 获取：双顶径
	 */
	public Double getDoubleTopDiameter() {
		return doubleTopDiameter;
	}
	/**
	 * 设置：胎儿发育
	 */
	public void setDevelopFetal(String developFetal) {
		this.developFetal = developFetal;
	}
	/**
	 * 获取：胎儿发育
	 */
	public String getDevelopFetal() {
		return developFetal;
	}
	/**
	 * 设置：妈妈变化
	 */
	public void setMotherChange(String motherChange) {
		this.motherChange = motherChange;
	}
	/**
	 * 获取：妈妈变化
	 */
	public String getMotherChange() {
		return motherChange;
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

	public String getBadyPicture() {
		return badyPicture;
	}

	public void setBadyPicture(String badyPicture) {
		this.badyPicture = badyPicture;
	}

	public String getPregnancyWeek() {
		return pregnancyWeek;
	}

	public void setPregnancyWeek(String pregnancyWeek) {
		this.pregnancyWeek = pregnancyWeek;
	}

	public long getBabyBirth() {
		return babyBirth;
	}

	public void setBabyBirth(long babyBirth) {
		this.babyBirth = babyBirth;
	}

	public String getFetalDetail() {
		return fetalDetail;
	}

	public void setFetalDetail(String fetalDetail) {
		this.fetalDetail = fetalDetail;
	}
}
