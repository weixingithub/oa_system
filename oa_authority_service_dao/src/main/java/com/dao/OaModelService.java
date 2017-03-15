package com.dao;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.OaModel;
import org.oa_bean.Page;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月12日 下午4:06:33 
 * @version 1.0 
 */
public interface OaModelService {
	/**
	 * 获取用户所拥有权限
	 * @param modelIds
	 * @return
	 */
    public List<OaModel> findModelById(HashSet<String> modelIds);
    /**
     * 获取所有权限
     * @return
     */
    public List<OaModel> findAllModel();
    /**
     * 分页获取功能列表
     * @param page
     * @param oaModel
     * @param orderby
     * @return
     */
    public Page<OaModel> findOaModelPage(Page<OaModel> page, OaModel oaModel,
    		LinkedHashMap<String,String> orderby,List<Integer> modelIds,int userId);
    /**
     * 获取功能模块的子节点
     * @param parentId
     * @return
     */
    public List<Integer> findChildOaModel(String parentId);
    /**
     * 通过id获取模块详情
     * @param modelId
     * @return
     */
    public OaModel findModelById(String modelId);
    /**
     * 添加或更新模块
     * @param oaModel
     * @return
     */
    public boolean addOrUpdateOaModelService(OaModel oaModel,String optype,String nodeIdType,String dataId);
    /**
     * 删除模块
     * @param id
     * @param parentId
     * @param modelname 
     * @return
     */
    public boolean delModelService(String id,String nodeId,String parentId, 
    		String modelname,String misparent,int length,String pAuto);
    /**
     * 通过parentId获取模块
     * @param parentId
     * @return
     */
    public List<OaModel> findOaModleByParentId(String parentId);
    /**
     * 获取模块下所有子模块
     * @param modelId
     * @return
     */
    public String findModelChlidren(String modelId);
    /**
     * 获取相应OaModel
     * @param oaparam
     * @return
     */
	public OaModel getOaModel(OaModel oaparam);
}