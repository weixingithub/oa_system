package com.dao.weibo;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.weibo.WeiboMedia;

import com.dao.BaseDao;
/**
 * 
 *保存短微博的DAO
 *
 */
public interface WeiboMediaDao extends BaseDao{
	/**
	 * 新增短微博
	 * @param weiboMedia
	 */
	public void addWeiboMedia(WeiboMedia weiboMedia);
	/**
	 * 更新短微博
	 * @param weiboMedia
	 */
	public void updateWeiboMedia(WeiboMedia weiboMedia);
	/**
	 * 删除短微博
	 * @param id
	 */
	public void deleteWeiboMedia(Integer id);
	/**
	 * 查询短微博信息
	 * @param id
	 */
	public WeiboMedia getWeiboMediaById(Integer id);
	/**
	 * 分页查询短微博信息
	 * @param page
	 * @param wheresql
	 * @param queryParams
	 * @param orderby
	 */
	public Page<WeiboMedia> getPageWeiboMedia(Page<WeiboMedia> page,String wheresql, List<Object> queryParams,LinkedHashMap<String, String> orderby);
	/**
	 * 根据文章ID查询
	 * @param pubId
	 * @return
	 */
	public List<WeiboMedia>  getWeiboMediaByPubId(String pubId);
}
