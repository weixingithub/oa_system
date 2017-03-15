package com.dao.wechat;

import java.util.LinkedHashMap;

import org.oa_bean.Page;
import org.oa_bean.wechat.WechatArticle;

public interface WechatArticleService {
	/**
	 * 新增单个微信图文信息
	 * @param wechatArticle
	 */
	public boolean addSingleWechatArticleService(WechatArticle wechatArticle,String accessToken);
	/**
	 * 修改微信图文信息
	 * @param wechatArticle
	 */
	public boolean updateWechatArticleService(WechatArticle wechatArticle,String accessToken);
	/**
	 * 删除微信图文信息
	 * @param id
	 */
	public boolean deleteWechatArticleService(Integer id);
	/**
	 * 查询微信图文信息
	 * @param id
	 * @return
	 */
	public WechatArticle getWechatArticleById(Integer id);
	/**
	 * 分页查询微信图文信息
	 * @param page
	 * @param wechatArticle
	 * @param orderby
	 * @return
	 */
	public Page<WechatArticle> getPageWechatArticle(Page<WechatArticle> page,WechatArticle wechatArticle,LinkedHashMap<String, String> orderby,String startTime,String endTime);
	
	
}
