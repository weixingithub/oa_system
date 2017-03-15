<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<form  method="post" action="<%=basepath %>/oa/pwelfare/donetask">
	<input type="hidden" name="status" value="">
	<input type="hidden" name="keywords" value=""/>
	<input type="hidden" name="pageNum" value="${page.pageNo }"/>
	<input type="hidden" name="numPerPage" value="${page.pageSize }"/>
	<input type="hidden" name="orderField" value=""/>
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"   action="<%=basepath %>/oa/pwelfare/donetask" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					申请日期：
					<input type="text" name="starttime" class="date" readonly="true" />
					~
					<input type="text" name="endtime" class="date" readonly="true" />
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<li><a class="button" onclick="showpic();" ><span>高级检索</span></a></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
	</div>
	<div layoutH="117">
	<table class="list" width="100%" >
		<thead>
			<tr>
				<th width="4%">序号</th>
				<th width="60%">标题</th>
				<th width="8%">发起用户</th>
				<th width="14%">开始时间</th>
				<th width="14%">结束时间</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach items="${page.result }" var="pw" varStatus="st">
				<tr>
				    <td>${st.index+1+(page.pageNo-1)*page.pageSize }</td>
					<td>${pw.title }</td>
					<td>${pw.activityuserId }</td>
					<td>${pw.starttime }</td>
					<td>${pw.endtime }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="10" <c:if test="${page.pageSize == 10}">selected</c:if> >10</option>
				<option value="20" <c:if test="${page.pageSize == 20}">selected</c:if> >20</option>
				<option value="50" <c:if test="${page.pageSize == 50}">selected</c:if>>50</option>
				<option value="100" <c:if test="${page.pageSize == 100}">selected</c:if>>100</option>
			</select>
			<span>条，共${page.totalCount }条</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${page.totalCount }" numPerPage="${page.pageSize }" pageNumShown="10" currentPage="${page.pageNo }"></div>
	</div>
</div>
</body>
</html>