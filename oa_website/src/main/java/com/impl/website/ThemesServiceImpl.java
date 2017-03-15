package com.impl.website;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.website.Themes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.website.ThemesDao;
import com.dao.website.ThemesService;
@Service(value="themesService")
public class ThemesServiceImpl  implements ThemesService {
	private ThemesDao themesDao;
	
	@Autowired
	public void setThemesDao(ThemesDao themesDao) {
		this.themesDao = themesDao;
	}

	@Override
	@Transactional
	public boolean addThemesService(Themes themes) {
		boolean flag = false;
		try {
			themesDao.addThemes(themes);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateThemesService(Themes themes) {
		boolean flag = false;
		try {
			themesDao.updateThemes(themes);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteThemesService(Integer id) {
		boolean flag = false;
		try {
			themesDao.deleteThemes(id);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@Override
	public Themes findThemesById(Integer id) {
		return themesDao.findThemesById(id);
	}

	@Override
	public Page<Themes> findThemesPage(Page<Themes> page, Themes themes,
			LinkedHashMap<String, String> orderby) {
		StringBuilder wheresql = new StringBuilder("1=1");
		List<Object> params = new ArrayList<Object>();
		return themesDao.findThemesPage(page, wheresql.toString(), params, orderby);
	}

}
