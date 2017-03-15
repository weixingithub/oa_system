package com.dao.population;

import org.oa_bean.population.LaborInsurance;

import com.dao.BaseDao;

/**
 * 人口劳保信息
 * @author Administrator
 * @date 2016年7月5日
 * @company
 * LaborInsuranceDao.java
 */
public interface LaborInsuranceDao extends BaseDao {
    /**
     * 编辑人口劳保信息
     * @param labor
     */
	public void updateLaborInsurance(LaborInsurance labor);
    /**
     * 删除人口劳保信息
     * @param ids
     */
	public void deleteLaborInsurance(String ids);

}
