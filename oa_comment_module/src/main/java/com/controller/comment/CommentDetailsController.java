package com.controller.comment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.oa_bean.Page;
import org.oa_bean.SysUser;
import org.oa_bean.comment.CommentDetails;
import org.oa_bean.comment.CommentInfo;
import org.oa_common.html.HTMLSpirit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dao.comment.CommentDetailsService;
import com.dao.comment.CommentInfoService;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;

@Controller
@RequestMapping("/oa/commentDetails")
public class CommentDetailsController {
	private CommentDetailsService commentDetailsService;
	
	private CommentInfoService commentInfoService;
	
	@Autowired
	public void setCommentInfoService(CommentInfoService commentInfoService) {
		this.commentInfoService = commentInfoService;
	}

	@Autowired
	public void setCommentDetailsService(CommentDetailsService commentDetailsService) {
		this.commentDetailsService = commentDetailsService;
	}
	
	/**
	 * 添加留言
	 * @param request
	 * @param commentInfoId
	 * @param commentDetail
	 * @return
	 */
	@RequestMapping(value="/addComment",method=RequestMethod.POST)
	@ResponseBody
	public Object addComment(HttpServletRequest request,Integer commentInfoId,CommentDetails commentDetail,Integer pId){
		  JsonBean jsonBean = null;
		  String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		  if(commentInfoId != null && commentInfoId != 0){
			  commentDetail.setCreateTime(currentTime);
			  commentDetail.setRecipient("admin");
			  commentDetail.setIsRec(0);
			  commentDetail.setRecFlag(2);	//新增的留言默认状态是未回复
		  }
		  String returnUrl = "oa/pwelfare/showPwelfare&id="+pId;
		  if(commentDetailsService.addCommentDetailsService(commentDetail, commentInfoId)){
			  jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get(returnUrl),"","closeCurrent","","");
		  }else{
			  jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		  }
		return jsonBean;
	}
	
	/**
	 * 弹出留言的窗口
	 * @param commentInfoId
	 * @return
	 */
	@RequestMapping(value="/toAddComment")
	public ModelAndView toAddComment(Integer commentInfoId,Integer pId){
		ModelAndView mov = new ModelAndView("conservice/conmmentEditor");
		mov.addObject("commentInfoId",commentInfoId);
		mov.addObject("pId",pId);
		return mov;
	}
	
	/**
	 * 获取留言列表（分页）
	 * @param request
	 * @param numPerPage
	 * @param pageNum
	 * @param page
	 * @param commentDetails
	 * @param startTime
	 * @param endTime
	 * @param orderField
	 * @param orderDirection
	 * @return
	 */
	@RequestMapping(value="/findCommentPage")
	public ModelAndView findCommentPage(HttpServletRequest request,Integer numPerPage ,Integer pageNum,
			   Page<CommentDetails> page,CommentDetails commentDetails,String commentKey,String startTime,String endTime,String orderField,String orderDirection){
		ModelAndView mov = new ModelAndView("comment/comment_list");
		LinkedHashMap<String,String> orderby =null;
		if(numPerPage == null ||numPerPage == -1){
			page = new Page(10);
		}else{
			page.setPageNo(pageNum);
			page.setPageSize(numPerPage);
		}
		if(orderField != null && !"".equals(orderField) && orderDirection != null && !"".equals(orderDirection)){
			orderby = new LinkedHashMap<String, String>();
			orderby.put(orderField, orderDirection);
		}else{
			orderby = new LinkedHashMap<String, String>();
			orderField = "createTime";
			orderDirection = "desc";
			orderby.put(orderField, orderDirection);
		}

		page = commentDetailsService.findCommentDetailsPage(page, commentDetails, orderby,commentKey,startTime,endTime);
		
		//将评论和对应的回复做匹配
		List<CommentDetails> comments = new ArrayList<CommentDetails>();
		comments = commentDetailsService.findCommentsByIsrec();
		for (CommentDetails comment : page.getResult()) {
			String content = HTMLSpirit.delHTMLTag(comment.getContent());
			for (CommentDetails commentDetails2 : comments) {
				if (commentDetails2.getIsRec() == comment.getContentId()) {
					comment.setCommentDetails(commentDetails2);
				}
			}
			comment.setContent(content);
		}
		mov.addObject("page",page);
		mov.addObject("orderField",orderField);
		mov.addObject("orderDirection",orderDirection);
		mov.addObject("commentDetails",commentDetails);
		mov.addObject("startTime",startTime);
		mov.addObject("endTime",endTime);
		return mov;
	}
	
	/**
	 * 弹出回复的页面
	 * @param commentId
	 * @return
	 */
	@RequestMapping(value="/toRecEditor")
	public ModelAndView toRecEditor(Integer commentId,String type){
		ModelAndView mov = null;
		CommentDetails commentDetail = commentDetailsService.findCommentDetailsById(commentId);
		Integer commentInfoId = commentDetailsService.findCommentInfoIdById(commentDetail.getContentId());
		CommentInfo commentInfo = commentInfoService.findCommentInfoById(commentInfoId);
		Integer flag = 0;	//0未回复，显示回复页面；1已回复，显示预览页面
		if("show".equals(type)){
			//已回复，查看回复
			mov = new ModelAndView("comment/conmmentEditor");
			//根据留言查看回复
			CommentDetails recComment = commentDetailsService.findRecByCommentId(commentId);
			commentDetail.setCommentDetails(recComment);
			flag = 1;
		}else if("rec".equals(type)){
			//回复留言
			mov = new ModelAndView("comment/conmmentEditor");
			flag = 0;
		}
		mov.addObject("flag",flag);
		mov.addObject("commentDetail",commentDetail);
		mov.addObject("commentInfo",commentInfo);
		return mov;
	}
	
	/**
	 * 保存回复信息
	 * @return
	 */
	@RequestMapping(value="/saveRec")
	@ResponseBody
	public Object saveRec(CommentDetails commentDetail,Integer commentId,Integer commentInfoId,Integer recId){
		  JsonBean jsonBean = null;
		  String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		  if(commentDetail != null && commentId != null && commentInfoId != null){
			  commentDetail.setCreateTime(currentTime);
			  commentDetail.setIsRec(commentId);
			  commentDetail.setRecFlag(0);
		  }
		  if(recId != null){
			  //修改回复
			  commentDetail.setContentId(recId);
			  if(commentDetailsService.updateCommentDetailsService(commentDetail, commentInfoId)){
				  jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/commentDetails/findCommentPage"),"","closeCurrent","","");
			  }else{
				  jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
			  }
			  
		  }else{
			  //新增回复
			  if(commentDetailsService.addCommentRecService(commentDetail, commentInfoId)){
				  jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/commentDetails/findCommentPage"),"","closeCurrent","","");
			  }else{
				  jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
			  }
		  }
		return jsonBean;
	}
	
	/**
	 * 删除留言
	 * @param commentId
	 * @return
	 */
	@RequestMapping(value="/delComment")
	@ResponseBody
	public Object delComment(Integer commentId,Integer recFlag,Integer isRec){
		JsonBean jsonBean = null;
		  if(commentDetailsService.deleteCommentDetails(commentId,recFlag)){
			  jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/commentDetails/findCommentPage"),"","","","");
		  }else{
			  jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		  }
		  return jsonBean;
	}
	
	/**
	 * 批量删除留言
	 * @param ids
	 * @return
	 */
	@RequestMapping(value="/batchDelComment")
	@ResponseBody
	public Object batchDelComment(String ids){
		JsonBean jsonBean = null;
		  if(commentDetailsService.batchDelComment(ids)){
			  jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,OACommon.modelMap.get("/oa/commentDetails/findCommentPage"),"","","","");
		  }else{
			  jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
		  }
		  return jsonBean;
	}
}
