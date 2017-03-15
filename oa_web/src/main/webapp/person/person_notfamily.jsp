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
	<form id="pagerForm" method="post" action="<%=basepath %>/oa/person/getFamilyPersonPage" onsubmit="return dwzSearch(this, 'dialog');">
		<input type="hidden" name="pageNum" value="${page.pageNo }" />
	    <input type="hidden" name="numPerPage" value="${page.pageSize }" /> 
	    <input type="hidden" name="orderField" value="${orderField}" />
	    <input type="hidden" name="orderDirection" value="${orderDirection}" />
	    <input type="hidden" name="startTime" value="${startTime}" />
	    <input type="hidden" name="endTime" value="${endTime}" />
	    <input type="hidden" name="name" value="${person.name }" />
	    <input type="hidden" name="idnumber" value="${person.idnumber }" />
	    <input type="hidden" name="conditioned" value="${conditioned }" />
	</form>
	<div class="pageHeader">
		<form onsubmit="return dwzSearch(this, 'dialog');" action="<%=basepath %>/oa/person/getFamilyPersonPage" method="post">
		<input type="hidden" name="pageNum" value="${page.pageNo }" />
	    	<input type="hidden" name="numPerPage" value="${page.pageSize }" /> 
	    	<input type="hidden" name="orderField" value="${orderField}" />
	    	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	    	<input type="hidden" name="conditioned" value="${conditioned}" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>姓名：<input type="text" name="name"
							value="${person.name }" />
						</td>
						<td>身份证号：<input type="text" name="idnumber"
							value="${person.idnumber }" />
						</td>
						<td>出生日期：
						<input name="startTime" type="text" class="date" readonly="true" value="${startTime}"/>
						- 
						<input name="endTime" type="text" class="date" readonly="true" value="${endTime}"  />
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive">
								<div class="buttonContent">
									<button type="submit">检索</button>
								 </div>
							</div>
					    </li>
					</ul>
					<ul>
						<li><div class="buttonActive">
								<div class="buttonContent">
									<button type="button" onclick="javascript:$.bringBack({personId:'',personName:'',personContact:''})">置空</button>
								 </div>
							</div>
					    </li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li class="line">line</li>
			</ul>
		</div>
		<div layoutH="135">
			<table class="list" width="100%">
				<thead>
					<tr>
						<th>序号</th>
						<th>姓名</th>
						<th>性别</th>
						<th>出生日期</th>
						<th>身份证号</th>
						<th>联系方式</th>
						<th orderField="creatime" <c:if test="${orderField == 'createTime'}">  class="${orderDirection}" </c:if>>创建时间</th> 
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.result }" var="person" varStatus="st">
						<tr target="sid_person" rel="${person.id }">
							<td align="center">${st.index+1+page.pageSize*(page.pageNo-1)}</td>
							<td>${person.name }</td>
			                <td><c:if test="${person.sex==0 }">男</c:if> <c:if test="${person.sex==1 }">女</c:if>  </td>
			                <td>${person.birthdate}</td>
			                <td>${person.idnumber}</td>
	                        <td>${person.contact}</td>
			                <td>${person.creatime}</td>
			                <td>
			                	<a class="btnSelect" href="javascript:$.bringBack({personId:'${person.id }',personName:'${person.name }',personIdnumber:'${person.idnumber}',personContact:'${person.contact}'})" title="确认选择">选择</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span> 
				<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="10" <c:if test="${page.pageSize == 10}">selected</c:if>>10</option>
					<option value="20" <c:if test="${page.pageSize == 20}">selected</c:if>>20</option>
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