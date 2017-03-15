package org.oa_bean.website;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 网站信息
 * @author Administrator
 *
 */
@Entity
@Table(name="w_website")
public class Website implements Serializable {
	private Integer id;
	private String name;		//网站名称
	private Integer type;		//网站类型
	private String logoUrl; 	//logo地址
	private String synopsis;	//简介
	private String address;		//地址
	private String wDomain;		//网站域名
	private String orgId;		//所属部门id
	private String orgPid;		//所属部门父节点
	private String orgName;		//部门名称
	private String createTime;	//创建时间
	private Menu menu ;			//菜单
	private Integer menuId ;	//菜单ID
	private Integer themeId;	//关联的主题ID
	private Themes  themes;		//关联的主题
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
	@Column(name="logo_url")
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	@Column(name="synopsis")
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(name="w_domain")
	public String getwDomain() {
		return wDomain;
	}
	public void setwDomain(String wDomain) {
		this.wDomain = wDomain;
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
	@Column(name="type")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Column(name="create_time")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name="theme_id")
	public Integer getThemeId() {
		return themeId;
	}
	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
	}
	@Transient
	public Themes getThemes() {
		return themes;
	}
	public void setThemes(Themes themes) {
		this.themes = themes;
	}
	@Column(name="menu_id")
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	@Transient
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	 
	
}
