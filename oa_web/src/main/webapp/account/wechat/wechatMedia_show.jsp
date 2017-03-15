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
<title>微博</title>
</head>
<body>
<div class="pageContent" style="padding:5px">
	<div class="tabs">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>微博信息</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<!-- 微博基本信息 -->
			<div>
				<div class="pageFormContent" layoutH="120">
					<fieldset>
						<p>
							<label>来源名称:</label> 
							${status.source.name }
						</p>
						<p>
							<label>来源链接:</label> 
							${status.source.url }
						</p>
						<p>
							<label>转发数:</label> 
							${status.repostsCount }
						</p><p>
							<label>评论数:</label> 
							${status.commentsCount }
						</p><p>
							<label>点赞数:</label> 
							${status.attitudesCount }
						</p>
						<img alt="" src="${status.originalPic }" width="300" height="150"/>
					</fieldset>
					<fieldset>
						<legend>正文:</legend>
						<textarea name="text"cols="80" rows="6" >${status.text }</textarea>
					</fieldset>
				</div>
				<div class="formBar">
					<ul>
						<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>
</body>
</html>