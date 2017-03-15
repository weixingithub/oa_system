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
<title>民政信息管理修改</title>
</head>
<script type="text/javascript">
$(document).ready(function(){
    checkpopulationcivil();
    checklabor();
    checkstability();
});
	/**
	 * 人口民政信息
	 * 控制input是否可编辑
	 */
    function checkpopulationcivil() {
		 var receivegrantsorphan= $('#receivegrantsorphan').val();//是否领取孤儿补助
		 var civil=document.getElementById("civil");
		 if(receivegrantsorphan=='0') {
			 civil.style.display="none";
		 }else if(receivegrantsorphan=='1'){
			 civil.style.display="";
		 }
		 var disabilitybenefits= $('#disabilitybenefits').val();//是否享受残疾人津贴
		 var civilone=document.getElementById("civilone");
		 if(disabilitybenefits=='0') {
			 civilone.style.display="none";
		 }else if(disabilitybenefits=='1'){
			 civilone.style.display="";
		 }
		 var subsidies= $('#subsidies').val();//是否享受高龄津贴
		 var civiltwo=document.getElementById("civiltwo");
		 if(subsidies=='0') {
			 civiltwo.style.display="none";
		 }else{
			 civiltwo.style.display="";
		 }
	 } 
  
</script>
<style type="text/css">
	ul.rightTools {float:right; display:block;}
	ul.rightTools li{float:left; display:block; margin-left:5px}
