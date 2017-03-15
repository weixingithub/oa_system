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
						
						<li><a href="javascript:;"><span>户籍信息</span></a></li>
						<c:if test="${family.id !=null }">
							<li><a href="javascript:;"><span>房屋信息</span></a></li>
							<li><a href="javascript:;"><span>土地信息</span></a></li>
							<li><a href="javascript:;"><span>家庭成员</span></a></li>
							<li><a href="javascript:;"><span>证件信息</span></a></li>
						</c:if>
					</ul>
				</div>
			</div>
			<div class="tabsContent">
				<div>
					<!-- 户籍信息 -->
					<form method="post" action="<%=basepath%>/oa/population/addAndUpdateFamily"
						class="pageForm required-validate"
						onsubmit="return validateCallback(this, navTabAjaxDone);">
						<div class="pageFormContent" layoutH="120">
							<input type="hidden" name="id" value="${family.id}" />
							<input type="hidden" name="houseId" value="${family.house.id}" />
							<input type="hidden" name="groundId" value="${family.ground.id}" />
							<input type="hidden" name="fileCabinetId" value="${family.fileCabinet.id}" />
							<input type="hidden" name="id" value="${family.id}" />
							<input type="hidden" name="createTime" value="${family.createTime}" /> 
							<input type="hidden" name="userId" value="${family.userId}" /> 
							<p>
								<label>户籍编号：</label> 
								  <input name="householderID" type="text" size="20" class="required" value="${family.householderID}" 
								  remote="<%=basepath%>/oa/population/verifyHouseholderID?orgId=${family.orgId}&id=${family.id}&oldhouseholderID=${family.householderID}"
								  title="户籍编号重复"/>
							</p>
							<p>
								<label>户主姓名：</label> 
								<input type="hidden" name="personOldId" value="${hPerson.id}" />
								<input type="hidden" name="personId" value="${hPerson.id}" />
								<input type="text"  name="personName" readonly="readonly" class="required"  value="${hPerson.name}"/>
								<a class="btnLook" href="<%=basepath%>/oa/person/getFamilyPersonPage?conditioned=notFamily" lookupGroup="">查找带回</a>
							</p>
							<p>
								<label>户主电话：</label> <input name="personContact" type="text" readonly="readonly" value="${hPerson.contact}" />
							</p>
							<p>
								<label>夫妻姓名：</label> <input name="spouseName" type="text"
									size="30" value="${family.spouseName}" />
							</p>
							<p>
								<label>所属社区：</label>
								<input name="orgId" value="${family.orgId }" type="hidden"/>
								<input name="orgPId" value="${family.orgPid }" type="hidden"/>
								<input class="required" name="orgName" type="text" value="${family.orgName}" readonly/>
								<a  class="btnLook" href="<%=basepath %>/oaorgpage/orgtree.jsp" lookupGroup="">选择社区</a>	
							</p>
							<p>
								<!--省市区三级联动  -->
								<label>户籍地 ：</label> <%-- <input name="registryPlace" type="text"
									size="30" value="${family.registryPlace}" /> --%>
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
								<label>户籍详细地址：</label> <input name="registryAddress" type="text"
									size="30" value="${family.registryAddress}" />
							</p>
							<p>
								<label>现住地：</label> <%-- <input name="currentPlace" class="required"
									type="text" size="30" value="${family.currentPlace}" /> --%>
								<!--现居地的省市区  -->
								<select class="combox" name="provinces" ref="oa_p_combox_city" ref="oa_p_combox_region" refUrl="<%=basepath%>/oa/area/city?pvalue={value}">
									<option value="0">所有省市</option>
									<c:forEach items="${plist}"  var="province">
									  <option  <c:if test="${province.proID == proids}">selected</c:if> value="${province.proID }">${province.proName }</option>
									</c:forEach>
								</select>
								<select class="combox" name="citys" id="oa_p_combox_city" ref="oa_p_combox_region" refUrl="<%=basepath%>/oa/area/region?cvalue={value}">
									<option value="0">所有城市</option>
									<c:if test="${!empty cityids}">
									<c:forEach items="${clists }"  var="city">
									  <option  <c:if test="${city.cityID == cityids}">selected</c:if> value="${city.cityID }">${city.cityName }</option>
									</c:forEach>
									</c:if>
								</select>
								<select class="combox" name="regions" id="oa_p_combox_region">
									<option value="0">所有区县</option>
									<c:if test="${!empty disids}">
									<c:forEach items="${dlists }"  var="district">
									  <option  <c:if test="${district.id == disids}">selected</c:if> value="${district.id }">${district.disName }</option>
									</c:forEach>
									</c:if>
								</select>	
							</p>
							<p>
								<label>现住详细地址：</label> <input name="currentAddress"
									class="required" type="text" size="30" value="${family.currentAddress}" />
							</p>
							<p>
								<label>户别类型：</label> <select name="householdType" class="combox">
									<option value=""  <c:if test="${family.householdType ==''}">selected</c:if> >请选择</option>
									<option value="1" <c:if test="${family.householdType =='1'}">selected</c:if> >家庭户</option>
									<option value="2" <c:if test="${family.householdType =='2'}">selected</c:if> >集体户</option>
								</select>
							</p>
							<p>
								<label>双女户：</label> <select name="doublefemaleHouseholds" class="combox">
									<option value=""  <c:if test="${family.doublefemaleHouseholds ==''}">selected</c:if>>请选择</option>
									<option value="1" <c:if test="${family.doublefemaleHouseholds =='1'}">selected</c:if>>是</option>
									<option value="0" <c:if test="${family.doublefemaleHouseholds =='0'}">selected</c:if>>否</option>
								</select>
							</p>
							<p>
								<label>独生子女家庭：</label> <select name="oneChildFamily"
									class="combox">
									<option value=""  <c:if test="${family.oneChildFamily ==''}">selected</c:if>>请选择</option>
									<option value="1" <c:if test="${family.oneChildFamily =='1'}">selected</c:if>>是</option>
									<option value="0" <c:if test="${family.oneChildFamily =='0'}">selected</c:if>>否</option>
								</select>
							</p>
							<p>
								<label>计划内生二胎：</label> <select name="haveTwoChildren"
									class="combox">
									<option value=""  <c:if test="${family.haveTwoChildren ==''}">selected</c:if>>请选择</option>
									<option value="1" <c:if test="${family.haveTwoChildren =='1'}">selected</c:if>>是</option>
									<option value="0" <c:if test="${family.haveTwoChildren =='0'}">selected</c:if>>否</option>
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
								<label>优抚对象其他类型：</label> <input name="otherplace" type="text"
									size="20" value="${family.otherplace}" />
							</p>
							<p>
								<label>困难家庭：</label> <select name="poorfamilystate"
									class="combox">
									<option value=""  <c:if test="${family.poorfamilystate ==''}">selected</c:if>>请选择</option>
									<option value="1" <c:if test="${family.poorfamilystate =='1'}">selected</c:if>>是</option>
									<option value="0" <c:if test="${family.poorfamilystate =='0'}">selected</c:if>>否</option>
								</select>
							</p>
							<p>
								<label>致困原因：</label> <select name="poorfamilytype"
									class="combox">
									<option value=""  <c:if test="${family.poorfamilytype ==''}">selected</c:if>>请选择</option>
									<option value="1" <c:if test="${family.poorfamilytype =='1'}">selected</c:if>>因病</option>
									<option value="2" <c:if test="${family.poorfamilytype =='2'}">selected</c:if>>因残</option>
								</select>
							</p>
							<p>
								<label>困难家庭其他原因：</label> <input name="nopoorfamilystate"
									type="text" size="30" value="${family.nopoorfamilystate }" />
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
								<label>低保户证号：</label> <input name="lownumber" type="text"
									size="20" value="${family.lownumber }" />
							</p>
							<p>
								<label>低收入：</label> <select name="lowincome" class="combox">
									<option value=""  <c:if test="${family.lowincome ==''}">selected</c:if>>请选择</option>
									<option value="1" <c:if test="${family.lowincome =='1'}">selected</c:if>>是</option>
									<option value="0" <c:if test="${family.lowincome =='0'}">selected</c:if>>否</option>
								</select>
							</p>
							<p>
								<label>低收入证号：</label> <input type="text" name="lowincomenumber"
									size="20" value="${family.lowincomenumber }" />
							</p>
							<p>
								<label>家庭需求：</label> <input type="text" name="familydemand"
									size="20" value="${family.familydemand }" />
							</p>
							<p>
								<label>提供服务：</label> <input type="text" name="provisionService"
									size="20" value="${family.provisionService }" />
							</p>
							<p>
								<label>审核低保：</label> <select name="lowincomeAudit"
									class="combox">
									<option value=""  <c:if test="${family.lowincomeAudit ==''}">selected</c:if>>请选择</option>
									<option value="1" <c:if test="${family.lowincomeAudit =='1'}">selected</c:if>>是</option>
									<option value="0" <c:if test="${family.lowincomeAudit =='0'}">selected</c:if>>否</option>
								</select>
							</p>
							<p>
								<label>低保享受金额 ：</label> <input type="text" name="enjoyAmount"
									class="number" size="20" value="${family.enjoyAmount }" /><span class="unit">元/季度</span>
							</p>
							<p>
								<label>单亲家庭：</label> <select name="singleparent" class="combox">
									<option value=""  <c:if test="${family.singleparent ==''}">selected</c:if>>请选择</option>
									<option value="1" <c:if test="${family.singleparent =='1'}">selected</c:if>>是</option>
									<option value="0" <c:if test="${family.singleparent =='0'}">selected</c:if>>否</option>
								</select>
							</p>
							<p>
								<label>参加老年体检人数：</label> <input type="text" name="checkamount"
									class="digits" size="10" value="${family.checkamount }" /><span class="unit">/个</span>
							</p>
							<p>
								<label>参加孕前优生人数：</label> <input type="text" name="pregnantcheck"
									class="digits" size="10" value="${family.pregnantcheck }" /><span class="unit">/个</span>
							</p>
							<p>
								<label>婴幼儿疫苗人数 ：</label> <input type="text" name="vaccinecheck"
									class="digits" size="10" value="${family.vaccinecheck }" /><span class="unit">/个</span>
							</p>
						</div>
						<div class="formBar">
							<ul>
								<li><div class="buttonActive">
										<div class="buttonContent">
											<button type="submit">保存</button>
										</div>
									</div></li>
								<li><div class="button">
										<div class="buttonContent">
											<button type="button" class="close">关闭</button>
										</div>
									</div></li>
							</ul>
						</div>
					</form>
				</div>
				<!-- 房屋信息 -->
				<div>
					<form method="post" action="<%=basepath%>/oa/population/updateFamilyByHouse"
						class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
						<div class="pageFormContent" layoutH="120">
						   <input type="hidden" name="id" value="${family.house.id}" />
						   <p>
								<label>房主姓名：</label> 
								<input name="housemaster" type="text" size="30" value="${family.house.housemaster}" />
							</p>
							<p>
								<label>房屋性质：</label> <select name="housenature" class="combox">
									<option value="" <c:if test="${family.house.housenature ==''}">selected</c:if>>请选择</option>
									<option value="1"<c:if test="${family.house.housenature =='1'}">selected</c:if>>私有</option>
									<option value="2"<c:if test="${family.house.housenature =='2'}">selected</c:if>>租住</option>
									<option value="3"<c:if test="${family.house.housenature =='3'}">selected</c:if>>常住户</option>
									<option value="4"<c:if test="${family.house.housenature =='4'}">selected</c:if>>廉租房</option>
								</select>
							</p>
						   <p>
								<label>房主联系电话：</label> 
								<input name="housemasterphone" type="text" size="30" value="${family.house.housemasterphone}"  />
							</p>
							<p>
								<label>承重类型：</label> <select name="hosetype" class="combox">
									<option value="" <c:if test="${family.house.hosetype ==''}">selected</c:if>>请选择</option>
									<option value="1"<c:if test="${family.house.hosetype =='1'}">selected</c:if>>钢筋混凝</option>
									<option value="2"<c:if test="${family.house.hosetype =='2'}">selected</c:if>>混合</option>
									<option value="3"<c:if test="${family.house.hosetype =='3'}">selected</c:if>>砖木</option>
									<option value="4"<c:if test="${family.house.hosetype =='4'}">selected</c:if>>其他</option>
								</select>
							</p>
							<p>
								<label>房屋产权：</label> <select name="housepremise" class="combox">
									<option value="" <c:if test="${family.house.housepremise ==''}">selected</c:if>>请选择</option>
									<option value="1"<c:if test="${family.house.housepremise =='1'}">selected</c:if>>公</option>
									<option value="2"<c:if test="${family.house.housepremise =='2'}">selected</c:if>>商品</option>
									<option value="3"<c:if test="${family.house.housepremise =='3'}">selected</c:if>>小产权</option>
								</select>
							</p>
							<p>
								<label>房屋建筑面积：</label> 
								<input type="text" name="constructionArea"class="digits" size="10" value="${family.house.constructionArea}" /><span class="unit">/平米</span>
							</p>
							<p>
								<label>室：</label> 
								<input type="text" name="houseroom"class="digits" size="10" value="${family.house.houseroom}" /><span class="unit">/室</span>
							</p>
							<p>
								<label>厅：</label> 
								<input type="text" name="househall"class="digits" size="10" value="${family.house.househall}" /><span class="unit">/厅</span>
							</p>
							<p>
								<label>卫：</label> 
								<input type="text" name="housetoilet"class="digits" size="10" value="${family.house.housetoilet}" /><span class="unit">/卫</span>
							</p>
							<p>
								<label>防范设施：</label> <select name="housefacility" class="combox">
									<option value="" <c:if test="${family.house.housefacility ==''}">selected</c:if>>请选择</option>
									<option value="1"<c:if test="${family.house.housefacility =='1'}">selected</c:if>>防盗门</option>
									<option value="2"<c:if test="${family.house.housefacility =='2'}">selected</c:if>>防护网</option>
									<option value="3"<c:if test="${family.house.housefacility =='3'}">selected</c:if>>防盗门和防护网</option>
								</select>
							</p>
							<p>
								<label>建成时间：</label>
								<input type="text" name="housedatatime" class="date" size="20" value="${family.house.housedatatime}" /><a class="inputDateButton" href="javascript:;">选择</a>
							</p>
							<p>
								<label>房屋受灾性质：</label> <select name="housedisasternature" class="combox">
									<option value="" <c:if test="${family.house.housedisasternature ==''}">selected</c:if>>请选择</option>
									<option value="1"<c:if test="${family.house.housedisasternature =='1'}">selected</c:if>>地质灾害点</option>
									<option value="2"<c:if test="${family.house.housedisasternature =='2'}">selected</c:if>>危房危害点</option>
								</select>
							</p>
						</div>
						<div class="formBar">
							<ul>
								<li><div class="buttonActive">
										<div class="buttonContent">
											<button type="submit">保存</button>
										</div>
									</div></li>
								<li><div class="button">
										<div class="buttonContent">
											<button type="button" class="close">关闭</button>
										</div>
									</div></li>
							</ul>
						</div>
					</form>
				</div>
				<!-- 土地信息 -->
				<div>
					<form method="post" action="<%=basepath%>/oa/population/updateFamilyByGround"
						class="pageForm required-validate"
						onsubmit="return validateCallback(this, navTabAjaxDone);">
						<div class="pageFormContent" layoutH="120">
								 <input type="hidden" name="id" value="${family.ground.id}" />
							<p>
								<label>耕地面积：</label> 
								<input type="text" name="arableArea"class="number" size="20" value="${family.ground.arableArea}" /><span class="unit">/亩</span>
							</p>
							<p>
								<label>直补金额：</label> 
								<input type="text" name="subsidyAmount"class="number" size="20" value="${family.ground.subsidyAmount}" /><span class="unit">/元</span>
							</p>
							<p>
								<label>林地面积：</label> 
								<input type="text" name="woodlandArea"class="number" size="20" value="${family.ground.woodlandArea}" /><span class="unit">/亩</span>
							</p>
							<p>
								<label>退耕还林面积：</label> 
								<input type="text" name="farmlandtoForestArea"class="number"" size="20" value="${family.ground.farmlandtoForestArea}" /><span class="unit">/亩</span>
							</p>
							<p>
								<label>补贴金额：</label> 
								<input type="text" name="compensation"class="number" size="20" value="${family.ground.compensation}" /><span class="unit">/元</span>
							</p>
						</div>
						<div class="formBar">
							<ul>
								<li><div class="buttonActive">
										<div class="buttonContent">
											<button type="submit">保存</button>
										</div>
									</div></li>
								<li><div class="button">
										<div class="buttonContent">
											<button type="button" class="close">关闭</button>
										</div>
									</div></li>
							</ul>
						</div>
					</form>
				</div>
				<!-- 家庭成员 -->
				<div>
					<form method="post" action="<%=basepath%>/oa/population/updateFamilyByPerson"
						class="pageForm required-validate"
						onsubmit="return validateCallback(this, navTabAjaxDone);">
						<div class="pageFormContent" layoutH="120">
							<input type="hidden" name="familyId"  value="${family.id}">
							<input type="hidden" id="familymemberjson" name="familymemberjson">
							<table id="familytable" class="list nowrap itemDetail" addButton="添加家庭成员" width="70%">
								<thead>
									<tr>
										<th type="lookup"name="items.personName[#index#]" lookupGroup="items" fieldClass="required" lookupUrl="<%=basepath%>/oa/person/getFamilyPersonPage?conditioned=notHouseholderAll" lookupPk="personId" suggestFields="personId,personName,personContact" size="12">姓名</th>
										<th type="enum" name="items.ralation[#index#]"  enumUrl="<%=basepath%>/population/family_ralation.html" size="12">与户主关系</th>
										<th type="text" name="items.personIdnumber[#index#]" size="20" fieldClass="required">身份证号</th>
										<th type="text" name="items.personContact[#index#]" size="20" fieldClass="required">联系方式</th>
										<th type="del" width="60">操作</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${family.person}" var="person" varStatus="st">
									<c:if test="${person.ralation =='1' }">
											<tr class="unitBox">
											<td>
												<input type="hidden" name="items.personId[${st.index}]"  value="${person.id }">
											    <input type="text" name="items.personName[${st.index}]" value="${person.name }"  size="12" class="required" readonly="true"> 
											</td>
											<td>
												<select class="combox" name="items.ralation[${st.index}]">
													<option value="1" <c:if test="${person.ralation =='1' }">selected</c:if>>户主</option>
												</select>
											</td>
											<td><input type="text" name="items.personIdnumber[${st.index}]" value="${person.idnumber }" size="20" class="required" readonly="true"></td>
											<td><input type="text" name="items.personContact[${st.index}]" value="${person.contact }" size="20" class="required" readonly="true"></td>
											<td></td>
										</tr>
									</c:if>
									<c:if test="${person.ralation !='1' }">
											<tr class="unitBox">
											<td>
												<input type="hidden" name="items.personId[${st.index}]"  value="${person.id }">
											    <input type="text" name="items.personName[${st.index}]" value="${person.name }"  size="12" class="required">
											    <a class="btnLook" href="<%=basepath%>/oa/person/getFamilyPersonPage?conditioned=notHouseholderAll" lookupgroup="items" autocomplete="off" lookupPk="personName" suggestFields="personId,personName,personIdnumber,personContact" title="查找带回">查找带回</a>
											</td>
											<td>
												<select class="combox" name="items.ralation[${st.index}]">
												<!-- 
													<option value="1" <c:if test="${person.ralation =='1' }">selected</c:if>>户主</option>
												 -->			
													<option value="2" <c:if test="${person.ralation =='2' }">selected</c:if>>配偶</option>
													<option value="3" <c:if test="${person.ralation =='3' }">selected</c:if>>子女</option>
													<option value="4" <c:if test="${person.ralation =='4' }">selected</c:if>>父母</option>
													<option value="5" <c:if test="${person.ralation =='5' }">selected</c:if>>岳父母、公婆</option>
													<option value="6" <c:if test="${person.ralation =='6' }">selected</c:if>>媳婿</option>
													<option value="7" <c:if test="${person.ralation =='7' }">selected</c:if>>兄弟姐妹</option>
													<option value="8" <c:if test="${person.ralation =='8' }">selected</c:if>>孙子女</option>
													<option value="9" <c:if test="${person.ralation =='9' }">selected</c:if>>其它</option>
												</select>
											</td>
											<td><input type="text" name="items.personIdnumber[${st.index}]" value="${person.idnumber }" size="20" class="required" readonly="true"></td>
											<td><input type="text" name="items.personContact[${st.index}]" value="${person.contact }" size="20" class="required" readonly="true"></td>
											<td><a href="javascript:void(0)" class="btnDel ">删除</a></td>
										</tr>
									</c:if>
								</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="formBar">
							<ul>
								<li><div class="buttonActive">
										<div class="buttonContent">
											<button type="submit" onclick="familydata();">保存</button>
										</div>
									</div></li>
								<li><div class="button">
										<div class="buttonContent">
											<button type="button" class="close">关闭</button>
										</div>
									</div></li>
							</ul>
						</div>
					</form>
				</div>
				<!-- 证件信息 -->
				<div>
					<form method="post" action="<%=basepath%>/oa/fileCabinet/updateFileCabinet"
						class="pageForm required-validate"
						onsubmit="return validateCallback(this, navTabAjaxDone);">
						<input type="hidden" name="id" value="${family.fileCabinet.id}" />
						<input type="hidden" id="filejson" name="filejson">
						<input type="hidden" id="fileCabinetId" name="fileCabinetId" value="${family.fileCabinet.id}">
						<div class="pageFormContent" layoutH="120">
							<fieldset>
								<dl>
									<dt>文件标签：</dt>
									<dd><input name="fileLabel" type="text" class="required" value="${family.fileCabinet.fileLabel}"/></dd>
								</dl>
							</fieldset>
							<fieldset>
								<div class="tabsContent" style="height: 200px;">
									<table id="filetable" class="list nowrap itemDetail" addButton="添加附件" width="90%">
										<thead>
											<tr>
												<th type="attach" name="items.fileOldName[#index#]"  lookupGroup="items" lookupUrl="<%=basepath%>/filemanager/file_singleUpload.jsp?fileID=family&fileCabinetId=${family.fileCabinet.id}" lookupPk="fileNewName" suggestFields="fileOldName,fileNewName,fileSize,fileWebUrl" size="30">从附件</th>
												<th type="enum" name="items.fileType[#index#]"  enumUrl="<%=basepath%>/filemanager/file_type.html" size="12">文件类型</th>
												<th type="text" name="items.fileSize[#index#]"  size="8">文件大小</th>
												<th type="text" name="items.fileWebUrl[#index#]"   size="20" >文件web路径</th>
												<th type="del" width="60">操作</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${family.fileCabinet.listFile}" var="fileMessage" varStatus="st">
												<tr class="unitBox">
												 <td >
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
												 <td><a href="javascript:void(0);"  class="btnDel">删除</a></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</fieldset>
							<fieldset>
								<dl class="nowrap">
									<dt>文件备注：</dt>
									<dd><textarea name="fileComment" class="required" cols="60" rows="5">${family.fileCabinet.fileComment}</textarea></dd>
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
											<button type="button" class="close">关闭</button>
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
</body>
<script type="text/javascript">
//异步删除文件
function deletefile(trobjs){
	var falg = false;
	var tdobjs = trobjs[0].getElementsByTagName("td");
	var inputobj = tdobjs[0].getElementsByTagName("input");
	if(inputobj[0].name.indexOf("fileNewName")!=-1){
		var selobj = tdobjs[1].getElementsByTagName("select");
		var inputa = tdobjs[1].getElementsByTagName("input");
		var inputb = tdobjs[2].getElementsByTagName("input");
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