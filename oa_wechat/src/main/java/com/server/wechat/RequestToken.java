package com.server.wechat;

import java.util.Arrays;
import java.util.UUID;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.oa_bean.wechat.Oauth2Token;
import org.oa_bean.wechat.Ticket;
import org.oa_bean.wechat.WechatToken;
import org.oa_common.http.HttpThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tool.SignUtil;

/**
 * token微信请求
 * @author Administrator
 *
 */
public class  RequestToken {
	// 凭证获取token(GET)
	public final static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	// 凭证获取ticket(GET)
	public final static String TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	// 凭证获取授权token(GET)
	public final static String OAUTH2_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	private static Logger log = LoggerFactory.getLogger(RequestToken.class);
	/**
	 * 获取接口访问凭证
	 * 
	 * @param appid
	 *            凭证
	 * @param appsecret
	 *            密钥
	 * @return
	 */
	public static WechatToken getToken(String appid ,String  appsecret){
		WechatToken token = null;
		String requestUrl = TOKEN_URL.replace("APPID", appid).replace(
				"APPSECRET", appsecret);
		// 发起GET请求获取凭证
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil
				.doGetSSL(requestUrl));
		
	    if (null != jsonObject) {
			try {
				token = new WechatToken();
				token.setAccessToken(jsonObject.getString("access_token"));
				token.setExpiresIn(jsonObject.getInt("expires_in"));
				token.setAppId(appid);
				token.setCreateTime(System.currentTimeMillis());
			} catch (JSONException e) {
				token = null;
				// 获取token失败
				log.error("获取token失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return token;
	}
	/**
	 * 获取网页授权凭证
	 * 
	 * @param appId
	 *            公众账号的唯一标识
	 * @param appSecret
	 *            公众账号的密钥
	 * @param code
	 * @return Aouth2Token
	 */
	public static Oauth2Token getOauth2AccessToken(String appId,
			String appSecret, String code) {
		Oauth2Token o2T = null;
		// 拼接请求地址
		String requestUrl = OAUTH2_TOKEN_URL.replace("APPID", appId)
				.replace("SECRET", appSecret).replace("CODE", code);
		// 获取网页授权凭证
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil
				.doGetSSL(requestUrl));
		if (null != jsonObject) {
			try {
				o2T = new Oauth2Token();
				o2T.setAccessToken(jsonObject.getString("access_token"));
				o2T.setExpiresIn(jsonObject.getInt("expires_in"));
				o2T.setRefreshToken(jsonObject.getString("refresh_token"));
				o2T.setOpenId(jsonObject.getString("openid"));
				o2T.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				o2T = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode,
						errorMsg);
			}
		}
		return o2T;
	}
	/**
	 * 根据获取临时jsapi_ticket
	 * 
	 * @param access_token
	 * @return
	 */
	public static String getTicket(String access_token) {
		String ticket = null;
		// 拼接请求地址
		String requestUrl = TICKET_URL.replace("ACCESS_TOKEN", access_token);
		// 获取网页授权凭证
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil
				.doGetSSL(requestUrl));
		if (null != jsonObject) {
			try {
				ticket = jsonObject.getString("ticket");
			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取ticket失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return ticket;
	}
	/**
	 * Js接口的权限验证配置
	 * 
	 * @param token
	 * @param urlPath
	 * @return
	 */
	public static Ticket getTicketParams(String accessToken, String appId, String urlPath) {
		Ticket tokenParam = new Ticket();
		// 1、获取Ticket
		String jsapi_ticket = getTicket(accessToken);
		// 2、时间戳和随机字符串
		String noncestr = UUID.randomUUID().toString().replace("-", "")
				.substring(0, 16);// 随机字符串
		String timestamp = String.valueOf(System.currentTimeMillis() / 1000);// 时间戳
		// 根据JSSDK上面的规则进行计算，这里比较简单，我就手动写啦
		String[] ArrTmp = { "jsapi_ticket", "timestamp", "nonce", "url" };
		Arrays.sort(ArrTmp);
		StringBuffer sf = new StringBuffer();
		for (int i = 0; i < ArrTmp.length; i++) {
			sf.append(ArrTmp[i]);
		}
		// 3、将参数排序并拼接字符串
		String str = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + noncestr
				+ "&timestamp=" + timestamp + "&url=" + urlPath;
		// 4、将字符串进行sha1加密
		String signature = SignUtil.SHA1(str);
		tokenParam.setAppId(appId);
		tokenParam.setNoncestr(noncestr);
		tokenParam.setSignature(signature);
		tokenParam.setTimestamp(timestamp);
		return tokenParam;
	}
}
