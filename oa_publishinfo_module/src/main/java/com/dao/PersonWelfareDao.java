package com.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import org.oa_bean.Page;
import org.oa_bean.PersonWelfare;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月4日 上午11:42:17 
 * @version 1.0 
 */
public interface PersonWelfareDao extends BaseDao{
	/**
	 * 
	 * @param page
	 * @param personWelfare
	 * @param orderby
	 * @return
	 */
	public Page<PersonWelfare> findPersonWelfarePage(Page<PersonWelfare> page, String wheresql, List<Object> params,
			LinkedHashMap<String, String> orderby);
	/**
	 * 删除所属个人的所有信息
	 * @param sysUserId
	 */
	public void deletePersonWelfareByUser(String userId,String modleId);
	/**
	 * 批量ids查询
	 * @param businessKeys
	 * @return
	 */
	public List<PersonWelfare>  findByPersonWelfareIds(Set<String> businessKeys);
	/**
	 * 通过lucene索引获取文章id
	 * @param query
	 * @param z
	 * @return
	 */
	public String findPwIdByCIndex(org.apache.lucene.search.Query query,Class z);
}
