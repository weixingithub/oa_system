package org.oa_bean.population;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 人口计生^^基础信息
 * @author Administrator
 * @date 2016年7月1日
 * @company
 * familyplanning.java
 */
@Entity
@Table(name="c_familyplanning")
public class FamilyPlanning {
	private Integer id;//计生信息主键
	private String marriagestatus;//婚育情况
	private String marriagetime;//初婚时间
	private String marriagechangetime;//婚姻变动时间
	private String contraceptive;//节育措施
	private String contraceptiontime;//节育时间
	private String onlychildstatus;//是否领取独生子女证
	private String annualnumber;//年审号
	private String twotires;//是否有二胎指标
	private String childgrants;//是否领取独生子女父母补助金
	private String childsubsidyamount;//独生子女补助金额
	private String onlyamounttime;//独生金额领取时间
	private String birthregistration;//是否办理生育登记
	private String richardIII;//是否办理三查
	private String obstetricalcard;//是否办理流动人口婚育证
	private String onlychildstatuslqd;//独生子女证领取地
	private String childgrantslqd;//独生子女费领取地
	private String subsidiesstate;//是否领取保健费
	private String getsubsidiestime;//领取时间
	private String money;//领取金额
	private String socialcompensationfee;//社会抚养费
	private String pregnantnumber;//参加孕前优生人数
	private String vaccinenumber;//接受婴幼儿疫苗人数
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="marriagestatus")
	public String getMarriagestatus() {
		return marriagestatus;
	}
	public void setMarriagestatus(String marriagestatus) {
		this.marriagestatus = marriagestatus;
	}
	@Column(name="marriagetime")
	public String getMarriagetime() {
		return marriagetime;
	}
	public void setMarriagetime(String marriagetime) {
		this.marriagetime = marriagetime;
	}
	@Column(name="marriagechangetime")
	public String getMarriagechangetime() {
		return marriagechangetime;
	}
	public void setMarriagechangetime(String marriagechangetime) {
		this.marriagechangetime = marriagechangetime;
	}
	@Column(name="contraceptive")
	public String getContraceptive() {
		return contraceptive;
	}
	public void setContraceptive(String contraceptive) {
		this.contraceptive = contraceptive;
	}
	@Column(name="contraceptiontime")
	public String getContraceptiontime() {
		return contraceptiontime;
	}
	public void setContraceptiontime(String contraceptiontime) {
		this.contraceptiontime = contraceptiontime;
	}
	@Column(name="onlychildstatus")
	public String getOnlychildstatus() {
		return onlychildstatus;
	}
	public void setOnlychildstatus(String onlychildstatus) {
		this.onlychildstatus = onlychildstatus;
	}
	@Column(name="annualnumber")
	public String getAnnualnumber() {
		return annualnumber;
	}
	public void setAnnualnumber(String annualnumber) {
		this.annualnumber = annualnumber;
	}
	@Column(name="twotires")
	public String getTwotires() {
		return twotires;
	}
	public void setTwotires(String twotires) {
		this.twotires = twotires;
	}
	@Column(name="childgrants")
	public String getChildgrants() {
		return childgrants;
	}
	public void setChildgrants(String childgrants) {
		this.childgrants = childgrants;
	}
	@Column(name="childsubsidyamount")
	public String getChildsubsidyamount() {
		return childsubsidyamount;
	}
	public void setChildsubsidyamount(String childsubsidyamount) {
		this.childsubsidyamount = childsubsidyamount;
	}
	@Column(name="onlyamounttime")
	public String getOnlyamounttime() {
		return onlyamounttime;
	}
	public void setOnlyamounttime(String onlyamounttime) {
		this.onlyamounttime = onlyamounttime;
	}
	@Column(name="birthregistration")
	public String getBirthregistration() {
		return birthregistration;
	}
	public void setBirthregistration(String birthregistration) {
		this.birthregistration = birthregistration;
	}
	@Column(name="richardIII")
	public String getRichardIII() {
		return richardIII;
	}
	public void setRichardIII(String richardIII) {
		this.richardIII = richardIII;
	}
	@Column(name="obstetricalcard")
	public String getObstetricalcard() {
		return obstetricalcard;
	}
	public void setObstetricalcard(String obstetricalcard) {
		this.obstetricalcard = obstetricalcard;
	}
	@Column(name="onlychildstatuslqd")
	public String getOnlychildstatuslqd() {
		return onlychildstatuslqd;
	}
	public void setOnlychildstatuslqd(String onlychildstatuslqd) {
		this.onlychildstatuslqd = onlychildstatuslqd;
	}
	@Column(name="childgrantslqd")
	public String getChildgrantslqd() {
		return childgrantslqd;
	}
	public void setChildgrantslqd(String childgrantslqd) {
		this.childgrantslqd = childgrantslqd;
	}
	@Column(name="subsidiesstate")
	public String getSubsidiesstate() {
		return subsidiesstate;
	}
	public void setSubsidiesstate(String subsidiesstate) {
		this.subsidiesstate = subsidiesstate;
	}
	@Column(name="getsubsidiestime")
	public String getGetsubsidiestime() {
		return getsubsidiestime;
	}
	public void setGetsubsidiestime(String getsubsidiestime) {
		this.getsubsidiestime = getsubsidiestime;
	}
	@Column(name="money")
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	@Column(name="socialcompensationfee")
	public String getSocialcompensationfee() {
		return socialcompensationfee;
	}
	public void setSocialcompensationfee(String socialcompensationfee) {
		this.socialcompensationfee = socialcompensationfee;
	}
	@Column(name="pregnantnumber")
	public String getPregnantnumber() {
		return pregnantnumber;
	}
	public void setPregnantnumber(String pregnantnumber) {
		this.pregnantnumber = pregnantnumber;
	}
	@Column(name="vaccinenumber")
	public String getVaccinenumber() {
		return vaccinenumber;
	}
	public void setVaccinenumber(String vaccinenumber) {
		this.vaccinenumber = vaccinenumber;
	}

	
	

}
