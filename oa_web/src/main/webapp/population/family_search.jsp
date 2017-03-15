<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String basepath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>户籍管理的高级检索</title>
</head>
<body>
<div class="pageContent">
	<!-- 户籍信息 -->
	<form method="post" action="<%=basepath%>/oa/population/findFamilyPage" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
			<div class="pageFormContent" layoutH="60">
				<p>
					<label>户籍编号：</label> 
					 <input name="householderID" type="text" size="20"/>
				</p>
				<p>
					<label>户主姓名：</label> 
					<input type="text"  name="householderName" />
				</p>
				<p>
					<label>双女户：</label> <select name="doublefemaleHouseholds" class="combox">
						<option value=""  <c:if test="${family.doublefemaleHouseholds ==''}">selected</c:if>>请选择</option>
						<option value="1" <c:if test="${family.doublefemaleHouseholds =='1'}">selected</c:if>>是</option>
						<option value="0" <c:if test="${family.doublefemaleHouseholds =='0'}">selected</c:if>>否</option>
					</select>
				</p>
				<p>
					<label>独生子女家庭：</label> <select name="oneChildFamily" class="combox">
						<option value=""  <c:if test="${family.oneChildFamily ==''}">selected</c:if>>请选择</option>
						<option value="1" <c:if test="${family.oneChildFamily =='1'}">selected</c:if>>是</option>
						<option value="0" <c:if test="${family.oneChildFamily =='0'}">selected</c:if>>否</option>
					</select>
				</p>
				<p>
					<label>优抚对象：</label> <select name="placestate" class="combox">
						<option value=""  <c:if test="${family.placestate ==''}">selected</c:if>>请选择</option>
						<option value="1" <c:if test="${family.placestate =='1'}">selected</c:if>>是</option>
						<option value="0" <c:if test="${family.placestate =='0'}">selected</c:if>>否</option>
					</select>
				</p>
				<p>
					<label>优抚类型：</label> <select name="placetype" class="combox">
						<option value=""  <c:if test="${family.placetype ==''}">selected</c:if>>请选择</option>
						<option value="1" <c:if test="${family.placetype =='1'}">selected</c:if>>军人军烈及家属</option>
						<option value="2" <c:if test="${family.placetype =='2'}">selected</c:if>>五保户</option>
					</select>
				</p>
				<p>
					<label>困难家庭：</label> <select name="poorfamilystate" class="combox">
						<option value=""  <c:if test="${family.poorfamilystate ==''}">selected</c:if>>请选择</option>
						<option value="1" <c:if test="${family.poorfamilystate =='1'}">selected</c:if>>是</option>
						<option value="0" <c:if test="${family.poorfamilystate =='0'}">selected</c:if>>否</option>
					</select>
				</p>
				<p>
					<label>低保户：</label> <select name="lowincomestate" class="combox">
						<option value=""  <c:if test="${family.lowincomestate ==''}">selected</c:if>>请选择</option>
						<option value="1" <c:if test="${family.lowincomestate =='1'}">selected</c:if>>是</option>
						<option value="0" <c:if test="${family.lowincomestate =='0'}">selected</c:if>>否</option>
					</select>
				</p>
				<p>
					<label>低保类型：</label> <select name="lowincometype" class="combox">
						<option value=""  <c:if test="${family.lowincometype ==''}">selected</c:if>>请选择</option>
						<option value="1" <c:if test="${family.lowincometype =='1'}">selected</c:if>>A类</option>
						<option value="2" <c:if test="${family.lowincometype =='2'}">selected</c:if>>B类</option>
						<option value="3" <c:if test="${family.lowincometype =='3'}">selected</c:if>>C类</option>
					</select>
				</p>
				<p>
					<label>单亲家庭：</label> <select name="singleparent" class="combox">
						<option value=""  <c:if test="${family.singleparent ==''}">selected</c:if>>请选择</option>
						<option value="1" <c:if test="${family.singleparent =='1'}">selected</c:if>>是</option>
						<option value="0" <c:if test="${family.singleparent =='0'}">selected</c:if>>否</option>
					</select>
				</p>
				
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