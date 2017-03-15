package com.impl.website;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.website.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.website.ColumnDao;
import com.dao.website.LayoutDao;
import com.dao.website.LayoutService;
import com.dao.website.WebsiteDao;

@Service(value="layoutService")
public class LayoutServiceImpl implements LayoutService{
	private LayoutDao layoutDao;
	private ColumnDao columnDao;
	private WebsiteDao websiteDao;
	@Autowired
	public void setWebsiteDao(WebsiteDao websiteDao) {
		this.websiteDao = websiteDao;
	}
	@Autowired
	public void setColumnDao(ColumnDao columnDao) {
		this.columnDao = columnDao;
	}
	@Autowired
	public void setLayoutDao(LayoutDao layoutDao) {
		this.layoutDao = layoutDao;
	}

	@Override
	@Transactional
	public boolean addLayoutService(Layout layout) {
		boolean flag = false;
		try{
			layoutDao.addLayout(layout);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateLayoutService(Layout layout) {
		boolean flag = false;
		try{
			layoutDao.updateLayout(layout);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteLayoutService(Integer id) {
		boolean flag = false;
		try{
			layoutDao.deleteLayout(id);
			columnDao.deleteColumnByLayout(id);
			
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	public Layout findLayoutById(Integer id) {
		return layoutDao.findLayoutById(id);
	}

	@Override
	public Page<Layout> findLayoutPage(Page<Layout> page, Layout layout,
			LinkedHashMap<String, String> orderby) {
		StringBuilder wheresql = new StringBuilder("1=1");
		List<Object> params = new ArrayList<Object>();
		if(!"".equals(layout.getLayoutname()) && layout.getLayoutname()!=null){
			wheresql.append(" and o.layoutname like ? ");
			params.add("%"+layout.getLayoutname()+"%");
		}
		if(layout.getLayoutlevel()!=null){
			wheresql.append(" and o.layoutlevel = ?");
			params.add(layout.getLayoutlevel());  
		}
		return layoutDao.findLayoutPage(page, wheresql.toString(), params, orderby);
	}
	@Override
	public List<Layout> findLayoutByLayoutids(String layoutids) {
		return layoutDao.findLayoutByLayoutids(layoutids);
	}

}
