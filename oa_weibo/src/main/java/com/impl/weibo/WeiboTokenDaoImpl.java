package com.impl.weibo;

import java.util.List;

import org.oa_bean.weibo.WeiboToken;
import org.springframework.stereotype.Repository;

import com.dao.weibo.WeiboTokenDao;
import com.impl.BaseDaoImpl;
@Repository(value="weiboTokenDao")
public class WeiboTokenDaoImpl extends BaseDaoImpl implements WeiboTokenDao {

	@Override
	public void addAccessToken(WeiboToken accessToken) {
		save(accessToken);
	}

	@Override
	public void updateAccessToken(WeiboToken accessToken) {
		update(accessToken);
	}

	@Override
	public void deleteAccessToken(Integer id) {
		delete(WeiboToken.class, id);
	}

	@Override
	public WeiboToken findAccessTokenById(Integer id) {
		return find(WeiboToken.class, id);
	}

	@Override
	public List<WeiboToken> getAccessTokenByClientId(String clientId) {
		String hql ="select at from  WeiboToken at where at.clientId = '"+clientId+"'";
		return findObjectList(hql);
	}

	@Override
	public WeiboToken findAccessTokenByUid(String uid) {
		String hql ="select at from  WeiboToken at where at.uid = '"+uid+"'";
		return (WeiboToken) findObject(hql);
	}

}
