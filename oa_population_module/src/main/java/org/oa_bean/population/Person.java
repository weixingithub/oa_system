package org.oa_bean.population;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.oa_bean.cabinet.FileCabinet;
/**
 * 庞兰
 * 人口信息实体类
 * @author Administrator
 * @date 2016年6月28日
 * @company
 * Person.java
 */
@Entity
@Table(name="c_person")
public class Person {
	private Integer id;//人口id
	private String name;//姓名
	private String oldname;//曾用名
	private String sex;//性别
	private String citywoman;//是否嫁城女
	private String householdid;//关联户主
	private String ralation;//与户主关系
	private String birthdate;//出生日期
	private String nation;//民族
	private String residenttype;//户口性质
	private String idnumber;//身份证号
	private String tall;//身高
	private String bloodtype;//血型
	private String political;//政治面貌
	private String registry_place;//户籍详细地址地
	private String jurisdiction;//是否辖区内
	private String uniformIdentification;//人口一致标识
	private String current_address;//现居详细地
	private String village_household;//是否本村户籍
	private String economicdistribution;//参加村集体经济分配
	private String occupation;//职业
	private String religion;//宗教信仰
	private String marital_status;//婚姻状况
	private String contact;//联系方式
	private String gridid;//网格id
	private String creatime;//创建时间
	
	private String personUrl;//头像地址
	private String poverty;//是否扶贫对象  0：否， 1:是
	
	//0：低收入，1：六十年代精简 ，2：城市困难群众，3：农村困难群众，4：农村劳模，5：重点优抚对象，6：因病致残对象，7：见义勇为人员
	private String category;//人员类别 
	
	private String userId;//创建者id
	private String orgId;//所属部门id
	private String orgPid;//所属部门父节点
	private String orgName;//部门名称
	
	private Family family;//户籍对象
	private PopulationCivil populationCivil;//民政对象
	private Stability stability;//综治对象
	private LaborInsurance laborInsurance;//劳动保障对象
	private FamilyPlanning familyPlanning;//计生对象
	private FileCabinet fileCabinet; //文件管理对象
	
	private String perPCD;//户籍地址:省,市, 区
	@Id
	@GeneratedValue
	@Column(name="id")
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="oldname")
	public String getOldname() {
		return oldname;
	}
	public void setOldname(String oldname) {
		this.oldname = oldname;
	}
	@Column(name="sex")
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	@Column(name="citywoman")
	public String getCitywoman() {
		return citywoman;
	}
	public void setCitywoman(String citywoman) {
		this.citywoman = citywoman;
	}
	@Column(name="householdid")
	public String getHouseholdid() {
		return householdid;
	}
	public void setHouseholdid(String householdid) {
		this.householdid = householdid;
	}
	@Column(name="ralation")
	public String getRalation() {
		return ralation;
	}
	public void setRalation(String ralation) {
		this.ralation = ralation;
	}
	@Column(name="birthdate")
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	@Column(name="nation")
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	@Column(name="residenttype")
	public String getResidenttype() {
		return residenttype;
	}
	public void setResidenttype(String residenttype) {
		this.residenttype = residenttype;
	}
	@Column(name="idnumber")
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	@Column(name="tall")
	public String getTall() {
		return tall;
	}
	public void setTall(String tall) {
		this.tall = tall;
	}
	@Column(name="bloodtype")
	public String getBloodtype() {
		return bloodtype;
	}
	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}
	@Column(name="political")
	public String getPolitical() {
		return political;
	}
	public void setPolitical(String political) {
		this.political = political;
	}
	@Column(name="registry_place")
	public String getRegistry_place() {
		return registry_place;
	}
	public void setRegistry_place(String registry_place) {
		this.registry_place = registry_place;
	}
	@Column(name="jurisdiction")
	public String getJurisdiction() {
		return jurisdiction;
	}
	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
	@Column(name="uniformIdentification")
	public String getUniformIdentification() {
		return uniformIdentification;
	}
	public void setUniformIdentification(String uniformIdentification) {
		this.uniformIdentification = uniformIdentification;
	}
	@Column(name="current_address")
	public String getCurrent_address() {
		return current_address;
	}
	public void setCurrent_address(String current_address) {
		this.current_address = current_address;
	}
	@Column(name="village_household")
	public String getVillage_household() {
		return village_household;
	}
	public void setVillage_household(String village_household) {
		this.village_household = village_household;
	}
	@Column(name="economicdistribution")
	public String getEconomicdistribution() {
		return economicdistribution;
	}
	public void setEconomicdistribution(String economicdistribution) {
		this.economicdistribution = economicdistribution;
	}
	@Column(name="occupation")
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	@Column(name="religion")
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	@Column(name="marital_status")
	public String getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	@Column(name="contact")
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	@Column(name="creatime")
	public String getCreatime() {
		return creatime;
	}
	public void setCreatime(String creatime) {
		this.creatime = creatime;
	}
	@Column(name="grid")
	public String getGridid() {
		return gridid;
	}
	public void setGridid(String gridid) {
		this.gridid = gridid;
	}
	@Column(name="org_id")
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	@Column(name="user_id")
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="family_id")
	public Family getFamily() {
		return family;
	}
	public void setFamily(Family family) {
		this.family = family;
	}
	@OneToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST})
	@JoinColumn(name="civil_id")
	public PopulationCivil getPopulationCivil() {
		return populationCivil;
	}
	public void setPopulationCivil(PopulationCivil populationCivil) {
		this.populationCivil = populationCivil;
	}
	@OneToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST})
	@JoinColumn(name="stab_id")
	public Stability getStability() {
		return stability;
	}
	public void setStability(Stability stability) {
		this.stability = stability;
	}
	@OneToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST})
	@JoinColumn(name="labor_id")
	public LaborInsurance getLaborInsurance() {
		return laborInsurance;
	}
	public void setLaborInsurance(LaborInsurance laborInsurance) {
		this.laborInsurance = laborInsurance;
	}
	@OneToOne(fetch=FetchType.LAZY,cascade={CascadeType.PERSIST})
	@JoinColumn(name="fplanning_id")
	public FamilyPlanning getFamilyPlanning() {
		return familyPlanning;
	}
	public void setFamilyPlanning(FamilyPlanning familyPlanning) {
		this.familyPlanning = familyPlanning;
	}
	@Column(name="per_p_c_d")
	public String getPerPCD() {
		return perPCD;
	}
	public void setPerPCD(String perPCD) {
		this.perPCD = perPCD;
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
	@Column(name="person_url")
	public String getPersonUrl() {
		return personUrl;
	}
	public void setPersonUrl(String personUrl) {
		this.personUrl = personUrl;
	}
	@Column(name="poverty")
	public String getPoverty() {
		return poverty;
	}
	public void setPoverty(String poverty) {
		this.poverty = poverty;
	}
	@Column(name="category")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	@Column(name="org_pid")
	public String getOrgPid() {
		return orgPid;
	}
	public void setOrgPid(String orgPid) {
		this.orgPid = orgPid;
	}
	
	 
	
}
