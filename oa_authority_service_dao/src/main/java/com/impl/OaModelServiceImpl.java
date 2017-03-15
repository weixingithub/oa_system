package com.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.OaModel;
import org.oa_bean.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.OaModelDao;
import com.dao.OaModelService;
import com.dao.RoleModelDao;
import com.tool.util.OACommon;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月12日 下午4:07:57 
 * @version 1.0 
 */
@Service(value="oaModelService")
public class OaModelServiceImpl implements OaModelService {
    private OaModelDao oaModelDao;
    
    private RoleModelDao roleModelDao;

	public RoleModelDao getRoleModelDao() {
		return roleModelDao;
	}
	@Autowired
	public void setRoleModelDao(RoleModelDao roleModelDao) {
		this.roleModelDao = roleModelDao;
	}
	public OaModelDao getOaModelDao() {
		return oaModelDao;
	}
    @Autowired
	public void setOaModelDao(OaModelDao oaModelDao) {
		this.oaModelDao = oaModelDao;
	}
	@Override
	public List<OaModel> findModelById(HashSet<String> modelIds) {
		// TODO Auto-generated method stub
		return oaModelDao.findModelById(modelIds);
	}
	@Override
	public List<OaModel> findAllModel() {
		// TODO Auto-generated method stub
		return oaModelDao.findAllModel();
	}
	@Override
	public Page<OaModel> findOaModelPage(Page<OaModel> page, OaModel oaModel,
			LinkedHashMap<String, String> orderby,List<Integer> modelIds,int userId) {
		// TODO Auto-generated method stub
		StringBuffer wheresql = new StringBuffer("1=1");
		List<Object> params = new ArrayList<Object>();
		if(oaModel.getModelName() != null && !"".equals(oaModel.getModelName())){
			wheresql.append(" and modelName like ? ");
			params.add("%"+oaModel.getModelName()+"%");
		}
		if(modelIds != null){
			wheresql.append(" and ( modelId in (");
			for(int i=0;i<modelIds.size();i++){
				wheresql.append("?");
				wheresql.append(",");
				params.add(modelIds.get(i));
			}
			wheresql = wheresql.deleteCharAt(wheresql.length()-1);
			wheresql.append(")");
			wheresql.append(" or userId="+userId+")");
		}
		return oaModelDao.getConditionOaModelPage(page, wheresql.toString(), params, orderby);
	}
	@Override
	public List<Integer> findChildOaModel(String parentId) {
		// TODO Auto-generated method stub
		String sql = "select model_id from  oa_model  where parent_id ='"+parentId+"'";
		return oaModelDao.createSqlMethod(sql).getResultList();
	}
	@Override
	public OaModel findModelById(String modelId) {
		// TODO Auto-generated method stub
		return oaModelDao.find(OaModel.class, Integer.parseInt(modelId));
	}
	@Override
	@Transactional
	public boolean addOrUpdateOaModelService(OaModel oaModel,String optype,String nodeIdType,
			String dataId) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try{
			if("add".equals(optype)){
				oaModelDao.save(oaModel);
				oaModel.setNodeId(oaModel.getModelId().toString());
				if(oaModel.getIsAuto()==1 && "true".equals(oaModel.getpAuto())){
					oaModel.setModelUrl(OACommon.PWELFAREBASEURL+oaModel.getModelId());
					oaModel.setStageUrl(OACommon.PWELFARESTAGEURL);
				}else if(oaModel.getIsAuto() !=1 && "true".equals(oaModel.getpAuto())){
					oaModel.setpAuto("false");
				}
				if("child".equals(nodeIdType) || "brother".equals(nodeIdType)){
						oaModel.setParentId(dataId);
						if("child".equals(nodeIdType)){
							oaModelDao.updateModelIsParent(dataId, "true");
						}
				}else{
					oaModel.setParentId(OACommon.MODEL_PARENT);
				}
			}else{
				oaModelDao.updateModel(oaModel);
			}
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	@Transactional
	public boolean delModelService(String id,String nodeId,String parentId,
			String modelname,String misparent,int length,String pAuto) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try{
			oaModelDao.delete(OaModel.class, Integer.parseInt(id));
			if("true".equals(misparent)){
				oaModelDao.updateChangeParent(nodeId,parentId);
			}else{
				if(length<=3){
					oaModelDao.updateModelIsParent(parentId, "false");
				}
			}
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	public List<OaModel> findOaModleByParentId(String parentId) {
		// TODO Auto-generated method stub
		String hql = "select om from OaModel om where om.parentId ='"+parentId+"'";
		return oaModelDao.findObjectList(hql);
	}
	@Override
	public String findModelChlidren(String modelId) {
		// TODO Auto-generated method stub
		return oaModelDao.findModelChildren(modelId);
	}
	@Override
	public OaModel getOaModel(OaModel oaparam) {
		// TODO Auto-generated method stub
		return oaModelDao.getOaModelDao(oaparam);
	}
}
