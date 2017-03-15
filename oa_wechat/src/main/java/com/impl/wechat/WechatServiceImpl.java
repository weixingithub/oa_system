package com.impl.wechat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.wechat.Wechat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.wechat.WechatDao;
import com.dao.wechat.WechatService;
@Service(value="wechatService")
public class WechatServiceImpl implements WechatService {
	private WechatDao wechatDao;
	
	@Autowired
	public void setWechatDao(WechatDao wechatDao) {
		this.wechatDao = wechatDao;
	}
	@Override
	@Transactional
	public boolean addWechatService(Wechat wechat) {
		boolean flag = false;
		try{
			wechatDao.addWechat(wechat);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateWechatByAppIdService(Wechat wechat) {
		boolean flag = false;
		try{
			wechatDao.updateWechatByAppId(wechat);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteWechatByIdService(Integer id) {
		boolean flag = false;
		try{
			wechatDao.deleteWechatById(id);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	public Page<Wechat> getPageWechat(Page<Wechat> page, Wechat wechat,LinkedHashMap<String, String> orderby,String startTime,String endTime) {
		   StringBuffer wheresql = new StringBuffer("1=1");
		   List<Object> params = new ArrayList<Object>();
		   if(!"".equals(wechat.getOrgId()) && wechat.getOrgId()!=null){
				 String[] arr = wechat.getOrgId().split(",");
					wheresql.append(" and o.orgId in (");
					for(int i=0;i<arr.length;i++){
						wheresql.append(" ? ");
						if(i<arr.length-1){
							wheresql.append(",");
						}
						params.add(arr[i].trim());
					}
					wheresql.append(")");
			}
		    if(!"".equals(wechat.getWebsiteUrl())&&wechat.getWebsiteUrl()!=null){
			   wheresql.append(" and o.websiteUrl like ? ");
			   params.add(wechat.getWebsiteUrl());
			}
			if(!"".equals(wechat.getWechatID())&&wechat.getWechatID()!=null){
				wheresql.append(" and o.weChatID like ? ");
				params.add(wechat.getWechatID());
			}
			if(!"".equals(wechat.getAppSecret())&&wechat.getAppSecret()!=null){
				wheresql.append(" and o.appId like ? ");
				params.add(wechat.getAppSecret());
			}
			if(!"".equals(startTime) && startTime!=null){
				wheresql.append(" and o.createTime > ?");
				params.add(startTime);
			}
			if(!"".equals(endTime)&& endTime!=null){
				wheresql.append(" and o.createTime < ?");
				params.add(endTime);	
			}
		return wechatDao.getPageWechat(page, wheresql.toString(), params, orderby);
	}

	@Override
	public Wechat getWechatByWeChatID(String weChatID) {
		List<Wechat> list =  wechatDao.getWechatByWeChatID(weChatID);
		Wechat weChat = null;
		if(list.size()>0){
			weChat =list.get(0);
		}
		return weChat;
	}
	@Override
	public Wechat getWechatById(Integer id) {
		
		return wechatDao.getWechatById(id);
	}
	@Override
	@Transactional
	public boolean deleteWechatByAppId(String appId) {
		boolean flag = false;
		try{
			wechatDao.deleteWechatByAppId(appId);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	public Wechat getWechatByAppId(String appId) {
		List<Wechat> list =  wechatDao.getWechatByAppId(appId);
		Wechat weChat = null;
		if(list.size()>0){
			weChat =list.get(0);
		}
		return weChat;
	}
	 
	@Override
	@Transactional
	public boolean updateWechatService(Wechat wechat) {
		boolean flag = false;
		try{
			wechatDao.updateWechat(wechat);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	public List<Wechat> getWechatByOrgId(String orgId) {
		 
		return wechatDao.getWechatByOrgId(orgId);
	}
}
