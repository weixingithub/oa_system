package org.oa_bean;
/**
 * @author  魏歆
 * @description 具体模块权限标识
 * @date 创建时间：2016年5月13日 上午10:30:04 
 * @version 1.0 
 */
public class AuthorityMsg {
    private boolean visit;//是否有访问权限true:false
    private boolean add;//是否有新增权限true:false
    private boolean editor;//是否有编辑权限true:false
    private boolean delete;//是否有删除权限true:false
    private boolean detail;//查看详情 true:false
    private boolean configure;//配置 true:false
	public boolean isVisit() {
		return visit;
	}
	public void setVisit(boolean visit) {
		this.visit = visit;
	}
	public boolean isAdd() {
		return add;
	}
	public void setAdd(boolean add) {
		this.add = add;
	}
	public boolean isEditor() {
		return editor;
	}
	public void setEditor(boolean editor) {
		this.editor = editor;
	}
	public boolean isDelete() {
		return delete;
	}
	public void setDelete(boolean delete) {
		this.delete = delete;
	}
	public boolean isDetail() {
		return detail;
	}
	public void setDetail(boolean detail) {
		this.detail = detail;
	}
	public boolean isConfigure() {
		return configure;
	}
	public void setConfigure(boolean configure) {
		this.configure = configure;
	}
}