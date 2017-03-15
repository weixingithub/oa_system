package com.tool;

import org.slf4j.Logger;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;


public class WechatException extends Exception {
	private int statusCode = -1;
	private int errcode;
	private String errmsg;
	private static final long serialVersionUID = -2623309261327598087L;
	
	public WechatException() {
	} // 用来创建无参数对象
	public WechatException(String message) { // 用来创建指定参数对象
		super(message); // 调用超类构造器
	}
	public WechatException(Exception cause) {
		super(cause);
	}
	public WechatException(String msg , JSONObject json,Logger log)throws JSONException {
		super(msg + "\n errmsg:" + json.getString("errmsg") +" errcode:" + json.getInt("errcode"));
		log.error(msg,json.getInt("errcode"), json.getString("errmsg"));
		this.errcode =  json.getInt("errcode");
		this.errmsg = json.getString("errmsg");
	}
	public WechatException(String msg , JSONObject json)throws JSONException {
		super(msg + "\n errmsg:" + json.getString("errmsg") +" errcode:" + json.getInt("errcode"));
		this.errcode =  json.getInt("errcode");
		this.errmsg = json.getString("errmsg");
	}
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
}
