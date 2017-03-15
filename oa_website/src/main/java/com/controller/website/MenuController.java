package com.controller.website;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.oa_bean.Page;
import org.oa_bean.Ztree;
import org.oa_bean.website.Menu;
import org.oa_bean.website.Module;
import org.oa_bean.website.Website;
import org.oa_common.date.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.website.MenuService;
import com.dao.website.ModuleService;
import com.dao.website.WebsiteService;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;

@Controller
@RequestMapping("/oa/menu")
public class MenuController {
	private MenuService menuService;
	private WebsiteService websiteService;
	private ModuleService moduleService;
	
	@Autowired
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	@Autowired
	public void setWebsiteService(WebsiteService websiteService) {
		this.websiteService = websiteService;
	}
	@Autowired
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}
	/**
	 * 分页
	 * @param numPerPage
	 * @param pageNum
	 * @param page
	 * @param menu
	 * @param orderField
	 * @param orderDirection
	 * @return
	 */
	@RequestMapping(value = "/findMenuPage")
	public ModelAndView findMenuPage(Integer numPerPage,Integer pageNum,Page<Menu> page, Menu menu,String orderField,String orderDirection){
		 ModelAndView mav = new ModelAndView("account/portals/menu/menu_list");
		 LinkedHashMap<String,String> orderby  = null;
		 if(numPerPage == null || numPerPage ==-1){
			page = new Page<Menu>(10);
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
		 page = menuService.findMenuPage(page, menu, orderby);
		 mav.addObject("page", page);
		 mav.addObject("menu", menu);
		 mav.addObject("orderField", orderField);
		 mav.addObject("orderDirection", orderDirection);
		 return mav;
	}
	/**
	 * 选择分页
	 * @param numPerPage
	 * @param pageNum
	 * @param page
	 * @param menu
	 * @param orderField
	 * @param orderDirection
	 * @return
	 */
	@RequestMapping(value = "/findMenuPagejbsxBox")
	public ModelAndView findMenuPagejbsxBox(Integer numPerPage,Integer pageNum,Page<Menu> page, Menu menu,String orderField,String orderDirection,Integer websiteId){
		 ModelAndView mav = new ModelAndView("account/portals/menu/menujbsxBox_list");
		 if(numPerPage == null || numPerPage ==-1){
			page = new Page<Menu>(10);
		 }else{
			page.setPageSize(numPerPage); 
		    page.setPageNo(pageNum);
		 }
		 LinkedHashMap<String,String> orderby  = new LinkedHashMap<String,String>();
		 if(orderField != null && !"".equals(orderField) && orderDirection != null && !"".equals(orderDirection)){
		    	orderby = new LinkedHashMap<String,String>();
	 		    orderby.put(orderField, orderDirection);
		 }else{
	    	orderby = new LinkedHashMap<String,String>();
	    	orderField = "createTime";
	    	orderDirection="desc";
	    	orderby.put(orderField, orderDirection);
		 }
    	 page = menuService.findMenuPage(page, menu, orderby);
    	 Website website = websiteService.findWebsiteById(websiteId);
		 mav.addObject("websiteId", websiteId);
		 mav.addObject("website", website);
		 mav.addObject("menu", menu);
		 mav.addObject("page", page);
		 mav.addObject("orderField", orderField);
		 mav.addObject("orderDirection", orderDirection);
		 return mav;
	}
	/**
	 * 添加或编辑
	 * @return
	 */
	@RequestMapping(value = "/addAndUpdateMenu")
	@ResponseBody
	public Object addAndUpdateMenu(Menu menu){
		JsonBean  jsonbean = null;
		boolean falg = false;
		String createTime =  DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
		menu.setCreateTime(createTime);
		
		if(menu.getMenuId()==null ){
			falg =  menuService.addMenuService(menu);
		}else{
			falg =	menuService.updateMenuService(menu);
		}
		if(falg){
			jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/menu/findMenuPage"),"","closeCurrent","","");
		}else{
			jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		}
		 return jsonbean;
	}
	
	/**
	 * 进入编辑页面
	 * @return
	 */
	@RequestMapping(value = "/intoMenuEdit")
	public ModelAndView intoMenuEdit(Integer id){
		Menu menu = new Menu();
		ModelAndView mav  = new ModelAndView("account/portals/menu/menu_edit");
		List<Ztree> listztree = new ArrayList<Ztree>();
		Set<Integer> setModelNid =new HashSet<Integer>();
		
		Ztree ztreeTop = new Ztree();
		ztreeTop.setId("0");
		ztreeTop.setpId("-1");
		ztreeTop.setName("菜单");
		ztreeTop.setOpen("true");
		ztreeTop.setIsParent("true");
		ztreeTop.setTarget("none");
		ztreeTop.setUrl("");
		listztree.add(ztreeTop);
		
		if(id!=null){
			menu = menuService.findMenuById(id);
			List<Module> listModule= moduleService.findModuleByMenuId(id);
			for (Module module : listModule) {
				Ztree ztree= new Ztree();
				ztree.setId(module.getModelNid().toString());
				ztree.setpId(module.getModelPid().toString());
				ztree.setName(module.getModelName());
				ztree.setIsParent(module.getIsParent());
				ztree.setOpen("true");
				ztree.setTarget("none");
				ztree.setUrl("");
				setModelNid.add(module.getModelNid());
				listztree.add(ztree);
			}
		}else{
			Ztree ztreeHome = new Ztree();
			ztreeHome.setId("1");
			ztreeHome.setpId("0");
			ztreeHome.setName("首页");
			ztreeHome.setOpen("true");
			ztreeHome.setIsParent("false");
			ztreeHome.setTarget("none");
			ztreeHome.setUrl("");
			listztree.add(ztreeHome);
			menu = null;
		}
		int modelNid;
		
		if(setModelNid.size()>0){
			modelNid = Collections.max(setModelNid);
		}else{
			modelNid = 2;
		}
		mav.addObject("modelNid", modelNid);
		mav.addObject("menu", menu);
		mav.addObject("listztree", JSONArray.fromObject(listztree));
		return mav;
	}
	/**
	 * 进入配置页面
	 * @return
	 */
	@RequestMapping(value = "/configMenu")
	public ModelAndView configMenu(HttpServletRequest request,Integer id){
		String url = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ request.getContextPath(); // 项目名称
		ModelAndView mav  = new ModelAndView("account/portals/menu/menu_config");
		Menu menu = menuService.findMenuById(id);
		List<Ztree> listztree = new ArrayList<Ztree>();
		Ztree ztreeTop = new Ztree();
		ztreeTop.setId("0");
		ztreeTop.setpId("-1");
		ztreeTop.setName("菜单");
		ztreeTop.setOpen("true");
		ztreeTop.setIsParent("true");
		ztreeTop.setTarget("none");
		ztreeTop.setUrl("");
		listztree.add(ztreeTop);
		List<Module> listModule= moduleService.findModuleByMenuId(id);
		for (Module module : listModule) {
			Ztree ztree= new Ztree();
			ztree.setId(module.getModelNid().toString());
			ztree.setpId(module.getModelPid().toString());
			ztree.setName(module.getModelName());
			ztree.setIsParent(module.getIsParent());
			ztree.setOpen("true");
			ztree.setTarget("ajax");
			ztree.setRel("layoutPage");
			if(module.getModelNid()!=1 && !"true".equals(module.getIsParent()) ){
				ztree.setUrl(url+"/oa/module/intoModuelEdit?modelId="+module.getModelId());
			}else{
				ztree.setUrl(url+"/oa/layout/findLayoutPagejbsxBox?modelId="+module.getModelId());
			}
			listztree.add(ztree);
		}
		mav.addObject("menu", menu);
		mav.addObject("listztree", JSONArray.fromObject(listztree));
		return mav;
	}
	/**
	 * 进入详情页面
	 * @return
	 */
	@RequestMapping(value = "/findMenuById")
	public ModelAndView findMenuById(Integer id){
		ModelAndView mav  = new ModelAndView("account/portals/menu/menu_details");
		Menu menu = menuService.findMenuById(id);
		List<Ztree> listztree = new ArrayList<Ztree>();
		Ztree ztreeTop = new Ztree();
		ztreeTop.setId("0");
		ztreeTop.setpId("-1");
		ztreeTop.setName("菜单");
		ztreeTop.setOpen("true");
		ztreeTop.setIsParent("true");
		ztreeTop.setTarget("none");
		ztreeTop.setUrl("");
		listztree.add(ztreeTop);
		List<Module> listModule= moduleService.findModuleByMenuId(id);
		for (Module module : listModule) {
			Ztree ztree= new Ztree();
			ztree.setId(module.getModelNid().toString());
			ztree.setpId(module.getModelPid().toString());
			ztree.setName(module.getModelName());
			ztree.setIsParent(module.getIsParent());
			ztree.setOpen("true");
			ztree.setTarget("none");
			ztree.setUrl("");
			listztree.add(ztree);
		}
		mav.addObject("menu", menu);
		mav.addObject("listztree", JSONArray.fromObject(listztree));
		return mav;
	}
	/**
	 * 更新菜单树
	 * @return
	 */
	@RequestMapping(value = "/updateMenuZtree")
	@ResponseBody
	public Object updateMenuZtree(Menu menu){
		JsonBean  jsonbean = null;
		if(menuService.updateMenuZtreeService(menu)){
			jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/menu/findMenuPage"),"","closeCurrent","","");
		}else{
			jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		}
		 return jsonbean;
	}
	/**
	 * 删除信息
	 * @return
	 */
	@RequestMapping(value = "/deleteMenu")
	@ResponseBody
	public Object deleteMenu(Integer id){
		JsonBean  jsonbean = null;
		if(menuService.deleteMenuService(id)){
			jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/menu/findMenuPage"),"","","","");
		}else{
			jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		}
		 return jsonbean;
	}
}
