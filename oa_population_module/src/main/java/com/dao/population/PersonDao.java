package com.dao.population;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.population.Person;
import org.oa_bean.population.VehicleInformation;

import com.dao.BaseDao;

/**
 * 人口信息
 * @author pl 
 * @date 2016年6月28日
 * @company
 * PersonDao.java
 */
public interface PersonDao extends BaseDao{
	/**
	 * 分页排序显示人口信息
	 * @param page
	 * @return
	 */
	public Page<Person> findPersonPage(Page<Person> page,LinkedHashMap<String, String> orderby);
	/**
	 * 人口信息添加
	 * @param person
	 */
	public void addPerson(Person person);
	/**
	 * 验证身份账号是否已经存在
	 * @param idnumber
	 * @param orgId
	 * @return
	 */
	public List<Person> findPersonByIdnumber(String idnumber, String orgId,String orgPid);
	/**
	 * 人口信息编辑
	 * @param person
	 */
	public void updatePerson(Person person);
	/**
	 * 根据id查询出一条人口信息
	 * @param id
	 * @return 
	 */
	public Person findPersonById(Integer id);
	/**
	 * 根据ids查询出人口信息集合
	 * @param id
	 * @return 
	 */
	public List<Person> findPersonByIds(String ids);
	/**
	 * 条件分页查询
	 * @param page
	 * @param wheresql
	 * @param params
	 * @param orderby
	 * @return
	 */
	public Page<Person> getConditionFamilyPage(Page<Person> page,
			String wheresql, List<Object> params,
			LinkedHashMap<String, String> orderby);
	/**
	 * 批量删除
	 * @param ids
	 */
	public void deletePerson(String ids);
	/**
	 * 分页查询人口车辆信息
	 * @param page
	 * @return
	 */
	public Page<VehicleInformation> findVehiclePage(
			Page<VehicleInformation> page);
	/**
	 * 更新人口与户籍及户主的关系
	 * @param familyId
	 * @param person
	 */
	public void updatePersonByFamily(String familyId,Person person);
	/**
	 * 查询所有人的手机号码
	 * @return 
	 */
	public List findPersonContact();
	/**
	 * 根据计生Id查询人口基本信息
	 * @param familyPlanId
	 * @return
	 */
	public List<Person> findByFamilyPlan(Integer familyPlanId);
	
	/**
	 * 根据区域Id统计人口基本信息
	 * @param orgId
	 * @return
	 */
	public List statisticsData(String sql);
	
}
