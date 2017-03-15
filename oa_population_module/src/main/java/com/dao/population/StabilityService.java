package com.dao.population;

import java.util.List;

import org.oa_bean.population.Person;
import org.oa_bean.population.Stability;

/**
 * 综治信息
 * @author Administrator
 *
 */
public interface StabilityService {
	
	/**
	 * 批量更新综治信息
	 * @return
	 */
	public boolean updateStability(List<Person> listPerson);
	/**
	 * 编辑综治维稳信息
	 * @param stability
	 * @return
	 */
	public boolean updateStabilityService(Stability stability);
}
