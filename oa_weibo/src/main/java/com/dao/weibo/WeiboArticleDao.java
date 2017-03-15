package com.dao.weibo;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.weibo.WeiboArticle;

import com.dao.BaseDao;

/**
 * 保存长微博的DAO
 * @author 江斌
 *
 */
public interface WeiboArticleDao extends BaseDao{
	/**
	 * 新增长微博
	 * @param weiboArticle
	 */
	public void addWeiboArticle(WeiboArticle weiboArticle);
	/**
	 * 更新长微博
	 * @param weiboArticle
	 */
	public void updateWeiboArticle(WeiboArticle weiboArticle);
	/**
	 * 删除长微博
	 * @param id
	 */
	public void deleteWeiboArticle(Integer id);
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
	public Page<WeiboArticle> getPageWeiboArticle(Page<WeiboArticle> page,String wheresql, List<Object> queryParams,LinkedHashMap<String, String> orderby);

}
