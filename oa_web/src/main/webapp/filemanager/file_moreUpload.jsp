<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String basepath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>多个文件上传</title>
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
		<input type="image" src="<%=basepath %>/uploadify/img/upload.jpg" onclick="$('#file_upload').uploadify('upload', '*');"/>
		<input type="image" src="<%=basepath %>/uploadify/img/cancel.jpg" onclick="$('#file_upload').uploadify('cancel', '*');"/>
		<div id="fileQueue" class="fileQueue"></div>
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
    $(function() {
        $("#file_upload").uploadify({
        	swf:'<%=basepath %>/uploadify/scripts/uploadify.swf',
			uploader:'<%=basepath %>/upload/singleUploadFile',
			queueID:'fileQueue',
			formData:{PHPSESSID:'xxx', ajax:1},
			fileObjName:'file',
			buttonImage:'<%=basepath %>/uploadify/img/add.jpg',
			buttonClass:'my-uploadify-button',
			width:102,
			auto:false,
			onUploadSuccess:uploadifySuccess,
			onQueueComplete:uploadifyQueueComplete
        });
    });
</script>
<script type="text/javascript">
var fileJsonList = {};
function uploadifyQueueComplete(queueData){  
    var msg = "文件上传的总数: "+queueData.uploadsSuccessful+"<br/>"  
        + "上传错误的总数: "+queueData.uploadsErrored+"<br/>"  
        + "文件上传的总大小(KB): "+queueData.queueBytesUploaded+"<br/>"  
        + "所有上传文件的平均速度(KB/S): "+queueData.averageSpeed;  
    if (queueData.uploadsErrored) {  
      alertMsg.error(msg);  
    } else {  
      alertMsg.correct(msg);  
      $.pdialog.closeCurrent();
	  $.bringBack({fileName:fileNames,filePathUrl:filePathUrl,fileWebUrl:fileWebUrl,fileSize:fileSizes,fileType:fileTypes});
    }  
} 
function uploadifySuccess(file, data, response) {
   // alert('The file ' + file.name + ' was successfully uploaded with a response of ' + response + ':' + data); 
    if(response){
    	var fileJson=eval('('+data+')');
    	var fileOldName= file.name;
		var fileNewName= fileJson.fileNewName;
		var filePathUrl= fileJson.filePathUrl;
		var fileWebUrl= fileJson.fileWebUrl; 	
		var fileSize= fileJson.fileSize;	
		var fileType= fileJson.fileType;
		
    }
}
/** 
 * http://www.uploadify.com/documentation/uploadify/onuploaderror/ 
 */  
function uploadifyError(file, errorCode, errorMsg) {  
 alertMsg.error(errorCode+": "+errorMsg);  
}  
  
  
/** 
 * http://www.uploadify.com/documentation/ 
 * @param {Object} event 
 * @param {Object} queueID 
 * @param {Object} fileObj 
 * @param {Object} errorObj 
 */  
function uploadifyError(event, queueId, fileObj, errorObj){  
  alert("event:" + event + "\nqueueId:" + queueId + "\nfileObj.name:"   
      + fileObj.name + "\nerrorObj.type:" + errorObj.type + "\nerrorObj.info:" + errorObj.info);  
}  
</script>
</html>