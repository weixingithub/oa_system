package org.oa_bean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月6日 上午8:43:13 
 * @version 1.0 
 */
@Entity
@Table(name="model_role")
public class RoleModel {
   private Integer id;//角色权限id
   private String dataflag;//角色权限数据标记 访问2 添加 3 编辑5 删除7  查看11  配置41
   private String datatype;//群组资源g  用户资源u  系统资源o
   private OaModel model;//关联权限
   private OaRole role;//关联角色
   private String isParent;//是否为父节点true false
   private int isCheckRole;//是否为审核角色 0.否 1.是
   
	@Id
    @GeneratedValue
    public Integer getId() {
		return id;
    }
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="data_flag",length=20)
	public String getDataflag() {
		return dataflag;
	}
	public void setDataflag(String dataflag) {
		this.dataflag = dataflag;
	}
	@Column(name="data_type",length=10)
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="model_Id")
	public OaModel getModel() {
		return model;
	}
	
	public void setModel(OaModel model) {
		this.model = model;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="role_Id")
	public OaRole getRole() {
		return role;
	}
	public void setRole(OaRole role) {
		this.role = role;
	}
	@Column(name="is_parent")
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	@Column(name="is_check_role",nullable=false,columnDefinition="INT default 0")
	public int getIsCheckRole() {
		return isCheckRole;
	}
	public void setIsCheckRole(int isCheckRole) {
		this.isCheckRole = isCheckRole;
	}
}
