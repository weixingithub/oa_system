package com.impl.population;

import org.oa_bean.population.PopulationCivil;
import org.springframework.stereotype.Repository;

import com.dao.population.PopulationCivilDao;
import com.impl.BaseDaoImpl;

/**
 * 人口民政信息
 * @author Administrator
 * @date 2016年7月5日
 * @company
 * PopulationCivilDaoImp.java
 */
@Repository(value="populationcivilDao")
public class PopulationCivilDaoImp extends BaseDaoImpl implements PopulationCivilDao{
    /**
     * 编辑人口民政信息
     */
	@Override
	public void updatePopulationCivil(PopulationCivil pc) {
		     update(pc);
	}
    /**
     * 删除人口民政信息
     */
	@Override
	public void deletePopulationCivil(String ids) {
		String []entityids = ids.split(",");
		for(int i=0;i<entityids.length;i++){
			String sql = "delete from c_populationcivil where id="+entityids[i];
			createSqlMethod(sql).executeUpdate();
		}
	}
	

}
