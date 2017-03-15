package org.oa_bean.area;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年6月29日 下午2:30:07 
 * @version 1.0 
 */
@Entity
@Table(name="T_Province")
public class Province {
    private Integer proID;//省份主键
    private String proName;//省份名称
    private Integer proSort;//省份排序
    private String proRemark;//省份说明
    @Id
    @GeneratedValue
    @Column(name="ProID")
    public Integer getProID() {
		return proID;
	}
	public void setProID(Integer proID) {
		this.proID = proID;
	}
	@Column(name="ProName",length=50)
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	@Column(name="ProSort")
	public Integer getProSort() {
		return proSort;
	}
	public void setProSort(Integer proSort) {
		this.proSort = proSort;
	}
	@Column(name="ProRemark",length=50)
	public String getProRemark() {
		return proRemark;
	}
	public void setProRemark(String proRemark) {
		this.proRemark = proRemark;
	}
}
