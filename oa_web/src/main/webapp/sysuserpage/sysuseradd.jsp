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
<script type="text/javascript">
	var flag = false;
	var msgdiv = document.getElementById("errormsg");
	function verifyUserName(){
		var userName = $("#userName").val();
		if(userName==""){
			 msgdiv.innerHTML="<font  color=\"\"></font>";
		}else{
			$.ajax({
				dataType:"json",
				type:"post",
				url:baseurl +"/oa/sysuser/verifyUserName",
				data:{userName:userName},
				success:function(msg){
					if(msg.result == "true"){
						msgdiv.innerHTML="<font  color=\"green\">* 用户账号唯一可用！</font>";
						flag = false;
					}else{
						msgdiv.innerHTML="<font  color=\"red\">* 用户账号重复！</font>";
						flag = true;
					}
				}	
			});
		}
	}
	function verifySubmit(){
		if(flag==true){
			msgdiv.innerHTML="<font  color=\"red\">* 信息不符合！</font>";
			return false;
		}else{
			return true;
		}
	}
</script>
<body>
<div class="pageContent">
	<form method="post" action="<%=basepath %>/oa/sysuser/addSysUser" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	    <input name="id" value="${userinfo.id }" type="hidden"/>
	    <input name="nodeId" value="${userinfo.nodeId }" type="hidden"/>
	    <input name="parentId" value="${userinfo.paretId }" type="hidden"/>
	    <input name="userPwd" value="${userpassword }" type="hidden"/>
	    <input name="userCreateTime" value="${userinfo.userCreateTime }" type="hidden"/>
	    <input name="token" value="${userinfor.token }" type="hidden"/> 
	    <input name="orgIds" value="${orgIds }" type="hidden"/>
		<div class="pageFormContent nowrap" layoutH="56">
			<dl>
				<dt>用户账号：</dt>
				<dd>
					<c:if test="${edit==true }"><!--编辑不可更改 -->
					<input id="userName" name="userName" type="text" size="30"   value="${userinfo.userName }" readonly="readonly" />
					</c:if>
					<c:if test="${edit==false }"><!--添加必填 -->
					<input id="userName" name="userName" type="text" size="30" class="required"  value="${userinfo.userName }"  onblur="verifyUserName()" alt="请输入登录账号"/>
					</c:if>
				<div id="errormsg" align="center"></div>
				</dd>
			</dl>
		    <dl>
				<dt>用户密码：</dt>
				<dd>
					<c:if test="${edit==true }"> <!--编辑可以不填 -->
						<input id="w_validation_pwd" type="password" name="newUserPwd"  class="alphanumeric" minlength="6" maxlength="20"  />
					</c:if>
					<c:if test="${edit==false }"> <!--添加必填 -->
						<input id="w_validation_pwd" type="password" name="newUserPwd"  class="required alphanumeric" minlength="6" maxlength="20"  />
					</c:if>
				</dd>
		    </dl>
		     <dl>
				<dt>确认密码：</dt>
				<dd>
				<input type="password" name="repassword"   equalto="#w_validation_pwd" alt=""/>
				</dd>
			</dl>
		    <dl>
				<dt>真实姓名：</dt>
				<dd>
				<input name="realName"  type="text" value="${userinfo.realName }" size="30" class="required" alt="请输入用户姓名"/>
				</dd>
		    </dl>
			 <dl>
				<dt>性别：</dt>
				<dd>
				<label><input type="radio" name="userSex" value="1" <c:if test="${userinfo.userSex == 1}">checked</c:if> />女
				       <input type="radio" name="userSex" value="2" <c:if test="${userinfo.userSex == 2 || userinfo.userSex == null }">checked</c:if> />男</label>
				</dd>
			 </dl>
			 <dl>
				<dt>年龄：</dt>
				<input name="userAge" class="digits" value="${userinfo.userAge }"  type="text" size="30" alt="请输入用户年龄" />
		    </dl>
		    <dl>
				<dt>电话号码：</dt>
				<input name="userPhone" class="phone" value="${userinfo.userPhone }"  type="text" size="30" alt="请输入电话号码" />
		    </dl>
		    <dl>
				<dt>邮箱：</dt>
				<dd>
				<input name="userEmail" class="email" value="${userinfo.userEmail }"  type="text" size="30" alt="请输入邮箱地址" />
				</dd>
		    </dl>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" onclick="return verifySubmit()" >保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
</body>
</html>