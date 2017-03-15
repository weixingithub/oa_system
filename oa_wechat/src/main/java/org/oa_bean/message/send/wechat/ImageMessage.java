package org.oa_bean.message.send.wechat;

/** 
 * 图片消息 
 */ 
public class ImageMessage extends BaseMessage{
	 // 图片链接  
    private String PicUrl;  
  
    public String getPicUrl() {  
        return PicUrl;  
    }  
  
    public void setPicUrl(String picUrl) {  
        PicUrl = picUrl;  
    }

	@Override
	public String toString() {
		return "ImageMessage [PicUrl=" + PicUrl + "]";
	}  
    
}
