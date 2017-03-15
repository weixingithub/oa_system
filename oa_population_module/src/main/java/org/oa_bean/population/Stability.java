package org.oa_bean.population;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 综治维稳信息
 * @author Administrator
 * @date 2016年7月1日
 * @company
 * Stability.java
 */
@Entity
@Table(name="c_stability")
public class Stability {
	private Integer id;//主键
	private String communitycorrection;//是否社区矫正人员
	private String rectifyid;//社区矫正人员编号
	private String custodyplace;//原羁押场所
	private String rectifytype;//矫正类别
	private String rectifystartdate;//矫正开始日期
	private String rectifyenddate;//矫正结束日期
	private String illegalfund;//是否非法集资人员
	private String reeducationreform;//是否两劳释放人员
	private String recidivist;//是否累惯犯
	private String foursituation;//“四史”情况
	private String threesituation;//“三涉”情况
	private String focuspetitioners;//是否重点上访人员
	private String nuclearpersonnel;//是否涉核人员
	private String involvedInCults;//是否参与邪教组织
	@Id
	@GeneratedValue
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="Communitycorrection")
	public String getCommunitycorrection() {
		return communitycorrection;
	}
	public void setCommunitycorrection(String communitycorrection) {
		this.communitycorrection = communitycorrection;
	}
	@Column(name="RectifyID")
	public String getRectifyid() {
		return rectifyid;
	}
	public void setRectifyid(String rectifyid) {
		this.rectifyid = rectifyid;
	}
	@Column(name="CustodyPlace")
	public String getCustodyplace() {
		return custodyplace;
	}
	public void setCustodyplace(String custodyplace) {
		this.custodyplace = custodyplace;
	}
	@Column(name="RectifyType")
	public String getRectifytype() {
		return rectifytype;
	}
	public void setRectifytype(String rectifytype) {
		this.rectifytype = rectifytype;
	}
	@Column(name="RectifyStartDate")
	public String getRectifystartdate() {
		return rectifystartdate;
	}
	public void setRectifystartdate(String rectifystartdate) {
		this.rectifystartdate = rectifystartdate;
	}
	@Column(name="RectifyEndDate")
	public String getRectifyenddate() {
		return rectifyenddate;
	}
	public void setRectifyenddate(String rectifyenddate) {
		this.rectifyenddate = rectifyenddate;
	}
	@Column(name="Illegalfund")
	public String getIllegalfund() {
		return illegalfund;
	}
	public void setIllegalfund(String illegalfund) {
		this.illegalfund = illegalfund;
	}
	@Column(name="Reeducationreform")
	public String getReeducationreform() {
		return reeducationreform;
	}
	public void setReeducationreform(String reeducationreform) {
		this.reeducationreform = reeducationreform;
	}
	@Column(name="Recidivist")
	public String getRecidivist() {
		return recidivist;
	}
	public void setRecidivist(String recidivist) {
		this.recidivist = recidivist;
	}
	@Column(name="FourSituation")
	public String getFoursituation() {
		return foursituation;
	}
	public void setFoursituation(String foursituation) {
		this.foursituation = foursituation;
	}
	@Column(name="ThreeSituation")
	public String getThreesituation() {
		return threesituation;
	}
	public void setThreesituation(String threesituation) {
		this.threesituation = threesituation;
	}
	@Column(name="FocusPetitioners")
	public String getFocuspetitioners() {
		return focuspetitioners;
	}
	public void setFocuspetitioners(String focuspetitioners) {
		this.focuspetitioners = focuspetitioners;
	}
	@Column(name="Nuclearpersonnel")
	public String getNuclearpersonnel() {
		return nuclearpersonnel;
	}
	public void setNuclearpersonnel(String nuclearpersonnel) {
		this.nuclearpersonnel = nuclearpersonnel;
	}
	@Column(name="involvedInCults")
	public String getInvolvedInCults() {
		return involvedInCults;
	}
	public void setInvolvedInCults(String involvedInCults) {
		this.involvedInCults = involvedInCults;
	}
	


}
