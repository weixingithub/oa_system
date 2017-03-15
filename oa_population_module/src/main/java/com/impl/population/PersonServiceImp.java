package com.impl.population;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.population.Family;
import org.oa_bean.population.FamilyPlanning;
import org.oa_bean.population.LaborInsurance;
import org.oa_bean.population.Person;
import org.oa_bean.population.PopulationCivil;
import org.oa_bean.population.Stability;
import org.oa_bean.population.VehicleInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.cabinet.FileCabinetDao;
import com.dao.population.FamilyDao;
import com.dao.population.FamilyPlanningDao;
import com.dao.population.LaborInsuranceDao;
import com.dao.population.PersonDao;
import com.dao.population.PersonService;
import com.dao.population.PopulationCivilDao;
import com.dao.population.StabilityDao;
import com.dao.population.VehicleDao;

/**
 * 人口信息
 * @author pl 
 * @date 2016年6月28日
 * @company
 * PersonServiceImp.java
 */
@Service(value="personService")
public class PersonServiceImp implements PersonService{
	private PersonDao personDao;//人口基本信息
	private VehicleDao vehicleDao;//人口车辆信息
	private StabilityDao stabilityDao;//人口综治信息
	private PopulationCivilDao populationcivilDao;//人口民政信息
	private FamilyPlanningDao familyplanDao;//人口计生信息
	private LaborInsuranceDao  laborinsuranceDao;//人口劳保信息
	private FamilyDao familyDao;//户籍信息
	private FileCabinetDao fileCabinetDao;//文件柜信息
	
