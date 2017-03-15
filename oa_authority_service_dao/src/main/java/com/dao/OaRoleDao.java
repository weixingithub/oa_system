package com.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.OaOrg;
import org.oa_bean.OaRole;
import org.oa_bean.Page;
import org.oa_bean.RoleModel;


/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月12日 上午11:19:10 
 * @version 1.0 
 */
public interface OaRoleDao extends BaseDao {
	public Page<OaRole> getConditionOaRolePage(Page<OaRole> page, String wheresql, 
			List<Object> params, LinkedHashMap<String, String> orderby);

	public RoleModel findOneRoleModel(String roleId, String modelId);
	
	public OaRole findRoleById(Integer id);
	/**
	 * 查找是该角色的一级子节点
	 * @param oaRole
	 * @return
	 */
	public List<OaRole> findRoleByNode(String parentIds);
	/**
	 * 查出所属该角色的所有子节点
	 */
	public String findRoleNodeChild(String roleId);
	/**
	 * 根据集合ID查找出角色集合
	 */
	public List<OaRole> findRoleAllById(String ids,int userId);
	/**
	 * 查找父节点信息
	 * @param id
	 * @return
	 */
	public OaRole findRoleByParent(String parentId);
	/**
	 * 更新isParent的状态
	 * @param parentId
	 * @param isParent
	 */
	public void updateByIsParent(String parentId, String isParent);
	/**
	 * 更新角色的父节点及父节点名称
	 * @param ids
	 * @param parentId
	 */
	public void updateByParentId(String ids, String parentId);
	/**
	 * 更新角色的信息
	 * @param oaRole
	 */
	public void updateRole(OaRole oaRole);
	
	/**
	 * 根据id集合查找所有的子节点
	 * @param ids
	 * @param count
	 * @return
	 */
	public String findMoreRoleNodeChild(String ids,Integer count);
	/**
	 * 查询创建者的所有角色
	 * @return
	 */
	public List<OaRole> getAllRole();
    /**
     * 获取审核角色
     * @param orgId
     * @return
     */
	public String findCheckRoleDao(String orgId,int modelId);
    /**
     * 根据角色获取相应用户
     * @param roleids
     * @return
     */
	public String findUserIdsByRoleIdDao(String roleids);
}
