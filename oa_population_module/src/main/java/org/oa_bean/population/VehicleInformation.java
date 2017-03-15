package org.oa_bean.population;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 车辆管理
 * @author Administrator
 * @date 2016年7月1日
 * @company
 * VehicleInformation.java
 */
@Entity
@Table(name="c_vehicleinformation")
public class VehicleInformation {
	private Integer id;//主键
	private String licenseplatenumber;//车牌号
	private String vehicletype;//车辆类型
	private String owners;//车主
	private Integer person_id;//人口id
	private String person_contact;//车主联系方式
	@Id
	@GeneratedValue
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="licenseplatenumber")
	public String getLicenseplatenumber() {
		return licenseplatenumber;
	}
	public void setLicenseplatenumber(String licenseplatenumber) {
		this.licenseplatenumber = licenseplatenumber;
	}
	@Column(name="vehicletype")
	public String getVehicletype() {
		return vehicletype;
	}
	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}
	@Column(name="owners")
	public String getOwners() {
		return owners;
	}
	public void setOwners(String owners) {
		this.owners = owners;
	}
	@Column(name="person_id")
	public Integer getPerson_id() {
		return person_id;
	}
	public void setPerson_id(Integer person_id) {
		this.person_id = person_id;
	}
	@Column(name="person_contact")
	public String getPerson_contact() {
		return person_contact;
	}
	public void setPerson_contact(String person_contact) {
		this.person_contact = person_contact;
	}
}
