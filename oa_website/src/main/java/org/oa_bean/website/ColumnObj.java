package org.oa_bean.website;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 栏目模块
 * @author Administrator
 *
 */
@Entity
@Table(name="w_column")
public class ColumnObj {
	private Integer columnId;     
	private String  columnDiv;	   	    //栏目所在divID的ID名
	private String  moduleIds;          //模块id
	private String  moduleNames;        //模块名称
	private String 	modelUrl;			//访问数据地址
	private String 	pageUrl;			//跳转页面地址
	private Integer pluginId;	   	    //插件id
	private Plugin  plugin;         	//引用的插件
	@Id
    @GeneratedValue
    @Column(name="column_id")
	public Integer getColumnId() {
		return columnId;
	}
	public void setColumnId(Integer columnId) {
		this.columnId = columnId;
	}
	@Column(name="column_div")
	public String getColumnDiv() {
		return columnDiv;
	}
	public void setColumnDiv(String columnDiv) {
		this.columnDiv = columnDiv;
	}
	@Column(name="plugin_id")
	public Integer getPluginId() {
		return pluginId;
	}
	public void setPluginId(Integer pluginId) {
		this.pluginId = pluginId;
	}
	@Column(name="module_ids")
	public String getModuleIds() {
		return moduleIds;
	}
	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}
	@Column(name="module_names")
	public String getModuleNames() {
		return moduleNames;
	}
	public void setModuleNames(String moduleNames) {
		this.moduleNames = moduleNames;
	}
	@Column(name="model_url")
	public String getModelUrl() {
		return modelUrl;
	}
	public void setModelUrl(String modelUrl) {
		this.modelUrl = modelUrl;
	}
	@Column(name="page_url")
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	@Transient
	public Plugin getPlugin() {
		return plugin;
	}
	public void setPlugin(Plugin plugin) {
		this.plugin = plugin;
	}
}
