package com.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.OaOrg;
import org.oa_bean.Page;
import org.oa_bean.SysUser;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月11日 上午9:51:21 
 * @version 1.0 
 */
public interface OaOrgDao extends BaseDao{
	/**
	 * 组织机构分页
	 * @param page
	 * @param wheresql
	 * @param params
	 * @param orderby
	 * @return
	 */
	public Page<OaOrg> getConditionOaOrgPage(Page<OaOrg> page, String wheresql,List<Object> params, LinkedHashMap<String, String> orderby);
	/**
	 * 通过ID查找机构信息
	 * @param id
	 * @return
	 */
	public OaOrg findOaOrg(Integer id);
	/**
     * 通过父节点查询子节点
     * @param parentId
     * @return
     */
	public List<OaOrg> getNodeOaOrg(String parentId);
	/**
	 * 更新组织机构
	 * @param oaOrg
	 */
	public void updateOrg(OaOrg oaOrg);
	/**
	 * 更新组织机构父节点标识
	 * @param id
	 * @return
	 */
	public void updateOrgIsParent(String id,String flag);
	/**
	 * 获取节点下的全部子节点
	 * @param nodeId
	 * @return
	 */
	public String findOrgNodeChild(String nodeId);
	/**
	 * 判断是否为父节点
	 * @param nodeId
	 * @return
	 */
	public boolean isOrNotParent(String nodeId);
	/**
	 * 通过id集合查找集合信息
	 * @param ids
	 * @return
	 */
	public List<OaOrg> findOrgAllById(String ids);
	/**
	 * 根据id集合查找所有的孩子
	 * @param ids
	 * @param count
	 * @return
	 */
	public String findMoreOrgNodeChild(String ids,Integer count);
	/**
	 * 查询创建者的所有机构
	 * @return
	 */
	public List<OaOrg> getAllOrg();
	
	public List getModelByRoleIdDao(String roleIds);
	
}
