package com.dao.population;

import org.oa_bean.population.FamilyPlanning;

import com.dao.BaseDao;

/**
 * 人口计生信息
 * @author Administrator
 * @date 2016年7月5日
 * @company
 * FamilyPlanningDao.java
 */
public interface FamilyPlanningDao extends BaseDao{
    /**
     * 编辑人口计生信息
     * @param plan
     */
	public void updateFamilyPlanning(FamilyPlanning plan);
   /**
    * 删除人口计生信息
    * @param ids
    */
	public void deleteFamilyPlanning(String ids);
	

}
