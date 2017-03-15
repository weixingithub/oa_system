package com.dao.massmsg;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.massmsg.MassMsg;

import com.dao.BaseDao;

/**
 * （微博、微信）消息群发保存结果DAO
 * @author 江斌
 *
 */
public interface MassMsgDao extends BaseDao{
	/**
	 * 新增群发信息
	 */
	public void addMassMsg(MassMsg massMsg);
	/**
	 * 更新群发信息
	 */
	public void updateMassMsg(MassMsg massMsg);
	/**
	 * 删除群发信息
	 */
	public void deleteMassMsg(Integer id);
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
	public Page<MassMsg> getPageMassMsg(Page<MassMsg> page,String wheresql, List<Object> queryParams,LinkedHashMap<String, String> orderby);
	/**
	 * 根据文章ID查询发送的消息状态
	 * @param aIds
	 * @return
	 */
	public List<MassMsg> getMassMsgByArticleIds(String aIds);
}
