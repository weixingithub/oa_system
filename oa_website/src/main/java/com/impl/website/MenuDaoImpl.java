package com.impl.website;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.website.Menu;
import org.springframework.stereotype.Repository;

import com.dao.website.MenuDao;
import com.impl.BaseDaoImpl;
@Repository(value="menuDao")
public class MenuDaoImpl extends BaseDaoImpl implements MenuDao{

	@Override
	public void addMenu(Menu menu) {
		save(menu);
	}

	@Override
	public void updateMenu(Menu menu) {
		update(menu);
	}

	@Override
	public void deleteMenu(Integer menuId) {
		delete(Menu.class, menuId);
	}

	@Override
	public Menu findMenuById(Integer menuId) {
		return find(Menu.class, menuId);
	}

	@Override
	public Page<Menu> findMenuPage(Page<Menu> page, String wheresql,
			List<Object> queryParams, LinkedHashMap<String, String> orderby) {
		return getScrollData(page, Menu.class, wheresql, queryParams, orderby);
	}

	@Override
	public void updateMenuZtree(Menu menu) {
		String sql = "update w_menu set menu_tree='"+menu.getMenuTree()+"' where menu_id="+menu.getMenuId();
		createSqlMethod(sql).executeUpdate();
	}

}
