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
    /**
	 * 人口劳保信息
	 * 控制是否可编辑
	 */
    function checklabor() {
    	var endowmentinsurance= $('#endowmentinsurance').val();//养老保险
    	var labor=document.getElementById("labor"); 
    	if(endowmentinsurance=='0'){
    		labor.style.display="none"; 
    	}else if(endowmentinsurance=='1'){
    		labor.style.display=""; 
    	}
    	var medicalinsurance= $('#medicalinsurance').val();//医疗保险
    	var laborone=document.getElementById("laborone"); 
    	if(medicalinsurance=='0'){
    		laborone.style.display="none"; 
    	}else if(medicalinsurance=='1'){
    		laborone.style.display=""; 
    	}
		 var employmentstatus= $('#employmentstatus').val();//就业状态
		 var labortwo=document.getElementById("labortwo"); 
		 var laborthree=document.getElementById("laborthree"); 
		 if(employmentstatus=='2') {
			 laborthree.style.display=""; 
			 labortwo.style.display="none"; 
		 }else if(employmentstatus=='1' || employmentstatus=='3'){
			 laborthree.style.display="none"; 
			 labortwo.style.display=""; 
		 }
		 var unemployregistration= $('#unemployregistration').val();//办理失业登记
		 var laborfour=document.getElementById("laborfour"); 
		 if(unemployregistration=='0') {
			 laborfour.style.display="none"; 
		 }else if(unemployregistration=='1'){
			 laborfour.style.display=""; 
		 }
	 } 
    /**
	 * 人口综治维稳信息
	 * 控制是否可编辑
	 */
    function checkstability() {
    	var communitycorrection=$('#communitycorrection').val();//是否是社区矫正人员
    	var reeducationreform=$('#reeducationreform').val();//是否为两劳释放人员
    	var stabone=document.getElementById("stabone");  
    	var stabtwo=document.getElementById("stabtwo");  
    	if(communitycorrection=='1'){
    		stabone.style.display=""; 
    	}else if(communitycorrection=='0'){
    		stabone.style.display="none"; 
    	}
    	if(reeducationreform=='1'){
    		stabtwo.style.display=""; 
    	}else if(reeducationreform=='0'){
    		stabtwo.style.display="none"; 
    	}
    	
	 } 
    function addimage(url){
		$("#portraitimageId").attr("src",url);
		$("#personUrl").attr("value",url);
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
					<li><a href="javascript:;"><span>车辆信息</span></a></li>
					<li><a href="javascript:;"><span>民政信息</span></a></li>
					<li><a href="javascript:;"><span>计生信息</span></a></li>
					<li><a href="javascript:;"><span>劳动保障</span></a></li>
					<li><a href="javascript:;"><span>综治信息</span></a></li>
					<li><a href="javascript:;"><span>证件信息</span></a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
		<!-- 人口信息 -->
		 <div>
		  <form method="post" action="<%=basepath %>/oa/person/personAddOrUpdate" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	       <input type="hidden" name="id" value="${person.id }">
	       <input type="hidden" name="ralation" value="${person.ralation }"> <!--与户主关联关系 -->
	       <input type="hidden" name="userId" value="${person.userId }">
	       <input type="hidden" name="creatime" value="${person.creatime }">
	       <input type="hidden" name="populationCiviId" value="${person.populationCivil.id }">
	       <input type="hidden" name="stabilityId" value="${person.stability.id }">
	       <input type="hidden" name="laborInsuranceId" value="${person.laborInsurance.id }">
	       <input type="hidden" name="familyPlanningId" value="${person.familyPlanning.id }">
	       <input type="hidden" name="fileCabinetId" value="${person.fileCabinet.id }">
	       <input type="hidden" name="familyId" value="${person.family.id }"><!--户籍id -->
	       <input type="hidden" name="perPCD" value="${person.perPCD }" />
	       <input type="hidden" name="personUrl" id="personUrl" value="${person.personUrl }"><!-- 头像信息 -->
	       <div class="pageFormContent" layoutH="120" >
	       	<fieldset>
				<legend>基本信息</legend>
				<div style="width: 70%;float: left;">
					<p>
						<label>姓名：</label>
						<input name="name" type="text" size="30" value="${person.name }" class="required" />
					</p>
					<p>
						<label>曾用名：</label>
						<input name="oldname" type="text" size="20" value="${person.oldname }"  />
					</p>
					<p>
						<label>性别：</label>
					    <input type="radio" name="sex" value="0" checked="checked" ${person.sex == 0 ? 'checked = "checked"' : '' }>男
					    <input type="radio" name="sex" value="1"  ${person.sex == 1 ? 'checked = "checked"' : '' }>女
					</p>
					<p>
						<label>身份证号：</label>
					     <input name="idnumber" type="text" size="20" class="required idnumber" value="${person.idnumber}" 
								  remote="<%=basepath%>/oa/person/verifyIdnumber?orgId=${person.orgId}&id=${person.id}&oldidnumber=${person.idnumber}"
								 />
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
						<label>联系方式：</label>
						<input type="text" name="contact" value="${person.contact }" size="30" class="required" />
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
						<label>现居详细地址：</label>
				    	<input name="current_address" value="${person.current_address }" type="text" size="30" alt="请输入现居详细地址" />
					</p>
					<p>
						<label>所属社区：</label>
						<input name="orgId" value="${person.orgId }" type="hidden"/>
						<input name="orgPid" value="${person.orgPid }" type="hidden"/>
						<input name="orgName" type="text" value="${person.orgName}"  class="required"  readonly/>
						<a  class="btnLook" href="<%=basepath %>/oaorgpage/orgtree.jsp" lookupGroup="">选择社区</a>	
					</p>
					</div>
					<div style="width: 150px;float: right;margin-right:15px; height:240px; ">
						<div style ="width:150px; height:200px;">
						<c:if test="${person.personUrl==null || person.personUrl ==''}">
							<img  id="portraitimageId" alt="" src="<%=basepath %>/themes/default/images/unllpic.png" width="150" height="200">
						</c:if>
						<c:if test="${person.personUrl!=null && person.personUrl !=''}">
							<img  id="portraitimageId" alt="" src="${person.personUrl }" width="150" height="200">
						</c:if>
						
						</div>
						<ul style = "margin-top: 5px;">
							<li  style = "float:left;margin-left:15px;">
								<a class="buttonActive"  href="<%=basepath%>/filemanager/file_singleUploadPicture.jsp?fileID=person" lookupGroup="attachment" width="560" height="300" ><span>本地上传</span></a>
							</li>
							<li  style = "float:right;margin-right:15px;">
								 <a class="buttonActive" href="javascript:;"><span>扫描</span></a>
							</li>
						</ul>
					</div>
				</fieldset>
				<fieldset>
					<legend>特殊信息</legend>
					<p>
						<label>是否扶贫对象：</label>
						<select name="poverty" class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.poverty == 1 ? 'selected = "selected"' : '' }>是</option>
							<option value="0" ${person.poverty == 0 ? 'selected = "selected"' : '' }>否</option>
						</select>
					</p>
					<p>
						<label>人员类别 ：</label>
						<select name="category" class="combox" >
							<option value="">请选择</option>
							<option value="0" ${person.category == 0 ? 'selected = "selected"' : '' }>低收入</option>
							<option value="1" ${person.category == 1 ? 'selected = "selected"' : '' }>六十年代精简</option>
							<option value="2" ${person.category == 2 ? 'selected = "selected"' : '' }>城市困难群众</option>
							<option value="3" ${person.category == 3 ? 'selected = "selected"' : '' }>农村困难群众</option>
							<option value="4" ${person.category == 4 ? 'selected = "selected"' : '' }>农村劳模</option>
							<option value="5" ${person.category == 5 ? 'selected = "selected"' : '' }>重点优抚对象</option>
							<option value="6" ${person.category == 6 ? 'selected = "selected"' : '' }>因病致残对象</option>
							<option value="7" ${person.category == 7 ? 'selected = "selected"' : '' }>见义勇为人员</option>
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
						<label>本村户籍：</label>
						<select name="village_household" class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.village_household == 1 ? 'selected = "selected"' : '' }>是</option>
							<option value="0" ${person.village_household == 0 ? 'selected = "selected"' : '' }>否</option>
						</select>
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
						<label>村集体经济分配：</label>
						<select name="economicdistribution" class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.economicdistribution == 1 ? 'selected = "selected"' : '' } >是</option>
							<option value="0" ${person.economicdistribution == 0 ? 'selected = "selected"' : '' }>否</option>
						</select>
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
						<label>职业：</label>
						<input name="occupation"  value="${person.occupation }"  type="text" size="30"  />
					</p>
					<p>
						<label>宗教信仰：</label>
						<input type="text" name="religion" value="${person.religion }" size="30" />
					</p>
					<p>
						<label>网格：</label>
						<input type="text" name="gridid" value="${person.gridid }" size="10" />
					</p>
				</fieldset>
			</div>
			<div class="formBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
						<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
					</ul>
			</div>
			</form>
			</div>
			<!-- 车辆信息 -->
			<div id="vehicle">
				<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
				<table class="table" width="100%" layoutH="210" rel="sysUserBox">
					<thead>
						<tr>
							<th width="10%" >序号</th>
							<th width="20%">车牌号</th>
							<th width="20%">车辆类型</th>
							<th width="25%">车主</th>
						</tr>
					</thead>
					<tbody>
					    <c:forEach  items="${listv}"  var="vehicle" varStatus="i">
						 <tr>
							    <td align="center">${i.index+1 }</td>
				                <td>${vehicle[1] }</td>
				                <td>${vehicle[2]}</td>
				                <td>${vehicle[3] }</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				</div>
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
			<!-- 计生信息 -->
			<div>
				<form method="post" action="<%=basepath %>/oa/planning/updateFamilyPlanning" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
				<input type="hidden" name="id" value="${person.familyPlanning.id }">
				<div class="pageFormContent" layoutH="120" >
					 <p>
						<label>婚育情况：</label>
						<select name="marriagestatus" class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.familyPlanning.marriagestatus == 1 ? 'selected = "selected"' : '' } >未婚</option>
							<option value="2" ${person.familyPlanning.marriagestatus == 2 ? 'selected = "selected"' : '' }>初婚</option>
							<option value="3" ${person.familyPlanning.marriagestatus == 3 ? 'selected = "selected"' : '' }>离婚</option>
							<option value="4" ${person.familyPlanning.marriagestatus == 4 ? 'selected = "selected"' : '' }>再婚</option>
							<option value="5" ${person.familyPlanning.marriagestatus == 5 ? 'selected = "selected"' : '' }>三婚</option>
						</select>
					</p> 
					<p>
						<label>初婚时间：</label>
						<input name="marriagetime" class="date" type="text" size="30" value="${person.familyPlanning.marriagetime }" /><a class="inputDateButton" href="javascript:;">选择</a>
					</p>
					<p>
						<label>婚姻变动时间：</label>
						<input name="marriagechangetime" class="date" type="text" size="30" value="${person.familyPlanning.marriagechangetime }" /><a class="inputDateButton" href="javascript:;">选择</a>
					</p>
					 <p>
						<label>节育措施：</label>
						<select name="contraceptive" class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.familyPlanning.contraceptive == 1 ? 'selected = "selected"' : '' } >上环</option>
							<option value="2" ${person.familyPlanning.contraceptive == 2 ? 'selected = "selected"' : '' }>药具</option>
							<option value="3" ${person.familyPlanning.contraceptive == 3 ? 'selected = "selected"' : '' }>结扎</option>
							<option value="4" ${person.familyPlanning.contraceptive == 4 ? 'selected = "selected"' : '' }>皮埋</option>
							<option value="5" ${person.familyPlanning.contraceptive == 5 ? 'selected = "selected"' : '' }>其它</option>
						</select>
					</p> 
					<p>
						<label>节育时间：</label>
						<input name="contraceptiontime" class="date" type="text" size="30" value="${person.familyPlanning.contraceptiontime }" /><a class="inputDateButton" href="javascript:;">选择</a>
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
						<label>年审号：</label>
						<input type="text" name="annualnumber" value="${person.familyPlanning.annualnumber }" size="30" />人
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
						<label>独生子女补助金额：</label>
						<input type="text" name="childsubsidyamount" value="${person.familyPlanning.childsubsidyamount }" size="30" />元
					</p>
					<p>
						<label>独生子女金额领取时间：</label>
						<input name="onlyamounttime" class="date" type="text" size="30" value="${person.familyPlanning.onlyamounttime }" /><a class="inputDateButton" href="javascript:;">选择</a>
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
						<label>独生子女证领取地：</label>
						<input type="text" name="onlychildstatuslqd" value="${person.familyPlanning.onlychildstatuslqd }" size="30" />
					</p>
					 <p>
						<label>独生子女费领取地：</label>
						<input type="text" name="childgrantslqd" value="${person.familyPlanning.childgrantslqd }" size="30" />
					</p>
					<p>
						<label>领取保健费：</label>
						<select name=subsidiesstate class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.familyPlanning.subsidiesstate == 1 ? 'selected = "selected"' : '' } >是</option>
							<option value="0" ${person.familyPlanning.subsidiesstate == 0 ? 'selected = "selected"' : '' }>否</option>
						</select>
					</p>
					<p>
						<label>领取时间：</label>
						<input name="getsubsidiestime" class="date" type="text" size="30" value="${person.familyPlanning.getsubsidiestime }" /><a class="inputDateButton" href="javascript:;">选择</a>
					</p>
					 <p>
						<label>领取金额：</label>
						<input type="text" name="money" value="${person.familyPlanning.money }" size="30" />元
					 </p>
					 <p>
						<label>社会抚养费：</label>
						<input type="text" name="socialcompensationfee" value="${person.familyPlanning.socialcompensationfee }" size="30" />元
					 </p>
					 <p>
						<label>参加孕前优生人数：</label>
						<input type="text" name="pregnantnumber" value="${person.familyPlanning.pregnantnumber }" size="30" />人
					 </p>
					 <p>
						<label>接受婴幼儿疫苗人数：</label>
						<input type="text" name="vaccinenumber" value="${person.familyPlanning.vaccinenumber }" size="30" />人
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
			<!-- 劳保信息 -->
			<div>
				<form method="post" action="<%=basepath %>/oa/labour/updateLaborInsurance" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
				<input type="hidden" name="id" value="${person.laborInsurance.id }">
				<div class="pageFormContent" layoutH="120" >
				     <p>
						<label>养老保险：</label>
						<select name=endowmentinsurance class="combox" id="endowmentinsurance" onchange="checklabor()" >
							<option value="">请选择</option>
							<option value="1" ${person.laborInsurance.endowmentinsurance == 1 ? 'selected = "selected"' : '' } >参保</option>
							<option value="0" ${person.laborInsurance.endowmentinsurance == 0 ? 'selected = "selected"' : '' }>未参保</option>
						</select>
					 </p>
					 <div id="labor" style="display: none">
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
					</div>
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
					<div id="laborone" style="display: none">
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
					</div>
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
						<label>享受年限：</label>
						<input type="text" name="enjoymenttime" value="${person.laborInsurance.enjoymenttime }" size="30" />年
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
						<label>领取地：</label>
						<input type="text" name="pensionlqd" value="${person.laborInsurance.pensionlqd }" size="30" />
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
					<div id="labortwo" style="display: none">
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
						<label>就职单位名称：</label>
						<input type="text" name="officename" value="${person.laborInsurance.officename }" size="30" />
					 </p>
					</div>
					<div id="laborthree" style="display: none">
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
					</div>
					 <p>
						<label>办理就业失业登记：</label>
						<select name=unemployregistration class="combox" id="unemployregistration" onchange="checklabor()">
							<option value="">请选择</option>
							<option value="1" ${person.laborInsurance.unemployregistration == 1 ? 'selected = "selected"' : '' } >是</option>
							<option value="0" ${person.laborInsurance.unemployregistration == 0 ? 'selected = "selected"' : '' }>否</option>
						</select>
					</p>
					<div id="laborfour" style="display: none">
					 <p>
						<label>备注：</label>
						<input type="text" name="remark" id="remark" value="${person.laborInsurance.remark }" size="30" />
					 </p>
					 </div>
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
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
						<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
					</ul>
				</div>
				</form>
			</div>
			<!-- 综治维稳信息 -->
			<div>
				<form method="post" action="<%=basepath %>/oa/stability/updateStability" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
				  <input type="hidden" name="id" value="${person.stability.id }">
				<div class="pageFormContent" layoutH="120" >
				   <p>
						<label>社区矫正人员：</label>
						<select name=communitycorrection class="combox"  id="communitycorrection" onchange="checkstability()">
							<option value="">请选择</option>
							<option value="1" ${person.stability.communitycorrection == '1' ? 'selected = "selected"' : '' } >是</option>
							<option value="0" ${person.stability.communitycorrection == '0' ? 'selected = "selected"' : '' }>否</option>
						</select>
					</p>
					<div id="stabone" style="display: none">
					<p>
						<label>社区矫正人员编号：</label>
						<input type="text" name="rectifyid" id="rectifyid" value="${person.stability.rectifyid }" size="30" />
					 </p>
					 <p>
						<label>原羁押场所：</label>
						<input type="text" name="custodyplace" value="${person.stability.custodyplace }" size="30" />
					 </p>
					 <p>
						<label>矫正类别：</label>
						<select name=rectifytype class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.stability.rectifytype == 1 ? 'selected = "selected"' : '' }>管制</option>
							<option value="2" ${person.stability.rectifytype == 2 ? 'selected = "selected"' : '' }>缓刑</option>
							<option value="3" ${person.stability.rectifytype == 3 ? 'selected = "selected"' : '' }>假释</option>
							<option value="4" ${person.stability.rectifytype == 4 ? 'selected = "selected"' : '' }>暂予监外执行</option>
							<option value="5" ${person.stability.rectifytype == 5 ? 'selected = "selected"' : '' }>剥夺政治权利</option>
						</select>
					</p>
					<p>
						<label>矫正开始日期：</label>
						<input name="rectifystartdate" class="date" type="text" size="30" value="${person.stability.rectifystartdate }" /><a class="inputDateButton" href="javascript:;">选择</a>
					</p>
					<p>
						<label>矫正结束日期：</label>
						<input name="rectifyenddate" class="date" type="text" size="30" value="${person.stability.rectifyenddate }" /><a class="inputDateButton" href="javascript:;">选择</a>
					</p>
					<p>
						<label>非法集资人员：</label>
						<select name=illegalfund class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.stability.illegalfund == 1 ? 'selected = "selected"' : '' } >是</option>
							<option value="0" ${person.stability.illegalfund == 0 ? 'selected = "selected"' : '' }>否</option>
						</select>
					</p>
					<p>
						<label>两劳释放人员：</label>
						<select name=reeducationreform class="combox" id="reeducationreform" onchange="checkstability()">
							<option value="">请选择</option>
							<option value="1" ${person.stability.reeducationreform == 1 ? 'selected = "selected"' : '' } >是</option>
							<option value="0" ${person.stability.reeducationreform == 0 ? 'selected = "selected"' : '' }>否</option>
						</select>
					</p>
					<div id="stabtwo" style="display: none">
					<p>
						<label>累惯犯：</label>
						<select name=recidivist class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.stability.recidivist == 1 ? 'selected = "selected"' : '' } >是</option>
							<option value="0" ${person.stability.recidivist == 0 ? 'selected = "selected"' : '' }>否</option>
						</select>
					</p>
					<p>
						<label>"四史"情况：</label>
						<select name=foursituation class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.stability.foursituation == 1 ? 'selected = "selected"' : '' } >吸毒史</option>
							<option value="2" ${person.stability.foursituation == 2 ? 'selected = "selected"' : '' }>逃脱史</option>
							<option value="3" ${person.stability.foursituation == 3 ? 'selected = "selected"' : '' } >自杀史</option>
							<option value="4" ${person.stability.foursituation == 4 ? 'selected = "selected"' : '' }>袭警史</option>
						</select>
					</p>
					<p>
						<label>"三涉"情况：</label>
						<select name=threesituation class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.stability.threesituation == 1 ? 'selected = "selected"' : '' } >涉毒</option>
							<option value="2" ${person.stability.threesituation == 2 ? 'selected = "selected"' : '' }>涉黑</option>
							<option value="3" ${person.stability.threesituation == 3 ? 'selected = "selected"' : '' } >涉枪</option>
						</select>
					</p>
					<p>
						<label>重点上访人员：</label>
						<select name=focuspetitioners class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.stability.focuspetitioners == 1 ? 'selected = "selected"' : '' } >是</option>
							<option value="0" ${person.stability.focuspetitioners == 0 ? 'selected = "selected"' : '' }>否</option>
						</select>
					</p>
					<p>
						<label>涉核人员：</label>
						<select name=nuclearpersonnel class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.stability.nuclearpersonnel == 1 ? 'selected = "selected"' : '' } >是</option>
							<option value="0" ${person.stability.nuclearpersonnel == 0 ? 'selected = "selected"' : '' }>否</option>
						</select>
					</p>
					<p>
						<label>参与邪教组织：</label>
						<select name=involvedInCults class="combox" >
							<option value="">请选择</option>
							<option value="1" ${person.stability.involvedInCults == 1 ? 'selected = "selected"' : '' } >是</option>
							<option value="0" ${person.stability.involvedInCults == 0 ? 'selected = "selected"' : '' }>否</option>
						</select>
					</p>
					</div>
				 </div>
				</div>
				<div class="formBar">
					<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
						<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
					</ul>
				</div>
				</form>
			</div>
			<!-- 证件信息 -->
			<div>
				<form method="post" action="<%=basepath%>/oa/fileCabinet/updateFileCabinet"
					class="pageForm required-validate"
					onsubmit="return validateCallback(this, navTabAjaxDone);">
					<input type="hidden" name="id" value="${person.fileCabinet.id}" />
					<input type="hidden" id="filejson" name="filejson">
					<input type="hidden" id="fileCabinetId" name="fileCabinetId" value="${person.fileCabinet.id}">
					<div class="pageFormContent" layoutH="120">
						<fieldset>
							<dl>
								<dt>文件标签：</dt>
								<dd><input name="fileLabel" type="text" class="required" value="${person.fileCabinet.fileLabel}"/></dd>
							</dl>
						</fieldset>
						<fieldset>
							<div class="tabsContent" style="height: 200px;">
								<table id="filetable" class="list nowrap itemDetail" addButton="添加附件" width="90%">
									<thead>
										<tr>
											<th type="attach" name="items.fileOldName[#index#]" lookupGroup="items" lookupUrl="<%=basepath%>/filemanager/file_singleUpload.jsp?fileID=person&fileCabinetId=${person.fileCabinet.id}" lookupPk="fileNewName" suggestFields="fileOldName,fileNewName,fileSize,fileType,filePathUrl,fileWebUrl" size="30">从附件</th>
											<th type="enum" name="items.fileType[#index#]"  enumUrl="<%=basepath%>/filemanager/file_type.html" size="12">文件类型</th>
											<th type="text" name="items.fileSize[#index#]" size="8">文件大小</th>
											<th type="text" name="items.fileWebUrl[#index#]" size="20" >文件web路径</th>
											<th type="del" width="60">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${person.fileCabinet.listFile}" var="fileMessage" varStatus="st">
											<tr class="unitBox">
											 <td>
											 	<input type="hidden" name="items.fileNewName[${st.index}]"  value="${fileMessage.fileNewName }" >
											 	<input type="text" name="items.fileOldName[${st.index}]"  value="${fileMessage.fileOldName }" size="30" readonly="true">
											 </td>
											 <td>
											 	<select class="combox" name="items.fileType[${st.index}]">
											 		<option value="1" <c:if test="${fileMessage.fileType =='1' }">selected</c:if>>身份证</option>
													<option value="2" <c:if test="${fileMessage.fileType =='2' }">selected</c:if>>户口本</option>
													<option value="3" <c:if test="${fileMessage.fileType =='3' }">selected</c:if>>病例或诊断证明</option>
													<option value="4" <c:if test="${fileMessage.fileType =='4' }">selected</c:if>>个人申请</option>
													<option value="5" <c:if test="${fileMessage.fileType =='5' }">selected</c:if>>申报表</option>
													<option value="6" <c:if test="${fileMessage.fileType =='6' }">selected</c:if>>残疾证明</option>
													<option value="7" <c:if test="${fileMessage.fileType =='7' }">selected</c:if>>其他相关材料</option>
											 	</select>
											 </td>
											 <td>
											 	<input type="text" name="items.fileSize[${st.index}]"  value="${fileMessage.fileSize }" size="8" readonly="true">
											 </td>
											 <td>
											 	<input type="text" name="items.fileWebUrl[${st.index}]"  value="${fileMessage.fileWebUrl }" size="20" readonly="true">
											 </td>
											 <td><a href="javascript:void(0)" class="btnDel " >删除</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</fieldset>
						<fieldset>
							<dl class="nowrap">
								<dt>文件备注：</dt>
								<dd><textarea name="fileComment" class="required" cols="60" rows="5">${person.fileCabinet.fileComment}</textarea></dd>
							</dl>
						</fieldset>
					</div>
					<div class="formBar">
						<ul>
							<li><div class="buttonActive">
									<div class="buttonContent">
										<button type="submit" onclick="filedata();">保存</button>
									</div>
								</div></li>
							<li><div class="button">
									<div class="buttonContent">
										<button type="button" class="close">取消</button>
									</div>
								</div></li>
						</ul>
					</div>
				</form>
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>
<script type="text/javascript">
//异步删除文件
function deletefile(trobjs){
	var falg = false;
	var tdobjs = trobjs[0].getElementsByTagName("td");
	var inputobj = tdobjs[0].getElementsByTagName("input");
	if(inputobj[0].name.indexOf("fileNewName")!=-1){
		var selobj = tdobjs[1].getElementsByTagName("select");
		var inputa = tdobjs[2].getElementsByTagName("input");
		var inputb = tdobjs[3].getElementsByTagName("input");
		var fileMessage = new Object();
		fileMessage.fileNewName = inputobj[0].value;
		fileMessage.fileOldName = inputobj[1].value;
		fileMessage.fileType = selobj[0].value;
		fileMessage.fileSize = inputa[0].value;
		fileMessage.fileWebUrl = inputb[0].value;
		if(inputobj[0].value!="" && inputobj[0].value!=null){
			var fileCabinetId =  $("#fileCabinetId").val();
			var url = "<%=basepath%>/oa/fileCabinet/deleteFile";
			falg = deleteAjax(url,JSON.stringify(fileMessage),fileCabinetId);
		}else{
			falg = true;
		}
	}else{
		falg = true;
	}
	return falg;
}
function deleteAjax(url,json,id){
	var falg = false;
		$.ajax({
			type:'POST',
			dataType:"json",
			url:url,
			data:{
					objetcJson:json,
					id:id
				},
			async: false,
			success: function(result){
				alertMsg.correct(result.message);
				if(result.statusCode=="200"){
					falg = true;
				}
			},
			error: DWZ.ajaxError
		})
	return falg;
}
</script>
</body>