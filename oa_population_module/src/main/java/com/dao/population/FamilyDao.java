package com.dao.population;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.population.Family;

import com.dao.BaseDao;

public interface FamilyDao extends BaseDao {
	/**
	 * 条件分页
	 * @param page
	 * @param wheresql
	 * @param params
	 * @param orderby
	 * @return
	 */
	public Page<Family> getConditionFamilyPage(Page<Family> page, String wheresql, 
			List<Object> params, LinkedHashMap<String, String> orderby);
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public Family findFamilyById(String id);
	/**
	 * 根据ids查找
	 * @param ids
	 * @return
	 */
	public List<Family> findFamilyByIds(String ids);
	/**
	 * 根据户籍编号查找
	 * @param householderID
	 * @param orgId
	 * @return
	 */
	public List<Family> findFamilyByHouseholderID(String householderID,String orgId);
	/**
	 * 删除户籍信息
	 * @param ids
	 */
	public void deleteFamily(String ids);
	/**
	 * 更新户籍中户主的信息
	 * @param familyId
	 * @param family
	 */
	public void updateByFamilyHouseholder(Family family);
	
}
