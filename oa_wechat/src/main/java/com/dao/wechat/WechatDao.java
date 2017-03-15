package com.dao.wechat;


import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.wechat.Wechat;

import com.dao.BaseDao;

public interface WechatDao extends BaseDao {
	/**
	 * 添加微信公众号参数
	 * @param wechat
	 * @return
	 */
	public void addWechat(Wechat wechat);
	/**
	 *  根据微信AppID更新微信公众号参数
	 * @param wechat
	 * @return
	 */
	public void updateWechatByAppId(Wechat wechat);
	/**
	 *  ID更新微信公众号参数
	 * @param wechat
	 * @return
	 */
	public void updateWechat(Wechat wechat);
	/**
	 * 根据ID删除微信公众号
	 * @param id
	 * @return
	 */
	public void deleteWechatById(Integer id);
	/**
	 * 根据微信唯一appId删除
	 * @param weChatID
	 */
	public void deleteWechatByAppId(String appId);
	/**
	 * 带限制条件分页
	 * @param page
	 * @param wechat
	 * @param wheresql
	 * @param queryParams
	 * @param orderby
	 * @return
	 */
	public Page<Wechat> getPageWechat(Page<Wechat> page,String wheresql, List<Object> queryParams,LinkedHashMap<String, String> orderby);
	/**
	 * 查找微信唯一ID信息
	 * @param weChatID
	 * @return
	 */
	public List<Wechat> getWechatByWeChatID(String wechatID);
	/**
	 * 查找微信唯一appId信息
	 * @param appId
	 * @return
	 */
	public List<Wechat> getWechatByAppId(String appId);
	/**
	 * 查找微信ID信息
	 * @param weChatID
	 * @return
	 */
	public Wechat getWechatById(Integer id);
	/**
	 * 通过orgId查找微信信息
	 * @param orgId
	 * @return
	 */
	public List<Wechat> getWechatByOrgId(String orgId);
}
