package com.dao.wechat;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.wechat.WechatArticle;

import com.dao.BaseDao;

/**
 * 保存微信图文信息的DAO
 * @author 江斌
 *
 */
public interface WechatArticleDao extends BaseDao{
	/**
	 * 新增图文信息
	 * @param wechatArticle
	 */
	public void addWechatArticle(WechatArticle wechatArticle);
	/**
	 * 修改图文信息
	 * @param wechatArticle
	 */
	public void updateWechatArticle(WechatArticle wechatArticle);
	/**
	 * 删除图文信息
	 * @param id
	 */
	public void deleteWechatArticle(Integer id);
	/**
	 * 查询图文信息
	 * @param id
	 * @return
	 */
	public WechatArticle getWechatArticleById(Integer id);
	/**
	 * 分页查询
	 * @param page
	 * @param wheresql
	 * @param queryParams
	 * @param orderby
	 * @return
	 */
	public Page<WechatArticle> getPageWechatArticle(Page<WechatArticle> page,String wheresql, List<Object> queryParams,LinkedHashMap<String, String> orderby);
	
	
}
