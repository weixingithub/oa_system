package com.dao.weibo;

import java.util.List;

import org.oa_bean.weibo.WeiboToken;

import com.dao.BaseDao;

public interface WeiboTokenDao extends BaseDao {
	/**
	 * 添加微博的token
	 * @param accessToken
	 */
	public void addAccessToken(WeiboToken  accessToken);
	/**
	 * 编辑微博的token
	 * @param accessToken
	 */
	public void updateAccessToken(WeiboToken  accessToken);
	/**
	 * 删除微博token
	 * @param id
	 */
	public void deleteAccessToken(Integer  id);
	/**
	 * 根据UID查找微博的token
	 * @param uid
	 */
	public WeiboToken findAccessTokenByUid(String  uid);
	/**
	 * 根据ID查找微博的token
	 * @param id
	 */
	public WeiboToken findAccessTokenById(Integer  id);
	/**
	 * 根据微博的clientId 查找对应的token
	 * @param clientId
	 */
	public List<WeiboToken> getAccessTokenByClientId(String  clientId);
}
