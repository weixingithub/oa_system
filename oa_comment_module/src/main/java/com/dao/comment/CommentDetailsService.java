package com.dao.comment;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.comment.CommentDetails;

public interface CommentDetailsService {
	/**
	 * 添加评论内容
	 * @param commentDetails
	 */
	public boolean addCommentDetailsService(CommentDetails commentDetails,Integer commentInfoId);
	/**
	 * 修改评论内容
	 * @param commentDetails
	 */
	public boolean updateCommentDetailsService(CommentDetails commentDetails,Integer commentInfoId);
	/**
	 * 删除评论内容
	 * @param contentId
	 */
	public boolean deleteCommentDetails(Integer contentId,Integer recFlag);
	/**
	 * 根据评论对象ID删除评论内容
	 * @param commentInfoId
	 */
	public boolean deleteCommentDetailsByCommentInfo(Integer commentInfoId);
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
	public Page<CommentDetails> findCommentDetailsPage(Page<CommentDetails> page,CommentDetails commentDetails,LinkedHashMap<String, String> orderby,String commentKey,String startTime, String endTime);
	
	/**
	 * 根据留言ID查询其对应的commentInfo的ID
	 * @param contentId
	 * @return
	 */
	public Integer findCommentInfoIdById(Integer contentId);
	
	/**
	 * 保存回复信息
	 * @param commentDetail
	 * @param commentInfoId
	 * @return
	 */
	public boolean addCommentRecService(CommentDetails commentDetail,
			Integer commentInfoId);
	
	/**
	 * 查询所有回复
	 * @return
	 */
	public List<CommentDetails> findCommentsByIsrec();
	
	/**
	 * 根据留言ID查询本留言的回复
	 * @param commentId
	 * @return 
	 */
	public CommentDetails findRecByCommentId(Integer commentId);
	
	/**
	 * 批量删除留言
	 * @param ids
	 * @return
	 */
	public boolean batchDelComment(String ids);

}
