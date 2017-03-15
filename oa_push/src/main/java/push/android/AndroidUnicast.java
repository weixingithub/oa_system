package push.android;

import push.AndroidNotification;
/**
 * 单播发送
 * @author Administrator
 * @date 2016年10月19日
 * @company
 * AndroidUnicast.java
 */
public class AndroidUnicast extends AndroidNotification {
	public AndroidUnicast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "unicast");	
	}
	
	public void setDeviceToken(String token) throws Exception {
    	setPredefinedKeyValue("device_tokens", token);//设备唯一标识
    }

}