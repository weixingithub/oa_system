package com.server.wechat;


import java.util.Date;

import org.oa_bean.message.send.wechat.TextMessage;
import org.oa_bean.wechat.ReceiveXmlEntity;
import org.oa_bean.wechat.Wechat;

import com.tool.ClientCommon;
/**
 * 接收微信的消息
 * @author Administrator
 *
 */
public class ResponseReceiveMessage {
	 /**
	 * 类型判断
	 * @param xmlEntity
	 * @param json
	 * @return
	 */ 
	 public String processRequest(ReceiveXmlEntity xmlEntity,Wechat weChat) {
		
		String result = "";
		TextMessage textMessage = new TextMessage();
        textMessage.setToUserName(xmlEntity.getFromUserName());
        textMessage.setFromUserName(xmlEntity.getToUserName());
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(ClientCommon.RESP_MESSAGE_TYPE_TEXT);
        textMessage.setFuncFlag(0);
        
		if(ClientCommon.REQ_MESSAGE_TYPE_TEXT.endsWith(xmlEntity.getMsgType())){ //文本
			
			result = textWechatMag(xmlEntity,weChat);
			
		}else if(ClientCommon.REQ_MESSAGE_TYPE_IMAGE.endsWith(xmlEntity.getMsgType())){ //图片
			
			result = imageWechatMag(xmlEntity,weChat);
			
		}else if(ClientCommon.REQ_MESSAGE_TYPE_LOCATION.endsWith(xmlEntity.getMsgType())){ //地理位置
			
			result = locationWechatMag(xmlEntity,weChat);
			
		}else if(ClientCommon.REQ_MESSAGE_TYPE_LINK.endsWith(xmlEntity.getMsgType())){ //链接
			
			result = linkWechatMag(xmlEntity,weChat);
			
		}else if(ClientCommon.REQ_MESSAGE_TYPE_VOICE.endsWith(xmlEntity.getMsgType())){//音频
			
			result = textWechatMag(xmlEntity,weChat);
			
		}else if (ClientCommon.REQ_MESSAGE_TYPE_EVENT.endsWith(xmlEntity.getMsgType())) {// 事件类型  
			
			result = eventWechatMag(xmlEntity,weChat);
	    }  
		return result;
		
	 }
	 
	/**
	 * 文本消息
	 * @param xmlEntity
	 * @param json
	 * @return
	 */
	public String textWechatMag(ReceiveXmlEntity xmlEntity,Wechat weChat){
		String result = "";
		return result;
	}
	/**
	 * 图片消息
	 * @param xmlEntity
	 * @param json
	 * @return
	 */
	public String imageWechatMag(ReceiveXmlEntity xmlEntity,Wechat weChat){
		String result = "";
		return result;
	}
	/**
	 * 地理位置消息
	 * @param xmlEntity
	 * @param json
	 * @return
	 */
	public String locationWechatMag(ReceiveXmlEntity xmlEntity,Wechat weChat){
		String result = "";
		return result;
	}
	/**
	 * 链接消息
	 * @param xmlEntity
	 * @param json
	 * @return
	 */
	public String linkWechatMag(ReceiveXmlEntity xmlEntity,Wechat weChat){
		String result = "";
		return result;
	}
	/**
	 * 语音消息
	 * @param xmlEntity
	 * @param json
	 * @return
	 */
	public String voiceWechatMag(ReceiveXmlEntity xmlEntity,Wechat weChat){
		String result = "";
		return result;
	}
	/**
	 * 小视频消息
	 * @param xmlEntity
	 * @param json
	 * @return
	 */
	public String videoWechatMag(ReceiveXmlEntity xmlEntity,Wechat weChat){
		String result = "";
		return result;
	}
	 /**
	 * 接收事件推送
	 * @param xmlEntity
	 * @param json
	 * @return
	 */
	public String eventWechatMag(ReceiveXmlEntity xmlEntity,Wechat weChat){
		String result = "";
        if (ClientCommon.EVENT_TYPE_SUBSCRIBE.endsWith(xmlEntity.getEvent())) {   // 订阅  
        	        	
        } else if (ClientCommon.EVENT_TYPE_UNSUBSCRIBE.endsWith(xmlEntity.getEvent())) {   // 取消订阅  
            // 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
   
		} else if(ClientCommon.REQ_MESSAGE_TYPE_LOCATION.equals(xmlEntity.getEvent())){ //上报地理位置
			
			
			
		} else if(ClientCommon.EVENT_TYPE_CLICK.equals(xmlEntity.getEvent())){ 
			//事件KEY值，与自定义菜单接口中KEY值对应
			result = eventClick(xmlEntity,weChat);
			
		}
		return result;
	}
	/**
	 * 点击菜单拉取消息时的事件推送
	 * @param xmlEntity
	 * @return
	 */
	public String eventClick(ReceiveXmlEntity xmlEntity,Wechat weChat){
		String result = "";
		return result;
	}
}
