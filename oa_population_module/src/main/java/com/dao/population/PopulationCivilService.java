package com.dao.population;

import java.util.List;

import org.oa_bean.population.Person;
import org.oa_bean.population.PopulationCivil;
/**
 * 民政信息
 * @author Administrator
 *
 */
public interface PopulationCivilService {
	
	/**
	 * 批量更新民政信息
	 * @return
	 */
	public boolean updatePopulationCivil(List<Person> listPerson);
	/**
	 * 编辑人口民政信息
	 * @param pc
	 * @return
	 */
	public boolean updatePopulationCivilService(PopulationCivil pc);
}
