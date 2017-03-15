package org.oa_bean.weibo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 微博发布长微博文章
 * @author Administrator
 *
 */
@Entity
@Table(name="wb_article")
public class WeiboArticle  {
	/**
	 * PC端查看文章的url地址是：http://weibo.com/ttarticle/p/show?id={id}
	 * 手机端查看文章的url地址是：http://media.weibo.cn/article?id={id}
	 */
	private Integer id;
	private String pubId;				//本地数据库中的文章Id集合
	private String title;				//文章标题，限定32个中英文字符以内
	private String content;				//正文内容，限制20000个中英文字符内，需要urlencode
	private String cover;				//文章封面图片地址url
	private String summary;				//文章导语
	private String text;				//与其绑定短微博内容，限制120个中英文字符内
	private String objectId;			//对象id
	private String articleUrl;			//文章url
	private String mid;					//短微博对象id
	private String createTime;			//创建时间
	private String uid; 				//授权用户的UID，本字段只是为了方便开发者，减少一次user/show接口调用而返回的，
	private int errcode;				//返回状态码
	@Id
	@GeneratedValue
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="pub_id")
	public String getPubId() {
		return pubId;
	}
	public void setPubId(String pubId) {
		this.pubId = pubId;
	}
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="cover")
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	@Column(name="summary")
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@Column(name="text")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Column(name="object_id")
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	@Column(name="article_url")
	public String getArticleUrl() {
		return articleUrl;
	}
	public void setArticleUrl(String articleUrl) {
		this.articleUrl = articleUrl;
	}
	@Column(name="mid")
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	@Column(name="create_time")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name="errcode")
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	@Column(name="uid")
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
}
