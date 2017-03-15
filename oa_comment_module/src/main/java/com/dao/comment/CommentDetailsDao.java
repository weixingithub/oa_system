package com.dao.comment;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.comment.CommentDetails;

public interface CommentDetailsDao {
	/**
	 * 添加评论内容
	 * @param commentDetails
	 */
	public void addCommentDetails(CommentDetails commentDetails,Integer commentInfoId);
	/**
	 * 修改评论内容
	 * @param commentDetails
	 */
	public void updateCommentDetails(CommentDetails commentDetails,Integer commentInfoId);
	/**
	 * 删除评论内容
	 * @param contentId
	 */
	public void deleteCommentDetails(Integer contentId);
	/**
	 * 根据评论对象ID删除评论内容
	 * @param commentInfoId
	 */
	public void deleteCommentDetailsByCommentInfo(Integer commentInfoId);
	/**
	 * 查询评论内容
	 * @param contentId
	 * @return
	 */
	public CommentDetails findCommentDetailsById(Integer contentId);
	/**
	 * 根据评论对象ID查询所有的评论信息
	 * @param commentInfoId
	 * @return
	 */
	public List<CommentDetails> findCommentDetailsByCommentInfo(Integer commentInfoId);
	/**
	 * 分页评论内容
	 * @param page
	 * @param wheresql
	 * @param queryParams
	 * @param orderby
	 * @return
	 */
	public Page<CommentDetails> findCommentDetailsPage(Page<CommentDetails> page,String wheresql, List<Object> queryParams,LinkedHashMap<String, String> orderby);
	
	/**
	 * 根据留言ID查询其留言对象ID
	 * @param contentId
	 * @return
	 */
	public Integer findCommentInfoIdById(Integer contentId);
	
	/**
	 * 回复信息后更新对应的留言信息
	 * @param commentDetail
	 * @param commentInfoId
	 */
	public void updateCommentStatus(CommentDetails commentDetail);
	
	/**
	 * 查询所有回复
	 * @return
	 */
	public List<CommentDetails> findCommentsByIsrec();
	
	/**
	 * 根据留言ID查询其回复信息
	 * @param commentId
	 * @return
	 */
	public CommentDetails findRecByCommentId(Integer commentId);
	/**
	 * 删除评论下的回复
	 * @param contentId
	 */
	public void deleteRecContenId(Integer contentId);
	
}
