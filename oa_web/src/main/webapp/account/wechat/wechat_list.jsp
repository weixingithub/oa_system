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
	<form id="pagerForm" method="post" action="<%=basepath %>/oa/wechat/getPageWechat">
		<input type="hidden" name="pageNum" value="${page.pageNo }" />
	    <input type="hidden" name="numPerPage" value="${page.pageSize }" /> 
	    <input type="hidden" name="orderField" value="${orderField}" />
	    <input type="hidden" name="orderDirection" value="${orderDirection}" />
	</form>
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="<%=basepath %>/oa/wechat/getPagewechat" method="post">
			<input type="hidden" name="pageNum" value="${page.pageNo }" />
	    	<input type="hidden" name="numPerPage" value="${page.pageSize }" /> 
	    	<input type="hidden" name="orderField" value="${orderField}" />
	    	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	    	
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>微信账号：<input type="text" name="name" value="${wechat.name }" /> </td>
						<td>创建时间：
							<input name="startTime" type="text" class="date" readonly="true" value="${startTime}"/>
							- 
							<input name="endTime" type="text" class="date" readonly="true" value="${endTime}"  />
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
			 	<c:if test="${sessionScope.modelflag.add }">
					<li><a class="add"  href="<%=basepath %>/oa/wechat/IntoWechatEdit" target="dialog" mask="true" title="添加"  width="800" height="500" rel="wechat_add"><span>添加</span></a></li>
			  	</c:if>
				<c:if test="${sessionScope.modelflag.editor }">
					<li><a class="edit" href="<%=basepath %>/oa/wechat/IntoWechatEdit?id={sid_role}"" target="dialog"  mask="true" width="800" height="500" rel="wechat_add"><span>修改</span></a></li>
				</c:if>
			</ul>
		</div>
		<div layoutH="120">
			<table class="list" width="100%">
				<thead>
					<tr>
						<th width="2%">序号</th>
						<th width="10%">微信账号</th>
						<th width="8%">微信AppID</th>
						<th width="8%">所属部门</th>
						<th width="8%" orderField="createTime" <c:if test="${orderField == 'createTime'}">  class="${orderDirection}" </c:if>>创建时间</th>
						<th width="10%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.result }" var="wechat" varStatus="st">
						<tr target="sid_role" rel="${wechat.id }">
							<td>${st.index+1+page.pageSize*(page.pageNo-1)}</td>
							<td>${wechat.name}<a title="预览" href="<%=basepath %>/oa/wechat/IntoWechatEdit?id=${wechat.id}" class="btnLook"  target="dialog" mask="true" width="800" height="500">详情</a>
							<td>${wechat.appId }</td>
							<td>${wechat.orgName }</td>
							<td>${wechat.createTime }</td>
							<td>
								<c:if test="${sessionScope.modelflag.delete }">
									<a title="删除" target="ajaxTodo" href="<%=basepath %>/oa/wechat/deleteWechat?id=${wechat.id }" class="btnDel" callback="navTabAjaxDone">删除</a>
								</c:if> 
								<c:if test="${sessionScope.modelflag.editor }">
									<a title="编辑" target="dialog" href="<%=basepath %>/oa/wechat/IntoWechatEdit?id=${wechat.id }" class="btnEdit" " mask="true" width="800" height="500" rel="wechat_add" >编辑</a>
									<a title="更新Token" target="ajaxTodo" href="<%=basepath %>/oa/wechat/updateWechatToken?id=${wechat.id }"  callback="navTabAjaxDone">更新Token</a>
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