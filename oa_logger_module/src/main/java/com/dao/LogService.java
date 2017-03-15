package com.dao;

import java.util.LinkedHashMap;

import org.oa_bean.OALog;
import org.oa_bean.Page;

public interface LogService {
    /**
   	 * 新增日志
   	 * @param oalog
   	 * @return
   	 */
     public void baseSaveLog(OALog oalog) throws Exception;
     /**
      * 日志分页
      * @param page
      * @return
      * author weixin
      */
     public Page<OALog> getLogPage(Page<OALog> page,LinkedHashMap<String, String> orderby);
     
     /**
 	 * 条件分页
 	 * @param page
 	 * @param wheresql
 	 * @param params
 	 * @param orderby
 	 * @return
 	 */
 	public Page<OALog> getConditionOaLogPage(Page<OALog> page, OALog oalog,LinkedHashMap<String, String> orderby,String userName,String operationType,String startTime,String endTime);
 
 	/**
 	 * 新增
 	 * @param oalog
 	 * @return
 	 */
	public boolean addOALogService(OALog oalog);
 	
}
