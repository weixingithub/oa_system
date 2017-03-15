package org.oa_bean.population;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 人口民政基本信息
 * @author Administrator
 * @date 2016年7月1日
 * @company
 * PopulationCivil.java
 */
@Entity
@Table(name="c_populationcivil")
public class PopulationCivil {
	private Integer id;//民政基本信息主键
	private String disabled;//是否残疾
	private String deformitystate;//残疾类型
	private String disabilitynumber;//残疾证号
	private String disablitygrade;//残疾级别
	private String orphanschildren;//是否孤残儿
	private String receivegrantsorphan;//是否领取孤儿补助
	private String orphanmoney;//孤儿补助金额
	private String disabilitybenefits;//是否享受残疾人津贴
	private String disabledmoney;//残疾人补助金额
	private String activearmy;//是否现役军人
	private String elderly;//是否为孤寡老人
	private String subsidies;//是否领取高龄津贴
	private String subsidiesmoney;//高龄津贴金额
	private String veteran;//是否退伍军人
	private String emptynester;//是否为空巢老人
	private String oldarmy;//老红军
	private String recadres;//复转干部
	private String modelworkers;//是否劳模
	private String checkamount;//参加老年体检人数
	@Id
	@GeneratedValue
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="disabled")
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	@Column(name="deformitystate")
	public String getDeformitystate() {
		return deformitystate;
	}
	public void setDeformitystate(String deformitystate) {
		this.deformitystate = deformitystate;
	}
	@Column(name="disabilitynumber")
	public String getDisabilitynumber() {
		return disabilitynumber;
	}
	public void setDisabilitynumber(String disabilitynumber) {
		this.disabilitynumber = disabilitynumber;
	}
	@Column(name="disablitygrade")
	public String getDisablitygrade() {
		return disablitygrade;
	}
	public void setDisablitygrade(String disablitygrade) {
		this.disablitygrade = disablitygrade;
	}
	@Column(name="orphanschildren")
	public String getOrphanschildren() {
		return orphanschildren;
	}
	public void setOrphanschildren(String orphanschildren) {
		this.orphanschildren = orphanschildren;
	}
	@Column(name="receivegrantsorphan")
	public String getReceivegrantsorphan() {
		return receivegrantsorphan;
	}
	public void setReceivegrantsorphan(String receivegrantsorphan) {
		this.receivegrantsorphan = receivegrantsorphan;
	}
	@Column(name="orphanmoney")
	public String getOrphanmoney() {
		return orphanmoney;
	}
	public void setOrphanmoney(String orphanmoney) {
		this.orphanmoney = orphanmoney;
	}
	@Column(name="disabilitybenefits")
	public String getDisabilitybenefits() {
		return disabilitybenefits;
	}
	public void setDisabilitybenefits(String disabilitybenefits) {
		this.disabilitybenefits = disabilitybenefits;
	}
	@Column(name="disabledmoney")
	public String getDisabledmoney() {
		return disabledmoney;
	}
	public void setDisabledmoney(String disabledmoney) {
		this.disabledmoney = disabledmoney;
	}
	@Column(name="activearmy")
	public String getActivearmy() {
		return activearmy;
	}
	public void setActivearmy(String activearmy) {
		this.activearmy = activearmy;
	}
	@Column(name="elderly")
	public String getElderly() {
		return elderly;
	}
	public void setElderly(String elderly) {
		this.elderly = elderly;
	}
	@Column(name="subsidies")
	public String getSubsidies() {
		return subsidies;
	}
	public void setSubsidies(String subsidies) {
		this.subsidies = subsidies;
	}
	@Column(name="subsidiesmoney")
	public String getSubsidiesmoney() {
		return subsidiesmoney;
	}
	public void setSubsidiesmoney(String subsidiesmoney) {
		this.subsidiesmoney = subsidiesmoney;
	}
	@Column(name="veteran")
	public String getVeteran() {
		return veteran;
	}
	public void setVeteran(String veteran) {
		this.veteran = veteran;
	}
	@Column(name="emptynester")
	public String getEmptynester() {
		return emptynester;
	}
	public void setEmptynester(String emptynester) {
		this.emptynester = emptynester;
	}
	@Column(name="oldarmy")
	public String getOldarmy() {
		return oldarmy;
	}
	public void setOldarmy(String oldarmy) {
		this.oldarmy = oldarmy;
	}
	@Column(name="recadres")
	public String getRecadres() {
		return recadres;
	}
	public void setRecadres(String recadres) {
		this.recadres = recadres;
	}
	@Column(name="modelworkers")
	public String getModelworkers() {
		return modelworkers;
	}
	public void setModelworkers(String modelworkers) {
		this.modelworkers = modelworkers;
	}
	@Column(name="checkamount")
	public String getCheckamount() {
		return checkamount;
	}
	public void setCheckamount(String checkamount) {
		this.checkamount = checkamount;
	}
	

}
