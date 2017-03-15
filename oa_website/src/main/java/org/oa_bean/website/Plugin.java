package org.oa_bean.website;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 插件
 * @author 
 *
 */
@Entity
@Table(name="w_plugin")
public class Plugin {
	private Integer pluginId;   
	private String pluginName; 		//插件名称
	private String pluginUrl;		//插件调用方法名
	private Integer pluginType;	   // 插件类型 （图文，文本，视频，图片，链接）
	private String pluginPic;		//实例图片
	private String pluginIntro;		//说明
	private String createTime;      //创建时间
	private String author;       	//作者
	@Id
    @GeneratedValue
    @Column(name="plugin_id")
	public Integer getPluginId() {
		return pluginId;
	}
	public void setPluginId(Integer pluginId) {
		this.pluginId = pluginId;
	}
	@Column(name="plugin_name")
	public String getPluginName() {
		return pluginName;
	}
	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}
	@Column(name="plugin_url")
	public String getPluginUrl() {
		return pluginUrl;
	}
	public void setPluginUrl(String pluginUrl) {
		this.pluginUrl = pluginUrl;
	}
	@Column(name="plugin_pic")
	public String getPluginPic() {
		return pluginPic;
	}
	public void setPluginPic(String pluginPic) {
		this.pluginPic = pluginPic;
	}
	@Column(name="plugin_intro")
	public String getPluginIntro() {
		return pluginIntro;
	}
	public void setPluginIntro(String pluginIntro) {
		this.pluginIntro = pluginIntro;
	}
	@Column(name="create_time")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name="author")
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	@Column(name="plugin_type")
	public Integer getPluginType() {
		return pluginType;
	}
	public void setPluginType(Integer pluginType) {
		this.pluginType = pluginType;
	}
	
	
}
