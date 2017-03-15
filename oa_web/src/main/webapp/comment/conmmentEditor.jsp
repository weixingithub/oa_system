<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
   String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

</head >
<body>

<div class="pageContent" style="width:auto">
			<!-- 回复留言框 -->
			<fieldset>
				<legend>留言</legend>
				<div class="unit">
					<p><label>留言人：</label><strong>${commentDetail.sender}</strong></p>
					<p><label>留言日期：</label>${commentDetail.createTime}</p>
					<p><label>所属文章：</label><a href="${commentInfo.url}" target="navTab">${commentInfo.title}</a></p>
					<p><label>留言内容：</label>${commentDetail.content}</p>
				</div>
			</fieldset><br/><br/>
			<fieldset>
				<legend>回复</legend>
					<div class="unit" id="subComment" >
						<form method="post" action="<%=basePath %>/oa/commentDetails/saveRec" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return validateCallback(this, dialogAjaxDone)">
							<input type="hidden" name="commentId" value="${commentDetail.contentId}"/>
							<input type="hidden" name="commentInfoId" value="${commentInfo.commentInfoId}"/>
							<input type="hidden" name="recipient" value="${commentDetail.sender}"/>
							<input type="hidden" name="recId" value="${commentDetail.commentDetails.contentId}"/>
							<div class="required">
								<textarea class="editor"  name="content" id="xheditor"  rows="4" cols="10">${commentDetail.commentDetails.content}</textarea>
							</div>
							<br/>回复人:<input type="text" name="sender" size="18" value="${commentDetail.commentDetails.sender}"/>
							  
						</form>
					</div>
		  </fieldset>
		<div class="formBar">
			<ul>
				<c:if test="${flag == 0}">
				<li><div class="buttonActive"><div class="buttonContent" onclick="submit()"><button type="submit" >提交回复</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
				</c:if>
				<c:if test="${flag == 1}">
				<li><div class="buttonActive"><div class="buttonContent" onclick="submit()"><button type="submit">修改回复</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button class="close" type="button">取消返回</button></div></div></li>
				</c:if>
			</ul>
		</div>
</body>
<script type="text/javascript">
$(document).ready(function(){  
    //初始化xhEditor编辑器插件  
    $('#xheditor').xheditor({  
    	width:526,
    	height:170,
        tools:'Bold,Italic,Underline,FontColor,BackColor,Align,Emot',
        skin:'nostyle',  
        upMultiple:98,
        html5Upload:false,
        formatXHTML:true
    });    
});
function submit(){
	$("#subComment form").submit();
}
</script>  
</html>