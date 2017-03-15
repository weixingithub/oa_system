package com.dao.population;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.oa_bean.Page;
import org.oa_bean.population.Family;
import org.oa_bean.population.Ground;
import org.oa_bean.population.House;
import org.oa_bean.population.Person;

public interface FamilyService  {
	/**
	 * 条件分页
	 * @param page
	 * @param wheresql
	 * @param params
	 * @param orderby
	 * @return
	 */
	public Page<Family> getConditionFamilyPage(Page<Family> page, Family family,
			LinkedHashMap<String, String> orderby,String starttime,String endtime);
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public Family findFamilyById(String id);
	/**
	 * 添加和更新
	 * @param family
	 * @return
	 */
	public boolean addAndUpdateFamilyService(Family family,String operationType,String personId,String personOldId);
	/**
	 * 更新户籍的房屋信息
	 * @param house
	 * @return
	 */
	public boolean updateFamilyByHouseService(House house);
	/**
	 * 更新户籍的土地信息
	 * @param ground
	 * @return
	 */
	public boolean updateFamilyByGroundService(Ground ground);
	/**
	 * 删除操作
	 * @param ids
	 * @return
	 */
	public boolean deleteFamilyService(String ids);
	/**
	 * 根据户籍编号查找
	 * @param householderID
	 * @param orgId
	 * @return
	 */
	public List<Family> findFamilyByHouseholderID(String householderID,String orgId);
	/**
	 * 添加或更新家庭成员
	 */
	public boolean updateFamilyByPerson(String familyId,Set<Person> set);
	/**
	 * 根据ids查找
	 * @param ids
	 * @return
	 */
	public List<Family> findFamilyByIds(String ids);
}

