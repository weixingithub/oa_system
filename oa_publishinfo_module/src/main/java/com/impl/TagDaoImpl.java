package com.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.Tags;
import org.springframework.stereotype.Repository;

import com.dao.TagDao;

@Repository(value="tagCategoryDao")
public class TagDaoImpl extends BaseDaoImpl implements TagDao{

	@Override
	public Page<Tags> findTagBypage(Page<Tags> page,
			String sqlWhere, List<Object> params,
			LinkedHashMap<String, String> orderby) {
		return getScrollData(page, Tags.class,sqlWhere,params,orderby);
	}

	@Override
	public void saveTag(Tags tagCategory) {
		this.save(tagCategory);
	}

	@Override
	public Tags findTagById(Integer id) {
		return find(Tags.class, id);
	}

	@Override
	public void updateTag(Tags tagCategory) {
		this.update(tagCategory);
	}

	@Override
	public void delTagById(Integer id) {
		this.delete(Tags.class,id);
	}

	@Override
	public List<Tags> findAllTags() {
		String hql = "select t from Tags t";
		return findObjectList(hql);
	}

	@Override
	public Tags findTagByName(String tagName) {
		String hql = "select t from Tags t where t.tagName like '%" + tagName + "%'";
		List<Tags> tags = findObjectList(hql);
		if(tags != null && tags.size() != 0){
			return tags.get(0);
		}else {
			return null;
		}
	}

}
