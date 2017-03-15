package com.impl.website;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.website.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.website.PluginDao;
import com.dao.website.PluginService;
@Service(value="pluginService")
public class PluginServiceImpl implements PluginService{
	
	private PluginDao pluginDao;
	@Autowired
	public void setPluginDao(PluginDao pluginDao) {
		this.pluginDao = pluginDao;
	}

	@Override
	@Transactional
	public boolean addPluginService(Plugin plugin) {
		boolean flag = false;
		try {
			pluginDao.addPlugin(plugin);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updatePluginService(Plugin plugin) {
		boolean flag = false;
		try {
			pluginDao.updatePlugin(plugin);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deletePluginService(Integer id) {
		boolean flag = false;
		try {
			pluginDao.deletePlugin(id);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@Override
	public Plugin findPluginById(Integer id) {
		return pluginDao.findPluginById(id);
	}

	@Override
	public Page<Plugin> findPluginPage(Page<Plugin> page, Plugin plugin, LinkedHashMap<String, String> orderby) {
		StringBuilder wheresql = new StringBuilder("1=1");
		List<Object> params = new ArrayList<Object>();
		return pluginDao.findPluginPage(page, wheresql.toString(), params, orderby);
	}

}
