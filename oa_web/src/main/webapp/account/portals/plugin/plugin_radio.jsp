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
		<form id="pagerForm" onsubmit="return dwzSearch(this, 'dialog');" action="<%=basepath %>/oa/plugin/findPluginPagejbsxBox" method="post">
			<input type="hidden" name="pageNum" value="${page.pageNo }" />
	    	<input type="hidden" name="numPerPage" value="${page.pageSize }" /> 
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>插件名称：<input type="text" name="pluginName" value="${plugin.pluginName }" /> </td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="submit">检索</button>
								</div>
							</div>
						</li>
						<li>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="button" onclick="javascript:$.bringBack({pluginId:'',pluginName:''})">置空</button>
								</div>
							</div>
					    </li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div layoutH="120">
			<table class="list" width="100%">
				<thead>
					<tr>
						<th width="3%">序号</th>
						<th width="10%">插件名称</th>
						<th width="6%">作者</th>
						<th width="30%">说明</th>
						<th width="10%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.result }" var="plugin" varStatus="st">
						<tr target="sid_role" rel="${plugin.pluginId }">
							<td>${st.index+1+page.pageSize*(page.pageNo-1)}</td>
							<td>
							    <a title="效果图" href="<%=basepath %>/oa/plugin/findPluginById?id=${plugin.pluginId }" class="btnLook"  target="dialog" mask="true" width="700" height="500" rel="plugin_pic">详情</a>
								${plugin.pluginName}
							</td>
							<td>${plugin.author }</td>
							<td>${plugin.pluginIntro }</td>
							<td>
							   	 <a class="btnSelect" href="javascript:$.bringBack({pluginId:'${plugin.pluginId }',pluginName:'${plugin.pluginName}'})" title="确认选择">选择</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span> <select class="combox" name="numPerPage"
					onchange="dwzPageBreak({targetType:'dialog', numPerPage:'10'})">
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
			<div class="pagination" targetType="dialog"
				totalCount="${page.totalCount }" numPerPage="${page.pageSize }"
				pageNumShown="10" currentPage="${page.pageNo }"></div>
		</div>
	</div>
</body>
</html>