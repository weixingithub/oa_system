package com.dao.population;

import org.oa_bean.population.Stability;

import com.dao.BaseDao;

/**
 * 综治维稳信息
 * @author Administrator
 * @date 2016年7月2日
 * @company
 * StabilityDao.java
 */
public interface StabilityDao extends BaseDao {
	/**
	 * 删除人口综治维稳信息
	 * @param ids
	 */
	public void deleteStability(String ids);
    /**
     * 编辑人口综治维稳信息
     * @param stability
     */
	public void updateStability(Stability stability);

}
