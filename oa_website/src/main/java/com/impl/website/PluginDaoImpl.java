package com.impl.website;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.website.Plugin;
import org.springframework.stereotype.Repository;

import com.dao.website.PluginDao;
import com.impl.BaseDaoImpl;
@Repository(value="pluginDao")
public class PluginDaoImpl extends BaseDaoImpl implements  PluginDao{

	@Override
	public void addPlugin(Plugin plugin) {
		save(plugin);
	}

	@Override
	public void updatePlugin(Plugin plugin) {
		update(plugin);
	}

	@Override
	public void deletePlugin(Integer id) {
		delete(Plugin.class, id);
	}

	@Override
	public Plugin findPluginById(Integer id) {
		return find(Plugin.class, id);
	}

	@Override
	public Page<Plugin> findPluginPage(Page<Plugin> page, String wheresql,
			List<Object> queryParams, LinkedHashMap<String, String> orderby) {
		return getScrollData(page, Plugin.class, wheresql, queryParams, orderby);
	}

}
