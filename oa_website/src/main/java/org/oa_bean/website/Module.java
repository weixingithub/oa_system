package org.oa_bean.website;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 前台模块
 * @author Administrator
 *
 */
@Entity
@Table(name="w_module")
public class Module {
	private Integer modelId;    			//模块id
	private Integer modelNid;				//子节点Id
	private Integer modelPid;				//父节点Id
	private Integer modelKey;				//模块后台对应的模块id
	private String  modelName;				//模块名称
	private String 	modelUrl;				//访问数据地址
	private String 	modelPageUrl;			//跳转页面地址
	private String 	isParent;				//是否为父节点 true:false
	private Integer modelRole;				//模块访问权限
	private Layout  layout;					//关联的页面
	private Integer layoutId;      	    	//布局id
	private Integer menuId;      	    	//导航Id
	private List<ColumnObj> listColumn;		//拥有的栏目
	private Integer pluginId;	   	    	//插件id
	private Plugin  plugin;         		//引用的插件
	@Id
    @GeneratedValue
    @Column(name="model_id")
	public Integer getModelId() {
		return modelId;
	}
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	@Column(name="model_nid")
	public Integer getModelNid() {
		return modelNid;
	}
	public void setModelNid(Integer modelNid) {
		this.modelNid = modelNid;
	}
	@Column(name="model_pid")
	public Integer getModelPid() {
		return modelPid;
	}
	public void setModelPid(Integer modelPid) {
		this.modelPid = modelPid;
	}
	@Column(name="model_key")
	public Integer getModelKey() {
		return modelKey;
	}
	public void setModelKey(Integer modelKey) {
		this.modelKey = modelKey;
	}
	@Column(name="model_name")
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	@Column(name="model_url")
	public String getModelUrl() {
		return modelUrl;
	}
	public void setModelUrl(String modelUrl) {
		this.modelUrl = modelUrl;
	}
	@Column(name="model_page_url")
	public String getModelPageUrl() {
		return modelPageUrl;
	}
	public void setModelPageUrl(String modelPageUrl) {
		this.modelPageUrl = modelPageUrl;
	}
	@Column(name="is_parent")
	public String getIsParent() {
		return isParent;
	}
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	@Column(name="model_role")
	public Integer getModelRole() {
		return modelRole;
	}
	public void setModelRole(Integer modelRole) {
		this.modelRole = modelRole;
	}
	 
	@Column(name="layout_id")
	public Integer getLayoutId() {
		return layoutId;
	}
	public void setLayoutId(Integer layoutId) {
		this.layoutId = layoutId;
	}
	@Column(name="menu_id")
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	@Transient
	public Layout getLayout() {
		return layout;
	}
	public void setLayout(Layout layout) {
		this.layout = layout;
	}
	@OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.REMOVE})
	@JoinColumn(name="model_id")
	public List<ColumnObj> getListColumn() {
		return listColumn;
	}
	public void setListColumn(List<ColumnObj> listColumn) {
		this.listColumn = listColumn;
	}
	@Column(name="plugin_id")
	public Integer getPluginId() {
		return pluginId;
	}
	public void setPluginId(Integer pluginId) {
		this.pluginId = pluginId;
	}
	@Transient
	public Plugin getPlugin() {
		return plugin;
	}
	public void setPlugin(Plugin plugin) {
		this.plugin = plugin;
	}
}
