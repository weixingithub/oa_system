package org.oa_bean.population;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="c_ground")
public class Ground {
	/**土地11管理**/
	private Integer id;
	private String arableArea;// 耕地面积  单位：亩
	private String subsidyAmount;// 直补金额 单位：元
	private String woodlandArea;// 林地面积 单位：亩
	private String farmlandtoForestArea;// 退耕还林面积
	private String compensation;// 补贴金额
	
	@Id
    @GeneratedValue
    @Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="arable_area")
	public String getArableArea() {
		return arableArea;
	}
	public void setArableArea(String arableArea) {
		this.arableArea = arableArea;
	}
	@Column(name="woodland_area")
	public String getWoodlandArea() {
		return woodlandArea;
	}
	public void setWoodlandArea(String woodlandArea) {
		this.woodlandArea = woodlandArea;
	}
	@Column(name="farmlandto_forest_area")
	public String getFarmlandtoForestArea() {
		return farmlandtoForestArea;
	}
	public void setFarmlandtoForestArea(String farmlandtoForestArea) {
		this.farmlandtoForestArea = farmlandtoForestArea;
	}
	@Column(name="compensation")
	public String getCompensation() {
		return compensation;
	}
	public void setCompensation(String compensation) {
		this.compensation = compensation;
	}
	@Column(name="subsidy_amount")
	public String getSubsidyAmount() {
		return subsidyAmount;
	}
	public void setSubsidyAmount(String subsidyAmount) {
		this.subsidyAmount = subsidyAmount;
	}
	
}
