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
<title>人口文本显示页面</title>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		checkpopulationcivil();
		checklabor();
		checkstability();
	});
	/**
	 * 人口民政信息
	 * 控制input是否可编辑
	 */
	function checkpopulationcivil() {
		var receivegrantsorphan = $('#receivegrantsorphan').val();//是否领取孤儿补助
		var civil = document.getElementById("civil");
		if (receivegrantsorphan == '0') {
			civil.style.display = "none";
		} else if (receivegrantsorphan == '1') {
			civil.style.display = "";
		}
		var disabilitybenefits = $('#disabilitybenefits').val();//是否享受残疾人津贴
		var civilone = document.getElementById("civilone");
		if (disabilitybenefits == '0') {
			civilone.style.display = "none";
		} else if (disabilitybenefits == '1') {
			civilone.style.display = "";
		}
		var subsidies = $('#subsidies').val();//是否享受高龄津贴
		var civiltwo = document.getElementById("civiltwo");
		if (subsidies == '0') {
			civiltwo.style.display = "none";
		} else {
			civiltwo.style.display = "";
		}
	}
	/**
	 * 人口劳保信息
	 * 控制是否可编辑
	 */
	function checklabor() {
		var endowmentinsurance = $('#endowmentinsurance').val();//养老保险
		var labor = document.getElementById("labor");
		if (endowmentinsurance == '0') {
			labor.style.display = "none";
		} else if (endowmentinsurance == '1') {
			labor.style.display = "";
		}
		var medicalinsurance = $('#medicalinsurance').val();//医疗保险
		var laborone = document.getElementById("laborone");
		if (medicalinsurance == '0') {
			laborone.style.display = "none";
		} else if (medicalinsurance == '1') {
			laborone.style.display = "";
		}
		var employmentstatus = $('#employmentstatus').val();//就业状态
		var labortwo = document.getElementById("labortwo");
		var laborthree = document.getElementById("laborthree");
		if (employmentstatus == '2') {
			laborthree.style.display = "";
			labortwo.style.display = "none";
		} else if (employmentstatus == '1' || employmentstatus == '3') {
			laborthree.style.display = "none";
			labortwo.style.display = "";
		}
		var unemployregistration = $('#unemployregistration').val();//办理失业登记
		var laborfour = document.getElementById("laborfour");
		if (unemployregistration == '0') {
			laborfour.style.display = "none";
		} else if (unemployregistration == '1') {
			laborfour.style.display = "";
		}
	}
	/**
	 * 人口综治维稳信息
	 * 控制是否可编辑
	 */
	function checkstability() {
		var communitycorrection = $('#communitycorrection').val();//是否是社区矫正人员
		var reeducationreform = $('#reeducationreform').val();//是否为两劳释放人员
		var stabone = document.getElementById("stabone");
		var stabtwo = document.getElementById("stabtwo");
		if (communitycorrection == '1') {
			stabone.style.display = "";
		} else if (communitycorrection == '0') {
			stabone.style.display = "none";
		}
		if (reeducationreform == '1') {
			stabtwo.style.display = "";
		} else if (reeducationreform == '0') {
			stabtwo.style.display = "none";
		}

	}
</script>
<style type="text/css">
ul.rightTools {
	float: right;
	display: block;
}

