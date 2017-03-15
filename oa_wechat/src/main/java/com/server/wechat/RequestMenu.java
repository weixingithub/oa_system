package com.server.wechat;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.oa_bean.message.receive.wechat.Button;
import org.oa_bean.message.receive.wechat.Menu;
import org.oa_bean.message.receive.wechat.Root;
import org.oa_bean.message.receive.wechat.Sub_button;
import org.oa_common.http.HttpThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信菜单管理的方法类
 * 
 * @author Administrator
 *
 */
public class RequestMenu {
	// 创建菜单（POST） 限100（次/天）
	public final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	// 查询菜单（get）
	public final static String MENU_QUERY_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	// 删除菜单（get）
	public final static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	private static Logger log = LoggerFactory.getLogger(RequestMenu.class);

	/**
	 * 创建菜单
	 * 
	 * @return
	 */
	public static boolean creatorMenu(String accessToken,String jsonMenu ) {
		boolean flag = false;
		// 拼装创建菜单的url
		String url = MENU_CREATE_URL.replace("ACCESS_TOKEN", accessToken);
		// 调用接口创建菜单
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil.doPostSSL(
				url, jsonMenu));
		int errcode = jsonObject.getInt("errcode");
		if (errcode == 0) {
			flag = true;
		} else {
			flag = true;
			log.error("创建菜单失败 errcode:{} errmsg:{}",
					jsonObject.getInt("errcode"),
					jsonObject.getString("errmsg"));
		}
		return flag;
	}

	/**
	 * 删除菜单
	 * 
	 * @return
	 */
	public static boolean deleteMenu(String accessToken) {
		boolean flag = false;
		String url = MENU_QUERY_URL.replace("ACCESS_TOKEN", accessToken);
		// 调用接口查询菜单
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil
				.doGetSSL(url));
		int errcode = jsonObject.getInt("errcode");
		if (errcode == 0) {
			flag = true;
		} else {
			flag = true;
			log.error("删除菜单 errcode:{} errmsg:{}",
					jsonObject.getInt("errcode"),
					jsonObject.getString("errmsg"));
		}
		return flag;
	}

	/**
	 * 查询菜单
	 * 
	 * @return
	 */
	public static JSONObject QueryMenu(String accessToken) {
		String url = MENU_QUERY_URL.replace("ACCESS_TOKEN", accessToken);
		// 调用接口查询菜单
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil
				.doGetSSL(url));
		return jsonObject;
	}
	
	
	public static void main(String[] args) {
		Root root = null;
		JSONObject jsonObject = RequestMenu.QueryMenu("EWpf5-vFtCB9hpzAyTPtLG0CuHBQ1zPKVB6NoK3KK_4GPCbYSQR4rXiRGcH7QzRQb0y4Wphi2Bffp9XwzF6vgUUYi58jb2vOV2mz52imvGqrsiOrpu712p7dty7ps9FVTMCjAFAJCS");
		System.out.println(jsonObject.toString());
		if (null != jsonObject) {
			try {
				Map<String, Class<?>> classMap = new  HashMap<String, Class<?>>();  
				 classMap.put("menu", Menu.class);  
				 classMap.put("button", Button.class);  
				 classMap.put("sub_button", Sub_button.class);  
				 root = (Root) JSONObject.toBean(jsonObject,  Root.class, classMap);
			} catch (JSONException e) {
				log.error("查询菜单失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		 JSONArray jsonArray = new JSONArray();
		System.out.println(root.toString());
		int count = root.getMenu().getButton().size();
		System.out.println(count);
		for (int i = 0; i < count; i++) {
			System.out.println(root.getMenu().getButton().get(i).getName());
			for(int j = 0; j < root.getMenu().getButton().get(i).getSub_button().size(); j++){
				System.out.println(root.getMenu().getButton().get(i).getSub_button().get(j));
			}
		}
	}
}
