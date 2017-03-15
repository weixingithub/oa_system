package com.impl.weibo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.weibo.WeiboMedia;
import org.oa_bean.weibo.WeiboToken;
import org.oa_common.date.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weibo4j.Timeline;
import weibo4j.http.ImageItem;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;

import com.dao.weibo.WeiboMediaDao;
import com.dao.weibo.WeiboMediaService;
@Service(value="weiboMediaService")
public class WeiboMediaServiceImpl implements WeiboMediaService {
	private WeiboMediaDao weiboMediaDao;
	public WeiboMediaDao getWeiboMediaDao() {
		return weiboMediaDao;
	}
	@Autowired
	public void setWeiboMediaDao(WeiboMediaDao weiboMediaDao) {
		this.weiboMediaDao = weiboMediaDao;
	}
	@Override
	@Transactional
	public boolean addWeiboMediaService(WeiboMedia weiboMedia,WeiboToken weiboToken) {
		boolean flag = false;
		try{
			String createTime = DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
			weiboMedia.setCreateTime(createTime);
			weiboMedia.setUid(weiboToken.getUid());
			String access_token = weiboToken.getAccessToken(); 
			Timeline tm = new Timeline(access_token);
			Status status = null;
			if(!"".equals(weiboMedia.getLocalUrl()) &&  weiboMedia.getLocalUrl()!=null){
				byte[] content = readFileImage(weiboMedia.getLocalUrl());
				ImageItem pic = new ImageItem("pic", content);
				String s = java.net.URLEncoder.encode(weiboMedia.getText(), "utf-8");
				status = tm.uploadStatus(s, pic);
			}else{
				status = tm.updateStatus(weiboMedia.getText());
			}
			if(status!=null){
				weiboMedia.setStatusId(status.getId());
				weiboMedia.setMid(status.getMid());
				weiboMedia.setThumbnailPic(status.getThumbnailPic());
				weiboMedia.setBmiddlePic(status.getBmiddlePic());
				weiboMedia.setOriginalPic(status.getOriginalPic());
				weiboMedia.setRepostsCount(status.getRepostsCount());
				weiboMedia.setCommentsCount(status.getCommentsCount());
				weiboMedia.setErrcode(0);
			}else{
				weiboMedia.setErrcode(-1);
			}
			weiboMediaDao.addWeiboMedia(weiboMedia);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateWeiboMediaService(WeiboMedia weiboMedia,WeiboToken weiboToken) {
		boolean flag = false;
		try{
			String updateTime = DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
			weiboMedia.setUpdateTime(updateTime);
			String access_token = weiboToken.getAccessToken(); 
			Timeline tm = new Timeline(access_token);
			Status status = tm.showStatus(weiboMedia.getStatusId());
			if(status!=null){
				weiboMedia.setRepostsCount(status.getRepostsCount());
				weiboMedia.setCommentsCount(status.getCommentsCount());
				weiboMedia.setAttitudesCount(status.getAttitudesCount());
			} 
			weiboMediaDao.updateWeiboMedia(weiboMedia);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteWeiboMediaService(Integer id,String mid,WeiboToken weiboToken) {
		boolean flag = false;
		Timeline tm = new Timeline(weiboToken.getAccessToken());
		try {
			Status status = tm.destroy(mid);
			weiboMediaDao.deleteWeiboMedia(id);
			flag = true;
		} catch (WeiboException e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public Page<WeiboMedia> getPageWeiboMedia(Page<WeiboMedia> page,WeiboMedia weiboMedia,
			LinkedHashMap<String, String> orderby,String startTime,String endTime) {
		StringBuilder wheresql = new StringBuilder("1=1");
		List<Object> params = new ArrayList<Object>();
		if(!"".equals(weiboMedia.getUid())&& weiboMedia.getUid()!=null){
			String[] uids = weiboMedia.getUid().split(",");
			wheresql.append(" and  uid in(");
			for (int i = 0; i < uids.length; i++) {
				wheresql.append("?");
				wheresql.append(",");
				params.add(uids[i]);
			}
			wheresql = wheresql.deleteCharAt(wheresql.length()-1);
			wheresql.append(")");
		}
		if("".equals(startTime) && startTime!=null){
			wheresql.append(" and createTime > ?");
			params.add(startTime);
		}
		if("".equals(endTime)&& endTime!=null){
			wheresql.append(" and createTime < ?");
			params.add(endTime);	
		}
		return weiboMediaDao.getPageWeiboMedia(page, wheresql.toString(), params, orderby);
	}
	@Override
	public List<WeiboMedia> getWeiboMediaByPubId(String pubId) {
		 
		return weiboMediaDao.getWeiboMediaByPubId(pubId);
	}
	/**
	 * 将图片转换为流
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static byte[] readFileImage(String filename) throws IOException {
		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				new FileInputStream(filename));
		int len = bufferedInputStream.available();
		byte[] bytes = new byte[len];
		int r = bufferedInputStream.read(bytes);
		if (len != r) {
			bytes = null;
			throw new IOException("读取文件不正确");
		}
		bufferedInputStream.close();
		return bytes;
	}
	@Override
	public WeiboMedia getWeiboMediaById(Integer id) {
		return  weiboMediaDao.getWeiboMediaById(id);
	}
}
