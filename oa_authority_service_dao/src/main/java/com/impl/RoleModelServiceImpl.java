package com.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oa_bean.OaModel;
import org.oa_bean.RoleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.OaModelService;
import com.dao.OaRoleService;
import com.dao.RoleModelDao;
import com.dao.RoleModelService;
import com.tool.util.OACommon;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月12日 上午11:43:52 
 * @version 1.0 
 */
@Service(value="roleModelService")
public class RoleModelServiceImpl implements RoleModelService {
    private RoleModelDao roleModelDao;
    
    private OaRoleService oaRoleService;
    
	public OaRoleService getOaRoleService() {
		return oaRoleService;
	}
	@Autowired
	public void setOaRoleService(OaRoleService oaRoleService) {
		this.oaRoleService = oaRoleService;
	}
	public RoleModelDao getRoleModelDao() {
		return roleModelDao;
	}
	@Autowired
	public void setRoleModelDao(RoleModelDao roleModelDao) {
		this.roleModelDao = roleModelDao;
	}
	@Override
	public List<RoleModel> findRModelList(List<Integer> roleIds) {
		// TODO Auto-generated method stub
		return roleModelDao.findRModelList(roleIds);
	}
	@Override
	@Transactional
	public boolean updateRoleModelService(RoleModel rm,String mparentId) {
		// TODO Auto-generated method stub
	    boolean flag = false;
	    try{
	    	roleModelDao.update(rm);
	    	if(!(rm.getIsCheckRole() == 1 && (rm.getDataflag() == null || "".equals(rm.getDataflag())))){
	    		if(!OACommon.MODEL_PARENT.equals(mparentId) && mparentId != null){//添加上级菜单
				    RoleModel prm =  oaRoleService.findOneRoleModel(rm.getRole().getRoleId().toString(), mparentId);
				    if(!"true".equals(prm.getIsParent())){
				    	OaModel poa = new OaModel();
				    	poa.setModelId(Integer.parseInt(mparentId));
				    	prm.setModel(poa);
				    	prm.setRole(rm.getRole());
				    	prm.setIsParent("true");
				    	roleModelDao.update(prm);
				    }
				   }
	    	}
	    	flag  = true;
	    }catch(Exception e){
	    	flag = false;
	    }
		return flag;
	}
	@Override
	public List<Integer> findModelsIdByRole(Integer roleId) {
		// TODO Auto-generated method stub
		return roleModelDao.findModelIdByRole(roleId);
	}
	@Override
	@Transactional
	public boolean cancelModelService(RoleModel rm) {
		// TODO Auto-generated method stub
		boolean falg = false;
		try{
			roleModelDao.delete(RoleModel.class, rm.getId());
			falg = true;
		}catch(Exception e){
			falg = false;
		}
		return falg;
	}
	@Override
	public List<RoleModel> findRoleModelId(Integer roleId, List<Integer> modeIds) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer("select rm from RoleModel rm where ");
		hql.append(" rm.role.roleId ="+roleId);
		hql.append(" and rm.model.modelId in (");
		if(modeIds != null && modeIds.size()>0){
			for(int i=0;i<modeIds.size();i++){
				hql.append(modeIds.get(i));
				hql.append(",");
			}
			hql = hql.deleteCharAt(hql.length()-1);
			hql.append(")");
		}
		return roleModelDao.findObjectList(hql.toString());
	}
	@Override
	@Transactional
	public void conditionDelService(Integer roleId, Integer modelId) {
		// TODO Auto-generated method stub
		String sql ="delete from model_role where  role_id ="+roleId+" and model_id="+modelId;
		roleModelDao.createSqlMethod(sql).executeUpdate();
	}
	@Override
	@Transactional
	public boolean conditionUpdateService(List<Integer> roleIds,
			Integer modelId, String pflag) {
		boolean flag = false;
		try{
			StringBuffer sql = new StringBuffer("update model_role set is_parent ='"+pflag+"' where role_id in (");
			for(int i=0;i<roleIds.size();i++){
				sql.append(roleIds.get(i));
				sql.append(",");
			}
			sql = sql.deleteCharAt(sql.length()-1);
			sql.append(")");
			sql.append(" and model_id="+modelId);
			roleModelDao.createSqlMethod(sql.toString()).executeUpdate();
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	public List<Integer> findModelsIdByRoles(List<Integer> roleIds) {
		// TODO Auto-generated method stub
		return roleModelDao.findModelsIdByRoles(roleIds);
	}
	@Override
	public Map<Integer, OaModel> findRmMap(List<Integer> roleIds) {
		// TODO Auto-generated method stub
		List<Object> objarray = roleModelDao.findRmMap(roleIds);
		Map<Integer, OaModel> map = new HashMap<Integer,OaModel>();
		OaModel om;
		for(int i=0;i<objarray.size();i++){
			Object obj[] = (Object[]) objarray.get(i);
			om = new OaModel();
			om.setModelId(Integer.parseInt(obj[1].toString()));
			if(obj[2] == null){
				om.setModelUrl("");
			}else{
				om.setModelUrl(obj[2].toString());
			}
			map.put(Integer.parseInt(obj[0].toString()), om);
		}
		return map;
	}
}
