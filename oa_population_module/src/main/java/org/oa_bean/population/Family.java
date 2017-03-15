package org.oa_bean.population;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.oa_bean.cabinet.FileCabinet;
@Entity
@Table(name="c_family")
public class Family {
	/**户籍基本信息(及家庭信息)**/
	private Integer id; //id
	private String householderID;//户籍编号
	private String householdType;//户别   家庭户：1；集体户：2
	private String doublefemaleHouseholds;//是否双女户  是：1；否：0
	private String householderName;//户主姓名
	private String householderContact;//户主联系方式
	private String registryPlace;//户籍地 
	private String registryAddress;//户籍详细地址
	private String currentPlace;//现住地
	private String currentAddress;//现住详细地址
	private String spouseName;// 夫妻姓名
	/**计划生育 **/
	private String oneChildFamily;// 是否独生子女家庭   是：1；否：0
	private String haveTwoChildren;// 是否在计划内生二胎  是：1；否：0
	/** 为优抚对象 **/
	private String placestate; //是否优抚对象  是：1；否：0
	private String placetype; //优抚类型     军人军烈及家属：1；五保户：2
	private String otherplace; //优抚其他
	/** 为困难家庭**/
	private String poorfamilystate;//是否困难家庭  是：1；否：0
	private String poorfamilytype;// 致困原因   因病：1；因残：2；
	private String nopoorfamilystate; //困难家庭其他
	/** 为低保户/低收入**/
	private String lowincomestate; //低保户  是：1；否：0
	private String lowincometype; //低保户类型    A类：1；B类：2；C类：3；
	private String lownumber;//低保户证号
	private String lowincome; //低收入   是：1；否：0
	private String lowincomenumber;  //低收入证号
	private String familydemand;//家庭需求
	private String provisionService;//提供服务
	private String lowincomeAudit;//是否审核  是：1；否：0
	private String enjoyAmount;//享受金额   单位：元/季度
	/** 是否单亲家庭**/
	private String singleparent ; //是否单亲家庭  是：1；否：0
	/** 预防保健 **/
	private String checkamount;// 参加老年体检人数     单位：个
	private String pregnantcheck;// 参加孕前优生人数   单位：个
	private String vaccinecheck;//  接受婴幼儿疫苗人数  单位：个
	/**管理者属性**/
	private String createTime;//创建时间
	private String userId;//创建人id
	private String orgId;//区域id
	private String orgPid;//所属部门父节点
	private String orgName;//区域名称
	
	
	private List<Person> person;//人口对象列表
	private Ground ground;//土地信息对象
	private House house;//房屋对象
	private FileCabinet fileCabinet; //文件管理对象
	
