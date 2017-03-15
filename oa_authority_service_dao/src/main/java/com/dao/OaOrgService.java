package com.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.OaOrg;
import org.oa_bean.Page;
import org.oa_bean.SysUser;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月11日 上午9:50:29 
 * @version 1.0 
 */
public interface OaOrgService {
	/**
	 * 新增编辑群组
	 * @param oaOrg
	 * @return
	 */
     public boolean saveOrUpdateOaOrgService(OaOrg oaOrg,String nodeIdType,String dataId,String optype,SysUser sysUser,List<Integer>oaOrglist);
     /**
      * 删除组织机构
      * @param ids
     * @param orgNum 
      * @return
      */
     public boolean delOaOrgService(String ids,String parentId, String orgNum);
     /**
      * 分页显示
      * @param page
      * @param oaOrg
      * @return
      */
     public Page<OaOrg> findOaOrgPage(Page<OaOrg> page,OaOrg oaOrg,LinkedHashMap<String,String> orderby);
     /**
      * 通过id查找群组
      * @param id
      * @return
      */
     public OaOrg getFindOaOrg(Integer id);
     /**
      * 通过父节点查询子节点
      * @param parentId
      * @return
      */
     public List<OaOrg> getNodeOaOrg(String parentId);
     
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
	 * 获取节点下的全部子节点
	 * @param nodeId
	 * @return
	 */
	public String findOrgNodeChild(String nodeId);
	/**
	 * 查询所有机构信息
	 * @return
	 */
	public List<OaOrg> getAllOrg();
	/**
	 * 根据权限Id查询modelId
	 * @param roleId
	 * @return
	 */
	public List getModelByRoleId(String roleIds);
	

}
