<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
        String basepath = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="pagerForm" method="post"
		action="<%=basepath %>/oa/role/findRolePage">
		<input type="hidden" name="status" value="">
		<input type="hidden" name="keywords" value="" />
	    <input type="hidden" name="pageNum" value="${page.pageNo }" />
	    <input type="hidden" name="numPerPage" value="${page.pageSize }" />
	    <input type="hidden" name="orderField" value="" />
	</form>
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);"
			action="<%=basepath %>/oa/role/findRolePage" method="post">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>角色名称：<input type="text" name="roleName"
							value="${oaRole.roleName }" />
						</td>
						<td>创建日期：<input name="starttime" type="text" class="date" readonly="true" value="${starttime }" />
										- 
									<input name="endtime" type="text" class="date" readonly="true"  value="${endtime }"/>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive">
								<div class="buttonContent">
									<button type="submit">检索</button>
								</div>
							</div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<c:if test="${sessionScope.modelflag.add }">
					<li><a class="add"
						href="<%=basepath %>/oarolepage/oaroleadd.jsp"  rel="roletree" target="dialog" mask="true" title="添加" width="900" height="500"><span>添加</span></a></li>
				</c:if>
				<c:if test="${sessionScope.modelflag.editor }">
					<li><a class="edit"
						href="<%=basepath %>/oa/role/IntoRoleUpdate?id={sid_role}"
						rel="roletree" target="dialog"  mask="true" title="编辑" width="900" height="500"><span>修改</span></a></li>
				</c:if>
				<li class="line">line</li>
			</ul>
		</div>
		<div layoutH="120">
			<table class="list" width="100%">
				<thead>
					<tr>
						<th width="2%">序号</th>
						<th width="10%">角色名称</th>
						<th width="30%">角色备注</th>
						<th width="8%">创建时间</th>
						<th width="10%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.result }" var="oaRole" varStatus="st">
						<tr target="sid_role" rel="${oaRole.roleId }">
							<td>${st.index+1+page.pageSize*(page.pageNo-1)}</td>
							<td>${oaRole.roleName }</td>
							<td>${oaRole.remark }</td>
							<td>${oaRole.createTime }</td>
							<td><c:if test="${sessionScope.modelflag.delete }">
									<a title="删除" target="ajaxTodo"
										href="<%=basepath %>/oa/role/delRole?id=${oaRole.roleId}&nodeId=${oaRole.nodeId}&parentId=${oaRole.parentId}&name=${oaRole.roleName}"
										class="btnDel" callback="navTabAjaxDone">删除</a>
								</c:if> <c:if test="${sessionScope.modelflag.editor }">
									<a title="编辑" target="dialog"
										href="<%=basepath %>/oa/role/IntoRoleUpdate?id=${oaRole.roleId}"
										class="btnEdit" rel="roletree"   mask="true" title="编辑" width="900" height="500" >编辑</a>
								</c:if> <c:if test="${sessionScope.modelflag.configure }">
									<a title="添加权限" target="navTab" rel="addauth"
										href="<%=basepath %>/oa/model/makeMore?roleId=${oaRole.roleId}"
										class="btnAdd">添加权限</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span> <select class="combox" name="numPerPage"
					onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="10"
						<c:if test="${page.pageSize == 10}">selected</c:if>>10</option>
					<option value="20"
						<c:if test="${page.pageSize == 20}">selected</c:if>>20</option>
					<option value="50"
						<c:if test="${page.pageSize == 50}">selected</c:if>>50</option>
					<option value="100"
						<c:if test="${page.pageSize == 100}">selected</c:if>>100</option>
				</select> <span>条，共${page.totalCount }条</span>
			</div>
			<div class="pagination" targetType="navTab"
				totalCount="${page.totalCount }" numPerPage="${page.pageSize }"
				pageNumShown="10" currentPage="${page.pageNo }"></div>
		</div>
	</div>
</body>
</html>