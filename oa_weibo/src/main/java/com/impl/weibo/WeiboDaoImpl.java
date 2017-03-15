package com.impl.weibo;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.weibo.Weibo;
import org.springframework.stereotype.Repository;

import com.dao.weibo.WeiboDao;
import com.impl.BaseDaoImpl;

@Repository(value="weiboDao")
public class WeiboDaoImpl extends BaseDaoImpl implements WeiboDao{
	@Override
	public void addWeibo(Weibo weibo) {
		save(weibo);
	}
	@Override
	public void updateWeibo(Weibo weibo) {
		update(weibo);
	}

	@Override
	public void deleteWeibo(Integer id) {
		delete(Weibo.class, id);
	}

	@Override
	public Weibo findWeiboById(Integer id) {
		return find(Weibo.class, id);
	}

	@Override
	public List<Weibo> getWeiboByOrgId(String orgIds) {
		String hql = "select wb from Weibo wb where 1=1";
		if(!orgIds.equals(orgIds) && orgIds!=null){
			hql +=" and wb.orgId in("+orgIds+")";
		}
		return findObjectList(hql);
	}

	@Override
	public List<Weibo> getWeiboByClientId(String clientId) {
		String hql = "select wb from Weibo wb where wb.clientId ='"+clientId+"'";
		return findObjectList(hql);
	}

	@Override
	public Page<Weibo> getPageWeibo(Page<Weibo> page,
			String wheresql, List<Object> queryParams,
			LinkedHashMap<String, String> orderby) {
		return getScrollData(page, Weibo.class, wheresql, queryParams, orderby);
	}

}
