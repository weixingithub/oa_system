package com.impl.wechat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.oa_bean.Page;
import org.oa_bean.wechat.WechatMedia;
import org.oa_common.date.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.wechat.WechatMediaDao;
import com.dao.wechat.WechatMediaService;
import com.impl.BaseDaoImpl;
import com.server.wechat.RequestUploadMedia;
@Service(value="wechatMediaService")
public class WechatMediaServiceImpl extends BaseDaoImpl implements WechatMediaService {
	private WechatMediaDao wechatMediaDao;
	
	@Autowired
	public void setWechatMediaDao(WechatMediaDao wechatMediaDao) {
		this.wechatMediaDao = wechatMediaDao;
	}
	/**
	 *  新增上传媒体素材
	 * @param wechatMedia
	 * @param accessToken
	 * @return
	 */
	@Override
	@Transactional
	public boolean addWechatMediaService(WechatMedia wechatMedia ,String accessToken){
		//上传媒体素材
		boolean flag = false;
		try {
    		JSONObject jsonObject = RequestUploadMedia.uploadMedia(accessToken, wechatMedia.getLocalUrl(), wechatMedia.getType());
    		String createTime = DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
    		wechatMedia.setCreateTime(createTime);
    		if (null != jsonObject) {
    			try {
    				wechatMedia.setMediaId(jsonObject.getString("media_id"));
    				wechatMedia.setUrl(jsonObject.getString("url"));
    				wechatMedia.setErrcode(0);
    			} catch (JSONException e) {
    				wechatMedia.setErrcode(jsonObject.getInt("errcode"));
    			}
    		}
    		wechatMediaDao.addWechatMedia(wechatMedia);
    		flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	@Override
	@Transactional
	public boolean updateWechatMediaService(WechatMedia wechatMedia) {
		boolean flag = false;
		try{
			wechatMediaDao.updateWechatMedia(wechatMedia);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteWechatMediaService(Integer id) {
		boolean flag = false;
		try{
			wechatMediaDao.deleteWechatMedia(id);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	public WechatMedia getWechatMediaById(Integer id) {
		return wechatMediaDao.getWechatMediaById(id);
	}

	@Override
	public Page<WechatMedia> getPageWechatMedia(Page<WechatMedia> page,
			WechatMedia wechatMedia, LinkedHashMap<String, String> orderby,String startTime,String endTime) {
		StringBuilder wheresql = new StringBuilder("1=1");
		List<Object> params = new ArrayList<Object>(); 
		if(!"".equals(wechatMedia.getAppId())&& wechatMedia.getAppId()!=null){
			String[] appIds = wechatMedia.getAppId().split(",");
				wheresql.append(" and  appId in(");
			for (int i = 0; i < appIds.length; i++) {
				wheresql.append("?");
				wheresql.append(",");
				params.add(appIds[i]);
			}
			wheresql = wheresql.deleteCharAt(wheresql.length()-1);
			wheresql.append(")");
		}
		if(!"".equals(wechatMedia.getPubId())&& wechatMedia.getPubId()!=null){
			wheresql.append(" and  pubId = ? ");
			params.add(wechatMedia.getPubId());
		}
		if(!"".equals(wechatMedia.getType())&& wechatMedia.getType()!=null){
			wheresql.append(" and  type = ? ");
			params.add(wechatMedia.getType());
		}
		if(!"".equals(startTime)&& startTime!=null){
			wheresql.append(" and  createTime > ? ");
			params.add(startTime);
		}
		if(!"".equals(endTime)&& endTime!=null){
			wheresql.append(" and  createTime < ? ");
			params.add(endTime);
		}
		return wechatMediaDao.getPageWechatMedia(page, wheresql.toString(), params, orderby);
	}

}
