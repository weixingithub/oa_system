package com.impl.wechat;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.wechat.WechatArticle;
import org.springframework.stereotype.Repository;

import com.dao.wechat.WechatArticleDao;
import com.impl.BaseDaoImpl;
@Repository(value="wechatArticleDao")
public class WechatArticleDaoImpl extends BaseDaoImpl implements WechatArticleDao{

	@Override
	public void addWechatArticle(WechatArticle wechatArticle) {
		save(wechatArticle);
	}

	@Override
	public void updateWechatArticle(WechatArticle wechatArticle) {
		update(wechatArticle);
	}

	@Override
	public void deleteWechatArticle(Integer id) {
		delete(WechatArticle.class, id);
	}

	@Override
	public WechatArticle getWechatArticleById(Integer id) {
		return find(WechatArticle.class, id);
	}

	@Override
	public Page<WechatArticle> getPageWechatArticle(Page<WechatArticle> page,
			String wheresql, List<Object> queryParams,
			LinkedHashMap<String, String> orderby) {
		return getScrollData(page, WechatArticle.class, wheresql, queryParams, orderby);
	}

}
