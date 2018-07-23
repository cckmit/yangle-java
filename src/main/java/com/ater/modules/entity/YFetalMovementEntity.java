package com.ater.modules.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 
 * 
 * @author ater
 * @email ${email}
 * @date 2018-06-05 14:01:08
 */
public class YFetalMovementEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//自增字段（知识ID）
	private String moveId;

	//用户Id
	private String userId;

	private String userAccount;

	private String readUserId;

	//平均心率
	private Double meanHeartRate;

	//状态 1：不合规  <20 分钟 、2：未提交>20分钟、3：待判读；4  已判读
	private Integer state;
   //结果状态 1：正常 2：异常
	private Integer  resultState;
	//监测时间
	private String monitoringTime;

	private Integer fetalMove;

	private String heartRecord;

	private String userName;

	private String doctor;
	//开始时间
	private Date startTime;

	private String readContent;

	private String nstResult;

    private String fischerResult;

    private String krebsResult;

    private String acogResult;

	private String	scoreData;
	//删除状态，0：正常，1：已删除
	private Integer delStatus;
	//
	private Date gmtCreate;
	//修改时间
	private Date gmtModified;

	private Date pregnancyDate;

	private Date  birthday;

    private Integer age;

    public Date getPregnancyDate() {
        return pregnancyDate;
    }

    public void setPregnancyDate(Date pregnancyDate) {
        this.pregnancyDate = pregnancyDate;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
	 * 设置：自增字段（知识ID）
	 */
	public void setMoveId(String moveId) {
		this.moveId = moveId;
	}
	/**
	 * 获取：自增字段（知识ID）
	 */
	public String getMoveId() {
		return moveId;
	}

	/**
	 * 设置：用户Id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户Id
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * 设置：平均心率
	 */
	public void setMeanHeartRate(Double meanHeartRate) {
		this.meanHeartRate = meanHeartRate;
	}
	/**
	 * 获取：平均心率
	 */
	public Double getMeanHeartRate() {
		return meanHeartRate;
	}
	/**
	 * 设置：状态 1：草稿 2：已提交 3：正常 4异常
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：状态 1：草稿 2：已提交 3：正常 4异常
	 */
	public Integer getState() {
		return state;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	/**
	 * 设置：开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	/**
	 * 获取：开始时间
	 */
	public Date getStartTime() {
		return startTime;
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

	public String getMonitoringTime() {
		return monitoringTime;
	}

	public void setMonitoringTime(String monitoringTime) {
		this.monitoringTime = monitoringTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getFetalMove() {
		return fetalMove;
	}

	public void setFetalMove(Integer fetalMove) {
		this.fetalMove = fetalMove;
	}

	public String getHeartRecord() {
		return heartRecord;
	}

	public void setHeartRecord(String heartRecord) {
		this.heartRecord = heartRecord;
	}

	public String getReadContent() {
		return readContent;
	}

	public void setReadContent(String readContent) {
		this.readContent = readContent;
	}

	public Integer getResultState() {
		return resultState;
	}

	public void setResultState(Integer resultState) {
		this.resultState = resultState;
	}

	public String getReadUserId() {
		return readUserId;
	}

	public void setReadUserId(String readUserId) {
		this.readUserId = readUserId;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNstResult() {
        return nstResult;
    }

    public void setNstResult(String nstResult) {
        this.nstResult = nstResult;
    }

    public String getFischerResult() {
        return fischerResult;
    }

    public void setFischerResult(String fischerResult) {
        this.fischerResult = fischerResult;
    }

    public String getKrebsResult() {
        return krebsResult;
    }

    public void setKrebsResult(String krebsResult) {
        this.krebsResult = krebsResult;
    }

    public String getAcogResult() {
        return acogResult;
    }

    public void setAcogResult(String acogResult) {
        this.acogResult = acogResult;
    }

    public String getScoreData() {
		return scoreData;
	}

	public void setScoreData(String scoreData) {
		this.scoreData = scoreData;
	}
}
