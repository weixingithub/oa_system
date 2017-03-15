package com.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.oa_bean.OaOrg;
import org.oa_bean.OaRole;
import org.oa_bean.Page;
import org.oa_bean.SysUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.SysUserDao;
import com.tool.util.OACommon;
import com.tool.util.PasswordHash;
/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月7日 上午10:14:11 
 * @version 1.0 
 */
@Repository(value="sysUserDao")
public class SysUserDaoImpl extends BaseDaoImpl implements SysUserDao {
	@Override
	public Page<SysUser> getConditionSysUserPage(Page<SysUser> page,
			String wheresql, List<Object> params,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return getSqlScrollData(page, SysUser.class, wheresql,"oa_sysuser",orderby);
	}

	@Override
	public SysUser findSysUser(Integer id) {
		// TODO Auto-generated method stub
		return find(SysUser.class,id);
	}

	@Override
	public Map<String,Object> valSysUser(SysUser sysUser) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<SysUser> ulist = new ArrayList<SysUser>();
		try{
			String hql = "select u from SysUser u where u.userName = '"+sysUser.getUserName()+"'";
			ulist = findObjectList(hql);
			if(ulist != null && ulist.size()>0){
				SysUser userinfo = ulist.get(0);
				if(!PasswordHash.validatePassword(sysUser.getUserPwd(),userinfo.getUserPwd())){
					map.put("result",OACommon.LOGIN_ERROR) ;
					
				}else{
					map.put("result","true");
					map.put("userInfo", userinfo);
				}
			}else{
				map.put("result",OACommon.NOBODY);
			}
		}catch(Exception e){
			map.put("result",OACommon.SYS_ERROR);
		}
		return map;
	}

	@Override
	public List<Integer> findUserByOrgId(String orgId) {
		// TODO Auto-generated method stub
		String sql = "select id from oa_sysuser where FIND_IN_SET('"+orgId+"',user_org_id)";
		Query query = createSqlMethod(sql);
		return query.getResultList();
	}

	@Override
	public List<SysUser> valSysUserCondition(String hql) {
		return findObjectList(hql);
	}

	//查询该角色下的所有角色信息
	@Override
	public List<OaRole> findSysUserRole(String ids) {
		String hql = "select r from OaRole r where r.roleId in ("+ids+")";
		return findObjectList(hql);
	}
	@Override
	//该机构下的所有机构信息
	public List<OaOrg> findSysUserOrg(String ids) {
		String hql = "select r from OaOrg r where r.orgId in ("+ids+")";
		return findObjectList(hql);
	}
	/**
	 * 配置用户的角色和机构信息
	 */
	@Override
	public void configureUser(SysUser user,List<OaRole> listrole, List<OaOrg> listorg) {
		user.setOaRole(listrole);
		user.setOaOrg(listorg);
		update(user);
	}
  

	@Override
	public void sysUserConditionUpdate(SysUser sysUser) {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer("update oa_sysuser set ");
		sql.append(" node_id = '"+sysUser.getNodeId()+"'");
		sql.append(",real_name= '"+sysUser.getRealName()+"'");
		sql.append(",parent_id= '"+sysUser.getParetId()+"'");
		sql.append(",is_parent= '"+sysUser.getIsParent()+"'");
		sql.append(",user_pwd= '"+sysUser.getUserPwd()+"'");
		sql.append(",user_sex= "+sysUser.getUserSex());
		sql.append(",user_age= "+sysUser.getUserAge());
		sql.append(",user_phone= '"+sysUser.getUserPhone()+"'");
		sql.append(",user_email= '"+sysUser.getUserEmail()+"'");
		sql.append(" where id="+sysUser.getId());
		Query query =  createSqlMethod(sql.toString());
		query.executeUpdate();
	}
   
  
	@Override
	public List<SysUser> findSysUserAllById(String ids) {
		String hql = "select u from SysUser u where u.id in("+ids+") ";
		return findObjectList(hql);
	}
    /**
     * 手机端获取同事接口
     */
	@Override
	@Transactional
	public List<SysUser> getLinkMan(Integer orgId,Integer loginUserId ) {
		String sql="select * from oa_sysuser where FIND_IN_SET('"+orgId+"',user_org_id) and id!="+loginUserId;
		return createSqlMethod(sql).getResultList();
	}
    /**
     * 将相同token置空
     */
	@Override
	@Transactional
	public void updateToken(Integer user_id, String token) {
		String sql ="";
		if(user_id==0){
			sql ="UPDATE oa_sysuser  SET device_token='' WHERE id  IN (SELECT * FROM (SELECT id AS id FROM oa_sysuser WHERE device_token='"+token+"') AS o)";
		}else{
			sql ="UPDATE oa_sysuser  SET device_token='' WHERE id  IN (SELECT * FROM (SELECT id AS id FROM oa_sysuser WHERE device_token='"+token+"' AND id!="+user_id+") AS o)";
		}
		 createSqlMethod(sql).executeUpdate();
	}

	@Override
	public void updateUserOrgids(SysUser sysUser) {
		// TODO Auto-generated method stub
		String sql = "update oa_sysuser set user_org_id ='"+sysUser.getOrgIds()+"' where id="+sysUser.getId();
		createSqlMethod(sql).executeUpdate();
	}

  }

