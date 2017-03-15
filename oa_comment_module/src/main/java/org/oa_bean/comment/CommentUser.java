package org.oa_bean.comment;
/**
 * 评论用户
 * @author Administrator
 *
 */
public class CommentUser {
	private Integer userId;  			//唯一ID
	private Integer userSex;		    //性别
	private String nickName;			//昵称
	private String userAccount;			//账号名
	private String userPassword;		//密码
	private Integer userVip;			//用户等级
	private Integer userQQ;				//QQ
	private Integer userPhone;			//电话号码
	private String userEmail;			//电子邮箱
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getUserSex() {
		return userSex;
	}
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Integer getUserVip() {
		return userVip;
	}
	public void setUserVip(Integer userVip) {
		this.userVip = userVip;
	}
	public Integer getUserQQ() {
		return userQQ;
	}
	public void setUserQQ(Integer userQQ) {
		this.userQQ = userQQ;
	}
	public Integer getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(Integer userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	
}
