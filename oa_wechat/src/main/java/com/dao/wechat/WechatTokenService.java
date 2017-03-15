package com.dao.wechat;


import org.oa_bean.Page;
import org.oa_bean.wechat.WechatToken;

public interface WechatTokenService {
	/**
	 * 添加 token
	 * @param token
	 * @return
	 */
	public boolean addTokenService (WechatToken token);
	/**
	 * 更新 token
	 * @param token
	 * @return
	 */
	public boolean updateTokenService (WechatToken token);
	/**
	 * 删除
	 * @param token
	 * @return
	 */
	public boolean deleteTokenService (String ids);
	/**
	 * 分页token
	 * @param wechat
	 * @return
	 */
	public Page<WechatToken> getPageToken(Page<WechatToken> page,WechatToken token);
	/**
	 * 查找最近一条appid对应的token
	 * @param appId
	 * @return
	 */
	public WechatToken getRecentTokenByAppId(String appId);
	
	/**
	 * 更新 token
	 * @param token
	 * @return
	 */
	public WechatToken updateTokenByTimeService (Integer id,String appId,String appSecret);
	/**
	 * 更新 token
	 * @param token
	 * @return
	 */
	public boolean updateTokenService (Integer id);
}
