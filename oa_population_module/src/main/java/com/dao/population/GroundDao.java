package com.dao.population;

import org.oa_bean.population.Ground;

public interface GroundDao {
	/**
	 * 更新土地信息
	 * @param ground
	 */
	public void updateGround(Ground ground);
	/**
	 * 删除土地信息
	 * @param ids
	 */
	public void deleteGround(String ids);
}
