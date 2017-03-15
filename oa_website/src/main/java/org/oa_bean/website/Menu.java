package org.oa_bean.website;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 菜单
 * @author 
 *
 */
@Entity
@Table(name="w_menu")
public class Menu implements Serializable{
	private Integer menuId;
	private String  menuName;		 // 菜单名称
	private Integer menuType;        // 菜单类别
	private String 	createTime;      // 创建时间
	private String  menuTree;		 // 保存的数据
	private String  menuIntro;		 //	说明
	private Integer  websiteId;		 //	网站ID
	private List<Module> module;
	@Id
    @GeneratedValue
    @Column(name="menu_id")
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	@Column(name="menu_tree",columnDefinition="TEXT")
	public String getMenuTree() {
		return menuTree;
	}
	public void setMenuTree(String menuTree) {
		this.menuTree = menuTree;
	}
	
	@Column(name="menu_name")
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	@Column(name="create_time")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name="menu_type")
	public Integer getMenuType() {
		return menuType;
	}
	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}
	@Column(name="menu_intro")
	public String getMenuIntro() {
		return menuIntro;
	}
	public void setMenuIntro(String menuIntro) {
		this.menuIntro = menuIntro;
	}
	@Column(name="website_id")
	public Integer getWebsiteId() {
		return websiteId;
	}
	public void setWebsiteId(Integer websiteId) {
		this.websiteId = websiteId;
	}
	@Transient
	public List<Module> getModule() {
		return module;
	}
	public void setModule(List<Module> module) {
		this.module = module;
	}
}
