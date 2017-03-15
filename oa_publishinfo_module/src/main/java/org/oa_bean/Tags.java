package org.oa_bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 标签分类的实体类
 * @author miao
 *
 */

@Entity
@Table(name="t_tags")
public class Tags {

	private Integer tagId;	//标签的id;
	private String tagName;	//标签名称
	private String createTime;	//标签创建时间
	
	@Id
	@GeneratedValue
	@Column(name="id")
	public Integer gettagId() {
		return tagId;
	}
	public void settagId(Integer tagId) {
		this.tagId = tagId;
	}
	@Column(name="tag_name")
	public String gettagName() {
		return tagName;
	}
	public void settagName(String tagName) {
		this.tagName = tagName;
	}
	
	@Column(name="create_time")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((tagId == null) ? 0 : tagId.hashCode());
		result = prime * result + ((tagName == null) ? 0 : tagName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tags other = (Tags) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (tagId == null) {
			if (other.tagId != null)
				return false;
		} else if (!tagId.equals(other.tagId))
			return false;
		if (tagName == null) {
			if (other.tagName != null)
				return false;
		} else if (!tagName.equals(other.tagName))
			return false;
		return true;
	}
	
}