	public FileCabinetDao getFileCabinetDao() {
		return fileCabinetDao;
	}
	@Autowired
	public void setFileCabinetDao(FileCabinetDao fileCabinetDao) {
		this.fileCabinetDao = fileCabinetDao;
	}
    public FamilyDao getFamilyDao() {
		return familyDao;
	}
    @Autowired
	public void setFamilyDao(FamilyDao familyDao) {
		this.familyDao = familyDao;
	}
	public LaborInsuranceDao getLaborinsuranceDao() {
		return laborinsuranceDao;
	}
    @Autowired
	public void setLaborinsuranceDao(LaborInsuranceDao laborinsuranceDao) {
		this.laborinsuranceDao = laborinsuranceDao;
	}
	public FamilyPlanningDao getFamilyplanDao() {
		return familyplanDao;
	}
    @Autowired
	public void setFamilyplanDao(FamilyPlanningDao familyplanDao) {
		this.familyplanDao = familyplanDao;
	}
	public PopulationCivilDao getPopulationcivilDao() {
		return populationcivilDao;
	}
    @Autowired
	public void setPopulationcivilDao(PopulationCivilDao populationcivilDao) {
		this.populationcivilDao = populationcivilDao;
	}
	public VehicleDao getVehicleDao() {
		return vehicleDao;
	}
    @Autowired
	public void setVehicleDao(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}
	public PersonDao getPersonDao() {
		return personDao;
	}
    @Autowired
	public void setPersonDao(PersonDao personDao) {
		this.personDao = personDao;
	}
	public StabilityDao getStabilityDao() {
		return stabilityDao;
	}
	@Autowired
	public void setStabilityDao(StabilityDao stabilityDao) {
		this.stabilityDao = stabilityDao;
	}
	/**
     * 分页显示人口信息
     */
	@Override
	@Transactional
	public Page<Person> findPersonPage(Page<Person> page,LinkedHashMap<String, String> orderby) {
		return personDao.findPersonPage(page, orderby);
	}
	/**
	 * 根据id删除人口信息
	 */
	@Override
	@Transactional
	public boolean delPersonService(String ids,String name) {
		boolean result=false;
		try{
			List<Person> listPerson = personDao.findPersonByIds(ids);
			StringBuffer populationcivilIds = new StringBuffer();
			StringBuffer familyplanIds = new StringBuffer();
			StringBuffer laborinsuranceIds = new StringBuffer();
			StringBuffer stabilityIds = new StringBuffer();
			StringBuffer fileCabinetIds = new StringBuffer();
			
			for (Person person : listPerson) {
				if(person.getPopulationCivil().getId()!=null && !"".equals(person.getPopulationCivil().getId())){
					populationcivilIds.append(person.getPopulationCivil().getId());
					populationcivilIds.append(",");
				}
				if(person.getFamilyPlanning().getId()!=null && !"".equals(person.getFamilyPlanning().getId())){
					familyplanIds.append(person.getFamilyPlanning().getId());
					familyplanIds.append(",");
				}
				if(person.getLaborInsurance().getId()!=null && !"".equals(person.getLaborInsurance().getId())){
					laborinsuranceIds.append(person.getLaborInsurance().getId());
					laborinsuranceIds.append(",");
				}
				if(person.getStability().getId()!=null && !"".equals(person.getStability().getId())){
					stabilityIds.append(person.getStability().getId());
					stabilityIds.append(",");
				}
				if(person.getFileCabinet().getId()!=null && !"".equals(person.getFileCabinet().getId())){
					fileCabinetIds.append(person.getFileCabinet().getId());
					fileCabinetIds.append(",");
				}
			}
			populationcivilIds = populationcivilIds.deleteCharAt(populationcivilIds.length()-1);
			familyplanIds = familyplanIds.deleteCharAt(familyplanIds.length()-1);
			laborinsuranceIds = laborinsuranceIds.deleteCharAt(laborinsuranceIds.length()-1);
			stabilityIds = stabilityIds.deleteCharAt(stabilityIds.length()-1);
			fileCabinetIds = fileCabinetIds.deleteCharAt(fileCabinetIds.length()-1);
			
			vehicleDao.deleteVehicle(ids);//删除车辆信息
			personDao.deletePerson(ids);//删除人口基本信息
			populationcivilDao.deletePopulationCivil(populationcivilIds.toString());//删除人口民政信息
			familyplanDao.deleteFamilyPlanning(familyplanIds.toString());//删除人口计生信息
			laborinsuranceDao.deleteLaborInsurance(laborinsuranceIds.toString());//删除人口劳保信息
			stabilityDao.deleteStability(stabilityIds.toString());//删除人口综治信息
			fileCabinetDao.deleteFile(fileCabinetIds.toString());//删除人口文件信息
			result=true;
		}catch(Exception e){
			e.printStackTrace();
			result=false;
		}
		return result;
	}
	/**
	 * 添加人口信息
	 */
	@Override
	@Transactional
	public boolean addAndUpdatePersonService(Person person,String opertionType,Integer id) {
		boolean result=false;
		try{
			if("add".equals(opertionType)){
				personDao.addPerson(person);
			}else{
				personDao.updatePerson(person);
				if(person.getFamily()!=null && "1".equals(person.getRalation())){
					Family family = new Family();
					family.setId(person.getFamily().getId());
					family.setHouseholderName(person.getName());
					family.setHouseholderContact(person.getContact());
					familyDao.updateByFamilyHouseholder(family);
				}
				vehicleDao.updatePersonVehicle(id,person.getName(),person.getContact());
			}
			result= true;
		}catch(Exception e){
			e.printStackTrace();
			result=false;
		}
		return result;
	}
	/**
	 * 验证人口身份证号是否存在
	 */
	@Override
	public List<Person> findPersonByIdnumber(String idnumber, String orgId,String orgPid) {
		return personDao.findPersonByIdnumber(idnumber,orgId,orgPid);
	}
	/**
	 * 根据id查询出一条人口信息
	 */
	@Override
	public Person findPersonById(Integer id) {
		return personDao.findPersonById(id);
	}
	/**
	 * 人口信息的条件查询
	 * @param page
	 * @param person
	 * @param orderby
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	@Override
	public Page<Person> getConditionPersonPage(Page<Person> page,
			Person person, LinkedHashMap<String, String> orderby,
			String starttime, String endtime,Stability stability,FamilyPlanning plan,LaborInsurance labour,PopulationCivil civil,String conditioned) {
		 StringBuffer wheresql = new StringBuffer("1=1");
		 List<Object> params = new ArrayList<Object>();
		 if(person.getOrgId()!=null &&!"".equals(person.getOrgId())){
			 String[] arr = person.getOrgId().split(",");
				wheresql.append(" and o.orgId in (");
				for(int i=0;i<arr.length;i++){
					wheresql.append(" ? ");
					if(i<arr.length-1){
						wheresql.append(",");
					}
					params.add(arr[i].trim());
				}
				wheresql.append(")");
		 }
		 if(person.getOrgPid()!=null &&!"".equals(person.getOrgPid())){
				wheresql.append(" and o.orgPid = ? ");
			 	params.add(person.getOrgPid());
		 }
		 if(person.getName()!=null && !"".equals(person.getName())){
			 	wheresql.append(" and o.name like ? ");
			 	params.add("%"+person.getName().trim()+"%");
		 }
		 if(person.getIdnumber()!=null && !"".equals(person.getIdnumber())){
			    wheresql.append(" and o.idnumber like ? ");
			 	params.add("%"+person.getIdnumber().trim()+"%");
		 }
		 if(starttime != null && !"".equals(starttime)){
		    	wheresql.append(" and o.birthdate  >= ? ");
		    	params.add(starttime);
		 }
		 if(endtime != null && !"".equals(endtime)){
		    	wheresql.append(" and o.birthdate <= ? ");
		    	params.add(endtime);
		 }
		 
		 if(conditioned !=null && !"".equals(conditioned)){
			if("notFamily".equals(conditioned)){ //没有户籍信息的人口
				 wheresql.append(" and o.ralation=''");
			 }else if("notHouseholderAll".equals(conditioned)){  //非户主的人口
				 wheresql.append(" and o.ralation<>'1' ");
			 } 
		 }
		 //综治信息模糊查询
		if(person.getStability()!=null && !"".equals(person.getStability())){
			 if(person.getStability().getRectifyid()!=null && !"".equals(person.getStability().getRectifyid())){
				    wheresql.append(" and o.stability.rectifyid like ? ");
				 	params.add("%"+person.getStability().getRectifyid().trim()+"%");
			 }
			 if(person.getStability().getCommunitycorrection()!=null && !"".equals(person.getStability().getCommunitycorrection())){
				 wheresql.append(" and o.stability.communitycorrection like ? ");
				 params.add("%"+person.getStability().getCommunitycorrection()+"%");
			 }
			 if(person.getStability().getCustodyplace()!=null && !"".equals(person.getStability().getCustodyplace())){
				 wheresql.append(" and o.stability.custodyplace like ? ");
				 params.add("%"+person.getStability().getCustodyplace().trim()+"%");
			 }
			 if(person.getStability().getRectifytype()!=null && !"".equals(person.getStability().getRectifytype())){
				 wheresql.append(" and o.stability.rectifytype like ? ");
				 params.add("%"+person.getStability().getRectifytype()+"%");
			 }
			 if(person.getStability().getRectifystartdate()!=null && !"".equals(person.getStability().getRectifystartdate())){
				 wheresql.append(" and o.stability.rectifystartdate like ? ");
				 params.add("%"+person.getStability().getRectifystartdate()+"%");
			 }
			 if(person.getStability().getRectifyenddate()!=null && !"".equals(person.getStability().getRectifyenddate())){
				 wheresql.append(" and o.stability.rectifyenddate like ? ");
				 params.add("%"+person.getStability().getRectifyenddate()+"%");
			 }
			 if(person.getStability().getIllegalfund()!=null && !"".equals(person.getStability().getIllegalfund())){
				 wheresql.append(" and o.stability.illegalfund like ? ");
				 params.add("%"+person.getStability().getIllegalfund()+"%");
			 }
			 if(person.getStability().getReeducationreform()!=null && !"".equals(person.getStability().getReeducationreform())){
				 wheresql.append(" and o.stability.reeducationreform like ? ");
				 params.add("%"+person.getStability().getReeducationreform()+"%");
			 }
			 if(person.getStability().getRecidivist()!=null && !"".equals(person.getStability().getRecidivist())){
				 wheresql.append(" and o.stability.recidivist like ? ");
				 params.add("%"+person.getStability().getRecidivist()+"%");
			 }
			 if(person.getStability().getFoursituation()!=null && !"".equals(person.getStability().getFoursituation())){
				 wheresql.append(" and o.stability.foursituation like ? ");
				 params.add("%"+person.getStability().getFoursituation()+"%");
			 }
			 if(person.getStability().getThreesituation()!=null && !"".equals(person.getStability().getThreesituation())){
				 wheresql.append(" and o.stability.threesituation like ? ");
				 params.add("%"+person.getStability().getThreesituation()+"%");
			 }
			 if(person.getStability().getFocuspetitioners()!=null && !"".equals(person.getStability().getFocuspetitioners())){
				 wheresql.append(" and o.stability.focuspetitioners like ? ");
				 params.add("%"+person.getStability().getFocuspetitioners()+"%");
			 }
			 if(person.getStability().getNuclearpersonnel()!=null && !"".equals(person.getStability().getNuclearpersonnel())){
				 wheresql.append(" and o.stability.nuclearpersonnel like ? ");
				 params.add("%"+person.getStability().getNuclearpersonnel()+"%");
			 }
			 if(person.getStability().getInvolvedInCults()!=null && !"".equals(person.getStability().getInvolvedInCults())){
				 wheresql.append(" and o.stability.involvedInCults like ? ");
				 params.add("%"+person.getStability().getInvolvedInCults()+"%");
			 }
		}
			//计生管理模糊查询
			if(person.getFamilyPlanning()!=null && !"".equals(person.getFamilyPlanning())){
				if(person.getFamilyPlanning().getMarriagestatus()!=null && !"".equals(person.getFamilyPlanning().getMarriagestatus())){
					 wheresql.append(" and o.familyPlanning.marriagestatus like ? ");
					 params.add("%"+person.getFamilyPlanning().getMarriagestatus()+"%");
				}
				if(person.getFamilyPlanning().getContraceptive()!=null && !"".equals(person.getFamilyPlanning().getContraceptive())){
					 wheresql.append(" and o.familyPlanning.contraceptive like ? ");
					 params.add("%"+person.getFamilyPlanning().getContraceptive()+"%");
				}
				if(person.getFamilyPlanning().getOnlychildstatus()!=null && !"".equals(person.getFamilyPlanning().getOnlychildstatus())){
					 wheresql.append(" and o.familyPlanning.onlychildstatus like ? ");
					 params.add("%"+person.getFamilyPlanning().getOnlychildstatus()+"%");
				}
				if(person.getFamilyPlanning().getTwotires()!=null && !"".equals(person.getFamilyPlanning().getTwotires())){
					 wheresql.append(" and o.familyPlanning.twotires like ? ");
					 params.add("%"+person.getFamilyPlanning().getTwotires()+"%");
				}
				if(person.getFamilyPlanning().getChildgrants()!=null && !"".equals(person.getFamilyPlanning().getChildgrants())){
					 wheresql.append(" and o.familyPlanning.childgrants like ? ");
					 params.add("%"+person.getFamilyPlanning().getChildgrants()+"%");
				}
				if(person.getFamilyPlanning().getBirthregistration()!=null && !"".equals(person.getFamilyPlanning().getBirthregistration())){
					 wheresql.append(" and o.familyPlanning.birthregistration like ? ");
					 params.add("%"+person.getFamilyPlanning().getBirthregistration()+"%");
				}
				if(person.getFamilyPlanning().getRichardIII()!=null && !"".equals(person.getFamilyPlanning().getRichardIII())){
					 wheresql.append(" and o.familyPlanning.richardIII like ? ");
					 params.add("%"+person.getFamilyPlanning().getRichardIII()+"%");
				}
				if(person.getFamilyPlanning().getObstetricalcard()!=null && !"".equals(person.getFamilyPlanning().getObstetricalcard())){
					 wheresql.append(" and o.familyPlanning.obstetricalcard like ? ");
					 params.add("%"+person.getFamilyPlanning().getObstetricalcard()+"%");
				}
				if(person.getFamilyPlanning().getSubsidiesstate()!=null && !"".equals(person.getFamilyPlanning().getSubsidiesstate())){
					 wheresql.append(" and o.familyPlanning.subsidiesstate like ? ");
					 params.add("%"+person.getFamilyPlanning().getSubsidiesstate()+"%");
				}
			} 
			//劳保管理信息
			if(person.getLaborInsurance()!=null && !"".equals(person.getLaborInsurance())){
				if(person.getLaborInsurance().getEndowmentinsurance()!=null && !"".equals(person.getLaborInsurance().getEndowmentinsurance())){
					 wheresql.append(" and o.laborInsurance.endowmentinsurance like ? ");
					 params.add("%"+person.getLaborInsurance().getEndowmentinsurance()+"%");
				}
				if(person.getLaborInsurance().getPensiontype()!=null && !"".equals(person.getLaborInsurance().getPensiontype())){
					 wheresql.append(" and o.laborInsurance.pensiontype like ? ");
					 params.add("%"+person.getLaborInsurance().getPensiontype()+"%");
				}
				if(person.getLaborInsurance().getPensionreturn()!=null && !"".equals(person.getLaborInsurance().getPensionreturn())){
					 wheresql.append(" and o.laborInsurance.pensionreturn like ? ");
					 params.add("%"+person.getLaborInsurance().getPensionreturn()+"%");
				}
				if(person.getLaborInsurance().getMedicalinsurance()!=null && !"".equals(person.getLaborInsurance().getMedicalinsurance())){
					 wheresql.append(" and o.laborInsurance.medicalinsurance like ? ");
					 params.add("%"+person.getLaborInsurance().getMedicalinsurance()+"%");
				}
				if(person.getLaborInsurance().getMedicaltype()!=null && !"".equals(person.getLaborInsurance().getMedicaltype())){
					 wheresql.append(" and o.laborInsurance.medicaltype like ? ");
					 params.add("%"+person.getLaborInsurance().getMedicaltype()+"%");
				}
				if(person.getLaborInsurance().getNationallowances()!=null && !"".equals(person.getLaborInsurance().getNationallowances())){
					 wheresql.append(" and o.laborInsurance.nationallowances like ? ");
					 params.add("%"+person.getLaborInsurance().getNationallowances()+"%");
				}
				if(person.getLaborInsurance().getEndowmentinsurance()!=null && !"".equals(person.getLaborInsurance().getEndowmentinsurance())){
					 wheresql.append(" and o.laborInsurance.endowmentinsurance like ? ");
					 params.add("%"+person.getLaborInsurance().getEndowmentinsurance()+"%");
				}
				if(person.getLaborInsurance().getRetirepension()!=null && !"".equals(person.getLaborInsurance().getRetirepension())){
					 wheresql.append(" and o.laborInsurance.retirepension like ? ");
					 params.add("%"+person.getLaborInsurance().getRetirepension()+"%");
				}
				if(person.getLaborInsurance().getEmploymentstatus()!=null && !"".equals(person.getLaborInsurance().getEmploymentstatus())){
					 wheresql.append(" and o.laborInsurance.employmentstatus like ? ");
					 params.add("%"+person.getLaborInsurance().getEmploymentstatus()+"%");
				}
				if(person.getLaborInsurance().getEmploymentnature()!=null && !"".equals(person.getLaborInsurance().getEmploymentnature())){
					 wheresql.append(" and o.laborInsurance.employmentnature like ? ");
					 params.add("%"+person.getLaborInsurance().getEmploymentnature()+"%");
				}
				if(person.getLaborInsurance().getJobintension()!=null && !"".equals(person.getLaborInsurance().getJobintension())){
					 wheresql.append(" and o.laborInsurance.jobintension like ? ");
					 params.add("%"+person.getLaborInsurance().getJobintension()+"%");
				}
				if(person.getLaborInsurance().getUnemployregistration()!=null && !"".equals(person.getLaborInsurance().getUnemployregistration())){
					 wheresql.append(" and o.laborInsurance.unemployregistration like ? ");
					 params.add("%"+person.getLaborInsurance().getUnemployregistration()+"%");
				}
				if(person.getLaborInsurance().getEnjoypettyloan()!=null && !"".equals(person.getLaborInsurance().getEnjoypettyloan())){
					 wheresql.append(" and o.laborInsurance.enjoypettyloan like ? ");
					 params.add("%"+person.getLaborInsurance().getEnjoypettyloan()+"%");
				}
			}
			//民政信息管理模糊查询条件
			if(person.getPopulationCivil()!=null && !"".equals(person.getPopulationCivil())){
				if(person.getPopulationCivil().getDisabled()!=null && !"".equals(person.getPopulationCivil().getDisabled())){
					 wheresql.append(" and o.populationCivil.disabled like ? ");
					 params.add("%"+person.getPopulationCivil().getDisabled()+"%");
				}
				if(person.getPopulationCivil().getDisablitygrade()!=null && !"".equals(person.getPopulationCivil().getDisablitygrade())){
					 wheresql.append(" and o.populationCivil.disablitygrade like ? ");
					 params.add("%"+person.getPopulationCivil().getDisablitygrade()+"%");
				}
				if(person.getPopulationCivil().getOrphanschildren()!=null && !"".equals(person.getPopulationCivil().getOrphanschildren())){
					 wheresql.append(" and o.populationCivil.orphanschildren like ? ");
					 params.add("%"+person.getPopulationCivil().getOrphanschildren()+"%");
				}
				if(person.getPopulationCivil().getReceivegrantsorphan()!=null && !"".equals(person.getPopulationCivil().getReceivegrantsorphan())){
					 wheresql.append(" and o.populationCivil.receivegrantsorphan like ? ");
					 params.add("%"+person.getPopulationCivil().getReceivegrantsorphan()+"%");
				}
				if(person.getPopulationCivil().getDisabilitybenefits()!=null && !"".equals(person.getPopulationCivil().getDisabilitybenefits())){
					 wheresql.append(" and o.populationCivil.disabilitybenefits like ? ");
					 params.add("%"+person.getPopulationCivil().getDisabilitybenefits()+"%");
				}
				if(person.getPopulationCivil().getActivearmy()!=null && !"".equals(person.getPopulationCivil().getActivearmy())){
					 wheresql.append(" and o.populationCivil.activearmy like ? ");
					 params.add("%"+person.getPopulationCivil().getActivearmy()+"%");
				}
				if(person.getPopulationCivil().getElderly()!=null && !"".equals(person.getPopulationCivil().getElderly())){
					 wheresql.append(" and o.populationCivil.elderly like ? ");
					 params.add("%"+person.getPopulationCivil().getElderly()+"%");
				}
				if(person.getPopulationCivil().getSubsidies()!=null && !"".equals(person.getPopulationCivil().getSubsidies())){
					 wheresql.append(" and o.populationCivil.subsidies like ? ");
					 params.add("%"+person.getPopulationCivil().getSubsidies()+"%");
				}
				if(person.getPopulationCivil().getVeteran()!=null && !"".equals(person.getPopulationCivil().getVeteran())){
					 wheresql.append(" and o.populationCivil.veteran like ? ");
					 params.add("%"+person.getPopulationCivil().getVeteran()+"%");
				}
				if(person.getPopulationCivil().getEmptynester()!=null && !"".equals(person.getPopulationCivil().getEmptynester())){
					 wheresql.append(" and o.populationCivil.emptynester like ? ");
					 params.add("%"+person.getPopulationCivil().getEmptynester()+"%");
				}
				if(person.getPopulationCivil().getOldarmy()!=null && !"".equals(person.getPopulationCivil().getOldarmy())){
					 wheresql.append(" and o.populationCivil.oldarmy like ? ");
					 params.add("%"+person.getPopulationCivil().getOldarmy()+"%");
				}
				if(person.getPopulationCivil().getRecadres()!=null && !"".equals(person.getPopulationCivil().getRecadres())){
					 wheresql.append(" and o.populationCivil.recadres like ? ");
					 params.add("%"+person.getPopulationCivil().getRecadres()+"%");
				}
				if(person.getPopulationCivil().getModelworkers()!=null && !"".equals(person.getPopulationCivil().getModelworkers())){
					 wheresql.append(" and o.populationCivil.modelworkers like ? ");
					 params.add("%"+person.getPopulationCivil().getModelworkers()+"%");
				}
			}
			
			
		return personDao.getConditionFamilyPage(page, wheresql.toString(), params, orderby);
	}
	/**
	 * 验证人口车辆信息的车牌号是否已存在
	 */
	@Override
	public List<VehicleInformation> findVehicleByLicenseNumber(String licensenumber) {
		return vehicleDao.findVehicleByLicenseNumber(licensenumber);
	}
	/**
	 * 根据id查询人口车辆信息
	 */
	@Override
	public List<VehicleInformation> findPersonVehicle(Integer id) {
		String sql="SELECT * FROM c_vehicleinformation WHERE person_id= "+id;
		return vehicleDao.findPersonVehicle(sql);
	}
	/**
	 * 人口更新时更新车辆信息
	 */
	@Override
	@Transactional
	public int updatePersonVehicle(Integer id,String owners,String contact) {
		return vehicleDao.updatePersonVehicle(id,owners,contact);
	}
	
