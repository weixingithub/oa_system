package org.oa_bean.wechat;
/**
 * 微信的分组实体类
 * @author Administrator
 *
 */
public class Group {
	private String id ;
	private String name ;
	private String count ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", count=" + count + "]";
	}
	
}
