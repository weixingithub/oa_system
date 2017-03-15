package com.dao.population;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.oa_bean.Page;
import org.oa_bean.population.FamilyPlanning;
import org.oa_bean.population.LaborInsurance;
import org.oa_bean.population.Person;
import org.oa_bean.population.PopulationCivil;
import org.oa_bean.population.Stability;
import org.oa_bean.population.VehicleInformation;

/**
 * 人口信息
 * @author pl
 * @date 2016年6月28日
 * @company
 * PersonService.java
 */
public interface PersonService {
	/**
	 * 分页显示人口信息
	 * @param page
	 * @return
	 */
	public Page<Person> findPersonPage(Page<Person> page,LinkedHashMap<String, String> orderby);
    /**
     * 根据ID删除人口信息
     * @param name 
     * @param id
     * @return 
     */
	public boolean delPersonService(String ids, String name);
	/**
	 * 人口信息添加及编辑
	 * @param person
	 * @param contact 
	 * @param owners 
	 * @param integer 
	 * @return 
	 */
	public boolean addAndUpdatePersonService(Person person,String opertionType, Integer personid);
	/**
	 * 根据id查询出一条人口信息
	 * @param id
	 * @return 
	 */
	public Person findPersonById(Integer id);
	/**
	 * 人口基本信息条件分页查询
	 * @param page
	 * @param person
	 * @param orderby
	 * @param starttime
	 * @param endtime
	 * @param stability
	 * @param plan
	 * @param labour
	 * @param civil
	 * @param conditioned 特定制条件
	 * @return
	 */
	public Page<Person> getConditionPersonPage(Page<Person> page, Person person,
			LinkedHashMap<String, String> orderby,String starttime,String endtime,Stability stability,FamilyPlanning plan,LaborInsurance labour,PopulationCivil civil,String conditioned);
	/**
	 * 验证人口车辆车牌号是否已存在
	 * @param licensenumber
	 * @param orgId
	 * @return
	 */
	public List<VehicleInformation> findVehicleByLicenseNumber(
			String licensenumber);
	
	/**
	 * 验证人口身份证号是否已经存在
	 * @param idnumber
	 * @param orgId
	 * @return
	 */
	public List<Person> findPersonByIdnumber(String idnumber, String orgId,String orgPid);
	/**
	 * 更新人口信息是同时更新车辆信息
	 * @param id
	 * @param owners
	 * @param contact
	 * @return
	 */
	public int updatePersonVehicle(Integer id, String owners, String contact);
	/**
	 * 分页查询人口车辆信息
	 * @param page
	 */
	public List<VehicleInformation> findPersonVehicle(Integer id);
	
	/**
	 * 查询所有人的电话号码
	 * @return 
	 */
	public List findPersonContact();
	/**
	 * 批量新增或者更新人口信息
	 * @param list
	 * @param opertinType
	 * @param ids
	 * @return
	 */
	public boolean addAndUpadetPersons(List<Person> list);
	
	/**
	 * 
	 * 根据区域Id统计人口基本信息
	 * @param orgIds 
	 * @return
	 */
	public List<Object[]> statisticsData(String orgIds);

}
