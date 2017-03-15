package com.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Query;

import org.oa_bean.OaModel;
import org.oa_bean.OaOrg;
import org.oa_bean.Page;
import org.springframework.stereotype.Repository;

import com.dao.OaModelDao;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月12日 下午4:08:59 
 * @version 1.0 
 */
@Repository(value="oaModelDao")
public class OaModelDaoImpl extends BaseDaoImpl implements OaModelDao {

	@Override
	public List<OaModel> findModelById(HashSet<String> modelIds) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select om from OaModel om where om.modelId in (");
		Iterator<String> it = modelIds.iterator();
		while(it.hasNext()){
			hql.append(it.next());
			hql.append(",");
		}
		hql = hql.deleteCharAt(hql.length()-1);
		hql.append(")");
		return findObjectList(hql.toString());
	}

	@Override
	public List<OaModel> findAllModel() {
		// TODO Auto-generated method stub
		String hql = "select om from OaModel om";
		return findObjectList(hql);
	}

	@Override
	public Page<OaModel> getConditionOaModelPage(Page<OaModel> page,
			String wheresql, List<Object> params,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return getScrollData(page, OaModel.class, wheresql, params, orderby);
	}

	@Override
	public void updateModelIsParent(String id, String flag) {
		// TODO Auto-generated method stub
		String sql  ="update oa_model set is_parent ='"+flag+"' where model_id="+id;
		Query query =  createSqlMethod(sql);
		query.executeUpdate();
	}

	@Override
	public void updateChangeParent(String nodeId, String parentId) {
		// TODO Auto-generated method stub
		String sql ="update oa_model set parent_id='"+parentId+"' where parent_id='"+nodeId+"'";
		createSqlMethod(sql).executeUpdate();
	}

	@Override
	public void updateModel(OaModel oaModel) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("update oa_model set ");
		sql.append(" node_id = '"+oaModel.getNodeId()+"'");
		sql.append(",parent_id= '"+oaModel.getParentId()+"'");
		sql.append(",model_name= '"+oaModel.getModelName()+"'");
		sql.append(",is_parent= '"+oaModel.getIsParent()+"'");
		sql.append(",remark= '"+oaModel.getRemark()+"'");
		sql.append(",model_url= '"+oaModel.getModelUrl()+"'");
		sql.append(",is_auto= "+oaModel.getIsAuto());
		sql.append(",model_frame= '"+oaModel.getModelIframe()+"'");
		sql.append(",is_check_model= "+oaModel.getIsCheckModel());
		if(oaModel.getStageUrl()!= null && !"".equals(oaModel.getStageUrl())){
			sql.append(",stage_url= '"+oaModel.getStageUrl()+"'");
		}
		if(oaModel.getpAuto() != null &&  !"".equals(oaModel.getpAuto())){
			sql.append(",p_auto= '"+oaModel.getpAuto()+"'");
		}
		if(oaModel.getCheckRoleId() != null && !"".equals(oaModel.getCheckRoleId()) ){
			sql.append(",check_role_id= '"+oaModel.getCheckRoleId()+"'");
		}
		sql.append(" where model_id= '"+oaModel.getModelId()+"'");
		createSqlMethod(sql.toString()).executeUpdate();
	}

	@Override
	public String findModelChildren(String modelId) {
		// TODO Auto-generated method stub
		String sql="select findModelChildren('"+modelId+"')";
		return (String) createSqlMethod(sql).getResultList().get(0);
	}

	@Override
	public OaModel getOaModelDao(OaModel oaparam) {
		// TODO Auto-generated method stub
		StringBuffer hql= new StringBuffer("select oam from OaModel oam where 1=1 ");
		if(oaparam.getModelUrl() != null && !"".equals(oaparam.getModelUrl())){
			hql.append(" and oam.modelUrl='"+oaparam.getModelUrl()+"'");
		}
		return (OaModel) findObject(hql.toString());
	}

}
