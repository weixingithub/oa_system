package org.oa_bean.massmsg;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * （微信，微博）
 * 群发消息返回结果
 * @author 江斌
 *
 */
@Entity
@Table(name="w_massmsg")
public class MassMsg {
	/* 创建时保存的信息*/
	private Integer id;
	private String sendTime;   	//发送时间
	private String sendType;	//发送类型（5，微信）（7，微博）(不可同时发送)
	private String sendJson;   	//发送的json字符串
	private String articleIds;  //本地的文章ID集合(标识发布文章的ID)
	private String filter;     	//发送方式  （分组进行群发，UID列表群发）
	private String msgtype;    	//信息类型 （微博：图文，文本。微信：图文，视频，文本，图片，卡券消息）
	private String mediaId;    	//上传返回的媒体ID
	/* 发送时立即返回的信息*/
	private String msgDataId;  	//消息的数据ID，该字段只有在群发图文消息时，才会出现。
	private String msgId; 	   	//消息发送任务的ID
	private int errcode;		//返回的状态码
	
	/* 任务完成时返回的信息*/
	private String status;	   	//群发的结构
	private String totalCount;	//group_id下粉丝数；或者openid_list中的粉丝数 
	private String filterCount; //过滤（过滤是指特定地区、性别的过滤、用户设置拒收的过滤，用户接收已超4条的过滤）后，准备发送的粉丝数
	private String sentCount;	//发送成功的粉丝数 
	private String errorCount;	//发送失败的粉丝数 
	@Id
    @GeneratedValue
    @Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="send_time")
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	@Column(name="send_type")
	public String getSendType() {
		return sendType;
	}
	public void setSendType(String sendType) {
		this.sendType = sendType;
	}
	@Column(name="filter")
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	@Column(name="msg_type")
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	@Column(name="media_id")
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	@Column(name="send_json")
	public String getSendJson() {
		return sendJson;
	}
	public void setSendJson(String sendJson) {
		this.sendJson = sendJson;
	}
	@Column(name="msg_data_id")
	public String getMsgDataId() {
		return msgDataId;
	}
	public void setMsgDataId(String msgDataId) {
		this.msgDataId = msgDataId;
	}
	@Column(name="msg_id")
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	@Column(name="status")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="total_count")
	public String getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}
	@Column(name="filter_count")
	public String getFilterCount() {
		return filterCount;
	}
	public void setFilterCount(String filterCount) {
		this.filterCount = filterCount;
	}
	@Column(name="sent_count")
	public String getSentCount() {
		return sentCount;
	}
	public void setSentCount(String sentCount) {
		this.sentCount = sentCount;
	}
	@Column(name="error_count")
	public String getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(String errorCount) {
		this.errorCount = errorCount;
	}
	@Column(name="article_ids")
	public String getArticleIds() {
		return articleIds;
	}
	public void setArticleIds(String articleIds) {
		this.articleIds = articleIds;
	}
	@Column(name="errcode")
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	
	 
	
}
