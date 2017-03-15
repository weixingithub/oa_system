package com.impl.comment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.comment.CommentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.comment.CommentInfoDao;
import com.dao.comment.CommentInfoService;
@Service(value="commentInfoService")
public class CommentInfoServiceImpl implements CommentInfoService{
	private CommentInfoDao commentInfoDao;
	@Autowired
	public void setCommentInfoDao(CommentInfoDao commentInfoDao) {
		this.commentInfoDao = commentInfoDao;
	}
	@Override
	@Transactional
	public boolean addCommentInfoService(CommentInfo commentInfo) {
		boolean flag = false;
		try{
			commentInfoDao.addCommentInfo(commentInfo);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateCommentInfoService(CommentInfo commentInfo) {
		boolean flag = false;
		try{
			commentInfoDao.updateCommentInfo(commentInfo);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteCommentInfoService(Integer commentInfoId) {
		boolean flag = false;
		try{
			commentInfoDao.deleteCommentInfo(commentInfoId);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteCommentInfoService(String commentInfoIds) {
		boolean flag = false;
		try{
			commentInfoDao.deleteCommentInfo(commentInfoIds);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	public CommentInfo findCommentInfoById(Integer commentInfoId) {
		return commentInfoDao.findCommentInfoById(commentInfoId);
	}

	@Override
	public Page<CommentInfo> findCommentInfoPage(Page<CommentInfo> page,
			CommentInfo commentInfo, LinkedHashMap<String, String> orderby) {
		StringBuilder wheresql = new StringBuilder("1=1");
		List<Object> params = new ArrayList<Object>();
		return commentInfoDao.findCommentInfoPage(page, wheresql.toString(), params, orderby);
	}
	@Override
	@Transactional
	public boolean addPraiseNumber(Integer commentInfoId) {
		boolean flag = false;
		try{
			commentInfoDao.addPraiseNumber(commentInfoId);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	@Transactional
	public boolean addNegativeNumber(Integer commentInfoId) {
		boolean flag = false;
		try{
			commentInfoDao.addNegativeNumber(commentInfoId);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	@Transactional
	public boolean addBrowseNumber(Integer commentInfoId) {
		boolean flag = false;
		try{
			commentInfoDao.addBrowseNumber(commentInfoId);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	
	@Override
	@Transactional
	public boolean updateCommentInfoByIdAndCondition(Integer commentInfoId,String type) {
		Boolean flag = false;
		try {
			String params = "";
			if("zan".equals(type)){
				//赞一下
				params = "praise_number = praise_number + 1";
			}else if("cai".equals(type)){
				//踩一下
				params = "negative_number = negative_number + 1";
			}else if ("browseNum".equals(type)) {
				//阅读量
				params = "browse_number = browse_number + 1";
			}
			commentInfoDao.updateCommentInfoByIdAndCondition(commentInfoId,params);
			flag = true;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	@Transactional
	public boolean updateCommentInfoByIdAndUrl(Integer commentInfoId,String url) {
		Boolean flag = false;
		try {
			if(commentInfoId != null && !"".equals(url)){
				String cloumnDate = "url = '" + url +"'";
				commentInfoDao.updateCommentInfoByIdAndCondition(commentInfoId, cloumnDate);
			}else{
				flag = false;
			}
			flag = true;
		} catch (Exception e) {
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}

}
