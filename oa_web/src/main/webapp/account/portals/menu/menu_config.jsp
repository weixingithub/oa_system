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
		<div class="pageFormContent" layoutH="50" >
			<div layoutH="50" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
			    <ul id="treeWebsiteMenu2" name="treeWebsiteMenu2" class="ztree"></ul>
			</div>
			<div id="layoutPage" class="unitBox" style="margin-left:246px;">
				<!--#include virtual="list1.html" -->
			</div>
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
		$.fn.zTree.init($("#treeWebsiteMenu2"), setting, listztree);
	});
</script>
</html>