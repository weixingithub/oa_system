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
	<form id="pagerForm" method="post" action="<%=basepath %>/oa/population/findFamilyPage">
		<input type="hidden" name="pageNum" value="${page.pageNo }" />
	    <input type="hidden" name="numPerPage" value="${page.pageSize }" /> 
	    <input type="hidden" name="orderField" value="${orderField}" />
	    <input type="hidden" name="orderDirection" value="${orderDirection}" />
	    <input type="hidden" name="startTime" value="${startTime}" />
	    <input type="hidden" name="endTime" value="${endTime}" />
	</form>
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="<%=basepath %>/oa/population/findFamilyPage" method="post">
			<input type="hidden" name="pageNum" value="${page.pageNo }" />
	    	<input type="hidden" name="numPerPage" value="${page.pageSize }" /> 
	    	<input type="hidden" name="orderField" value="${orderField}" />
	    	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	    	
			<input type="hidden" name="householderName" value="${householderName}" > 
			<input type="hidden" name="doublefemaleHouseholds" value="${doublefemaleHouseholds}" > 
			<input type="hidden" name="oneChildFamily" value="${oneChildFamily}" > 
			<input type="hidden" name="placestate" value="${placestate}" > 
			<input type="hidden" name="placetype" value="${placetype}" > 
			<input type="hidden" name="poorfamilystate" value="${poorfamilystate}" > 
			<input type="hidden" name="lowincomestate" value="${lowincomestate}" > 
			<input type="hidden" name="lowincometype" value="${lowincometype}" > 

			<input type="hidden" name="singleparent" value="${singleparent}" >
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
							<input name="orgId" value="" type="hidden"/>
							<input class="required" name="orgName" type="text" readonly/>
							<a  href="<%=basepath %>/oaorgpage/orgtree.jsp" lookupGroup="">选择部门</a>	
						</td>
						
						<td>户籍编号：<input type="text" name="householderID" value="${householderID }" />
						</td>
						<td>创建日期：
						<input name="startTime" type="text" class="date" readonly="true" value="${startTime}" />
						- 
						<input name="endTime" type="text" class="date" readonly="true" value="${endTime}"/>
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
						<li><a class="button" href="<%=basepath %>/oa/population/findByIdSearch" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<c:if test="${sessionScope.modelflag.add }">
					<li><a class="add" href="<%=basepath %>/oa/population/intoAddFamily" target="navTab"><span>添加</span></a></li>
				</c:if>
				<c:if test="${sessionScope.modelflag.delete }">
					<li><a class="delete" href="<%=basepath %>/oa/population/deleteFamily?ids={sid_role}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
				</c:if>
				<c:if test="${sessionScope.modelflag.editor }">
					<li><a class="edit" href="<%=basepath %>/oa/population/intoUpdateFamily?id={sid_role}" target="navTab"><span>修改</span></a></li>
					<li><a class="icon" href="<%=basepath %>/filemanager/file_excel.jsp?excelType=family" target="dialog" mask="true" title="导入户籍信息" width="500" height="300"><span>导入EXCEL</span></a></li>
				</c:if>
			</ul>
		</div>
		<div layoutH="120">
			<table class="list" width="100%">
				<thead>
					<tr>
						<th width="2%">序号</th>
						<th width="10%">户籍编号</th>
						<th width="8%">户主姓名</th>
						<th width="8%">户主联系电话</th>
						<th width="8%" orderField="createTime" <c:if test="${orderField == 'createTime'}">  class="${orderDirection}" </c:if>>创建时间</th>
						<th width="10%">所属部门</th>
						<th width="10%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.result }" var="famliy" varStatus="st">
						<tr target="sid_role" rel="${famliy.id }">
							<td>${st.index+1+page.pageSize*(page.pageNo-1)}</td>
							<td>${famliy.householderID}
							      <a title="详情" href="<%=basepath %>/oa/population/familyDetails?id=${famliy.id }" class="btnLook"  target="dialog" mask="true" width="900" height="600">详情</a>
							<td>${famliy.householderName }</td>
							<td>${famliy.householderContact }</td>
							<td>${famliy.createTime }</td>
							<td>${famliy.orgName }</td>
							<td>
								<c:if test="${sessionScope.modelflag.delete }">
									<a title="删除" target="ajaxTodo" href="<%=basepath %>/oa/population/deleteFamily?ids=${famliy.id }" class="btnDel" callback="navTabAjaxDone">删除</a>
								</c:if> <c:if test="${sessionScope.modelflag.editor }">
									<a title="编辑" target="navTab" href="<%=basepath %>/oa/population/intoUpdateFamily?id=${famliy.id }" class="btnEdit" >编辑</a>
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