</style>
<body>
<div class="pageContent" style="padding:5px">
	<div class="divider"></div>
	<div class="tabs">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>人口信息</span></a></li>
					<c:if test="${not empty person.id }">
					<li><a href="javascript:;"><span>民政信息</span></a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
		<!-- 人口信息 -->
		 <div>
		  <form method="post" action="<%=basepath %>/oa/civil/personAddOrUpdate" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	       <input type="hidden" name="id" value="${person.id }">
	       <input type="hidden" name="orgId" value="${person.orgId }">
	       <input type="hidden" name="userId" value="${person.userId }">
	       <input type="hidden" name="creatime" value="${person.creatime }">
	       <input type="hidden" name="populationCiviId" value="${person.populationCivil.id }">
	        <input name="perPCD" value="${persons.perPCD }" type="hidden"/>
	       <div class="pageFormContent" layoutH="120" >
					<p>
						<label>姓名：</label>
						<input name="name" type="text" size="30" value="${person.name }" class="required" />
					</p>
					<p>
						<label>曾用名：</label>
						<input name="oldname" type="text" size="30" value="${person.oldname }"  />
					</p>
					<p>
						<label>性别：</label>
					    <input type="radio" name="sex" value="0" checked="checked" ${person.sex == 0 ? 'checked = "checked"' : '' }>男
					    <input type="radio" name="sex" value="1"  ${person.sex == 1 ? 'checked = "checked"' : '' }>女
					</p>
					<p>
						<label>身份证号：</label>
					     <input name="idnumber" type="text" size="20" class="required" value="${person.idnumber}" 
								  remote="<%=basepath%>/oa/civil/verifyIdnumber?orgId=${person.orgId}&id=${person.id}&oldidnumber=${person.idnumber}"
								  title="身份证号重复"/>
					</p>
					<p>
						<label>出生日期：</label>
						<input name="birthdate" class="date" type="text" size="30" value="${person.birthdate }" /><a class="inputDateButton" href="javascript:;">选择</a>
					</p>
					<p>
						<label>民族：</label>
						<select name="nation" class="combox">
							<option value="">请选择</option>
							<option value="1" ${person.nation == 1 ? 'selected = "selected"' : '' }>汉族</option>
							<option value="2" ${person.nation == 2 ? 'selected = "selected"' : '' }>少数民族</option>
						</select> 
					</p>
					<p>
						<label>血型 ：</label>
						<select name="bloodtype" class="combox" value= "${person.bloodtype }">
							<option value="">请选择</option>
							<option value="1"  ${person.bloodtype == 1 ? 'selected = "selected"' : '' }>A型</option>
							<option value="2"  ${person.bloodtype == 2 ? 'selected = "selected"' : '' }>B型</option>
							<option value="3"  ${person.bloodtype == 3 ? 'selected = "selected"' : '' }>AB型</option>
							<option value="4"  ${person.bloodtype == 4 ? 'selected = "selected"' : '' }>O型</option>
							<option value="5"  ${person.bloodtype == 5 ? 'selected = "selected"' : '' }>其它</option>
						</select> 
					</p>
					<p>
						<label>身高：</label>
						<input name="tall"  type="text" size="30" value="${person.tall }" alt="请输入身高"/>
					</p>
					<p>
						<label>嫁城女：</label>
						<select name="citywoman" class="combox">
							<option value="">请选择</option>
							<option value="1" ${person.citywoman == 1 ? 'selected = "selected"' : '' }>是</option>
							<option value="2" ${person.citywoman == 2 ? 'selected = "selected"' : '' }>否</option>
						</select> 
					</p>
					
					<p>
						<label>户口性质：</label>
						<select name="residenttype" class="combox">
							<option value="">请选择</option>
							<option value="1" ${person.residenttype == 1 ? 'selected = "selected"' : '' }>农户</option>
							<option value="2" ${person.residenttype == 2 ? 'selected = "selected"' : '' }>非农户</option>
						</select> 
					</p>
					<p>
						<label>政治面貌：</label>
						<select name="political" class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.political == 1 ? 'selected = "selected"' : '' }>党员</option>
							<option value="2" ${person.political == 2 ? 'selected = "selected"' : '' }>团员</option>
							<option value="3" ${person.political == 3 ? 'selected = "selected"' : '' }>群众</option>
							<option value="4" ${person.political == 4 ? 'selected = "selected"' : '' }>其它</option>
						</select>
					</p>
					<p style="width:600px">
						<label>户籍地：</label>
						<select class="combox" name="province" ref="oa_w_combox_city" ref="oa_w_combox_region" refUrl="<%=basepath%>/oa/area/city?pvalue={value}">
								<option value="0">所有省市</option>
							<c:forEach items="${plist}"  var="province">
								<option  <c:if test="${province.proID == proid}">selected</c:if> value="${province.proID }">${province.proName }</option>
							</c:forEach>
						</select>
						<select class="combox" name="city" id="oa_w_combox_city" ref="oa_w_combox_region" refUrl="<%=basepath%>/oa/area/region?cvalue={value}">
							<option value="0">所有城市</option>
							<c:if test="${!empty cityid}">
							<c:forEach items="${clist }"  var="city">
								<option  <c:if test="${city.cityID == cityid}">selected</c:if> value="${city.cityID }">${city.cityName }</option>
							</c:forEach>
							</c:if>
						</select>
						<select class="combox" name="region" id="oa_w_combox_region">
							<option value="0">所有区县</option>
							<c:if test="${!empty disid}">
							<c:forEach items="${dlist }"  var="district">
								 <option  <c:if test="${district.id == disid}">selected</c:if> value="${district.id }">${district.disName }</option>
							</c:forEach>
							</c:if>
						</select>	
					</p>
					<p>
						<label>户籍详细地址：</label>
						<input name="registry_place"  type="text" size="30" value="${person.registry_place }" alt="请输入户籍详细地址"/>
					</p>
					<p>
						<label>辖区内：</label>
						<select name="jurisdiction" class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.jurisdiction == 1 ? 'selected = "selected"' : '' }>是</option>
							<option value="0" ${person.jurisdiction == 0 ? 'selected = "selected"' : '' }>否</option>
						</select>
					</p>
					<p>
						<label>人口一致标识：</label>
						<select name="uniformIdentification" class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.uniformIdentification == 1 ? 'selected = "selected"' : '' }>是</option>
							<option value="0" ${person.uniformIdentification == 0 ? 'selected = "selected"' : '' }>否</option>
						</select>
					</p>
					<p>
						<label>现居详细地址：</label>
				    	<input name="current_address" value="${person.current_address }" type="text" size="30" alt="请输入现居详细地址" />
					</p>
					<p>
						<label>本村户籍：</label>
						<select name="village_household" class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.village_household == 1 ? 'selected = "selected"' : '' }>是</option>
							<option value="0" ${person.village_household == 0 ? 'selected = "selected"' : '' }>否</option>
						</select>
					</p>
					<p>
						<label>村集体经济分配：</label>
						<select name="economicdistribution" class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.economicdistribution == 1 ? 'selected = "selected"' : '' } >是</option>
							<option value="0" ${person.economicdistribution == 0 ? 'selected = "selected"' : '' }>否</option>
						</select>
					</p>
					<p>
						<label>职业：</label>
						<input name="occupation"  value="${person.occupation }"  type="text" size="30"  />
					</p>
					<p>
						<label>宗教信仰：</label>
						<input type="text" name="religion" value="${person.religion }" size="30" />
					</p>
					<p>
						<label>婚姻状况：</label>
						<select name="marital_status" class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.marital_status == 1 ? 'selected = "selected"' : '' }>未婚</option>
							<option value="2" ${person.marital_status == 2 ? 'selected = "selected"' : '' }>已婚</option>
							<option value="3" ${person.marital_status == 3 ? 'selected = "selected"' : '' }>离异</option>
							<option value="4" ${person.marital_status == 4 ? 'selected = "selected"' : '' }>丧偶</option>
						</select>
					</p>
					<p>
						<label>联系方式：</label>
						<input type="text" name="contact" value="${person.contact }" size="30" />
					</p>
					<p>
						<label>网格：</label>
						<input type="text" name="gridid" value="${person.gridid }" size="30" />
					</p>
			</div>
			<div class="formBar">
					<ul>
						<!-- <li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li> -->
						<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
					</ul>
			</div>
			</form>
			</div>
			<!-- 民政信息 -->
			<div>
				<form method="post" action="<%=basepath %>/oa/civil/updatePopulationCivil" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
				<input type="hidden" name="id" value="${person.populationCivil.id}">
				<div class="pageFormContent" layoutH="120" >
					 <p>
						<label>残疾：</label>
						<select name="disabled" class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.populationCivil.disabled == 1 ? 'selected = "selected"' : '' } >是</option>
							<option value="0" ${person.populationCivil.disabled == 0 ? 'selected = "selected"' : '' }>否</option>
						</select>
					</p>
					 <p>
						<label>残疾类型：</label>
						<input type="text" name="deformitystate" value="${person.populationCivil.deformitystate }" size="30" />
					</p>
					 <p>
						<label>残疾证号：</label>
						<input type="text" name="disabilitynumber" value="${person.populationCivil.disabilitynumber }" size="30" />
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
					<div id="civil" style="display: none" >
					<p>
						<label>孤儿补助金额：</label>
						<input type="text"  name="orphanmoney" value="${populationCivil.orphanmoney }"  />元
					</p>
					</div>
					 <p>
						<label>享受残疾人津贴：</label>
						<select name="disabilitybenefits" id="disabilitybenefits" class="combox" onchange="checkpopulationcivil()">
							<option value="">请选择</option>
							<option value="1" ${person.populationCivil.disabilitybenefits == 1 ? 'selected = "selected"' : '' } >是</option>
							<option value="0" ${person.populationCivil.disabilitybenefits == 0 ? 'selected = "selected"' : '' }>否</option>
						</select>
					</p>
					<div id="civilone" style="display: none">
					<p>
						<label>残疾人补助金额：</label>
						<input type="text"  name="disabledmoney" value="${person.populationCivil.disabledmoney }" size="30" />
					</p>
					</div>
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
					<div id="civiltwo" style="display: none" >
					<p>
						<label>高龄补贴：</label>
						<input type="text" id="subsidiesmoney" name="subsidiesmoney" value="${person.populationCivil.subsidiesmoney }" size="30" />元
					</p>
					</div>
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
					 <p>
						<label>参加老年体检人数：</label>
						<input type="text" name="checkamount" value="${person.populationCivil.disabled }" size="30" />人
					</p>
				</div>
				<div class="formBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
						<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
					</ul>
				</div>
				</form>
			</div>
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>
</body>
</html>