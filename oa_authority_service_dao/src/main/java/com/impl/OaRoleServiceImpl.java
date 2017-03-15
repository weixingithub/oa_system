package com.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.OaRole;
import org.oa_bean.Page;
import org.oa_bean.RoleModel;
import org.oa_common.date.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.OaRoleDao;
import com.dao.OaRoleService;
import com.tool.util.OACommon;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月12日 上午11:18:20 
 * @version 1.0 
 */
@Service(value="oaRoleService")
public class OaRoleServiceImpl implements OaRoleService {
    private OaRoleDao oaRoleDao;

	public OaRoleDao getOaRoleDao() {
		return oaRoleDao;
	}
    @Autowired
	public void setOaRoleDao(OaRoleDao oaRoleDao) {
		this.oaRoleDao = oaRoleDao;
	}
    
    
	@Override
	public Page<OaRole> findOaRolePage(Page<OaRole> page, OaRole oaRole,
			LinkedHashMap<String, String> orderby,String starttime,String endtime,List<Integer>roleIds,int userId) {
		   // TODO Auto-generated method stub
		    StringBuffer wheresql = new StringBuffer("1=1");
		    List<Object> params = new ArrayList<Object>();
		    if(oaRole.getRoleName() != null && !"".equals(oaRole.getRoleName())){
		    	wheresql.append(" and o.roleName like ? ");
		    	params.add("%"+oaRole.getRoleName()+"%");
		    }
		    if(starttime != null && !"".equals(starttime)){
		    	wheresql.append(" and o.createTime >= ?");
		    	params.add(starttime+" 00:00:00");
		    }
		    if(endtime != null && !"".equals(endtime)){
		    	wheresql.append(" and o.createTime <= ? ");
		    	params.add(endtime +" 59:59:59");
		    }
		    if(roleIds != null){
		    	wheresql.append(" and ( o.roleId in (");
				for(int i=0;i<roleIds.size();i++){
					wheresql.append("?");
					wheresql.append(",");
					params.add(roleIds.get(i));
				}
				wheresql = wheresql.deleteCharAt(wheresql.length()-1);
				wheresql.append(")");
				wheresql.append(" or userId="+userId+")");
		    }
		return oaRoleDao.getConditionOaRolePage(page, wheresql.toString(), params, orderby);
	}
	@Override
	public RoleModel findOneRoleModel(String roleId, String modelId) {
		return oaRoleDao.findOneRoleModel(roleId, modelId);
	}
	@Override
	public List<OaRole> findOaRoleList() {
		String hql = "select role from OaRole role";
		return oaRoleDao.findObjectList(hql);
	}
	@Override
	@Transactional
	public boolean saveOrUpdateRoleService(OaRole oaRole,String oldParentId,String operationType) {
		boolean flag = false;
		try{
			if("add".equals(operationType)){  //添加操作
				String createTime =  DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
				if(oaRole.getParentId()!= null && !oaRole.getParentId().equals("")){ //
					oaRoleDao.updateByIsParent(oaRole.getParentId(), "true");
				}else{
					oaRole.setParentId(OACommon.ROLE_PARENT);
				}
				oaRole.setCreateTime(createTime);
				oaRole.setIsParent("false");
				oaRoleDao.save(oaRole);
				oaRole.setNodeId(oaRole.getRoleId().toString());
				flag = true;
			}else{
				if(!oldParentId.equals(oaRole.getParentId())){
					if("".equals(oaRole.getParentId()) || oaRole.getParentId()==null){
						oaRole.setParentId(OACommon.ROLE_PARENT);
					}
					String parentIds = oaRole.getParentId();
					if(oldParentId!= null && !"".equals(oldParentId)){
						parentIds += ","+oldParentId;
					}
					List<OaRole> oldlist = new ArrayList<OaRole>();//该角色之前父节点的子孩子集合
					List<OaRole> newlist = new ArrayList<OaRole>();//该角色新的父节点的子孩子集合
					List<OaRole> list = oaRoleDao.findRoleByNode(parentIds);
					
					for(OaRole role :list){
						if(role.getParentId().equals(oaRole.getParentId())){ 
							newlist.add(role);
						}
						if(role.getParentId().equals(oldParentId)){
							oldlist.add(role);
						}
					}
					if(oldlist.size()==1){
						oaRoleDao.updateByIsParent(oldParentId, "false");
					}
					if(newlist.size()==0){
						oaRoleDao.updateByIsParent(oaRole.getParentId(), "true");
					}
				}
				oaRoleDao.updateRole(oaRole);
				flag = true;
			}
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	@Transactional
	public boolean delRoleService(String id,String nodeId,String parentId,String name) {
		boolean flag = false;
		try{
			String parentIds = nodeId+","+parentId;
			List<OaRole> nodelist = new ArrayList<OaRole>(); //该角色的子节点集合
			List<OaRole> parentlist =new ArrayList<OaRole>();//该角色的同级节点集合;
			List<OaRole> list = oaRoleDao.findRoleByNode(parentIds); //同级节点集合和子节点
			for(OaRole role :list){
				if(role.getParentId().equals(parentId)){ //
					parentlist.add(role);
				}
				if(role.getParentId().equals(nodeId)){
					nodelist.add(role);
				}
			}
			if(parentId.equals(OACommon.ROLE_PARENT)){
				if(nodelist.size()>0){
					StringBuffer roleIds = new StringBuffer();
					 for(int i = 0;i<nodelist.size();i++){
						 roleIds.append(list.get(i).getRoleId());
						 roleIds.append(",");
					 }
					 roleIds =  roleIds.deleteCharAt(roleIds.length()-1);
					 oaRoleDao.updateByParentId(roleIds.toString(), OACommon.ROLE_PARENT);
				}
			}else{
				if(nodelist.size()>0){
					StringBuffer roleIds = new StringBuffer();
					 for(int i = 0;i<nodelist.size();i++){
						 roleIds.append(list.get(i).getRoleId());
						 roleIds.append(",");
					 }
					 roleIds =  roleIds.deleteCharAt(roleIds.length()-1);
					oaRoleDao.updateByParentId(roleIds.toString(), parentId);
				}else{
					if(parentlist.size()==1){
						oaRoleDao.updateByIsParent(parentId, "false");
					}
				}
			}
			oaRoleDao.delete(OaRole.class, Integer.parseInt(id));
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	public OaRole findRoleById(Integer id) {
		return oaRoleDao.findRoleById(id);
	}
	@Override
	public List<OaRole> findRoleByNode(String parentId) {
		return oaRoleDao.findRoleByNode(parentId);
	}
	@Override
	public String findRoleNodeChild(String roleId) {
		return oaRoleDao.findRoleNodeChild(roleId);
	}
	@Override
	public List<OaRole> findRoleAllById(String ids,int userId) {
		return oaRoleDao.findRoleAllById(ids, userId);
	}
	@Override
	public OaRole findRoleByParent(String parentId) {
		return oaRoleDao.findRoleByParent(parentId);
	}
	@Override
	public String findMoreRoleNodeChild(String ids, Integer count) {
		// TODO Auto-generated method stub
		return oaRoleDao.findMoreRoleNodeChild(ids, count);
	}
	/**
	 * 查询创建者的所有角色
	 */
	public List<OaRole> getAllRole() {
		return oaRoleDao.getAllRole();
	}
	@Override
	public String findCheckRole(String orgId,int modelId) {
		// TODO Auto-generated method stub
		return oaRoleDao.findCheckRoleDao(orgId,modelId);
	}
	@Override
	public String findUserIdsByRoleId(String roleids) {
		// TODO Auto-generated method stub
		return oaRoleDao.findUserIdsByRoleIdDao(roleids);
	}
	@Override
	@Transactional
	public boolean cancelModelByIds(int roleId, int modelId) {
		// TODO Auto-generated method stub
		boolean flag =false;
		try{
			String hql = "delete  from RoleModel rm where rm.model.modelId ="
					+modelId+" and rm.role.roleId ="+roleId;
			oaRoleDao.createHqlMethod(hql);
			flag =true;
		}catch(Exception e){
			flag =false;
		}
		return flag;
	}
}
