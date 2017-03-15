package com.dao.website;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.website.ColumnObj;

public interface ColumnDao {
	 
	/**
	 * 添加栏目使用sql 
	 * @param column
	 * @param modelId
	 */
	public void addColumn (ColumnObj column,Integer modelId);
	/**
	 * 更新使用sql
	 * @param column
	 * @param modelId
	 */
	public void updateColumn (ColumnObj column,Integer modelId);
	/**
	 * 删除页面所绑定的栏目会被删除
	 */
	public void deleteColumnByLayout(Integer layoutId);
	/**
	 * 删除插件所绑定的栏目会被删除
	 */
	public void deleteColumnByModel(Integer modelId);
	/**
	 * 删除模块所绑定的栏目会被删除
	 */
	public void deleteColumnByPlugin(Integer pluginId);
	/**
	 * 查询单元模块
	 */
	public ColumnObj findColumnById(Integer id);
	 
	/**
	 * 分页查询
	 * @param page
	 * @param wheresql
	 * @param queryParams
	 * @param orderby
	 * @return
	 */
	public Page<ColumnObj> findColumnPage(Page<ColumnObj> page,String wheresql, List<Object> queryParams,LinkedHashMap<String, String> orderby);
	
}
