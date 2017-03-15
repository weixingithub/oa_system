package com.impl.weibo;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.weibo.WeiboMedia;
import org.springframework.stereotype.Repository;

import com.dao.weibo.WeiboMediaDao;
import com.impl.BaseDaoImpl;

@Repository(value="weiboMediaDao")
public class WeiboMediaDaoImpl extends BaseDaoImpl implements WeiboMediaDao {

	@Override
	public void addWeiboMedia(WeiboMedia weiboMedia) {
		save(weiboMedia);
	}

	@Override
	public void updateWeiboMedia(WeiboMedia weiboMedia) {
		update(weiboMedia);
	}

	@Override
	public void deleteWeiboMedia(Integer id) {
		delete(WeiboMedia.class, id);
	}

	@Override
	public WeiboMedia getWeiboMediaById(Integer id) {
		return find(WeiboMedia.class, id);
	}

	@Override
	public Page<WeiboMedia> getPageWeiboMedia(Page<WeiboMedia> page,
			String wheresql, List<Object> queryParams,
			LinkedHashMap<String, String> orderby) {
		return getScrollData(page, WeiboMedia.class, wheresql, queryParams, orderby);
	}

	@Override
	public List<WeiboMedia> getWeiboMediaByPubId(String pubId) {
		String hql = "select wm from WeiboMedia  wm where wm.pubId='"+pubId+"' order by wm.createTime";
		return findObjectList(hql);
	}

}
