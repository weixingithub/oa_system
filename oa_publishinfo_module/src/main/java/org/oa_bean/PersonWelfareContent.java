package org.oa_bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年11月22日 上午10:12:37 
 * @version 1.0 
 */
@Entity
@Table(name="t_pw_content")
@Indexed
public class PersonWelfareContent {
    private Integer id;//内容id
    private String content;//内容详情
    private Integer orgId;
    private String modelId; 
	@Id
    @GeneratedValue
    @Column(name="content_id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="content",columnDefinition="TEXT")
	@Field(index=Index.YES,store=Store.YES)
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="org_id")
	@Field(index=Index.YES,store=Store.YES,analyze=Analyze.NO)
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	@Column(name="model_id")
	@Field(index=Index.YES,store=Store.YES,analyze=Analyze.NO)
	public String getModelId() {
		return modelId;
	}
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
}
