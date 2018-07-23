package com.ater.modules.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-01 15:07:15
 */
public class YSowingMapEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//自增字段（知识ID）
	private String sowId;
	//知识图片
	private String sowPicture;
	//删除状态，0：正常，1：已删除
	private Integer delStatus;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;

	/**
	 * 设置：自增字段（知识ID）
	 */
	public void setSowId(String sowId) {
		this.sowId = sowId;
	}
	/**
	 * 获取：自增字段（知识ID）
	 */
	public String getSowId() {
		return sowId;
	}
	/**
	 * 设置：知识图片
	 */
	public void setSowPicture(String sowPicture) {
		this.sowPicture = sowPicture;
	}
	/**
	 * 获取：知识图片
	 */
	public String getSowPicture() {
		return sowPicture;
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
