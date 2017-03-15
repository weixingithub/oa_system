package com.dao;

import java.util.List;

import org.oa_bean.RoleModel;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月12日 上午11:43:02 
 * @version 1.0 
 */
public interface RoleModelDao extends BaseDao {
	 public List<RoleModel> findRModelList(List<Integer> roleIds);
	 
	 public List<Integer>  findModelIdByRole(Integer roleId);
	 
	 public void conditionDelRoleModel(String modelId);

	public List<Integer> findModelsIdByRoles(List<Integer> roleIds);

	public List<Object> findRmMap(List<Integer> roleIds);
	
	 
}
