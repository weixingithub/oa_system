package com.dao.comment;

import java.util.LinkedHashMap;
import java.util.List;

import org.oa_bean.Page;
import org.oa_bean.comment.CommentInfo;

/**
 * 评论对象信息
 * @author Administrator
 *
 */
public interface CommentInfoDao {
	/**
	 * 新增评论信息
	 * @param commentInfo
	 */
	public void addCommentInfo(CommentInfo commentInfo);
	/**
	 * 新增好评数量
	 * @param commentInfo
	 */
	public void addPraiseNumber(Integer commentInfoId);
	/**
	 * 新增负面数量
	 * @param commentInfo
	 */
	public void addNegativeNumber(Integer commentInfoId);
	/**
	 * 新增浏览数量
	 * @param commentInfo
	 */
	public void addBrowseNumber(Integer commentInfoId);
	/**
	 * 编辑评论信息
	 * @param commentInfo
	 */
	public void updateCommentInfo(CommentInfo commentInfo);
	
	/**
	 * 删除评论信息
	 * @param commentInfoId
	 */
	public void deleteCommentInfo(Integer commentInfoId);
	
	/**
	 * 批量删除评论信息
	 * @param commentInfoIds
	 */
	public void deleteCommentInfo(String commentInfoIds);
	
	/**
	 * 查询评论信息
	 * @param commentInfoId
	 */
	public CommentInfo findCommentInfoById(Integer commentInfoId);
	
	/**
	 * 分页查询评论信息
	 * @param commentInfoId
	 */
	public Page<CommentInfo> findCommentInfoPage(Page<CommentInfo> page,String wheresql, List<Object> queryParams,LinkedHashMap<String, String> orderby);
	
	/**
	 * 根据ID和部分字段更新commentInfo
	 * @param commentInfoId
	 * @param string
	 */
	public void updateCommentInfoByIdAndCondition(Integer commentInfoId,
			String params);
}
