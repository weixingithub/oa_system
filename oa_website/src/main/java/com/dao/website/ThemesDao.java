package com.dao.website;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.website.Themes;

public interface ThemesDao {
	/**
	 * 添加主题
	 * @param Themes
	 */
	public void addThemes(Themes themes);
	/**
	 * 编辑主题
	 * @param Themes
	 */
	public void updateThemes(Themes themes);
	/**
	 * 删除主题
	 * @param Themes
	 */
	public void deleteThemes(Integer id);
	/**
	 * 查询主题
	 * @param Themes
	 * @return
	 */
	public Themes findThemesById(Integer id);
	/**
	 * 分页查询
	 * @param page
	 * @param wheresql
	 * @param queryParams
	 * @param orderby
	 * @return
	 */
	public Page<Themes> findThemesPage(Page<Themes> page,String wheresql, List<Object> queryParams,LinkedHashMap<String, String> orderby);

}
