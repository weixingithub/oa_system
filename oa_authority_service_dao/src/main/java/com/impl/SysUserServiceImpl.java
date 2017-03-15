package com.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.oa_bean.OaOrg;
import org.oa_bean.OaRole;
import org.oa_bean.Page;
import org.oa_bean.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.SysUserDao;
import com.dao.SysUserService;
import com.tool.util.OACommon;
/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月7日 上午10:32:17 
 * @version 1.0 
 */
@Service(value="sysUserService")
public class SysUserServiceImpl implements SysUserService {
    private SysUserDao sysUserDao;
    
	public SysUserDao getSysUserDao() {
		return sysUserDao;
	}
	@Autowired
	public void setSysUserDao(SysUserDao sysUserDao) {
		this.sysUserDao = sysUserDao;
	}
	@Override
	@Transactional
	public boolean saveOrUpdateSysUserService(SysUser sysUser,String opertionType) {
		boolean flag = false;
		try{
			if("add".equals(opertionType)){
				sysUserDao.save(sysUser);
				if (!"seller".equals(sysUser.getNodeId())){
					sysUser.setNodeId(sysUser.getId().toString());
				}
			}else{
				sysUserDao.sysUserConditionUpdate(sysUser);
			}
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	@Transactional
	public boolean delSysUserService(String ids,String userName) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try{
			String [] entityids = ids.split(",");
			Integer[] arr = {entityids.length};
			for(int i = 0;i<entityids.length;i++){
			  Integer a = Integer.parseInt(entityids[i]);
			  	arr[i] = a;
			}		
			sysUserDao.delete(SysUser.class, arr);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	public Page<SysUser> findSysUserPage(Page<SysUser> page, SysUser sysUser,
			LinkedHashMap<String, String> orderby, String orgId,String startTime,String endTime) {
		//TODO Auto-generated method stub
		StringBuffer wheresql = new StringBuffer("1=1");
		List<Object> params = new ArrayList<Object>();
		if(sysUser.getUserSex() != null && sysUser.getUserSex() != 0){
			wheresql.append(" and user_sex ="+sysUser.getUserSex());
		}
        if(!OACommon.ORGTOPNODE.equals(orgId)){
        	wheresql.append(" and FIND_IN_SET('"+orgId+"',user_org_id)");
		}
		if(sysUser.getRealName() != null && !"".equals(sysUser.getRealName())){
			wheresql.append(" and real_name like '%"+sysUser.getRealName()+"%'");
		}
		if(sysUser.getUserName() != null && !"".equals(sysUser.getUserName())){
			wheresql.append(" and user_name like '%"+sysUser.getUserName()+"%'");
		}
		if(startTime != null && !"".equals(startTime)){
			wheresql.append(" and user_createtime >= '"+startTime +" 00:00:00"+"'");
		}
		if(endTime != null && !"".equals(endTime)){
			wheresql.append(" and user_createtime <= '"+endTime + " 59:59:59"+"'");
		}
		return  sysUserDao.getConditionSysUserPage(page,wheresql.toString(),params,orderby);
	}
	@Override
	public SysUser getFindSysUser(Integer id) {
		// TODO Auto-generated method stub
		return sysUserDao.findSysUser(id);
	}
	@Override
	public Map<String,Object> valSysUser(SysUser sysUser) {
		// TODO Auto-generated method stub
		return sysUserDao.valSysUser(sysUser);
	}
	@Override
	public List<SysUser> valSysUserCondition(SysUser sysUser) {
		String hql = "select u from SysUser u where 1=1 ";
		if(sysUser.getUserName()!=null && !"".equals(sysUser.getUserName())){ 
			hql +=" and u.userName = '"+sysUser.getUserName()+"'" ;
		}
		if(sysUser.getUserPwd()!=null && !"".equals(sysUser.getUserPwd())){
			hql +=" and u.userPwd = '"+sysUser.getUserPwd() +"'";
		}
		if(sysUser.getRealName()!=null && !"".equals(sysUser.getRealName())){
			hql +=" and u.realName = '"+sysUser.getRealName()+"'" ;
		}
		if(sysUser.getUserPhone()!=null && !"".equals(sysUser.getUserPhone())){
			hql +=" and u.userPhone = '"+sysUser.getUserPhone() +"'";
		}
		if(sysUser.getUserEmail()!=null && !"".equals(sysUser.getUserEmail())){
			hql +=" and u.userEmail = '"+sysUser.getUserEmail()+"'" ;
		}
		return sysUserDao.valSysUserCondition(hql);
	}
	//该角色下的所有角色信息
	public List<OaRole> findSysUserRole(String ids){
		return sysUserDao.findSysUserRole(ids);
	}
	//该机构下的机构信息
	@Override
	public List<OaOrg> findSysUserOrg(String ids) {
		return sysUserDao.findSysUserOrg(ids);
		}
	/**
	 * 配置用户的角色和机构信息
	 * @param listrole
	 * @param listorg
	 */
	@Override
	@Transactional
	public void configureUserService(SysUser user,List<OaRole> listrole, List<OaOrg> listorg) {
		 sysUserDao.configureUser(user,listrole, listorg);
	}
	@Override
	public List<SysUser> findSysUserAllById(String ids) {
		return sysUserDao.findSysUserAllById(ids);
	}
	/**
	 * 手机端获取同事接口
	 */
	@Override
	@Transactional
	public List<SysUser> getLinkMan(Integer orgId,Integer loginUserId) {
		return sysUserDao.getLinkMan(orgId,loginUserId);
	}
	/**
	 * 将相同token置空
	 */
	@Override
	@Transactional
	public boolean updateToken(Integer user_id, String token) {
		boolean result = false;
		try{
			sysUserDao.updateToken(user_id,token);
			result = true;
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 按照店员名字查询店员信息
	 */
	@Override
	@Transactional
	public List<SysUser> findSysUserAllByName(String name) {
		String hql = "from SysUser where realName like '%"+name+"%'";
		return sysUserDao.findObjectList(hql);
	}

	
	
	
}
