package com.dao.website;

import java.util.LinkedHashMap;

import org.oa_bean.Page;
import org.oa_bean.website.Menu;

public interface MenuService {
	/**
	 * 添加菜单
	 * @param menu
	 */
	public boolean  addMenuService(Menu menu);
	/**
	 * 编辑菜单
	 * @param menu
	 */
	public boolean updateMenuService(Menu menu);
	/**
	 * 编辑菜单树
	 * @param menu
	 */
	public boolean updateMenuZtreeService(Menu menu);
	/**
	 * 删除菜单
	 * @param menuId
	 */
	public boolean deleteMenuService(Integer menuId);
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
	public Page<Menu> findMenuPage(Page<Menu> page,Menu menu,LinkedHashMap<String, String> orderby);
}
