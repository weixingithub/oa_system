package org.oa_bean.website;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 页面布局
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "w_layout")
public class Layout {
	private Integer layoutId;
	private Integer layoutlevel;      	// 级别
	private String  layoutname;       	// 名称
	private String  layoutThumbPic;   	// 缩略图
	private String  layoutPageUrl;    	// 链接地址
	private String  layoutEditUrl;    	// 编辑地址
	private String  createTime;       	// 创建时间
	private String  layoutIntro;		// 说明
	@Id
	@GeneratedValue
	@Column(name = "layout_id")
	public Integer getLayoutId() {
		return layoutId;
	}
	public void setLayoutId(Integer layoutId) {
		this.layoutId = layoutId;
	}
	@Column(name = "layout_level")
	public Integer getLayoutlevel() {
		return layoutlevel;
	}
	public void setLayoutlevel(Integer layoutlevel) {
		this.layoutlevel = layoutlevel;
	}
	@Column(name = "layout_name")
	public String getLayoutname() {
		return layoutname;
	}
	public void setLayoutname(String layoutname) {
		this.layoutname = layoutname;
	}
	@Column(name = "layout_thumb_pic")
	public String getLayoutThumbPic() {
		return layoutThumbPic;
	}

	public void setLayoutThumbPic(String layoutThumbPic) {
		this.layoutThumbPic = layoutThumbPic;
	}
	@Column(name = "layout_page_url")
	public String getLayoutPageUrl() {
		return layoutPageUrl;
	}
	public void setLayoutPageUrl(String layoutPageUrl) {
		this.layoutPageUrl = layoutPageUrl;
	}
	@Column(name = "layout_edit_url")
	public String getLayoutEditUrl() {
		return layoutEditUrl;
	}
	public void setLayoutEditUrl(String layoutEditUrl) {
		this.layoutEditUrl = layoutEditUrl;
	}
	@Column(name = "create_time")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name = "layout_intro")
	public String getLayoutIntro() {
		return layoutIntro;
	}
	public void setLayoutIntro(String layoutIntro) {
		this.layoutIntro = layoutIntro;
	}
}
