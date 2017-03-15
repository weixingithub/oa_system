package com.dao.website;

import java.util.List;

import org.oa_bean.website.Module;
/**
 * 前台模块
 * @author 
 *
 */
public interface ModuleDao {
	/**
	 * 添加模块
	 * @param Module
	 */
	public void addModule(Module module);
	/**
	 * 编辑模块
	 * @param Module
	 */
	public void updateModule(Module module);
	/**
	 * 删除模块
	 * @param ModuleId
	 */
	public void deleteModule(Integer moduleId);
	/**
	 * 查询模块
	 * @param ModuleId
	 */
	public Module findModuleById(Integer moduleId);
	/**
	 * 删除归属导航的模块
	 * @param ModuleId
	 */
	public void deleteModuleByMenuId(Integer menuId);
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
	public void updateModuleByLayout(Integer moduleId,Integer layoutId );
	
}
