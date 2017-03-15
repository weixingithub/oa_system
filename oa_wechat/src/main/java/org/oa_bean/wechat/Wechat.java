package org.oa_bean.wechat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 微信基本信息(保存到本地数据库)
 * @author 江斌
 *
 */
@Entity
@Table(name="wx_wechat")
public class Wechat {
	/**微信信息ID**/
	private Integer id ;
	/**访问的接口地址 域名或IP**/
	private String websiteUrl;
	/**开发者微信号**/
	private String wechatID;
	/**第三方用户唯一凭证 **/
	private String appId;
	/**第三方用户唯一凭证密钥，即appsecret **/
	private String appSecret;
	
	private String name;//账号名称
	private String password;//账号密码
	private WechatToken wechatToken;
	private String orgId;		//所属部门id
	private String orgPid;		//所属部门父节点
	private String orgName;		//部门名称
	private String createTime;
	@Id
	@GeneratedValue
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	@Column(name="website_url")
	public String getWebsiteUrl() {
		return websiteUrl;
	}
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
	@Column(name="app_id")
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	@Column(name="app_secret")
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	@Column(name="wechat_id")
	public String getWechatID() {
		return wechatID;
	}
	public void setWechatID(String wechatID) {
		this.wechatID = wechatID;
	}
	@OneToOne(fetch=FetchType.LAZY,cascade={CascadeType.ALL})
	@JoinColumn(name="token_id")
	public WechatToken getWechatToken() {
		return wechatToken;
	}
	public void setWechatToken(WechatToken wechatToken) {
		this.wechatToken = wechatToken;
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
}
