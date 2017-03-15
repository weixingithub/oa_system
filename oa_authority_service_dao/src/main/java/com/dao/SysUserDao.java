package com.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.oa_bean.OaOrg;
import org.oa_bean.OaRole;
import org.oa_bean.Page;
import org.oa_bean.SysUser;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月7日 上午10:12:12 
 * @version 1.0 
 */
public interface SysUserDao extends BaseDao {
	/**
	 * 条件分页
	 * @param page
	 * @param wheresql
	 * @param params
	 * @param orderby
	 * @return
	 */
	public Page<SysUser> getConditionSysUserPage(Page<SysUser> page,String wheresql,List<Object> params,LinkedHashMap<String, String> orderby);
	/**
	 * ID查询
	 * @param id
	 * @return
	 */
	public SysUser findSysUser(Integer id);
	/**
	 * 登录验证
	 * @param sysUser
	 * @return
	 */
	public Map<String,Object> valSysUser(SysUser sysUser);
	/**
	 * 根据机构获取用户id
	 * @param orgId
	 * @return
	 */
	public List<Integer> findUserByOrgId(String orgId);
	/**
	 * 多条件查询(如果验证唯一，则必须传入条件)
	 * @param sql
	 * @return
	 */
	public List<SysUser> valSysUserCondition(String hql);
	/**
	 *  只更新实体不更新关联
	 * @param sysUser
	 */
	public void sysUserConditionUpdate(SysUser sysUser);
	/**
	 * 查询该角色下所有角色信息
	 * @param ids
	 * @return
	 */
	public List<OaRole> findSysUserRole(String ids);
	/**
	 * 查询该机构下的下所有机构信息
	 * @param ids
	 * @return
	 */
	public List<OaOrg> findSysUserOrg(String ids);
	/**
	 * 配置用户机构信息
	 * @param orgid
	 * @return
	 */
	public void configureUser(SysUser user,List<OaRole> listrole,List<OaOrg> listorg);
	/**
	 * 通过id集合查找用户集合
	 * @param ids
	 * @return
	 */
	public List<SysUser> findSysUserAllById(String ids);
	/**
	 * 手机端获取同事接口
	 * @param orgId
	 * @return
	 */
	public List<SysUser> getLinkMan(Integer orgId,Integer loginUserId);
	/**
	 * 将相同token置空处理
	 * @param user_id
	 * @param token
	 */
	public void updateToken(Integer user_id, String token);
	/**
	 * 更新user orgids字段
	 * @param sysUser
	 */
	public void updateUserOrgids(SysUser sysUser);
}
