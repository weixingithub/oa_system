package com.dao.population;

import java.util.List;

import org.oa_bean.population.FamilyPlanning;
import org.oa_bean.population.Person;

public interface FamilyPlanningService {
	
	/**
	 * 批量新增或者更新计生信息
	 * @return
	 */
	public boolean updateFamilyPlanning(List<Person> listPerson);
	/**
	 * 编辑人口计生信息
	 * @param plan
	 * @return
	 */
	public boolean updateFamilyPlanningService(FamilyPlanning plan);
}
