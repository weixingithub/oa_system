package com.impl.population;

import org.oa_bean.population.FamilyPlanning;
import org.springframework.stereotype.Repository;

import com.dao.population.FamilyPlanningDao;
import com.impl.BaseDaoImpl;

/**
 * 人口计生信息
 * @author Administrator
 * @date 2016年7月5日
 * @company
 * FamilyPlanningDaoImp.java
 */
@Repository(value="familyplanDao")
public class FamilyPlanningDaoImp extends BaseDaoImpl implements FamilyPlanningDao {
   /**
    * 编辑人口计生信息
    */
	@Override
	public void updateFamilyPlanning(FamilyPlanning familyPlanning) {
		update(familyPlanning);
	}
    /**
     * 删除人口计生信息
     */
	@Override
	public void deleteFamilyPlanning(String ids) {
		String []entityids = ids.split(",");
		for(int i=0;i<entityids.length;i++){
			String sql = "delete from c_familyplanning where id="+entityids[i];
			createSqlMethod(sql).executeUpdate();
		}
	}

}
