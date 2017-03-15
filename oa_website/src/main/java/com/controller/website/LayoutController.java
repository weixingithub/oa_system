package com.controller.website;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.oa_bean.Page;
import org.oa_bean.website.Layout;
import org.oa_bean.website.Module;
import org.oa_common.date.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.website.LayoutService;
import com.dao.website.ModuleService;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;
import com.tool.website.WPCommon;

/**
 * 网站布局配置
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/oa/layout")
public class LayoutController {
	private LayoutService layoutService;
	private ModuleService moduleService;
	@Autowired
	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
	@Autowired
	public void setLayoutService(LayoutService layoutService) {
		this.layoutService = layoutService;
	}
	/**
	 * 分页
	 * @param numPerPage
	 * @param pageNum
	 * @param page
	 * @param layout
	 * @return
	 */
	@RequestMapping(value = "/findLayoutPage")
	public ModelAndView findLayoutPage(Integer numPerPage,Integer pageNum,Page<Layout> page, Layout layout,String orderField,String orderDirection){
		 ModelAndView mav = new ModelAndView("account/portals/layout/layout_list");
		 LinkedHashMap<String,String> orderby  = null;
		 if(numPerPage == null || numPerPage ==-1){
			page = new Page<Layout>(10);
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
		 page = layoutService.findLayoutPage(page, layout, orderby);
		 mav.addObject("layoutlevelMap",WPCommon.layoutlevelMap);
		 mav.addObject("page", page);
		 mav.addObject("layout", layout);
		 mav.addObject("orderField", orderField);
		 mav.addObject("orderDirection", orderDirection);
		return mav;
	}
	 
	/**
	 * 分页选择
	 * @param numPerPage
	 * @param pageNum
	 * @param page
	 * @param layout
	 * @return
	 */
	@RequestMapping(value = "/findLayoutPagejbsxBox")
	public ModelAndView findLayoutPagejbsxBox(Integer numPerPage,Integer pageNum,Page<Layout> page, Layout layout,String orderField,String orderDirection,Integer modelId){
		ModelAndView mav = new ModelAndView("account/portals/layout/layoutjbsxBox_list");
		LinkedHashMap<String,String> orderby  = null;
		if(numPerPage == null || numPerPage ==-1){
			page = new Page<Layout>(10);
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
		Module module = moduleService.findModuleById(modelId);
		if(module.getModelPid()==0 && module.getModelNid()==1){
			layout.setLayoutlevel(1);
		}else if(module.getModelPid()==0 && module.getModelNid()!=1){
			layout.setLayoutlevel(2);
		}else{
			layout.setLayoutlevel(3);
		}
		
		page = layoutService.findLayoutPage(page, layout, orderby);
		mav.addObject("module", module);
		mav.addObject("layoutlevelMap",WPCommon.layoutlevelMap);
		mav.addObject("page", page);
		mav.addObject("layout", layout);
		mav.addObject("orderField",orderField);
		mav.addObject("orderDirection",orderDirection);
		return mav;
	}
	/**
	 * 添加或编辑
	 * @return
	 */
	@RequestMapping(value = "/addAndUpdateLayout")
	@ResponseBody
	public Object addAndUpdateLayout(Layout layout){
		JsonBean  jsonbean = null;
		boolean falg = false;
		String createTime =  DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
		layout.setCreateTime(createTime);
		if(layout.getLayoutId()==null || layout.getLayoutId()==0){
			falg = layoutService.addLayoutService(layout);
		}else{
			falg = layoutService.updateLayoutService(layout);
		}
		if(falg){
			jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/layout/findLayoutPage"),"","closeCurrent","","");
		}else{
			 jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		}
		 return jsonbean;
	}
	/**
	 * 进入编辑页面
	 * @return
	 */
	@RequestMapping(value = "/intoEditLayout")
	public ModelAndView intoUpdateLayout(HttpServletRequest request,Integer id){
		ModelAndView  mav =  new ModelAndView("account/portals/layout/layout_edit");
		Layout layout = null;
		if(id!=null){
			layout =  layoutService.findLayoutById(id);
		}else{
			layout = new Layout();
		}
		mav.addObject("layoutlevelMap",WPCommon.layoutlevelMap);
		mav.addObject("layout", layout);
		return mav;
	}
	/**
	 * 查询信息
	 * @return
	 */
	@RequestMapping(value = "/findLayoutById")
	public ModelAndView findLayoutById(Integer id){
		Layout layout =  layoutService.findLayoutById(id);
		ModelAndView mav = new ModelAndView("account/portals/layout/layout_details");
		mav.addObject("layoutlevelMap",WPCommon.layoutlevelMap);
		mav.addObject("layout", layout);
		return mav;
	}
	/**
	 * 删除信息
	 * @return
	 */
	@RequestMapping(value = "/deleteLayout")
	@ResponseBody
	public Object deleteLayout(Integer id){
		JsonBean  jsonbean = null;
		try{
			if(layoutService.deleteLayoutService(id)){
				jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/layout/findLayoutPage"),"","","","");
			}else{
				 jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
			}
		}catch(Exception e){
			jsonbean = new JsonBean("300",OACommon.OPERATION_RELEVANCE,"","","","","");
		}
		 return jsonbean;
	}
}
