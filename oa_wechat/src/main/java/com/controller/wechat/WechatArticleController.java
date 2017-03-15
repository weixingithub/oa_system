package com.controller.wechat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.oa_bean.Page;
import org.oa_bean.SysUser;
import org.oa_bean.wechat.Wechat;
import org.oa_bean.wechat.WechatArticle;
import org.oa_bean.wechat.WechatToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.wechat.WechatArticleService;
import com.dao.wechat.WechatService;
import com.dao.wechat.WechatTokenService;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;
@Controller
@RequestMapping("/oa/wechatArticle")
public class WechatArticleController {
	private WechatService wechatService;
	private WechatTokenService tokenService;
	private WechatArticleService wechatArticleService;

	@Autowired
	public void setTokenService(WechatTokenService tokenService) {
		this.tokenService = tokenService;
	}

	@Autowired
	public void setWechatService(WechatService wechatService) {
		this.wechatService = wechatService;
	}

	@Autowired
	public void setWechatArticleService(
			WechatArticleService wechatArticleService) {
		this.wechatArticleService = wechatArticleService;
	}
	/**
	 * 分页查询
	 * @param request
	 * @param numPerPage
	 * @param pageNum
	 * @param page
	 * @param wechatArticle
	 * @param orderField
	 * @param orderDirection
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	@RequestMapping(value = "/getPageWechatArticle")
	public ModelAndView getPageWechatArticle(HttpServletRequest request,
			Integer numPerPage, Integer pageNum, Page<WechatArticle> page,
			WechatArticle wechatArticle, String orderField,
			String orderDirection, String startTime, String endTime) {
		ModelAndView mav = new ModelAndView("account/wechat/wechatArticle_list");
		LinkedHashMap<String, String> orderby = null;
		if (numPerPage == null || numPerPage == -1) {
			page = new Page(10);
		} else {
			page.setPageSize(numPerPage);
			page.setPageNo(pageNum);
		}
		if (orderField != null && !"".equals(orderField)
				&& orderDirection != null && !"".equals(orderDirection)) {
			orderby = new LinkedHashMap<String, String>();
			orderby.put(orderField, orderDirection);
		} else {
			orderby = new LinkedHashMap<String, String>();
			orderField = "createTime";
			orderDirection = "desc";
			orderby.put(orderField, orderDirection);
		}
		SysUser sysUser = (SysUser) request.getSession().getAttribute(
				OACommon.LOGIN_USER);
		if (sysUser.getNodeId().equals(OACommon.SYSCREATOR)) {
			page = wechatArticleService.getPageWechatArticle(page,
					wechatArticle, orderby, startTime, endTime);
		} else {
			List<Wechat> list = wechatService.getWechatByOrgId(sysUser
					.getOrgIds());
			StringBuilder appIds = new StringBuilder();
			for (Wechat wechat : list) {
				appIds.append(wechat.getAppId());
				appIds.append(",");
			}
			if (appIds.length() > 0) {
				appIds = appIds.deleteCharAt(appIds.length() - 1);
			}
			wechatArticle.setAppId(appIds.toString());
			page = wechatArticleService.getPageWechatArticle(page,
					wechatArticle, orderby, startTime, endTime);
		}
		mav.addObject("startTime", startTime);
		mav.addObject("endTime", endTime);
		mav.addObject("page", page);
		mav.addObject("wechatArticle", wechatArticle);
		mav.addObject("orderField", orderField);
		mav.addObject("orderDirection", orderDirection);
		return mav;
	}
	/**
	 * 上传图文信息
	 * @return
	 */
	@RequestMapping(value = "/uploadWechatArticle")
	@ResponseBody
	public Object uploadWechatArticle(HttpServletRequest request,Integer wechatId, WechatArticle wechatArticle) {
		JsonBean  jsonbean = null;
		Wechat wechat = wechatService.getWechatById(wechatId);
		WechatToken wechatToken = tokenService.updateTokenByTimeService(wechat.getWechatToken().getId(), wechat.getAppId(), wechat.getAppSecret());
		wechatArticle.setAppId(wechat.getAppId());
		if(wechatArticleService.updateWechatArticleService(wechatArticle, wechatToken.getAccessToken())){
			  jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/wechatArticle/getPageWechatArticle"),"","closeCurrent","","");
		 }else{
			  jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		 }
		return jsonbean;
	}
	/**
	 * 进入微信上传页面
	 * @return
	 */
	@RequestMapping(value = "/IntoWechatArticleAdd")
	@ResponseBody
	public ModelAndView IntoWechatArticleAdd(HttpServletRequest request) {
		SysUser sysUser = (SysUser)request.getSession().getAttribute(OACommon.LOGIN_USER);
		List<Wechat> list =  new ArrayList<Wechat>();
		if(sysUser.getNodeId().equals(OACommon.SYSCREATOR)){
			list = wechatService.getWechatByOrgId(null);
		}else{
			list = wechatService.getWechatByOrgId(sysUser
					.getOrgIds());
		}
		ModelAndView mav =  new ModelAndView("account/wechat/wechatArticle_upload");
		mav.addObject("wechatList", list);
		return mav;
	}
}
