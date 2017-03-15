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
<form id="pagerForm" method="post" action="<%=basepath %>/oa/sysuser/findSysUserPage" onsubmit="return divSearch(this,'sysUserBox')">
	<input type="hidden" name="sequence" value="${sequence }">
	<input type="hidden" name="orgId" value="${orgId }"/>
	<input type="hidden" name="pageNum" value="${page.pageNo }"/>
	<input type="hidden" name="numPerPage" value="${page.pageSize }"/>
	<input type="hidden" name="orderField"      value="${orderField }" />  
    <input type="hidden" name="orderDirection"  value="${orderDirection }" />  
    <input name="token" value="${userinfor.token }" type="hidden"/>
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					姓名：<input type="text" name="realName" value="${sysUser.realName }" />
				</td>
				<td>
					账号：<input type="text" name="userName" value="${sysUser.userName }" />
				</td>
				<td>
					创建日期：<input type="text" name="startTime" class="date" readonly="true" value="${startTime }"/> - <input type="text" name="endTime" class="date" readonly="true" value="${endTime }"/>
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
			  <li><a class="add" href="<%=basepath %>/oa/sysuser/addIntoSysUser?orgIds=${orgId }" target="dialog" mask="true" title="添加" width="800" height="480"><span>添加</span></a></li>
			</c:if>
			 <c:if test="${sessionScope.modelflag.delete }">
			<li><a class="delete" title="确定要删除吗?" href="<%=basepath%>/oa/sysuser/delSysUser?ids={sid_user}" target="ajaxTodo"><span>删除</span></a></li>
			</c:if>
			<c:if test="${sessionScope.modelflag.editor }">
			<li><a class="edit" href="<%=basepath %>/oa/sysuser/updateIntoSysUser?id={sid_user}" target="dialog" mask="true" title="编辑" width="800" height="480"><span>修改</span></a></li>
			</c:if>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="210" rel="sysUserBox">
		<thead>
			<tr>
				<th width="3%" >序号</th>
				<th width="15%">姓名</th>
				<th width="15%" orderField="user_name" <c:if test="${orderField == 'user_name'}">  class="${orderDirection}" </c:if> >账号</th>
				<th width="5%"  orderField="user_age" <c:if test="${orderField == 'user_age'}">  class="${orderDirection}" </c:if> >年龄</th>
				<th width="5%"  orderField="user_sex" <c:if test="${orderField == 'user_sex'}">  class="${orderDirection}" </c:if> >性别</th>
				<th width="13%" orderField="user_createTime" <c:if test="${orderField == 'user_createTime'}">  class="${orderDirection}" </c:if>  >创建时间</th>
				<th width="15%">操作</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach  items="${page.result }"  var="user" varStatus="status">
			 <tr target="sid_user" rel="${user.id }">
			     <td align="center">${status.index+1+(page.pageNo-1)*page.pageSize }</td>
				 <td><c:if test="${sessionScope.modelflag.detail }"> <a title="详情" href="<%=basepath %>/oa/sysuser/showIntoSysUser?id=${user.id}" class="btnLook"  target="dialog" mask="true" width="800" height="480">详情</a></c:if>
					 ${user.realName }
				 </td>
                 <td>${user.userName }</td>
                 <td>${user.userAge }</td>
                 <td>
                 <c:if test="${user.userSex ==1 }">
                                        女
                 </c:if>
                  <c:if test="${user.userSex ==2 }">
                                        男
                 </c:if>
                 </td>
                 <td>${user.userCreateTime }</td>
                 <td>
                  	<c:if test="${sessionScope.modelflag.delete }">
                     <a title="删除" href="<%=basepath %>/oa/sysuser/delSysUser?ids=${user.id}&userName=${user.userName}" class="btnDel" target="ajaxTodo">删除</a>
                    </c:if>
                     <c:if test="${sessionScope.modelflag.editor}">
					 <a title="编辑" href="<%=basepath %>/oa/sysuser/updateIntoSysUser?id=${user.id}" class="btnEdit" target="dialog" mask="true" width="800" height="480">编辑</a>
					</c:if>
					<c:if test="${sessionScope.modelflag.configure }">
					<a title="配置"  href="<%=basepath %>/oa/sysuser/findSysUserConfigure?id=${user.id}" class="btnAssign" target="dialog" mask="true" width="600" height="500">配置</a>
					</c:if>
				</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value},'sysUserBox')">
				<option value="10" <c:if test="${page.pageSize == 10}">selected</c:if> >10</option>
				<option value="20" <c:if test="${page.pageSize == 20}">selected</c:if> >20</option>
				<option value="50" <c:if test="${page.pageSize == 50}">selected</c:if>>50</option>
				<option value="100" <c:if test="${page.pageSize == 100}">selected</c:if>>100</option>
			</select>
			<span>条，共${page.totalCount }条</span>
			
		</div>
		<div class="pagination" rel="sysUserBox" targetType="navTab" totalCount="${page.totalCount }" numPerPage="${page.pageSize }" pageNumShown="10" currentPage="${page.pageNo }"></div>
	</div>
</div>
</body>
</html>