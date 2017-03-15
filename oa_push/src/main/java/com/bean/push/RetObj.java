package com.bean.push;
/**
 * 返回的json串实体类
 * @author Administrator
 * @date 2016年10月21日
 * @company
 * RetObj.java
 */
public class RetObj {
	private boolean flag = true;
	private String msg;
	//private Object obj;
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public RetObj(boolean flag, String msg) {
		super();
		this.flag = flag;
		this.msg = msg;
	}
	public RetObj() {
		super();
	}
	
}
