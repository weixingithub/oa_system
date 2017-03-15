package com.controller.weibo;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.oa_bean.Page;
import org.oa_bean.SysUser;
import org.oa_bean.weibo.Weibo;
import org.oa_bean.weibo.WeiboToken;
import org.oa_common.date.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.weibo.WeiboService;
import com.server.weibo.RequestAccessToken;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;

@Controller
@RequestMapping("/oa/weibo")
public class WeiboOperationController {
	private WeiboService weiboService;
	@Autowired
	public void setWeiboService(WeiboService weiboService) {
		this.weiboService = weiboService;
	}
	 
	/**
	 * 分页查询
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value = "/getPageWeibo")
	public ModelAndView getPageWeibo(HttpServletRequest request,Integer numPerPage
    		,Integer pageNum,Page<Weibo> page,Weibo weibo,String orderField,String orderDirection,String startTime,String endTime){
		ModelAndView mav =  new ModelAndView("account/weibo/weibo_list");
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
			page = weiboService.getPageWeibo(page, weibo, orderby, startTime, endTime);
		}else{
			weibo.setOrgId(sysUser.getOrgIds());
			page = weiboService.getPageWeibo(page, weibo, orderby, startTime, endTime);
		}
		mav.addObject("startTime", startTime);
		mav.addObject("endTime", endTime);
		mav.addObject("weibo", weibo);
		mav.addObject("page", page);
		mav.addObject("orderField", orderField);
		mav.addObject("orderDirection", orderDirection);
		return mav;
	}
	/**
	 * 编辑微博基本信息
	 * @param wechat
	 * @param tokenId
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdateWeibo")
	@ResponseBody
	public Object addOrUpdateWeibo(Weibo weibo,String accessTokenId){
		JsonBean  jsonbean = null;
		boolean falg = false;
		String createTime =  DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
		weibo.setCreateTime(createTime);
		if(weibo.getId()==null || weibo.getId()==0){
			WeiboToken accessToken = new WeiboToken();
			accessToken.setClientId(weibo.getClientId());
			weibo.setWeiboToken(accessToken);
			falg =weiboService.addWeiboService(weibo);
		}else{
			WeiboToken accessToken = new WeiboToken();
			accessToken.setId(Integer.parseInt(accessTokenId));
			weibo.setWeiboToken(accessToken);
			falg =weiboService.updateWeiboService(weibo);
		}
		if(falg){
			  jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/weibo/getPageWeibo"),"","closeCurrent","","");
		 }else{
			  jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		 }
		return jsonbean;
	}
	/**
	 * 删除微博账号
	 * @return
	 */
	@RequestMapping(value = "/deleteWeibo")
	@ResponseBody
	public Object deleteWeibo(Integer id) {
		JsonBean  jsonbean = null;
		if(weiboService.deleteWeiboService(id)){
			  jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/weibo/getPageWeibo"),"","","","");
		 }else{
			  jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		 }
		return jsonbean;
	}
	/**
	 * 进入编辑页面
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value = "/IntoWeiboEdit")
	@ResponseBody
	public ModelAndView IntoWeiboEdit(Integer id){
		ModelAndView mav =  new ModelAndView("account/weibo/weibo_edit");
		Weibo weibo = null;
		if(id!=null){
			weibo =  weiboService.findWeiboById(id);
		}else{
			weibo = new Weibo();
		}
		mav.addObject("weibo", weibo);
		return mav;
	}
	/**
	 * 进入微博配置页面
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value = "/IntoWeiboConfig")
	@ResponseBody
	public ModelAndView IntoWeiboConfig(Integer id){
		ModelAndView mav =  new ModelAndView("account/weibo/weibo_config");
		Weibo weibo =   weiboService.findWeiboById(id);
		mav.addObject("weibo", weibo);
		return mav;
	}
	/**
	 * 获取微博详细信息
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value = "/IntoWeiboDetails")
	@ResponseBody
	public ModelAndView IntoWeiboDetails(Integer id){
		ModelAndView mav =  new ModelAndView("account/weibo/weibo_details");
		Weibo weibo =   weiboService.findWeiboById(id);
		mav.addObject("weibo", weibo);
		return mav;
	}
	/**
	 * 开始微博OAuth2.0 授权
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/weiboStartPermit")
	@ResponseBody
	public Object weiboStartPermit(Integer id){
		Weibo weibo =   weiboService.findWeiboById(id);
		RequestAccessToken.getAuthorize(weibo.getClientId(), weibo.getRedirectUrl());
		JsonBean jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/weibo/getPageWeibo"),"","closeCurrent","","");
		return jsonbean;
	}
}
