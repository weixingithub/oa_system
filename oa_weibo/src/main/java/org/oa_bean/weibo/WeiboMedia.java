package org.oa_bean.weibo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import weibo4j.model.Status;

/**
 * 短微博信息（保存的上传媒体资料）
 * @author 江斌
 *
 */
@Entity
@Table(name="wb_media")
public class WeiboMedia {
	private Integer id;
	private String statusId;				//微博ID
	private String mid;                     //微博MID
	private String text;                    //微博内容
	private String pathUrl;					//网络路径
	private String localUrl;				//本地路径
	private Integer pubId;					//本地数据库中的文章Id集合
	private String title;					//内容标题
	private String thumbnailPic; 			//缩略图片地址，没有时不返回此字段 
	private String bmiddlePic; 				//中等尺寸图片地址，没有时不返回此字段 
	private String originalPic; 			//原始图片地址，没有时不返回此字段 
	private String createTime;				//创建时间
	private String updateTime;				//更新时间
	private int repostsCount;               //转发数
	private int commentsCount;              //评论数
	private int attitudesCount;				//表态数
	private int mlevel;						//暂未支持 
	private int errcode;					//返回状态码
	private String uid; 					//授权用户的UID，本字段只是为了方便开发者，减少一次user/show接口调用而返回的，
	
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="status_id")
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	@Column(name="mid")
	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}
	@Column(name="text")
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Column(name="local_url")
	public String getLocalUrl() {
		return localUrl;
	}
	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}
	@Column(name="pub_id")
	public Integer getPubId() {
		return pubId;
	}
	public void setPubId(Integer pubId) {
		this.pubId = pubId;
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
	@Column(name="path_url")
	public String getPathUrl() {
		return pathUrl;
	}
	public void setPathUrl(String pathUrl) {
		this.pathUrl = pathUrl;
	}
	@Column(name="reposts_count")
	public int getRepostsCount() {
		return repostsCount;
	}
	public void setRepostsCount(int repostsCount) {
		this.repostsCount = repostsCount;
	}
	@Column(name="comments_count")
	public int getCommentsCount() {
		return commentsCount;
	}
	public void setCommentsCount(int commentsCount) {
		this.commentsCount = commentsCount;
	}
	@Column(name="thumbnail_pic")
	public String getThumbnailPic() {
		return thumbnailPic;
	}
	public void setThumbnailPic(String thumbnailPic) {
		this.thumbnailPic = thumbnailPic;
	}
	@Column(name="bmiddle_pic")
	public String getBmiddlePic() {
		return bmiddlePic;
	}
	public void setBmiddlePic(String bmiddlePic) {
		this.bmiddlePic = bmiddlePic;
	}
	@Column(name="original_pic")
	public String getOriginalPic() {
		return originalPic;
	}
	public void setOriginalPic(String originalPic) {
		this.originalPic = originalPic;
	} 
	@Column(name="uid")
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	@Column(name="attitudes_count")
	public int getAttitudesCount() {
		return attitudesCount;
	}
	public void setAttitudesCount(int attitudesCount) {
		this.attitudesCount = attitudesCount;
	}
	@Column(name="mlevel")
	public int getMlevel() {
		return mlevel;
	}
	public void setMlevel(int mlevel) {
		this.mlevel = mlevel;
	}
	@Column(name="update_time")
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
