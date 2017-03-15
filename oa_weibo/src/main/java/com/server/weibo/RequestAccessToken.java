package com.server.weibo;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.oa_bean.weibo.WeiboToken;
import org.oa_common.http.HttpThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import weibo4j.Oauth;
import weibo4j.http.AccessToken;
import weibo4j.model.WeiboException;
import weibo4j.util.BareBonesBrowserLaunch;


public class RequestAccessToken {
	private static Logger log = LoggerFactory.getLogger(RequestAccessToken.class);
	private static final String get_token_info = "https://api.weibo.com/oauth2/get_token_info";
	/**
	 * 查询token的有效时间
	 * @param accessToken
	 * @return
	 */
	public static boolean  getTokenInfo(String accessToken){
		Map<String,Object>  params= new HashMap<String, Object>();
		boolean falg = false;
		params.put("access_token", accessToken); 
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil.doPostSSL(get_token_info, params));
		
		try {
			System.out.println(jsonObject.toString());
			long create_at = jsonObject.getLong("create_at");
			long expire_in = jsonObject.getLong("expire_in");
			long current_tm = System.currentTimeMillis()/1000;
			if(current_tm<(create_at + expire_in)){
				falg = true;
			}else{
				falg = false;
			}
		} catch (Exception e) {
			log.error("微博授权时间查询失败！");
			falg = false;
		}
		return falg;
	}
	/**
	 * 发起微博授权请求
	 * @param client_ID
	 * @param redirect_URI
	 */
	public static void getAuthorize(String client_ID,String redirect_URI){
		Oauth oauth = new Oauth();
		try {
			BareBonesBrowserLaunch.openURL(oauth.authorize("code",client_ID, "all", client_ID, redirect_URI));
		} catch (WeiboException e) {
			if(401 == e.getStatusCode()){
				log.error("发起微博授权请求失败！");
			}else{
				e.printStackTrace();
			}
		}
	}
	/**
	 * 获取微博token
	 * @param code
	 * @param client_ID
	 * @param client_SERCRET
	 * @param redirect_URI
	 * @return
	 */
	public static WeiboToken getAccessTokenByCode(String code,String client_ID,String client_SERCRET,String redirect_URI){
		Oauth oauth = new Oauth();
		WeiboToken token = new WeiboToken();
		try {
			AccessToken accessToken = oauth.getAccessTokenByCode(code, client_ID, client_SERCRET, redirect_URI);
			token.setAccessToken(accessToken.getAccessToken());
			token.setExpireIn(accessToken.getExpireIn());
			token.setUid(accessToken.getUid());
			token.setClientId(client_ID);
		} catch (WeiboException e) {
			if(401 == e.getStatusCode()){
				log.error("获取微博token失败！");
			}else{
				e.printStackTrace();
			}
		}
		return token;
	}
	public static void main(String[] args) {
		String client_ID ="3327712025";
		String client_SERCRET ="7b51b0e7512aece56276fe152f157f90";
		String redirect_URI = "http://155v52p428.iask.in/weibo/weiboPermit";
		String accessToken = "2.00LMCPIBbujMdD74c3c43efcsUZN7D";
		String create_at="1480063552";
		String expire_in ="157679956";
		 System.out.println(getTokenInfo(accessToken));
	}
}
