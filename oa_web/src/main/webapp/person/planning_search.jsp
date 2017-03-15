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
		<form method="post" action="<%=basepath %>/oa/planning/getPersonPage" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
		      <input type="hidden" name="familyPlanningId" value="${person.familyPlanning.id }">
			<div class="pageFormContent" layoutH="60" >
		   		 <p>
					<label>婚育情况：</label>
					<select name="marriagestatus" class="combox" >
						<option value="">请选择</option>
						<option value="1" ${person.familyPlanning.marriagestatus == 1 ? 'selected = "selected"' : '' } >未婚</option>
						<option value="2" ${person.familyPlanning.marriagestatus == 0 ? 'selected = "selected"' : '' }>初婚</option>
						<option value="3" ${person.familyPlanning.marriagestatus == 0 ? 'selected = "selected"' : '' }>离婚</option>
						<option value="4" ${person.familyPlanning.marriagestatus == 0 ? 'selected = "selected"' : '' }>再婚</option>
						<option value="5" ${person.familyPlanning.marriagestatus == 0 ? 'selected = "selected"' : '' }>三婚</option>
					</select>
				</p> 
				<p>
					<label>节育措施：</label>
					<select name="contraceptive" class="combox" >
						<option value="">请选择</option>
						<option value="1" ${person.familyPlanning.contraceptive == 1 ? 'selected = "selected"' : '' } >上环</option>
						<option value="2" ${person.familyPlanning.contraceptive == 0 ? 'selected = "selected"' : '' }>药具</option>
						<option value="3" ${person.familyPlanning.contraceptive == 0 ? 'selected = "selected"' : '' }>结扎</option>
						<option value="4" ${person.familyPlanning.contraceptive == 0 ? 'selected = "selected"' : '' }>皮埋</option>
						<option value="5" ${person.familyPlanning.contraceptive == 0 ? 'selected = "selected"' : '' }>其它</option>
					</select>
				</p> 
				<p>
					<label>领取独生子女证：</label>
					<select name="onlychildstatus" class="combox" >
						<option value="">请选择</option>
						<option value="1" ${person.familyPlanning.onlychildstatus == 1 ? 'selected = "selected"' : '' } >是</option>
						<option value="0" ${person.familyPlanning.onlychildstatus == 0 ? 'selected = "selected"' : '' }>否</option>
					</select>
				</p>
				<p>
					<label>二胎指标：</label>
					<select name="twotires" class="combox" >
						<option value="">请选择</option>
						<option value="1" ${person.familyPlanning.twotires == 1 ? 'selected = "selected"' : '' } >是</option>
						<option value="0" ${person.familyPlanning.twotires == 0 ? 'selected = "selected"' : '' }>否</option>
					</select>
				</p>
				<p>
					<label>领取独生子女父母补助金：</label>
					<select name="childgrants" class="combox" >
						<option value="">请选择</option>
						<option value="1" ${person.familyPlanning.childgrants == 1 ? 'selected = "selected"' : '' } >是</option>
						<option value="0" ${person.familyPlanning.childgrants == 0 ? 'selected = "selected"' : '' }>否</option>
					</select>
				</p>
				<p>
					<label>办理生育登记：</label>
					<select name="birthregistration" class="combox" >
						<option value="">请选择</option>
						<option value="1" ${person.familyPlanning.birthregistration == 1 ? 'selected = "selected"' : '' } >是</option>
						<option value="0" ${person.familyPlanning.birthregistration == 0 ? 'selected = "selected"' : '' }>否</option>
					</select>
				</p>
				<p>
					<label>办理三查：</label>
					<select name="richardIII" class="combox" >
						<option value="">请选择</option>
						<option value="1" ${person.familyPlanning.richardIII == 1 ? 'selected = "selected"' : '' } >是</option>
						<option value="0" ${person.familyPlanning.richardIII == 0 ? 'selected = "selected"' : '' }>否</option>
					</select>
				</p>
				<p>
					<label>办理流动人口婚育证：</label>
					<select name="obstetricalcard" class="combox" >
						<option value="">请选择</option>
						<option value="1" ${person.familyPlanning.obstetricalcard == 1 ? 'selected = "selected"' : '' } >是</option>
						<option value="0" ${person.familyPlanning.obstetricalcard == 0 ? 'selected = "selected"' : '' }>否</option>
					</select>
				</p>
				<p>
					<label>领取保健费：</label>
					<select name=subsidiesstate class="combox" >
						<option value="">请选择</option>
						<option value="1" ${person.familyPlanning.subsidiesstate == 1 ? 'selected = "selected"' : '' } >是</option>
						<option value="0" ${person.familyPlanning.subsidiesstate == 0 ? 'selected = "selected"' : '' }>否</option>
					</select>
				</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">搜索</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
   </div>
</body>
</html>