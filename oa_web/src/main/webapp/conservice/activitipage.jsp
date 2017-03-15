<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
   String basePath = request.getContextPath();
%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<div class="pageContent">
	<form method="post" action="<%=basePath %>/oa/pwelfare/start" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
	    <input type="hidden"  name="id" value="${id }">
		<div class="pageFormContent" layoutH="50">
		    <div class="unit">
				<label>标题：</label>
				${title }
			</div>
			<div class="unit">
				<label>创建时间：</label>
				${creattime }
			</div>
			<div class="unit">
			    <label>截止日期</label>
				<input type="text" name="finishtime" class="date textInput readonly required" datefmt="yyyy-MM-dd HH:mm" readonly="true" />
			</div>
			<div class="unit">
			  <dl class="nowrap">
			  <dd><select class="combox required" name="assigneperson">
			    <option value="">请选择审核人</option>
			     <c:forEach items="${oaRole.sysUser }"  var="users" >
			         <option value="${users.userName }">${users.realName }</option>
			     </c:forEach>
			     </select>
			  </dd>
			 </dl>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>
</body>
</html>