package com.controller.website;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oa_bean.website.ColumnObj;
import org.oa_bean.website.Layout;
import org.oa_bean.website.Module;
import org.oa_bean.website.Plugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.website.LayoutService;
import com.dao.website.ModuleService;
import com.dao.website.PluginService;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;
@Controller
@RequestMapping("/oa/module")
public class ModuleController {
	
	private ModuleService moduleService;
	private LayoutService layoutService;
	private PluginService pluginService;
	@Autowired
	public void setPluginService(PluginService pluginService) {
		this.pluginService = pluginService;
	}
	@Autowired
	public void setLayoutService(LayoutService layoutService) {
		this.layoutService = layoutService;
	}
	@Autowired
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	/**
	 * 进入模块编辑
	 * @return
	 */
	@RequestMapping(value = "/intoModuelEdit")
	public ModelAndView intoModuelEdit(Integer modelId){
		ModelAndView mav = new ModelAndView("/account/portals/menu/module_edit");
		Module module = moduleService.findModuleById(modelId);
		if(module.getPluginId()!=null){
			Plugin plugin = pluginService.findPluginById(module.getPluginId());
			module.setPlugin(plugin);
		}
		mav.addObject("module", module);
		return mav;
	}
	/**
	 * 进入编辑页面
	 * @return
	 */
	@RequestMapping(value = "/intoModuelEditColumn")
	public ModelAndView intoModuelEditColumn(Integer modelId,Integer layoutId ){
		ModelAndView mav  = null;
		Module module = moduleService.findModuleById(modelId);
		Layout layout = layoutService.findLayoutById(layoutId);
		List<ColumnObj> list =module.getListColumn();
		Map<String,ColumnObj> columnMap=new HashMap<String, ColumnObj>(); 
		for (ColumnObj columnObj : list) {
			Plugin  plugin = pluginService.findPluginById(columnObj.getPluginId());
			columnObj.setPlugin(plugin);
			columnMap.put(columnObj.getColumnDiv(), columnObj);
		}
		mav = new ModelAndView(layout.getLayoutEditUrl());
		mav.addObject("module", module);
		mav.addObject("columnMap", columnMap);
		mav.addObject("layout", layout);
		return mav;
	}
	/**
	 * 编辑绑定页面
	 * @return
	 */
	@RequestMapping(value = "/updateModuleByLayout")
	@ResponseBody
	public Object updateModuleByLayout(Integer modelId,Integer layoutId){
		JsonBean  jsonbean = null;
		if(moduleService.updateModuleByLayoutService(modelId, layoutId)){
			jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","layoutPage","","","");
		}else{
			 jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		}
		 return jsonbean;
	}
	/**
	 * 编辑编辑模块
	 * @return
	 */
	@RequestMapping(value = "/updateModule")
	@ResponseBody
	public Object updateModule(Module module){
		JsonBean  jsonbean = null;
		if(moduleService.updateModuleService(module)){
			jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","","closeCurrent","","");
		}else{
			 jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		}
		 return jsonbean;
	}
}
