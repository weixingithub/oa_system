package com.impl.wechat;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.wechat.WechatMedia;
import org.springframework.stereotype.Repository;

import com.dao.wechat.WechatMediaDao;
import com.impl.BaseDaoImpl;
@Repository(value="wechatMediaDao")
public class WechatMediaDaoImpl extends BaseDaoImpl implements WechatMediaDao {

	@Override
	public void addWechatMedia(WechatMedia wechatMedia) {
		save(wechatMedia);
	}

	@Override
	public void updateWechatMedia(WechatMedia wechatMedia) {
		update(wechatMedia);
	}

	@Override
	public void deleteWechatMedia(Integer id) {
		delete(WechatMedia.class, id);
	}

	@Override
	public WechatMedia getWechatMediaById(Integer id) {
		return find(WechatMedia.class, id);
	}

	@Override
	public Page<WechatMedia> getPageWechatMedia(Page<WechatMedia> page,
			String wheresql, List<Object> queryParams,
			LinkedHashMap<String, String> orderby) {
		return getScrollData(page, WechatMedia.class, wheresql, queryParams, orderby);
	}

}
