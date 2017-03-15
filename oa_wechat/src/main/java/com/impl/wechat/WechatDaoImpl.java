package com.impl.wechat;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.wechat.Wechat;
import org.springframework.stereotype.Repository;

import com.dao.wechat.WechatDao;
import com.impl.BaseDaoImpl;
@Repository(value="wechatDao")
public class WechatDaoImpl extends BaseDaoImpl implements WechatDao {

	@Override
	public void addWechat(Wechat wechat) {
		save(wechat);
		// TODO Auto-generated method stub
	}

	@Override
	public void updateWechatByAppId(Wechat wechat) {
		StringBuffer sql = new StringBuffer();
		sql.append("update wx_wechat set ");
		if(!"".equals(wechat.getWebsiteUrl())&&wechat.getWebsiteUrl()!=null){
			sql.append(" website_url ='"+wechat.getWebsiteUrl()+"',");
		}
		if(!"".equals(wechat.getWechatID())&&wechat.getWechatID()!=null){
			sql.append(" wechat_id ='"+wechat.getWechatID()+"',");
		}
		sql.append(" app_secret ='"+wechat.getAppSecret()+"'");
		sql.append(" where app_id='"+wechat.getAppId()+"'");
		createSqlMethod(sql.toString()).executeUpdate();
	}

	@Override
	public void deleteWechatById(Integer id) {
		delete(Wechat.class, id);
	}


	@Override
	public List<Wechat> getWechatByWeChatID(String weChatID) {
		String hql = "select w from Wechat w where w.wechatID = '"+weChatID+"'";
		return findObjectList(hql);
	}

	@Override
	public Wechat getWechatById(Integer id) {
		return find(Wechat.class, id);
	}

	@Override
	public Page<Wechat> getPageWechat(Page<Wechat> page, String wheresql, List<Object> queryParams,
			LinkedHashMap<String, String> orderby) {
		// TODO Auto-generated method stub
		return getScrollData(page, Wechat.class, wheresql, queryParams, orderby);
	}

	@Override
	public void updateWechat(Wechat wechat) {
			update(wechat);
	}

	@Override
	public void deleteWechatByAppId(String appId) {
		String sql = "delete from w_wechat where app_id = '"+appId+"'";
		createSqlMethod(sql).executeUpdate();
	}

	@Override
	public List<Wechat> getWechatByAppId(String appId) {
		String hql = "select w from Wechat w where w.appId = '"+appId+"'";
		return findObjectList(hql);
	}

	@Override
	public List<Wechat> getWechatByOrgId(String orgId) {
		String hql = "select w from Wechat w where 1=1 ";
		 if(!"".equals(orgId) && orgId!=null){
			 hql += " and  w.orgId = '"+orgId+"'";
		 }		
		return findObjectList(hql);
	}

}
