package com.impl;

import java.util.ArrayList;
import java.util.List;

import org.oa_bean.RoleModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.RoleModelDao;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月12日 上午11:44:28 
 * @version 1.0 
 */
@Repository(value="roleModelDao")
public class RoleModelDaoImpl extends BaseDaoImpl implements RoleModelDao {

	@Override
	public List<RoleModel> findRModelList(List<Integer> roleIds) {
		StringBuffer hql = new StringBuffer("select rm from RoleModel rm where rm.role.roleId in (");
		for(int i=0;i<roleIds.size();i++){
			hql.append(roleIds.get(i));
			hql.append(",");
		}
		hql = hql.deleteCharAt(hql.length()-1);
		hql.append(")");
		hql.append(" and (data_flag IS NOT NULL OR is_check_role !=1)");
		return findObjectList(hql.toString());
	}

	@Override
	public List<Integer> findModelIdByRole(Integer roleId) {
		List<Integer> list = new ArrayList<Integer>();
		String sql = "select model_id from model_role where role_id ="+roleId;
		list = createSqlMethod(sql).getResultList();
		return list;
	}
	@Override
	public void conditionDelRoleModel(String modelId) {
		// TODO Auto-generated method stub
		String sql ="delete from model_role where model_id ="+modelId;
		createSqlMethod(sql).executeUpdate();
	}

	@Override
	public List<Integer> findModelsIdByRoles(List<Integer> roleIds) {
		// TODO Auto-generated method stub
		StringBuffer sql =new StringBuffer("select distinct model_id from model_role where role_id in (");
		for(int i=0;i<roleIds.size();i++){
			sql.append(roleIds.get(i));
			sql.append(",");
		}
		sql = sql.deleteCharAt(sql.length()-1);
		sql.append(")");
		List<Integer> list = new ArrayList<Integer>();
		list = createSqlMethod(sql.toString()).getResultList();
		return list;
	}

	@Override
	public List<Object> findRmMap(List<Integer> roleIds) {
		// TODO Auto-generated method stub
		StringBuffer sql =new StringBuffer("SELECT r.id,m.model_id,m.model_url FROM model_role r,oa_model m  WHERE r.role_id IN (");
		for(int i=0;i<roleIds.size();i++){
			sql.append(roleIds.get(i)+",");
		}
		sql = sql.deleteCharAt(sql.length()-1);
		sql.append(") AND r.model_id = m.model_id");
		return createSqlMethod(sql.toString()).getResultList();
	}
}