package com.impl.wechat;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.oa_bean.massmsg.MassMsg;
import org.oa_bean.wechat.WechatArticle;
import org.oa_bean.wechat.WechatMedia;
import org.oa_common.date.DateTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.massmsg.MassMsgDao;
import com.dao.wechat.WechatArticleDao;
import com.dao.wechat.WechatMediaDao;
import com.dao.wechat.WechatSendService;
import com.server.wechat.RequestUploadMedia;
import com.server.wechat.SendInitiativeMessage;

@Service(value = "wchatSendService")
public class WechatSendServiceImpl implements WechatSendService {
	private MassMsgDao massMsgDao;
	private WechatMediaDao wechatMediaDao;
	private WechatArticleDao wechatArticleDao;

	@Autowired
	public void setWechatMediaDao(WechatMediaDao wechatMediaDao) {
		this.wechatMediaDao = wechatMediaDao;
	}

	@Autowired
	public void setWechatArticleDao(WechatArticleDao wechatArticleDao) {
		this.wechatArticleDao = wechatArticleDao;
	}

	@Autowired
	public void setMassMsgDao(MassMsgDao massMsgDao) {
		this.massMsgDao = massMsgDao;
	}

	@Override
	@Transactional
	public MassMsg sendWehcatDefaultGroupNewsMessageService(MassMsg massMsg,WechatArticle wechatArticle, String pathUrl, String accessToken) {
		String failureLink = "0";
		String content = wechatArticle.getContent();
		String digest ;
		if(content.length()>45){
			digest = content.substring(0, 45);
		}else{
			digest = content;
		}
		String createTime = DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss",
				"time-nw.nist.gov", 2500);
		try {
			String thumbMediaId = "";
			WechatMedia wechatMedia = new WechatMedia();
			// 上传文章里面的图片素材
			JSONObject mediajsonObject = RequestUploadMedia.uploadMedia(
					accessToken, pathUrl, "image");
			wechatMedia.setPubId(wechatArticle.getPubId());
			wechatMedia.setType("image");
			wechatMedia.setLocalUrl(pathUrl);
			wechatMedia.setCreateTime(createTime);
			try {
				wechatMedia.setMediaId(mediajsonObject.getString("media_id"));
				wechatMedia.setUrl(mediajsonObject.getString("url"));
				thumbMediaId = mediajsonObject.getString("media_id");
				wechatMedia.setErrcode(0);
				wechatMediaDao.addWechatMedia(wechatMedia);
			} catch (JSONException e) {
				wechatMedia.setErrcode(-1);
				failureLink = "-1";
			}
			if("0".equals(failureLink)){
				String media_id = "";
				// 保存文章
				String articlesJson = "{\"articles\":[{\"thumb_media_id\":\""
						+ thumbMediaId + "\",\"author\":\""
						+ wechatArticle.getAuthor() + "\"," + "\"title\":\""
						+ wechatArticle.getTitle() + "\",\"content_source_url\":\""
						+ wechatArticle.getContentSourceUrl() + "\",\"content\":\""
						+ content + "\"," + "\"digest\":\"" + digest
						+ "\",\"show_cover_pic\":\"1\"}]}";
				JSONObject articlesjsonObject = RequestUploadMedia.uploadNewsMedia(
						accessToken, articlesJson);
				wechatArticle.setThumbMediaId(thumbMediaId);
				wechatArticle.setContent(content);
				wechatArticle.setDigest(digest);
				wechatArticle.setCreateTime(createTime);
				try {
					media_id = articlesjsonObject.getString("media_id");
					wechatArticle.setMediaId(media_id);
					wechatArticle.setErrcode(0);
					wechatArticleDao.addWechatArticle(wechatArticle);
				} catch (JSONException e) {
					wechatArticle.setErrcode(-1);
					failureLink = "-2";
				}
				
				if("0".equals(failureLink)){
					// 保存发送信息
					String groupjson = "{\"filter\":{\"is_to_all\":false,\"group_id\":\"0\"},"
							+ " \"mpnews\":{\"media_id\":\""
							+ media_id
							+ "\"},"
							+ "\"msgtype\":\"mpnews\"}";
					massMsg.setSendJson(groupjson);
					massMsg.setMediaId(media_id);
					JSONObject massMsgjsonObject = SendInitiativeMessage
							.sendGroupMessage(accessToken, groupjson);
					try {
						massMsg.setMsgDataId(massMsgjsonObject.getString("msg_data_id"));
						massMsg.setMsgId(massMsgjsonObject.getString("msg_id"));
						massMsg.setErrcode(0);
					} catch (JSONException e) {
						massMsg.setErrcode(massMsgjsonObject.getInt("errcode"));
						failureLink = "-3";
					}
				}
			}
			massMsg.setSendTime(createTime);
			massMsg.setSendType("5");
			massMsg.setFilter("group");
			massMsg.setMsgtype("news");
			massMsgDao.addMassMsg(massMsg);
		} catch (Exception e) {
			massMsg.setErrcode(-1);
		}
		return massMsg;
	}

	@Override
	@Transactional
	public MassMsg sendWehcatDefaultGroupTextMessageService(MassMsg massMsg,
			String accessToken, String content) {
		try {
			String createTime = DateTools.NetWorkTime("yyyy-MM-dd HH:mm:ss",
					"time-nw.nist.gov", 2500);
			String json = "{\"filter\":{\"is_to_all\":false,\"group_id\":\"0\"},"
					+ " \"text\":{\"content\":\""
					+ content
					+ "\"},"
					+ "\"msgtype\":\"text\"}";
			massMsg.setSendType("5");
			massMsg.setFilter("group");
			massMsg.setMsgtype("text");
			massMsg.setSendJson(json);
			massMsg.setSendTime(createTime);
			JSONObject jsonObject = SendInitiativeMessage.sendGroupMessage(
					accessToken, json);
			if (null != jsonObject) {
				try {
					massMsg.setMsgId(jsonObject.getString("msg_id"));
					massMsg.setErrcode(0);
				} catch (JSONException e) {
					massMsg.setErrcode(jsonObject.getInt("errcode"));
				}
				massMsgDao.addMassMsg(massMsg);
			}
		} catch (Exception e) {
			massMsg.setErrcode(-1);
		}
		return massMsg;
	}

	@Override
	@Transactional
	public MassMsg sendWehcatDefaultGroupImageMessageService(MassMsg massMsg,
			String accessToken, String media_id) {
		try {
			String json = "{\"filter\":{\"is_to_all\":false,\"group_id\":\"0\"},"
					+ " \"image\":{\"media_id\":\""
					+ media_id
					+ "\"},"
					+ "\"msgtype\":\"image\"}";
			massMsg.setSendType("5");
			massMsg.setFilter("group");
			massMsg.setMsgtype("image");
			massMsg.setSendJson(json);
			massMsg.setMediaId(media_id);

			JSONObject jsonObject = SendInitiativeMessage.sendGroupMessage(
					accessToken, json);
			if (null != jsonObject) {
				try {
					massMsg.setMsgId(jsonObject.getString("msg_id"));
					massMsg.setErrcode(0);
				} catch (JSONException e) {
					massMsg.setErrcode(jsonObject.getInt("errcode"));
				}
				massMsgDao.addMassMsg(massMsg);
			}
		} catch (Exception e) {
			massMsg.setErrcode(-1);
		}
		return massMsg;
	}

	@Override
	public MassMsg sendWehcatDefaultGroupNewsMessageService(MassMsg massMsg,
			String accessToken, String media_id) {
		try {
			// 保存发送信息
			String groupjson = "{\"filter\":{\"is_to_all\":false,\"group_id\":\"0\"},"
					+ " \"mpnews\":{\"media_id\":\""
					+ media_id
					+ "\"},"
					+ "\"msgtype\":\"mpnews\"}";
			massMsg.setSendJson(groupjson);
			massMsg.setSendType("5");
			massMsg.setFilter("group");
			massMsg.setMsgtype("news");
			massMsg.setMediaId(media_id);
			JSONObject massMsgjsonObject = SendInitiativeMessage
					.sendGroupMessage(accessToken, groupjson);
			if (null != massMsgjsonObject) {
				try {
					massMsg.setMsgDataId(massMsgjsonObject
							.getString("msg_data_id"));
					massMsg.setMsgId(massMsgjsonObject.getString("msg_id"));
					massMsg.setErrcode(0);
				} catch (JSONException e) {
					massMsg.setErrcode(massMsgjsonObject.getInt("errcode"));
				}
				massMsgDao.addMassMsg(massMsg);
			}
		} catch (Exception e) {
			massMsg.setErrcode(-1);
		}
		return massMsg;
	}
}
