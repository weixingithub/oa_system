package com.impl.comment;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.comment.CommentInfo;
import org.springframework.stereotype.Repository;

import com.dao.comment.CommentInfoDao;
import com.impl.BaseDaoImpl;
@Repository(value="commentInfoDao")
public class CommentInfoDaoImpl extends BaseDaoImpl implements CommentInfoDao{

	@Override
	public void addCommentInfo(CommentInfo commentInfo) {
		save(commentInfo);
	}
	@Override
	public void updateCommentInfo(CommentInfo commentInfo) {
		update(commentInfo);
	}
	@Override
	public void deleteCommentInfo(Integer commentInfoId) {
		delete(CommentInfo.class, commentInfoId);
	}
	@Override
	public void deleteCommentInfo(String commentInfoIds) {
		delete(CommentInfo.class, commentInfoIds);
	}
	@Override
	public CommentInfo findCommentInfoById(Integer commentInfoId) {
		return find(CommentInfo.class, commentInfoId);
	}
	@Override
	public Page<CommentInfo> findCommentInfoPage(Page<CommentInfo> page,
			String wheresql, List<Object> queryParams,
			LinkedHashMap<String, String> orderby) {
		return getScrollData(page, CommentInfo.class, wheresql, queryParams, orderby);
	}
	@Override
	public void addPraiseNumber(Integer commentInfoId) {
		String sql = "UPDATE b_comment_info SET praise_number = praise_number+1 WHERE comment_info_id ="+commentInfoId;
		createSqlMethod(sql).executeUpdate();
	}
	@Override
	public void addNegativeNumber(Integer commentInfoId) {
		String sql = "UPDATE b_comment_info SET negative_number = negative_number+1 WHERE comment_info_id ="+commentInfoId;
		createSqlMethod(sql).executeUpdate();
	}
	@Override
	public void addBrowseNumber(Integer commentInfoId) {
		String sql = "UPDATE b_comment_info SET browse_number = browse_number+1 WHERE comment_info_id ="+commentInfoId;
		createSqlMethod(sql).executeUpdate();
	}
	@Override
	public void updateCommentInfoByIdAndCondition(Integer commentInfoId,String params) {
		String sql = "update b_comment_info set " + params + " where comment_info_id = " + commentInfoId;
		createSqlMethod(sql).executeUpdate();
	}

}
