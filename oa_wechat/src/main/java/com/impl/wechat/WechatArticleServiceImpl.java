package com.impl.wechat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.oa_bean.Page;
import org.oa_bean.wechat.WechatArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.wechat.WechatArticleDao;
import com.dao.wechat.WechatArticleService;
import com.server.wechat.RequestUploadMedia;
@Service(value="wechatArticleService")
public class WechatArticleServiceImpl implements WechatArticleService {
	private WechatArticleDao wechatArticleDao;
	

	public WechatArticleDao getWechatArticleDao() {
		return wechatArticleDao;
	}
	@Autowired
	public void setWechatArticleDao(WechatArticleDao wechatArticleDao) {
		this.wechatArticleDao = wechatArticleDao;
	}

	@Override
	@Transactional
	public boolean addSingleWechatArticleService(WechatArticle wechatArticle,String accessToken) {
		boolean flag = false;
		try{
			String jsonStr  = "{\"articles\":[{\"thumb_media_id\":\""+wechatArticle.getThumbMediaId()+"\",\"author\":\""+wechatArticle.getAuthor()+"\","
					+ "\"title\":\""+wechatArticle.getTitle()+"\",\"content_source_url\":\""+wechatArticle.getContentSourceUrl()+"\",\"content\":\""+wechatArticle.getContent()+"\","
					+ "\"digest\":\""+wechatArticle.getDigest()+"\",\"show_cover_pic\":\"1\"}]}";
			JSONObject jsonObject = RequestUploadMedia.uploadNewsMedia(accessToken, jsonStr);
			if(jsonObject!=null){
				try{
					wechatArticle.setMediaId(jsonObject.getString("media_id"));
					wechatArticle.setErrcode(0);
				}catch(JSONException e){
					wechatArticle.setErrcode(jsonObject.getInt("errcode"));
				}
			}
			wechatArticleDao.addWechatArticle(wechatArticle);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateWechatArticleService(WechatArticle wechatArticle,String accessToken) {
		boolean flag = false;
		try{
			String jsonStr  = "{\"articles\":[{\"thumb_media_id\":\""+wechatArticle.getThumbMediaId()+"\",\"author\":\""+wechatArticle.getAuthor()+"\","
					+ "\"title\":\""+wechatArticle.getTitle()+"\",\"content_source_url\":\""+wechatArticle.getContentSourceUrl()+"\",\"content\":\""+wechatArticle.getContent()+"\","
					+ "\"digest\":\""+wechatArticle.getDigest()+"\",\"show_cover_pic\":\"1\"}]}";
			JSONObject jsonObject = RequestUploadMedia.uploadNewsMedia(accessToken, jsonStr);
			if(jsonObject!=null){
				try{
					wechatArticle.setMediaId(jsonObject.getString("media_id"));
					wechatArticle.setErrcode(0);
				}catch(JSONException e){
					wechatArticle.setErrcode(jsonObject.getInt("errcode"));
				}
			}
			wechatArticleDao.updateWechatArticle(wechatArticle);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteWechatArticleService(Integer id) {
		boolean flag = false;
		try{
			wechatArticleDao.deleteWechatArticle(id);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	public WechatArticle getWechatArticleById(Integer id) {
		return wechatArticleDao.getWechatArticleById(id);
	}

	@Override
	public Page<WechatArticle> getPageWechatArticle(Page<WechatArticle> page,
			WechatArticle wechatArticle, LinkedHashMap<String, String> orderby,String startTime,String endTime) {
		StringBuilder wheresql = new StringBuilder("1=1");
		List<Object> params = new ArrayList<Object>(); 
		if(!"".equals(wechatArticle.getAppId()) && wechatArticle.getAppId()!=null){
			String[] appIds = wechatArticle.getAppId().split(",");
			wheresql.append(" and  appId in(");
			for (int i = 0; i < appIds.length; i++) {
				wheresql.append("?");
				wheresql.append(",");
				params.add(appIds[i]);
			}
			wheresql = wheresql.deleteCharAt(wheresql.length()-1);
			wheresql.append(")");
		}
		if(!"".equals(wechatArticle.getTitle()) && wechatArticle.getTitle()!=null){
			wheresql.append(" and title like ? ");
			params.add("%"+wechatArticle.getTitle()+"%");
		}
		if(!"".equals(wechatArticle.getPubId()) && wechatArticle.getPubId()!=null){
			wheresql.append(" and pubId = ? ");
			params.add(wechatArticle.getPubId());
		}
		if(!"".equals(startTime) && startTime!=null){
			wheresql.append(" and createTime > ? ");
			params.add(startTime);
		}
		if(!"".equals(endTime) && endTime!=null){
			wheresql.append(" and createTime < ? ");
			params.add(endTime);
		}
		return wechatArticleDao.getPageWechatArticle(page, wheresql.toString(), params, orderby);
	}

}
