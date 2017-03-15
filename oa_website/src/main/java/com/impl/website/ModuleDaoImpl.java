package com.impl.website;

import java.util.List;

import org.oa_bean.website.Module;
import org.springframework.stereotype.Repository;

import com.dao.website.ModuleDao;
import com.impl.BaseDaoImpl;
@Repository(value="moduleDao")
public class ModuleDaoImpl extends BaseDaoImpl implements ModuleDao {

	@Override
	public void addModule(Module module) {
		save(module);
	}

	@Override
	public void updateModule(Module module) {
		update(module);
	}

	@Override
	public void deleteModule(Integer moduleId) {
		delete(Module.class, moduleId);
	}

	@Override
	public Module findModuleById(Integer moduleId) {
		return find(Module.class, moduleId);
	}

	@Override
	public void deleteModuleByMenuId(Integer menuId) {
		String sql = "DELETE FROM w_module WHERE menu_id="+menuId;
		createSqlMethod(sql).executeUpdate();
	}

	@Override
	public List<Module> findModuleByMenuId(Integer menuId) {
		String sql = "select * FROM w_module WHERE menu_id="+menuId;
		return createSqlMethod(sql, Module.class).getResultList();
	}

	@Override
	public Module findModuleByNid(Integer menuId, Integer modelNid) {
		String hql = "select m from Module m where m.menuId = "+menuId +" and m.modelNid="+modelNid;
		return  (Module) findObjectList(hql).get(0);
	}
	@Override
	public Module findModuleByPid(Integer menuId, Integer modelPid) {
		String hql = "select m from Module m where m.menuId = "+menuId +" and m.modelPid="+modelPid;
		return  (Module) findObjectList(hql).get(0);
	}
	@Override
	public void updateModuleByLayout(Integer moduleId, Integer layoutId) {
		String sql = "";
		if(layoutId!=null){
			sql = "update w_module set layout_id="+layoutId+" WHERE model_id="+moduleId;
		}else{
			sql = "update w_module set layout_id = null WHERE model_id="+moduleId;
		}
		createSqlMethod(sql).executeUpdate();
	}

}
