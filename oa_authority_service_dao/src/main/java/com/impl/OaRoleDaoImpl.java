package com.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.OaRole;
import org.oa_bean.Page;
import org.oa_bean.RoleModel;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.OaRoleDao;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月12日 上午11:20:02 
 * @version 1.0 
 */
@Repository(value="oaRoleDao")
public class OaRoleDaoImpl extends BaseDaoImpl implements OaRoleDao {

	@Override
	public Page<OaRole> getConditionOaRolePage(Page<OaRole> page,
			String wheresql, List<Object> params,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return getScrollData(page, OaRole.class, wheresql, params, orderby);
	}

	@Override
	public RoleModel findOneRoleModel(String roleId, String modelId) {
		// TODO Auto-generated method stub
		RoleModel rm = new RoleModel();
		String hql = "select rm from RoleModel rm where rm.model.modelId ="
		+modelId+" and rm.role.roleId ="+roleId;
		List<RoleModel> list = findObjectList(hql);
		if(list != null && list.size()>0){
			rm = list.get(0);
		}
		return rm;
	}
	@Override
	public List<OaRole> findRoleByNode(String parentIds) {
		String hql = " select r from OaRole r where r.parentId in ("+parentIds+")";
		return findObjectList(hql);
	}

	@Override
	public String findRoleNodeChild(String roleId) {
		String sql = " SELECT  findRoleChildren ("+roleId+")";
		return (String)createSqlMethod(sql).getResultList().get(0);
	}

	@Override
	public List<OaRole> findRoleAllById(String ids,int userId) {
		String hql = "";
		if(userId != 0){
			hql = "select r from OaRole r where r.roleId in ("+ids+") or r.userId='"+userId+"'";
		}else{
			hql = "select r from OaRole r where r.roleId in ("+ids+")";
		}
		return findObjectList(hql);
	}

	@Override
	public OaRole findRoleByParent(String parentId) {
		String hql = " select r from OaRole r where r.nodeId = "+ parentId;
		List list = findObjectList(hql);
		if(list != null && !list.isEmpty()){
			 return (OaRole) findObjectList(hql).get(0);
		}else{
			return null;
		}
	}

	@Override
	public OaRole findRoleById(Integer id) {
		return find(OaRole.class, id);
	}
	
	@Override
	public void  updateByIsParent(String parentId, String isParent) {
			String sql = "update oa_role set is_parent='"+isParent+ "' where node_id='"+parentId+"'";
			createSqlMethod(sql).executeUpdate();
	}

	@Override
	public void updateByParentId(String ids, String parentId) {
		String sql = "update oa_role set parent_id='"+parentId+ "' where role_id in("+ids+")";
		createSqlMethod(sql).executeUpdate();
	}

	@Override
	public void updateRole(OaRole oaRole) {
		StringBuffer sql = new StringBuffer("update oa_role set ");
		sql.append(" node_id = '"+oaRole.getNodeId()+"'");
		sql.append(",parent_id= '"+oaRole.getParentId()+"'");
		sql.append(",role_name= '"+oaRole.getRoleName()+"'");
		sql.append(",is_parent= '"+oaRole.getIsParent()+"'");
		sql.append(",remark= '"+oaRole.getRemark()+"'");
		sql.append(",create_time= '"+oaRole.getCreateTime()+"'");
		sql.append(",org_id= '"+oaRole.getOrgId()+"'");
		sql.append("where role_id= '"+oaRole.getRoleId()+"'");
		createSqlMethod(sql.toString()).executeUpdate();
	}

	@Override
	public String findMoreRoleNodeChild(String ids, Integer count) {
		String sql = " SELECT  findMoreRoleChildren ('"+ids+"',"+count+")";
		return (String)createSqlMethod(sql).getResultList().get(0);
	}
	 /**
     * 创建者的所有角色
     */
	@Override
	public List<OaRole> getAllRole() {
		String hql="from OaRole";
		return findObjectList(hql);
	}

	@Override
	public String findCheckRoleDao(String orgId,int modelId) {
		// TODO Auto-generated method stub
		String sql = "SELECT GROUP_CONCAT(role_id) FROM model_role  WHERE role_id IN (SELECT role_id FROM oa_role WHERE org_id IN("+orgId+")) "
				+ "AND is_check_role =1"
				+ " AND model_id="+modelId;
		return (String) createSqlMethod(sql).getSingleResult();
	}

	@Override
	public String findUserIdsByRoleIdDao(String roleids) {
		// TODO Auto-generated method stub
		String sql = "SELECT GROUP_CONCAT(a.sys_id) FROM ( SELECT DISTINCT sys_id FROM role_sysuser WHERE role_id IN ("+roleids+")) a";
		return (String) createSqlMethod(sql).getSingleResult();
	}
}
