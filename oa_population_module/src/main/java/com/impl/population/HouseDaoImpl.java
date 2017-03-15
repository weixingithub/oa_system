package com.impl.population;

import org.oa_bean.population.House;
import org.springframework.stereotype.Repository;

import com.dao.population.HouseDao;
import com.impl.BaseDaoImpl;
@Repository(value="houseDao")
public class HouseDaoImpl extends BaseDaoImpl implements HouseDao {

	@Override
	public void updateHouse(House house) {
		update(house);
	}
	@Override
	public void deleteHouse(String ids) {
		String []entityids = ids.split(",");
		for(int i=0;i<entityids.length;i++){
			String sql = "delete from c_house where id="+entityids[i];
			createSqlMethod(sql).executeUpdate();
		}
	}
}
