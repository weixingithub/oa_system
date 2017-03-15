package com.dao;

import java.util.List;
import java.util.Map;

import org.oa_bean.OaModel;
import org.oa_bean.RoleModel;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月12日 上午11:42:50 
 * @version 1.0 
 */
public interface RoleModelService {
	/**
	 * 获取用户角色权限
	 * @param roleIds
	 * @return
	 */
     public List<RoleModel> findRModelList(List<Integer> roleIds);
     /**
      * 更新相关角色对应的模块儿权限
      * @param rm
      * @return
      */
     public boolean updateRoleModelService(RoleModel rm,String mparentId);
     /**
      * 获取角色拥有的所有模块的id
      * @param roleId
      * @return
      */
     public List<Integer> findModelsIdByRole(Integer roleId);
     /**
      * 取消所选模块
      * @param rm
      * @param mparentId
      * @return
      */
     public boolean cancelModelService(RoleModel rm);
     /**
      * 获取角色相应模块下的角色权限id
      * @param roleId
      * @param modeId
      * @return
      */
     public List<RoleModel>  findRoleModelId(Integer roleId,List<Integer> modeIds);
     /**
      * 条件删除角色权限
      * @param roleId
      * @param modelId
      */
     public void conditionDelService(Integer roleId,Integer modelId);
     /**
      * 条件更新节点
      * @param roleIds
      * @param modelId
      * @return
      */
     public boolean conditionUpdateService(List<Integer> roleIds,Integer modelId,String flag);
      /**
       *  获取多角色下的功能模块并去重
       * @param roleIds
       * @return
       */
	  public List<Integer> findModelsIdByRoles(List<Integer> roleIds);
	  /**
	   * 获取用户所拥有的所有模块
	   * @param roleIds
	   * @return
	   */
	  public Map<Integer,OaModel> findRmMap(List<Integer> roleIds);
}