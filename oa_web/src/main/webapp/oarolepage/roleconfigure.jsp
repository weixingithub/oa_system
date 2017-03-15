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
<form id="pagerForm" method="post" action="<%=basepath %>/oa/model/makeMore">
	<input type="hidden" name="status" value="">
	<input type="hidden" name="keywords" value=""/>
	<input type="hidden" name="pageNum" value="${page.pageNo }"/>
	<input type="hidden" name="numPerPage" value="${page.pageSize }"/>
	<input type="hidden" name="orderField" value=""/>
	<input type="hidden" name="roleId" value="${roleId}"/>
</form>
<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);"   action="<%=basepath %>/oa/model/makeMore" method="post">
	<input type="hidden" name="roleId" value="${roleId}"/>
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
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
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
	<div layoutH="120">
	<table class="list" width="100%" >
		<thead>
			<tr>
				<th width="4%">是否已选</th>
				<th width="2%" >序号</th>
				<th width="8%" >模块名称</th>
				<th width="35%" >模块备注</th>
				<th width="10%">操作</th>
			</tr>
		</thead>   
		<tbody>
		    <c:forEach  items="${page.result }"  var="oaModel" varStatus="st">
			 <tr target="sid_model" rel="${oaModel.modelId }">
			     <td align="center"><input name="ids" <c:if test="${modelIds.contains(oaModel.modelId) }">checked</c:if>  id="${oaModel.modelId  }"  value="${oaModel.modelId  }" type="checkbox"></td>
			     <td>${st.index+1+page.pageSize*(page.pageNo-1)}</td>
				 <td>${oaModel.modelName }</td>
                 <td>${oaModel.remark }</td>
                 <td>
                   <c:if test="${oaModel.modelUrl != null && oaModel.modelUrl !='' }">
					 <a title="配置" target="dialog"   mask="true"  href="<%=basepath %>/oa/role/findOneRoleModel?roleId=${roleId}&isCheckModel=${oaModel.isCheckModel}&modelId=${oaModel.modelId}&mparentId=${oaModel.parentId}" class="btnAssign">配置</a>
				   </c:if>
				   <c:if test="${oaModel.modelUrl == null || oaModel.modelUrl =='' }">
				     <c:if test="${modelIds.contains(oaModel.modelId) }">
					   <a title="删除"  target="ajaxTodo"  href="<%=basepath %>/oa/role/selNoUrlMeun?roleId=${roleId}&modelId=${oaModel.modelId}&mparentId=${oaModel.parentId}&type=0" class="btnDel"  callback="navTabAjaxDone">取消</a>
					  </c:if>
					   <c:if test="${!modelIds.contains(oaModel.modelId) }">
					   <a  target="ajaxTodo"  href="<%=basepath %>/oa/role/selNoUrlMeun?roleId=${roleId}&modelId=${oaModel.modelId}&mparentId=${oaModel.parentId}&type=1" class="btnSelect">选定</a>
					  </c:if>
				   </c:if>
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