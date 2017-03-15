package com.impl;

import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.oa_bean.OaOrg;
import org.oa_bean.Page;
import org.oa_bean.SysUser;
import org.springframework.stereotype.Repository;

import com.dao.OaOrgDao;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月11日 上午9:51:56 
 * @version 1.0 
 */
@Repository(value="oaOrgDao")
public class OaOrgDaoImpl extends BaseDaoImpl implements OaOrgDao {
	@Override
	public Page<OaOrg> getConditionOaOrgPage(Page<OaOrg> page,
			String wheresql, List<Object> params,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return getScrollData(page, OaOrg.class, wheresql, params, orderby);
	}

	@Override
	public OaOrg findOaOrg(Integer id) {
		// TODO Auto-generated method stub
		return find(OaOrg.class,id);
	}

	@Override
	public List<OaOrg> getNodeOaOrg(String parentId) {
		// TODO Auto-generated method stub
		String hql = "select org from OaOrg org where org.parentId = '"+parentId+"'";
		return findObjectList(hql);
	}

	@Override
	public void updateOrgIsParent(String id,String flag) {
		String sql  ="update oa_org set is_parent ='"+flag+"' where org_id="+id;
		Query query =  createSqlMethod(sql);
		query.executeUpdate();
	}

	@Override
	public void updateOrg(OaOrg oaOrg) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("update oa_org set ");
		sql.append(" node_id = '"+oaOrg.getNodeId()+"'");
		sql.append(",org_name= '"+oaOrg.getOrgName()+"'");
		sql.append(",parent_id= '"+oaOrg.getParentId()+"'");
		sql.append(",is_parent= '"+oaOrg.getIsParent()+"'");
		sql.append(",org_num= '"+oaOrg.getOrgNum()+"'");
		sql.append(",org_address= '"+oaOrg.getOrgAddress()+"'");
		sql.append(",org_remark= '"+oaOrg.getOrgRemark()+"'");
		sql.append(",org_p_c_d= '"+oaOrg.getOrgPCD()+"'");
		sql.append(" where org_id="+oaOrg.getOrgId());
		Query query =  createSqlMethod(sql.toString());
		query.executeUpdate();
	}

	@Override
	public String findOrgNodeChild(String nodeId) {
		// TODO Auto-generated method stub
		String sql ="select findOrgChildren('"+nodeId+"')";
		return (String) createSqlMethod(sql).getResultList().get(0);
	}
	
	@Override
	public boolean isOrNotParent(String nodeId) {
		boolean flag = false;
		String hql = "select oo from OaOrg oo where oo.parentId = '"+nodeId+"'";
		List<OaOrg> list = findObjectList(hql);
		if(list != null && list.size()>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public List<OaOrg> findOrgAllById(String ids) {
		String hql = "select oo from OaOrg oo where oo.orgId in("+ids+")";
		return findObjectList(hql);
	}

	@Override
	public String findMoreOrgNodeChild(String ids, Integer count) {
		String sql ="select findMoreOrgChildren('"+ids+"',"+count+")";
		return (String) createSqlMethod(sql).getResultList().get(0);
	}
	 /**
	    * 查询创建者的所有机构
	    */
		@Override
		public List<OaOrg> getAllOrg() {
			String hql=" from OaOrg";
			return findObjectList(hql);
		}
		//该机构下的所有机构信息
		public List<OaOrg> findSysUserOrg(String ids) {
			String hql = "select r from OaOrg r where r.orgId in ("+ids+")";
			return findObjectList(hql);
		}

		@Override
		public List getModelByRoleIdDao(String roleIds) {
			String sql="SELECT  model_id FROM  model_role WHERE role_id='"+roleIds+"'";
			return createSqlMethod(sql).getResultList();
		}
     
}
