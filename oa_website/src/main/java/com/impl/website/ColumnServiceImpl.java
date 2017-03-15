package com.impl.website;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.website.ColumnObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.website.ColumnDao;
import com.dao.website.ColumnService;
@Service(value="columnService")
public class ColumnServiceImpl implements ColumnService{
	private ColumnDao columnDao;
	@Autowired
	public void setColumnDao(ColumnDao columnDao) {
		this.columnDao = columnDao;
	}
	@Override
	public ColumnObj findColumnById(Integer id) {
		return columnDao.findColumnById(id);
	}

	@Override
	public Page<ColumnObj> findColumnPage(Page<ColumnObj> page, ColumnObj column,
			LinkedHashMap<String, String> orderby) {
		StringBuilder wheresql = new StringBuilder("1=1");
		List<Object> params = new ArrayList<Object>();
		
		return columnDao.findColumnPage(page, wheresql.toString(), params, orderby);
	}
	@Override
	@Transactional
	public boolean addAndUpdateColumnObjService(List<ColumnObj> list,Integer modelId) {
		boolean flag = false;
		try {
			for (ColumnObj columnObj : list) {
				if(columnObj.getColumnId()!=null && columnObj.getColumnId()!=0){
					columnDao.updateColumn(columnObj, modelId);
				}else{
					columnDao.addColumn (columnObj, modelId);
				}
			}
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	 
 
}
