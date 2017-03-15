package org.oa_bean;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * @author  魏歆: 
 * @description 管理员实体类
 * @date 创建时间：2016年5月4日 上午9:08:38 
 * @version 1.0 
 */
@Entity
@Table(name="oa_sysuser")
public class SysUser {
	    private Integer id;//管理员id
	    private String userName;//管理员账号
	    private String userPwd;//管理员密码
	    private String realName;//管理员姓名
	    private Integer userAge;//管理员年龄
	    private Integer userSex;//管理员性别 1.女 2.男
	    private String nodeId;//管理员节点
	    private String paretId;//管理员父节点
	    private String userPhone;//管理员电话号码
		private String userEmail;//管理员邮箱
		private String userCreateTime;//管理员创建时间
		private String verifycode;//验证码
		private String orgIds;//机构id集合
		private String isParent;//是否为父节点 true:false
		private List<OaRole> oaRole;//关联角色
	    private List<OaOrg> oaOrg;//关联群组
	    private String token;//设备唯一标识
		@Id
	    @GeneratedValue
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		@Column(name="user_name")
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		@Column(name="user_pwd")
		public String getUserPwd() {
			return userPwd;
		}
		public void setUserPwd(String userPwd) {
			this.userPwd = userPwd;
		}
		@Column(name="real_name")
		public String getRealName() {
			return realName;
		}
		public void setRealName(String realName) {
			this.realName = realName;
		}
		@Column(name="user_age")
		public Integer getUserAge() {
			return userAge;
		}
		public void setUserAge(Integer userAge) {
			this.userAge = userAge;
		}
		@Column(name="user_sex")
		public Integer getUserSex() {
			return userSex;
		}
		public void setUserSex(Integer userSex) {
			this.userSex = userSex;
		}
		
		@Column(name="node_Id")
		public String getNodeId() {
			return nodeId;
		}
		public void setNodeId(String nodeId) {
			this.nodeId = nodeId;
		}
		@Column(name="parent_Id")
		public String getParetId() {
			return paretId;
		}
		public void setParetId(String paretId) {
			this.paretId = paretId;
		}
		@Column(name="user_createtime")
		public String getUserCreateTime() {
			return userCreateTime;
		}
		public void setUserCreateTime(String userCreateTime) {
			this.userCreateTime = userCreateTime;
		}
		@Transient
		public String getVerifycode() {
			return verifycode;
		}
		public void setVerifycode(String verifycode) {
			this.verifycode = verifycode;
		}
		
		@ManyToMany(fetch=FetchType.LAZY)
		@JoinTable(name="role_sysuser",
		 joinColumns=@JoinColumn(name="sys_Id",referencedColumnName="id"),
	     inverseJoinColumns=@JoinColumn(name="role_Id")
		)
		public List<OaRole> getOaRole() {
			return oaRole;
		}
		public void setOaRole(List<OaRole> oaRole) {
			this.oaRole = oaRole;
		}
		@ManyToMany(fetch=FetchType.LAZY)
		@JoinTable(name="org_sysuser",
		 joinColumns=@JoinColumn(name="sys_Id",referencedColumnName="id"),
	     inverseJoinColumns=@JoinColumn(name="org_Id")
		)
		public List<OaOrg> getOaOrg() {
			return oaOrg;
		}
		public void setOaOrg(List<OaOrg> oaOrg) {
			this.oaOrg = oaOrg;
		}
		@Column(name="user_phone")
		 public String getUserPhone() {
			return userPhone;
		}
		public void setUserPhone(String userPhone) {
			this.userPhone = userPhone;
		}
		@Column(name="user_email")
		public String getUserEmail() {
			return userEmail;
		}
		public void setUserEmail(String userEmail) {
			this.userEmail = userEmail;
		}
		@Column(name="user_org_Id")
		public String getOrgIds() {
			return orgIds;
		}
		public void setOrgIds(String orgIds) {
			this.orgIds = orgIds;
		}
		@Column(name="is_parent")
		public String getIsParent() {
			return isParent;
		}
		public void setIsParent(String isParent) {
			this.isParent = isParent;
		}
		@Column(name="device_token")
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		
}
