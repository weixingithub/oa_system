package com.server.wechat;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.oa_common.http.HttpThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件素材上传下载功能
 * @author Administrator
 *
 */
public class RequestUploadMedia {
	private static Logger log = LoggerFactory.getLogger(RequestUploadMedia.class);
	// 新增临时素材 
	public final static String UPLOAD_TEMP_MEDIA_URl = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	// 获取临时素材 
	public final static String DOWNLOAD_TEMP_MEDIA_URl = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	// 新增永久图片素材(只返回url)
	public final static String UPLOAD_PICTURE_MEDIA_URl = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=ACCESS_TOKEN";
	// 新增永久多类型素材
	public final static String UPLOAD_MEDIA_URl = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=ACCESS_TOKEN";
	// 新增永久图文素材
	public final static String UPLOAD_NEWS_MEDIA_URl = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
	// 获取用具素材 
	public final static String DOWNLIAD_MEDIA_URL="https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
	// 删除永久素材（get）
	public final static String DELETE_MEDIA_URL="https://api.weixin.qq.com/cgi-bin/material/del_material?access_token=ACCESS_TOKEN";
	// 修改永久素材(post)
	public final static String UPDATE_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=ACCESS_TOKEN";
	// 获取素材总数
	public final static String MEDIA_COUNT_URL="https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN";
	// 获取素材列表
	public final static String MEDIA_LIST_URL="https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=ACCESS_TOKEN";
	//参数名称
	public final static String PARAMETER_FILE_NAME = "media";

