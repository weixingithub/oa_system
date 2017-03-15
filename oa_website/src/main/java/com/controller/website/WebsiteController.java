package com.controller.website;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.oa_bean.Page;
import org.oa_bean.SysUser;
import org.oa_bean.website.ColumnObj;
import org.oa_bean.website.Layout;
import org.oa_bean.website.Menu;
import org.oa_bean.website.Module;
import org.oa_bean.website.Themes;
import org.oa_bean.website.Website;
import org.oa_common.date.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.website.ColumnService;
import com.dao.website.LayoutService;
import com.dao.website.MenuService;
import com.dao.website.ModuleService;
import com.dao.website.PluginService;
import com.dao.website.ThemesService;
import com.dao.website.WebsiteService;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;
/**
 * 前台网站配置模块
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/oa/website")
public class WebsiteController {
	private WebsiteService websiteService;
	private MenuService menuService;
	private ThemesService themesService;
	private PluginService pluginService;
	private LayoutService layoutService;
	private ModuleService moduleService;
	private ColumnService columnService;
	@Autowired
	public void setColumnService(ColumnService columnService) {
		this.columnService = columnService;
	}
	@Autowired
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	@Autowired
	public void setLayoutService(LayoutService layoutService) {
		this.layoutService = layoutService;
	}
	@Autowired
	public void setThemesService(ThemesService themesService) {
		this.themesService = themesService;
	}
	@Autowired
	public void setPluginService(PluginService pluginService) {
		this.pluginService = pluginService;
	}
	@Autowired
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	@Autowired
	public void setWebsiteService(WebsiteService websiteService) {
		this.websiteService = websiteService;
	}
	/**
	 * 分页查询
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value = "/getPageWebsite")
	public ModelAndView getPageWebsite(HttpServletRequest request,Integer numPerPage
    		,Integer pageNum,Page<Website> page,Website website,String orderField,String orderDirection,String startTime,String endTime){
		ModelAndView mav =  new ModelAndView("account/portals/website/website_list");
		LinkedHashMap<String,String> orderby  = null;
		SysUser sysUser = (SysUser)request.getSession().getAttribute(OACommon.LOGIN_USER);
		if(numPerPage == null || numPerPage ==-1){
     		page = new Page(10);
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
		if(sysUser.getNodeId().equals(OACommon.SYSCREATOR)){
			 page = websiteService.findWebsitePage(page, website, orderby, startTime, endTime);
		}else{
			website.setOrgId(sysUser.getOrgIds());
			 page = websiteService.findWebsitePage(page, website, orderby, startTime, endTime);
		}
		 page = websiteService.findWebsitePage(page, website, orderby, startTime, endTime);
		 mav.addObject("page", page);
		 mav.addObject("orderField",orderField);
		 mav.addObject("orderDirection",orderDirection);
		 mav.addObject("startTime",startTime);
		 mav.addObject("endTime",endTime);
		 mav.addObject("website", website);
		return mav;
	}
	/**
	 * 添加或编辑
	 * @return
	 */
	@RequestMapping(value = "/addAndUpdateWebsite")
	@ResponseBody
	public Object addAndUpdateWebsite(Website website){
		JsonBean  jsonbean = null;
		boolean falg = false;
		String createTime =  DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
		website.setCreateTime(createTime);
		if(website.getId()==null){
			falg = websiteService.addWebsiteService(website);
		}else{
			falg = websiteService.updateWebsiteService(website);
		}
		if(falg){
			jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/website/getPageWebsite"),"","closeCurrent","","");
		}else{
			 jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		}
		 return jsonbean;
	}
	/**
	 * 删除网站信息
	 * @return
	 */
	@RequestMapping(value = "/deleteWebsite")
	@ResponseBody
	public Object deleteWebsite(Integer id){
		JsonBean  jsonbean = null;
		if(websiteService.deleteWebsiteService(id)){
			jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/website/getPageWebsite"),"","","","");
		}else{
			 jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		}
		 return jsonbean;
	}
	/**
	 * 进入配置页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/configWebsite")
	public ModelAndView configWebsite(Integer id){
		ModelAndView mav = new ModelAndView("account/portals/website/website_config");
		Website website = websiteService.findWebsiteById(id);
		mav.addObject("website", website);
		return mav;
	}
	 
	/**
	 * 编辑绑定主题
	 * @return
	 */
	@RequestMapping(value = "/updateWebsiteByThemes")
	@ResponseBody
	public Object updateWebsiteByThemes(Integer id,Integer themeId){
		JsonBean  jsonbean = null;
		if(websiteService.updateWebsiteByThemesService(id, themeId)){
			jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","layoutPage","","","");
		}else{
			 jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		}
		 return jsonbean;
	}
	/**
	 * 编辑绑定菜单
	 * @return
	 */
	@RequestMapping(value = "/updateWebsiteByMenu")
	@ResponseBody
	public Object updateWebsiteByMenu(Integer id,Integer menuId){
		JsonBean  jsonbean = null;
		if(websiteService.updateWebsiteByMenuService(id, menuId)){
			jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","layoutPage","","","");
		}else{
			 jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		}
		 return jsonbean;
	}
	 
	/**
	 * 进入菜单编辑页面
	 * @return
	 */
	@RequestMapping(value = "/configWebsiteMenu")
	public ModelAndView configWebsiteMenu(Integer websiteId){
		ModelAndView mav = new ModelAndView("account/portals/website/website_menu");
		Website website = websiteService.findWebsiteById(websiteId);
		Menu menu = menuService.findMenuById(website.getMenuId());
		mav.addObject("menu", menu);
		return mav;
	}
	/**
	 * 进入编辑页面
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value = "/intoWebsiteEdit")
	public ModelAndView intoWebsiteEdit(Integer id){
		ModelAndView mav = new ModelAndView("account/portals/website/website_edit");
		Website website =null;
		if(id==null){
			website = new Website();
		}else{
			website = websiteService.findWebsiteById(id);
		}
		mav.addObject("website", website);
		return mav;
	}
	
	/**
	 * 首页页面
	 * @return
	 */
	@RequestMapping(value = "/findWebsiteById")
	@ResponseBody
	public Object findWebsiteById(HttpServletRequest request,Integer id,Integer layoutlevel){
		Map<String,Object> map = new HashMap<String, Object>();
//		ModelAndView mav = new ModelAndView("account/portals/website/website_details");
		//网站基本信息
		Website website = websiteService.findWebsiteById(id);
		//当前主题信息
		Themes themes = themesService.findThemesById(website.getThemeId());
		//网站导航信息
		List<Module> listModule= moduleService.findModuleByMenuId(website.getMenuId());
		Layout layout = new Layout();
		List<ColumnObj> listColumnObjs = new ArrayList<ColumnObj>(); 
		for (Module module : listModule) {
			 if(module.getModelNid()==1){
				 layout =  layoutService.findLayoutById(module.getLayoutId());
				 listColumnObjs = module.getListColumn();
			 }
		}
		map.put("listColumn", listColumnObjs);
		map.put("layout", layout);
		map.put("website", website);
		map.put("themes", themes);
		return map;
	}
	
}
