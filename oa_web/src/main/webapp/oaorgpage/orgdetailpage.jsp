<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String basePath = request.getContextPath();   
    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style type="text/css">
	ul.rightTools {float:right; display:block;}
	ul.rightTools li{float:left; display:block; margin-left:5px}
</style>
<body>
<div class="pageContent" style="padding:5px">
	<div class="panel" defH="40">
		<h1>操作</h1>
		<div>
		           当前机构名称：<strong>${org.orgName }</strong>
			<ul class="rightTools">
			  <c:if test="${sessionScope.modelflag.add }">
				<li><a class="button" target="dialog" href="<%=basePath %>/oa/org/gotoAddOrg?parentId=${org.parentId}&nodeId=${org.nodeId}" mask="true"><span>创建同级机构</span></a></li>
				<li><a class="button" target="dialog" href="<%=basePath %>/oa/org/gotoAddOrg?nodeId=${org.nodeId}" mask="true"><span>创建下级机构</span></a></li>
			  </c:if>
			   <c:if test="${sessionScope.modelflag.delete }">
				<li><a class="button" href="<%=basePath %>/oa/org/delOaOrg?ids=${org.orgId }&parentId=${org.parentId}&orgName=${org.orgName}" target="ajaxTodo" title="删除此机构会将其子机构全部删除，确定删除?"><span>删除</span></a></li>
				</c:if>
			</ul>
		</div>
	</div>
	<div class="divider"></div>
	<div class="tabs">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>机构基本信息</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<!-- 机构基本信息 -->
			<div style="background:#fff">
				<form method="post" action="<%=basePath %>/oa/org/addOaOrg" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
				<input name="orgId" value="${org.orgId }" type="hidden"/>
			    <input name="isParent" value="${org.isParent }" type="hidden"/>
			    <input name="orgPCD" value="${org.orgPCD }" type="hidden"/>
			    <input name="nodeId" value="${org.nodeId }" type="hidden"/>
			    <input name="parentId" value="${org.parentId }" type="hidden"/>
			    <input id="optype" type="hidden"  name="optype"  value="update"/>
				<div class="pageFormContent"  layoutH="255" >
					<p>
					    <dl class="nowrap">
						<dt>机构编号：</dt>
						<dd>
						<input name="orgNum" type="text" size="30" value="${org.orgNum }" />
						</dd>
						</dl>
					</p>
					<p>
					   <dl class="nowrap">
						<dt>机构名称：</dt>
						<dd>
						<input name="orgName" class="required" type="text" size="30" value="${org.orgName }" alt="请输入机构名称"/>
					    </dd>
					   </dl>
					</p>
					<p>
					<dl class="nowrap">
					  <dt>机构地址：</dt>
					  <dd>
						<select class="combox" name="province" ref="w_combox_city" refUrl="<%=basePath %>/oa/area/city?pvalue={value}">
							<option value="0">所有省市</option>
							<c:forEach items="${plist}"  var="province">
							  <option  <c:if test="${province.proID == proid}">selected</c:if> value="${province.proID }">${province.proName }</option>
							</c:forEach>
						</select>
						<select class="combox" name="city" id="w_combox_city" ref="w_combox_region" refUrl="<%=basePath %>/oa/area/region?cvalue={value}">
							<option value="0">所有城市</option>
							<c:if test="${!empty cityid}">
							<c:forEach items="${clist }"  var="city">
							  <option  <c:if test="${city.cityID == cityid}">selected</c:if> value="${city.cityID }">${city.cityName }</option>
							</c:forEach>
							</c:if>
						</select>
						<select class="combox" name="region" id="w_combox_region">
							<option value="0">所有区县</option>
							<c:if test="${!empty disid}">
							<c:forEach items="${dlist }"  var="district">
							  <option  <c:if test="${district.id == disid}">selected</c:if> value="${district.id }">${district.disName }</option>
							</c:forEach>
							</c:if>
						</select>
						</dd>
					</dl>
			    	</p>
					<p>
					   <dl class="nowrap">
						<dt>详细地址：</dt>
						<dd>
						<input name="orgAddress" type="text" size="60" value="${org.orgAddress }"  />
					     </dl>
					   </dl>
					</p>
					<p>
					  <dl class="nowrap">
						  <dt>机构备注：</dt>
						  <dd><textarea name="orgRemark" cols="80" rows="2" >${org.orgRemark }</textarea></dd>
				      </dl>
					</p>
				</div>
				<div class="formBar">
					<ul>
					<c:if test="${sessionScope.modelflag.editor }">
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit">修改</button></div></div></li>
					</c:if>
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