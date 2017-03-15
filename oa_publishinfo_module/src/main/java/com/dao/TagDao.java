package com.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.Tags;

/**
 * 标签类别Dao接口
 * @author Administrator
 *
 */
public interface TagDao extends BaseDao {

	/**
	 * 分获取标签列表
	 * @param page
	 * @param string
	 * @param params
	 * @param orderby
	 * @return
	 */
	public Page<Tags> findTagBypage(Page<Tags> page,
			String sqlWhere, List<Object> params,
			LinkedHashMap<String, String> orderby);

	/**
	 * 保存标签对象
	 * @param tagCategory
	 */
	public void saveTag(Tags tagCategory);

	/**
	 * 根据ID查询标签
	 * @param id
	 * @return
	 */
	public Tags findTagById(Integer id);

	/**
	 * 修改更新标签
	 * @param tagCategory
	 */
	public void updateTag(Tags tagCategory);

	/**
	 * 根据ID删除标签
	 * @param id
	 */
	public void delTagById(Integer id);

	/**
	 * 查询所有标签名
	 * @return
	 */
	public List<Tags> findAllTags();

	/**
	 * 根据名称查询标签
	 * @param tagName
	 */
	public Tags findTagByName(String tagName);

}
