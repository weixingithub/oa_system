package com.impl.weibo;

import java.util.List;

import org.oa_bean.weibo.WeiboToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.weibo.WeiboTokenDao;
import com.dao.weibo.WeiboTokenService;


@Service(value="weiboTokenService")
public class WeiboTokenServiceImpl implements WeiboTokenService {
	private WeiboTokenDao weiboTokenDao;
	
	@Autowired
	public void setWeiboTokenDao(WeiboTokenDao weiboTokenDao) {
		this.weiboTokenDao = weiboTokenDao;
	}

	@Override
	@Transactional
	public boolean addAccessTokenService(WeiboToken accessToken) {
		boolean flag = false;
		try{
			weiboTokenDao.addAccessToken(accessToken);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateAccessTokenService(WeiboToken accessToken) {
		boolean flag = false;
		try{
			weiboTokenDao.updateAccessToken(accessToken);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteAccessTokenService(Integer id) {
		boolean flag = false;
		try{
			weiboTokenDao.deleteAccessToken(id);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	public WeiboToken findAccessTokenById(Integer id) {
		return weiboTokenDao.findAccessTokenById(id);
	}

	@Override
	public WeiboToken getAccessTokenByClientId(String clientId) {
		List<WeiboToken> list  = weiboTokenDao.getAccessTokenByClientId(clientId);
		WeiboToken accessToken ;
		if(list.size()>0){
			accessToken  = list.get(0);
		}else{
			accessToken = null;
		}
		return accessToken;
	}
	@Override
	public WeiboToken findAccessTokenByUid(String uid) {
		return weiboTokenDao.findAccessTokenByUid(uid);
	}

}
