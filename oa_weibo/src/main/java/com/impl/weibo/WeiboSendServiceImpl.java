package com.impl.weibo;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.oa_bean.weibo.WeiboMedia;
import org.oa_common.date.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import weibo4j.Timeline;
import weibo4j.http.ImageItem;
import weibo4j.model.Status;
import weibo4j.model.WeiboException;

import com.dao.weibo.WeiboMediaDao;
import com.dao.weibo.WeiboSendService;

@Service(value="weiboSendService")
public class WeiboSendServiceImpl implements WeiboSendService {
	private WeiboMediaDao weiboMediaDao;
	
	@Autowired
	public void setWeiboMediaDao(WeiboMediaDao weiboMediaDao) {
		this.weiboMediaDao = weiboMediaDao;
	}
	@Override
	@Transactional
	public WeiboMedia sendWeiboMediaNewsMessageService(WeiboMedia weiboMedia,
			String fileUrl , String accessToken) {
		try {
			try {
				byte[] content = readFileImage(fileUrl);
				ImageItem pic = new ImageItem("pic", content);
				String s = java.net.URLEncoder.encode(weiboMedia.getText(), "utf-8");
				Timeline tm = new Timeline(accessToken);
				Status status = tm.uploadStatus(s, pic);
				weiboMedia.setStatusId(status.getId());
				weiboMedia.setMid(status.getMid());
				weiboMedia.setErrcode(0);
			} catch (WeiboException e1 ) {
				weiboMedia.setErrcode(e1.getErrorCode());
			}
			String createTime = DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
			weiboMedia.setCreateTime(createTime);
			weiboMediaDao.addWeiboMedia(weiboMedia);
		} catch (Exception ioe) {
			weiboMedia.setErrcode(-1);
		}
		return weiboMedia;
	}
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
	@Transactional
	public WeiboMedia sendWeiboMediaTextMessageService(WeiboMedia weiboMedia,String accessToken) {
		try {
			try {
				Timeline tm = new Timeline(accessToken);
				Status status = tm.updateStatus(weiboMedia.getText());
				weiboMedia.setStatusId(status.getId());
				weiboMedia.setMid(status.getMid());
				weiboMedia.setErrcode(0);
			} catch (WeiboException e1 ) {
				weiboMedia.setErrcode(e1.getErrorCode());
			}
			String createTime = DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
			weiboMedia.setCreateTime(createTime);
			weiboMediaDao.addWeiboMedia(weiboMedia);
		} catch (Exception e) {
			weiboMedia.setErrcode(-1);
		}	
		return weiboMedia;
	}
	@Override
	public List<WeiboMedia> getWeiboMediaSendResult(String pubId) {
		List<WeiboMedia> list= weiboMediaDao.getWeiboMediaByPubId(pubId);
		return list;
	}
	@Override
	@Transactional
	public WeiboMedia resendSendWeiboMediaNewsMessageService(WeiboMedia weiboMedia,
			String fileUrl, String accessToken) {
		try {
			try {
				byte[] content = readFileImage(fileUrl);
				ImageItem pic = new ImageItem("pic", content);
				String s = java.net.URLEncoder.encode(weiboMedia.getText(), "utf-8");
				Timeline tm = new Timeline(accessToken);
				Status status = tm.uploadStatus(s, pic);
				weiboMedia.setStatusId(status.getId());
				weiboMedia.setMid(status.getMid());
				weiboMedia.setErrcode(0);
			} catch (WeiboException e1 ) {
				weiboMedia.setErrcode(e1.getErrorCode());
			}
			String createTime = DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
			weiboMedia.setCreateTime(createTime);
			weiboMediaDao.updateWeiboMedia(weiboMedia);
		} catch (Exception ioe) {
			weiboMedia.setErrcode(-1);
		}
		return weiboMedia;
	}
	@Override
	@Transactional
	public WeiboMedia resendSendWeiboMediaTextMessageService(WeiboMedia weiboMedia,
			String accessToken) {
		try {
			try {
				Timeline tm = new Timeline(accessToken);
				Status status = tm.updateStatus(weiboMedia.getText());
				weiboMedia.setStatusId(status.getId());
				weiboMedia.setMid(status.getMid());
				weiboMedia.setErrcode(0);
			} catch (WeiboException e1 ) {
				weiboMedia.setErrcode(e1.getErrorCode());
			}
			String createTime = DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss","time-nw.nist.gov",2500);
			weiboMedia.setCreateTime(createTime);
			weiboMediaDao.updateWeiboMedia(weiboMedia);
		} catch (Exception e) {
			weiboMedia.setErrcode(-1);
		}	
		return weiboMedia;
	}
}
