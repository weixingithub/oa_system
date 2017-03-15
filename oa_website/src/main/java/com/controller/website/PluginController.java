package com.controller.website;

import java.util.LinkedHashMap;

import org.oa_bean.Page;
import org.oa_bean.website.Plugin;
import org.oa_common.date.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.website.PluginService;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;
import com.tool.website.WPCommon;
@Controller
@RequestMapping("/oa/plugin")
public class PluginController {
	private PluginService pluginService;
	@Autowired
	public void setPluginService(PluginService pluginService) {
		this.pluginService = pluginService;
	}
	/**
	 * 分页
	 * @param numPerPage
	 * @param pageNum
	 * @param page
	 * @param plugin
	 * @param orderField
	 * @param orderDirection
	 * @return
	 */
	@RequestMapping(value = "/findPluginPage")
	public ModelAndView findPluginPage(Integer numPerPage,Integer pageNum,Page<Plugin> page, Plugin plugin,String orderField,String orderDirection){
		 ModelAndView mav = new ModelAndView("account/portals/plugin/plugin_list");
		 LinkedHashMap<String,String> orderby  = null;
		 if(numPerPage == null || numPerPage ==-1){
			page = new Page<Plugin>(10);
		 }else{
			page.setPageSize(numPerPage); 
		    page.setPageNo(pageNum);
		 }
		 if(orderField != null && !"".equals(orderField) && orderDirection != null && !"".equals(orderDirection)){
		    	orderby = new LinkedHashMap<String,String>();
	 		    orderby.put(orderField, orderDirection);
		 }else{
	    	orderby = new LinkedHashMap<String,String>();
	    	orderField = "createTime";
	    	orderDirection="desc";
	    	orderby.put(orderField, orderDirection);
		 }
		 
		 page = pluginService.findPluginPage(page, plugin, orderby);
		 mav.addObject("pluginTypeMap",WPCommon.pluginTypeMap);
		 mav.addObject("page", page);
		 mav.addObject("plugin", plugin);
		 mav.addObject("orderField", orderField);
		 mav.addObject("orderDirection", orderDirection);
		 return mav;
	}
	/**
	 * 选择分页
	 * @param numPerPage
	 * @param pageNum
	 * @param page
	 * @param plugin
	 * @param orderField
	 * @param orderDirection
	 * @return
	 */
	@RequestMapping(value = "/findPluginPagejbsxBox")
	public ModelAndView findPluginPagejbsxBox(Integer numPerPage,Integer pageNum,Page<Plugin> page, Plugin plugin){
		 ModelAndView mav = new ModelAndView("account/portals/plugin/plugin_radio");
		 if(numPerPage == null || numPerPage ==-1){
			page = new Page<Plugin>(10);
		 }else{
			page.setPageSize(numPerPage); 
		    page.setPageNo(pageNum);
		 }
		 if(numPerPage == null || numPerPage ==-1){
			page = new Page<Plugin>(10);
		 }else{
			page.setPageSize(numPerPage); 
		    page.setPageNo(pageNum);
		 }
		 LinkedHashMap<String,String> orderby  = new LinkedHashMap<String,String>();
    	 orderby.put("createTime", "desc");
		 page = pluginService.findPluginPage(page, plugin, orderby);
		 
		 mav.addObject("pluginTypeMap",WPCommon.pluginTypeMap);
		 mav.addObject("page", page);
		 mav.addObject("plugin", plugin);
		 return mav;
	}
	/**
	 * 添加或编辑
	 * @return
	 */
	@RequestMapping(value = "/addAndUpdatePlugin")
	@ResponseBody
	public Object addAndUpdatePlugin(Plugin plugin){
		JsonBean  jsonbean = null;
		boolean falg = false;
		String createTime =  DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
		plugin.setCreateTime(createTime);
		if(plugin.getPluginId()==null || plugin.getPluginId()==0){
			 falg = pluginService.addPluginService(plugin);
		}else{
			falg = pluginService.updatePluginService(plugin);
		}
		if(falg){
			jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/plugin/findPluginPage"),"","closeCurrent","","");
		}else{
			jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		}
		 return jsonbean;
	}
	/**
	 * 进入编辑页面
	 * @return
	 */
	@RequestMapping(value = "/intoPluginEdit")
	public ModelAndView intoPluginEdit(Integer id){
		Plugin plugin = new Plugin();
		if(id!=null){
			plugin =  pluginService.findPluginById(id);
		}else{
			plugin = null;
		}
		ModelAndView mav = new ModelAndView("account/portals/plugin/plugin_edit");
		mav.addObject("pluginTypeMap",WPCommon.pluginTypeMap);
		mav.addObject("plugin", plugin);
		return mav;
	}
	/**
	 * 查询信息
	 * @return
	 */
	@RequestMapping(value = "/findPluginById")
	public ModelAndView findPluginById(Integer id){
		Plugin plugin =  pluginService.findPluginById(id);
		ModelAndView mav = new ModelAndView("account/portals/plugin/plugin_details");
		mav.addObject("plugin", plugin);
		mav.addObject("pluginTypeMap",WPCommon.pluginTypeMap);
		return mav;
	}
	/**
	 * 删除信息
	 * @return
	 */
	@RequestMapping(value = "/deletePlugin")
	@ResponseBody
	public Object deletePlugin(Integer id){
		JsonBean  jsonbean = null;
		if(pluginService.deletePluginService(id)){
			jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/plugin/findPluginPage"),"","","","");
		}else{
			jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		}
		 return jsonbean;
	}
}
