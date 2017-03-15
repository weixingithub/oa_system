package org.oa_bean.population;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="c_house")
public class House {
	/**房屋管理**/
	private Integer id;
	private String housenature;//房屋性质  私有：1；租住：2；常住户：3；廉租房：4；
	private String housemaster;//  房主姓名
	private String housemasterphone;// 房主联系方式
	private String hosetype;// 承重类型   钢筋混凝：1；混合：2；砖木：3；其他：4
	private String housepremise;//房屋产权   公：1；商品：2；小产权：3；
	private String constructionArea;// 房屋建筑面积  单位：平米
	private String houseroom;// 室
	private String househall;// 厅
	private String housetoilet;//卫
	private String housefacility;// 防范设施  防盗门：1；防护网：2；防盗门和防护网：3；
	private String housedatatime;// 建成时间  1、日历表；2、固定时间输入格式：年、月、日
	private String housedisasternature;// 房屋受灾性质  地质灾害点：1；危房危害点：2；
	
	@Id
    @GeneratedValue
    @Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="house_nature")
	public String getHousenature() {
		return housenature;
	}
	public void setHousenature(String housenature) {
		this.housenature = housenature;
	}
	@Column(name="house_master")
	public String getHousemaster() {
		return housemaster;
	}
	public void setHousemaster(String housemaster) {
		this.housemaster = housemaster;
	}
	@Column(name="house_master_phone")
	public String getHousemasterphone() {
		return housemasterphone;
	}
	public void setHousemasterphone(String housemasterphone) {
		this.housemasterphone = housemasterphone;
	}
	@Column(name="hose_type")
	public String getHosetype() {
		return hosetype;
	}
	public void setHosetype(String hosetype) {
		this.hosetype = hosetype;
	}
	@Column(name="house_premise")
	public String getHousepremise() {
		return housepremise;
	}
	public void setHousepremise(String housepremise) {
		this.housepremise = housepremise;
	}
	@Column(name="construction_area")
	public String getConstructionArea() {
		return constructionArea;
	}
	public void setConstructionArea(String constructionArea) {
		this.constructionArea = constructionArea;
	}
	@Column(name="house_room")
	public String getHouseroom() {
		return houseroom;
	}
	public void setHouseroom(String houseroom) {
		this.houseroom = houseroom;
	}
	@Column(name="house_hall")
	public String getHousehall() {
		return househall;
	}
	public void setHousehall(String househall) {
		this.househall = househall;
	}
	@Column(name="house_toilet")
	public String getHousetoilet() {
		return housetoilet;
	}
	public void setHousetoilet(String housetoilet) {
		this.housetoilet = housetoilet;
	}
	@Column(name="house_facility")
	public String getHousefacility() {
		return housefacility;
	}
	public void setHousefacility(String housefacility) {
		this.housefacility = housefacility;
	}
	@Column(name="house_datatime")
	public String getHousedatatime() {
		return housedatatime;
	}
	public void setHousedatatime(String housedatatime) {
		this.housedatatime = housedatatime;
	}
	@Column(name="house_disaster_nature")
	public String getHousedisasternature() {
		return housedisasternature;
	}
	public void setHousedisasternature(String housedisasternature) {
		this.housedisasternature = housedisasternature;
	}
}
