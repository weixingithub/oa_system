package com.tool.util;

public class JsonBean {
	
	public JsonBean(String statusCode, String message, String navTabId, String rel, String callbackType,
			String forwardUrl, String confirmMsg) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.navTabId = navTabId;
		this.rel = rel;
		this.callbackType = callbackType;
		this.forwardUrl = forwardUrl;
		this.confirmMsg = confirmMsg;
	}

   private String statusCode;//返回状态 200:操作成功 300:操作失败  301:会话超时 
   
   private String message;//返回提示信息
   
   private String navTabId;//跳转的navTab id
   
   private String rel;//
   
   private String callbackType;//返回类型closeCurrent会关闭当前s
   
   private String forwardUrl;//重定向url
   
   private String confirmMsg;//

	public String getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getNavTabId() {
		return navTabId;
	}
	
	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}
	
	public String getRel() {
		return rel;
	}
	
	public void setRel(String rel) {
		this.rel = rel;
	}
	
	public String getCallbackType() {
		return callbackType;
	}
	
	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}
	
	public String getForwardUrl() {
		return forwardUrl;
	}
	
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
	
	public String getConfirmMsg() {
		return confirmMsg;
	}
	
	public void setConfirmMsg(String confirmMsg) {
		this.confirmMsg = confirmMsg;
	}
}
