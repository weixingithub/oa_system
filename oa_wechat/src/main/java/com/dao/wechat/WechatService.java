package com.dao.wechat;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.wechat.Wechat;

public interface WechatService {
	/**
	 * 添加微信公众号参数
	 * @param wechat
	 * @return
	 */
	public boolean addWechatService(Wechat wechat);
	/**
	 * 根据微信AppID更新微信公众号参数
	 * @param wechat
	 * @return
	 */
	public boolean updateWechatByAppIdService(Wechat wechat);
	/**
	 * 更新微信公众号参数
	 * @param wechat
	 * @return
	 */
	public boolean updateWechatService(Wechat wechat);
	/**
	 * 删除微信公众号
	 * @param id
	 * @return
	 */
	public boolean deleteWechatByIdService(Integer id);
	/**
	 * 带限制条件分页
	 * @param page
	 * @param wechat
	 * @param wheresql
	 * @param queryParams
	 * @param orderby
	 * @return
	 */
	public Page<Wechat> getPageWechat(Page<Wechat> page, Wechat wechat,LinkedHashMap<String, String> orderby,String startTime,String endTime);
	/**
	 * 查找微信唯一ID信息
	 * @param weChatID
	 * @return
	 */
	public Wechat getWechatByWeChatID(String weChatID);
	/**
	 * 查找微信ID信息
	 * @param weChatID
	 * @return
	 */
	public Wechat getWechatById(Integer id);
	/**
	 * 根据微信唯一appId删除
	 * @param weChatID
	 */
	public boolean deleteWechatByAppId(String appId);
	/**
	 * 查找微信唯一appId信息
	 * @param appId
	 * @return
	 */
	public Wechat getWechatByAppId(String appId);
	/**
	 * 通过orgId查找微信信息
	 * @param orgId
	 * @return
	 */
	public List<Wechat> getWechatByOrgId(String orgId);
	
}
