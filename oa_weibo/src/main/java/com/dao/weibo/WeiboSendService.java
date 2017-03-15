package com.dao.weibo;

import java.util.List;

import org.oa_bean.weibo.WeiboMedia;

public interface WeiboSendService {
	/**
	 * 发送微博图文消息
	 * @param weiboMedia
	 * @param fileUrl
	 * @param accessToken
	 * @return
	 */
	public WeiboMedia sendWeiboMediaNewsMessageService(WeiboMedia weiboMedia,String fileUrl,String accessToken);
	 
	/**
	 * 发送微博文本消息
	 * @param weiboMedia
	 * @param accessToken
	 * @return
	 */
	public WeiboMedia sendWeiboMediaTextMessageService(WeiboMedia weiboMedia,String accessToken);
	/**
	 * 查询发送结果
	 * @param pubId
	 * @return
	 */
	public List<WeiboMedia> getWeiboMediaSendResult(String pubId);
	
	/**
	 * 重新发送微博图文消息
	 * @param weiboMedia
	 * @param fileUrl
	 * @param accessToken
	 * @return
	 */
	public WeiboMedia resendSendWeiboMediaNewsMessageService(WeiboMedia weiboMedia,String fileUrl,String accessToken);
	/**
	 * 重新发送微博文本消息
	 * @param pubId
	 * @return
	 */
	public WeiboMedia resendSendWeiboMediaTextMessageService(WeiboMedia weiboMedia,String accessToken);
	 
}
