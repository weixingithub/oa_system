package org.oa_bean;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
/**
 * @author  魏歆
 * @description 角色实体类
 * @date 创建时间：2016年5月4日 上午11:30:33 
 * @version 1.0 
 */
@Entity
@Table(name="oa_role")
public class OaRole {
    private Integer roleId;//角色ID
    private String roleName;//角色名称
    private String nodeId;//角色节点
    private String parentId;//角色父节点
    private String isParent;//是否为父节点 true:false
    private String remark;//角色备注
    private String createTime;//创建日期
    private String orgId;//所属机构 
	private String userId;//创建者id
	@JsonIgnore
	private List<RoleModel> roleModel;//关联角色权限
    @JsonIgnore
    private List<SysUser> sysUser;//关联管理员
	@Id
    @GeneratedValue
    @Column(name="role_Id")
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	@Column(name="role_Name")
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Column(name="node_Id")
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	@Column(name="parent_Id")
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@OneToMany(mappedBy="role",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public List<RoleModel> getRoleModel() {
		return roleModel;
	}
	public void setRoleModel(List<RoleModel> roleModel) {
		this.roleModel = roleModel;
	}
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="role_sysuser",
	joinColumns=@JoinColumn(name="role_Id",referencedColumnName="role_Id"),
	inverseJoinColumns=@JoinColumn(name="sys_Id")
    )
	public List<SysUser> getSysUser() {
		return sysUser;
	}
	public void setSysUser(List<SysUser> sysUser) {
		this.sysUser = sysUser;
	}
	@Column(name="is_parent")
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="create_time")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name="org_id")
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
