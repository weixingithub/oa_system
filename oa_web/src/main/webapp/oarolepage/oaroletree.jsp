<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
<title>权限树</title>
</head>
<body>
	<input type="hidden" name="roleId" id="roleId" value="${roleId}">
	<div class="pageContent">
		<div class="pageFormContent" layoutH="58">
			<ul id="treeRole" class="ztree">

			</ul>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" onclick="emptyRole()">清空</button>
						</div>
					</div>
				</li>
				<li><div class="button">
						<div class="buttonContent">
							<button class="close" type="button">关闭</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</body>
<script type="text/javascript">
	var settingRole = {
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : zTreeOnClick
		}
	};
	function zTreeOnClick(event, treeId, treeNode) {
		$.bringBack({'${keyName}':treeNode.id, parentName:treeNode.name});
	}
	function createTree() {
		var zNodes;
		var roleId = $("#roleId").val();
		$.ajax({
			url:"<%=basepath%>/oa/role/getRoleTree",
			dataType:"json",
			type:"post",
			data:{roleId:roleId},
			global : false,
			success : function(data) {
				zNodes = eval(data);
				$.fn.zTree.init($("#treeRole"), settingRole, zNodes);
			},
			error : function(msg) {
				alert("加载失败!");
			}
		});
	}
	function emptyRole(){
		$.bringBack({'${keyName}':"", parentName:""});
	}
	$(document).ready(function() {
		createTree();
	});
</script>
</html>