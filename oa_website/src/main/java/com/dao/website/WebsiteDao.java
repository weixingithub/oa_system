package com.dao.website;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.website.Website;

import com.dao.BaseDao;

public interface WebsiteDao extends BaseDao {
	/**
	 * 添加网站信息
	 * @param website
	 */
	public void addWebsite(Website website);
	/**
	 * SQL编辑网站信息
	 * @param website
	 */
	public void updateSqlWebsite(Website website);
	/**
	 * 编辑网站信息
	 * @param website
	 */
	public void updateWebsite(Website website);
	/**
	 * 删除网站信息
	 * @param id
	 */
	public void deleteWebsite(Integer id);
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
	public Page<Website> findWebsitePage(Page<Website> page,String wheresql, List<Object> queryParams,LinkedHashMap<String, String> orderby);
	/**
	 * 编辑绑定的主题
	 * @param id
	 * @param themeId
	 */
	public void updateWebsiteByThemes(Integer id,Integer themeId);
	/**
	 * 编辑绑定的菜单
	 * @param id
	 * @param menuId
	 */
	public void updateWebsiteByMenu(Integer id,Integer menuId);
  
	 
	 
	 
}
