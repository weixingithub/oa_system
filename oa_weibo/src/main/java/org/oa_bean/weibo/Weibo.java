package org.oa_bean.weibo;

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

import weibo4j.model.User;
/**
 * 微博实体类
 * @author 江斌
 *
 */
@Entity
@Table(name="wb_weibo")
public class Weibo {
	private Integer id;//
	private String appName;//应用名称
	private String clientId ;// 申请应用时分配的AppKey。
	private String clientSercret;//申请应用时分配的AppSecret。 
	private String redirectUrl;//重定向地址
	private String name;//账号名称
	private String password;//账号密码
	private String orgId;		//所属部门id
	private String orgPid;		//所属部门父节点
	private String orgName;		//部门名称
	private String createTime;//创建时间
	private WeiboToken weiboToken;//
	private User user;
	@Id
	@GeneratedValue
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="client_id")
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	@Column(name="client_sercret")
	public String getClientSercret() {
		return clientSercret;
	}
	public void setClientSercret(String clientSercret) {
		this.clientSercret = clientSercret;
	}
	@Column(name="redirect_url")
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="password")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name="app_name")
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	@OneToOne(fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	@JoinColumn(name="token_id")
	public WeiboToken getWeiboToken() {
		return weiboToken;
	}
	public void setWeiboToken(WeiboToken weiboToken) {
		this.weiboToken = weiboToken;
	}
	@Column(name="create_time")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name="org_id")
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	@Column(name="org_pid")
	public String getOrgPid() {
		return orgPid;
	}
	public void setOrgPid(String orgPid) {
		this.orgPid = orgPid;
	}
	@Column(name="org_name")
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Transient
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
