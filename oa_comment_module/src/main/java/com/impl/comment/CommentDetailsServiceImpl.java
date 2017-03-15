package com.impl.comment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.comment.CommentDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.comment.CommentDetailsDao;
import com.dao.comment.CommentDetailsService;

@Service(value="commentDetailsService")
public class CommentDetailsServiceImpl implements CommentDetailsService{
	private CommentDetailsDao commentDetailsDao;
	
	@Autowired
	public void setCommentDetailsDao(CommentDetailsDao commentDetailsDao) {
		this.commentDetailsDao = commentDetailsDao;
	}

	@Override
	@Transactional
	public boolean addCommentDetailsService(CommentDetails commentDetails,Integer commentInfoId) {
		boolean flag = false;
		try{
			commentDetailsDao.addCommentDetails(commentDetails,commentInfoId);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean updateCommentDetailsService(CommentDetails commentDetails,Integer commentInfoId) {
		boolean flag = false;
		try{
			commentDetailsDao.updateCommentDetails(commentDetails,commentInfoId);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteCommentDetails(Integer contentId,Integer recFlag) {
		boolean flag = false;
		try{
			if (recFlag == 1) {
				//已回复，需要同时删除该评论的回复
				commentDetailsDao.deleteCommentDetails(contentId);
				commentDetailsDao.deleteRecContenId(contentId);
			}else if(recFlag == 2){
				//未回复，只需删除评论本身即可
				commentDetailsDao.deleteCommentDetails(contentId);
			}
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}

	@Override
	@Transactional
	public boolean deleteCommentDetailsByCommentInfo(
			Integer commentInfoId) {
		boolean flag = false;
		try{
			commentDetailsDao.deleteCommentDetailsByCommentInfo(commentInfoId);
			flag = true;
		}catch(Exception e){
			flag = false;
		}
		return flag;
	}
	@Override
	@Transactional
	public CommentDetails findCommentDetailsById(Integer contentId) {
		return commentDetailsDao.findCommentDetailsById(contentId);
	}

	@Override
	public List<CommentDetails> findCommentDetailsByCommentInfo(
			Integer commentInfoId) {
		return commentDetailsDao.findCommentDetailsByCommentInfo(commentInfoId);
	}

	@Override
	public Page<CommentDetails> findCommentDetailsPage(
			Page<CommentDetails> page, CommentDetails commentDetails,
			LinkedHashMap<String, String> orderby,String commentKey,String startTime, String endTime) {
		StringBuilder wheresql = new StringBuilder("1=1");
		List<Object> params = new ArrayList<Object>();
		if(commentKey != null && !"".equals(commentKey)){
			wheresql.append(" and o.content like '%" + commentKey + "%'");
		}
		if((startTime != null && !"".equals(startTime)) && (endTime != null && !"".equals(endTime))){
			wheresql.append(" and o.createTime between ? and ?");
			params.add(startTime + "00:00:00");
			params.add(endTime + "00:00:00");
		}
		if(commentDetails.getRecFlag() != null && commentDetails.getRecFlag() != 0 ){
			wheresql.append(" and o.recFlag = " + commentDetails.getRecFlag());
		}
		if(commentDetails.getIsRec()==null){
			wheresql.append(" and o.isRec = 0 ");
		}else{
			wheresql.append("  and o.isRec = " + commentDetails.getIsRec());
		}
		return commentDetailsDao.findCommentDetailsPage(page, wheresql.toString(), params, orderby);
	}

	@Override
	public Integer findCommentInfoIdById(Integer contentId) {
		return commentDetailsDao.findCommentInfoIdById(contentId);
	}

	@Override
	@Transactional
	public boolean addCommentRecService(CommentDetails commentDetail,
			Integer commentInfoId) {
		boolean flag = false;
		try{
			commentDetail.setRecFlag(1);	//设置状态为已回复
			commentDetailsDao.addCommentDetails(commentDetail,commentInfoId);
			commentDetailsDao.updateCommentStatus(commentDetail);
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

	@Override
	public List<CommentDetails> findCommentsByIsrec() {
		return commentDetailsDao.findCommentsByIsrec();
	}

	@Override
	public CommentDetails findRecByCommentId(Integer commentId) {
		return commentDetailsDao.findRecByCommentId(commentId);
	}

	/**
	 * 批量删除留言
	 */
	@Override
	@Transactional
	public boolean batchDelComment(String ids) {
		boolean flag = false;
		try{
			String[] idsTemp = null;
			if(ids != null && !"".equals(ids)){
				idsTemp = ids.split(",");
			}
			for (String id : idsTemp) {
				CommentDetails commentDetails = commentDetailsDao.findCommentDetailsById(Integer.parseInt(id));	//根据ID查询对应的留言信息
				deleteCommentDetails(Integer.parseInt(id),commentDetails.getRecFlag());
			}
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

}
