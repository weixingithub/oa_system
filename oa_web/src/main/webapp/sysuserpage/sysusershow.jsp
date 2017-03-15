<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
        String basepath = request.getContextPath();
 %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="pageContent">
	<form method="post" action="<%=basepath %>/oa/sysuser/addSysUser" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	    <input name="id" value="${userinfo.id }" type="hidden"/>
	    <input name="nodeId" value="${userinfo.nodeId }" type="hidden"/>
	    <input name="parentId" value="${userinfo.paretId }" type="hidden"/>
	    <input name="orgIds" value="${userinfo.orgIds }" type="hidden"/>
		<div class="pageFormContent nowrap" layoutH="56">
			<dl>
				<dt>用户账号：</dt>
				<dd>
					<input id="userName" name="userName" type="text" size="30"   value="${userinfo.userName }" readonly="readonly" />
				</dd>
			</dl>
		    <dl>
				<dt>真实姓名：</dt>
				<dd>
				<input name="realName"  type="text" value="${userinfo.realName }" size="30"  alt="请输入用户姓名"/>
				</dd>
		    </dl>
			 <dl>
				<dt>性别：</dt>
				<dd>
				<label><input type="radio" name="userSex" value="1" readonly="readonly"<c:if test="${userinfo.userSex == 1}">checked</c:if> />女
				       <input type="radio" name="userSex" value="2" readonly="readonly"<c:if test="${userinfo.userSex == 2 || userinfo.userSex == null }">checked</c:if> />男</label>
				</dd>
			 </dl>
			 <dl>
				<dt>年龄：</dt>
				<input name="userAge" class="digits" value="${userinfo.userAge }"  type="text" size="30" alt="请输入用户年龄" readonly="readonly"/>
		    </dl>
		    <dl>
				<dt>电话号码：</dt>
				<input name="userPhone" class="phone" value="${userinfo.userPhone }"  type="text" size="30" alt="请输入电话号码" readonly="readonly"/>
		    </dl>
		    <dl>
				<dt>邮箱：</dt>
				<dd>
				<input name="userEmail" class="email" value="${userinfo.userEmail }"  type="text" size="30" alt="请输入邮箱地址" readonly="readonly" />
				</dd>
		    </dl>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
</body>
</html>