package com.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.OaRole;
import org.oa_bean.Page;
import org.oa_bean.RoleModel;


/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月12日 上午11:16:32 
 * @version 1.0 
 */
public interface OaRoleService {
	/**
	 * 分页查询角色列表
	 * @param page
	 * @param oaRole
	 * @param orderby
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public Page<OaRole> findOaRolePage(Page<OaRole> page, OaRole oaRole,
			LinkedHashMap<String, String> orderby,String starttime,String endtime,List<Integer> roleId,int userId);
    /**
     * 获取当前角色针对某个具体模块儿的权限
     * @param roleId
     * @param modelId
     * @return
     */
	public RoleModel findOneRoleModel(String roleId, String modelId);
	/**
	 *  获取所有角色信息
	 * @return
	 */
	public List<OaRole> findOaRoleList();
	/**
	 * 新增编辑角色
	 * @param oaRole
	 * @return
	 */
	public boolean saveOrUpdateRoleService(OaRole oaRole,String oldParentId,String operationType);
	/**
	 * 删除角色
	 * @param id
	 * @param nodeId
	 * @param parentId
	 * @param name 
	 * @param parentName
	 * @return
	 */
	public boolean delRoleService(String id,String nodeId,String parentId, String name);
	/**
	 * 通过id查找角色
	 * @param id
	 * @return
	 */
	public OaRole findRoleById(Integer id);
	/**
	 * 查找是该角色的一级子节点
	 * @param oaRole
	 * @return
	 */
	public List<OaRole> findRoleByNode(String ParentId);
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
	 * 查询创建者的所有角色
	 * @return
	 */
	public List<OaRole> getAllRole();
	/**
	 * 根据id集合查找所有的子节点
	 * @param ids
	 * @param count
	 * @return
	 */
	public String findMoreRoleNodeChild(String ids,Integer count);
	/**
	 * 获取审核角色
	 * @param orgId
	 * @return
	 */
	public String findCheckRole(String orgId,int modelId);
	/**
	 * 根据roleId获取userIds
	 * @param roleids
	 * @return
	 */
	public String findUserIdsByRoleId(String roleids);
	/**
	 * 去除已选定model
	 * @param roleId
	 * @param modelId
	 * @return
	 */
	public boolean cancelModelByIds(int roleId,int modelId);
}
