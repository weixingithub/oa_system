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
<title>户籍详情显示</title>
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
					<div class="pageFormContent" layoutH="120">
						<input type="hidden" name="id" value="${family.id}" />
						<input type="hidden" name="houseId" value="${family.house.id}" />
						<input type="hidden" name="groundId" value="${family.ground.id}" />
						<input type="hidden" name="id" value="${family.id}" />
						<input type="hidden" name="createTime" value="${family.createTime}" /> 
						<input type="hidden" name="userId" value="${family.userId}" /> 
						<input type="hidden" name="orgId"  value="${family.orgId}" />
						<p>户籍编号：${family.householderID}</p>
						<p>户主姓名： ${family.householderName}</p>
						<p>户主电话： ${family.householderContact}</p>
						<p>夫妻姓名： ${family.spouseName}</p>
						<p>
							<!--省市区三级联动  -->
							户籍地 ：
							 ${provice.proName }&nbsp;${city.cityName }&nbsp;${dis.disName }
						</p>
						<p>户籍详细地址： ${family.registryAddress}</p>
						<p>现住地： 
							<!--现居地的省市区  -->
							 ${provices.proName }&nbsp;${citys.cityName }&nbsp;${diss.disName }
						</p>
						<p>现住详细地址：${family.currentAddress}</p>
						<p>户别类型：
							<c:if test="${family.householdType =='1'}">家庭户</c:if> 
							<c:if test="${family.householdType =='2'}">集体户</c:if>
						</p>
						<p>双女户：
							<c:if test="${family.doublefemaleHouseholds =='1'}">是</c:if>
							<c:if test="${family.doublefemaleHouseholds =='0'}">否</c:if>
						</p>
						<p>独生子女家庭：
							<c:if test="${family.oneChildFamily =='1'}">是</c:if>
							<c:if test="${family.oneChildFamily =='0'}">否</c:if>
						</p>
						<p>计划内生二胎： 
							<c:if test="${family.haveTwoChildren =='1'}">是</c:if>
							<c:if test="${family.haveTwoChildren =='0'}">否</c:if>
						</p>
						<p>优抚对象：
							<c:if test="${family.placestate =='1'}">是</c:if>
							<c:if test="${family.placestate =='0'}">否</c:if>
						</p>
						<p>优抚类型： 
							<c:if test="${family.placetype =='1'}">军人军烈及家属</c:if>
							<c:if test="${family.placetype =='2'}">>五保户</c:if>
						</p>
						<p>优抚对象其他类型：${family.otherplace}</p>
						<p>困难家庭： 
							<c:if test="${family.poorfamilystate =='1'}">是</c:if>
							<c:if test="${family.poorfamilystate =='0'}">否</c:if>
						</p>
						<p>致困原因：
							<c:if test="${family.poorfamilytype =='1'}">因病</c:if>
							<c:if test="${family.poorfamilytype =='2'}">因残</c:if>
						</p>
						<p>困难家庭其他原因： ${family.nopoorfamilystate }</p>
						<p>低保户： 
							<c:if test="${family.lowincomestate =='1'}">是</c:if>
							<c:if test="${family.lowincomestate =='0'}">否</c:if>
						</p>
						<p>低保类型：
							<c:if test="${family.lowincometype =='1'}">A类</c:if>
							<c:if test="${family.lowincometype =='2'}">B类</c:if>
							<c:if test="${family.lowincometype =='3'}">C类</c:if>
						</p>
						<p>低保户证号： ${family.lownumber }</p>
						<p>低收入：
							<c:if test="${family.lowincome =='1'}">是</c:if>
							<c:if test="${family.lowincome =='0'}">否</c:if>
						</p>
						<p>低收入证号：${family.lowincomenumber } </p>
						<p>家庭需求：${family.familydemand }</p>
						<p>提供服务：${family.provisionService }</p>
						<p>审核低保： 
							<c:if test="${family.lowincomeAudit =='1'}">是</c:if>
							<c:if test="${family.lowincomeAudit =='0'}">否</c:if>
						</p>
						<p>低保享受金额 ： ${family.enjoyAmount }</p>
						<p>单亲家庭： 
							<c:if test="${family.singleparent =='1'}">是</c:if>
							<c:if test="${family.singleparent =='0'}">否</c:if>
						</p>
						<p>参加老年体检人数： ${family.checkamount }</p>
						<p>参加孕前优生人数：${family.pregnantcheck }</p>
						<p>婴幼儿疫苗人数 ：${family.vaccinecheck }</p>
					</div>
					<div class="formBar">
						<ul>
							<li><div class="button">
									<div class="buttonContent">
										<button type="button" class="close">关闭</button>
									</div>
								</div></li>
						</ul>
					</div>
				</div>
				<!-- 房屋信息 -->
				<div>
						<div class="pageFormContent" layoutH="120">
						   <input type="hidden" name="id" value="${family.house.id}" />
						   <p>房主姓名： ${family.house.housemaster}</p>
							<p>房屋性质：  
								<c:if test="${family.house.housenature =='1'}">私有</c:if>
								<c:if test="${family.house.housenature =='2'}">租住</c:if>
								<c:if test="${family.house.housenature =='3'}">常住户</c:if>
								<c:if test="${family.house.housenature =='4'}">廉租房</c:if>
							</p>
						   <p>房主联系电话：${family.house.housemasterphone}</p>
							<p>承重类型： 
								<c:if test="${family.house.hosetype =='1'}">钢筋混凝</c:if>
								<c:if test="${family.house.hosetype =='2'}">混合</c:if>
								<c:if test="${family.house.hosetype =='3'}">砖木</c:if>
								<c:if test="${family.house.hosetype =='4'}">其他</c:if>
							</p>
							<p>房屋产权：
								<c:if test="${family.house.housepremise =='1'}">公</c:if>
								<c:if test="${family.house.housepremise =='2'}">商品</c:if>
								<c:if test="${family.house.housepremise =='3'}">小产权</c:if>
							</p>
							<p>房屋建筑面积：${family.house.constructionArea}</p>
							<p>室：${family.house.houseroom}</p>
							<p>厅：${family.house.househall}</p>
							<p>卫：${family.house.housetoilet}</p>
							<p>防范设施： 
								<c:if test="${family.house.housefacility =='1'}">防盗门</c:if>
								<c:if test="${family.house.housefacility =='2'}">防护网</c:if>
								<c:if test="${family.house.housefacility =='3'}">防盗门和防护网</c:if>
							</p>
							<p>建成时间：${family.house.housedatatime}</p>
							<p>房屋受灾性质：
								<c:if test="${family.house.housedisasternature =='1'}">地质灾害点</c:if>
								<c:if test="${family.house.housedisasternature =='2'}">危房危害点</c:if>
							</p>
						</div>
						<div class="formBar">
							<ul>
								<li><div class="button">
										<div class="buttonContent">
											<button type="button" class="close">关闭</button>
										</div>
									</div></li>
							</ul>
						</div>
				</div>
				<!-- 土地信息 -->
				<div>
					<div class="pageFormContent" layoutH="120">
							 <input type="hidden" name="id" value="${family.ground.id}" />
						<p>耕地面积： ${family.ground.arableArea}</p>
						<p>直补金额：${family.ground.subsidyAmount}</p>
						<p>林地面积： ${family.ground.woodlandArea}</p>
						<p>退耕还林面积：${family.ground.farmlandtoForestArea}</p>
						<p>补贴金额：${family.ground.compensation}</p>
					</div>
					<div class="formBar">
						<ul>
							<li><div class="button">
									<div class="buttonContent">
										<button type="button" class="close">关闭</button>
									</div>
								</div></li>
						</ul>
					</div>
				</div>
				<!-- 家庭成员 -->
				<div>
						<div class="pageFormContent" layoutH="120">
							<input type="hidden" name="familyId"  value="${family.id}">
							<input type="hidden" id="familymemberjson" name="familymemberjson">
							<table id="familytable" class="list nowrap" width="70%">
								<thead>
									<tr>
										<th width="80">姓名</th>
										<th width="120">与户主关系</th>
										<th width="150">身份证号</th>
										<th width="80">联系方式</th>
										<th width="80">操作</th>
									</tr>
							  </thead>
								<tbody>
								<c:forEach items="${family.person}" var="person" varStatus="st">
									<tr>
										<td>${person.name}</td>
										<td>
											<c:if test="${person.ralation =='1' }">户主</c:if>
											<c:if test="${person.ralation =='2' }">配偶</c:if>
											<c:if test="${person.ralation =='3' }">子女</c:if>
											<c:if test="${person.ralation =='4' }">父母</c:if>
											<c:if test="${person.ralation =='5' }">岳父母、公婆</c:if>
											<c:if test="${person.ralation =='6' }">媳婿</c:if>
											<c:if test="${person.ralation =='7' }">兄弟姐妹</c:if>
											<c:if test="${person.ralation =='8' }">孙子女</c:if>
											<c:if test="${person.ralation =='9' }">其它</c:if>
										</td>
										<td>${person.idnumber }</td>
										<td>${person.contact }</td>
										<td><a href="<%=basepath%>/oa/person/personDetails?id=${person.id}" width="800" mask="true" height="500" target="dialog" title="人口详情" rel="person">详情</a></td> 
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="formBar">
							<ul>
								<li><div class="button">
										<div class="buttonContent">
											<button type="button" class="close">关闭</button>
										</div>
									</div></li>
							</ul>
						</div>
				</div>
				<!-- 证件信息 -->
				<div>
					<div class="pageFormContent" layoutH="120">
						<fieldset>
							<dl>
								<dt>文件标签：</dt>
								<dd>${family.fileCabinet.fileLabel}</dd>
							</dl>
						</fieldset>
						<fieldset>
							<div class="tabsContent" style="height: 200px;">
								<table id="filetable" class="list nowrap"  width="90%">
									<thead>
										<tr>
											
											<th width="60">文件名称</th>
											<th width="10">文件类型</th>
											<th width="10">文件大小</th>
											<th width="60">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${family.fileCabinet.listFile}" var="fileMessage" varStatus="st">
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
											 <td>
											 	 <a title="预览"  href=" <c:url value='/oa/fileCabinet/previewFile' >  
														<c:param name="fileUrl" value='${fileMessage.fileWebUrl }' /></c:url>" 
												 width="800" mask="true" height="500" target="dialog" title="文件预览" rel="file" >预览</a>
											 </td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</fieldset>
						<fieldset>
							<dl class="nowrap">
								<dt>文件备注：</dt>
								<dd><textarea name="fileComment" disabled="true"cols="60" rows="5">${family.fileCabinet.fileComment}</textarea></dd>
							</dl>
						</fieldset>	
					</div>
					<div class="formBar">
						<ul>
							<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
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