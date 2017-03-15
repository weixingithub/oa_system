package push.android;

import push.AndroidNotification;

/**
 * 广播发送
 * @author Administrator
 * @date 2016年10月19日
 * @company
 * AndroidBroadcast.java
 */
public class AndroidBroadcast extends AndroidNotification {
	public AndroidBroadcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "broadcast");	
	}
}