	/**
	 * 查询所有人的手机号码
	 * @return 
	 */
	@Override
	@Transactional
	public List findPersonContact() {
		return personDao.findPersonContact();
	}
	
	/**
	 * 新增或者修改人口基本信息
	 */
	@Override
	@Transactional
	public boolean addAndUpadetPersons(List<Person> list) {
		boolean result=false;
		try{
			for(int i=0;i<list.size();i++){
				Person person = null;
				person = list.get(i);
				List<Person> listIdnumber = personDao.findPersonByIdnumber(person.getIdnumber(),null,null);
				if(listIdnumber.size()<1){
					personDao.addPerson(person);
				}
			}
			result= true;
		}catch(Exception e){
			e.printStackTrace();
			result=false;
		}
		return result;
	}
	
	/**
	 * 
	 * 根据区域Id统计人口基本信息
	 * @param orgIds 
	 * @return
	 */
	@Override
	public List<Object[]> statisticsData(String orgIds) {
		StringBuffer sql= new StringBuffer();
		sql.append(" SELECT COUNT(*) AS 'orgCount', o.org_name AS 'orgName' ");
		sql.append(" ,COUNT(  CASE WHEN p.sex = 0 THEN 'sex' ELSE NULL END ) AS 'man'");
		sql.append(" ,COUNT(  CASE WHEN p.sex = 1 THEN 'sex' ELSE NULL END ) AS 'woman'");
		
		sql.append(" ,COUNT(  CASE WHEN p.village_household = 0 THEN 'village_household' ELSE NULL END ) AS 'isVillage'");
		sql.append(" ,COUNT(  CASE WHEN p.village_household = 1 THEN 'village_household' ELSE NULL END ) AS 'notVillage'");
		
		sql.append(" ,COUNT(  CASE WHEN p.residenttype = 1 THEN 'residenttype' ELSE NULL END ) AS 'isResidenttype'");
		sql.append(" ,COUNT(  CASE WHEN p.residenttype = 2 THEN 'residenttype' ELSE NULL END ) AS 'notResidenttype'");
		
		sql.append(" ,COUNT(  CASE WHEN p.citywoman = 1 THEN 'citywoman' ELSE NULL END ) AS 'isCitywoman'");
		sql.append(" ,COUNT(  CASE WHEN p.citywoman = 2 THEN 'citywoman' ELSE NULL END ) AS 'notCitywoman'");
		
		sql.append(" ,COUNT(  CASE WHEN p.economicdistribution = 0 THEN 'economicdistribution' ELSE NULL END ) AS 'isEconomicdistribution'");
		sql.append(" ,COUNT(  CASE WHEN p.economicdistribution = 1 THEN 'economicdistribution' ELSE NULL END ) AS 'notEconomicdistribution'");
		
		sql.append(" ,COUNT(  CASE WHEN p.nation = 1 THEN 'nation' ELSE NULL END ) AS 'isHan'");
		sql.append(" ,COUNT(  CASE WHEN p.nation = 2 THEN 'nation' ELSE NULL END ) AS 'notHan'");
		sql.append(" FROM oa_org o, c_person p  WHERE o.org_id = p.org_id ");
		if(orgIds!=null && !"".endsWith(orgIds)){
			sql.append(" AND p.org_id in("+orgIds+") ");
		}
		sql.append(" Group BY  o.org_id");
		return personDao.statisticsData(sql.toString());
	}
}
