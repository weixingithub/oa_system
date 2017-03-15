package com.dao.population;

import org.oa_bean.population.PopulationCivil;

import com.dao.BaseDao;

/**
 * 人口民政信息
 * @author Administrator
 * @date 2016年7月5日
 * @company
 * PopulationCivilDao.java
 */
public interface PopulationCivilDao extends BaseDao {
	/**
	 * 编辑人口民政信息
	 * @param person
	 */
	public void updatePopulationCivil(PopulationCivil pc);
	/**
	 * 删除人口民政信息
	 * @param ids
	 */
	public void deletePopulationCivil(String ids);

}
