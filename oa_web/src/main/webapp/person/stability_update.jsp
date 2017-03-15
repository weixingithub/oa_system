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
<title>综治信息管理</title>
</head>
<script type="text/javascript">
$(document).ready(function(){
    checkpop ulationcivil();
    checklabor();
  /*   checkstability(); */
});
	
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
					<li><a href="javascript:;"><span>综治信息</span></a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
		<!-- 人口信息 -->
		 <div>
		  <form method="post" action="<%=basepath %>/oa/stability/personAddOrUpdate" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	       <input type="hidden" name="id" value="${person.id }">
	       <input type="hidden" name="orgId" value="${person.orgId }">
	       <input type="hidden" name="userId" value="${person.userId }">
	       <input type="hidden" name="creatime" value="${person.creatime }">
	       <input type="hidden" name="populationCiviId" value="${person.populationCivil.id }">
	       <input type="hidden" name="stabilityId" value="${person.stability.id }">
	       <input type="hidden" name="laborInsuranceId" value="${person.laborInsurance.id }">
	       <input type="hidden" name="familyPlanningId" value="${person.familyPlanning.id }">
	        <input name="perPCD" value="${persons.perPCD }" type="hidden"/>
	       <div class="pageFormContent" layoutH="120" >
					<p>
						<label>姓名：</label>
						<input name="name" type="text" size="30" value="${person.name }" class="required" readonly="readonly"/>
					</p>
					<p>
						<label>曾用名：</label>
						<input name="oldname" type="text" size="30" value="${person.oldname }"  />
					</p>
					<p>
						<label>性别：</label>
					    <input type="radio" name="sex" value="0" disabled="disabled" checked="checked" ${person.sex == 0 ? 'checked = "checked"' : '' }>男
					    <input type="radio" name="sex" value="1" disabled="disabled" ${person.sex == 1 ? 'checked = "checked"' : '' }>女
					</p>
					<p>
						<label>身份证号：</label>
					     <input name="idnumber" type="text" size="20" class="required" value="${person.idnumber}" 
								  remote="<%=basepath%>/oa/stability/verifyIdnumber?orgId=${person.orgId}&id=${person.id}&oldidnumber=${person.idnumber}"
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
						<!-- <li><div class="buttonActive"><div class="buttonContent"><button type="submit"  disabled="disabled">保存</button></div></div></li>  -->
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
					<div id="stabone">
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
					<div id="stabtwo">
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
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>
</body>
</html>