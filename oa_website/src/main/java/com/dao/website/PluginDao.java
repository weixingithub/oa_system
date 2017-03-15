package com.dao.website;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.website.Plugin;

public interface PluginDao {
	/**
	 * 添加插件
	 * @param plugin
	 */
	public void addPlugin(Plugin plugin);
	/**
	 * 编辑插件
	 * @param plugin
	 */
	public void updatePlugin(Plugin plugin);
	/**
	 * 删除插件
	 * @param plugin
	 */
	public void deletePlugin(Integer id);
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
	public Page<Plugin> findPluginPage(Page<Plugin> page,String wheresql, List<Object> queryParams,LinkedHashMap<String, String> orderby);

}
