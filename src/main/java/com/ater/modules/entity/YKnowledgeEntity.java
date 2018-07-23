package com.ater.modules.entity;

import java.io.Serializable;
import java.util.Date;


/**
 *
 *
 * @author ater
 * @email ${email}
 * @date 2018-06-01 15:00:41
 */
public class YKnowledgeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//自增字段（知识ID）
	private String konwId;
	//知识图片
	private String knowPicture;
	//知识名称
	private String knowName;
	//知识内容
	private String knowContent;
	//删除状态，0：正常，1：已删除
	private Integer delStatus;
	//创建时间
	private String gmtCreate;
	//修改时间
	private String gmtModified;
	//大图
	private String knowBigPicture;
	//作者
	private String author;
	//tip
	private String knowTip;

	private String knowBrief;

	public String getKnowTip() {
		return knowTip;
	}

	public void setKnowTip(String knowTip) {
		this.knowTip = knowTip;
	}

	public String getKnowBigPicture() {
		return knowBigPicture;
	}

	public String getAuthor() {
		return author;
	}

	public void setKnowBigPicture(String knowBigPicture) {
		this.knowBigPicture = knowBigPicture;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * 设置：自增字段（知识ID）
	 */
	public void setKonwId(String konwId) {
		this.konwId = konwId;
	}
	/**
	 * 获取：自增字段（知识ID）
	 */
	public String getKonwId() {
		return konwId;
	}
	/**
	 * 设置：知识图片
	 */
	public void setKnowPicture(String knowPicture) {
		this.knowPicture = knowPicture;
	}
	/**
	 * 获取：知识图片
	 */
	public String getKnowPicture() {
		return knowPicture;
	}
	/**
	 * 设置：知识名称
	 */
	public void setKnowName(String knowName) {
		this.knowName = knowName;
	}
	/**
	 * 获取：知识名称
	 */
	public String getKnowName() {
		return knowName;
	}
	/**
	 * 设置：知识内容
	 */
	public void setKnowContent(String knowContent) {
		this.knowContent = knowContent;
	}
	/**
	 * 获取：知识内容
	 */
	public String getKnowContent() {
		return knowContent;
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
	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	/**
	 * 获取：创建时间
	 */
	public String getGmtCreate() {
		return gmtCreate;
	}
	/**
	 * 设置：修改时间
	 */
	public void setGmtModified(String gmtModified) {
		this.gmtModified = gmtModified;
	}
	/**
	 * 获取：修改时间
	 */
	public String getGmtModified() {
		return gmtModified;
	}

	public String getKnowBrief() {
		return knowBrief;
	}

	public void setKnowBrief(String knowBrief) {
		this.knowBrief = knowBrief;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
}
