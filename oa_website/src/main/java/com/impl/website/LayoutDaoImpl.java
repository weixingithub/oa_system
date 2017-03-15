package com.impl.website;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.website.Layout;
import org.springframework.stereotype.Repository;

import com.dao.website.LayoutDao;
import com.impl.BaseDaoImpl;
@Repository(value="layoutDao")
public class LayoutDaoImpl extends BaseDaoImpl implements LayoutDao{

	@Override
	public void addLayout(Layout layout) {
		save(layout);
	}

	@Override
	public void updateLayout(Layout layout) {
		update(layout);
	}

	@Override
	public void deleteLayout(Integer id) {
		delete(Layout.class, id);
	}

	@Override
	public Layout findLayoutById(Integer id) {
		return find(Layout.class, id);
	}

	@Override
	public Page<Layout> findLayoutPage(Page<Layout> page, String wheresql,
			List<Object> queryParams, LinkedHashMap<String, String> orderby) {
		return getScrollData(page, Layout.class, wheresql, queryParams, orderby);
	}

	@Override
	public List<Layout> findLayoutByLayoutids(String layoutids) {
		String hql ="select l from Layout l where l.id in("+layoutids+")";
		return findObjectList(hql);
	}
	
	
}
