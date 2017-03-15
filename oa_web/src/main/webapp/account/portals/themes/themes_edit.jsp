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
		$("#themePicId").attr("src",url);
		$("#themePic").attr("value",url);
	}
</script>
<body>
<div class="pageContent">
		<form method="post" action="<%=basePath%>/oa/themes/addAndUpdateThemes"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone)">
			<input type="hidden" name="themeId" value="${themes.themeId}" />
			<input type="hidden" name="themePic" id="themePic" value="${themes.themePic}" />
			<div class="pageFormContent" layoutH="50">
				<fieldset>
					<legend>基本信息</legend>
					<p>
						<label>主题名称：</label> 
						<input class="required" name="themeName" type="text" size="35" value="${themes.themeName}" />
					</p>
					<p>
						<label>主题路径：</label> 
						<input class="required" name="themeUrl" type="text" size="35" value="${themes.themeUrl}" />
					</p>
					<p>
						<label>样式路径：</label> 
						<input class="required" name="themeCss" type="text" size="35" value="${themes.themeCss}" />
					</p>
					<p>
						<label>作者：</label> 
						<input class="required" name="author" type="text" size="35" value="${themes.author}" />
					</p>
					<p>
						<label>示意图片:</label> 
						<span class="info">点击图标上传</span>
						<a class="btnAttach" href="<%=basePath%>/filemanager/file_singleUploadPicture.jsp?fileID=layout" lookupGroup="attachment" width="560" height="300" title="附件"></a>
					</p>
					<p>
						<label>主题颜色：</label> 
						<select class="combox" name="styleColour">
							<c:forEach var="themeColourMap" items="${themeColourMap }">
								<option value="${themeColourMap.key}" <c:if test="${themes.styleColour==themeColourMap.key}">selected</c:if>>${themeColourMap.value}</option>
							</c:forEach>
						</select>
					</p>
				</fieldset>
				<fieldset>
					<legend>介绍说明</legend>
					<textarea name="themeIntro"   maxlength="200" cols="80" rows="3" >${themes.themeIntro }</textarea>
				</fieldset>
				<fieldset>
					<legend>图片预览</legend>
						<img  id="themePicId" alt="" src="${themes.themePic}" width="400" height="600">
				</fieldset>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">提交</button>
							</div>
						</div></li>
					<li><div class="button">
							<div class="buttonContent">
								<button type="button" class="close">取消</button>
							</div>
						</div></li>
				</ul>
			</div>
		</form>
	</div>
</body>
</html>