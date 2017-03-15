package com.dao.weibo;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.weibo.WeiboMedia;
import org.oa_bean.weibo.WeiboToken;

public interface WeiboMediaService {
	/**
	 * 新增短微博
	 * @param weiboMedia
	 */
	public boolean addWeiboMediaService(WeiboMedia weiboMedia,WeiboToken weiboToken);
	/**
	 * 更新短微博
	 * @param weiboMedia
	 */
	public boolean updateWeiboMediaService(WeiboMedia weiboMedia,WeiboToken weiboToken);
	/**
	 * 删除短微博
	 * @param id
	 */
	public boolean deleteWeiboMediaService(Integer id,String mid,WeiboToken weiboToken);
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
	public Page<WeiboMedia> getPageWeiboMedia(Page<WeiboMedia> page,WeiboMedia weiboMedia,LinkedHashMap<String, String> orderby,String startTime,String endTime);
	/**
	 * 根据文章ID查询
	 * @param pubId
	 * @return
	 */
	public List<WeiboMedia> getWeiboMediaByPubId(String pubId);
}
