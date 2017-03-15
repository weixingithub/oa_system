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
<script type="text/javascript">
$(document).ready(function(){  
    //初始化xhEditor编辑器插件  
    $('#xheditor').xheditor({  
    	width:900,
    	height:600,
        tools:'full',  
        skin:'nostyle',  
        upMultiple:98,
        html5Upload:false,
        formatXHTML:true,
        upImgUrl:"<%=basePath%>/upload/eidtorUploadFile?flag=img",  
        upMediaUrl:"<%=basePath%>/upload/eidtorUploadFile?flag=video",  
        upFlashUrl:"<%=basePath%>/upload/eidtorUploadFile?flag=flash",
        upImgExt: "jpg,jpeg,gif,bmp,png",  
        upMediaExt:"mp4",
        upFlashExt:"swf",
        onUpload:insertUpload  
    });  
    //xbhEditor编辑器图片上传回调函数  
    function insertUpload(msg) {  
    	if(msg=="0"){
    		alert("文件过大，最大支持10M!");
    	}else if(msg=="1"){
    		alert("上传失败请重新上传!");
    	}else{
    		var _msg = msg.toString(); 
	        var _picture_name = _msg.substring(_msg.lastIndexOf("/")+1);  
	        var _picture_path = Substring(_msg);  
	        $("#xheditor").append(_msg);  
    	}
    }  
    //处理服务器返回到回调函数的字符串内容,格式是JSON的数据格式.  
    function Substring(s){  
        return s.substring(s.substring(0,s.lastIndexOf("/")).lastIndexOf("/"),s.length);  
    }  
    
});

/**
 * 获取输入框中的标签名称
 */
function getTagName(){
	var tagName = $("#tagName").val();
	return tagName;
}
</script>  
<body>
<div class="pageContent">
	<form method="post" action="<%=basePath %>/oa/pwelfare/addAndUpdatePwelfare" class="pageForm required-validate" enctype="multipart/form-data" onsubmit="return validateCallback(this, navTabAjaxDone)">
		<input type="hidden" name="id" value="${personWelfare.id }"/>
		<input type="hidden" name="modelId" value="${personWelfare.modelId }"/>
		<input type="hidden" name="starttime" value="${personWelfare.starttime }"/>
		<input type="hidden" name="autoCheck" value="${autoCheck }"/>
		<input type="hidden" name="endtime" value="${personWelfare.endtime }"/>
		<input type="hidden" name="activityuserId" value="${personWelfare.activityuserId }"/>
		<input type="hidden" name="processInstanceId" value="${personWelfare.processInstanceId }"/>
		<input type="hidden" name="flag" value="${personWelfare.flag }"/>
		<input type="hidden" name="creattime" value="${personWelfare.creattime }"/>
		<input type="hidden" name="pubPlatform" id="pubPlatform" value="${personWelfare.pubPlatform }"/>
		<input type="hidden" name="flowstatus" value="${personWelfare.flowstatus }"/>
		<input type="hidden" name="commentInfoId" value="${personWelfare.commentInfo.commentInfoId}"/>
		
		<div class="pageFormContent" layoutH="80">
			<div class="unit">
			  <dl class="nowrap">
				<label>标题：</label>
				 <dd>
				   <input type="text" name="title" size="50" class="required"  value="${personWelfare.title }"/>
				  </dd>
			  </dl>
			</div>
			<div class="unit">
			  <dl class="nowrap">
				<label>文章作者：</label>
				 <dd>
				   <input type="text" name="author" size="20" class="required"  value="${personWelfare.author }"/>
				  </dd>
			  </dl>
			</div>
			<div class="unit">
			  <dl class="nowrap">
				<label>原文链接：</label>
				 <dd>
				   <input type="text" name="originalLinks" size="50"   value="${personWelfare.originalLinks }"/>
				  </dd>
			  </dl>
			</div>
			<div class="unit">
			  <dl class="nowrap">
				 <dt>创建人：</dt>
				   <input type="hidden" name="userId" value="${personWelfare.sysUser.id }"/>
				 <dd>${personWelfare.sysUser.realName }</dd>
			  </dl>
			</div>
			<div class="unit">
			  <dl class="nowrap">
				 <dt>分类标签：</dt>
				 <dd>
					<input  name="articleTag" id="tagName" type="text" size="50" value="${personWelfare.articleTag}" readonly="readonly"/>
					<a  class="btnLook" href="<%=basePath %>/tag/tags.jsp?articleTag=${personWelfare.articleTag}" lookupGroup=""   width="480" height="400">添加标签</a>
				  </dd>
			  </dl>
			</div>
			<div class="unit">
			  <dl class="nowrap">
				 <dt>发布渠道：</dt>
				 <dd>
				    <label><input type="checkbox" id="publish" value="3"  <c:if test="${personWelfare.pubPlatform%3==0 && personWelfare.pubPlatform!='' }"> checked</c:if>/>网站</label>
					<label><input type="checkbox" id="publish" value="5"  <c:if test="${personWelfare.pubPlatform%5==0 && personWelfare.pubPlatform!='' }"> checked</c:if>/>微信</label>
					<label><input type="checkbox" id="publish" value="7"  <c:if test="${personWelfare.pubPlatform%7==0 && personWelfare.pubPlatform!='' }"> checked</c:if>/>微博</label>
				  </dd>
			  </dl>
			</div>
			<div class="unit">
			  <dl class="nowrap">
				 <dt>是否开启评论：</dt>
				 <dd>
				    <select class="combox" name="commentSwitch">
				    		<option value="0" <c:if test="${personWelfare.commentSwitch == 0}">selected</c:if> >否</option>
				    		<option value="1" <c:if test="${personWelfare.commentSwitch == 1}">selected</c:if> >是</option>
				   </select>
				  </dd>
			  </dl>
			</div>
			<div class="unit">
			  <dl class="nowrap">
				 <dt>所属机构：</dt>
				 <dd>
				    <select class="combox" name="orgId">
				   		<c:forEach items="${orglist }" var="oaOrg" varStatus="st">
				    		<option value="${oaOrg.orgId }" <c:if test="${oaOrg.orgId == personWelfare.orgId }">selected</c:if> >${oaOrg.orgName }</option>
				   		</c:forEach>
				   </select>
				  </dd>
			  </dl>
			</div>
			<div class="unit">
			  <dl class="nowrap">
				 <dt>内容预览：</dt>
				 <dd>
				 	<a href="javascript::;" onclick="showXheditor()">微信预览</a>
				  </dd>
			  </dl>
			</div>
			<div class="unit">
			  <dl class="nowrap">
				 <dt>内容：</dt>
				 <dd>
					 <textarea class="editor" name="content" id="xheditor"  rows="15" cols="100">
						${personWelfare.personWelfareContent.content }
					</textarea>
				 </dd>
			  </dl>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" onclick="submitCheckbox()">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
</body>
<script type="text/javascript">
function showXheditor(){
	window.open('<%=basePath %>/account/wechat/wechat_preview.jsp','_blank','width=400,height=800,menubar=no,toolbar=no,location=no,directories=no,status=no,scrollbars=yes,resizable=yes'); 
}
function xheditorContent(){
	return $("#xheditor").val();
}
function submitCheckbox(){
	var chk_value =1;
	$('input[id="publish"]:checked').each(function(){
		chk_value = parseInt(chk_value)*parseInt($(this).val());
	});
	document.getElementById("pubPlatform").value = chk_value;
}
</script>
</html>