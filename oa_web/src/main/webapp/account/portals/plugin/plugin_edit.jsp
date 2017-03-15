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
</head>
<script type="text/javascript">
	function addimage(url){
		$("#pluginimageId").attr("src",url);
		$("#pluginPic").attr("value",url);
	}
</script>
<body>
	<div class="pageContent">
		<form method="post" action="<%=basePath%>/oa/plugin/addAndUpdatePlugin"
			class="pageForm required-validate"
			onsubmit="return validateCallback(this, dialogAjaxDone)">
			<input type="hidden" name="pluginId" value="${plugin.pluginId}" />
			<input type="hidden" name="createTime" value="${plugin.createTime}" />
			<input type="hidden" name="pluginPic" id="pluginPic" value="${plugin.pluginPic }" />
			<div class="pageFormContent" layoutH="50">
				<fieldset>
					<legend>基本信息</legend>
					<dl>
						<dt>插件名称：</dt>
						<dd>
							<input class="required" name="pluginName" type="text" size="20" value="${plugin.pluginName }"  />
						</dd>
					</dl>
					<dl>
						<dt>插件作者：</dt>
						<dd>
							<input class="required" name="author" type="text" size="20" value="${plugin.author }"  />
						</dd>
					</dl>
					<dl>
						<dt>插件类型：</dt>
						<dd>
							<select class="combox" name="pluginType">
								<c:forEach var="pluginTypeMap" items="${pluginTypeMap }">
									<option value="${pluginTypeMap.key}" <c:if test="${plugin.pluginType==pluginTypeMap.key}">selected</c:if>>${pluginTypeMap.value}</option>
								</c:forEach>
							</select>
						</dd>
					</dl>
					<dl>
						<dt>调用地址：</dt>
						<dd>
							<input class="required" name="pluginUrl" type="text" size="50" value="${plugin.pluginUrl }"  />
						</dd>
					</dl>
					<dl>
						<dt>示意图片：</dt>
						<dd>
							<span class="info">点击图标上传</span>
							<a class="btnAttach" href="<%=basePath%>/filemanager/file_singleUploadPicture.jsp?fileID=layout" lookupGroup="attachment" width="560" height="300" title="附件"></a>
						</dd>
					</dl>
				</fieldset>
				<fieldset>
					<legend>介绍说明</legend>
					<textarea name="pluginIntro"  maxlength="300" cols="40" rows="3" >${plugin.pluginIntro }</textarea>
				</fieldset>
				<fieldset>
					<legend>图片预览</legend>
							<img  id="pluginimageId" alt="" src="${plugin.pluginPic }" width="400">
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