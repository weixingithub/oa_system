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
					<li><a href="javascript:;"><span>微博基本信息</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<!-- 微博基本信息 -->
			<div>
				<form method="post" action="<%=basePath%>/oa/weibo/addOrUpdateWeibo"
					class="pageForm required-validate"
					onsubmit="return validateCallback(this, dialogAjaxDone)">
						<input type="hidden" name="id" value="${weibo.id}" />
						<input type="hidden" name="accessTokenId" value="${weibo.weiboToken.id}" />
					<div class="pageFormContent" layoutH="120">
						<dl>
							<dt>应用名称：</dt>
							<dd><input class="required" name="appName" type="text" size="35" value="${weibo.appName}" /></dd>
						</dl>
						<dl>
							<dt>微博账号：</dt>
							<dd><input class="required" name="name" type="text" size="35" value="${weibo.name}" /></dd>
						</dl>
						<dl>
							<dt>微博密码：</dt>
							<dd><input class="required" name="password" type="text" size="35" value="${weibo.password}" /></dd>
						</dl>
						<dl>
							<dt>应用AppKey：</dt>
							<dd><input class="required" name="clientId" type="text" size="35" value="${weibo.clientId}" /></dd>
						</dl>
						<dl>
							<dt>应用AppSecret：</dt>
							<dd><input class="required" name="clientSercret" type="text" size="35" value="${weibo.clientSercret}" /></dd>
						</dl>
						<dl>
							<dt>回调地址：</dt>
							<dd><input class="required" name="redirectUrl" type="text" size="35"  value="${weibo.redirectUrl}" /></dd>
						</dl>
						<dl>
							<dt>所属部门：</dt>
							<dd><input name="orgId" value="${weibo.orgId }" type="hidden"/>
								<input name="orgPid" value="${weibo.orgPid }" type="hidden"/>
								<input class="required" name="orgName" type="text" value="${weibo.orgName}" readonly/>
								<a  class="btnLook" href="<%=basePath %>/oaorgpage/orgtree.jsp" lookupGroup="">选择部门</a>	</dd>
						</dl>
					</div>
					<div class="formBar">
						<ul>
							<li><div class="buttonActive"><div class="buttonContent"><button type="submit" >提交</button></div></div></li>
							<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
						</ul>
					</div>
				</form>
			</div>						
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>
</body>
</html>