package com.dao.website;

import java.util.List;

import org.oa_bean.website.Module;

public interface ModuleService {
	/**
	 * 添加模块
	 * @param Module
	 */
	public boolean addModuleService(Module module);
	/**
	 * 编辑模块
	 * @param Module
	 */
	public boolean updateModuleService(Module module);
	/**
	 * 删除模块
	 * @param ModuleId
	 */
	public boolean deleteModuleService(Integer moduleId);
	/**
	 * 查询模块
	 * @param ModuleId
	 */
	public Module findModuleById(Integer moduleId);
	/**
	 * 查询归属导航的模块
	 * @param ModuleId
	 */
	public List<Module> findModuleByMenuId(Integer menuId);
	/**
	 * 查询归属导航的子模块
	 * @param ModuleId
	 */
	public Module findModuleByNid(Integer menuId,Integer modelNid);
	/**
	 * 查询归属导航的父模块
	 * @param ModuleId
	 */
	public Module findModuleByPid(Integer menuId,Integer modelPid);
	/**
	 * 模块绑定的页面
	 * @param menuId
	 */
	public boolean updateModuleByLayoutService(Integer moduleId,Integer layoutId );
	 
}
