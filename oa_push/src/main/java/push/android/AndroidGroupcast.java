package push.android;

import org.json.JSONObject;

import push.AndroidNotification;
/**
 * 组播发送
 * @author Administrator
 * @date 2016年10月19日
 * @company
 * AndroidGroupcast.java
 */
public class AndroidGroupcast extends AndroidNotification {
	public AndroidGroupcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "groupcast");	
	}
	
	public void setFilter(JSONObject filter) throws Exception {
    	setPredefinedKeyValue("filter", filter);//filter为筛选条件jsonobject对象
    }
}
