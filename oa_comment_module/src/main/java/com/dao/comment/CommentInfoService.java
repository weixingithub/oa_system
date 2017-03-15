package com.dao.comment;

import java.util.LinkedHashMap;

import org.oa_bean.Page;
import org.oa_bean.comment.CommentInfo;

public interface CommentInfoService {
	/**
	 * 新增评论信息
	 * @param commentInfo
	 */
	public boolean addCommentInfoService(CommentInfo commentInfo);
	/**
	 * 新增好评数量
	 * @param commentInfo
	 */
	public boolean addPraiseNumber(Integer commentInfoId);
	/**
	 * 新增负面数量
	 * @param commentInfo
	 */
	public boolean addNegativeNumber(Integer commentInfoId);
	/**
	 * 新增浏览数量
	 * @param commentInfo
	 */
	public boolean addBrowseNumber(Integer commentInfoId);
	/**
	 * 编辑评论信息
	 * @param commentInfo
	 */
	public boolean updateCommentInfoService(CommentInfo commentInfo);
	/**
	 * 删除评论信息
	 * @param commentInfoId
	 */
	public boolean deleteCommentInfoService(Integer commentInfoId);
	/**
	 * 批量删除评论信息
	 * @param commentInfoIds
	 */
	public boolean	 deleteCommentInfoService(String commentInfoIds);
	/**
	 * 查询评论信息
	 * @param commentInfoId
	 */
	public CommentInfo findCommentInfoById(Integer commentInfoId);
	/**
	 * 分页查询评论信息
	 * @param commentInfoId
	 */
	public Page<CommentInfo> findCommentInfoPage(Page<CommentInfo> page,CommentInfo commentInfo,LinkedHashMap<String, String> orderby);
	
	/**
	 * 根据ID和部分字段更新commentInfo(带操作类型)
	 * @param commentInfoId
	 * @param type
	 * @return
	 */
	public boolean updateCommentInfoByIdAndCondition(Integer commentInfoId,String type);
	
	/**
	 * 根据ID和部分字段更新commentInfo
	 * @param commentInfoId
	 * @param params
	 * @return
	 */
	public boolean updateCommentInfoByIdAndUrl(Integer commentInfoId,String url);
	

}
