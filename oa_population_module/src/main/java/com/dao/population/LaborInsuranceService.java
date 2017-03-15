package com.dao.population;

import java.util.List;

import org.oa_bean.population.LaborInsurance;
import org.oa_bean.population.Person;

/**
 * 劳保信息
 * @author Administrator
 *
 */
public interface LaborInsuranceService {
	/**
	 * 批量更新劳保信息
	 * @return
	 */
	public boolean updateLaborInsurance(List<Person> listPerson);
	/**
	 * 编辑人口劳保信息
	 * @param labor
	 * @return
	 */
	public boolean updateLaborInsuranceService(LaborInsurance labor);
}
