package org.oa_bean.weibo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="wb_token")
public class WeiboToken {
	private Integer id;
	private String accessToken; //用户授权的唯一票据，
	private String expireIn; 	//access_token的生命周期，单位是秒数。 
	private String uid; 		//授权用户的UID，本字段只是为了方便开发者，减少一次user/show接口调用而返回的，
	private String clientId ;	// 微博的应用ID
	@Id
	@GeneratedValue
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="access_token")
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	@Column(name="expireIn")
	public String getExpireIn() {
		return expireIn;
	}
	public void setExpireIn(String expireIn) {
		this.expireIn = expireIn;
	}
 
	@Column(name="uid")
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	@Column(name="client_id")
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	@Override
	public String toString() {
		return "Token [id=" + id + ", accessToken=" + accessToken
				+ ", expireIn=" + expireIn + ", uid=" + uid + ", clientId=" + clientId + "]";
	}
	
}
