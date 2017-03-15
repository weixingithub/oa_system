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
<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
	<table class="table" width="100%" layoutH="150" rel="layoutPage">
		<thead>
			<tr>
				<th width="2%">序号</th>
				<th width="10%">页面名称</th>
				<th width="8%">页面级别</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${website.layoutlist}" var="layout" varStatus="st">
				<tr target="sid_role" rel="${layout.layoutId }">
					<td>${st.index+1+page.pageSize*(page.pageNo-1)}</td>
					<td>
						<a title="预览" class="btnLook" href=" <c:url value='/oa/fileCabinet/previewFile' >  
								<c:param name="fileUrl" value='${layout.layoutThumbPic }' /></c:url>"
								width="1100" mask="true" height="500" target="dialog" title="文件预览" rel="file">预览</a>
						${layout.layoutname}
					</td>
					<td>
						<c:if test="${layout.layoutlevel == '1' }">首页页面</c:if> 
					    <c:if test="${layout.layoutlevel == '2' }">列表页面</c:if> 
					    <c:if test="${layout.layoutlevel == '3' }">详情页面</c:if> 
					    <c:if test="${layout.layoutlevel == '4' }">自定义页面</c:if> 
					</td>
					<td>
						<a title="配置"  href="<%=basepath %>/oa/layout/configLayout?websiteId=${website.id}&layoutId=${layout.layoutId}&configType=${configType}" class="btnAdd"  target="dialog" mask="true" width="700" height="550" rel="config_Layout">配置</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>