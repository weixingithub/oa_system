package com.impl.population;

import org.oa_bean.population.Stability;
import org.springframework.stereotype.Repository;

import com.dao.population.StabilityDao;
import com.impl.BaseDaoImpl;


/**
 * 综治维稳信息
 * @author Administrator
 * @date 2016年7月2日
 * @company
 * StabilityDaoImp.java
 */
@Repository(value="stabilityDao")
public class StabilityDaoImp extends BaseDaoImpl implements StabilityDao{
    /**
     * 删除人口综治维稳信息
     */
	@Override
	public void deleteStability(String ids) {
		String []entityids = ids.split(",");
		for(int i=0;i<entityids.length;i++){
			String sql = "delete from c_stability where id="+entityids[i];
			createSqlMethod(sql).executeUpdate();
		}
	}
   /**
    * 编辑人口综治维稳信息
    */
	@Override
	public void updateStability(Stability stability) {
		update(stability);
	}

}
