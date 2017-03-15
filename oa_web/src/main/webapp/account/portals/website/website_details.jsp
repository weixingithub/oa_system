<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<div class="accordion" fillSpace="">
	<div class="accordionHeader">
		<h2><span>icon</span>面板1</h2>
	</div>
	<div class="accordionContent" style="height:200px">
		内容1
	</div>
	<div class="accordionHeader">
		<h2><span>icon</span>面板2</h2>
	</div>
	<div class="accordionContent">
		内容2
	</div>
	<div class="accordionHeader">
		<h2><span>icon</span>面板3</h2>
	</div>
	<div class="accordionContent">
		内容3
	</div>
</div>
</body>
</html>