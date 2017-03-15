package com.dao.weibo;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.weibo.Weibo;

import com.dao.BaseDao;
/**
 * 保存微博基本信息DAO
 *
 */
public interface WeiboDao extends BaseDao{
	/**
	 * 添加微博账号
	 * @param weibo
	 */
	public void addWeibo(Weibo weibo);
	/**
	 * 编辑微博账号
	 * @param weibo
	 */
	public void updateWeibo(Weibo weibo);
	/**
	 * 删除微博账号
	 * @param id
	 */
	public void deleteWeibo(Integer id);
	/**
	 * 查询微博账号
	 * @param id
	 * @return
	 */
	public Weibo findWeiboById(Integer id);
	/**
	 * 通过orgIds查找微博
	 * @param orgIds
	 * @return
	 */
	public List<Weibo> getWeiboByOrgId(String orgIds);
	
	/**
	 * 通过orgId查找微博
	 * @param clientId
	 * @return
	 */
	public List<Weibo> getWeiboByClientId(String clientId);
	/**
	 * 分页查询
	 * @param page
	 * @param wheresql
	 * @param queryParams
	 * @param orderby
	 * @return
	 */
	public Page<Weibo> getPageWeibo(Page<Weibo> page,String wheresql, List<Object> queryParams,LinkedHashMap<String, String> orderby);

}
