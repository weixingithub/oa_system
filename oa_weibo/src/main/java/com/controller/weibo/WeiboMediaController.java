package com.controller.weibo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.oa_bean.Page;
import org.oa_bean.SysUser;
import org.oa_bean.weibo.Weibo;
import org.oa_bean.weibo.WeiboMedia;
import org.oa_bean.weibo.WeiboToken;
import org.oa_common.date.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import weibo4j.Timeline;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;

import com.dao.weibo.WeiboMediaService;
import com.dao.weibo.WeiboService;
import com.dao.weibo.WeiboTokenDao;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;

/**
 * 上传短微博信息
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/oa/weiboMedia")
public class WeiboMediaController {
	private WeiboService weiboService;
	private WeiboMediaService weiboMediaService;
	private WeiboTokenDao weiboTokenDao;
	
	@Autowired
	public void setWeiboTokenDao(WeiboTokenDao weiboTokenDao) {
		this.weiboTokenDao = weiboTokenDao;
	}

	@Autowired
	public void setWeiboService(WeiboService weiboService) {
		this.weiboService = weiboService;
	}
	@Autowired
	public void setWeiboMediaService(WeiboMediaService weiboMediaService) {
		this.weiboMediaService = weiboMediaService;
	}
	/**
	 * 分页查询
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value = "/getPageWeiboMedia")
	public ModelAndView getPageWeiboMedia(HttpServletRequest request,Integer numPerPage
    		,Integer pageNum,Page<WeiboMedia> page,WeiboMedia weiboMedia,String orderField,String orderDirection,String startTime,String endTime){
		ModelAndView mav =  new ModelAndView("account/weibo/weiboMedia_list");
		LinkedHashMap<String,String> orderby  = null;
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
		SysUser sysUser = (SysUser)request.getSession().getAttribute(OACommon.LOGIN_USER);
		if(sysUser.getNodeId().equals(OACommon.SYSCREATOR)){
			page =weiboMediaService.getPageWeiboMedia(page, weiboMedia, orderby, startTime, endTime);
		}else{
			List<Weibo> list= weiboService.getWeiboByOrgId(sysUser.getOrgIds());
			StringBuilder uids = new StringBuilder();
			for (Weibo weibo : list) {
				uids.append(weibo.getWeiboToken().getUid());
				uids.append(",");
			}
			if(uids.length()>0){
				uids = uids.deleteCharAt(uids.length()-1);
			}
			weiboMedia.setUid(uids.toString());
			page =weiboMediaService.getPageWeiboMedia(page, weiboMedia, orderby, startTime, endTime);
		}
		mav.addObject("startTime", startTime);
		mav.addObject("endTime", endTime);
		mav.addObject("page", page);
		mav.addObject("weiboMedia", weiboMedia);
		mav.addObject("orderField", orderField);
		mav.addObject("orderDirection", orderDirection);
		return mav;
	}
	/**
	 * 进入微博上传页面
	 * @return
	 */
	@RequestMapping(value = "/IntoWeiboMediaAdd")
	@ResponseBody
	public ModelAndView IntoWeiboMediaAdd(HttpServletRequest request) {
		SysUser sysUser = (SysUser)request.getSession().getAttribute(OACommon.LOGIN_USER);
		List<Weibo> list = new ArrayList<Weibo>();
		if(sysUser.getNodeId().equals(OACommon.SYSCREATOR)){
			list= weiboService.getWeiboByOrgId(null);
		}else{
			list= weiboService.getWeiboByOrgId(sysUser.getOrgIds());
		}
		ModelAndView mav =  new ModelAndView("account/weibo/weiboModia_upload");
		mav.addObject("weiboList", list);
		return mav;
	}
	/**
	 * 详情页面
	 * @return
	 */
	@RequestMapping(value = "/IntoWeiboMediaShow")
	@ResponseBody
	public ModelAndView IntoWeiboMediaShow(String mid,String uid) {
		WeiboToken weiboToken = weiboTokenDao.findAccessTokenByUid(uid);
		String access_token = weiboToken.getAccessToken(); 
		Timeline tm = new Timeline(access_token);
		Status status;
		try {
			status = tm.showStatus(mid);
		} catch (WeiboException e) {
			e.printStackTrace();
			status = new Status();
		}
		ModelAndView mav =  new ModelAndView("account/weibo/weiboModia_show");
		mav.addObject("status", status);
		return mav;
	}
	/**
	 * 上传短微博
	 * @return
	 */
	@RequestMapping(value = "/uploadWeiboMedia")
	@ResponseBody
	public Object uploadWeiboMedia(HttpServletRequest request,Integer weiboId,Integer pubId,String text,String imgSrc,String title) {
		WeiboMedia weiboMedia = new WeiboMedia();
		JsonBean  jsonbean = null;
		Weibo weibo =weiboService.findWeiboById(weiboId);
		OACommon oa = new OACommon();
		HttpSession session = request.getSession();
		//物理路径
		final String pathUrl = session.getServletContext().getRealPath("/") +"\\"+oa.getString("filepath","/config.properties");
		//网络路径
		final String netWorkUrl = "http://" + request.getServerName()+ ":" + request.getServerPort()+request.getContextPath()+"/"+oa.getString("filepath","/config.properties");
			
		if(!"".equals(imgSrc) && imgSrc!=null){
			String localUrl =imgSrc.replace(netWorkUrl, pathUrl);
			weiboMedia.setLocalUrl(localUrl);
			weiboMedia.setPathUrl(imgSrc);
		}
		String createTime = DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
		weiboMedia.setCreateTime(createTime);
		weiboMedia.setText(text);
		weiboMedia.setPubId(pubId);
		weiboMedia.setTitle(title);
		
		if(weiboMediaService.addWeiboMediaService(weiboMedia, weibo.getWeiboToken())){
			  jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/weiboMedia/getPageWeiboMedia"),"","closeCurrent","","");
		 }else{
			  jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		 }
		return jsonbean;
	}
	
	/**
	 * 同步更新短微博的评论以及浏览信息
	 * @return
	 */
	@RequestMapping(value = "/updateWeiboMedia")
	@ResponseBody
	public Object updateWeiboMedia(Integer  id) {
		WeiboMedia weiboMedia = weiboMediaService.getWeiboMediaById(id);
		WeiboToken weiboToken = weiboTokenDao.findAccessTokenByUid(weiboMedia.getUid());
		JsonBean  jsonbean = null;
		if(weiboMediaService.updateWeiboMediaService(weiboMedia, weiboToken)){
			  jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/weiboMedia/getPageWeiboMedia"),"","","","");
		 }else{
			  jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		 }
		return jsonbean;
	}
	/**
	 * 删除微博
	 * @return
	 */
	@RequestMapping(value = "/deleteWeiboMedia")
	@ResponseBody
	public Object deleteWeiboMedia(Integer id,String mid,String uid) {
		WeiboToken weiboToken = weiboTokenDao.findAccessTokenByUid(uid);
		JsonBean  jsonbean = null;
		if(weiboMediaService.deleteWeiboMediaService(id,mid,weiboToken)){
			  jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/weiboMedia/getPageWeiboMedia"),"","","","");
		 }else{
			  jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		 }
		return jsonbean;
	}
}
