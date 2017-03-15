package com.impl.weibo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.weibo.WeiboArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.weibo.WeiboArticleDao;
import com.dao.weibo.WeiboArticleService;
@Service(value="weiboArticleService")
public class WeiboArticleServiceImpl implements WeiboArticleService {
	private WeiboArticleDao WeiboArticleDao;
	
	
	public WeiboArticleDao getWeiboArticleDao() {
		return WeiboArticleDao;
	}
	@Autowired
	public void setWeiboArticleDao(WeiboArticleDao weiboArticleDao) {
		WeiboArticleDao = weiboArticleDao;
	}

	@Override
	@Transactional
	public boolean addWeiboArticleService(WeiboArticle weiboArticle) {
		boolean flag = false;
		try{
			WeiboArticleDao.addWeiboArticle(weiboArticle);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateWeiboArticleService(WeiboArticle weiboArticle) {
		boolean flag = false;
		try{
			WeiboArticleDao.updateWeiboArticle(weiboArticle);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteWeiboArticleService(Integer id) {
		boolean flag = false;
		try{
			WeiboArticleDao.deleteWeiboArticle(id);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	public WeiboArticle getWeiboArticleById(Integer id) {
		return WeiboArticleDao.getWeiboArticleById(id);
	}

	@Override
	public Page<WeiboArticle> getPageWeiboArticle(Page<WeiboArticle> page,WeiboArticle weiboArticle,
			LinkedHashMap<String, String> orderby, String startTime,
			String endTime) {
		StringBuilder wheresql = new StringBuilder("1=1");
		List<Object> params = new ArrayList<Object>();
		if(!"".equals(weiboArticle.getUid())&& weiboArticle.getUid()!=null){
			String[] uids = weiboArticle.getUid().split(",");
			wheresql.append(" and  uid in(");
			for (int i = 0; i < uids.length; i++) {
				wheresql.append("?");
				wheresql.append(",");
				params.add(uids[i]);
			}
			wheresql = wheresql.deleteCharAt(wheresql.length()-1);
			wheresql.append(")");
		}
		if(!"".equals(weiboArticle.getTitle()) && weiboArticle.getTitle()!=null){
			wheresql.append(" and title like ? ");
			params.add("%"+weiboArticle.getTitle()+"%");
		}
		if(!"".equals(weiboArticle.getPubId()) && weiboArticle.getPubId()!=null){
			wheresql.append(" and pubId = ?");
			params.add(weiboArticle.getPubId());
		}
		if(!"".equals(weiboArticle.getTitle()) && weiboArticle.getTitle()!=null){
			wheresql.append("");
			params.add("%"+weiboArticle.getTitle()+"%");
		}
		return WeiboArticleDao.getPageWeiboArticle(page, wheresql.toString(), params, orderby);
	}

}
