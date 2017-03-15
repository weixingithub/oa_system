package com.dao.website;

import java.util.LinkedHashMap;

import org.oa_bean.Page;
import org.oa_bean.website.Themes;

public interface ThemesService {
	/**
	 * 添加主题
	 * @param Themes
	 */
	public boolean addThemesService(Themes themes);
	/**
	 * 编辑主题
	 * @param Themes
	 */
	public boolean updateThemesService(Themes themes);
	/**
	 * 删除主题
	 * @param Themes
	 */
	public boolean deleteThemesService(Integer id);
	/**
	 * 查询主题
	 * @param Themes
	 * @return
	 */
	public Themes findThemesById(Integer id);
	/**
	 * 分页查询
	 * @param page
	 * @param themes
	 * @param orderby
	 * @return
	 */
	public Page<Themes> findThemesPage(Page<Themes> page,Themes themes,LinkedHashMap<String, String> orderby);

}
