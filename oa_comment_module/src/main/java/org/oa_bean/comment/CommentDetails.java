package org.oa_bean.comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 评论内容
 * @author Administrator
 *
 */
@Entity
@Table(name="b_comment_details")
public class CommentDetails {
	private Integer contentId;        //内容ID	
	private String createTime;	      //创建时间
	private String sender;		      //发送人
	private String recipient;	      //接收人
	private String content;		      //消息内容
	private Integer isRec;			  //评论或回复id:0为首次评论，1回复的评论id
	private Integer recFlag;		  //是否已回复    1已回复    2未回复
	private CommentDetails commentDetails;	//该评论下的回复实体
	@Id
    @GeneratedValue
    @Column(name="content_id")
	public Integer getContentId() {
		return contentId;
	}
	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}
	@Column(name="create_time")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name="sender")
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	@Column(name="recipient")
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	@Column(name="content",columnDefinition="TEXT")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Column(name="isrec")
	public Integer getIsRec() {
		return isRec;
	}
	public void setIsRec(Integer isRec) {
		this.isRec = isRec;
	}
	
	
	@Column(name="rec_flag")
	public Integer getRecFlag() {
		return recFlag;
	}
	public void setRecFlag(Integer recFlag) {
		this.recFlag = recFlag;
	}
	
	@Transient
	public CommentDetails getCommentDetails() {
		return commentDetails;
	}
	public void setCommentDetails(CommentDetails commentDetails) {
		this.commentDetails = commentDetails;
	}
	
}
