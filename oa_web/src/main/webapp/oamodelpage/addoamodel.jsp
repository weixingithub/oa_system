<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String basePath = request.getContextPath();  
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addorg</title>
</head>
<body>
<div class="pageContent">
	<form method="post" action="<%=basePath %>/oa/model/addOaModel" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
	     <input type="hidden"  name="nodeIdType"  value="${datatype}"  />
	     <input type="hidden"  name="dataId"  value="${dataId}"  />
	     <input id="optype" type="hidden"  name="optype"  value="add"/>
		<div class="pageFormContent" layoutH="50">
			    <div class="unit">
				<label>模块名称：</label>
				  <input name="modelName" class="required" type="text" size="30" alt="请输入模块名称"/>
				</div>
				
				
				
			 <c:if test="${userflag == 1 }">
			   <div  id="modelurldiv">
				  <div class="unit">
					  <label>模块链接：</label>
					  <input name="modelUrl"  id="modelurl"  type="text" size="60"  />
				 </div>
				 <div class="unit">
					  <label>前台链接：</label>
					  <input name="stageUrl"  id="stageUrl"  type="text" size="60"  />
			     </div>
		       </div>
			  <div class="unit" id="isAutoselectdiv">
			  <label>模块自动化：</label>
				<select class="combox"  onchange="AutoChose();" id="autoselect" name="isAuto">
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
			 </div>
			  <div class="unit" id="pAutoselectdiv" style="display:none;">
			  <label>信息自动化：</label>
				<select class="combox" id="pAuto" onchange="changeCheck();"   name="pAuto">
					<option value="false">否</option>
					<option value="true">是</option>
				</select>
			 </div>
			 <div class="unit" id="autocheckdiv" style="display:none;">
			  <label>是否审核：</label>
				<select class="combox" id="pAutoCheck" onchange="autoChange();"   name="autoIsCheckModel">
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
			</div>
			</c:if>
			
			
			
			
			 <c:if test="${userflag == 0 }">
		      <input name="isAuto" type="hidden" value="1" />
		       <div class="unit" id="pAutoselectdiv">
				  <label>信息自动化：</label>
					<select class="combox" id="pAuto" onchange="changeCheck();"   name="pAuto">
						<option value="false">否</option>
						<option value="true">是</option>
					</select>
				 </div>
				 <div class="unit" id="autocheckdiv" style="display:none;">
				  <label>是否审核：</label>
					<select class="combox" id="pAutoCheck" onchange="autoChange();"   name="autoIsCheckModel">
						<option value="0">否</option>
						<option value="1">是</option>
					</select>
				</div>
			 </c:if>
			 
			
			 <div  class="unit"  id="checkRoleIddiv" style="display:none;">
			  <label>审核角色：</label>
				<input type="hidden" name="checkRoleId" id="checkRoleId" value="" />
			    <input type="text"  id="checkRoleName"  name="parentName" maxlength="20" value="" /> 
			    <a class="btnLook" href="<%=basePath %>/oa/role/getIntoRole?id=${oaRole.roleId }&keyName=checkRoleId" lookupGroup="" width="300" height="500">权限树</a>
			  </div>
			 
			 
			 
			  <c:if test="${userflag == 1 }">
			   <div  class="unit">
			  <label>frame跳转：</label>
				<select class="combox" name="modelIframe">
					<option value="false">否</option>
					<option value="true">是</option>
				</select>
			  </div>
			  <div  class="unit" id="developcheckdiv">
			  <label>审核模块：</label>
				<select class="combox" name="isCheckModel">
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
			  </div>
			  </c:if>
			  
			  
			  
			  <div class="unit">
			  <dl class="nowrap">
				  <dt>模块备注：</dt>
				  <dd><textarea name="remark" cols="80" rows="2" ></textarea></dd>
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