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
	<div class="pageHeader">
		<form  id="pagerForm" onsubmit="return dwzSearch(this, 'dialog');" action="<%=basepath %>/oa/model/findOaModelPageSelect" method="post">
		<input type="hidden" name="pageNum" value="${page.pageNo }" />
		<input type="hidden" name="numPerPage" value="${page.pageSize }"/>
		<input type="hidden" name="select" value="${select }"/>
		<input type="hidden" name="modelType" value="${modelType }"/>
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
							模块名称：<input type="text" name="modelName" value="${oaModel.modelName }" />
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
						<li><div class="button"><div class="buttonContent"><button type="button" multLookup="model" warn="请选择模块">选择带回</button></div></div></li>
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
						<th width="2%"></th>
						<th width="2%" >序号</th>
						<th width="8%" >模块名称</th>
						<th width="35%" >模块备注</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach  items="${page.result }"  var="oaModel" varStatus="st">
						 <tr  >
						 	 <td>
						 	 	<c:if test="${oaModel.isParent !='true' }">
							 	 	<input type="checkbox" name="model" value="{moduleId:'${oaModel.modelId }',moduleName:'${oaModel.modelName }',modelUrl:'${oaModel.stageUrl}'}"/>
						 	 	</c:if>
						 	 </td>
						     <td>${st.index+1+page.pageSize*(page.pageNo-1)}</td>
							 <td>${oaModel.modelName }</td>
			                 <td>${oaModel.remark }</td>
		                   	 
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span> 
				<select class="combox" name="numPerPage" onchange="dwzPageBreak({targetType:'dialog', numPerPage:'100'})">
					<option value="100" <c:if test="${page.pageSize == 100}">selected</c:if>>100</option>
				</select> 
				<span>条，共${page.totalCount }条</span>
			</div>
			<div class="pagination" targetType="dialog" totalCount="${page.totalCount }" numPerPage="${page.pageSize }" pageNumShown="100" currentPage="${page.pageNo }"></div>
		</div>
	</div>
</body>
</html>