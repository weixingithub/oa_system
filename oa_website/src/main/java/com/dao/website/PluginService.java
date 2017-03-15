package com.dao.website;

import java.util.LinkedHashMap;

import org.oa_bean.Page;
import org.oa_bean.website.Plugin;

public interface PluginService {
	/**
	 * 添加插件
	 * @param plugin
	 */
	public boolean addPluginService(Plugin plugin);
	/**
	 * 编辑插件
	 * @param plugin
	 */
	public boolean updatePluginService(Plugin plugin);
	/**
	 * 删除插件
	 * @param plugin
	 */
	public boolean deletePluginService(Integer id);
	/**
	 * 查询插件
	 * @param plugin
	 * @return
	 */
	public Plugin findPluginById(Integer id);
	/**
	 * 分页查询
	 * @param page
	 * @param wheresql
	 * @param queryParams
	 * @param orderby
	 * @return
	 */
	public Page<Plugin> findPluginPage(Page<Plugin> page,Plugin plugin,LinkedHashMap<String, String> orderby);

}
