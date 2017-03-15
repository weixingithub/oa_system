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

<div class="pageContent" whidth="510">
			<!-- 留言框 -->
				<div class="unit" id="subComment" style={ddisplay:none}>
					<form method="post" action="<%=basePath %>/oa/commentDetails/addComment" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return iframeCallback(this, dialogAjaxDone);">
						<input type="hidden" name="commentInfoId" value="${commentInfoId}"/>
						<input type="hidden" name="recipient" value="${recipient}"/>
						<input type="hidden" name="pId" value="${pId}"/>
						  
							<label>请在这里输入留言信息：</label><br/>
							<textarea class="editor" name="content" id="xheditor"  rows="4" cols="16"></textarea>
						<br/>请输入您的姓名:<input type="text" name="sender" size="18"/>
						  
					</form>
				</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent" onclick="submit()"><button type="submit">提交留言</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
			</ul>
		</div>
</body>
<script type="text/javascript">
$(document).ready(function(){  
    //初始化xhEditor编辑器插件  
    $('#xheditor').xheditor({  
    	width:500,
    	height:230,
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