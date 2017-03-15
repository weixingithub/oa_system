package com.controller.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dao.comment.CommentDetailsService;
import com.dao.comment.CommentInfoService;
import com.tool.util.JsonBean;
import com.tool.util.OACommon;

@Controller
@RequestMapping("oa/commentInfo")
public class CommentInfoController {
	private CommentInfoService commentInfoService;
	
	private CommentDetailsService commentDetailsService;
	@Autowired
	public void setCommentDetailsService(CommentDetailsService commentDetailsService) {
		this.commentDetailsService = commentDetailsService;
	}
	@Autowired
	public void setCommentInfoService(CommentInfoService commentInfoService) {
		this.commentInfoService = commentInfoService;
	}
	
	/**
	 * 获取并更新点赞和点踩的数量
	 * @param commentInfoId
	 */
	@RequestMapping(value="/getPraiseAndNegativeNumber")
	@ResponseBody
	public Object getPraiseNumber(Integer commentInfoId,String type){
		JsonBean jsonBean = null;
		if(commentInfoId != null ){
			 if(commentInfoService.updateCommentInfoByIdAndCondition(commentInfoId,type)){
				  jsonBean = new JsonBean("200",OACommon.OPERATION_SUCCESS,"","","","","");
			  }else{
				  jsonBean = new JsonBean("300",OACommon.OPERATION_FAIL,"","","","","");
			  }
		}
		 
		return jsonBean;
	}
}
