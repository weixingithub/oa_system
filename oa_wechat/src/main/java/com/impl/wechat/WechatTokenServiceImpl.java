package com.impl.wechat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.wechat.Wechat;
import org.oa_bean.wechat.WechatToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.wechat.WechatDao;
import com.dao.wechat.WechatTokenDao;
import com.dao.wechat.WechatTokenService;
import com.server.wechat.RequestToken;

@Service(value="wechatTokenService")
public class WechatTokenServiceImpl implements WechatTokenService{
	private WechatTokenDao tokenDao;
	private WechatDao wechatDao;
	@Autowired
	public void setWechatDao(WechatDao wechatDao) {
		this.wechatDao = wechatDao;
	}
	@Autowired
	public void setTokenDao(WechatTokenDao tokenDao) {
		this.tokenDao = tokenDao;
	}

	@Override
	@Transactional
	public boolean addTokenService(WechatToken token) {
		boolean flag = false;
		try{
			tokenDao.addToken(token);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateTokenService(WechatToken token) {
		boolean flag = false;
		try{
			tokenDao.updateToken(token);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteTokenService(String ids) {
		boolean flag = false;
		try{
			tokenDao.deleteToken(ids);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	public Page<WechatToken> getPageToken(Page<WechatToken> page, WechatToken token) {
		 StringBuffer wheresql = new StringBuffer("1=1");
		 List<Object> params = new ArrayList<Object>();
		 if(!"".equals(token.getAppId())&& token.getAppId()!=null){
			 wheresql.append(" and o.appId = ?");
			 params.add(token.getAppId());
		 }
		 LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		 orderby.put("createTime", "desc");
		return tokenDao.getPageToken(page, wheresql.toString(), params, orderby);
	}

	@Override
	public WechatToken getRecentTokenByAppId(String appId) {
		List<WechatToken> list  = tokenDao.getRecentTokenByAppId(appId);
		WechatToken token ;
		if(list.size()>0){
			token  = list.get(0);
		}else{
			token = null;
		}
		return token;
	}
	@Override
	@Transactional
	public WechatToken updateTokenByTimeService(Integer id, String appId, String appSecret) {
		WechatToken token =  tokenDao.findTokenById(id);
		if (token.getAccessToken()!= null) {
			long currentTime = System.currentTimeMillis();
			long createTime = token.getCreateTime();
				if ((currentTime - createTime) > token.getExpiresIn() * 1000) {
				WechatToken newtoken = RequestToken.getToken(appId, appSecret);
				if (null != newtoken) {
					token.setAccessToken(newtoken.getAccessToken());
					token.setExpiresIn(newtoken.getExpiresIn());
					token.setAppId(appId);
					token.setCreateTime(System.currentTimeMillis());
				}
				tokenDao.updateToken(token);
			}
		} else {
			WechatToken newtoken = RequestToken.getToken(appId, appSecret);
			if (null != newtoken) {
				token.setAccessToken(newtoken.getAccessToken());
				token.setExpiresIn(newtoken.getExpiresIn());
				token.setAppId(appId);
				token.setCreateTime(System.currentTimeMillis());
			}
			tokenDao.updateToken(token);
		}
		return token;
	}
	@Override
	@Transactional
	public boolean updateTokenService(Integer id) {
		boolean flag = false;
		try{
			Wechat wechat = wechatDao.getWechatById(id);
			WechatToken newtoken = RequestToken.getToken(wechat.getAppId(),wechat.getAppSecret());
			WechatToken token = wechat.getWechatToken();
			if (null != newtoken) {
				token.setAccessToken(newtoken.getAccessToken());
				token.setExpiresIn(newtoken.getExpiresIn());
				token.setAppId(wechat.getAppId());
				token.setCreateTime(System.currentTimeMillis());
				tokenDao.updateToken(token);
				flag = true;
			}else{
				flag = false;
			}
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
}
