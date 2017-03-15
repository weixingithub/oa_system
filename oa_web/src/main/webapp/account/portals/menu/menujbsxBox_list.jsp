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
<div class="pageHeader" style="border:1px #B8D0D6 solid">
	<form id="pagerForm" onsubmit="return divSearch(this, 'layoutPage');" action="<%=basepath %>/oa/menu/findMenuPagejbsxBox" method="post">
		<input type="hidden" name="pageNum" value="${page.pageNo }" />
    	<input type="hidden" name="numPerPage" value="${page.pageSize }" /> 
    	<input type="hidden" name="orderField" value="${orderField}" />
    	<input type="hidden" name="websiteId" value="${websiteId}" />
    	<input type="hidden" name="orderDirection" value="${orderDirection}" />
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>名称：<input type="text" name="menuName" value="${menu.menuName }"  /> </td>
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
<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
	<table class="table" width="100%" layoutH="210" rel="layoutPage">
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
						<a title="预览" href="<%=basepath %>/oa/menu/findMenuById?id=${menu.menuId }" class="btnLook"  target="dialog" mask="true" width="400" height="500" rel="menu_add">详情</a>
						${menu.menuName}
					</td>
					<td>${menu.createTime }</td>
					<td>${menu.menuIntro }</td>
					<td>
						 <c:if test="${website.menuId==menu.menuId}">
						   <a title="取消"  target="ajaxTodo"  href="<%=basepath %>/oa/website/updateWebsiteByMenu?id=${websiteId}" class="btnDel"    callback="navTabAjaxDone">取消</a>
						  </c:if>
						   <c:if test="${website.menuId!=menu.menuId }">
						   <a title="绑定"  target="ajaxTodo"  href="<%=basepath %>/oa/website/updateWebsiteByMenu?id=${websiteId}&menuId=${menu.menuId}" class="btnSelect" callback="navTabAjaxDone">选定</a>
					   </c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value},'layoutPage')">
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
		<div class="pagination" rel="layoutPage" targetType="navTab"
			totalCount="${page.totalCount }" numPerPage="${page.pageSize }"
			pageNumShown="10" currentPage="${page.pageNo }"></div>
	</div>
</div>
</body>
</html>