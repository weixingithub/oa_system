package com.impl.push;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bean.push.ScheduleJob;
import com.dao.push.ScheduleJobDao;
import com.impl.BaseDaoImpl;
@Repository(value="scheduleJobMapper")
public class ScheduleDaoImp extends BaseDaoImpl implements ScheduleJobDao {

	@Override
	public void deleteByPrimaryKey(Long jobId) {
		 delete(ScheduleJob.class, jobId);;
	}

	@Override
	public void insert(ScheduleJob record) {
		save(record);
	}

	@Override
	public void insertSelective(ScheduleJob record) {
		save(record);
	}

	@Override
	public ScheduleJob selectByPrimaryKey(Long jobId) {
		return find(ScheduleJob.class, jobId);
	}

	@Override
	public void updateByPrimaryKeySelective(ScheduleJob record) {
		update(record);
	}

	@Override
	public void updateByPrimaryKey(ScheduleJob record) {
		update(record);
	}

	@Override
	@Transactional
	public Page<ScheduleJob> getAllTask(Page<ScheduleJob> page,LinkedHashMap<String, String> orderby, String wheresql,List<Object> queryParams,ScheduleJob schedule) {
		 return getScrollData(page, ScheduleJob.class, wheresql.toString(), queryParams, orderby);
	}

	@Override
	@Transactional
	public List<ScheduleJob> getAll() {
		String hql="from ScheduleJob";
		return findObjectList(hql);
	}

	@Override
	public void deleteJobTask(Long jobId) {
		delete(ScheduleJob.class, jobId);
	}

}
