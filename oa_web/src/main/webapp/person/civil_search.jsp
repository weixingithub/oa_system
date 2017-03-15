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
<head><!--       <input type="hidden" name="populationCiviId" value="${person.populationCivil.id }"> -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>民政管理检索</title>
</head>
<body>
		<div class="pageContent">
		<form method="post" action="<%=basepath %>/oa/civil/getPersonPage" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
		 <input type="hidden" name="laborInsuranceId" value="${person.laborInsurance.id }">
			<div class="pageFormContent" layoutH="60" >
   		 	 	<p>
					<label>残疾：</label>
					<select name="disabled" class="combox" >
						<option value="">请选择</option>
						<option value="1" ${person.populationCivil.disabled == 1 ? 'selected = "selected"' : '' } >是</option>
						<option value="0" ${person.populationCivil.disabled == 0 ? 'selected = "selected"' : '' }>否</option>
					</select>
				</p>
				 <p>
					<label>残疾级别：</label>
					<select name="disablitygrade" class="combox" >
						<option value="">请选择</option>
						<option value="1" ${person.populationCivil.disablitygrade == 1 ? 'selected = "selected"' : '' } >一级</option>
						<option value="2" ${person.populationCivil.disablitygrade == 2 ? 'selected = "selected"' : '' }>二级</option>
						<option value="3" ${person.populationCivil.disablitygrade == 3 ? 'selected = "selected"' : '' }>三级</option>
						<option value="4" ${person.populationCivil.disablitygrade == 4 ? 'selected = "selected"' : '' }>四级</option>
						<option value="5" ${person.populationCivil.disablitygrade == 5 ? 'selected = "selected"' : '' }>五级</option>
						<option value="6" ${person.populationCivil.disablitygrade == 6 ? 'selected = "selected"' : '' }>六级</option>
						<option value="7" ${person.populationCivil.disablitygrade == 7 ? 'selected = "selected"' : '' }>七级</option>
						<option value="8" ${person.populationCivil.disablitygrade == 8 ? 'selected = "selected"' : '' }>八级</option>
						<option value="9" ${person.populationCivil.disablitygrade == 9 ? 'selected = "selected"' : '' }>九级</option>
						<option value="10" ${person.populationCivil.disablitygrade == 10 ? 'selected = "selected"' : '' }>十级</option>
					</select>
				</p>
				 <p>
					<label>孤残儿：</label>
					<select name="orphanschildren" class="combox" >
						<option value="">请选择</option>
						<option value="1" ${person.populationCivil.orphanschildren == 1 ? 'selected = "selected"' : '' } >是</option>
						<option value="0" ${person.populationCivil.orphanschildren == 0 ? 'selected = "selected"' : '' }>否</option>
					</select>
				</p>
				 <p>
					<label>领取孤儿补助：</label>
					<select name="receivegrantsorphan" id="receivegrantsorphan" class="combox" onchange="checkpopulationcivil()" >
						<option value="">请选择</option>
						<option value="1"  ${person.populationCivil.receivegrantsorphan == 1 ? 'selected = "selected"' : ''} >是</option>
						<option value="0" ${person.populationCivil.receivegrantsorphan == 0 ? 'selected = "selected"' : '' }>否</option>
					</select>
				</p>
				 <p>
					<label>享受残疾人津贴：</label>
					<select name="disabilitybenefits" id="disabilitybenefits" class="combox" onchange="checkpopulationcivil()">
						<option value="">请选择</option>
						<option value="1" ${person.populationCivil.disabilitybenefits == 1 ? 'selected = "selected"' : '' } >是</option>
						<option value="0" ${person.populationCivil.disabilitybenefits == 0 ? 'selected = "selected"' : '' }>否</option>
					</select>
				</p>
				 <p>
					<label>现役军人：</label>
					<select name="activearmy" class="combox" >
						<option value="">请选择</option>
						<option value="1" ${person.populationCivil.activearmy == 1 ? 'selected = "selected"' : '' } >是</option>
						<option value="0" ${person.populationCivil.activearmy == 0 ? 'selected = "selected"' : '' }>否</option>
					</select>
				</p>
				 <p>
					<label>孤寡老人：</label>
					<select name="elderly" class="combox" >
						<option value="">请选择</option>
						<option value="1" ${person.populationCivil.elderly == 1 ? 'selected = "selected"' : '' } >是</option>
						<option value="0" ${person.populationCivil.elderly == 0 ? 'selected = "selected"' : '' }>否</option>
					</select>
				</p>
				 <p>
					<label>领取高龄津贴：</label>
					<select name="subsidies" id="subsidies" class="combox" onchange="checkpopulationcivil()">
						<option value="">请选择</option>
						<option value="1" ${person.populationCivil.subsidies == 1 ? 'selected = "selected"' : '' } >是</option>
						<option value="0" ${person.populationCivil.subsidies == 0 ? 'selected = "selected"' : '' }>否</option>
					</select>
				</p>
				 <p>
					<label>退伍军人：</label>
					<select name="veteran" class="combox" >
						<option value="">请选择</option>
						<option value="1" ${person.populationCivil.veteran == 1 ? 'selected = "selected"' : '' } >是</option>
						<option value="0" ${person.populationCivil.veteran == 0 ? 'selected = "selected"' : '' }>否</option>
					</select>
				</p>
				 <p>
					<label>空巢老人：</label>
					<select name="emptynester" class="combox" >
						<option value="">请选择</option>
						<option value="1" ${person.populationCivil.emptynester == 1 ? 'selected = "selected"' : '' } >是</option>
						<option value="0" ${person.populationCivil.emptynester == 0 ? 'selected = "selected"' : '' }>否</option>
					</select>
				</p>
				 <p>
					<label>老红军：</label>
					<select name="oldarmy" class="combox" >
						<option value="">请选择</option>
						<option value="1" ${person.populationCivil.oldarmy == 1 ? 'selected = "selected"' : '' } >是</option>
						<option value="0" ${person.populationCivil.oldarmy == 0 ? 'selected = "selected"' : '' }>否</option>
					</select>
				</p>
				 <p>
					<label>复转干部：</label>
					<select name="recadres" class="combox" >
						<option value="">请选择</option>
						<option value="1" ${person.populationCivil.recadres == 1 ? 'selected = "selected"' : '' } >是</option>
						<option value="0" ${person.populationCivil.recadres == 0 ? 'selected = "selected"' : '' }>否</option>
					</select>
				</p>
				 <p>
					<label>劳模：</label>
					<select name="modelworkers" class="combox" >
						<option value="">请选择</option>
						<option value="1" ${person.populationCivil.modelworkers == 1 ? 'selected = "selected"' : '' } >是</option>
						<option value="0" ${person.populationCivil.modelworkers == 0 ? 'selected = "selected"' : '' }>否</option>
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