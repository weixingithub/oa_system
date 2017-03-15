<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
        String basepath = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="pageContent">
		<form method="post" action="<%=basepath %>/oa/labour/getPersonPage" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
		 <input type="hidden" name="laborInsuranceId" value="${person.laborInsurance.id }">
			<div class="pageFormContent" layoutH="60" >
	   		  <p>
				<label>养老保险：</label>
				<select name=endowmentinsurance class="combox" id="endowmentinsurance" onchange="checklabor()" >
					<option value="">请选择</option>
					<option value="1" ${person.laborInsurance.endowmentinsurance == 1 ? 'selected = "selected"' : '' } >参保</option>
					<option value="0" ${person.laborInsurance.endowmentinsurance == 0 ? 'selected = "selected"' : '' }>未参保</option>
				</select>
			 </p>
			  <p>
				<label>养老保险类型：</label>
				<select name=pensiontype class="combox" id="pensiontype">
					<option value="">请选择</option>
					<option value="1" ${person.laborInsurance.pensiontype == 1 ? 'selected = "selected"' : '' } >职工养老保险</option>
					<option value="2" ${person.laborInsurance.pensiontype == 2 ? 'selected = "selected"' : '' }>城镇医疗保险</option>
					<option value="3" ${person.laborInsurance.pensiontype == 3 ? 'selected = "selected"' : '' }>商业保险</option>
					<option value="4" ${person.laborInsurance.pensiontype == 4 ? 'selected = "selected"' : '' }>失地农民养老保险</option>
				</select>
			</p>
			  <p>
				<label>享受养老金返还：</label>
				<select name=pensionreturn class="combox" >
					<option value="">请选择</option>
					<option value="1" ${person.laborInsurance.pensionreturn == 1 ? 'selected = "selected"' : '' } >是</option>
					<option value="0" ${person.laborInsurance.pensionreturn == 0 ? 'selected = "selected"' : '' }>否</option>
				</select>
			</p>	
			<p>
				<label>医疗保险：</label>
				<select name=medicalinsurance id="medicalinsurance" class="combox" onchange="checklabor()">
					<option value="">请选择</option>
					<option value="1" ${person.laborInsurance.medicalinsurance == 1 ? 'selected = "selected"' : '' } >参保</option>
					<option value="0" ${person.laborInsurance.medicalinsurance == 0 ? 'selected = "selected"' : '' }>未参保</option>
				</select>
			</p>
			<p>
				<label>医疗保险类型：</label>
				<select name=medicaltype class="combox" id="medicaltype" >
					<option value="">请选择</option>
					<option value="1" ${person.laborInsurance.medicaltype == 1 ? 'selected = "selected"' : '' } >职工医疗保险</option>
					<option value="2" ${person.laborInsurance.medicaltype == 2 ? 'selected = "selected"' : '' }>城镇居民医疗保险</option>
					<option value="3" ${person.laborInsurance.medicaltype == 3 ? 'selected = "selected"' : '' }>新农合</option>
					<option value="4" ${person.laborInsurance.medicaltype == 4 ? 'selected = "selected"' : '' }>商业保险</option>
				</select>
			</p>
		    <p>
				<label>享受国家低保：</label>
				<select name=nationallowances class="combox" >
					<option value="">请选择</option>
					<option value="1" ${person.laborInsurance.nationallowances == 1 ? 'selected = "selected"' : '' } >是</option>
					<option value="0" ${person.laborInsurance.nationallowances == 0 ? 'selected = "selected"' : '' }>否</option>
				</select>
			</p>
			<p>
				<label>享受社保补贴：</label>
				<select name=socialsubsidies class="combox" >
					<option value="">请选择</option>
					<option value="1" ${person.laborInsurance.socialsubsidies == 1 ? 'selected = "selected"' : '' } >是</option>
					<option value="0" ${person.laborInsurance.socialsubsidies == 0 ? 'selected = "selected"' : '' }>否</option>
				</select>
			</p>
			<p>
				<label>领取养老金：</label>
				<select name=retirepension class="combox" >
					<option value="">请选择</option>
					<option value="1" ${person.laborInsurance.retirepension == 1 ? 'selected = "selected"' : '' } >是</option>
					<option value="0" ${person.laborInsurance.retirepension == 0 ? 'selected = "selected"' : '' }>否</option>
				</select>
			</p>
			 <p>
				<label>就业状态：</label>
				<select name=employmentstatus class="combox" onchange="checklabor()" id="employmentstatus">
					<option value="">请选择</option>
					<option value="1" ${person.laborInsurance.employmentstatus == 1 ? 'selected = "selected"' : '' } >已就业</option>
					<option value="2" ${person.laborInsurance.employmentstatus == 2 ? 'selected = "selected"' : '' }>未就业</option>
					<option value="3" ${person.laborInsurance.employmentstatus == 3 ? 'selected = "selected"' : '' }>再就业</option>
				</select>
			</p>
			 <p>
				<label>就业性质：</label>
				<select name=employmentnature class="combox" id="employmentnature" >
					<option value="">请选择</option>
					<option value="1" ${person.laborInsurance.employmentnature == 1 ? 'selected = "selected"' : '' } >企事业单位</option>
					<option value="2" ${person.laborInsurance.employmentnature == 2 ? 'selected = "selected"' : '' }>私营企业</option>
					<option value="3" ${person.laborInsurance.employmentnature == 3 ? 'selected = "selected"' : '' }>灵活就业</option>
					<option value="4" ${person.laborInsurance.employmentnature == 4 ? 'selected = "selected"' : '' }>公益岗位</option>
					<option value="5" ${person.laborInsurance.employmentnature == 5 ? 'selected = "selected"' : '' }>退休</option>
				</select>
			</p>
			 <p>
				<label>求职意向：</label>
				<select name=jobintension class="combox" id="jobintension">
					<option value="">请选择</option>
					<option value="1" ${person.laborInsurance.jobintension == 1 ? 'selected = "selected"' : '' }>自主创业</option>
					<option value="2" ${person.laborInsurance.jobintension == 2 ? 'selected = "selected"' : '' }>灵活就业</option>
					<option value="3" ${person.laborInsurance.jobintension == 3 ? 'selected = "selected"' : '' }>企事业单位</option>
					<option value="4" ${person.laborInsurance.jobintension == 4 ? 'selected = "selected"' : '' }>暂不考虑就业</option>
					<option value="5" ${person.laborInsurance.jobintension == 5 ? 'selected = "selected"' : '' }>其它</option>
				</select>
			</p>
			 <p>
				<label>办理就业失业登记：</label>
				<select name=unemployregistration class="combox" id="unemployregistration" onchange="checklabor()">
					<option value="">请选择</option>
					<option value="1" ${person.laborInsurance.unemployregistration == 1 ? 'selected = "selected"' : '' } >是</option>
					<option value="0" ${person.laborInsurance.unemployregistration == 0 ? 'selected = "selected"' : '' }>否</option>
				</select>
			</p>
			 <p>
				<label>私营企业小额贷款：</label>
				<select name=enjoypettyloan class="combox" >
					<option value="">请选择</option>
					<option value="1" ${person.laborInsurance.enjoypettyloan == 1 ? 'selected = "selected"' : '' } >是</option>
					<option value="0" ${person.laborInsurance.enjoypettyloan == 0 ? 'selected = "selected"' : '' }>否</option>
				</select>
			</p>
			</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">搜索</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>	
</body>
</html>