<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<!DOCTYPE html>
<html >
<head lang = "en" >
  <meta charset = "UTF-8" >
  <title ></title >
	<link rel = "stylesheet" href = "css/bootstrap.min.css" >
	<script src = "js/bootstrap.min.js" ></script >
	<script src = "js/jquery.1.11.1.min.js" ></script >
	<style >

		.WeChat_header img,
		.WeChat_footer img{
			width: 80%;
			position: fixed;
		}

		.WeChat_header img{
			top: 0;
		}
		.WeChat_footer img{
			bottom: 0;
		}
		.details{
			text-align: center;
		}
		.details p{
			text-indent: 30px;
			line-height: 25px;
			padding: 10px;
		}
		.details img{
			width: 50%;
		}
		.content{
			height: 100%;
			overflow: scroll;
			padding: 18% 10px 200px 10px;
		}
		.content img{
			width: 100%;
		}
		.content_bg{
			width: 80%;
		}
	</style >
	
	<script type="text/javascript">
	$(document).ready(function(){
		var content = window.opener.xheditorContent();
		document.getElementById('panel').innerHTML = content;
	});
	</script>
</head >
<body >
<div class="container-fluid content_bg">
	<div class="row">
		<div class="WeChat_header">
			<img src = "images/header.png" alt = "" >
		</div>
		<div class="content" id="panel">
			
		</div>
		<div class="WeChat_footer">
			<img src = "images/footer.png" alt = "" >
		</div>
	</div>
</div>
</body >
</html >