	/**
	 * 
	 * 新增临时多媒体文件  （媒体文件在后台保存时间为3天，即3天后media_id失效。）
	 * @param access_token
	 * @param type 图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * 图片（image）: 1M，支持JPG格式
	 * 语音（voice）：2M，播放长度不超过60s，支持AMR\MP3\SPEEX格式
	 * 视频（video）：10MB，支持MP4格式
	 * 缩略图（thumb）：64KB，支持JPG格式
	 * 
	 * access_token 	调用接口凭证
	 * type 	媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * media    form-data中媒体文件标识，有filename、filelength、content-type等信息 
	 * 
	 * @param fileUrl
	 * @param accessToken
	 * @param type
	 * @return
	 * 
	 * 返回数据{"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
	 * 
	 *  type 	媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb，主要用于视频与音乐格式的缩略图）
	 *  media_id 	媒体文件上传后，获取时的唯一标识
	 *  created_at 	媒体文件上传时间戳 
	 */
	public static JSONObject uploadTempMedia(String accessToken, String type,String fileUrl) {
		String apiUrl = UPLOAD_TEMP_MEDIA_URl.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil.doPostFileSSL(apiUrl, fileUrl, PARAMETER_FILE_NAME));
		if (null != jsonObject) {
			try {
				String media_id =  jsonObject.getString("media_id");
			} catch (JSONException e) {
				log.error("新增临时素材失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return jsonObject;
	}
	/**
	 * 获取临时素材
	 * @param accessToken  调用接口凭证
	 * @param mediaId    媒体文件ID 
	 * @return
	 */
	public static JSONObject downloadTempMedia(String accessToken, String mediaId){
		String apiUrl =DOWNLOAD_TEMP_MEDIA_URl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil.doGetSSL(apiUrl));
		if (null != jsonObject) {
			try {
				String fileUrl = jsonObject.getString("");
			} catch (JSONException e) {
				log.error("获取临时素材失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
			return jsonObject;
	}
	/**
	 * 新增永久图片素材
	 * access_token 	是 	调用接口凭证
	 * media 	是 	form-data中媒体文件标识，有filename、filelength、content-type等信息 
	 * @param accessToken
	 * @param fileUrl
	 * @return
	 * { "url":  "http://mmbiz.qpic.cn/mmbiz/gLO17UPS6FS2xsypf378iaNhWacZ1G1UplZYWEYfwvuU6Ont96b1roYs CNFwaRrSaKTPCUdBK9DgEHicsKwWCBRQ/0"}
	 * 其中url就是上传图片的URL，可用于后续群发中，放置到图文消息中。 
	 */
	public static JSONObject uploadPictureMedia(String accessToken,String fileUrl) {
		String apiUrl = UPLOAD_PICTURE_MEDIA_URl.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil.doPostFileSSL(apiUrl, fileUrl, PARAMETER_FILE_NAME));
		if (null != jsonObject) {
			try {
				String pictureUrl = jsonObject.getString("url");
			} catch (JSONException e) {
				log.error("新增永久多类型素材失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return jsonObject;
	}
	/**
	 * 更新永久图片素材
	 * access_token 	是 	调用接口凭证
	 * @param accessToken
	 * @param json
	 * @return
	 * { "media_id":MEDIA_ID, "index":INDEX,
  			"articles": 
  			{ 
  				"title": TITLE, 
	  			"thumb_media_id": THUMB_MEDIA_ID, 
	  			"author": AUTHOR, "digest": DIGEST,
	  			"show_cover_pic": SHOW_COVER_PIC(0 / 1),
	       		"content": CONTENT,
	       		"content_source_url": CONTENT_SOURCE_URL
    		}
		}
	 */
	public static JSONObject updatePictureMedia(String accessToken,String json) {
		String apiUrl = UPLOAD_PICTURE_MEDIA_URl.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil.doPostSSL(apiUrl, json));
		if (null != jsonObject) {
			try {
				String errcode = jsonObject.getString("errcode");
			} catch (JSONException e) {
				log.error("修改永久多类型素材失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return jsonObject;
	}
	/**
	 * 新增永久多类型素材(不支持视频)
	 * 
	 * access_token 	是 	调用接口凭证
	 * type 	是 	媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
	 * media 	是 	form-data中媒体文件标识，有filename、filelength、content-type等信息 
	 * @param accessToken
	 * @param fileUrl
	 * @return
	 * { "media_id":MEDIA_ID, "url":URL }
	 * media_id 	新增的永久素材的media_id
	 * url 	新增的图片素材的图片URL（仅新增图片素材时会返回该字段） 
	 */
	public static JSONObject uploadMedia(String accessToken,String fileUrl ,String type) {
		String apiUrl = UPLOAD_MEDIA_URl.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil.doPostFileSSL(apiUrl, fileUrl, PARAMETER_FILE_NAME));
		if (null != jsonObject) {
			try {
				String media_id =  jsonObject.getString("media_id");
				String url = jsonObject.getString("url");
			} catch (JSONException e) {
				log.error("新增永久多类型素材失败 errcode:{} errmsg:{}",
						jsonObject.getInt("errcode"),
						jsonObject.getString("errmsg"));
			}
		}
		return jsonObject;
	}
	/**
	 * 新增永久图文素材
	 * 
	 *  title 	是 	标题
	 *  thumb_media_id 	是 	图文消息的封面图片素材id（必须是永久mediaID）
	 *  author 	是 	作者
	 *  digest 	是 	图文消息的摘要，仅有单图文消息才有摘要，多图文此处为空
	 * 	show_cover_pic 	是 	是否显示封面，0为false，即不显示，1为true，即显示
	 *  content 	是 	图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS
	 *  content_source_url 	是 	图文消息的原文地址，即点击“阅读原文”后的URL 
	 * 
	 * {"articles":
	 * 		 [{ "title": TITLE, "thumb_media_id": THUMB_MEDIA_ID, "author": AUTHOR,  "digest": DIGEST, 
	 * 			"show_cover_pic": SHOW_COVER_PIC(0 / 1),  "content": CONTENT, "content_source_url": CONTENT_SOURCE_URL }
	 *  	]}
	 * //若新增的是多图文素材，则此处应有几段articles结构，最多8段
	 * @param accessToken
	 * @param jsonNews
	 * @return
	 * { "media_id":MEDIA_ID }
	 * 返回的即为新增的图文消息素材的media_id。
	 * 请注意，在图文消息的具体内容中，将过滤外部的图片链接，开发者可以通过下述接口上传图片得到URL，放到图文内容中使用
	 */
	public static JSONObject uploadNewsMedia(String accessToken,String jsonNews) {
		String apiUrl = UPLOAD_NEWS_MEDIA_URl.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil.doPostSSL(apiUrl, jsonNews));
		 if (null != jsonObject) {
				try {
					String media_id = jsonObject.getString("media_id");
				} catch (JSONException e) {
					log.error("新增永久图文素材失败 errcode:{} errmsg:{}",
							jsonObject.getInt("errcode"),
							jsonObject.getString("errmsg"));
				}
			}
		 return jsonObject;
	}
	/**
	 * 获取媒体文件
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param mediaId
	 *            媒体文件id
	 * @param savePath 文件在本地服务器上的存储路径
	 * */
	public String downloadMedia(String accessToken, String mediaId,String savePath) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String filePath = null;
		String pathurl = null;
		// 拼接请求地址
		String requestUrl = DOWNLOAD_TEMP_MEDIA_URl.replace("ACCESS_TOKEN",
				accessToken).replace("MEDIA_ID", mediaId);
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// 根据内容类型获取扩展名
			String fileExt = getFileExt(conn.getHeaderField("Content-Type"));

			String chagepath = sdf.format(new Date()) + "/";
			File filedir = new File(savePath + chagepath);
			if (!filedir.exists()) {
				filedir.mkdirs();
			}
			// 将mediaId作为文件名
			filePath = savePath + chagepath + new Date().getTime() + fileExt;
			pathurl = new Date().getTime() + "%" + sdf.format(new Date()) + "/"
					+ new Date().getTime() + fileExt;
			BufferedInputStream bis = new BufferedInputStream(
					conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[1024 * 1024 * 50];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();
			conn.disconnect();
			String info = String.format("下载媒体文件成功，filePath=" + filePath);
			System.out.println(info);
		} catch (Exception e) {
			filePath = null;
			String error = String.format("下载媒体文件失败：%s", e);
			System.out.println(error);
		}
		return pathurl;
	}

	/**
	 * 根据内容类型判断文件扩展名
	 * 
	 * @param contentType
	 *            内容类型
	 * @return
	 */
	public static String getFileExt(String contentType) {
		String fileExt = "";
		if ("image/jpeg".equals(contentType))
			fileExt = ".jpg";
		else if ("audio/mpeg".equals(contentType))
			fileExt = ".mp3";
		else if ("audio/amr".equals(contentType))
			fileExt = ".amr";
		else if ("video/mp4".equals(contentType))
			fileExt = ".mp4";
		else if ("video/mpeg4".equals(contentType))
			fileExt = ".mp4";
		return fileExt;
	}
	/**
	 * 获取永久素材个数
	 * @param accessToken
	 * @return
	 */
	public static JSONObject getMediaCount(String accessToken){
		String apiUrl = MEDIA_COUNT_URL.replace("ACCESS_TOKEN", accessToken);
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil.doGetSSL(apiUrl));
		 if (null != jsonObject) {
				try {
					String result = jsonObject.toString();
				} catch (JSONException e) {
					log.error("新增永久图文素材失败 errcode:{} errmsg:{}",
							jsonObject.getInt("errcode"),
							jsonObject.getString("errmsg"));
				}
			}
		return jsonObject;
	}
	/**
	 * 
	 * @param accessToken
	 * @param offset  从全部素材的该偏移位置开始返回，0表示从第一个素材 返回 
	 * @param count 返回素材的数量，取值在1到20之间 
	 * @param type  素材的类型，图片（image）、视频（video）、语音 （voice）、图文（news） 
	 * @return
	 */
	public static JSONObject getMediaList(String accessToken,int offset ,int count,String type ){
		String apiUrl = MEDIA_LIST_URL.replace("ACCESS_TOKEN", accessToken);
		Map<String,Object> params = new  HashMap<String, Object>();
		params.put("type ", type);
		params.put("offset", offset);
		params.put("count", count);
		
		JSONObject jsonObject = JSONObject.fromObject(HttpThreadUtil.doPostSSL(apiUrl, params));
		 if (null != jsonObject) {
				try {
					String result =  jsonObject.toString();
				} catch (JSONException e) {
					log.error("获取素材列表失败 errcode:{} errmsg:{}",
							jsonObject.getInt("errcode"),
							jsonObject.getString("errmsg"));
				}
			}
		return jsonObject;
	}
   public static void main(String[] args) throws IOException {  
		String accessToken="TQP4UZSI9iVomkOlK0bZ_clLV6NZ0OGhbkf3QC9f3fzpd4TSn5xS5m4XJmoBnchCCa71o2F9vdJj-_r5JKaGtAQZxddRpHrkvsok4ZypPZxDse4lTJLaMUSq4bnzUvNwQHWdAIAARG";
	    String type ="news";
		System.out.println(RequestUploadMedia.getMediaList(accessToken, 0, 20, type));

   }    
}
