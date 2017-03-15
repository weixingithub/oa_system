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
 * @date 创建时间：2016年5月7日 上午10:16:08 
 * @version 1.0 
 */
public interface SysUserService {
	/**
	 * 新增编辑管理员
	 * @param sysUser
	 * @return
	 */
     public boolean saveOrUpdateSysUserService(SysUser sysUser,String opertionType);
     /**
      * 删除管理员
      * @param ids
      * @return
      */
     public boolean delSysUserService(String ids,String userName);
     /**
      * 分页显示全部管理员
      * @param page
      * @param sysUser
      * @return
      */
     public Page<SysUser> findSysUserPage(Page<SysUser> page,SysUser sysUser,LinkedHashMap<String,String> orderby,String orgId,String startTime,String endTime);
     /**
      * 通过id查找管理员
      * @param id
      * @return
      */
     public SysUser getFindSysUser(Integer id);
     /**
      * 验证用户名密码
      * @param sysUser
      * @return
      */
     public Map<String,Object> valSysUser(SysUser sysUser);
	/**
	 * 多条件查询(如果验证唯一，则必须传入条件)
	 * @param sysUser
	 * @return
	 */
	public List<SysUser> valSysUserCondition(SysUser sysUser);
	/**
	 * 查询该角色下的所有角色信息
	 * @param ids
	 * @return
	 */
	public List<OaRole> findSysUserRole(String ids);
	/**
	 * 查询该机构下的所有机构信息
	 * @param ids
	 * @return
	 */
	public List<OaOrg> findSysUserOrg(String ids);
	/**
	 * 配置用户的角色和机构信息
	 * @param user 
	 * @param listrole
	 * @param listorg
	 * @return 
	 */
	public void configureUserService(SysUser user, List<OaRole> listrole, List<OaOrg> listorg);
	/**
	 * 通过id集合查找用户集合
	 * @param ids
	 * @return
	 */
	public List<SysUser> findSysUserAllById(String ids);
	/**
	 * 登录时根据
	 * @param user_name
	 * @return
	 */
	//public SysUser findSysUserByName(String user_name);
	/**
	 * 手机端查询同事接口
	 * @param parseInt
	 * @return
	 */
	public List<SysUser> getLinkMan(Integer  orgId,Integer loginUserId);
	/**
	 * 将相同token置空
	 * @param user_id
	 * @param token
	 */
	public boolean updateToken(Integer user_id, String token);
	/**
	 * 按照店员名称查询店员信息
	 * @param name
	 * @return
	 */
	public List<SysUser> findSysUserAllByName(String name);

}
