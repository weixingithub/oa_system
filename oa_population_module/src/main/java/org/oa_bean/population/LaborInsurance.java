package org.oa_bean.population;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 个人劳动保障基础信息
 * @author Administrator
 * @date 2016年7月1日
 * @company
 * LaborInsurance.java
 */
@Entity
@Table(name="c_laborinsurance")
public class LaborInsurance {
	private Integer id;//主键
	private String endowmentinsurance;//养老保险
	private String pensiontype ;//养老保险类型
	private String pensionreturn;//是否享受养老金返还
    private String medicalinsurance;//医疗保险
    private String medicaltype;//医疗保险类型
    private String nationallowances;//是否享受国家低保
    private String socialsubsidies;//是否享受社保补贴
    private String enjoymenttime;//享受年限
    private String retirepension;//是否领取养老金
    private String pensionlqd;//领取地
    private String employmentstatus;//就业状态
    private String employmentnature;//就业性质
    private String officename;//就职单位名称
    private String jobintension;//求职意向
    private String unemployregistration;//是否办理就业失业登记
    private String remark;//备注
    private String enjoypettyloan;//私营企业是否享受小额贷款
    @Id
	@GeneratedValue
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="endowmentinsurance")
	public String getEndowmentinsurance() {
		return endowmentinsurance;
	}
	public void setEndowmentinsurance(String endowmentinsurance) {
		this.endowmentinsurance = endowmentinsurance;
	}
	@Column(name="pensiontype")
	public String getPensiontype() {
		return pensiontype;
	}
	public void setPensiontype(String pensiontype) {
		this.pensiontype = pensiontype;
	}
	@Column(name="pensionreturn")
	public String getPensionreturn() {
		return pensionreturn;
	}
	public void setPensionreturn(String pensionreturn) {
		this.pensionreturn = pensionreturn;
	}
	@Column(name="medicalinsurance")
	public String getMedicalinsurance() {
		return medicalinsurance;
	}
	public void setMedicalinsurance(String medicalinsurance) {
		this.medicalinsurance = medicalinsurance;
	}
	@Column(name="medicaltype")
	public String getMedicaltype() {
		return medicaltype;
	}
	public void setMedicaltype(String medicaltype) {
		this.medicaltype = medicaltype;
	}
	@Column(name="nationallowances")
	public String getNationallowances() {
		return nationallowances;
	}
	public void setNationallowances(String nationallowances) {
		this.nationallowances = nationallowances;
	}@Column(name="socialsubsidies")
	public String getSocialsubsidies() {
		return socialsubsidies;
	}
	public void setSocialsubsidies(String socialsubsidies) {
		this.socialsubsidies = socialsubsidies;
	}
	@Column(name="enjoymenttime")
	public String getEnjoymenttime() {
		return enjoymenttime;
	}
	public void setEnjoymenttime(String enjoymenttime) {
		this.enjoymenttime = enjoymenttime;
	}
	@Column(name="retirepension")
	public String getRetirepension() {
		return retirepension;
	}
	public void setRetirepension(String retirepension) {
		this.retirepension = retirepension;
	}
	@Column(name="pensionlqd")
	public String getPensionlqd() {
		return pensionlqd;
	}
	public void setPensionlqd(String pensionlqd) {
		this.pensionlqd = pensionlqd;
	}
	@Column(name="employmentstatus")
	public String getEmploymentstatus() {
		return employmentstatus;
	}
	public void setEmploymentstatus(String employmentstatus) {
		this.employmentstatus = employmentstatus;
	}
	@Column(name="employmentnature")
	public String getEmploymentnature() {
		return employmentnature;
	}
	public void setEmploymentnature(String employmentnature) {
		this.employmentnature = employmentnature;
	}
	@Column(name="officename")
	public String getOfficename() {
		return officename;
	}
	public void setOfficename(String officename) {
		this.officename = officename;
	}
	@Column(name="jobintension")
	public String getJobintension() {
		return jobintension;
	}
	public void setJobintension(String jobintension) {
		this.jobintension = jobintension;
	}
	@Column(name="unemployregistration")
	public String getUnemployregistration() {
		return unemployregistration;
	}
	public void setUnemployregistration(String unemployregistration) {
		this.unemployregistration = unemployregistration;
	}
	@Column(name="remark")
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="enjoypettyloan")
	public String getEnjoypettyloan() {
		return enjoypettyloan;
	}
	public void setEnjoypettyloan(String enjoypettyloan) {
		this.enjoypettyloan = enjoypettyloan;
	}
    

}
