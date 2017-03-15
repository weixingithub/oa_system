package com.dao.website;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.website.Menu;

/**
 * 网站菜单
 * @author 
 *
 */
public interface MenuDao {
	/**
	 * 添加菜单
	 * @param menu
	 */
	public void addMenu(Menu menu);
	/**
	 * 编辑菜单
	 * @param menu
	 */
	public void updateMenu(Menu menu);
	/**
	 * 编辑菜单树
	 * @param menu
	 */
	public void updateMenuZtree(Menu menu);
	/**
	 * 删除菜单
	 * @param menuId
	 */
	public void deleteMenu(Integer menuId);
	/**
	 * 查询菜单
	 * @param menuId
	 */
	public Menu findMenuById(Integer menuId);
	/**
	 * 分页查询
	 * @param page
	 * @param wheresql
	 * @param queryParams
	 * @param orderby
	 * @return
	 */
	public Page<Menu> findMenuPage(Page<Menu> page,String wheresql, List<Object> queryParams,LinkedHashMap<String, String> orderby);
}
