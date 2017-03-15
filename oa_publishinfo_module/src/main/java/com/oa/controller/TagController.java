package com.oa.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;




import java.util.List;

import javax.servlet.http.HttpServletRequest;

import jodd.io.http.Http;

import org.infinispan.context.Flag;
import org.oa_bean.Page;
import org.oa_bean.SysUser;
import org.oa_bean.Tags;
import org.oa_common.date.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.TagService;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;

/**
 * 标签管理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="/oa/tagCategory")
public class TagController {

	
	private TagService tagService;
	
	@Autowired
	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	/**
	 * 获取标签分类列表(分页)
	 * @param request
	 * @param numPerPage
	 * @param pageNum
	 * @param page
	 * @param tagCategory
	 * @param orderField
	 * @param orderDirection
	 * @return
	 */
	@RequestMapping(value="/findTagByPage")
	public ModelAndView findTagCategoryPage(HttpServletRequest request,Integer numPerPage ,Integer pageNum,
			   Page<Tags> page,Tags tagCategory,String startTime,String endTime,String orderField,String orderDirection){
		
		ModelAndView mov = new ModelAndView("tag/tag_list");
		LinkedHashMap<String,String> orderby =null;
		if(numPerPage == null ||numPerPage == -1){
			page = new Page(10);
		}else{
			page.setPageNo(pageNum);
			page.setPageSize(numPerPage);
		}
		if(orderField != null && !"".equals(orderField) && orderDirection != null && !"".equals(orderDirection)){
			orderby = new LinkedHashMap<String, String>();
			orderby.put(orderField, orderDirection);
		}else{
			orderby = new LinkedHashMap<String, String>();
			orderField = "createTime";
			orderDirection = "desc";
			orderby.put(orderField, orderDirection);
		}
		page = tagService.findTagCategoryByPage(page,tagCategory,orderby,startTime,endTime);
		mov.addObject("page",page);
		mov.addObject("orderField",orderField);
		mov.addObject("orderDirection",orderDirection);
		mov.addObject("tagCategory",tagCategory);
		mov.addObject("startTime",startTime);
		mov.addObject("endTime",endTime);
		return mov;
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(value="/toAddTagCategory")
	public ModelAndView toAddCategory(){
		ModelAndView mov = new ModelAndView("tag/tagCategory_add");
		return mov;
	}
	
	/**
	 * 添加标签分类
	 * @param request
	 * @param tagCategory
	 * @return
	 */
	@RequestMapping(value="/addTagCategory")
	@ResponseBody
	public Object addTagCategory(HttpServletRequest request,Tags tagCategory){
		  JsonBean jsonBean = null;
		  String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		  tagCategory.setCreateTime(currentTime);
		  if(tagService.saveTagCategory(tagCategory)){
			  jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/tagCategory/findTagByPage"),"","closeCurrent","","");
		  }else{
			  jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		  }
		
		return jsonBean;
	}
	
	/**
	 * 跳转到修改页面
	 * @param resuqest
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/toUpdateTagCategory")
	public ModelAndView toUdateTagCategory(HttpServletRequest resuqest,Integer id){
		ModelAndView mov = new ModelAndView("tag/tagCategory_update");
		Tags tagCategory = tagService.findTagCategoryById(id);
		mov.addObject("tagCategory",tagCategory);
		return mov;
	}
	
	/**
	 * 修改标签分类
	 * @param request
	 * @param tagCategory
	 * @return
	 */
	@RequestMapping(value="/updateTagCategory")
	@ResponseBody
	public Object updateTagCategory(HttpServletRequest request,Tags tagCategory){
		  JsonBean jsonBean = null;
		   String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		  tagCategory.setCreateTime(currentTime);
		  if(tagService.updateTagCategory(tagCategory)){
			  jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/tagCategory/findTagByPage"),"","closeCurrent","","");
		  }else{
			  jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		  }
		
		return jsonBean;
	}
	
	/**
	 * 删除标签分类
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/delTagCategory")
	@ResponseBody
	public Object delTagCategory(Integer id){
		  JsonBean jsonBean = null;
		  if(tagService.delTagCategory(id)){
			  jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/tagCategory/findTagByPage"),"","closeCurrent","","");
		  }else{
			  jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		  }
		return jsonBean;
	}
	
	/**
	 * 比较标签是否已存在，不存在则插入数据库
	 */
	@RequestMapping(value="/equalsTags")
	@ResponseBody
	public Object equalsTags(String tagName){
		 JsonBean jsonBean = null;
		  if(tagService.equalsAndUpdateTag(tagName)){
			  jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","","closeCurrent","","");
		  }else{
			  jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		  }
		return jsonBean;
	}
}
