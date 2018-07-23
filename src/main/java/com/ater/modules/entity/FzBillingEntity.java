package com.ater.modules.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


/**
 *
 *
 * @author Onpu
 * @date 2018-01-15 09:49:19
 */
public class FzBillingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

    /**
     * 主键
	 */
	private Long id;
    /**
     * 合同编号
	 */
	@Excel(name = "合同编号")
	@NotNull
	private String caseNo;
    /**
     * 客户名称
	 */
	@Excel(name = "客户名称")
	private String customerName;
    /**
     * 合同金额（引入合同管理的数据，无数据显示0）
	 */

	private Double caseAmount;
    /**
     * 已收金额
	 */
	private Double receivedAmount;
    /**
     * 主办律师
	 */

	private String hostLawyer;


	@Excel(name = "承办律师")
	private String undertakeLawyer;

    /**
     * 合同-已收金额，负数置为0
	 */
	private Double receivableAmount;

    /**
     * 开票日期
	 */
	@Excel(name = "开票日期", format = "yyyy-MM-dd")
	private Date openDate;
    /**
     * 发票号码
	 */
	@Excel(name = "发票号码")
	private String invoiceNumber;
    /**
     * 发票金额
	 */
	private Double invoiceAmount;

	@Excel(name = "金额")
	private Double amount;

	@Excel(name = "税率")
	private String tax;
	/**
	 * 税额
	 */
	@Excel(name = "税额")
	private Double taxAmount;
    /**
     * 发票抬头（必须与客户名称一致）
	 */
	@Excel(name = "购方企业名称")
	private String invoiceTitle;
    /**
     * 发票类型（1专用发票、2普通发票）
	 */
	private Integer invoiceType;
    /**
     * 状态（1正常、2作废）
	 */
	//@Excel(name = "状态",replace = { "作废_2", "正常_1" })
	private Integer state;
    /**
     * 备注
	 */
	//@Excel(name = "备注")
	private String note;

	/**
	 * 清款日期
	 */
	private Date payedDate;

	/**
     * 删除状态(0：有效,1：无效)
	 */
	private Integer isDeleted;
    /**
     *
	 */
	private Date gmtCreate;
    /**
     * 更新时间
	 */
	private Date gmtModified;

	public String getUndertakeLawyer() {
		return undertakeLawyer;
	}

	public void setUndertakeLawyer(String undertakeLawyer) {
		this.undertakeLawyer = undertakeLawyer;
	}
	/**
	 * 设置：主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：合同编号
	 */
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	/**
	 * 获取：合同编号
	 */
	public String getCaseNo() {
		return caseNo;
	}
	/**
	 * 设置：客户名称
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * 获取：客户名称
	 */
	public String getCustomerName() {
		return customerName;
	}
	/**
	 * 设置：合同金额（引入合同管理的数据，无数据显示0）
	 */
	public void setCaseAmount(Double caseAmount) {
		this.caseAmount = caseAmount;
	}
	/**
	 * 获取：合同金额（引入合同管理的数据，无数据显示0）
	 */
	public Double getCaseAmount() {
		return caseAmount;
	}
	/**
	 * 设置：已收金额
	 */
	public void setReceivedAmount(Double receivedAmount) {
		this.receivedAmount = receivedAmount;
	}
	/**
	 * 获取：已收金额
	 */
	public Double getReceivedAmount() {
		return receivedAmount;
	}
	/**
	 * 设置：主办律师
	 */
	public void setHostLawyer(String hostLawyer) {
		this.hostLawyer = hostLawyer;
	}
	/**
	 * 获取：主办律师
	 */
	public String getHostLawyer() {
		return hostLawyer;
	}
	/**
	 * 设置：合同-已收金额，负数置为0
	 */
	public void setReceivableAmount(Double receivableAmount) {
		this.receivableAmount = receivableAmount;
	}
	/**
	 * 获取：合同-已收金额，负数置为0
	 */
	public Double getReceivableAmount() {
		return receivableAmount;
	}

	public String getTax() {
		return tax;
	}

	public void setTax(String tax) {
		this.tax = tax;
	}

	/**
	 * 设置：开票日期
	 */
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	/**
	 * 获取：开票日期
	 */
	public Date getOpenDate() {
		return openDate;
	}
	/**
	 * 设置：发票号码
	 */
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	/**
	 * 获取：发票号码
	 */
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	/**
	 * 设置：发票金额
	 */
	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}
	/**
	 * 获取：发票金额
	 */
	public Double getInvoiceAmount() {
		return invoiceAmount;
	}
	/**
	 * 设置：发票抬头（必须与客户名称一致）
	 */
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	/**
	 * 获取：发票抬头（必须与客户名称一致）
	 */
	public String getInvoiceTitle() {
		return invoiceTitle;
	}
	/**
	 * 设置：发票类型（1专用发票、2普通发票）
	 */
	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}
	/**
	 * 获取：发票类型（1专用发票、2普通发票）
	 */
	public Integer getInvoiceType() {
		return invoiceType;
	}
	/**
	 * 设置：状态（1正常、2作废）
	 */
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * 设置：备注
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * 获取：备注
	 */
	public String getNote() {
		return note;
	}

	public Double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * 设置：删除状态(0：有效,1：无效)
	 */
	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 获取：删除状态(0：有效,1：无效)
	 */
	public Integer getIsDeleted() {
		return isDeleted;
	}
	/**
	 * 设置：
	 */
	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	/**
	 * 获取：
	 */
	public Date getGmtCreate() {
		return gmtCreate;
	}
	/**
	 * 设置：更新时间
	 */
	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getGmtModified() {
		return gmtModified;
	}

	public Date getPayedDate() {
		return payedDate;
	}

	public void setPayedDate(Date payedDate) {
		this.payedDate = payedDate;
	}
}
