package org.oa_bean.wechat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 微信的token
* 类名: Token </br>
* 描述:  凭证  </br>
 */
@Entity
@Table(name="wx_token")
public class WechatToken  {
	
	private Integer id;
    // 接口访问凭证
    private String accessToken;
    // 凭证有效期，单位：秒
    private int expiresIn;
    // 更新时间
    private long createTime;
    //微信的AppId
    private String appId;    
    
    @Id
	@GeneratedValue
	@Column(name="id")
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
    
	@Column(name="app_id")
	public String getAppId() {
		return appId;
	}
	
	public void setAppId(String appId) {
		this.appId = appId;
	}
	@Column(name="access_token")
    public String getAccessToken() {
        return accessToken;
    }
	public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    @Column(name="expiresIn")
    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
    @Column(name="create_time")
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
    
}