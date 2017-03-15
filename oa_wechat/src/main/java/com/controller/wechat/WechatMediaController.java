package com.controller.wechat;

import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.oa_bean.Page;
import org.oa_bean.SysUser;
import org.oa_bean.wechat.Wechat;
import org.oa_bean.wechat.WechatMedia;
import org.oa_bean.wechat.WechatToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.wechat.WechatMediaService;
import com.dao.wechat.WechatService;
import com.dao.wechat.WechatTokenService;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;
@Controller
@RequestMapping("/oa/wechatMedia")
public class WechatMediaController {
	private WechatService wechatService;
	private WechatTokenService tokenService;
	private WechatMediaService wechatMediaService;
	 
	@Autowired
	public void setTokenService(WechatTokenService tokenService) {
		this.tokenService = tokenService;
	}
	@Autowired
	public void setWechatService(WechatService wechatService) {
		this.wechatService = wechatService;
	}
	@Autowired
	public void setWechatMediaService(WechatMediaService wechatMediaService) {
		this.wechatMediaService = wechatMediaService;
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
	@RequestMapping(value = "/getPageWechatMedia")
	public ModelAndView getPageWechatMedia(HttpServletRequest request,
			Integer numPerPage, Integer pageNum, Page<WechatMedia> page,
			WechatMedia wechatMedia, String orderField,
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
			page =  wechatMediaService.getPageWechatMedia(page, wechatMedia, orderby, startTime, endTime);
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
			wechatMedia.setAppId(appIds.toString());
			page = wechatMediaService.getPageWechatMedia(page, wechatMedia, orderby, startTime, endTime);
		}
		mav.addObject("startTime", startTime);
		mav.addObject("endTime", endTime);
		mav.addObject("page", page);
		mav.addObject("wechatMedia", wechatMedia);
		mav.addObject("orderField", orderField);
		mav.addObject("orderDirection", orderDirection);
		return mav;
	}
	/**
	 * 上传素材
	 * @return
	 */
	@RequestMapping(value = "/uploadWechatMedia")
	@ResponseBody
	public Object uploadWechatMedia(HttpServletRequest request,Integer wechatId,String imgSrc, WechatMedia wechatMedia) {
		JsonBean  jsonbean = null;
		Wechat wechat = wechatService.getWechatById(wechatId);
		WechatToken wechatToken = tokenService.updateTokenByTimeService(wechat.getWechatToken().getId(), wechat.getAppId(), wechat.getAppSecret());
		OACommon oa = new OACommon();
		HttpSession session = request.getSession();
		//物理路径
		final String pathUrl = session.getServletContext().getRealPath("/") +"\\"+oa.getString("filepath","/config.properties");
		//网络路径
		final String netWorkUrl = "http://" + request.getServerName()+ ":" + request.getServerPort()+request.getContextPath()+"/"+oa.getString("filepath","/config.properties");
			
		if(!"".equals(imgSrc) && imgSrc!=null){
			String localUrl =imgSrc.replace(netWorkUrl, pathUrl);
			wechatMedia.setLocalUrl(localUrl);
			wechatMedia.setPathUrl(imgSrc);
		}
		wechatMedia.setAppId(wechat.getAppId());
		if(wechatMediaService.addWechatMediaService(wechatMedia, wechatToken.getAccessToken())){
			  jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","","closeCurrent","","");
		 }else{
			  jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		 }
		return jsonbean;
	}
}
