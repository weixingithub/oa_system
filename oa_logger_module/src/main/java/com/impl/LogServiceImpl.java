package com.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.OALog;
import org.oa_bean.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.LogBaseDao;
import com.dao.LogService;
@Service(value="logService")
public class LogServiceImpl implements LogService {
    
	private LogBaseDao logBaseDao;
	
	@Override
	@Transactional
	public void baseSaveLog(OALog oalog) throws Exception {
		logBaseDao.save(oalog);
	}
	
    @Autowired
	public void setLogBaseDao(LogBaseDao logBaseDao) {
		this.logBaseDao = logBaseDao;
	}

    @Override
	public Page<OALog> getLogPage(Page<OALog> page,LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		StringBuffer wherehql = new StringBuffer("1=1");
		List<Object> queryParams = new ArrayList<Object>();
	    return logBaseDao.getScrollData(page, OALog.class,wherehql.toString(), queryParams,orderby);
	}
	public LogBaseDao getLogBaseDao() {
		return logBaseDao;
	}
	@Override
	public Page<OALog> getConditionOaLogPage(Page<OALog> page, OALog oalog,
			LinkedHashMap<String, String> orderby,String userName,String operationType,String startTime,String endTime) {
		 StringBuffer wheresql = new StringBuffer("1=1");
		 List<Object> params = new ArrayList<Object>();
		 if(userName!=null && !"".equals(userName)){
			 wheresql.append(" and o.actiondescribe like ? ");
			 params.add("%"+userName.trim()+"%");
		 }
		 if(operationType!=null && !"".equals(operationType)){
			 if(userName==null || "".equals(userName)){
				 wheresql.append(" and o.actiondescribe like ? ");
				 params.add("%"+operationType+"%");
			 }else{
				 wheresql.append("  and o.actiondescribe like ? ");
				 params.add("%"+operationType+"%");
			 }
		 }
		 if((startTime != null && !"".equals(startTime)) && (endTime != null && !"".equals(endTime))){
		    	wheresql.append(" and o.actiontime between ?  and ? ");
		    	params.add(startTime+" 00:00:00");
		    	params.add(endTime+" 00:00:00");
		  }
		return logBaseDao.getScrollData(page, OALog.class,wheresql.toString(), params,orderby);
	}

	@Override
	@Transactional
	public boolean addOALogService(OALog oalog) {
		boolean flag = false;
		try{
			logBaseDao.save(oalog);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
}
