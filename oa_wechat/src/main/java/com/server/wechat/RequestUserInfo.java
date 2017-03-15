package com.server.wechat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.oa_bean.wechat.SNSUserInfo;
import org.oa_bean.wechat.UserInfo;
import org.oa_common.http.HttpThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信获取关注用户的方法类
 * @author Administrator
 *
 */
public class  RequestUserInfo {
	// 获取用户列表
	public final static String USER_LIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
	// 批量获取用户基本信息（POST）
	public final static String USERTINFO_BARCH_url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
	// 获取用户基本信息（包括UnionID机制）(GET)
	public final static String USERTINFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	// 获取授权用户基本信息（包括UnionID机制）(GET)
	public final static String SNSUSERTINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	
	private static Logger log = LoggerFactory.getLogger(RequestUserInfo.class);
	/**
	 * 通过网页授权获取用户信息
	 * 
	 * @param accessToken
	 *            网页授权接口调用凭证
	 * @param openId
	 *            用户标识
	 * @return SNSUserInfo
	 */
	public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
		SNSUserInfo snsUserInfo = null;
		// 拼接请求地址
		String requestUrl = SNSUSERTINFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
		// 通过网页授权获取用户信息
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil
				.doGetSSL(requestUrl));
		if (null != jsonObject) {
			try {
				snsUserInfo = new SNSUserInfo();
				// 用户的标识
				snsUserInfo.setOpenId(jsonObject.getString("openid"));
				// 昵称
				snsUserInfo.setNickname(jsonObject.getString("nickname"));
				// 性别（1是男性，2是女性，0是未知）
				snsUserInfo.setSex(jsonObject.getInt("sex"));
				// 用户所在国家
				snsUserInfo.setCountry(jsonObject.getString("country"));
				// 用户所在省份
				snsUserInfo.setProvince(jsonObject.getString("province"));
				// 用户所在城市
				snsUserInfo.setCity(jsonObject.getString("city"));
				// 用户头像
				snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				// 用户特权信息
				snsUserInfo.setPrivilegeList(JSONArray.toList(
						jsonObject.getJSONArray("privilege"), List.class));
			} catch (Exception e) {
				snsUserInfo = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return snsUserInfo;
	}
	/**
	 * 获取用户信息
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param openId
	 *            用户标识
	 * @return UserInfo
	 */
	public static UserInfo getUserInfo(String accessToken, String openId) {
		UserInfo userInfo = null;
		// 拼接请求地址
		String requestUrl = USERTINFO_URL.replace("ACCESS_TOKEN", accessToken)
				.replace("OPENID", openId);
		// 获取用户信息
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil
				.doGetSSL(requestUrl));
		if (null != jsonObject) {
			try {
				userInfo = new UserInfo();
				// 用户的标识
				userInfo.setOpenId(jsonObject.getString("openid"));
				// 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
				userInfo.setSubscribe(jsonObject.getInt("subscribe"));
				// 用户关注时间
				userInfo.setSubscribeTime(jsonObject
						.getString("subscribe_time"));
				// 昵称
				userInfo.setNickname(jsonObject.getString("nickname"));
				// 用户的性别（1是男性，2是女性，0是未知）
				userInfo.setSex(jsonObject.getInt("sex"));
				// 用户所在国家
				userInfo.setCountry(jsonObject.getString("country"));
				// 用户所在省份
				userInfo.setProvince(jsonObject.getString("province"));
				// 用户所在城市
				userInfo.setCity(jsonObject.getString("city"));
				// 用户的语言，简体中文为zh_CN
				userInfo.setLanguage(jsonObject.getString("language"));
				// 用户头像
				userInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
				 // 用户所在的分组ID 
				userInfo.setGroupid(jsonObject.getString("groupid"));
			} catch (Exception e) {
				if (0 == userInfo.getSubscribe()) {
					log.error("用户{}已取消关注", userInfo.getOpenId());
				} else {
					int errorCode = jsonObject.getInt("errcode");
					String errorMsg = jsonObject.getString("errmsg");
					log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode,
							errorMsg);
				}
			}
		}
		return userInfo;
	}
	/**
	 * 获取用户列表
	 * @param accessToken
	 * @param openId
	 * @return
	 * 附：关注者数量超过10000时
	 * 当公众号关注者数量超过10000时，可通过填写next_openid的值，从而多次拉取列表的方式来满足需求。
	 * 具体而言，就是在调用接口时，将上一次调用得到的返回中的next_openid值，作为下一次调用中的next_openid值。 
	 */
	public static Map<String,Object> getUserInfoList(String accessToken, String openId) {
		Map<String,Object> map = new HashMap<String, Object>();
		// 拼接请求地址
		String requestUrl = USER_LIST_URL.replace("ACCESS_TOKEN", accessToken);
		if(openId!=null&&"".equals(openId)){
			requestUrl +="&next_openid="+openId;
		}
		// 获取用户信息
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil
				.doGetSSL(requestUrl));
		if (null != jsonObject) {
			try {
				map.put("total", jsonObject.getString("total"));
				map.put("count", jsonObject.getString("count"));
				map.put("data", jsonObject.getString("data"));
				map.put("next_openid", jsonObject.getString("next_openid"));
			} catch (Exception e) {
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取用户列表失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return map;
	}
	/**
	 * 批量获取用户信息
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param json
	 *            用户标识集合
	 * @return jsonObject
	 * POST数据格式：json
	 * POST数据例子： {"user_list": [{ "openid": "otvxTs4dckWG7imySrJd6jSi0CWE","lang": "zh-CN"}, {"openid": "otvxTs_JZ6SEiP0imdhpi50fuSZg","lang": "zh-CN"}]}
	 * 开发者可通过该接口来批量获取用户基本信息。最多支持一次拉取100条。 
	 */
	public static List<UserInfo> getBarchUserInfo(String accessToken, String json) {
		List<UserInfo> listUser = new ArrayList<UserInfo>();
		// 拼接请求地址
		String apiUrl = USERTINFO_BARCH_url.replace("ACCESS_TOKEN", accessToken);
		// 获取用户信息
		JSONObject jsonObjects = JSONObject.fromObject(HttpThreadUtil.doGetSSL(apiUrl));
		if (null != jsonObjects) {
			try {
				String arr = jsonObjects.getString("user_info_list");
				JSONArray jsonArr = JSONArray.fromObject(arr);
				for(int i=0;i<jsonArr.size();i++){
					UserInfo userInfo = new UserInfo();
					JSONObject jsonObject = (JSONObject)jsonArr.get(i);
					if(0!=jsonObject.getInt("subscribe")){
						// 用户的标识
						userInfo.setOpenId(jsonObject.getString("openid"));
						// 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
						userInfo.setSubscribe(jsonObject.getInt("subscribe"));
						// 用户关注时间
						userInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
						// 昵称
						userInfo.setNickname(jsonObject.getString("nickname"));
						// 用户的性别（1是男性，2是女性，0是未知）
						userInfo.setSex(jsonObject.getInt("sex"));
						// 用户所在国家
						userInfo.setCountry(jsonObject.getString("country"));
						// 用户所在省份
						userInfo.setProvince(jsonObject.getString("province"));
						// 用户所在城市
						userInfo.setCity(jsonObject.getString("city"));
						// 用户的语言，简体中文为zh_CN
						userInfo.setLanguage(jsonObject.getString("language"));
						// 用户头像
						userInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
						// 用户所在的分组ID 
						userInfo.setGroupid(jsonObject.getString("groupid"));
					}else{
						// 用户的标识
						userInfo.setOpenId(jsonObject.getString("openid"));
						// 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
						userInfo.setSubscribe(jsonObject.getInt("subscribe"));
					}
					listUser.add(userInfo);
				}
			} catch (Exception e) {
				int errorCode = jsonObjects.getInt("errcode");
				String errorMsg = jsonObjects.getString("errmsg");
				log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode,
						errorMsg);
			}
		}
		return listUser;
	}
	public static void main(String args[]) {
		String accessToken = "CPwuxWSZfv2sPUs3iogbiY0RW_ZF8gzxCgM16yfuAi2RiaCYnUMmYVBAACs3cPbSRstGL649Q7HHs5kas81eXOYsg4DgYYfcmKBk9g1URQ4EQTeAHAUGJ";
		String openId="o2ljjvo5lf9kRJ-puLFFgJ3VOXmA";
		Map<String, Object> map= getUserInfoList(accessToken, openId);
		System.out.println(map.toString());
		System.out.println(getUserInfo(accessToken, openId).toString());
	}
	
}
