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
<form id="pagerForm" method="post" action="<%=basepath %>/oa/logger/findloggerPage">
	<input type="hidden" name="status" value="">
	<input type="hidden" name="pageNum" value="${page.pageNo }" />
    <input type="hidden" name="numPerPage" value="${page.pageSize }" /> 
    <input type="hidden" name="orderField" value="${orderField}" />
    <input type="hidden" name="orderDirection" value="${orderDirection}" />
    <input type="hidden" name="startTime" value="${startTime}" />
     <input type="hidden" name="endTime" value="${endTime}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="<%=basepath %>/oa/logger/findloggerPage" method="post">
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					<input name="orgId" value="" type="hidden"/>
					<input class="required" name="orgName" type="text" readonly/>
					<a  href="<%=basepath %>/oaorgpage/orgtree.jsp" lookupGroup="" width="300" height="500">选择部门</a>	
				</td>
				<td>
					<input name="modelId" value="" type="hidden"/>
					<input class="required" name="modelName" type="text" readonly/>
					<a  href="<%=basepath %>/oamodelpage/automodeltree.jsp" lookupGroup="" width="300" height="500">选择模块</a>	
				</td>
				<td>
					用户名：<input type="text" name="userName"  value="${userName}"/>
				</td>
				<td>
					<select class="combox" name="operationType">
						<option value="">所有操作</option>
						<option value="add">添加</option>
						<option value="delete">删除</option>
						<option value="editor">更新</option>
						<option value="configure">配置</option>
					</select>
				</td>
				<td>
					操作日期：<input name="startTime" type="text" class="date" readonly="true"  value="${startTime}"  dateFmt="yyyy-MM-dd HH:mm:ss" />
					- 
					<input name="endTime" type="text" class="date" readonly="true" value="${endTime}" dateFmt="yyyy-MM-dd HH:mm:ss" />
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<li><a class="button" href="<%=basepath %>/upload/uploadify.jsp" target="navTab" ><span>测试页面</span></a></li>
				<li><a class="button" href="<%=basepath %>/oaorgpage/testorgalltree.jsp" target="navTab" ><span>机构树测试</span></a></li>
				<li><a class="button" href="<%=basepath %>/oaorgpage/testorgrolemem.jsp" target="navTab" ><span>机构角色人员树</span></a></li>
				<li><a class="button" href="<%=basepath %>/statistics/statistics_person.jsp"  target="dialog" mask="true" title="图表" width="1000" height="600"><span>dialog图表测试页面1</span></a></li>
				<li><a class="button" href="<%=basepath %>/statistics/statistics_person.jsp"  target="navTab"><span>navTab图表测试页面2</span></a></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
	</div>
	<div layoutH="117">
	<table class="list" width="100%">
		<thead>
			<tr>
				<tr>
				<th width="5%" >编号</th>
				<th width="50%" >操作内容</th>
				<th width="45%" orderField="actiontime" <c:if test="${orderField == 'actiontime'}">  class="${orderDirection}" </c:if>>操作时间</th>
			</tr>
			</tr>
		</thead>
		<tbody>
		    <c:forEach  items="${page.result }"  var="log" varStatus="st">
			<tr target="sid_group" rel="1">
			     <td width="1" align="center">${st.index+1+(page.pageNo-1)*10}</td>
				 <td width="400">${log.actiondescribe}</td>
				 <td width="400">${log.actiontime }</td>
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