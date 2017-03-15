package org.oa_bean.wechat;
/**
 * js 许可Ticket实体类
 * @author Administrator
 *
 */
public class Ticket {
	/** 必填，公众号的唯一标识**/
    private String appId;
    /** 必填，生成签名的时间戳 **/
    private String timestamp;
    /** 必填，生成签名的随机串**/
    private String noncestr;
    /** 必填，签名，见附录1 **/
    private String signature;
    
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNoncestr() {
		return noncestr;
	}
	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
    
}