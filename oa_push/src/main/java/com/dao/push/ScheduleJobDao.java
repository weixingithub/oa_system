package com.dao.push;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;

import com.bean.push.ScheduleJob;
/**
 * 定时任务管理接口
 * @author Administrator
 * @date 2016年10月20日
 * @company
 * ScheduleJobMapper.java
 */
public interface ScheduleJobDao {
	void deleteByPrimaryKey(Long jobId);

	void insert(ScheduleJob record);

	void insertSelective(ScheduleJob record);

	ScheduleJob selectByPrimaryKey(Long jobId);

	void updateByPrimaryKeySelective(ScheduleJob record);

	void updateByPrimaryKey(ScheduleJob record);

    Page<ScheduleJob> getAllTask(Page<ScheduleJob> page, LinkedHashMap<String, String> orderby, String wheresql, List<Object> queryParams,ScheduleJob schedule);

	List<ScheduleJob> getAll();

	void deleteJobTask(Long jobId);



}