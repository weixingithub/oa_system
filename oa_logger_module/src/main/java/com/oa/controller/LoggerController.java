package com.oa.controller;

import java.util.LinkedHashMap;

import org.oa_bean.OALog;
import org.oa_bean.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dao.LogService;

@Controller
@RequestMapping("/oa/logger")
public class LoggerController {
	
	private LogService logService;

	public LogService getLogService() {
		return logService;
	}
	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}
	/**
	 * 查询并且按照条件分页
	 * @param numPerPage
	 * @param pageNum
	 * @param page
	 * @return
	 */
	@RequestMapping("/findloggerPage")
	public ModelAndView findGroupPage(Integer numPerPage,Integer pageNum,
			Page<OALog> page,OALog oalog,String startTime,String endTime,String orderField,String orderDirection,String userName,String operationType){
		ModelAndView mv = new ModelAndView("/loggerpage/pagelog");
		LinkedHashMap<String,String> orderby  = null;
		  if(numPerPage == null){
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
	    	orderField = "actiontime";
	    	orderDirection="desc";
	    	orderby.put(orderField, orderDirection);
		 }
		 if("editor".equals(operationType)){
			 operationType="更新";
		 }
		 if("add".equals(operationType)){
			 operationType="添加";
		 }
		 if("delete".equals(operationType)){
			 operationType="删除";
		 }
		 if("configure".equals(operationType)){
			 operationType="配置";
		 }
		  	page = logService.getConditionOaLogPage(page, oalog, orderby,userName,operationType,startTime,endTime);
			mv.addObject("page", page);
			mv.addObject("orderField",orderField);
			mv.addObject("orderDirection",orderDirection);
			mv.addObject("startTime",startTime);
			mv.addObject("endTime", endTime);
			mv.addObject("operationType", operationType);
			mv.addObject("oalog",oalog);
		  return mv;
	}
	
}
