package push.android;
import push.AndroidNotification;
/**
 * 自定义发送
 * @author Administrator
 * @date 2016年10月19日
 * @company
 * AndroidCustomizedcast.java
 */
public class AndroidCustomizedcast extends AndroidNotification {
	public AndroidCustomizedcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "customizedcast");	
	}
	/**
	 * alias由sdk端调用addAlias接口进行添加账号
	 * @param alias
	 * @param aliasType
	 * @throws Exception
	 */
	public void setAlias(String alias,String aliasType) throws Exception {
    	setPredefinedKeyValue("alias", alias);
    	setPredefinedKeyValue("alias_type", aliasType);
    }
			
	public void setFileId(String fileId,String aliasType) throws Exception {
    	setPredefinedKeyValue("file_id", fileId);
    	setPredefinedKeyValue("alias_type", aliasType);
    }

}
