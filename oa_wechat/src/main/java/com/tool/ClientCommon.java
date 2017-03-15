package com.tool;

import java.util.Date;

import org.oa_bean.message.send.wechat.TextMessage;
import org.oa_bean.wechat.ReceiveXmlEntity;

/**
 *
 * 消息模板
 *
 */
public class ClientCommon {
  /** 
    * 返回消息类型：文本 
    */  
   public static final String RESP_MESSAGE_TYPE_TEXT = "text";  
 
   /** 
    * 返回消息类型：音乐 
    */  
   public static final String RESP_MESSAGE_TYPE_MUSIC = "music";  
 
   /** 
    * 返回消息类型：图文 
    */  
   public static final String RESP_MESSAGE_TYPE_NEWS = "news";  
   
   /** 
    * 返回消息类型：地理位置
    */  
   public static final String RESP_MESSAGE_TYPE_LOCATION = "location";  
   /** 
    * 请求消息类型：文本 
    */  
   public static final String REQ_MESSAGE_TYPE_TEXT = "text";  
 
   /** 
    * 请求消息类型：图片 
    */  
   public static final String REQ_MESSAGE_TYPE_IMAGE = "image";  
 
   /** 
    * 请求消息类型：链接 
    */  
   public static final String REQ_MESSAGE_TYPE_LINK = "link";  
 
   /** 
    * 请求消息类型：地理位置 
    */  
   public static final String REQ_MESSAGE_TYPE_LOCATION = "location";  
 
   /** 
    * 请求消息类型：音频 
    */  
   public static final String REQ_MESSAGE_TYPE_VOICE = "voice";  
 
   /** 
    * 请求消息类型：推送 
    */  
   public static final String REQ_MESSAGE_TYPE_EVENT = "event";  
 
   /** 
    * 事件类型：subscribe(订阅) 
    */  
   public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";  
 
   /** 
    * 事件类型：unsubscribe(取消订阅) 
    */  
   public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";  
 
   /** 
    * 事件类型：CLICK(自定义菜单点击事件) 
    */  
   public static final String EVENT_TYPE_CLICK = "CLICK"; 
   /** 
    * 错误信息
    */  
   public static final String ERROR_MESSAGE = "对不起，服务现在有误请稍后重试！"; 
   /** 
    * 订阅信息 
    */  
   public static final String SUBSCRIBE_MESSAGE = "感谢您的关注！"; 
   /** 
    * 暂无信息
    */  
   public static final String NULL_MESSAGE = "非常抱歉，目前还没有信息发布，感谢您的关注。"; 
   /** 
    * 暂无功能
    */  
   public static final String NULL_FUNCTION = "非常抱歉该功能正在开发中。"; 
   
   public static final String SERVER_WECHAT = "WECHAT"; 
   
   public static final String WECHAT_TOKEN = "weixin"; 
   
   public static String ErrorMessage(ReceiveXmlEntity xmlEntity,int a){
	   String content = "";
	   switch (a) {
		case 0 :
			content = SUBSCRIBE_MESSAGE ;
			break;
		case 1 :
			content = ERROR_MESSAGE ;
			break;
		case 2 :
			content = NULL_MESSAGE ;
			break;
		case 3 :
			content = NULL_FUNCTION ;
			break;
		}
	   TextMessage textMessage = new TextMessage();
       textMessage.setToUserName(xmlEntity.getFromUserName());
       textMessage.setFromUserName(xmlEntity.getToUserName());
       textMessage.setCreateTime(new Date().getTime());
       textMessage.setMsgType(RESP_MESSAGE_TYPE_TEXT);
       textMessage.setFuncFlag(0);
       textMessage.setContent(content);
	   return  WechatMessageUtil.toXml(textMessage);
   }
}
