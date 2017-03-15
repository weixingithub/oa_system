package com.impl.website;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.website.Themes;
import org.oa_bean.website.Website;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.website.ThemesDao;
import com.dao.website.WebsiteDao;
import com.dao.website.WebsiteService;

@Service(value = "websiteService")
public class WebsiteServiceImpl implements WebsiteService {
	private WebsiteDao websiteDao;
	private ThemesDao themesDao;
	
	@Autowired
	public void setThemesDao(ThemesDao themesDao) {
		this.themesDao = themesDao;
	}
	@Autowired
	public void setWebsiteDao(WebsiteDao websiteDao) {
		this.websiteDao = websiteDao;
	}

	/**
	 * 添加网站信息
	 */
	@Override
	@Transactional
	public boolean addWebsiteService(Website website) {
		boolean flag = false;
		try {
			websiteDao.addWebsite(website);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 编辑网站信息
	 */
	@Override
	@Transactional
	public boolean updateWebsiteService(Website website) {
		boolean flag = false;
		try {
			websiteDao.updateWebsite(website);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	/**
	 * SQL编辑网站信息
	 */
	@Override
	@Transactional
	public boolean updateSqlWebsiteService(Website website) {
		boolean flag = false;
		try {
			websiteDao.updateSqlWebsite(website);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	/**
	 * 删除网站信息
	 */
	@Override
	@Transactional
	public boolean deleteWebsiteService(Integer id) {
		boolean flag = false;
		try {
			websiteDao.deleteWebsite(id);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 查询网站信息
	 */
	@Override
	public Website findWebsiteById(Integer id) {
		Website website = websiteDao.findWebsiteById(id);
		if(website.getThemeId()!=null && website.getThemeId()!=0){
			Themes themes =themesDao.findThemesById(website.getThemeId());
			website.setThemes(themes);
		}else{
			website.setThemes(null);
		}
		return website ;
	}

	/**
	 * 分页查询
	 */
	@Override
	public Page<Website> findWebsitePage(Page<Website> page, Website website,
			LinkedHashMap<String, String> orderby, String startTime,
			String endTime) {
		StringBuilder wheresql = new StringBuilder("1=1");
		List<Object> params = new ArrayList<Object>();
		if (!"".equals(website.getOrgId()) && website.getOrgId() != null) {
			String[] arr = website.getOrgId().split(",");
			wheresql.append(" and o.orgId in (");
			for (int i = 0; i < arr.length; i++) {
				wheresql.append(" ? ");
				if (i < arr.length - 1) {
					wheresql.append(",");
				}
				params.add(arr[i].trim());
			}
			wheresql.append(")");
		}
		if (!"".equals(website.getwDomain()) && website.getwDomain() != null) {
			wheresql.append(" and o.wDomain like ? ");
			params.add("%" + website.getwDomain() + "%");
		}
		if (!"".equals(website.getName()) && website.getName() != null) {
			wheresql.append(" and o.name like  ? ");
			params.add("%" + website.getName() + "%");
		}
		if (!"".equals(startTime) && startTime != null) {
			wheresql.append(" and o.createTime > ?");
			params.add(startTime);
		}
		if (!"".equals(endTime) && endTime != null) {
			wheresql.append(" and o.createTime < ?");
			params.add(endTime);
		}
		return websiteDao.findWebsitePage(page, wheresql.toString(), params,
				orderby);
	}

	@Override
	@Transactional
	public boolean updateWebsiteByThemesService(Integer id, Integer themeId) {
		boolean flag = false;
		try {
			websiteDao.updateWebsiteByThemes(id, themeId);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	@Override
	@Transactional
	public boolean updateWebsiteByMenuService(Integer id, Integer menuId) {
		boolean flag = false;
		try {
			websiteDao.updateWebsiteByMenu(id, menuId);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	@Override
	public Website findWebsiteBywDomain(String wDomain) {
		return websiteDao.findWebsiteBywDomain(wDomain);
	}
}
