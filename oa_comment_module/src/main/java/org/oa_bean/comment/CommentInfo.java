package org.oa_bean.comment;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 评论对象
 * @author Administrator
 *
 */
@Entity
@Table(name="b_comment_info")
public class CommentInfo {
	private Integer commentInfoId; 		//评论对象ID
	private String title;				//评论对象标题
	private String createTime;	   		//创建时间
	private Integer createUserId;   	//创建者ID
	private Integer orgId;//所属机构id
	private String modelId;//所属模块id
	private Long praiseNumber;	   		//好评数量
	private Long negativeNumber;   		//负面数量
	private Long browseNumber;	   		//浏览数量
	private String url;					//评论对象的详情url
	private Integer type;				//评论对象类型：1文章，2活动
	
	private List<CommentDetails> commentDetails; //评论细节信息
	@Id
    @GeneratedValue
    @Column(name="comment_info_id")
	public Integer getCommentInfoId() {
		return commentInfoId;
	}
	public void setCommentInfoId(Integer commentInfoId) {
		this.commentInfoId = commentInfoId;
	}
	@Column(name="create_time")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name="create_user_id")
	public Integer getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	@Column(name="praise_number")
	public Long getPraiseNumber() {
		return praiseNumber;
	}
	public void setPraiseNumber(Long praiseNumber) {
		this.praiseNumber = praiseNumber;
	}
	@Column(name="negative_number")
	public Long getNegativeNumber() {
		return negativeNumber;
	}
	public void setNegativeNumber(Long negativeNumber) {
		this.negativeNumber = negativeNumber;
	}
	@Column(name="browse_number")
	public Long getBrowseNumber() {
		return browseNumber;
	}
	public void setBrowseNumber(Long browseNumber) {
		this.browseNumber = browseNumber;
	}
	@OneToMany(fetch=FetchType.LAZY,cascade={CascadeType.REMOVE})
	@JoinColumn(name="comment_info_id")
	public List<CommentDetails> getCommentDetails() {
		return commentDetails;
	}
	public void setCommentDetails(List<CommentDetails> commentDetails) {
		this.commentDetails = commentDetails;
	}
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="orgid")
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	@Column(name="modelId")
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
	
	@Column(name="url")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name="type")
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
