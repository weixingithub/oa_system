package com.impl.comment;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.comment.CommentDetails;
import org.springframework.stereotype.Repository;

import com.dao.comment.CommentDetailsDao;
import com.impl.BaseDaoImpl;
@Repository(value="commentDetailsDao")
public class CommentDetailsDaoImpl extends BaseDaoImpl implements CommentDetailsDao{

	@Override
	public void addCommentDetails(CommentDetails commentDetails,Integer commentInfoId) {
		String sql = "insert into b_comment_details (content,create_time,recipient,sender,comment_info_id,isrec,rec_flag) values('"+commentDetails.getContent()+"','"
				+commentDetails.getCreateTime()+"','"+commentDetails.getRecipient()+"','"+commentDetails.getSender()+"',"+commentInfoId+","+commentDetails.getIsRec()+
				"," + commentDetails.getRecFlag() + ")";
		createSqlMethod(sql).executeUpdate();
	}

	@Override
	public void updateCommentDetails(CommentDetails commentDetails,Integer commentInfoId) {
		String sql = "update b_comment_details set content='"+commentDetails.getContent()+"',create_time='"+commentDetails.getCreateTime()+"',recipient='"+commentDetails.getRecipient()+
				"',sender='"+commentDetails.getSender()+"',comment_info_id="+commentInfoId+",isrec="+commentDetails.getIsRec()+"  where content_id = "+commentDetails.getContentId();
		createSqlMethod(sql).executeUpdate();
	}

	@Override
	public void deleteCommentDetails(Integer contentId) {
		delete(CommentDetails.class, contentId);
	}

	@Override
	public void deleteCommentDetailsByCommentInfo(Integer commentInfoId) {
		String sql = "delete from b_comment_details where comment_info_id ="+commentInfoId;
		createSqlMethod(sql).executeUpdate();
	}

	@Override
	public CommentDetails findCommentDetailsById(Integer contentId) {
		return find(CommentDetails.class, contentId);
	}

	@Override
	public List<CommentDetails> findCommentDetailsByCommentInfo(
			Integer commentInfoId) {
		String hql = "select c from CommentDetails c where c.commentInfoId ="+commentInfoId;
		return findObjectList(hql);
	}

	@Override
	public Page<CommentDetails> findCommentDetailsPage(
			Page<CommentDetails> page, String wheresql,
			List<Object> queryParams, LinkedHashMap<String, String> orderby) {
		return getScrollData(page, CommentDetails.class, wheresql, queryParams, orderby);
	}

	@Override
	public Integer findCommentInfoIdById(Integer contentId) {
		String sql = "select comment_info_id from b_comment_details where content_id = " + contentId;
		Integer commentInfoId = (Integer)createSqlMethod(sql).getSingleResult();
		return commentInfoId;
	}

	@Override
	public void updateCommentStatus(CommentDetails commentDetail){
		String sql = "update b_comment_details set rec_flag = " + commentDetail.getRecFlag() + "  where content_id = "+commentDetail.getIsRec();
		createSqlMethod(sql).executeUpdate();
	}

	@Override
	public List<CommentDetails> findCommentsByIsrec() {
		String hql = "select c from CommentDetails c where c.isRec <> 0";
		return findObjectList(hql );
	}

	@Override
	public CommentDetails findRecByCommentId(Integer commentId) {
		String hql = "select c from CommentDetails c where c.isRec = " + commentId;
		return (CommentDetails) findObject(hql);
	}

	@Override
	public void deleteRecContenId(Integer contentId) {
		String sql = "delete from b_comment_details where isrec ="+contentId;
		createSqlMethod(sql).executeUpdate();
	}

}
