package com.impl.website;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.oa_bean.Page;
import org.oa_bean.website.Menu;
import org.oa_bean.website.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.website.MenuDao;
import com.dao.website.MenuService;
import com.dao.website.ModuleDao;
import com.tool.website.WPCommon;
@Service(value="menuService")
public class MenuServiceImpl implements  MenuService{
	private ModuleDao moduleDao;
	private MenuDao menuDao;
	
	@Autowired
	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}
	@Autowired
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	@Transactional
	public boolean addMenuService(Menu menu) {
		boolean flag = false;
		try {
			menuDao.addMenu(menu);
			
			List<Module> listModule = new ArrayList<Module>();
			if(!"".equals(menu.getMenuTree()) && menu.getMenuTree()!=null){
				JSONArray ztreeJson = JSONArray.fromObject(menu.getMenuTree());
				for (int i=0;i<ztreeJson.size();i++) {
					Module module = (Module) JSONObject.toBean((JSONObject) ztreeJson.get(i), Module.class);
					module.setMenuId(menu.getMenuId());
					listModule.add(module);
				}
				for (Module module : listModule) {
					moduleDao.addModule(module);
				}
			}
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateMenuService(Menu menu) {
		boolean flag = false;
		try {
			Map<Integer,Object> oldMap = new HashMap<Integer, Object>();
			Map<Integer,Object> newMap = new HashMap<Integer, Object>();
			List<Module> oldlist = moduleDao.findModuleByMenuId(menu.getMenuId());
			
			for (Module module : oldlist) {
				oldMap.put(module.getModelNid(), module);
			}
			if(!"".equals(menu.getMenuTree()) && menu.getMenuTree()!=null){
				JSONArray newJson = JSONArray.fromObject(menu.getMenuTree());
				for (int i=0;i<newJson.size();i++) {
					Module newModule = (Module) JSONObject.toBean((JSONObject) newJson.get(i), Module.class);
					Module newModule1 = (Module)oldMap.get(newModule.getModelNid());
					if(newModule1==null){
						newModule.setMenuId(menu.getMenuId());
						 if(newModule.getModelNid()==1){
							 newModule.setModelUrl(WPCommon.findWebsiteIndex);
						 }else if(newModule.getIsParent().equals("true")){
							 newModule.setModelUrl(WPCommon.findWebsiteNext+"?modelNid="+newModule.getModelNid());
						 }
						moduleDao.addModule(newModule);
					}else{
						newModule1.setModelName(newModule.getModelName());
						newModule1.setIsParent(newModule.getIsParent());
						newModule1.setModelNid(newModule.getModelNid());
						newModule1.setModelPid(newModule.getModelPid());
						if(newModule1.getModelNid()==1){
							newModule1.setModelUrl(WPCommon.findWebsiteIndex);
						 }else if(newModule1.getIsParent().equals("true")){
							newModule1.setModelUrl(WPCommon.findWebsiteNext+"?modelNid="+newModule1.getModelNid());
						 }
						moduleDao.updateModule(newModule1);
					}
					newMap.put(newModule.getModelNid(), newModule);
				}
			}
			for (Module module : oldlist) {
				Module newModule = (Module)newMap.get(module.getModelNid());
				if(newModule==null){
					moduleDao.deleteModule(module.getModelId());
				} 
			}
			menu.setMenuTree("");
			menuDao.updateMenu(menu);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	@Override
	@Transactional
	public boolean updateMenuZtreeService(Menu menu) {
		boolean flag = false;
		try {
			menuDao.updateMenuZtree(menu);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	@Override
	@Transactional
	public boolean deleteMenuService(Integer menuId) {
		boolean flag = false;
		try {
			menuDao.deleteMenu(menuId);
			moduleDao.deleteModuleByMenuId(menuId);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@Override
	public Menu findMenuById(Integer menuId) {
		return menuDao.findMenuById(menuId);
	}

	@Override
	public Page<Menu> findMenuPage(Page<Menu> page, Menu menu,
			LinkedHashMap<String, String> orderby) {
		StringBuilder wheresql = new StringBuilder("1=1");
		List<Object> params = new ArrayList<Object>();
		return menuDao.findMenuPage(page, wheresql.toString(), params, orderby);
	}

	

}
