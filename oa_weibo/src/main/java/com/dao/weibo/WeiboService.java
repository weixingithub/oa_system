package com.dao.weibo;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.weibo.Weibo;

public interface WeiboService {
	/**
	 * 添加微博账号
	 * @param weibo
	 */
	public boolean addWeiboService(Weibo weibo);
	/**
	 * 编辑微博账号
	 * @param weibo
	 */
	public boolean updateWeiboService(Weibo weibo);
	/**
	 * 删除微博账号
	 * @param id
	 */
	public boolean deleteWeiboService(Integer id);
	/**
	 * 查询微博账号
	 * @param id
	 * @return
	 */
	public Weibo findWeiboById(Integer id);
	/**
	 * 通过orgId查找微博
	 * @param orgIds
	 * @return
	 */
	public List<Weibo> getWeiboByOrgId(String orgIds);
	/**
	 * 通过orgId查找微博
	 * @param clientId
	 * @return
	 */
	public Weibo getWeiboByClientId(String clientId);
	/**
	 * 分页查询
	 * @param page
	 * @param weibo
	 * @param orderby
	 * @return
	 */
	public Page<Weibo> getPageWeibo(Page<Weibo> page,Weibo weibo, LinkedHashMap<String, String> orderby,String startTime,String endTime);
}
