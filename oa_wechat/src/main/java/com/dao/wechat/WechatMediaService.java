package com.dao.wechat;

import java.util.LinkedHashMap;

import org.oa_bean.Page;
import org.oa_bean.wechat.WechatMedia;

/**
 * 保存微信媒体素材的信息
 * @author Administrator
 *
 */
public interface WechatMediaService {
	/**
	 *  新增上传媒体素材
	 * @param wechatMedia
	 * @param accessToken
	 * @return
	 */
	public boolean addWechatMediaService(WechatMedia wechatMedia ,String accessToken);
	/**
	 * 编辑微信媒体素材
	 * @param wechatMedia
	 */
	public boolean updateWechatMediaService(WechatMedia wechatMedia);
	/**
	 * 删除微信媒体素材
	 * @param id
	 */
	public boolean deleteWechatMediaService(Integer id);
	/**
	 * 查询微信媒体素材
	 * @param id
	 * @return
	 */
	public WechatMedia getWechatMediaById(Integer id);
	/**
	 * 分页查询微信媒体素材
	 * @param page
	 * @param wechatMedia
	 * @param orderby
	 * @return
	 */
	public Page<WechatMedia> getPageWechatMedia(Page<WechatMedia> page,WechatMedia wechatMedia,LinkedHashMap<String, String> orderby,String startTime,String endTime);
	
	
	
}
