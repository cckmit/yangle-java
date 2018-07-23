package com.ater.modules.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-01 15:39:00
 */
public class YTipsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//自增字段（知识ID）
	private String tipId;
	//知识图片
	private String tipContent;
	//首页是否显示 0：未显示 1：显示
	private Integer isDisplay;
	//删除状态，0：正常，1：已删除
	private Integer delStatus;
	//创建时间
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;

	/**
	 * 设置：自增字段（知识ID）
	 */
	public void setTipId(String tipId) {
		this.tipId = tipId;
	}
	/**
	 * 获取：自增字段（知识ID）
	 */
	public String getTipId() {
		return tipId;
	}
	/**
	 * 设置：知识图片
	 */
	public void setTipContent(String tipContent) {
		this.tipContent = tipContent;
	}
	/**
	 * 获取：知识图片
	 */
	public String getTipContent() {
		return tipContent;
	}
	/**
	 * 设置：首页是否显示 0：未显示 1：显示
	 */
	public void setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
	}
	/**
	 * 获取：首页是否显示 0：未显示 1：显示
	 */
	public Integer getIsDisplay() {
		return isDisplay;
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
