package com.impl.website;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.website.Themes;
import org.springframework.stereotype.Repository;

import com.dao.website.ThemesDao;
import com.impl.BaseDaoImpl;
@Repository(value="themesDao")
public class ThemesDaoImpl extends BaseDaoImpl implements ThemesDao {

	@Override
	public void addThemes(Themes themes) {
		save(themes);
	}

	@Override
	public void updateThemes(Themes themes) {
		update(themes);
	}

	@Override
	public void deleteThemes(Integer id) {
		delete(Themes.class, id);
	}

	@Override
	public Themes findThemesById(Integer id) {
		return find(Themes.class, id);
	}

	@Override
	public Page<Themes> findThemesPage(Page<Themes> page, String wheresql,
			List<Object> queryParams, LinkedHashMap<String, String> orderby) {
		return getScrollData(page, Themes.class, wheresql, queryParams, orderby);
	}

}