	private String famPCD;//户籍地址:省,市, 区
	private String penPCD;//现住址:省,市, 区
	@Id
    @GeneratedValue
    @Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="householder_id")
	public String getHouseholderID() {
		return householderID;
	}
	public void setHouseholderID(String householderID) {
		this.householderID = householderID;
	}
	@Column(name="household_type")
	public String getHouseholdType() {
		return householdType;
	}
	public void setHouseholdType(String householdType) {
		this.householdType = householdType;
	}
	@Column(name="double_female_house_holds")
	public String getDoublefemaleHouseholds() {
		return doublefemaleHouseholds;
	}
	public void setDoublefemaleHouseholds(String doublefemaleHouseholds) {
		this.doublefemaleHouseholds = doublefemaleHouseholds;
	}
	@Column(name="house_holder_name")
	public String getHouseholderName() {
		return householderName;
	}
	public void setHouseholderName(String householderName) {
		this.householderName = householderName;
	}
	@Column(name="house_holder_contact")
	public String getHouseholderContact() {
		return householderContact;
	}
	public void setHouseholderContact(String householderContact) {
		this.householderContact = householderContact;
	}
	@Column(name="registry_place")
	public String getRegistryPlace() {
		return registryPlace;
	}
	public void setRegistryPlace(String registryPlace) {
		this.registryPlace = registryPlace;
	}
	@Column(name="registry_address")
	public String getRegistryAddress() {
		return registryAddress;
	}
	public void setRegistryAddress(String registryAddress) {
		this.registryAddress = registryAddress;
	}
	@Column(name="current_place")
	public String getCurrentPlace() {
		return currentPlace;
	}
	public void setCurrentPlace(String currentPlace) {
		this.currentPlace = currentPlace;
	}
	@Column(name="current_address")
	public String getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}
	@Column(name="place_state")
	public String getPlacestate() {
		return placestate;
	}
	public void setPlacestate(String placestate) {
		this.placestate = placestate;
	}
	@Column(name="place_type")
	public String getPlacetype() {
		return placetype;
	}
	public void setPlacetype(String placetype) {
		this.placetype = placetype;
	}
	@Column(name="other_place")
	public String getOtherplace() {
		return otherplace;
	}
	public void setOtherplace(String otherplace) {
		this.otherplace = otherplace;
	}
	@Column(name="poor_family_state")
	public String getPoorfamilystate() {
		return poorfamilystate;
	}
	public void setPoorfamilystate(String poorfamilystate) {
		this.poorfamilystate = poorfamilystate;
	}
	@Column(name="poor_family_type")
	public String getPoorfamilytype() {
		return poorfamilytype;
	}
	public void setPoorfamilytype(String poorfamilytype) {
		this.poorfamilytype = poorfamilytype;
	}
	@Column(name="no_poor_family_state")
	public String getNopoorfamilystate() {
		return nopoorfamilystate;
	}
	public void setNopoorfamilystate(String nopoorfamilystate) {
		this.nopoorfamilystate = nopoorfamilystate;
	}
	@Column(name="low_income_state")
	public String getLowincomestate() {
		return lowincomestate;
	}
	public void setLowincomestate(String lowincomestate) {
		this.lowincomestate = lowincomestate;
	}
	@Column(name="low_income_type")
	public String getLowincometype() {
		return lowincometype;
	}
	public void setLowincometype(String lowincometype) {
		this.lowincometype = lowincometype;
	}
	@Column(name="low_number")
	public String getLownumber() {
		return lownumber;
	}
	public void setLownumber(String lownumber) {
		this.lownumber = lownumber;
	}
	@Column(name="low_income")
	public String getLowincome() {
		return lowincome;
	}
	public void setLowincome(String lowincome) {
		this.lowincome = lowincome;
	}
	@Column(name="low_income_numbe")
	public String getLowincomenumber() {
		return lowincomenumber;
	}
	public void setLowincomenumber(String lowincomenumber) {
		this.lowincomenumber = lowincomenumber;
	}
	@Column(name="family_demand")
	public String getFamilydemand() {
		return familydemand;
	}
	public void setFamilydemand(String familydemand) {
		this.familydemand = familydemand;
	}
	@Column(name="enjoy_amount")
	public String getEnjoyAmount() {
		return enjoyAmount;
	}
	public void setEnjoyAmount(String enjoyAmount) {
		this.enjoyAmount = enjoyAmount;
	}
	@Column(name="single_parent")
	public String getSingleparent() {
		return singleparent;
	}
	public void setSingleparent(String singleparent) {
		this.singleparent = singleparent;
	}
	@Column(name="check_amount")
	public String getCheckamount() {
		return checkamount;
	}
	public void setCheckamount(String checkamount) {
		this.checkamount = checkamount;
	}
	@Column(name="pregnant_check")
	public String getPregnantcheck() {
		return pregnantcheck;
	}
	public void setPregnantcheck(String pregnantcheck) {
		this.pregnantcheck = pregnantcheck;
	}
	@Column(name="vaccine_check")
	public String getVaccinecheck() {
		return vaccinecheck;
	}
	public void setVaccinecheck(String vaccinecheck) {
		this.vaccinecheck = vaccinecheck;
	}
	@Column(name="create_time")
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	@Column(name="spouse_name")
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	@Column(name="one_child_family")
	public String getOneChildFamily() {
		return oneChildFamily;
	}
	public void setOneChildFamily(String oneChildFamily) {
		this.oneChildFamily = oneChildFamily;
	}
	@Column(name="have_two_children")
	public String getHaveTwoChildren() {
		return haveTwoChildren;
	}
	public void setHaveTwoChildren(String haveTwoChildren) {
		this.haveTwoChildren = haveTwoChildren;
	}
	@Column(name="provision_Service")
	public String getProvisionService() {
		return provisionService;
	}
	public void setProvisionService(String provisionService) {
		this.provisionService = provisionService;
	}
	@Column(name="low_income_audit")
	public String getLowincomeAudit() {
		return lowincomeAudit;
	}
	public void setLowincomeAudit(String lowincomeAudit) {
		this.lowincomeAudit = lowincomeAudit;
	}
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name="org_id")
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	@OneToMany(fetch=FetchType.LAZY,mappedBy="family")
	public List<Person> getPerson() {
		return person;
	}
	public void setPerson(List<Person> person) {
		this.person = person;
	}
	
	@OneToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST})
	@JoinColumn(name="ground_id")
	public Ground getGround() {
		return ground;
	}
	public void setGround(Ground ground) {
		this.ground = ground;
	}
	@OneToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST})
	@JoinColumn(name="house_id")
	public House getHouse() {
		return house;
	}
	public void setHouse(House house) {
		this.house = house;
	}
	@Column(name="fam_p_c_d")
	public String getFamPCD() {
		return famPCD;
	}
	public void setFamPCD(String famPCD) {
		this.famPCD = famPCD;
	}
	@Column(name="pen_p_c_d")
	public String getPenPCD() {
		return penPCD;
	}
	public void setPenPCD(String penPCD) {
		this.penPCD = penPCD;
	}
	
	@OneToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST})
	@JoinColumn(name="file_id")
	public FileCabinet getFileCabinet() {
		return fileCabinet;
	}
	public void setFileCabinet(FileCabinet fileCabinet) {
		this.fileCabinet = fileCabinet;
	}
	@Column(name="org_name")
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	@Column(name="org_pid")
	public String getOrgPid() {
		return orgPid;
	}
	public void setOrgPid(String orgPid) {
		this.orgPid = orgPid;
	}
	
	
	
}
