package com.dao.weibo;

import org.oa_bean.weibo.WeiboToken;

public interface WeiboTokenService {
	/**
	 * 添加微博的token
	 * @param accessToken
	 */
	public boolean addAccessTokenService(WeiboToken  accessToken);
	/**
	 * 编辑微博的token
	 * @param accessToken
	 */
	public boolean updateAccessTokenService(WeiboToken  accessToken);
	/**
	 * 删除微博token
	 * @param id
	 */
	public boolean deleteAccessTokenService(Integer  id);
	/**
	 * 根据ID查找微博的token
	 * @param id
	 */
	public WeiboToken findAccessTokenById(Integer  id);
	/**
	 * 根据UID查找微博的token
	 * @param uid
	 */
	public WeiboToken findAccessTokenByUid(String  uid);
	/**
	 * 根据微博的clientId 查找对应的token
	 * @param clientId
	 */
	public WeiboToken getAccessTokenByClientId(String  clientId);
}
