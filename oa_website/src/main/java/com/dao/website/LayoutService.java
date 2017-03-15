package com.dao.website;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.website.Layout;

public interface LayoutService{
	/**
	 * 添加模板布局
	 * @param website
	 */
	public boolean addLayoutService(Layout layout);
	/**
	 * 编辑模板布局
	 * @param website
	 */
	public boolean updateLayoutService(Layout layout);
	/**
	 * 删除模板布局
	 * @param id
	 */
	public boolean deleteLayoutService(Integer id);
	/**
	 * 通过id查询模板布局
	 * @param id
	 * @return
	 */
	public Layout findLayoutById(Integer id);
	/**
	 * 通过layoutids查询模板布局
	 * @param id
	 * @return
	 */
	public List<Layout> findLayoutByLayoutids(String layoutids);
	/**
	 * 分页查询
	 * @param page
	 * @param wheresql
	 * @param queryParams
	 * @param orderby
	 * @return
	 */
	public Page<Layout> findLayoutPage(Page<Layout> page,Layout layout,LinkedHashMap<String, String> orderby);

}
