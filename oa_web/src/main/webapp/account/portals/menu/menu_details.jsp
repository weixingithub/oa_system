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
	<form method="post" action="<%=basePath%>/oa/menu/updateMenuZtree"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<input type="hidden" name="menuId" value="${menu.menuId}" />
		<input type="hidden" name="menuTree" id="menuTree" value="${menu.menuTree}" />
		<div class="pageFormContent" layoutH="60" >
			<fieldset  >
				<legend>导航菜单</legend>
				 <ul id="treeWebsiteMenu1" name="treeWebsiteMenu1" class="ztree"></ul>
			</fieldset>
		</div>
		<div class="formBar">
			<ul>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
</body>
<script type="text/javascript">
var listztree = ${listztree};
var setting = {
		data: {
			simpleData: {
				enable: true
			}
		} 
	};

	$(document).ready(function(){
		$.fn.zTree.init($("#treeWebsiteMenu1"), setting, listztree);
	});
</script>
</html>