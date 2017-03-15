package com.dao;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.Tags;

/**
 * 标签类别业务层接口
 * @author Administrator
 *
 */
public interface TagService {

	/**
	 * 获取标签分类列表(分页)
	 * @param page
	 * @param tagCategory
	 * @param orderby
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public Page<Tags> findTagCategoryByPage(Page<Tags> page,
			Tags tagCategory, LinkedHashMap<String, String> orderby,
			String startTime, String endTime);

	/**
	 * 添加标签分类
	 * @param tagCategory
	 * @return
	 */
	public boolean saveTagCategory(Tags tagCategory);

	/**
	 * 根据ID查询标签分类
	 * @param id
	 * @return
	 */
	public Tags findTagCategoryById(Integer id);

	/**
	 * 修改标签分类
	 * @param tagCategory
	 * @return
	 */
	public boolean updateTagCategory(Tags tagCategory);

	/**
	 * 根据ID删除标签分类
	 * @param id
	 * @return
	 */
	public boolean delTagCategory(Integer id);

	/**
	 * 查询所有标签
	 * @return
	 */
	public List<Tags> findAllTags();

	/**
	 * 判断标签是否已存在
	 * @param trim
	 * @return
	 */
	public Boolean equalsAndUpdateTag(String tagName);
	
}
