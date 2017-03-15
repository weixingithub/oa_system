<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String basepath = request.getContextPath();
	String fileID = request.getParameter("fileID");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>单个文件上传</title>
</head>
<style type="text/css" media="screen">
.my-uploadify-button {
	background:none;
	border: none;
	text-shadow: none;
	border-radius:0;
}

.uploadify:hover .my-uploadify-button {
	background:none;
	border: none;
}

.fileQueue {
	width: 400px;
	height: 150px;
	overflow: auto;
	border: 1px solid #E5E5E5;
	margin-bottom: 10px;
}
</style>
<body>
<h2 class="contentTitle">请选择需要上传的附件</h2>
<div class="pageContent">
	<div class="pageFormContent" layoutH="97">
		<dl>
			<dt>附件：</dt>
			<dd>
				<input id="file_upload" name="file_upload" type="file" multiple="true" >
			</dd>
		<div class="divider"></div>
		</dl>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</body>
<script type="text/javascript">
$(document).ready( function() {
        $("#file_upload").uploadify({
       	 swf:'<%=basepath %>/uploadify/scripts/uploadify.swf',
			uploader:'<%=basepath %>/upload/singleUploadFile',
			formData:{fileID:'<%=fileID%>'},
			buttonText:'请选择文件',
			fileSizeLimit:'36000KB',
			fileTypeDesc:'*.*;',
			fileTypeExts:'*.*;',
			auto:true,
			fileObjName:'file',
			multi:true,
			onUploadSuccess:function(file, data, response) {
			   	 //alert('The file ' + file.name + ' was successfully uploaded with a response of ' + response + ':' + data); 
			     if(response){
			     	var fileJson=eval('('+data+')');
			 		var fileOldName= file.name;
			 		var fileNewName= fileJson.fileNewName;
			 		var fileWebUrl= fileJson.fileWebUrl; 	
			 		var fileSize= fileJson.fileSize;	
			 		$.bringBack({"fileOldName":fileOldName,"fileNewName":fileNewName,"fileWebUrl":fileWebUrl,"fileSize":fileSize });
			 		alertMsg.correct("文件上传成功!");  
			     }else{
			     	alertMsg.error("文件上传失败!");  
			     }
			 }
        });
    });
</script>
</html>