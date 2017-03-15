package com.dao.population;

import org.oa_bean.population.House;

public interface HouseDao {
	/**
	 * 更新房屋信息
	 * @param house
	 */
	public void updateHouse(House house);
	/**
	 * 删除房屋信息
	 * @param ids
	 */
	public void deleteHouse(String ids);
}
