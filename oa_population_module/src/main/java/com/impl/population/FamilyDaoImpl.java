package com.impl.population;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.population.Family;
import org.springframework.stereotype.Repository;

import com.dao.population.FamilyDao;
import com.impl.BaseDaoImpl;

@Repository(value="familyDao")
public class FamilyDaoImpl extends BaseDaoImpl implements FamilyDao{

	@Override
	public Page<Family> getConditionFamilyPage(Page<Family> page,
			String wheresql, List<Object> params,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return getScrollData(page, Family.class, wheresql, params, orderby);
	}
	@Override
	public Family findFamilyById(String id) {
		// TODO Auto-generated method stub
		return find(Family.class, Integer.parseInt(id));
	}
	@Override
	public List<Family> findFamilyByHouseholderID(String householderID,String orgId) {
		String hql ="select f from Family f where  f.orgId in("+orgId+") and f.householderID='"+householderID+"'";
		return findObjectList(hql);
	}
	@Override
	public void deleteFamily(String ids) {
		// TODO Auto-generated method stub
		String []entityids = ids.split(",");
		for(int i=0;i<entityids.length;i++){
			String sql = "delete from c_family where id="+entityids[i];
			createSqlMethod(sql).executeUpdate();
		}
	}
	@Override
	public void updateByFamilyHouseholder(Family family) {
		String sql = "update c_family set house_holder_name='"+family.getHouseholderName()+"',house_holder_contact='"+family.getHouseholderContact()+"' where id="+family.getId();
		createSqlMethod(sql).executeUpdate();
	}
	@Override
	public List<Family> findFamilyByIds(String ids) {
		String hql = "select f from Family f where f.id in("+ids+")";
		return findObjectList(hql);
	}
}
