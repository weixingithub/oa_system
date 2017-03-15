package com.server.wechat;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.oa_bean.wechat.Group;
import org.oa_common.http.HttpThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信用户分组管理
 * 
 * @author Administrator
 *
 */
public class RequestGroup {
	// 创建分组(post) json
	public final static String GROUP_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
	// 查询所有分组 （GET）
	public final static String GROUP_QUERYALL_URL = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
	// 查询用户所在分组 （POST）
	public final static String GROUP_QUERY_BY_USER_URL = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";
	// 修改分组名 （POST）
	public final static String GROUP_UPDATE_NAME_URL = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
	// 移动用户分组（POST）
	public final static String GROUP_MOVE_BY_USER_URL = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
	// 批量移动用户分组（POST）
	public final static String GROUP_BARCH_MOVE_BY_USER_URL = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=ACCESS_TOKEN";
	// 删除分组（POST）
	public final static String GROUP_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=ACCESS_TOKEN";
	
	private static Logger log = LoggerFactory.getLogger(RequestGroup.class);

	/**
	 * 创建分组 一个公众账号，最多支持创建100个分组。 POST数据格式：json
	 * POST数据例子：{"group":{"name":"test"}}
	 * 
	 * @return
	 */
	public static Group createGroup(String accessToken, String json) {
		Group group = null;
		String url = GROUP_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil.doPostSSL(
				url, json));
		if (null != jsonObject) {
			try {
				group = (Group) JSONObject.toBean(jsonObject, Group.class);
			} catch (JSONException e) {
				group = null;
				// 获取token失败
				log.error("创建Group失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return group;
	}

	/**
	 * 查询所有分组
	 * 
	 * @return
	 */
	public static List<Group> findAllListGroup(String accessToken) {
		List<Group> list = new ArrayList<Group>();
		String url = GROUP_QUERYALL_URL.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObjects = JSONObject.fromObject(HttpThreadUtil.doGetSSL(url));
		if (null != jsonObjects) {
			try {
				String arr = jsonObjects.getString("groups");
				JSONArray jsonArr = JSONArray.fromObject(arr);
				for (int i = 0; i < jsonArr.size(); i++) {
					JSONObject jsonObject = (JSONObject) jsonArr.get(i);
					Group group = (Group) JSONObject.toBean(jsonObject,
							Group.class);
					list.add(group);
				}
			} catch (JSONException e) {
				log.error("查询GroupList失败 errcode:{} errmsg:{}",
						jsonObjects.getInt("errcode"),
						jsonObjects.getString("errmsg"));
			}
		}
		return list;
	}

	/**
	 * 查询用户所在分组 POST数据格式：json POST数据例子：{"openid":"od8XIjsmk6QdVTETa9jLtGWA6KBc"}
	 * 
	 * @return
	 */
	public static Group findGroupByUser(String accessToken, String json) {
		Group group = null;
		String url = GROUP_QUERY_BY_USER_URL.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil.doPostSSL(
				url, json));
		if (null != jsonObject) {
			try {
				group = (Group) JSONObject.toBean(jsonObject, Group.class);
			} catch (JSONException e) {
				group = null;
				log.error("查询用户所在分组失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return group;
	}

	/**
	 * 修改分组名 POST数据格式：json POST数据例子：{"group":{"id":108,"name":"test2_modify2"}}
	 * 
	 * @return
	 */
	public static boolean updateGroupName(String accessToken, String json) {
		boolean flag = false;
		String url = GROUP_UPDATE_NAME_URL.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil.doPostSSL(
				url, json));
		int errcode = jsonObject.getInt("errcode");
		if (errcode == 0) {
			flag = true;
		} else {
			flag = true;
			log.error("修改分组名失败 errcode:{} errmsg:{}",
					jsonObject.getInt("errcode"),
					jsonObject.getString("errmsg"));
		}
		return flag;
	}

	/**
	 * 移动用户分组 POST数据格式：json
	 * POST数据例子：{"openid":"oDF3iYx0ro3_7jD4HFRDfrjdCM58","to_groupid":108}
	 * 
	 * @return
	 */
	public static boolean moveUserGroup(String accessToken, String json) {
		boolean flag = false;
		String url = GROUP_MOVE_BY_USER_URL.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil.doPostSSL(
				url, json));
		int errcode = jsonObject.getInt("errcode");
		if (errcode == 0) {
			flag = true;
		} else {
			flag = true;
			log.error("移动用户分组失败 errcode:{} errmsg:{}",
					jsonObject.getInt("errcode"),
					jsonObject.getString("errmsg"));
		}
		return flag;
	}

	/**
	 * 批量移动用户分组 POST数据格式：json
	 * POST数据例子：{"openid_list":["oDF3iYx0ro3_7jD4HFRDfrjdCM58"
	 * ,"oDF3iY9FGSSRHom3B-0w5j4jlEyY"],"to_groupid":108}
	 * 
	 * @return
	 */
	public static boolean batchMoveUserGroup(String accessToken, String json) {
		boolean flag = false;
		String url = GROUP_BARCH_MOVE_BY_USER_URL.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil.doPostSSL(
				url, json));
		int errcode = jsonObject.getInt("errcode");
		if (errcode == 0) {
			flag = true;
		} else {
			flag = true;
			log.error("批量移动用户分组失败 errcode:{} errmsg:{}",
					jsonObject.getInt("errcode"),
					jsonObject.getString("errmsg"));
		}
		return flag;
	}

	/**
	 * 删除分组 POST数据格式：json POST数据例子：{"group":{"id":108}}
	 * 
	 * @return
	 */
	public static boolean deleteGroup(String accessToken, String json) {
		boolean flag = false;
		String url = GROUP_DELETE_URL.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil.doPostSSL(
				url, json));
		int errcode = jsonObject.getInt("errcode");
		if (errcode == 0) {
			flag = true;
		} else {
			flag = true;
			log.error("删除分组失败 errcode:{} errmsg:{}",
					jsonObject.getInt("errcode"),
					jsonObject.getString("errmsg"));
		}
		return flag;
	}
	public static void main(String[] args) {
		//Token token =  RequestToken.getToken("wxe364a617a210dace", "d73909f433f9931f88bcb2b119c7f6ab");
    	//System.out.println(token.getAccessToken());
		String accessToken = "3956KVMGy4AEVshGggpOqrdesyth_nllWWfA2O0uhWiJJ6gFa15y1_QBfbCi3ceEn7kVt-vhu4pu_3BtfXW8NXFEcG5PaR-C64QwP9V82z0ZZMhADAEER";
		System.out.println(RequestGroup.findAllListGroup(accessToken).toString());
	}
}
