package com.controller.weibo;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.oa_bean.weibo.Weibo;
import org.oa_bean.weibo.WeiboToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dao.weibo.WeiboService;
import com.dao.weibo.WeiboTokenService;
import com.server.weibo.RequestAccessToken;

@Controller
@RequestMapping("/weibo")
public class WeiboController {
	private WeiboTokenService accessTokenService;
	private WeiboService weiboService;
	
	public WeiboService getWeiboService() {
		return weiboService;
	}
	@Autowired
	public void setWeiboService(WeiboService weiboService) {
		this.weiboService = weiboService;
	}
	public WeiboTokenService getAccessTokenService() {
		return accessTokenService;
	}
	@Autowired
	public void setAccessTokenService(WeiboTokenService accessTokenService) {
		this.accessTokenService = accessTokenService;
	}
	/**
	 * 进行微博OAuth2.0 授权
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/weiboPermit")
	public ModelAndView weiboPermit(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		Weibo weibo = weiboService.getWeiboByClientId(state);
		WeiboToken token = weibo.getWeiboToken();
		if(token.getAccessToken()!=null && !"".equals(token.getAccessToken())){
			boolean falg = RequestAccessToken.getTokenInfo(token.getAccessToken());
			if(!falg){
				WeiboToken token1 = RequestAccessToken.getAccessTokenByCode(code, weibo.getClientId(), weibo.getClientSercret(), weibo.getRedirectUrl());
				if(token1!=null){
					token1.setId(token.getId());
					accessTokenService.updateAccessTokenService(token1);
				}
			}
		}else{
			WeiboToken token1 = RequestAccessToken.getAccessTokenByCode(code, weibo.getClientId(), weibo.getClientSercret(), weibo.getRedirectUrl());
			if(token1!=null){
				token1.setId(token.getId());
				accessTokenService.updateAccessTokenService(token1);
			}
		}
		ModelAndView mav = new ModelAndView("login");
		return mav;
	}
	/**
	 * 进行微博OAuth2.0 撤销授权
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/weiboRevoke")
	public String weiboRevoke(HttpServletRequest request, HttpServletResponse response){
		return "";
	}
}
