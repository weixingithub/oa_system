package com.impl.weibo;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.weibo.Weibo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weibo4j.Users;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

import com.dao.weibo.WeiboDao;
import com.dao.weibo.WeiboService;
@Service(value="weiboService")
public class WeiboServiceImpl implements WeiboService{
	private WeiboDao weiboDao ;
	@Autowired
	public void setWeiboDao(WeiboDao weiboDao) {
		this.weiboDao = weiboDao;
	}

	@Override
	@Transactional
	public boolean addWeiboService(Weibo weibo) {
		boolean flag = false;
		try{
			weiboDao.addWeibo(weibo);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateWeiboService(Weibo weibo) {
		boolean flag = false;
		try{
			weiboDao.updateWeibo(weibo);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteWeiboService(Integer id) {
		boolean flag = false;
		try{
			weiboDao.deleteWeibo(id);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	public Weibo findWeiboById(Integer id) {
		Weibo weibo = weiboDao.findWeiboById(id);
//		String access_token = weibo.getWeiboToken().getAccessToken();
//		String uid = weibo.getWeiboToken().getUid();
//		Users um = new Users(access_token);
//		try {
//			User user = um.showUserById(uid);
//			weibo.setUser(user);
//		} catch (WeiboException e) {
//			e.printStackTrace();
//		}
		return weibo;
	}

	@Override
	public List<Weibo> getWeiboByOrgId(String orgIds) {
		return weiboDao.getWeiboByOrgId(orgIds);
	}

	@Override
	public Weibo getWeiboByClientId(String clientId) {
		List<Weibo> list  = weiboDao.getWeiboByClientId(clientId);
		Weibo weibo;
		if(list.size()>0){
			weibo  = list.get(0);
		}else{
			weibo = null;
		}
		return weibo;
	}
	@Override
	public Page<Weibo> getPageWeibo(Page<Weibo> page, Weibo weibo,
			LinkedHashMap<String, String> orderby,String startTime,String endTime) {
		StringBuilder wheresql = new StringBuilder("1=1");
		List<Object> params = new ArrayList<Object>();
		if(!"".equals(weibo.getOrgId()) && weibo.getOrgId()!=null){
				wheresql.append(" and o.orgId in ("+weibo.getOrgId()+")");
		}
		if(!"".equals(weibo.getAppName())&& weibo.getAppName()!=null){
			wheresql.append(" and o.appName like ?");
			params.add("%"+weibo.getAppName()+"%");
		}
		if(!"".equals(weibo.getName())&& weibo.getName()!=null){
			wheresql.append(" and o.name like ?");
			params.add("%"+weibo.getName()+"%");
		}
		if("".equals(startTime) && startTime!=null){
			wheresql.append(" and o.createTime >= ?");
			params.add(startTime);
		}
		if("".equals(endTime)&& endTime!=null){
			wheresql.append(" and o.createTime <= ?");
			params.add(endTime);	
		}
		return weiboDao.getPageWeibo(page, wheresql.toString(), params, orderby);
	}

}
