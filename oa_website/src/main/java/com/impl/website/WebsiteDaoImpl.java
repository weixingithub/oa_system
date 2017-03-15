package com.impl.website;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.website.Website;
import org.springframework.stereotype.Repository;

import com.dao.website.WebsiteDao;
import com.impl.BaseDaoImpl;

@Repository(value="websiteDao")
public class WebsiteDaoImpl extends BaseDaoImpl implements  WebsiteDao {
	/**
	 * 添加网站信息
	 */
	@Override
	public void addWebsite(Website website) {
		save(website);
	}
	/**
	 * 编辑网站信息
	 */
	@Override
	public void updateWebsite(Website website) {
		update(website);
	}
	/**
	 * SQL编辑网站信息
	 */
	@Override
	public void updateSqlWebsite(Website website) {
		StringBuilder sql = new StringBuilder();
		sql.append("update w_website set ");
		sql.append("name = '"+website.getName()+"',");
		sql.append("logo_url = '"+website.getLogoUrl()+"',");
		sql.append("synopsis = '"+website.getSynopsis()+"',");
		sql.append("address = '"+website.getAddress()+"',");
		sql.append("w_domain = '"+website.getwDomain()+"',");
		sql.append("org_id = '"+website.getOrgId()+"',");
		sql.append("type = '"+website.getType()+"',");
		sql.append("create_time = '"+website.getCreateTime()+"',");
		sql.append("where id = "+website.getId());
		createSqlMethod(sql.toString()).executeUpdate();
	}
	/**
	 * 删除网站信息
	 */
	@Override
	public void deleteWebsite(Integer id) {
		delete(Website.class, id);
	}
	/**
	 * 查询网站信息
	 */
	@Override
	public Website findWebsiteById(Integer id) {
		return find(Website.class, id);
	}
	/**
	 * 分页查询
	 */
	@Override
	public Page<Website> findWebsitePage(Page<Website> page, String wheresql,
			List<Object> queryParams, LinkedHashMap<String, String> orderby) {
		return getScrollData(page, Website.class, wheresql, queryParams, orderby);
	}
	@Override
	public void updateWebsiteByThemes(Integer id, Integer themeId) {
		String sql ="";
		if(themeId==null){
			sql = "update w_website set theme_id=null where id="+id;
		}else{
			sql = "update w_website set theme_id="+themeId+" where id="+id;
		}
		createSqlMethod(sql).executeUpdate();
	}
	@Override
	public void updateWebsiteByMenu(Integer id, Integer menuId) {
		String sql ="";
		if(menuId==null){
			sql = "update w_website set menu_id=null where id="+id;
		}else{
			sql = "update w_website set menu_id="+menuId+" where id="+id;
		}
		createSqlMethod(sql).executeUpdate();
	}
	@Override
	public Website findWebsiteBywDomain(String wDomain) {
		String hql= "select w from Website w where w.wDomain ='"+wDomain+"'";
		return (Website) findObject(hql);
	}
}
