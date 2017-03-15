package com.dao.website;

import java.util.LinkedHashMap;

import org.oa_bean.Page;
import org.oa_bean.website.Website;

public interface WebsiteService {
	/**
	 * 添加网站信息
	 * @param website
	 */
	public boolean addWebsiteService(Website website);
	/**
	 * 编辑网站信息
	 * @param website
	 */
	public boolean updateWebsiteService(Website website);
	/**
	 * 编辑网站信息
	 * @param website
	 */
	public boolean updateSqlWebsiteService(Website website);
	/**
	 * 删除网站信息
	 * @param id
	 */
	public boolean deleteWebsiteService(Integer id);
	/**
	 * 通过id查询网站信息
	 * @param id
	 * @return
	 */
	public Website findWebsiteById(Integer id);
	/**
	 * 通过网站域名信息查询
	 * @param wDomain
	 * @return
	 */
	public Website findWebsiteBywDomain(String wDomain);
	/**
	 * 分页查询
	 * @param page
	 * @param wheresql
	 * @param queryParams
	 * @param orderby
	 * @return
	 */
	public Page<Website> findWebsitePage(Page<Website> page,Website website,LinkedHashMap<String, String> orderby,String startTime,String endTime);
	
	/**
	 * 编辑绑定的主题
	 * @param id
	 * @param themeId
	 */
	public boolean updateWebsiteByThemesService(Integer id,Integer themeId);
	/**
	 * 编辑绑定的菜单
	 * @param id
	 * @param menuId
	 */
	public boolean updateWebsiteByMenuService(Integer id,Integer menuId);
}
