package com.dao.wechat;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.wechat.WechatMedia;

import com.dao.BaseDao;

/**
 * 保存微信媒体素材的信息
 * @author Administrator
 *
 */
public interface WechatMediaDao extends BaseDao {
	/**
	 * 新增媒体素材
	 * @param wechatMedia
	 */
	public void addWechatMedia(WechatMedia wechatMedia);
	/**
	 * 编辑媒体素材
	 * @param wechatMedia
	 */
	public void updateWechatMedia(WechatMedia wechatMedia);
	/**
	 * 删除媒体素材
	 * @param id
	 */
	public void deleteWechatMedia(Integer id);
	/**
	 * 查询媒体素材
	 * @param id
	 * @return
	 */
	public WechatMedia getWechatMediaById(Integer id);
	/**
	 * 分页查询媒体素材
	 * @param page
	 * @param wheresql
	 * @param queryParams
	 * @param orderby
	 * @return
	 */
	public Page<WechatMedia> getPageWechatMedia(Page<WechatMedia> page,String wheresql, List<Object> queryParams,LinkedHashMap<String, String> orderby);
	
	
	
}
