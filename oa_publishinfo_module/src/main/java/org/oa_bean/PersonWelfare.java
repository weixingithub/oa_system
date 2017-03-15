package org.oa_bean;

import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.hibernate.annotations.Index;
import org.oa_bean.comment.CommentInfo;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月4日 上午11:42:17 
 * @version 1.0 
 */
@Entity
@Table(name="pw_table")
public class PersonWelfare {
   private Integer id; //文章id
   private String title;//文章标题
   private String starttime;//流程开始时间
   private String endtime;//流程结束时间
   private String finishtime;//预计结束时间
   private String activityuserId;//发起流程人员用户名
   private String processInstanceId;//流程Id
   private Integer flowstatus;//审核流程状态   
   private Task task;
   private Map<String, Object> variables;//流程参数
   private ProcessInstance processInstance;// 运行中的流程实例
   private HistoricProcessInstance historicProcessInstance; // 历史的流程实例
   private ProcessDefinition processDefinition;// 流程定义
   private Boolean flag;//是否发布 true false
   private Integer userId;//创建者id
   private Integer orgId;//所属机构id
   private String modelId;//所属模块id
   private String creattime;//创建时间
   private SysUser sysUser;
   private OaOrg org;
   private PersonWelfareContent personWelfareContent;
   private int pubPlatform;//发布平台3.前台 5.微信  7.微博   以它们的乘积保存
   private String articleTag;//文章标签 用,隔开 
   private String imgSrc;//媒体链接 
   private String author; //文章作者
   private String originalLinks;//原文链接
   private String signUserName;//签收人的用户名
   
   private Integer commentSwitch = 0; 	 // 评论开关，0关闭；1开启,默认关闭
   private CommentInfo commentInfo;	 //评论实体
   @Id
   @GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="activiti_user")
	public String getActivityuserId() {
		return activityuserId;
	}
	public void setActivityuserId(String activityuserId) {
		this.activityuserId = activityuserId;
	}
	@Column(name="start_time")
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	@Column(name="end_time")
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	@Column(name="flag")
	 public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	@Column(name="process_instance_id")
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	@Transient
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	@Transient
	public Map<String, Object> getVariables() {
		return variables;
	}
	public void setVariables(Map<String, Object> variables) {
		this.variables = variables;
	}
	@Transient
	public ProcessInstance getProcessInstance() {
		return processInstance;
	}
	public void setProcessInstance(ProcessInstance processInstance) {
		this.processInstance = processInstance;
	}
	@Transient
	public HistoricProcessInstance getHistoricProcessInstance() {
		return historicProcessInstance;
	}
	public void setHistoricProcessInstance(HistoricProcessInstance historicProcessInstance) {
		this.historicProcessInstance = historicProcessInstance;
	}
	@Transient
	public ProcessDefinition getProcessDefinition() {
		return processDefinition;
	}
	public void setProcessDefinition(ProcessDefinition processDefinition) {
		this.processDefinition = processDefinition;
	}
	@Column(name="create_time")
	public String getCreattime() {
		return creattime;
	}
	public void setCreattime(String creattime) {
		this.creattime = creattime;
	}
	@Transient
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	@Transient
	public OaOrg getOrg() {
		return org;
	}
	public void setOrg(OaOrg org) {
		this.org = org;
	}
	@Column(name="model_id")
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	@Column(name="user_id")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name="org_id")
	@Index(name="orgIndex")
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	
	@Column(name="flow_status",nullable=false,columnDefinition="INT default 0")
	@Index(name="statusIndex")
	public Integer getFlowstatus() {
		return flowstatus;
	}
	public void setFlowstatus(Integer flowstatus) {
		this.flowstatus = flowstatus;
	}
	@Column(name="pub_platform")
	public int getPubPlatform() {
		return pubPlatform;
	}
	public void setPubPlatform(int pubPlatform) {
		this.pubPlatform = pubPlatform;
	}
	@Column(name="author")
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Column(name="article_tag")
	public String getArticleTag() {
		return articleTag;
	}
	public void setArticleTag(String articleTag) {
		this.articleTag = articleTag;
	}
	@Transient
	public PersonWelfareContent getPersonWelfareContent() {
		return personWelfareContent;
	}
	public void setPersonWelfareContent(PersonWelfareContent personWelfareContent) {
		this.personWelfareContent = personWelfareContent;
	}
    @Column(name="img_src")
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	 @Column(name="original_links")
	public String getOriginalLinks() {
		return originalLinks;
	}
	public void setOriginalLinks(String originalLinks) {
		this.originalLinks = originalLinks;
	}
	@Column(name="sign_user_name")
	public String getSignUserName() {
		return signUserName;
	}
	public void setSignUserName(String signUserName) {
		this.signUserName = signUserName;
	}
	@Column(name="finish_time")
	public String getFinishtime() {
		return finishtime;
	}
	public void setFinishtime(String finishtime) {
		this.finishtime = finishtime;
	}
	@Column(name="comment_switch")
	public Integer getCommentSwitch() {
		return commentSwitch;
	}
	public void setCommentSwitch(Integer commentSwitch) {
		this.commentSwitch = commentSwitch;
	}
	@OneToOne(fetch=FetchType.LAZY,cascade={CascadeType.REMOVE,CascadeType.PERSIST})
	@JoinColumn(name="comment_info_id")
	public CommentInfo getCommentInfo() {
		return commentInfo;
	}
	public void setCommentInfo(CommentInfo commentInfo) {
		this.commentInfo = commentInfo;
	}
	
}
