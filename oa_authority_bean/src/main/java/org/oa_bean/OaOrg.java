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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author  魏歆
 * @description 群组实体类
 * @date 创建时间：2016年5月4日 上午10:21:22 
 * @version 1.0 
 */
@Entity
@Table(name="oa_org")
public class OaOrg {
    private Integer orgId;//群组ID
    private String orgName;//群组名称
    private String parentId;//群组父节点
    private String nodeId;//群组节点
    private String isParent;//是否为父节点 true:false
    private String orgNum;//机构编号
    private String orgPCD;//机构地址:省,市, 区
	private String orgAddress;//机构详细地址
    private String orgRemark;//机构备注
    @JsonIgnore
	private List<SysUser> sysUser;//关联管理员
	@Id
    @GeneratedValue
    @Column(name="org_Id")
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	@Column(name="org_Name")
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Column(name="parent_Id")
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	@Column(name="node_Id")
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="org_sysuser",
	 joinColumns=@JoinColumn(name="org_Id",referencedColumnName="org_Id"),
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
	@Column(name="org_num")
	public String getOrgNum() {
		return orgNum;
	}
	public void setOrgNum(String orgNum) {
		this.orgNum = orgNum;
	}
	@Column(name="org_p_c_d")
	public String getOrgPCD() {
		return orgPCD;
	}
	public void setOrgPCD(String orgPCD) {
		this.orgPCD = orgPCD;
	}
	@Column(name="org_address")
	public String getOrgAddress() {
		return orgAddress;
	}
	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}
	@Column(name="org_remark")
	public String getOrgRemark() {
		return orgRemark;
	}
	public void setOrgRemark(String orgRemark) {
		this.orgRemark = orgRemark;
	}
}
