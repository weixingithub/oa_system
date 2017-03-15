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
<title>劳动保障信息管理</title>
</head>
<script type="text/javascript">
$(document).ready(function(){
    checklabor();
});
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
					<li><a href="javascript:;"><span>劳动保障</span></a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
		<!-- 人口信息 -->
		 <div>
		  <form method="post" action="<%=basepath %>/oa/labour/personAddOrUpdate" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	       <input type="hidden" name="id" value="${person.id }">
	       <input type="hidden" name="orgId" value="${person.orgId }">
	       <input type="hidden" name="userId" value="${person.userId }">
	       <input type="hidden" name="creatime" value="${person.creatime }">
	       <input type="hidden" name="laborInsuranceId" value="${person.laborInsurance.id }">
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
								  remote="<%=basepath%>/oa/labour/verifyIdnumber?orgId=${person.orgId}&id=${person.id}&oldidnumber=${person.idnumber}"
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
					 <div id="labor">
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
					<div id="laborone">
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
					<div id="labortwo">
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
					<div id="laborthree">
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
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>
</body>
</html>