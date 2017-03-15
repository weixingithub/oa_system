<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<script type="text/javascript">
	function addimage(url){
		$("#layoutThumbPicId").attr("src",url);
		$("#layoutThumbPic").attr("value",url);
	}
</script>
<body>
	<div class="pageContent">
		<form method="post" action="<%=basePath%>/oa/layout/addAndUpdateLayout"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone)">
			<input type="hidden" name="layoutId" value="${layout.layoutId}" />
			<input type="hidden" name="layoutThumbPic" id="layoutThumbPic" value="${layout.layoutThumbPic}" />
			<div class="pageFormContent" layoutH="70" >
			<fieldset>
					<legend>基本信息</legend>
				<p>
					<label>页面名称：</label> 
					<input class="required" name="layoutname" type="text" size="35" value="${layout.layoutname}" />
				</p>
				<p>
					<label>页面级别：</label> 
					<select class="combox" name="layoutlevel">
						<c:forEach var="layoutlevelMap" items="${layoutlevelMap }">
							<option value="${layoutlevelMap.key}" <c:if test="${layout.layoutlevel==layoutlevelMap.key}">selected</c:if>>${layoutlevelMap.value}</option>
						</c:forEach>
					</select>
				</p>
				<p>
					<label>链接地址：</label> 
					<input class="required" name="layoutPageUrl" type="text" size="35" value="${layout.layoutPageUrl}" />
				</p>
				<p>
					<label>编辑地址：</label> 
					<input class="required" name="layoutEditUrl" type="text" size="35" value="${layout.layoutEditUrl}" />
				</p>
				<p>
					<label>示意图片:</label> 
					<span class="info">点击图标上传</span>
					<a class="btnAttach" href="<%=basePath%>/filemanager/file_singleUploadPicture.jsp?fileID=layout" lookupGroup="attachment" width="560" height="300" title="附件"></a>
				</p>
				</fieldset>
				<fieldset>
					<legend>介绍说明</legend>
					<textarea name="layoutIntro" maxlength="300" cols="40" rows="3" >${layout.layoutIntro}</textarea>
				</fieldset>
				<fieldset>
					<legend>图片预览</legend>
						<img  id="layoutThumbPicId" alt="" src="${layout.layoutThumbPic}" width="400" height="600">
				</fieldset>
			</div>
			<div class="formBar">
				<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
				</ul>
			</div>
		</form>
	</div>
</body>
</html>