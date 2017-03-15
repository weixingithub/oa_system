package com.impl.website;

import java.util.List;

import org.oa_bean.website.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.website.ModuleDao;
import com.dao.website.ModuleService;

@Service(value="moduleService")
public class ModuleServiceImpl implements  ModuleService{
	private ModuleDao moduleDao;
	
	@Autowired
	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}

	@Override
	@Transactional
	public boolean addModuleService(Module module) {
		boolean flag = false;
		try {
			moduleDao.addModule(module);
			flag =true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateModuleService(Module module) {
		boolean flag = false;
		try {
			moduleDao.updateModule(module);
			flag =true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteModuleService(Integer moduleId) {
		boolean flag = false;
		try {
			moduleDao.deleteModule(moduleId);
			flag =true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@Override
	public Module findModuleById(Integer moduleId) {
		return moduleDao.findModuleById(moduleId);
	}

	@Override
	public List<Module> findModuleByMenuId(Integer menuId) {
		return moduleDao.findModuleByMenuId(menuId);
	}

	@Override
	public Module findModuleByNid(Integer menuId, Integer modelNid) {
		return moduleDao.findModuleByNid(menuId, modelNid);
	}
	@Override
	public Module findModuleByPid(Integer menuId, Integer modelPid) {
		return moduleDao.findModuleByPid(menuId, modelPid);
	}
	@Override
	@Transactional
	public boolean updateModuleByLayoutService(Integer moduleId,
			Integer layoutId) {
		boolean flag = false;
		try {
			moduleDao.updateModuleByLayout(moduleId, layoutId);
			flag =true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}


	 
}
