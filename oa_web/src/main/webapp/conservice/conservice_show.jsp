<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
   String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel = "stylesheet" href = "<%=basePath %>/wechat/css/bootstrap.min.css" >
	<script src = "<%=basePath %>/wechat/js/bootstrap.min.js" ></script >
</head>
<body>

<style >
		.content{
			height: 100%;
			padding: 15px 10px 50px 10px;
		}
		.content img{
			width: auto;
		}
		.content_bg{
			width: 80%;
			margin:0 auto;
		}
	</style >
</head >
<body>
<div class="container-fluid content_bg">
	<div class="row">
		<div class="content" id="panel">
			${personWelfare.personWelfareContent.content }
		</div>
	</div>
</div>
</body>
</html>