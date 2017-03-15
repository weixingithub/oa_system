<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>oa系统</title>
<style type = "text/css" >
.code {
	font-family: Arial;
	font-style: italic;
	color: Red;
	border: 0;
	padding: 2px 3px;
	letter-spacing: 3px;
	font-weight: bolder;
}

.unchanged {
	border: 0;
}
</style >
<link href="<%=request.getContextPath() %>/themes/css/login.css" rel="stylesheet" type="text/css" />
<script src="<%=request.getContextPath() %>/js/jquery-1.4.4.min.js" type="text/javascript"></script>
</head>

<script language = "javascript" type = "text/javascript" >
var code; //在全局 定义验证码
function createCode() {
	code = "";
	var codeLength = 4;//验证码的长度
	var checkCode = document.getElementById("checkCode");
	var selectChar = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9];//所有候选组成验证码的字符，当然也可以用中文的

	for (var i = 0; i < codeLength; i++) {
		var charIndex = Math.floor(Math.random() * 10);
		code += selectChar[charIndex];
	}
	//alert(code);
	if (checkCode) {
		checkCode.className = "code";
		checkCode.value = code;
	}
}

//进行验证
function validate() {
	var username = $("#username").val();
	var password = $("#password").val();
	var inputCode =$("#input1").val();  //获取验证值
	var msgdiv = document.getElementById("errormsg");
	
	if(username == "" || password == ""){
		msgdiv.innerHTML="<font  color=\"red\">* 请填写用户名和密码</font>";
		return false;
	}
	
	if (inputCode.length <= 0) {
		msgdiv.innerHTML="<font  color=\"red\">* 请填写验证码</font>";
	}
	else if (inputCode != code) {
		msgdiv.innerHTML="<font  color=\"red\">* 验证码错误</font>";
		createCode();//刷新验证码
	}
	else {
		//alert("^-^ OK");
		 checkInfo();
	}
}

function checkInfo(){
	var username = $("#username").val();
	var password = $("#password").val();
	var baseurl= '<%=request.getContextPath() %>';
	var msgdiv = document.getElementById("errormsg");
	
	$.ajax({
		dataType:"json",
		type:"post",
		url:baseurl +"/login/validation",
		data:$("#lgform").serialize(),
		success:function(msg){
			if(msg.result == "true"){
				window.location.href=baseurl+"/oa/sysuser/main";
			}else{
				msgdiv.innerHTML="<font  color=\"red\">"+msg.result+"</font>";
			}
		}	
	});
}

//鼠标事件
$(function(){
	document.onkeydown = function(e){
	    var ev = document.all ? window.event : e;
	    if(ev.keyCode==13) {

	           $('#Button1').click();

	     }
	}
}); 

</script >
<body onload = "createCode()">
<div id="login">
		<div id="login_content">
			<div class="loginForm">
				<div class="input_content"> 
					<form id="lgform" action="<%=request.getContextPath() %>/login/validation" method="post">
						<div id="errormsg" align="center" style="height: 8px"></div>
						<p>
							<label>用户名：</label>
							<input type="text" id="username" name="userName" class="login_input" />
						</p>
						<p>
							<label>密码：</label>
							<input type="password" id="password" name="userPwd" class="login_input" />
						</p>
						<p>
							<label>验证码：</label>
							<input class="code" id="input1" name="verifycode" type="text" />
							<input type = "text" onclick = "createCode()" readonly = "readonly" id = "checkCode" style = "width: 72px" />
						</p>
						<div class="login_bar">
							<input id="Button1" class="sub" type="button"  value="登&nbsp; 录" onclick = "validate();" />
						</div>
					</form>
				</div>
			</div>
		</div>
		<div id="login_footer">
			oa信息管理平台
		</div>
	</div>
</body>
</html>