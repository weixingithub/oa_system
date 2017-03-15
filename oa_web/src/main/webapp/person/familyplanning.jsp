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
<title>计划生育信息管理</title>
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
					<li><a href="javascript:;"><span>计生信息</span></a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
		<!-- 人口信息 -->
		 <div>
		  <form method="post" action="<%=basepath %>/oa/planning/personAddOrUpdate" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	       <input type="hidden" name="id" value="${person.id }">
	       <input type="hidden" name="orgId" value="${person.orgId }">
	       <input type="hidden" name="userId" value="${person.userId }">
	       <input type="hidden" name="creatime" value="${person.creatime }">
	       <input type="hidden" name="familyPlanningId" value="${person.familyPlanning.id }">
	        <input name="perPCD" value="${persons.perPCD }" type="hidden"/>
	       <div class="pageFormContent" layoutH="120" >
					<p>
						<label>姓名：</label>
						<input name="name" type="text" size="30" value="${person.name }" class="required" readonly="readonly"/>
					</p>
					<p>
						<label>曾用名：</label>
						<input name="oldname" type="text" size="30" value="${person.oldname }" readonly="readonly" />
					</p>
					<p>
						<label>性别：</label>
					    <input type="radio" name="sex" value="0" checked="checked" ${person.sex == 0 ? 'checked = "checked"' : '' } disabled="disabled">男
					    <input type="radio" name="sex" value="1"  ${person.sex == 1 ? 'checked = "checked"' : '' } disabled="disabled">女
					</p>
					<p>
						<label>身份证号：</label>
					     <input name="idnumber" type="text" size="20" class="required" value="${person.idnumber}" 
								  remote="<%=basepath%>/oa/planning/verifyIdnumber?orgId=${person.orgId}&id=${person.id}&oldidnumber=${person.idnumber}"
								  title="身份证号重复"  readonly="readonly"/>
					</p>
					<p>
						<label>出生日期：</label>
						<input name="birthdate" class="date" type="text" size="30" value="${person.birthdate }" /><a class="inputDateButton" href="javascript:;">选择</a>
					</p>
					<p>
						<label>民族：</label>
						<select name="nation" class="combox" id="mySelect">
							<option value="">请选择</option>
							<option value="1" ${person.nation == 1 ? 'selected = "selected"' : '' } >汉族</option>
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
						<input name="tall"  type="text" size="30" value="${person.tall }" alt="请输入身高"  readonly="readonly"/>
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
						<input name="registry_place"  type="text" size="30" value="${person.registry_place }" alt="请输入户籍详细地址"  readonly="readonly"/>
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
				    	<input name="current_address" value="${person.current_address }" type="text" size="30" alt="请输入现居详细地址"  readonly="readonly"/>
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
						<input name="occupation"  value="${person.occupation }"  type="text" size="30"   readonly="readonly"/>
					</p>
					<p>
						<label>宗教信仰：</label>
						<input type="text" name="religion" value="${person.religion }" size="30"  readonly="readonly"/>
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
						<input type="text" name="contact" value="${person.contact }" size="30"  readonly="readonly"/>
					</p>
					<p>
						<label>网格：</label>
						<input type="text" name="gridid" value="${person.gridid }" size="30"  readonly="readonly"/>
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
		   </div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>
</body>
</html>