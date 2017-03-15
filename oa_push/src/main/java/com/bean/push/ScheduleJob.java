package com.bean.push;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 计划任务消息(用来存储重复发送的定时任务)
 * @author Administrator
 * @date 2016年10月19日
 * @company
 * ScheduleJob.java
 */
@Entity
@Table(name="scheduleJob")
public class ScheduleJob {

	public static final String STATUS_RUNNING = "1";//任务运行状态（1为正常运行）
	public static final String STATUS_NOT_RUNNING = "0";
	public static final String CONCURRENT_IS = "1";//任务是否有状态，来给出不同的job实现类
	public static final String CONCURRENT_NOT = "0";
	public static final String appMasterSecret="";//应用秘钥
	
	private Long jobId;

	private String createTime;

	private String updateTime;
	/**
	 * 任务名称
	 */
	private String jobName;
	/**
	 * 任务分组
	 */
	private String jobGroup;
	/**
	 * 任务状态 是否启动任务
	 */
	private String jobStatus;
	/**
	 * cron表达式
	 */
	private String cronExpression;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 任务执行时调用哪个类的方法 包名+类名
	 */
	private String beanClass;
	/**
	 * 任务是否有状态
	 */
	private String isConcurrent;
	/**
	 * spring bean
	 */
	private String springId;
	/**
	 * 任务调用的方法名
	 */
	private String methodName;
	/**
	 * 接收者id
	 */
	private Long receiverid; 
	private Integer orgId;//机构id(要发送的门店id)
	private Integer messagekind;//消息种类
	private String starttime;//开始时间
	private String endtime;//结束时间
	private Integer day;//最近几次
	@Id
	@GeneratedValue
	@Column(name="jobid")
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	@Column(name="createtime")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name="updatetime")
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name="jobname")
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	@Column(name="jobgroup")
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	@Column(name="jobstatus")
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	@Column(name="cronexpression")
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	@Column(name="description")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Column(name="beanclass")
	public String getBeanClass() {
		return beanClass;
	}
	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}
	@Column(name="isconcurrent")
	public String getIsConcurrent() {
		return isConcurrent;
	}
	public void setIsConcurrent(String isConcurrent) {
		this.isConcurrent = isConcurrent;
	}
	@Column(name="springid")
	public String getSpringId() {
		return springId;
	}
	public void setSpringId(String springId) {
		this.springId = springId;
	}
	@Column(name="methodname")
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	@Column(name="receiverid")
	public Long getReceiverid() {
		return receiverid;
	}
	public void setReceiverid(Long receiverid) {
		this.receiverid = receiverid;
	}
	@Transient
	public static String getStatusRunning() {
		return STATUS_RUNNING;
	}
	@Transient
	public static String getStatusNotRunning() {
		return STATUS_NOT_RUNNING;
	}
	@Transient
	public static String getConcurrentIs() {
		return CONCURRENT_IS;
	}
	@Transient
	public static String getConcurrentNot() {
		return CONCURRENT_NOT;
	}
	@Column(name="org_id")
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	@Column(name="message_kind")
	public Integer getMessagekind() {
		return messagekind;
	}
	public void setMessagekind(Integer messagekind) {
		this.messagekind = messagekind;
	}
	@Column(name="starttime")
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	@Column(name="endtime")
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	@Column(name="day")
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	

	
}