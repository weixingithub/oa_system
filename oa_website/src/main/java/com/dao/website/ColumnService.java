package com.dao.website;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.website.ColumnObj;

public interface ColumnService {
	 
 
	/**
	 * 查询单元模块
	 * @return
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
	public Page<ColumnObj> findColumnPage(Page<ColumnObj> page,ColumnObj column,LinkedHashMap<String, String> orderby);
 
	/**
	 * 添加或更新栏目使用sql
	 * @param column
	 * @param websiteId
	 */
	public boolean addAndUpdateColumnObjService(List<ColumnObj> list,Integer modelId);
 
 
 
}
