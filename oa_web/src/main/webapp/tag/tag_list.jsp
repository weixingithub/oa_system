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
<div class="pageHeader" style="border:1px #B8D0D6 solid">
<form id="pagerForm" method="post" action="<%=basepath %>/oa/tagCategory/findTagByPage" onsubmit="return navTabSearch(this);">
	<input type="hidden" name="sequence" value="${sequence }">
	<input type="hidden" name="pageNum" value="${page.pageNo }"/>
	<input type="hidden" name="numPerPage" value="${page.pageSize }"/>
	<input type="hidden" name="orderField"      value="${orderField }" />  
    <input type="hidden" name="orderDirection"  value="${orderDirection }" />  
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					标签类别名称：<input type="text" name="realName" value="${tagCategory.tagName}" />
				</td>
				<td>
					创建日期：<input type="text" name="startTime" class="date" readonly="true" value="${endTime}"/> - <input type="text" name="endTime" class="date" readonly="true" value="${endTime}"/>
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
<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
	<div class="panelBar">
		<ul class="toolBar">
		    <c:if test="${sessionScope.modelflag.add }">
			  <li><a class="add" href="<%=basepath %>/oa/tagCategory/toAddTagCategory" target="dialog" mask="true" title="添加" width="800" height="480"><span>添加</span></a></li>
			</c:if>
			 <c:if test="${sessionScope.modelflag.delete }">
			<li><a class="delete" title="确定要删除吗?" href="<%=basepath%>/oa/tagCategory/delTagCategory?id={tid_tagCategory}" target="ajaxTodo"><span>删除</span></a></li>
			</c:if>
			<c:if test="${sessionScope.modelflag.editor }">
			<li><a class="edit" href="<%=basepath %>/oa/tagCategory/toUpdateTagCategory?id={tid_tagCategory}" target="dialog" mask="true" title="编辑" width="800" height="480"><span>修改</span></a></li>
			</c:if>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="210">
		<thead>
			<tr>
				<th width="3%" >序号</th>
				<th width="15%">标签名称</th>
				<th width="8%" orderField="createTime" <c:if test="${orderField == 'createTime'}">  class="${orderDirection}" </c:if>>创建时间</th>
				<th width="15%">操作</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach  items="${page.result}"  var="tagCategory" varStatus="status">
			 <tr target="tid_tagCategory" rel="${tagCategory.tagId}">
			     <td align="center">${status.index+1+(page.pageNo-1)*page.pageSize }</td>

                 <td>${tagCategory.tagName}</td>
                 <td>${tagCategory.createTime}</td>
                 <td>
                  	<c:if test="${sessionScope.modelflag.delete }">
                     <a title="删除" href="<%=basepath %>/oa/tagCategory/delTagCategory?id=${tagCategory.tagId}" class="btnDel" target="ajaxTodo">删除</a>
                    </c:if>
                     <c:if test="${sessionScope.modelflag.editor}">
					 <a title="编辑" href="<%=basepath %>/oa/tagCategory/toUpdateTagCategory?id=${tagCategory.tagId}" class="btnEdit" target="dialog" mask="true" width="800" height="480">编辑</a>
					</c:if>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
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