package com.dao.wechat;

import org.oa_bean.massmsg.MassMsg;
import org.oa_bean.wechat.WechatArticle;

public interface WechatSendService {
	
	/**
	 * 群发图文消息到默认的群组
	 * @param wechatArticle
	 * @param massMsg
	 * @param pathUrl
	 * @param netWorkUrl
	 * @param accessToken
	 * @return
	 */
	public MassMsg sendWehcatDefaultGroupNewsMessageService(MassMsg massMsg,WechatArticle wechatArticle,String pathUrl,String accessToken);
	/**
	 * 群发图文消息到默认的群组
	 * @param wechatArticle
	 * @param massMsg
	 * @param pathUrl
	 * @param netWorkUrl
	 * @param accessToken
	 * @return
	 */
	public MassMsg sendWehcatDefaultGroupNewsMessageService(MassMsg massMsg,String accessToken,String media_id);
	/**
	 * 群发文本消息到默认的群组
	 * @param accessToken
	 * @param content
	 * @return
	 */
	public MassMsg sendWehcatDefaultGroupTextMessageService(MassMsg massMsg,String accessToken,String content);
	/**
	 * 群发图片消息到默认的群组
	 * @param accessToken
	 * @param count
	 * @return
	 */
	public MassMsg sendWehcatDefaultGroupImageMessageService(MassMsg massMsg,String accessToken,String media_id);
	
}
