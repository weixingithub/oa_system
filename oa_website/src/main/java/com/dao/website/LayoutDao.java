package com.dao.website;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.website.Layout;

import com.dao.BaseDao;

public interface LayoutDao extends BaseDao{
	/**
	 * 添加模板布局
	 * @param website
	 */
	public void addLayout(Layout layout);
	/**
	 * 编辑模板布局
	 * @param website
	 */
	public void updateLayout(Layout layout);
	/**
	 * 删除模板布局
	 * @param id
	 */
	public void deleteLayout(Integer id);
	/**
	 * 通过id查询模板布局
	 * @param id
	 * @return
	 */
	public Layout findLayoutById(Integer id);
	/**
	 * 分页查询
	 * @param page
	 * @param wheresql
	 * @param queryParams
	 * @param orderby
	 * @return
	 */
	public Page<Layout> findLayoutPage(Page<Layout> page,String wheresql, List<Object> queryParams,LinkedHashMap<String, String> orderby);
	/**
	 * 通过layoutids查询模板布局
	 * @param id
	 * @return
	 */
	public List<Layout> findLayoutByLayoutids(String layoutids);
}