ul.rightTools li {
	float: left;
	display: block;
	margin-left: 5px
}
</style>
<body>
	<div class="pageContent" style="padding: 5px">
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
							<li><a href="javascript:;"><span>户籍详情</span></a></li>
						</c:if>
					</ul>
				</div>
			</div>
			<div class="tabsContent">
				<!-- 人口信息 -->
				<div>
					<input type="hidden" name="ralation" value=""> <input
						type="hidden" name="id" value="${person.id }"> <input
						type="hidden" name="orgId" value="${person.orgId }"> <input
						type="hidden" name="userId" value="${person.userId }"> <input
						type="hidden" name="creatime" value="${person.creatime }">
					<input type="hidden" name="populationCiviId"
						value="${person.populationCivil.id }"> <input
						type="hidden" name="stabilityId" value="${person.stability.id }">
					<input type="hidden" name="laborInsuranceId"
						value="${person.laborInsurance.id }"> <input type="hidden"
						name="familyPlanningId" value="${person.familyPlanning.id }">
					<input type="hidden" name="perPCD" value="${person.perPCD }" />
					<div class="pageFormContent" layoutH="120">
						<fieldset>
							<legend>基本信息</legend>
							<div style="width: 70%; float: left;">
								<p>姓名：${person.name }</p>
								<p>曾用名：${person.oldname }</p>
								<p>
									性别：
									<c:if test="${person.sex == 0}">男</c:if>
									<c:if test="${person.sex == 1}">女</c:if>
								</p>
								<p>身份证号：${person.idnumber}</p>
								<p>出生日期：${person.birthdate }</p>
								<p>
									民族：
									<c:if test="${person.nation == 1}">汉族</c:if>
									<c:if test="${person.nation == 2}">少数民族</c:if>
								</p>
								<p>
									婚姻状况：
									<c:if test="${person.marital_status == 1 }">未婚</c:if>
									<c:if test="${person.marital_status == 2}">已婚</c:if>
									<c:if test="${person.marital_status == 3 }">离异</c:if>
									<c:if test="${person.marital_status == 4}">丧偶</c:if>
								</p>
								<p>
									血型 ：
									<c:if test="${person.bloodtype==1}">A型</c:if>
									<c:if test="${person.bloodtype==2}">B型</c:if>
									<c:if test="${person.bloodtype==3}">AB型</c:if>
									<c:if test="${person.bloodtype==4}">O型</c:if>
									<c:if test="${person.bloodtype==5}">其它</c:if>
								</p>
								<p>身高：${person.tall }</p>
								<p>联系方式：${person.contact }</p>
								<p>
									户口性质：
									<c:if test="${person.residenttype == 1 }">农户</c:if>
									<c:if test="${person.residenttype == 2 }">非农户</c:if>
								</p>
								<p>
									政治面貌：
									<c:if test="${person.political==1}">党员</c:if>
									<c:if test="${person.political==2}">团员</c:if>
									<c:if test="${person.political==3}">群众</c:if>
									<c:if test="${person.political==4}">其它</c:if>
								</p>
								<p>户籍地： ${provice.proName }&nbsp;${city.cityName }&nbsp;${dis.disName }
								</p>
								<p>户籍详细地址：${person.registry_place }</p>
								<p>现居详细地址：${person.current_address }</p>
								<p>所属社区：${person.orgName}</p>
							</div>
							<div
								style="width: 150px; float: right; margin-right: 15px; height: 240px;">
								<div style="width: 150px; height: 200px;">
									<c:if test="${person.personUrl==null || person.personUrl ==''}">
										<img id="portraitimageId" alt=""
											src="<%=basepath%>/themes/default/images/unllpic.png"
											width="150" height="200">
									</c:if>
									<c:if test="${person.personUrl!=null && person.personUrl !=''}">
										<img id="portraitimageId" alt="" src="${person.personUrl }"
											width="150" height="200">
									</c:if>
								</div>
							</div>
						</fieldset>
						<fieldset>
							<legend>特殊信息</legend>
							<p>
								是否扶贫对象：
								<c:if test="${person.poverty == 1 }">是</c:if>
								<c:if test="${person.poverty == 0 }">否</c:if>
							</p>
							<p>
								人员类别：
								<c:if test="${person.category == 0 }">低收入</c:if>
								<c:if test="${person.category == 1 }">六十年代精简</c:if>
								<c:if test="${person.category == 2 }">城市困难群众</c:if>
								<c:if test="${person.category == 3 }">农村困难群众</c:if>
								<c:if test="${person.category == 4 }">农村劳模</c:if>
								<c:if test="${person.category == 5 }">重点优抚对象</c:if>
								<c:if test="${person.category == 6 }">因病致残对象</c:if>
								<c:if test="${person.category == 7 }">见义勇为人员</c:if>
							</p>
							<p>
								人口一致标识：
								<c:if test="${person.uniformIdentification == 1 }">是</c:if>
								<c:if test="${person.uniformIdentification == 0 }">否</c:if>
							</p>
							<p>
								本村户籍：
								<c:if test="${person.village_household == 1 }">是</c:if>
								<c:if test="${person.village_household == 0 }">否</c:if>
							</p>
							<p>
								辖区内：
								<c:if test="${person.jurisdiction == 1 }">是</c:if>
								<c:if test="${person.jurisdiction == 0 }">否</c:if>
							</p>

							<p>
								村集体经济分配：
								<c:if test="${person.economicdistribution == 1 }">是</c:if>
								<c:if test="${person.economicdistribution == 0 }">否</c:if>
							</p>
							<p>
								嫁城女：
								<c:if test="${person.citywoman == 1 }">是</c:if>
								<c:if test="${person.citywoman == 2 }">否</c:if>
							</p>
							<p>职业：${person.occupation }</p>
							<p>宗教信仰：${person.religion }</p>
							<p>网格：${person.gridid }</p>
						</fieldset>
					</div>
				</div>
				<!-- 车辆信息 -->
				<div id="vehicle">
					<div class="pageContent"
						style="border-left: 1px #B8D0D6 solid; border-right: 1px #B8D0D6 solid">
						<table class="table" width="100%" layoutH="210" rel="sysUserBox">
							<thead>
								<tr>
									<th width="10%">序号</th>
									<th width="20%">车牌号</th>
									<th width="20%">车辆类型</th>
									<th width="25%">车主</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${listv }" var="vehicle" varStatus="i">
									<tr>
										<td align="center">${i.index+1 }</td>
										<td>${vehicle[0] }</td>
										<td>${vehicle[1]}</td>
										<td>${vehicle[2] }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
				<!-- 民政信息 -->
				<div>
					<input type="hidden" name="id" value="${person.populationCivil.id}">
					<div class="pageFormContent" layoutH="120">
						<p>
							残疾：
							<c:if test="${person.populationCivil.disabled == 1 }">是</c:if>
							<c:if test="${person.populationCivil.disabled == 0 }">否</c:if>
						</p>
						<p>残疾类型：${person.populationCivil.deformitystate }</p>
						<p>残疾证号：${person.populationCivil.disabilitynumber }</p>
						<p>
							残疾级别：
							<c:if test="${person.populationCivil.disablitygrade == 1}">一级</c:if>
							<c:if test="${person.populationCivil.disablitygrade == 2}">二级</c:if>
							<c:if test="${person.populationCivil.disablitygrade == 3}">三级</c:if>
							<c:if test="${person.populationCivil.disablitygrade == 4}">四级</c:if>
							<c:if test="${person.populationCivil.disablitygrade == 5}">五级</c:if>
							<c:if test="${person.populationCivil.disablitygrade == 6}">六级</c:if>
							<c:if test="${person.populationCivil.disablitygrade == 7}">七级</c:if>
							<c:if test="${person.populationCivil.disablitygrade == 8}">八级</c:if>
							<c:if test="${person.populationCivil.disablitygrade == 9}">九级</c:if>
							<c:if test="${person.populationCivil.disablitygrade == 10}">十级</c:if>
						</p>
						<p>
							孤残儿：
							<c:if test="${person.populationCivil.orphanschildren == 1 }">是</c:if>
							<c:if test="${person.populationCivil.orphanschildren == 0 }">否</c:if>
						</p>
						<p>
							领取孤儿补助：
							<c:if test="${person.populationCivil.receivegrantsorphan == 1}">是</c:if>
							<c:if test="${person.populationCivil.receivegrantsorphan == 0}">否</c:if>
						</p>
						<p>孤儿补助金额：${populationCivil.orphanmoney }</p>
						<p>
							享受残疾人津贴：
							<c:if test="${person.populationCivil.disabilitybenefits == 1}">是</c:if>
							<c:if test="${person.populationCivil.disabilitybenefits == 0}">否</c:if>
						</p>
						<p>残疾人补助金额：${person.populationCivil.disabledmoney }</p>
						<p>
							现役军人：
							<c:if test="${person.populationCivil.activearmy == 1}">是</c:if>
							<c:if test="${person.populationCivil.activearmy == 0}">否</c:if>
						</p>
						<p>
							孤寡老人：
							<c:if test="${person.populationCivil.elderly == 1}">是</c:if>
							<c:if test="${person.populationCivil.elderly == 0}">否</c:if>
						</p>
						<p>
							领取高龄津贴：
							<c:if test="${person.populationCivil.subsidies == 1}">是</c:if>
							<c:if test="${person.populationCivil.subsidies == 0}">否</c:if>
						</p>
						<p>高龄补贴：${person.populationCivil.subsidiesmoney }</p>
						<p>
							退伍军人：
							<c:if test="${person.populationCivil.veteran == 1}">是</c:if>
							<c:if test="${person.populationCivil.veteran == 0}">否</c:if>
						</p>
						<p>
							空巢老人：
							<c:if test="${person.populationCivil.emptynester== 1}">是</c:if>
							<c:if test="${person.populationCivil.emptynester== 0}">否</c:if>
						</p>
						<p>
							老红军：
							<c:if test="${person.populationCivil.oldarmy== 1}">是</c:if>
							<c:if test="${person.populationCivil.oldarmy== 0}">否</c:if>
						</p>
						<p>
							复转干部：
							<c:if test="${person.populationCivil.recadres== 1}">是</c:if>
						</p>
						<p>
							劳模：
							<c:if test="${person.populationCivil.modelworkers == 1}">是</c:if>
							<c:if test="${person.populationCivil.modelworkers == 0}">否</c:if>
						</p>
						<p>参加老年体检人数：${person.populationCivil.disabled }</p>
					</div>
				</div>
				<!-- 计生信息 -->
				<div>
					<input type="hidden" name="id" value="${person.familyPlanning.id }">
					<div class="pageFormContent" layoutH="120">
						<p>
							婚育情况：
							<c:if test="${person.familyPlanning.marriagestatus == 1}">未婚</c:if>
							<c:if test="${person.familyPlanning.marriagestatus == 2}">初婚</c:if>
							<c:if test="${person.familyPlanning.marriagestatus == 3}">离婚</c:if>
							<c:if test="${person.familyPlanning.marriagestatus== 4}">再婚</c:if>
							<c:if test="${person.familyPlanning.marriagestatus== 5}">三婚</c:if>
						</p>
						<p>初婚时间：${person.familyPlanning.marriagetime }</p>
						<p>婚姻变动时间：${person.familyPlanning.marriagechangetime }</p>
						<p>
							节育措施：
							<c:if test="${person.familyPlanning.contraceptive  == 1}">上环</c:if>
							<c:if test="${person.familyPlanning.contraceptive  == 2}">药具</c:if>
							<c:if test="${person.familyPlanning.contraceptive  == 3}">结扎</c:if>
							<c:if test="${person.familyPlanning.contraceptive == 4}">皮埋</c:if>
							<c:if test="${person.familyPlanning.contraceptive == 5}">其它</c:if>
						</p>
						<p>节育时间：${person.familyPlanning.contraceptiontime }</p>
						<p>
							领取独生子女证：
							<c:if test="${person.familyPlanning.onlychildstatus== 1}">是</c:if>
							<c:if test="${person.familyPlanning.onlychildstatus== 0}">否</c:if>
						</p>
						<p>年审号：${person.familyPlanning.annualnumber }</p>
						<p>
							二胎指标：
							<c:if test="${person.familyPlanning.twotires== 1}">是</c:if>
							<c:if test="${person.familyPlanning.twotires== 0}">否</c:if>
						</p>
						<p>
							领取独生子女父母补助金：
							<c:if test="${person.familyPlanning.childgrants== 1}">是</c:if>
							<c:if test="${person.familyPlanning.childgrants== 0}">否</c:if>
						</p>
						<p>独生子女补助金额：${person.familyPlanning.childsubsidyamount }</p>
						<p>独生子女金额领取时间：${person.familyPlanning.onlyamounttime }</p>
						<p>
							办理生育登记：
							<c:if test="${person.familyPlanning.birthregistration== 1}">是</c:if>
							<c:if test="${person.familyPlanning.birthregistration== 0}">否</c:if>
						</p>
						<p>
							办理三查：
							<c:if test="${person.familyPlanning.richardIII == 1}">是</c:if>
							<c:if test="${person.familyPlanning.richardIII == 0}">否</c:if>
						</p>
						<p>
							办理流动人口婚育证：
							<c:if test="${person.familyPlanning.obstetricalcard== 1}">是</c:if>
							<c:if test="${person.familyPlanning.obstetricalcard == 0}">否</c:if>
						</p>
						<p>独生子女证领取地：${person.familyPlanning.onlychildstatuslqd }</p>
						<p>独生子女费领取地：${person.familyPlanning.childgrantslqd }</p>
						<p>
							领取保健费：
							<c:if test="${person.familyPlanning.subsidiesstate== 1}">是</c:if>
							<c:if test="${person.familyPlanning.subsidiesstate == 0}">否</c:if>
						</p>
						<p>领取时间：${person.familyPlanning.getsubsidiestime }</p>
						<p>领取金额：${person.familyPlanning.money }</p>
						<p>社会抚养费：${person.familyPlanning.socialcompensationfee }</p>
						<p>参加孕前优生人数：${person.familyPlanning.pregnantnumber }</p>
						<p>接受婴幼儿疫苗人数：${person.familyPlanning.vaccinenumber }</p>
					</div>
				</div>
				<!-- 劳保信息 -->
				<div>
					<input type="hidden" name="id" value="${person.laborInsurance.id }">
					<div class="pageFormContent" layoutH="120">
						<p>
							养老保险：
							<c:if test="${person.laborInsurance.endowmentinsurance== 1}">参保</c:if>
							<c:if test="${person.laborInsurance.endowmentinsurance== 0}">未参保</c:if>
						</p>
						<p>
							养老保险类型：
							<c:if test="${person.laborInsurance.pensiontype== 1}">职工养老保险</c:if>
							<c:if test="${person.laborInsurance.pensiontype== 2}">城镇医疗保险</c:if>
							<c:if test="${person.laborInsurance.pensiontype== 3}">商业保险</c:if>
							<c:if test="${person.laborInsurance.pensiontype== 4}">失地农民养老保险</c:if>
						</p>
						<p>
							享受养老金返还：
							<c:if test="${person.laborInsurance.pensionreturn== 1}">是</c:if>
							<c:if test="${person.laborInsurance.pensionreturn == 0}">否</c:if>
						</p>
						<p>
							医疗保险：
							<c:if test="${person.laborInsurance.medicalinsurance == 1}">参保</c:if>
							<c:if test="${person.laborInsurance.medicalinsurance == 0}">未参保</c:if>
						</p>
						<p>
							医疗保险类型：
							<c:if test="${person.laborInsurance.medicaltype== 1}">职工医疗保险</c:if>
							<c:if test="${person.laborInsurance.medicaltype== 2}">城镇居民医疗保险</c:if>
							<c:if test="${person.laborInsurance.medicaltype== 3}">新农合</c:if>
							<c:if test="${person.laborInsurance.medicaltype== 4}">商业保险</c:if>
						</p>
						<p>
							享受国家低保：
							<c:if test="${person.laborInsurance.nationallowances == 1}">是</c:if>
							<c:if test="${person.laborInsurance.nationallowances == 0}">否</c:if>
						</p>
						<p>
							享受社保补贴：
							<c:if test="${person.laborInsurance.socialsubsidies == 1}">是</c:if>
							<c:if test="${person.laborInsurance.socialsubsidies == 0}">否</c:if>
						</p>
						<p>享受年限：${person.laborInsurance.enjoymenttime }</p>
						<p>
							领取养老金：
							<c:if test="${person.laborInsurance.retirepension== 1}">是</c:if>
							<c:if test="${person.laborInsurance.retirepension== 0}">否</c:if>
						</p>
						<p>领取地：${person.laborInsurance.pensionlqd }</p>
						<p>
							就业状态：
							<c:if test="${person.laborInsurance.employmentstatus== 1}">已就业</c:if>
							<c:if test="${person.laborInsurance.employmentstatus== 2}">未就业</c:if>
							<c:if test="${person.laborInsurance.employmentstatus== 3}">再就业</c:if>
						</p>
						<p>
							就业性质：
							<c:if test="${person.laborInsurance.employmentnature== 1}">企事业单位</c:if>
							<c:if test="${person.laborInsurance.employmentnature== 2}">私营企业</c:if>
							<c:if test="${person.laborInsurance.employmentnature== 3}">灵活就业</c:if>
							<c:if test="${person.laborInsurance.employmentnature== 4}">公益岗位</c:if>
							<c:if test="${person.laborInsurance.employmentnature== 5}">退休</c:if>
						</p>
						<p>就职单位名称：${person.laborInsurance.officename }</p>
						<p>
							求职意向：
							<c:if test="${person.laborInsurance.jobintension == 1}">自主创业</c:if>
							<c:if test="${person.laborInsurance.jobintension == 2}">灵活就业</c:if>
							<c:if test="${person.laborInsurance.jobintension== 3}">企事业单位</c:if>
							<c:if test="${person.laborInsurance.jobintension ==4}">暂不考虑就业</c:if>
							<c:if test="${person.laborInsurance.jobintension == 5}">其它</c:if>
						</p>
						<p>
							办理就业失业登记：
							<c:if test="${person.laborInsurance.unemployregistration== 1}">是</c:if>
							<c:if test="${person.laborInsurance.unemployregistration== 0}">否</c:if>
						</p>
						<p>备注：${person.laborInsurance.remark }</p>
						<p>
							私营企业小额贷款：
							<c:if test="${person.laborInsurance.enjoypettyloan== 1}">是</c:if>
							<c:if test="${person.laborInsurance.enjoypettyloan== 0}">否</c:if>
						</p>
					</div>
				</div>
				<!-- 综治维稳信息 -->
				<div>
					<input type="hidden" name="id" value="${person.stability.id }">
					<div class="pageFormContent" layoutH="120">
						<p>
							社区矫正人员：
							<c:if test="${person.stability.communitycorrection== 1}">是</c:if>
							<c:if test="${person.stability.communitycorrection== 0}">否</c:if>
						</p>
						<p>社区矫正人员编号：${person.stability.rectifyid }</p>
						<p>原羁押场所：${person.stability.custodyplace }</p>
						<p>
							矫正类别：
							<c:if test="${person.stability.rectifytype == 1}">管制</c:if>
							<c:if test="${person.stability.rectifytype== 2}">缓刑</c:if>
							<c:if test="${person.stability.rectifytype== 3}">假释</c:if>
							<c:if test="${person.stability.rectifytype ==4}">暂予监外执行</c:if>
							<c:if test="${person.stability.rectifytype== 5}">剥夺政治权利</c:if>
						</p>
						<p>矫正开始日期：${person.stability.rectifystartdate }</p>
						<p>矫正结束日期：${person.stability.rectifyenddate }</p>
						<p>
							非法集资人员：
							<c:if test="${person.stability.illegalfund == 1}">是</c:if>
							<c:if test="${person.stability.illegalfund == 0}">否</c:if>
						</p>
						<p>
							两劳释放人员：
							<c:if test="${person.stability.reeducationreform== 1}">是</c:if>
							<c:if test="${person.stability.reeducationreform== 0}">否</c:if>
						</p>
						<p>
							累惯犯：
							<c:if test="${person.stability.recidivist== 1}">是</c:if>
							<c:if test="${person.stability.recidivist== 0}">否</c:if>
						</p>
						<p>
							"四史"情况：
							<c:if test="${person.stability.foursituation == 1}">吸毒史</c:if>
							<c:if test="${person.stability.foursituation== 2}">逃脱史</c:if>
							<c:if test="${person.stability.foursituation== 3}">自杀史</c:if>
							<c:if test="${person.stability.foursituation ==4}">袭警史</c:if>
						</p>
						<p>
							"三涉"情况：
							<c:if test="${person.stability.threesituation == 1}">涉毒</c:if>
							<c:if test="${person.stability.threesituation== 2}">涉黑</c:if>
							<c:if test="${person.stability.threesituation== 3}">涉枪</c:if>
						</p>
						<p>
							重点上访人员：
							<c:if test="${person.stability.focuspetitioners== 1}">是</c:if>
							<c:if test="${person.stability.focuspetitioners== 0}">否</c:if>
						</p>
						<p>
							涉核人员：
							<c:if test="${person.stability.nuclearpersonnel== 1}">是</c:if>
							<c:if test="${person.stability.nuclearpersonnel== 0}">否</c:if>
						</p>
						<p>
							参与邪教组织：
							<c:if test="${person.stability.involvedInCults== 1}">是</c:if>
							<c:if test="${person.stability.involvedInCults== 0}">否</c:if>
						</p>
					</div>
				</div>
				<!-- 证件信息 -->
				<div>
					<div class="pageFormContent" layoutH="120">
						<fieldset>
							<dl>
								<dt>文件标签：</dt>
								<dd>${person.fileCabinet.fileLabel}</dd>
							</dl>
						</fieldset>
						<fieldset>
							<div class="tabsContent" style="height: 200px;">
								<table id="filetable" class="list nowrap" width="90%">
									<thead>
										<tr>

											<th width="60">文件名称</th>
											<th width="10">文件类型</th>
											<th width="10">文件大小</th>
											<th width="60">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${person.fileCabinet.listFile}"
											var="fileMessage" varStatus="st">
											<tr class="unitBox">
												<td>${fileMessage.fileOldName }</td>
												 <td>
												 	<c:if test="${fileMessage.fileType =='1' }">身份证</c:if>
													<c:if test="${fileMessage.fileType =='2' }">户口本</c:if>
													<c:if test="${fileMessage.fileType =='3' }">病例或诊断证明</c:if> 
													<c:if test="${fileMessage.fileType =='4' }">个人申请</c:if>
													<c:if test="${fileMessage.fileType =='5' }">申报表</c:if> 
													<c:if test="${fileMessage.fileType =='6' }">残疾证明</c:if> 
													<c:if test="${fileMessage.fileType =='7' }">其他相关材料</c:if> 
												 </td>
												<td>${fileMessage.fileSize }(KB)</td>
												<td><a title="预览"
													href=" <c:url value='/oa/fileCabinet/previewFile' >  
														<c:param name="fileUrl" value='${fileMessage.fileWebUrl }' /></c:url>"
													width="800" mask="true" height="500" target="dialog"
													title="文件预览" rel="file">预览</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</fieldset>
						<fieldset>
							<dl class="nowrap">
								<dt>文件备注：</dt>
								<dd>
									<textarea name="fileComment" disabled="true" cols="60" rows="5">${person.fileCabinet.fileComment}</textarea>
								</dd>
							</dl>
						</fieldset>
					</div>
					<!-- <div class="formBar">
					<ul>
						<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
					</ul>
				</div> -->
				</div>
				<!--户籍详情  -->
				<div>
					<div class="pageFormContent" layoutH="120">
						<input type="hidden" name="id" value="${family.id}" /> <input
							type="hidden" name="houseId" value="${family.house.id}" /> <input
							type="hidden" name="groundId" value="${family.ground.id}" /> <input
							type="hidden" name="id" value="${family.id}" /> <input
							type="hidden" name="createTime" value="${family.createTime}" />
						<input type="hidden" name="userId" value="${family.userId}" /> <input
							type="hidden" name="orgId" value="${family.orgId}" />
						<p>户籍编号： ${family.householderID}</p>
						<p>户主姓名： ${family.householderName}</p>
						<p>户主电话：${family.householderContact}</p>
						<p>夫妻姓名：${family.spouseName}</p>
						<!--省市区三级联动  -->
						<p>户籍地 ： ${provicess.proName }&nbsp;${cityss.cityName }&nbsp;${disss.disName }
						</p>
						<p>户籍详细地址：${family.registryAddress}</p>
						<p>现住地： ${provices.proName }&nbsp;${citys.cityName }&nbsp;${diss.disName }
						</p>
						<p>现住详细地址：${family.currentAddress}</p>
						<p>
							户别类型：
							<c:if test="${family.householdType =='1'}">家庭户</c:if>
							<c:if test="${family.householdType =='2'}">集体户</c:if>
						</p>
						<p>
							双女户：
							<c:if test="${family.doublefemaleHouseholds =='1'}">是</c:if>
							<c:if test="${family.doublefemaleHouseholds =='0'}">否</c:if>
						</p>
						<p>
							独生子女家庭：
							<c:if test="${family.oneChildFamily =='1'}">是</c:if>
							<c:if test="${family.oneChildFamily =='0'}">否</c:if>
						</p>
						<p>
							计划内生二胎：
							<c:if test="${family.haveTwoChildren =='1'}">是</c:if>
							<c:if test="${family.haveTwoChildren =='0'}">否</c:if>
						</p>
						<p>
							优抚对象：
							<c:if test="${family.placestate =='1'}">是</c:if>
							<c:if test="${family.placestate =='0'}">否</c:if>
						</p>
						<p>
							优抚类型：
							<c:if test="${family.placetype =='1'}">军人军烈及家属</c:if>
							<c:if test="${family.placetype =='2'}">五保户</c:if>
						</p>
						<p>优抚对象其他类型： ${family.otherplace}</p>
						<p>
							困难家庭：
							<c:if test="${family.poorfamilystate =='1'}">是</c:if>
							<c:if test="${family.poorfamilystate =='0'}">否</c:if>
						</p>
						<p>
							致困原因：
							<c:if test="${family.poorfamilytype =='1'}">因病</c:if>
							<c:if test="${family.poorfamilytype =='2'}">因残</c:if>
						</p>
						<p>困难家庭其他原因：${family.nopoorfamilystate }</p>
						<p>
							低保户：
							<c:if test="${family.lowincomestate =='1'}">是</c:if>
							<c:if test="${family.lowincomestate =='0'}">否</c:if>
						</p>
						<p>
							低保类型：
							<c:if test="${family.lowincometype =='1'}">A类</c:if>
							<c:if test="${family.lowincometype =='2'}">B类</c:if>
							<c:if test="${family.lowincometype =='3'}">C类</c:if>
						</p>
						<p>低保户证号： ${family.lownumber }</p>
						<p>
							低收入：
							<c:if test="${family.lowincome =='1'}">是</c:if>
							<c:if test="${family.lowincome =='0'}">否</c:if>
						</p>
						<p>低收入证号：${family.lowincomenumber }</p>
						<p>家庭需求：${family.familydemand }</p>
						<p>提供服务：${family.provisionService }</p>
						<p>
							审核低保：
							<c:if test="${family.lowincomeAudit =='1'}">是</c:if>
							<c:if test="${family.lowincomeAudit =='0'}">否</c:if>
						</p>
						<p>低保享受金额 ： ${family.enjoyAmount }</p>
						<p>
							单亲家庭：
							<c:if test="${family.singleparent =='1'}">是</c:if>
							<c:if test="${family.singleparent =='0'}">否</c:if>
						</p>
						<p>参加老年体检人数： ${family.checkamount }</p>
						<p>参加孕前优生人数：${family.pregnantcheck }</p>
						<p>婴幼儿疫苗人数 ：${family.vaccinecheck }</p>
					</div>
				</div>
			</div>
			<div class="formBar">
				<ul>
					<li><div class="button">
							<div class="buttonContent">
								<button type="button" class="close">取消</button>
							</div>
						</div></li>
				</ul>
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