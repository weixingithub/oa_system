package com.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.Tags;
import org.oa_common.date.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.TagDao;
import com.dao.TagService;

@Service
@Transactional
public class TagServiceImpl implements TagService{
	
	private TagDao tagDao;
	
	@Autowired
	public void setTagCategoryDao(TagDao tagCategoryDao) {
		this.tagDao = tagCategoryDao;
	}


	@Override
	public Page<Tags> findTagCategoryByPage(Page<Tags> page,
			Tags tagCategory, LinkedHashMap<String, String> orderby,
			String startTime, String endTime) {
		StringBuffer sqlWhere = new StringBuffer("1=1");
		List<Object> params = new ArrayList<Object>();
		if(tagCategory.gettagName() != null && !"".equals(tagCategory.gettagName())){
			sqlWhere.append(" and o.tagCategoryName like '%" + tagCategory.gettagName() + "%'");
		}
		if((startTime != null && !"".equals(startTime)) && (endTime != null && !"".equals(endTime))){
			sqlWhere.append(" and o.createTime between ? and ?");
			params.add(startTime + "00:00:00");
			params.add(endTime + "00:00:00");
		}
		return tagDao.findTagBypage(page,sqlWhere.toString(),params,orderby);
	}


	@Override
	public boolean saveTagCategory(Tags tagCategory) {
		Boolean flag = false;
		try {
			tagDao.saveTag(tagCategory);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}


	@Override
	public Tags findTagCategoryById(Integer id) {
		return tagDao.findTagById(id);
	}


	@Override
	public boolean updateTagCategory(Tags tagCategory) {
		Boolean flag = false;
		try {
			tagDao.updateTag(tagCategory);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}


	@Override
	public boolean delTagCategory(Integer id) {
		Boolean flag = false;
		try {
			tagDao.delTagById(id);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}


	@Override
	public List<Tags> findAllTags() {
		
		return tagDao.findAllTags();
	}


	@Override
	public Boolean equalsAndUpdateTag(String tagName) {
		Boolean flag = false;
		try {
			String[] articleTags = tagName.split(",");
			String createTime = DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss", "time-nw.nist.gov",2500);
					
			for (String t : articleTags) {
				Tags tName = tagDao.findTagByName(t.trim());
				if(tName != null && !"".equals(tName.gettagName())){
					continue;
				}else{
					Tags tag = new Tags();
					tag.setCreateTime(createTime);
					tag.settagName(t.trim());
					tagDao.saveTag(tag);
				}
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

}
