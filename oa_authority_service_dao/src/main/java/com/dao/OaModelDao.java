package com.dao;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.OaModel;
import org.oa_bean.OaOrg;
import org.oa_bean.Page;

import com.dao.BaseDao;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月12日 下午4:06:57 
 * @version 1.0 
 */
public interface OaModelDao extends BaseDao {
	public List<OaModel> findModelById(HashSet<String> modelIds);

	public List<OaModel> findAllModel();
	
	public  Page<OaModel> getConditionOaModelPage(Page<OaModel> page, String wheresql,List<Object> params, LinkedHashMap<String, String> orderby);
	
	/**
	 * 更新模块父节点标识
	 * @param id
	 * @return
	 */
	public void updateModelIsParent(String id,String flag);
	
    /**
     * 改变模块父节点
     * @param id
     * @param parentId
     */
	public void updateChangeParent(String id, String parentId);
    /**
     * 更新模块
     * @param oaModel
     */
	public void updateModel(OaModel oaModel);
	/**
	 * 获取所有子模块
	 * @param modelId
	 * @return
	 */
	public String findModelChildren(String modelId);
    /**
     * 获取相应OaModel
     * @param oaparam
     * @return
     */
	public OaModel getOaModelDao(OaModel oaparam);
}
