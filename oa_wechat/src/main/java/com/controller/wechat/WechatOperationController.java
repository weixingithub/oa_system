package com.controller.wechat;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.oa_bean.Page;
import org.oa_bean.SysUser;
import org.oa_bean.message.receive.wechat.Button;
import org.oa_bean.message.receive.wechat.Menu;
import org.oa_bean.message.receive.wechat.Root;
import org.oa_bean.message.receive.wechat.Sub_button;
import org.oa_bean.wechat.Wechat;
import org.oa_bean.wechat.WechatToken;
import org.oa_common.date.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.wechat.WechatService;
import com.dao.wechat.WechatTokenService;
import com.server.wechat.RequestMenu;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;
@Controller
@RequestMapping("/oa/wechat")
public class WechatOperationController {
	private WechatService wechatService;
	private WechatTokenService tokenService;
	
	 
	@Autowired
	public void setTokenService(WechatTokenService tokenService) {
		this.tokenService = tokenService;
	}
	@Autowired
	public void setWechatService(WechatService wechatService) {
		this.wechatService = wechatService;
	}
	/**
	 * 分页查询
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value = "/getPageWechat")
	public ModelAndView getPageWeibo(HttpServletRequest request,Integer numPerPage
    		,Integer pageNum,Page<Wechat> page,Wechat wechat,String orderField,String orderDirection,String startTime,String endTime){
		ModelAndView mav =  new ModelAndView("account/wechat/wechat_list");
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
			page = wechatService.getPageWechat(page, wechat, orderby, startTime, endTime);
		}else{
			wechat.setOrgId(sysUser.getOrgIds());
			page = wechatService.getPageWechat(page, wechat, orderby, startTime, endTime);
		}
		mav.addObject("startTime", startTime);
		mav.addObject("endTime", endTime);
		mav.addObject("wechat", wechat);
		mav.addObject("page", page);
		mav.addObject("orderField", orderField);
		mav.addObject("orderDirection", orderDirection);
		return mav;
	}
	/**
	 * 编辑微信基本信息
	 * @param wechat
	 * @param tokenId
	 * @return
	 */
	@RequestMapping(value = "/addOrUpdateWechat")
	@ResponseBody
	public Object addOrUpdateWechat(Wechat wechat,String tokenId){
		JsonBean  jsonbean = null;
		String createTime =  DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
		wechat.setCreateTime(createTime);
		boolean falg = false;
		if(wechat.getId()==null || wechat.getId()==0){
			wechat.setWechatToken(new WechatToken());
			falg = wechatService.addWechatService(wechat);
		}else{
			WechatToken token = new WechatToken();
			token.setId(Integer.parseInt(tokenId));
			wechat.setWechatToken(token);
			falg = wechatService.updateWechatService(wechat);
		}
		if(falg){
			  jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/wechat/getPageWechat"),"","closeCurrent","","");
		 }else{
			  jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		 }
		return jsonbean;
	}
	/**
	 * 进入微信配置页面
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value = "/IntoWechatConfig")
	public ModelAndView IntoWechatConfig(Integer id ){
		ModelAndView mav =  new ModelAndView("account/wechat/wechat_config");
		Wechat wechat = wechatService.getWechatById(id);
		WechatToken token = tokenService.updateTokenByTimeService(wechat.getWechatToken().getId(), wechat.getAppId(), wechat.getAppSecret());
		wechat.setWechatToken(token);
		mav.addObject("wechat", wechat);
		return mav;
	}
	/**
	 * 通过AccountID
	 * @param accountId
	 * @param orgId
	 * @return
	 */
	@RequestMapping(value = "/IntoWechatEdit")
	public ModelAndView IntoWechatEdit(Integer id ){
		ModelAndView mav =  new ModelAndView("account/wechat/wechat_edit");
		Wechat wechat = null;
		if(id!=null){
			wechat = wechatService.getWechatById(id);
		}else{
			 wechat = new Wechat();
		}
		mav.addObject("wechat", wechat);
		return mav;
	}
	/**
	 * 删除微信账号
	 * @return
	 */
	@RequestMapping(value = "/deleteWechat")
	@ResponseBody
	public Object deleteWechat(Integer id) {
		JsonBean  jsonbean = null;
		if(wechatService.deleteWechatByIdService(id)){
			  jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/wechat/getPageWechat"),"","","","");
		 }else{
			  jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		 }
		return jsonbean;
	}
	/**
	 * 更新微信token
	 * @return
	 */
	@RequestMapping(value = "/updateWechatToken")
	@ResponseBody
	public Object updateWechatToken(Integer id) {
		JsonBean  jsonbean = null;
		if(tokenService.updateTokenService(id)){
			  jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/wechat/getPageWechat"),"","","","");
		 }else{
			  jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		 }
		return jsonbean;
	}
	/**
	 * 微信的菜单创建
	 * @param request
	 * @param response
	 * @param MenuJson
	 * @return
	 */
	@RequestMapping(value = "/createWechatMenu")
	@ResponseBody
	public Object createMenu(HttpServletRequest request,
			HttpServletResponse response, String jsonMenu,String accessToken) {
		JsonBean  jsonbean = null;
		if(RequestMenu.creatorMenu(accessToken, jsonMenu)){
			  jsonbean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/wechat/getPageWechat"),"","closeCurrent","","");
		 }else{
			  jsonbean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		 }
		return jsonbean;
	}
	
	/**
	 * 查询微信菜单
	 * @param request
	 * @param response
	 * @param MenuJson
	 * @return
	 */
	@RequestMapping(value = "/queryWechatMenu")
	@ResponseBody
	public Object queryWechatMenu(HttpServletRequest request,HttpServletResponse response,String accessToken) {
		JSONObject jsonObject = RequestMenu.QueryMenu(accessToken);
		Root root = null;
		if (null != jsonObject) {
			try {
				Map<String, Class<?>> classMap = new  HashMap<String, Class<?>>();  
				 classMap.put("menu", Menu.class);  
				 classMap.put("button", Button.class);  
				 classMap.put("sub_button", Sub_button.class);  
				 root = (Root) JSONObject.toBean(jsonObject,  Root.class, classMap);
			} catch (JSONException e) {
				root = null;
			}
		}
		return root;
	}
}
