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
	<form method="post" action="<%=basepath %>/oa/vehicle/updateVehicle" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	    <input name="id" value="${vehicle.id }" type="hidden"/>
	    <input name="person_id" value="${vehicle.person_id }" type="hidden"/>
		<div class="pageFormContent nowrap" layoutH="56">
			<dl>
				<dt>车牌号：</dt>
				<dd>
				    <input name="licenseplatenumber" type="text" size="20" class="required" value="${vehicle.licenseplatenumber }"
								  remote="<%=basepath%>/oa/person/verifyLicenseNumber?id=${vehicle.id}&oldlicensenumber=${vehicle.licenseplatenumber}"
								  title="车牌号重复"/>
				<div id="errormsg" align="center"></div>
				</dd>
			</dl>
		    <dl>
				<dt>车辆类型：</dt>
				<dd>
						<input id="w_validation_pwd" type="text" name="vehicletype" value="${vehicle.vehicletype }"    />
				</dd>
		    </dl>
		     <dl>
				<dt>车主：</dt>
				<dd>
				<input type="hidden" name="personId" value="${vehicle.person_id }"/>
				<input id="owners" type="text" name="personName" value="${vehicle.owners }" readonly="readonly" class="button_look" />
				<a class="btnLook" href="<%=basepath%>/oa/person/getFamilyPersonPage" lookupGroup="" >查找带回</a>
				</dd>
			</dl>
			 <dl>
				<dt>车主联系方式：</dt>
				<dd>
				<input name="personContact" type="text" value="${vehicle.person_contact }" readonly="readonly" />
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
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