package com.bean.push;

import java.util.List;

/**
 * 推送消息的实体类
 * @author Administrator
 * @date 2016年10月10日
 * @company
 * PushBean.java
 */
public class PushBean {
	private String ticker;
	private String title;
	private String text;
	private String devicetoken;
	private List tokens;
	private String postId;//在线咨询中用于区分发送者id
	private String flag;
	//private String 
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDevicetoken() {
		return devicetoken;
	}
	public void setDevicetoken(String devicetoken) {
		this.devicetoken = devicetoken;
	}
	public List getTokens() {
		return tokens;
	}
	public void setTokens(List tokens) {
		this.tokens = tokens;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}

}
