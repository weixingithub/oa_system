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
<body>
<div class="pageContent"> 
	<form method="post" action="<%=basePath%>/oa/module/updateModule"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<input type="hidden" name="modelId" value="${module.modelId}" />
		<input type="hidden" name="modelNid" value="${module.modelNid}" />
		<input type="hidden" name="modelPid" value="${module.modelPid}" />
		<input type="hidden" name="isParent" value="${module.isParent}" />
		<input type="hidden" name="menuId" value="${module.menuId}" />
		<div class="pageFormContent" layoutH="100" >
			 <fieldset >
				<legend>基本信息</legend>
				<p>
					<label>选择数据源：</label> 
					<input name="modelName" type="text"   value="${module.modelName }"  class="required"  readonly />
					<a class="btnLook" href="<%=basePath %>/oa/model/findOaModelPageSelect?select=radio" lookupGroup=""  rel="module_radio" >查找带回</a>
				</p>
				<p>
					<label>访问地址：</label> 
					<input name="modelUrl"  type="text" value="${module.modelUrl }" />
				</p>
				<p>
					<label>数据源标记：</label> 
					<input name="modelKey"  type="text" value="${module.modelKey }" class="required" readonly />
				</p>
				<p>
					<label>权限类型：</label> 
					<select class="combox" name="modelRole">
						<option value="0" <c:if test="${module.modelRole==0}">selected</c:if>>默认</option>
						<option value="1" <c:if test="${module.modelRole==1}">selected</c:if>>游客可见</option>
						<option value="2" <c:if test="${module.modelRole==2}">selected</c:if>>登录可见</option>
					</select>
				</p>
				<p>
					<label>选择插件：</label> 
					<input name="pluginId"   type="hidden" value="${module.plugin.pluginId }" />
					<input name="pluginName" type="text"   value="${module.plugin.pluginName }"  class="required"  readonly />
					<a class="btnAdd" href="<%=basePath %>/oa/plugin/findPluginPagejbsxBox" lookupGroup=""  rel="plugin_radio">查找带回</a>
				</p>
			</fieldset>
		</div>
		<div class="formBar">
			<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit" >提交</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
</body>
</html>