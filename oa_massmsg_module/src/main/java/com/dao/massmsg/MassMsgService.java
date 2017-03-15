package com.dao.massmsg;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.massmsg.MassMsg;

public interface MassMsgService {
	/**
	 * 新增群发信息
	 */
	public boolean addMassMsgService(MassMsg massMsg);
	/**
	 * 更新群发信息
	 */
	public boolean updateMassMsgService(MassMsg massMsg);
	/**
	 * 删除群发信息
	 */
	public boolean deleteMassMsgService(Integer id);
	/**
	 * 查询群发信息
	 * @return
	 */
	public MassMsg getMassMsgById(Integer id);
	/**
	 * 分页查询
	 * @param page
	 * @param wheresql
	 * @param queryParams
	 * @param orderby
	 * @return
	 */
	public Page<MassMsg> getPageMassMsg(Page<MassMsg> page, MassMsg massMsg,LinkedHashMap<String, String> orderby,String startTime,String endTime);
	/**
	 * 根据文章ID查询发送的消息状态
	 * @param aIds
	 * @return
	 */
	public List<MassMsg> getMassMsgByArticleIds(String aIds);
	
}
