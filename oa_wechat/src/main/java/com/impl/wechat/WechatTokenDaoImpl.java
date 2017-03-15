package com.impl.wechat;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.wechat.WechatToken;
import org.springframework.stereotype.Repository;

import com.dao.wechat.WechatTokenDao;
import com.impl.BaseDaoImpl;

@Repository(value="wechatTokenDao")
public class WechatTokenDaoImpl  extends BaseDaoImpl implements WechatTokenDao {

	@Override
	public void addToken(WechatToken token) {
		save(token);
	}

	@Override
	public void updateToken(WechatToken token) {
		update(token);
	}

	@Override
	public void deleteToken(String ids) {
		String []entityids = ids.split(",");
		for(int i=0;i<entityids.length;i++){
			String sql = "delete from wx_token where id="+entityids[i];
			createSqlMethod(sql).executeUpdate();
		}
	}

	@Override
	public Page<WechatToken> getPageToken(Page<WechatToken> page, String wheresql,
			List<Object> queryParams, LinkedHashMap<String, String> orderby) {
		return getScrollData(page, WechatToken.class, wheresql, queryParams, orderby);
	}

	@Override
	public List<WechatToken> getRecentTokenByAppId(String appId) {
		String hql = "select t from WehcatToken t where t.appId = '"+appId+"' order by t.createTime desc ";
		return findObjectList(hql);
	}

	@Override
	public void updateTokenByAppId(WechatToken token) {
		StringBuffer sql = new StringBuffer();
		sql.append("update wx_token set ");
		sql.append("access_token = '"+token.getAccessToken()+"',");
		sql.append("expiresIn = '"+token.getExpiresIn()+"',");
		sql.append("create_time = '"+token.getCreateTime()+"'");
		sql.append(" where app_id = '"+token.getAppId()+"',");
		createSqlMethod(sql.toString()).executeUpdate();
	}

	@Override
	public WechatToken findTokenById(Integer id) {
		// TODO Auto-generated method stub
		return find(WechatToken.class, id);
	}
}
