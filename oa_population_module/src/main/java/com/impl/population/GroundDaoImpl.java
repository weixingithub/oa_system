package com.impl.population;

import org.oa_bean.population.Ground;
import org.springframework.stereotype.Repository;

import com.dao.population.GroundDao;
import com.impl.BaseDaoImpl;
@Repository(value="groundDao")
public class GroundDaoImpl extends BaseDaoImpl implements GroundDao{
	@Override
	public void updateGround(Ground ground) {
		update(ground);
	}

	@Override
	public void deleteGround(String ids) {
		String []entityids = ids.split(",");
		for(int i=0;i<entityids.length;i++){
			String sql = "delete from c_ground where id="+entityids[i];
			createSqlMethod(sql).executeUpdate();
		}
	}

}
