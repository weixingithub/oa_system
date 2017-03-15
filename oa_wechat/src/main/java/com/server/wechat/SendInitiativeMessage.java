package com.server.wechat;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.oa_common.http.HttpThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 发送主动消息
 * @author Administrator
 *
 */
public class SendInitiativeMessage {
	private static Logger log = LoggerFactory.getLogger(SendInitiativeMessage.class);
	private final static String SEND_GROUP_MESSAGE_URL ="https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=ACCESS_TOKEN";
	private final static String SEND_OPENID_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/mass/send?access_token=ACCESS_TOKEN";
	/**
	 * 根据分组进行群发
	 * @param accessToken
	 * @param json
	 * @return
	 */
	public static JSONObject sendGroupMessage(String accessToken, String json){
		String apiUrl = SEND_GROUP_MESSAGE_URL.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil.doPostSSL(apiUrl, json));
		 if (null != jsonObject) {
				try {
					String msg_id =  jsonObject.getString("msg_id");
				} catch (JSONException e) {
					log.error("分组进行群发失败 errcode:{} errmsg:{}",
							jsonObject.getInt("errcode"),
							jsonObject.getString("errmsg"));
				}
			}
		return jsonObject;
	}
	/**
	 * 根据OpenID列表群发
	 * @param accessToken
	 * @param json
	 * @return
	 */
	public static JSONObject sendOpenIDMessage(String accessToken, String json){
		String apiUrl = SEND_OPENID_MESSAGE_URL.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil.doPostSSL(apiUrl, json));
		 if (null != jsonObject) {
				try {
					String msg_id =  jsonObject.getString("msg_id");
				} catch (JSONException e) {
					log.error("OpenID列表群发失败 errcode:{} errmsg:{}",
							jsonObject.getInt("errcode"),
							jsonObject.getString("errmsg"));
				}
			}
		return jsonObject;
	}
	  /**
     * 发送图文到默认的群组用户
     * @param accessToken 
     * @param media_id 
     * @return
     */
    public static JSONObject sendDefaultGroupNewsMessage(String accessToken,String media_id){
    	String json ="{\"filter\":{\"is_to_all\":false,\"group_id\":\"0\"},"
    				+ " \"mpnews\":{\"media_id\":\""+media_id+"\"},"
    				+ "\"msgtype\":\"mpnews\"}";
    	 
    	return sendGroupMessage(accessToken, json);
    } 
    /**
     * 发送文本到默认的群组用户
     * @param accessToken
     * @param media_id
     * @return
     */
    public static JSONObject sendDefaultGroupTextMessage(String accessToken,String count){
    	String json ="{\"filter\":{\"is_to_all\":false,\"group_id\":\"0\"},"
    				+ " \"text\":{\"content\":\""+count+"\" },"
    				+ "\"msgtype\":\"text\"}";
    	 
    	return sendGroupMessage(accessToken, json);
    } 
	public static void main(String[] args) {
		String accessToken="TQP4UZSI9iVomkOlK0bZ_clLV6NZ0OGhbkf3QC9f3fzpd4TSn5xS5m4XJmoBnchCCa71o2F9vdJj-_r5JKaGtAQZxddRpHrkvsok4ZypPZxDse4lTJLaMUSq4bnzUvNwQHWdAIAARG";
    	String media_id="HZVIaDBr1h7uwUaZt5DrX4DBvAxIVrko6mX-mulncpA";
    	String json ="{\"filter\":{\"is_to_all\":ture,\"group_id\":\"0\"},"
				+ " \"mpnews\":{\"media_id\":\""+media_id+"\"},"
				+ "\"msgtype\":\"mpnews\"}";
    	JSONObject msgResult = SendInitiativeMessage.sendGroupMessage(accessToken, json);
		System.out.println(msgResult.toString());
	}
}
