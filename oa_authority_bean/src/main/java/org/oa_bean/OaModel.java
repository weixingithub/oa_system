package org.oa_bean;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Index;
/**
 * @author  魏歆
 * @description 权限模块实体类
 * @date 创建时间：2016年5月4日 上午11:37:13 
 * @version 1.0 
 */
@Entity
@Table(name="oa_model")
public class OaModel {
    private Integer modelId;//模块ID
    private String modelName;//模块名称
    private String parentId;//模块父节点
    private String nodeId;//模块子节点
    private List<RoleModel> roleModel;//关联角色权限
    private String modelUrl;//模块地址
    private String modelIframe;//模块是否为frame true:false
    private String isParent;//是否为父节点 true:false
    private String remark;//模块备注
    private Integer isAuto;//是否自动 1是  其他否
    private String checkRoleId;//审核角色id
    private String pAuto;//是否为信息模块true;false
    private Integer userId;//创建者id
    private String stageUrl;//前台url
    
    private int isCheckModel;//是否为审核模块 0否 1是(此字段为开发人员所用，在使用creator时进行是否为审核模块的设置)
    private int autoIsCheckModel;//动态模块是否审核 0否  1是
    private String modelImage;//模块图片链接地址
    private int appFlag;//手机端是否使用 0否 1是
	@Id
    @GeneratedValue
    @Column(name="model_Id")
	public Integer getModelId() {
		return modelId;
	}
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	@Column(name="model_Name")
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
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
	@OneToMany(mappedBy="model",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	public List<RoleModel> getRoleModel() {
		return roleModel;
	}
	public void setRoleModel(List<RoleModel> roleModel) {
		this.roleModel = roleModel;
	}
	@Column(name="model_url")
	public String getModelUrl() {
		return modelUrl;
	}
	public void setModelUrl(String modelUrl) {
		this.modelUrl = modelUrl;
	}
	@Column(name="model_frame")
	public String getModelIframe() {
		return modelIframe;
	}
	public void setModelIframe(String modelIframe) {
		this.modelIframe = modelIframe;
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
	@Column(name="is_auto")
	public Integer getIsAuto() {
		return isAuto;
	}
	public void setIsAuto(Integer isAuto) {
		this.isAuto = isAuto;
	}
	@Column(name="check_role_id")
	public String getCheckRoleId() {
		return checkRoleId;
	}
	public void setCheckRoleId(String checkRoleId) {
		this.checkRoleId = checkRoleId;
	}
	@Column(name="p_auto")
	public String getpAuto() {
		return pAuto;
	}
	public void setpAuto(String pAuto) {
		this.pAuto = pAuto;
	}
	@Column(name="user_id")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Column(name="stage_url")
	public String getStageUrl() {
		return stageUrl;
	}
	public void setStageUrl(String stageUrl) {
		this.stageUrl = stageUrl;
	}
	@Column(name="is_check_model",nullable=false,columnDefinition="INT default 0")
	public int getIsCheckModel() {
		return isCheckModel;
	}
	public void setIsCheckModel(int isCheckModel) {
		this.isCheckModel = isCheckModel;
	}
	@Column(name="auto_is_check_model",nullable=false,columnDefinition="INT default 0")
	public int getAutoIsCheckModel() {
		return autoIsCheckModel;
	}
	public void setAutoIsCheckModel(int autoIsCheckModel) {
		this.autoIsCheckModel = autoIsCheckModel;
	}
	@Column(name="model_image")
	public String getModelImage() {
		return modelImage;
	}
	public void setModelImage(String modelImage) {
		this.modelImage = modelImage;
	}
	@Column(name="app_flag",nullable=false,columnDefinition="INT default 0")
	@Index(name="appFlagIndex")
	public int getAppFlag() {
		return appFlag;
	}
	public void setAppFlag(int appFlag) {
		this.appFlag = appFlag;
	}
}
