<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String basepath = request.getContextPath();
	String excelType = request.getParameter("excelType");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Excel导入</title>
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
			</dl>
			<div class="divider"></div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
			</ul>
		</div>
	</div>
</body>
<script type="text/javascript">
    $(function() {
        $("#file_upload").uploadify({
       	 swf:'<%=basepath %>/uploadify/scripts/uploadify.swf',
			uploader:'<%=basepath %>/upload/singleUploadFile',
			formData:{'fileID':'excel'},
			buttonText:'请选择文件',
			fileSizeLimit:'36000KB',
			fileTypeDesc:'*.*;',
			fileTypeExts:'*.xls;',
			auto:true,
			fileObjName:'file',
			multi:true,
			onUploadSuccess:function(file, data, response) {
				var excelType = "<%=excelType%>";
				var url = "";
				if(excelType=="family"){ //户籍
					url = "<%=basepath %>/oa/civil/civilExcelImport";
				}
				if(excelType=="person"){//人口基本信息
					url = "<%=basepath %>/oa/person/personExcelImport";
				}
				if(excelType=="populationcivil"){//人口民政基本信息
					url = "<%=basepath %>/oa/civil/civilExcelImport";
				}
				if(excelType=="familyplanning"){ //人口计生基础信息
					url = "<%=basepath %>/oa/planning/familyPlanningExcelImport";
				}
				if(excelType=="laborinsurance"){ //个人劳动保障基础信息
					url = "<%=basepath %>/oa/labour/labourExcelImport";
				}
				if(excelType=="stability"){//人口综治基础信息
					url = "<%=basepath %>/oa/stability/stabilityExcelImport";
				}
			   	 //alert('The file ' + file.name + ' was successfully uploaded with a response of ' + response + ':' + data); 
			    if(response){
			    	var fileJson=eval('('+data+')');
			    	var fileOldName= file.name;
					var fileNewName= fileJson.fileNewName;
					var filePathUrl= fileJson.filePathUrl;
					var fileWebUrl= fileJson.fileWebUrl; 	
					var fileSize= fileJson.fileSize;	
					var fileType= fileJson.fileType;
					alertMsg.correct("文件上传成功!");  
					excelConfirmMsg(url,filePathUrl+fileNewName);
			    }else{
			    	alertMsg.error("文件上传失败!");  
			    }
			}
        });
    });
</script>
<script type="text/javascript">
function uploadifySuccessExcel(file, data, response) {
	var excelType = "<%=excelType%>";
	var url = "";
	if(excelType=="family"){ //户籍
		url = "<%=basepath %>/oa/civil/civilExcelImport";
	}
	if(excelType=="person"){//人口基本信息
		url = "<%=basepath %>/oa/person/personExcelImport";
	}
	if(excelType=="populationcivil"){//人口民政基本信息
		url = "<%=basepath %>/oa/civil/civilExcelImport";
	}
	if(excelType=="familyplanning"){ //人口计生基础信息
		url = "<%=basepath %>/oa/planning/familyPlanningExcelImport";
	}
	if(excelType=="laborinsurance"){ //个人劳动保障基础信息
		url = "<%=basepath %>/oa/labour/labourExcelImport";
	}
	if(excelType=="stability"){//人口综治基础信息
		url = "<%=basepath %>/oa/stability/stabilityExcelImport";
	}
   	 //alert('The file ' + file.name + ' was successfully uploaded with a response of ' + response + ':' + data); 
    if(response){
    	var fileJson=eval('('+data+')');
    	var fileOldName= file.name;
		var fileNewName= fileJson.fileNewName;
		var filePathUrl= fileJson.filePathUrl;
		var fileWebUrl= fileJson.fileWebUrl; 	
		var fileSize= fileJson.fileSize;	
		var fileType= fileJson.fileType;
		alertMsg.correct("文件上传成功!");  
		excelConfirmMsg(url,filePathUrl+fileNewName);
    }else{
    	alertMsg.error("文件上传失败!");  
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
  alert("event:" + event + "\nqueueId:" + queueId + "\nfileObj.name:" + fileObj.name + "\nerrorObj.type:" + errorObj.type + "\nerrorObj.info:" + errorObj.info);  
}  
 
//Excel导入
function excelConfirmMsg(url, filePathName){
	 if(filePathName!=null|| filePathName!=""){
		alertMsg.confirm("确定要导入数据吗?", {
			okCall: function(data){
				$.ajax({
					dataType:"json",
					type:"post",
					url:url,
					data:{ filePath:filePathName},
					success:function(json){
						 alertMsg.correct(json.message);
					}	
				});
			}
		});
	 }else{
		 alertMsg.error("请上传您要导入的Excel文件!");   
	 }
}
</script>
</html>