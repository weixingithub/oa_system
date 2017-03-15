package com.dao.wechat;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.wechat.WechatToken;

import com.dao.BaseDao;

public interface WechatTokenDao extends BaseDao{
	/**
	 * 添加 token
	 * @param token
	 * @return
	 */
	public void addToken (WechatToken token);
	/**
	 * 编辑 token
	 * @param token
	 * @return
	 */
	public void updateToken (WechatToken token);
	/**
	 * 编辑 token
	 * @param token
	 * @return
	 */
	public void updateTokenByAppId (WechatToken token);
	/**
	 * 删除
	 * @param token
	 * @return
	 */
	public void deleteToken (String ids);
	/**
	 * 分页token
	 * @param wechat
	 * @return
	 */
	public Page<WechatToken> getPageToken(Page<WechatToken> page,String wheresql, List<Object> queryParams,LinkedHashMap<String, String> orderby);
	/**
	 * 查找最近一条appid对应的token
	 * @param appId
	 * @return
	 */
	public List<WechatToken> getRecentTokenByAppId(String appId);
	/**
	 * 根据Id查询
	 * @param id
	 * @return
	 */
	public WechatToken findTokenById(Integer id);
	
}
