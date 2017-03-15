package com.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.OaOrg;
import org.oa_bean.Page;
import org.oa_bean.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.OaOrgDao;
import com.dao.OaOrgService;
import com.tool.util.OACommon;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月11日 上午9:50:58 
 * @version 1.0 
 */
@Service(value="oaOrgService")
public class OaOrgServiceImpl implements OaOrgService {
    private OaOrgDao oaOrgDao;
    
	public OaOrgDao getOaOrgDao() {
		return oaOrgDao;
	}
	@Autowired
	public void setOaOrgDao(OaOrgDao oaOrgDao) {
		this.oaOrgDao = oaOrgDao;
	}
	
	@Override
	@Transactional
	public boolean saveOrUpdateOaOrgService(OaOrg oaOrg,String nodeIdType,String dataId,
			String optype,SysUser sysUser,List<Integer>oaOrglist) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try{
			if("update".equals(optype)){
				oaOrgDao.updateOrg(oaOrg);
			}else{
				List<SysUser> sysUsers = null;
				if(sysUser.getId() != OACommon.CREATORID){
					if(("add".equals(optype) && "brother".equals(nodeIdType) && 
							oaOrglist.contains(Integer.parseInt(dataId.split(";")[1]))) || 
							nodeIdType == null){
						sysUsers = new ArrayList<SysUser>();
						sysUsers.add(sysUser);
						oaOrg.setSysUser(sysUsers);
					}
				}
				oaOrgDao.save(oaOrg);
				oaOrg.setNodeId(oaOrg.getOrgId().toString());
				oaOrg.setIsParent("false");
				if("brother".equals(nodeIdType) || "child".equals(nodeIdType)){
		    		 if("child".equals(nodeIdType)){
		    			 oaOrg.setParentId(dataId);
		    			 oaOrgDao.updateOrgIsParent(dataId,"true");
		    		 }else{
		    			 oaOrg.setParentId(dataId.split(";")[0]);
		    		 }
		    	 }else{
		    		  oaOrg.setParentId(OACommon.ORGTOP_PARENT);
		    	 }
			}
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	@Transactional
	public boolean delOaOrgService(String ids,String parentId,String orgName) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try{
			String childids = oaOrgDao.findOrgNodeChild(ids);
			String []entityids = childids.split(",");
			Integer arrayIds[] = new Integer[entityids.length-1];
			for(int i=0;i<entityids.length-1;i++){
				arrayIds[i] = Integer.parseInt(entityids[i+1]);
			}
			oaOrgDao.delete(OaOrg.class, arrayIds);
			if(!oaOrgDao.isOrNotParent(parentId)){
				oaOrgDao.updateOrgIsParent(parentId,"false");
			}
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	public Page<OaOrg> findOaOrgPage(Page<OaOrg> page, OaOrg oaOrg,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		StringBuffer wheresql = new StringBuffer("1=1");
		List<Object> params = new ArrayList<Object>();
		if(oaOrg.getOrgName() != null && !"".equals(oaOrg.getOrgName())){
			wheresql.append(" and orgName like ? ");
			params.add(oaOrg.getOrgName());
		}
		return oaOrgDao.getConditionOaOrgPage(page, wheresql.toString(), params, orderby);
	}
	@Override
	public OaOrg getFindOaOrg(Integer id) {
		// TODO Auto-generated method stub
		return oaOrgDao.findOaOrg(id);
	}
	@Override
	public List<OaOrg> getNodeOaOrg(String parentId) {
		// TODO Auto-generated method stub
		return oaOrgDao.getNodeOaOrg(parentId);
	}
	@Override
	public List<OaOrg> findOrgAllById(String ids) {
		// TODO Auto-generated method stub
		return oaOrgDao.findOrgAllById(ids);
	}
	@Override
	public String findMoreOrgNodeChild(String ids, Integer count) {
		// TODO Auto-generated method stub
		return oaOrgDao.findMoreOrgNodeChild(ids, count);
	}
	@Override
	public String findOrgNodeChild(String nodeId) {
		// TODO Auto-generated method stub
		return oaOrgDao.findOrgNodeChild(nodeId);
	}
	/**
	 * 查询创建者的所有机构
	 * @return
	 */
	public List<OaOrg> getAllOrg() {
		return oaOrgDao.getAllOrg();
	}
	/**
	 * 根据权限Id查询modelId
	 * @param roleId
	 * @return
	 */
	@Override
	public List getModelByRoleId(String roleIds) {
		return oaOrgDao.getModelByRoleIdDao(roleIds);
	}
}
