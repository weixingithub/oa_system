package com.impl.population;

import org.oa_bean.population.LaborInsurance;
import org.springframework.stereotype.Repository;

import com.dao.population.LaborInsuranceDao;
import com.impl.BaseDaoImpl;

/**
 * 人口劳保信息
 * @author Administrator
 * @date 2016年7月5日
 * @company
 * LaborInsuranceDaoImp.java
 */
@Repository(value="laborinsuranceDao")
public class LaborInsuranceDaoImp extends BaseDaoImpl implements LaborInsuranceDao{
    /**
     * 编辑人口劳保信息
     */
	@Override
	public void updateLaborInsurance(LaborInsurance labor) {
		 super.update(labor);
	}
    /**
     * 删除人口劳保信息
     */
	@Override
	public void deleteLaborInsurance(String ids) {
		String []entityids = ids.split(",");
		for(int i=0;i<entityids.length;i++){
			String sql = "delete from c_laborinsurance where id="+entityids[i];
			createSqlMethod(sql).executeUpdate();
		}
	}

}
