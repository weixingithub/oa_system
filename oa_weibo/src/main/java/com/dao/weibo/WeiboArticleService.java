package com.dao.weibo;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.weibo.WeiboArticle;

public interface WeiboArticleService {
	/**
	 * 新增长微博
	 * @param weiboArticle
	 */
	public boolean addWeiboArticleService(WeiboArticle weiboArticle);
	/**
	 * 更新长微博
	 * @param weiboArticle
	 */
	public boolean updateWeiboArticleService(WeiboArticle weiboArticle);
	/**
	 * 删除长微博
	 * @param id
	 */
	public boolean deleteWeiboArticleService(Integer id);
	/**
	 * 查询长微博信息
	 * @param id
	 */
	public WeiboArticle getWeiboArticleById(Integer id);
	/**
	 * 分页查询长微博信息
	 * @param page
	 * @param wheresql
	 * @param queryParams
	 * @param orderby
	 */
	public Page<WeiboArticle> getPageWeiboArticle(Page<WeiboArticle> page,WeiboArticle weiboArticle,LinkedHashMap<String, String> orderby,String startTime,String endTime);

}
