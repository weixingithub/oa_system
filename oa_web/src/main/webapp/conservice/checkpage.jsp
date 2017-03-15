<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String basePath = request.getContextPath();  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addorg</title>
</head>
<script type="text/javascript">
$(document).ready(function(){  
    //初始化xhEditor编辑器插件  
    $('#checkxheditor').xheditor({  
    	width:550,
    	height:400,
        tools:'full',  
        skin:'default',  
        upMultiple:98,
        html5Upload:false,
        upImgUrl: "<%=basePath%>/eidtorUpload/eidtorUploadFile?flag=img",  
        upImgExt: "jpg,jpeg,gif,bmp,png",  
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
	        $("#checkxheditor").append(_msg);  
    	}
    }  
    //处理服务器返回到回调函数的字符串内容,格式是JSON的数据格式.  
    function Substring(s){  
        return s.substring(s.substring(0,s.lastIndexOf("/")).lastIndexOf("/"),s.length);  
    }  
});  


function myReApply(obj){
	document.getElementById("rep").value=obj;
	
	
}
function applyDesicion(){
	var radios = document.getElementsByName("flag");
    if(radios[0].checked)  
    { 
    	 document.getElementById("applyreason").style.display="none";
    }else{
    	document.getElementById("applyreason").style.display="";
    }
}
</script>
<body>
<div class="pageContent">
	<form method="post" action="<%=basePath %>/oa/pwelfare/handleWorkFlow" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
	    <input  type="hidden"  name="id"   value="${pw.id }"/>
	    <input  type="hidden"  name="processInstanceId"   value="${pw.processInstanceId }"/>
	    <input  type="hidden"  name=activityuserId   value="${pw.activityuserId }"/>
	    <input  type="hidden"  name="taskId"   value="${taskId }"/>
		<input type="hidden" name="modelId" value="${pw.modelId }"/>
		<input type="hidden" name="starttime" value="${pw.starttime }"/>
		<input type="hidden" name="flowstatus" value="${pw.flowstatus }"/>
		<input type="hidden" name="signUserName" value="${pw.signUserName }"/>
		<input type="hidden" name="endtime" value="${pw.endtime }"/>
		<input type="hidden" name="userId" value="${pw.userId }"/>
		<input type="hidden" name="finishtime" value="${pw.finishtime }"/>
		<input type="hidden" name="orgId" value="${pw.orgId }"/>
		<input type="hidden" name="commentSwitch" value="${pw.commentSwitch }"/>
		<input type="hidden" name="commentInfoId" value="${pw.commentInfo.commentInfoId }"/>
		<input type="hidden" name="creattime" value="${pw.creattime }"/>
		<input type="hidden" name="imgSrc" value="${pw.imgSrc }"/>
		<input type="hidden" name="pubPlatform" id="pubPlatform" value="${pw.pubPlatform }"/>
	    <input  id="rep"  type="hidden"  name="reApply"/>
		<div class="pageFormContent" layoutH="120">
			    <div class="unit">
					<label>文章标题：</label>
				    <input name="title"  type="text" size="50" class="required"  value="${pw.title }"/>
				</div>
				<div class="unit">
				   <label>文章作者：</label>
				   <input type="text" name="author" size="20" class="required"  value="${pw.author }"/>
				</div>
				<div class="unit">
				   <label>原文链接：</label>
				   <input type="text" name="originalLinks" size="50"  value="${pw.originalLinks }"/>
				</div>
				<dl class="nowrap">
				 <dt>发布渠道：</dt>
				 <dd>
				    <label><input type="checkbox" id="publish" value="3"  <c:if test="${pw.pubPlatform%3==0 }"> checked</c:if>/>网站</label>
					<label><input type="checkbox" id="publish" value="5"  <c:if test="${pw.pubPlatform%5==0 }"> checked</c:if>/>微信</label>
					<label><input type="checkbox" id="publish" value="7"  <c:if test="${pw.pubPlatform%7==0 }"> checked</c:if>/>微博</label>
				  </dd>
			  </dl>
			  <div class="unit">
			  <dl class="nowrap">
				 <dt>内容：</dt>
				 <dd>
					 <textarea class="editor" name="content" id="checkxheditor"  rows="15" cols="100">
						${pw.personWelfareContent.content }
					</textarea>
				 </dd>
			  </dl>
			</div>
		      <c:if test="${sign == true}">
		      <div class="unit">
				   <label><input type="radio" name="flag" onchange="applyDesicion();" checked="checked"  value="true" />发布</label>
			       <label><input type="radio" name="flag" onchange="applyDesicion();"   value="false"/>驳回</label>
		      </div>
		      <div  class="unit">
		         <div id="applyreason"  style="display: none">
		           <label>驳回原因：</label>
		           <textarea   name="rebackReason"  rows="2" cols="80"></textarea>
		         </div>
		      </div>
		      <div class="formBar">
				  <ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit" onclick="submitCheckbox()">提交</button></div></div></li>
				  </ul>
		      </div>
		      
		      </c:if>
		<c:if test="${sign == false }">
		<div  class="unit">
		         <div id="applyreason">
		           <label>驳回原因：</label>
		           <textarea   name="rebackReason"  readonly="true"    rows="2" cols="80">${rebackReason }</textarea>
		         </div>
		      </div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button  type="submit"  onclick="myReApply('true');">重新申请</button></div></div></li>
				 <li><div class="buttonActive"><div class="buttonContent"><button  type="submit"  onclick="myReApply('false');">结束</button></div></div></li>
			</ul>
		</div>
		</c:if>
		</div>
	</form>
	
</div>
</body>
<script type="text/javascript">
function submitCheckbox(){
	var pubId = ${pw.id };
	 $.ajax({
			url:'<%=basePath %>/oa/sendMessage/sendMessage',
			dataType:"json",
			type:"post",
			data:{pubId:pubId},
			async : false,
			success : function(result) {
			},
			error : function(msg) {
			}
		});
}
</script>
</html>