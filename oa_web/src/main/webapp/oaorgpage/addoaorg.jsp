<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String basePath = request.getContextPath();  
%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addorg</title>
</head>
<script type="text/javascript">
function subMethod(){
	document.getElementById("freshorg").value="fresh";
}
</script>
<body>
<div class="pageContent">
	<form method="post" action="<%=basePath %>/oa/org/addOaOrg" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
	     <input type="hidden"  name="nodeIdType"  value="${datatype}"  />
	     <input type="hidden"  name="dataId"  value="${dataId}"  />
	     <input id="freshorg" type="hidden"  name="freshValue" />
	     <input id="optype" type="hidden"  name="optype"  value="add"/>
		<div class="pageFormContent" layoutH="50">
			<p>
				<label>机构编号：</label>
				<input name="orgNum" type="text" size="30" value="" />
			</p>
			<p>
				<label>机构名称：</label>
				<input name="orgName" class="required" type="text" size="30" value="" alt="请输入机构名称"/>
			</p>
			<p style="float:left; display:block; width:540px; height:21px; margin:0; padding:5px 0; position:relative;">
			  <label>机构地址：</label>
				<select class="combox" name="province" ref="oa_w_combox_city" refUrl="<%=basePath %>/oa/area/city?pvalue={value}">
					<option value="0">所有省市</option>
					<c:forEach items="${plist}"  var="province">
					  <option value="${province.proID}">${province.proName}</option>
					</c:forEach>
				</select>
				<select class="combox" name="city" id="oa_w_combox_city" ref="oa_w_combox_region" refUrl="<%=basePath %>/oa/area/region?cvalue={value}">
					<option value="0">所有城市</option>
				</select>
				<select class="combox" name="region" id="oa_w_combox_region">
					<option value="0">所有区县</option>
				</select>
			</p>
			<p style="float:left; display:block; width:540px; height:21px; margin:0; padding:5px 0; position:relative;">
				<label>详细地址：</label>
				<input name="orgAddress" type="text" size="45"  />
			</p>
			<div class="unit">
			  <dl class="nowrap">
				  <dt>机构备注：</dt>
				  <dd><textarea name="orgRemark" cols="80" rows="2" ></textarea></dd>
		      </dl>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" onclick="subMethod();">提交并刷新</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>
</body>
</html>