package org.oa_bean.area;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年6月29日 下午2:30:40 
 * @version 1.0 
 */
@Entity
@Table(name="T_District")
public class District {
   private Integer id;
   private String disName;//区县名称
   private Integer cityID;//所属城市
   private Integer disSort;//区县排序
   @Id
   @GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="DisName",length=50)
	public String getDisName() {
		return disName;
	}
	public void setDisName(String disName) {
		this.disName = disName;
	}
	@Column(name="CityID")
	public Integer getCityID() {
		return cityID;
	}
	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}
	@Column(name="DisSort")
	public Integer getDisSort() {
		return disSort;
	}
	public void setDisSort(Integer disSort) {
		this.disSort = disSort;
	}
}
