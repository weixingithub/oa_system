package com.impl.weibo;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.weibo.WeiboArticle;
import org.springframework.stereotype.Repository;

import com.dao.weibo.WeiboArticleDao;
import com.impl.BaseDaoImpl;
@Repository(value="weiboArticleDao")
public class WeiboArticleDaoImpl extends BaseDaoImpl implements WeiboArticleDao{

	@Override
	public void addWeiboArticle(WeiboArticle weiboArticle) {
		save(weiboArticle);
	}

	@Override
	public void updateWeiboArticle(WeiboArticle weiboArticle) {
		update(weiboArticle);
	}

	@Override
	public void deleteWeiboArticle(Integer id) {
		delete(WeiboArticle.class, id);
	}

	@Override
	public WeiboArticle getWeiboArticleById(Integer id) {
		return find(WeiboArticle.class, id);
	}

	@Override
	public Page<WeiboArticle> getPageWeiboArticle(Page<WeiboArticle> page,
			String wheresql, List<Object> queryParams,
			LinkedHashMap<String, String> orderby) {
		return getScrollData(page, WeiboArticle.class, wheresql, queryParams, orderby);
	}

}
