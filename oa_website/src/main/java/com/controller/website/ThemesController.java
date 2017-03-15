package com.controller.website;

import java.util.LinkedHashMap;

import org.oa_bean.Page;
import org.oa_bean.website.Themes;
import org.oa_bean.website.Website;
import org.oa_common.date.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.website.ThemesService;
import com.dao.website.WebsiteService;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;
import com.tool.website.WPCommon;

@Controller
@RequestMapping("/oa/themes")
public class ThemesController {
	private ThemesService themesService;
	
	private WebsiteService websiteService;
	@Autowired
	public void setThemesService(ThemesService themesService) {
		this.themesService = themesService;
	}
	@Autowired
	public void setWebsiteService(WebsiteService websiteService) {
		this.websiteService = websiteService;
	}
	/**
	 * 分页
	 * @param numPerPage
	 * @param pageNum
	 * @param page
	 * @param Themes
	 * @param orderField
	 * @param orderDirection
	 * @return
	 */
	@RequestMapping(value = "/findThemesPage")
	public ModelAndView findThemesPage(Integer numPerPage,Integer pageNum,Page<Themes> page, Themes themes,String orderField,String orderDirection){
		 ModelAndView mav = new ModelAndView("account/portals/themes/themes_list");
		 LinkedHashMap<String,String> orderby  = null;
		 if(numPerPage == null || numPerPage ==-1){
			page = new Page<Themes>(10);
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
		 page = themesService.findThemesPage(page, themes, orderby);
		 mav.addObject("themeColourMap",WPCommon.themeColourMap);
		 mav.addObject("page", page);
		 mav.addObject("themes", themes);
		 mav.addObject("orderField", orderField);
		 mav.addObject("orderDirection", orderDirection);
		 return mav;
	}
	/**
	 * 分页 选择
	 * @param numPerPage
	 * @param pageNum
	 * @param page
	 * @param Themes
	 * @param orderField
	 * @param orderDirection
	 * @return
	 */
	@RequestMapping(value = "/findThemesPagejbsxBox")
	public ModelAndView findLayoutPagejbsxBox(Integer numPerPage,Integer pageNum,Page<Themes> page, Themes themes,String orderField,String orderDirection,Integer websiteId){
		 ModelAndView mav = new ModelAndView("account/portals/themes/themesjbsxBox_list");
		 LinkedHashMap<String,String> orderby  = null;
		 if(numPerPage == null || numPerPage ==-1){
			page = new Page<Themes>(10);
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
		 page = themesService.findThemesPage(page, themes, orderby);
		 Website website = websiteService.findWebsiteById(websiteId);
		 mav.addObject("themeColourMap",WPCommon.themeColourMap);
		 mav.addObject("websiteId", websiteId);
		 mav.addObject("website", website);
		 mav.addObject("page", page);
		 mav.addObject("themes", themes);
		 mav.addObject("orderField", orderField);
		 mav.addObject("orderDirection", orderDirection);
		 return mav;
	}
	/**
	 * 添加或编辑
	 * @return
	 */
	@RequestMapping(value = "/addAndUpdateThemes")
	@ResponseBody
	public Object addAndUpdateThemes(Themes themes){
		JsonBean  jsonbean = null;
		boolean falg = false;
		String createTime =  DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
		themes.setCreateTime(createTime);
		if(themes.getThemeId()==null || themes.getThemeId()==0){
			 falg = themesService.addThemesService(themes);
		}else{
			falg = themesService.updateThemesService(themes);
		}
		if(falg){
			jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/themes/findThemesPage"),"","closeCurrent","","");
		}else{
			jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		}
		 return jsonbean;
	}
	/**
	 * 查询信息
	 * @return
	 */
	@RequestMapping(value = "/findThemesById")
	public ModelAndView findThemesById(Integer id){
		Themes themes =  themesService.findThemesById(id);
		ModelAndView mav = new ModelAndView("account/portals/themes/themes_details");
		mav.addObject("themes", themes);
		mav.addObject("themeColourMap",WPCommon.themeColourMap);
		return mav;
	}
	/**
	 * 进入编辑页面
	 * @return
	 */
	@RequestMapping(value = "/intoThemesEdit")
	public ModelAndView intoThemesEdit(Integer id){
		Themes themes = null;
		ModelAndView mav = new ModelAndView("account/portals/themes/themes_edit");
		if(id!=null){
			themes = themesService.findThemesById(id);
		}else{
			themes = new Themes();
		}
		mav.addObject("themeColourMap",WPCommon.themeColourMap);
		mav.addObject("themes", themes);
		return mav;
	}
	/**
	 * 删除信息
	 * @return
	 */
	@RequestMapping(value = "/deleteThemes")
	@ResponseBody
	public Object deleteThemes(Integer id){
		JsonBean  jsonbean = null;
		if(themesService.deleteThemesService(id)){
			 jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/themes/findThemesPage"),"","","","");
		}else{
			 jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		}
		 return jsonbean;
	}
	
}
