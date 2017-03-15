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
	<form id="pagerForm" method="post" action="<%=basepath %>/oa/menu/findMenuPage">
		<input type="hidden" name="pageNum" value="${page.pageNo }" />
	    <input type="hidden" name="numPerPage" value="${page.pageSize }" /> 
	    <input type="hidden" name="orderField" value="${orderField}" />
	    <input type="hidden" name="orderDirection" value="${orderDirection}" />
	</form>
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="<%=basepath %>/oa/menu/findMenuPage" method="post">
			<input type="hidden" name="pageNum" value="${page.pageNo }" />
	    	<input type="hidden" name="numPerPage" value="${page.pageSize }" /> 
	    	<input type="hidden" name="orderField" value="${orderField}" />
	    	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	    	
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>菜单名称：<input type="text" name="name" value="${menu.menuName }" /> </td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<c:if test="${sessionScope.modelflag.add }">
					<li><a class="add" href="<%=basepath %>/oa/menu/intoMenuEdit" title="添加" target="dialog"  mask="true" width="800" height="500" rel="menu_add"><span>添加</span></a></li>
				</c:if>
				<c:if test="${sessionScope.modelflag.delete }">
					<li><a class="delete" href="<%=basepath %>/oa/menu/deleteMenu?id={sid_role}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
				</c:if>
				<c:if test="${sessionScope.modelflag.editor }">
					<li><a class="edit" href="<%=basepath %>/oa/menu/intoMenuEdit?id={sid_role}" target="dialog"  mask="true" width="800" height="500" rel="menu_add"><span>修改</span></a></li>
				</c:if>
			</ul>
		</div>
		<div layoutH="120">
			<table class="list" width="100%">
				<thead>
					<tr>
						<th width="3%">序号</th>
						<th width="10%">菜单名称</th>
						<th width="15%" orderField="createTime" <c:if test="${orderField == 'createTime'}">  class="${orderDirection}" </c:if>>修改时间</th>
						<th width="30%">说明</th>
						<th width="10%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.result }" var="menu" varStatus="st">
						<tr target="sid_role" rel="${menu.menuId }">
							<td>${st.index+1+page.pageSize*(page.pageNo-1)}</td>
							<td>
								${menu.menuName}
								<a title="预览" href="<%=basepath %>/oa/menu/findMenuById?id=${menu.menuId }" class="btnLook" target="dialog" mask="true" width="400" height="500" rel="menu_add">详情</a>
							</td>
							<td>${menu.createTime }</td>
							<td>${menu.menuIntro }</td>
							<td>
								<c:if test="${sessionScope.modelflag.delete }">
									<a title="删除" target="ajaxTodo" href="<%=basepath %>/oa/menu/deleteMenu?id=${menu.menuId }" class="btnDel" callback="navTabAjaxDone">删除</a>
								</c:if> 
								<c:if test="${sessionScope.modelflag.editor }">
									<a title="编辑" target="dialog" href="<%=basepath %>/oa/menu/intoMenuEdit?id=${menu.menuId }" class="btnEdit"  mask="true" width="800" height="500" rel="menu_add">编辑</a>
								</c:if> 
								<c:if test="${sessionScope.modelflag.configure }">
									<a title="配置" target="navTab" href="<%=basepath %>/oa/menu/configMenu?id=${menu.menuId }" class="btnAdd"   >网站配置</a>
								</c:if>
							</td>
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