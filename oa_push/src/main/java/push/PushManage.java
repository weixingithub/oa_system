package push;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


import com.bean.push.PushBean;

import push.android.AndroidBroadcast;
import push.android.AndroidCustomizedcast;
import push.android.AndroidFilecast;
import push.android.AndroidGroupcast;
import push.android.AndroidUnicast;
import push.ios.IOSBroadcast;
import push.ios.IOSCustomizedcast;
import push.ios.IOSFilecast;
import push.ios.IOSGroupcast;
import push.ios.IOSUnicast;
/*
 * 消息推送
 */
public class PushManage {
	private String appkey = null;
	private String appMasterSecret = null;
	private String timestamp = null;
	private PushClient client = new PushClient();
	
	public PushManage(String appkey, String appMasterSecret) {
		super();
		this.appkey = appkey;
		this.appMasterSecret = appMasterSecret;
	}
//	public PushManage(String key, String secret) {
//		try {
//			appkey = key;
//			appMasterSecret = secret;
//		} catch (Exception e) {
//			e.printStackTrace();
//			System.exit(1);
//		}
//	}
	
	 
	/**
	 * 广播发送
	 * @throws Exception
	 */
	public Boolean sendAndroidBroadcast(PushBean push) throws Exception {
		AndroidBroadcast broadcast = new AndroidBroadcast(appkey,appMasterSecret);
		broadcast.setTicker(push.getTicker());
		broadcast.setTitle(push.getTitle());
		broadcast.setText(push.getText());
		broadcast.goAppAfterOpen();
		broadcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		broadcast.setProductionMode();
		// Set customized fields
		broadcast.setExtraField("test", "helloworld");
		return client.send(broadcast);
	}
	/**
	 * 单播发送
	 * @throws Exception
	 * weixin
	 */
	public Boolean sendAndroidUnicast(PushBean push ) throws Exception {
		AndroidUnicast unicast = new AndroidUnicast(appkey,appMasterSecret);
		// TODO Set your device token
		unicast.setDeviceToken(push.getDevicetoken());
		unicast.setTicker( push.getTicker());
		unicast.setTitle(push.getTitle());
		unicast.setText(push.getText());
		unicast.setBuilderId(1);//可选 默认为0，用于标识该通知采用的样式。 开发者在集成SDK时，可为 不同的id指定不同的通知样式。注意: 该字段从SDK-V1.3.0开始支持。
		unicast.setPlaySound(true);//是否发出声音
		unicast.setPlayLights(true);//是否闪灯
		unicast.setPlayVibrate(true);//是否震动
		unicast.setImg("http://imgstore.cdn.sogou.com/app/a/100540002/503008.png");
		unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		unicast.setProductionMode();
		// Set customized fields
		unicast.setExtraField("postId", push.getPostId());  // 可选 用户自定义key-value。只对"通知(display_type=notification)"生效。
													        //      可以配合通知到达后, 打开App, 打开URL, 打开Activity使用。
													        //      注意: SDK V1.2.3后开始支持
		return client.send(unicast);
	}
	/**
	 * 针对某一用户的重复推送消息
	 * @param ticker
	 * @param title
	 * @param text
	 * @return
	 * @throws Exception 
	 */
	public Boolean sendAndroidRepeat(String token,String ticker,String title,String text) throws Exception{
		AndroidUnicast unicast = new AndroidUnicast(appkey, appMasterSecret);
		unicast.setDeviceToken(token);
		unicast.setTicker(ticker);
		unicast.setTitle(title);
		unicast.setText(text);
		unicast.setBuilderId(1);//可选 默认为0，用于标识该通知采用的样式。 开发者在集成SDK时，可为 不同的id指定不同的通知样式。注意: 该字段从SDK-V1.3.0开始支持。
		unicast.setPlaySound(true);//是否发出声音
		unicast.setPlayLights(true);//是否闪灯
		unicast.setPlayVibrate(true);//是否震动
		unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		unicast.setProductionMode();
		return client.send(unicast);
		
	}
	/**
	 * 组播发送（筛选条件）
	 * @throws Exception
	 */
	public boolean sendAndroidGroupcast(PushBean push) throws Exception {
		AndroidGroupcast groupcast = new AndroidGroupcast(appkey,appMasterSecret);
		/*  TODO
		 *  Construct the filter condition:
		 *  "where": 
		 *	{
    	 *		"and": 
    	 *		[
      	 *			{"tag":"test"},
      	 *			{"tag":"Test"}
    	 *		]
		 *	}
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		JSONObject testTag = new JSONObject();
		JSONObject TestTag = new JSONObject();
		testTag.put("tag", push.getClass());
		TestTag.put("tag", "pl");
		tagArray.put(testTag);
		tagArray.put(TestTag);
		whereJson.put("and", tagArray);
		filterJson.put("where", whereJson);
		System.out.println(filterJson.toString());
		
		groupcast.setFilter(filterJson);
		groupcast.setTicker( "Android groupcast ticker");
		groupcast.setTitle(  "中文的title");
		groupcast.setText(   "Android groupcast text");
		groupcast.setStartTime("");//当时发送时间 格式为yyyy-mm-dd hh:mm:ss
		groupcast.setExpireTime("");//消息过期时间
		groupcast.goAppAfterOpen();
		groupcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		groupcast.setProductionMode();
		return client.send(groupcast);
	}
	/**
	 * 自定义发送
	 * @throws Exception
	 */
	public void sendAndroidCustomizedcast(PushBean push) throws Exception {
		AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appkey,appMasterSecret);
		// TODO Set your alias here, and use comma to split them if there are multiple alias.
		// And if you have many alias, you can also upload a file containing these alias, then 
		// use file_id to send customized notification.
		customizedcast.setAlias("plalias", "pl");
		customizedcast.setTicker(push.getTicker());
		customizedcast.setTitle(push.getTitle());
		customizedcast.setText(push.getText());
		customizedcast.goAppAfterOpen();
		customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		customizedcast.setProductionMode();//正常模式
		client.send(customizedcast);
	}
	/**
	 * 自定义文件播发送
	 * @throws Exception
	 */
	public void sendAndroidCustomizedcastFile() throws Exception {
		AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appkey,appMasterSecret);
		// TODO Set your alias here, and use comma to split them if there are multiple alias.
		// And if you have many alias, you can also upload a file containing these alias, then 
		// use file_id to send customized notification.
		String fileId = client.uploadContents(appkey,appMasterSecret,"aa"+"\n"+"bb"+"\n"+"alias");
		customizedcast.setFileId(fileId, "alias_type");
		customizedcast.setTicker( "Android customizedcast ticker");
		customizedcast.setTitle(  "中文的title");
		customizedcast.setText(   "Android customizedcast text");
		customizedcast.goAppAfterOpen();
		customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device. 
		// For how to register a test device, please see the developer doc.
		customizedcast.setProductionMode();
		client.send(customizedcast);
	}
	/**
	 * 文件播发送
	 * @throws Exception
	 */
	public boolean sendAndroidFilecast(PushBean push) throws Exception {
		AndroidFilecast filecast = new AndroidFilecast(appkey,appMasterSecret);
		// TODO upload your device tokens, and use '\n' to split them if there are multiple tokens 
		//先将要发送的设备以文件形式打包上传，得到文件id,在发送消息
		List<String> tokens= push.getTokens();
		StringBuilder device_token=new StringBuilder();
        boolean flag=false;
        for (String string : tokens) {
            if (flag) {
            	device_token.append("\n");
            }else {
                flag=true;
            }
            device_token.append(string);
        }
        System.out.println("start"+device_token+"要推送的用户token");
		String fileId = client.uploadContents(appkey,appMasterSecret,device_token.toString());
		filecast.setFileId( fileId);
		filecast.setTicker(push.getTicker());
		filecast.setTitle(push.getTitle());
		filecast.setText(push.getText());
		filecast.goAppAfterOpen();
		filecast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		filecast.setExtraField("updatealarm", push.getFlag());
		return client.send(filecast);
	}
	/**
	 * 测试发送
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// TODO set your appkey and master secret here
		PushManage demo = new PushManage("58087981f29d9869f00000e2", "fqrhykwgmcpa8lni8wrpumk8nvc6sqpa");
		try {
			PushBean push = new PushBean();
			push.setText("plppk");
			push.setTitle("注意");
			push.setTicker("hihi");
//			List<String> listNew = new ArrayList<>();
//			listNew.add("AkEKH7EqCy1DoRRB3ZYSgTh7pEs94lyiv6_f7FTUm-dU");
//			listNew.add("Aq2YgvKyd4txB099Gc-pEAyUKIsmgr8VlbZGQNctfCRw");
//			listNew.add("1");
//			listNew.add("pp");
//			push.setTokens(listNew);
//			push.setFlag("收到了吗？");
//			demo.sendAndroidFilecast(push);
//          demo.sendAndroidUnicast(push);//单播ok
//			demo.sendAndroidCustomizedcastFile();
//          demo.sendAndroidBroadcast();
//			demo.sendAndroidGroupcast();
//			demo.sendAndroidCustomizedcast();
			/* TODO these methods are all available, just fill in some fields and do the test
			 * demo.sendAndroidCustomizedcastFile();
			 * demo.sendAndroidBroadcast();
			 * demo.sendAndroidGroupcast();
			 * demo.sendAndroidCustomizedcast();
			 * demo.sendAndroidFilecast();
			 * 
			 * demo.sendIOSBroadcast();
			 * demo.sendIOSUnicast();
			 * demo.sendIOSGroupcast();
			 * demo.sendIOSCustomizedcast();
			 * demo.sendIOSFilecast();
			 */
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	/**
	 * 广播发送
	 * @throws Exception
	 */
	public void sendIOSBroadcast() throws Exception {
		IOSBroadcast broadcast = new IOSBroadcast(appkey,appMasterSecret);

		broadcast.setAlert("IOS 广播测试");
		broadcast.setBadge( 0);
		broadcast.setSound( "default");
		// TODO set 'production_mode' to 'true' if your app is under production mode
		broadcast.setTestMode();
		// Set customized fields
		broadcast.setCustomizedField("test", "helloworld");
		client.send(broadcast);
	}
	
	public void sendIOSUnicast() throws Exception {
		IOSUnicast unicast = new IOSUnicast(appkey,appMasterSecret);
		// TODO Set your device token
		unicast.setDeviceToken( "xx");
		unicast.setAlert("IOS 单播测试");
		unicast.setBadge( 0);
		unicast.setSound( "default");
		// TODO set 'production_mode' to 'true' if your app is under production mode
		unicast.setTestMode();
		// Set customized fields
		unicast.setCustomizedField("test", "helloworld");
		client.send(unicast);
	}
	
	public void sendIOSGroupcast() throws Exception {
		IOSGroupcast groupcast = new IOSGroupcast(appkey,appMasterSecret);
		/*  TODO
		 *  Construct the filter condition:
		 *  "where": 
		 *	{
    	 *		"and": 
    	 *		[
      	 *			{"tag":"iostest"}
    	 *		]
		 *	}
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		JSONObject testTag = new JSONObject();
		testTag.put("tag", "iostest");
		tagArray.put(testTag);
		whereJson.put("and", tagArray);
		filterJson.put("where", whereJson);
		System.out.println(filterJson.toString());
		
		// Set filter condition into rootJson
		groupcast.setFilter(filterJson);
		groupcast.setAlert("IOS 组播测试");
		groupcast.setBadge( 0);
		groupcast.setSound( "default");
		// TODO set 'production_mode' to 'true' if your app is under production mode
		groupcast.setTestMode();
		client.send(groupcast);
	}
	
	public void sendIOSCustomizedcast() throws Exception {
		IOSCustomizedcast customizedcast = new IOSCustomizedcast(appkey,appMasterSecret);
		// TODO Set your alias and alias_type here, and use comma to split them if there are multiple alias.
		// And if you have many alias, you can also upload a file containing these alias, then 
		// use file_id to send customized notification.
		customizedcast.setAlias("alias", "alias_type");
		customizedcast.setAlert("IOS 个性化测试");
		customizedcast.setBadge( 0);
		customizedcast.setSound( "default");
		// TODO set 'production_mode' to 'true' if your app is under production mode
		customizedcast.setTestMode();
		client.send(customizedcast);
	}
	
	public void sendIOSFilecast() throws Exception {
		IOSFilecast filecast = new IOSFilecast(appkey,appMasterSecret);
		// TODO upload your device tokens, and use '\n' to split them if there are multiple tokens 
		String fileId = client.uploadContents(appkey,appMasterSecret,"aa"+"\n"+"bb");
		filecast.setFileId( fileId);
		filecast.setAlert("IOS 文件播测试");
		filecast.setBadge( 0);
		filecast.setSound( "default");
		// TODO set 'production_mode' to 'true' if your app is under production mode
		filecast.setTestMode();
		client.send(filecast);
	}
	
	
	}
	


