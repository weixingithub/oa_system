package com.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.oa_bean.Page;
import org.oa_bean.PersonWelfare;

/**
 * @author  魏歆
 * @description
 * @date 创建时间：2016年5月4日 上午11:42:17 
 * @version 1.0 
 */
public interface PersonWelfareService {
	/**
	 * 进行流程启动
	 * @param personWelfare
	 * @param assigneperson	
	 * @return
	 */
	public boolean savePersonWelfareService(Integer id,String userName,String assigneperson,String finishtime);
	/**
	 * 进行业务关联
	 * @param userName
	 * @param page
	 * @return
	 */
	public Page<PersonWelfare> findTodoTasks(String userName, Page<PersonWelfare> page, int[] pageParams,PersonWelfare personWelfare);
	/**
	 * 根据ID查找信息
	 * @param id
	 * @return
	 */
	public PersonWelfare findPwById(Integer id);
	/**
	 * 更新信息
	 * @param personWelfare
	 * @return
	 */
	public boolean updatePersonWelfareService(PersonWelfare personWelfare);
	/**
	 * 分页查询
	 * @param page
	 * @param personWelfare
	 * @param orderby
	 * @return
	 */
	public Page<PersonWelfare> findPersonWelfarePage(Page<PersonWelfare> page, PersonWelfare personWelfare,
			LinkedHashMap<String, String> orderby,String content,String orgids);
	/**
	 * 根据发布渠道分页查询
	 * @param page
	 * @param personWelfare
	 * @param orderby
	 * @return
	 */
	public Page<PersonWelfare> findPersonWelfarePageByPub(Page<PersonWelfare> page, PersonWelfare personWelfare,
			LinkedHashMap<String, String> orderby,String pubPlatform);
	/**
	 * 新增和编辑信息
	 * @param personWelfare
	 * @return
	 */
	public boolean addAndUpdatePersonWelfare(PersonWelfare personWelfare);
	/**
	 * 删除
	 * @param ids
	 * @return
	 */
	public boolean delPersonWelfare(String ids);
	
	
	public Page<PersonWelfare> findFinishedProcessInstaces(Page<PersonWelfare> page, String key, String userId, int[] pageParams,PersonWelfare personWelfare);
	
	/**
	 * 删除所属个人的所有信息
	 * @param sysUserId
	 */
	public boolean deletePersonWelfareByUser(String userId,String modleId);
	/**
	 * 获取超时任务分页列表
	 * @param userName
	 * @param page
	 * @param pageParams
	 * @param personWelfare
	 * @return
	 */
	public Page<PersonWelfare> findOvertimeTasks(String userName,
			Page<PersonWelfare> page, int[] pageParams,
			PersonWelfare personWelfare);
	/**
	 * sql更新方式
	 * @param pw
	 * @return
	 */
	public int updatePwBySqlService(PersonWelfare pw);
}
