package org.oa_bean.website;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 主题
 * @author Administrator
 *
 */
@Entity
@Table(name="w_themes")
public class Themes implements Serializable {
	private Integer themeId;
	private String themeName;		//主题名称
	private String themeUrl;		//主题路径(保存根目录名称) 
	private String themeCss;		//CSS路径(保存根目录到文件名)
	private String themePic;		//实例图片
	private String themeIntro;		//说明介绍
	private String createTime;      // 创建时间
	private String author;       	// 作者
	private Integer styleColour;		// 主题颜色
	@Id
    @GeneratedValue
    @Column(name="theme_id")
	public Integer getThemeId() {
		return themeId;
	}
	public void setThemeId(Integer themeId) {
		this.themeId = themeId;
	}
	@Column(name="theme_name")
	public String getThemeName() {
		return themeName;
	}
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
	@Column(name="theme_url")
	public String getThemeUrl() {
		return themeUrl;
	}
	public void setThemeUrl(String themeUrl) {
		this.themeUrl = themeUrl;
	}
	@Column(name="theme_css")
	public String getThemeCss() {
		return themeCss;
	}
	public void setThemeCss(String themeCss) {
		this.themeCss = themeCss;
	}
	@Column(name="theme_pic")
	public String getThemePic() {
		return themePic;
	}
	public void setThemePic(String themePic) {
		this.themePic = themePic;
	}
	@Column(name="theme_intro")
	public String getThemeIntro() {
		return themeIntro;
	}
	public void setThemeIntro(String themeIntro) {
		this.themeIntro = themeIntro;
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
	@Column(name="style_colour")
	public Integer getStyleColour() {
		return styleColour;
	}
	public void setStyleColour(Integer styleColour) {
		this.styleColour = styleColour;
	}
}
