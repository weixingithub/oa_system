package org.oa_bean.area;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年6月29日 下午2:30:24 
 * @version 1.0 
 */
@Entity
@Table(name="T_City")
public class City {
   private Integer cityID;//城市主键
   private String  cityName;//城市名称
   private Integer proID;//所属省份
   private Integer citySort;//城市排序
    @Id
    @GeneratedValue
    @Column(name="CityID")
	public Integer getCityID() {
		return cityID;
	}
	public void setCityID(Integer cityID) {
		this.cityID = cityID;
	}
	@Column(name="CityName",length=50)
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	@Column(name="ProID")
	public Integer getProID() {
		return proID;
	}
	public void setProID(Integer proID) {
		this.proID = proID;
	}
	@Column(name="CitySort")
	public Integer getCitySort() {
		return citySort;
	}
	public void setCitySort(Integer citySort) {
		this.citySort = citySort;
	}
}
