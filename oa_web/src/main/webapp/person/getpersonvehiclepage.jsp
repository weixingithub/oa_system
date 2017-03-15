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
<title>人口车辆信息</title>
</head>
<body>
	<form id="pagerForm" method="post" action="<%=basepath %>/oa/vehicle/getPersonVehiclePage">
		<input type="hidden" name="pageNum" value="${page.pageNo }" />
	    <input type="hidden" name="numPerPage" value="${page.pageSize }" /> 
	    <input type="hidden" name="orderField" value="${orderField}" />
	    <input type="hidden" name="orderDirection" value="${orderDirection}" />
	    <input type="hidden" name="name" value="${vehicle.owners }" />
	</form>
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="<%=basepath %>/oa/vehicle/getPersonVehiclePage" method="post">
		<input type="hidden" name="pageNum" value="${page.pageNo }" />
	    	<input type="hidden" name="numPerPage" value="${page.pageSize }" /> 
	    	<input type="hidden" name="orderField" value="${orderField}" />
	    	<input type="hidden" name="orderDirection" value="${orderDirection}" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>车主：<input type="text" name="owners"
							value="${vehicle.owners }" />
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive">
								<div class="buttonContent">
									<button type="submit">检索</button>
								 </div>
							</div>
					    </li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<c:if test="${sessionScope.modelflag.add }">
					<li><a class="add" href="<%=basepath %>/person/vehicle_add.jsp"  target="dialog" mask="true" title="添加" width="1000" height="500" rel="vehicle" ><span>添加</span></a></li>
				</c:if>
				<c:if test="${sessionScope.modelflag.delete }">
					<li><a class="delete" href="<%=basepath %>/oa/vehicle/delPersonVehicle?ids={sid_vehicle}&number={number_vehicle}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
				</c:if>
				<c:if test="${sessionScope.modelflag.editor }">
					<li><a class="edit" href="<%=basepath %>/oa/vehicle/findPersonVehicleById?id={sid_vehicle}" mask="true" target="dialog" rel="vehicle"><span>修改</span></a></li>
				</c:if> 
				<li class="line">line</li>
			</ul>
		</div>
		<div layoutH="120">
			<table class="list" width="100%">
				<thead>
					<tr>
						    <th width="5%" >序号</th>
							<th width="20%">车牌号</th>
							<th width="20%">车辆类型</th>
							<th width="20%">车主</th>
							<th width="20%">车主联系方式</th>
						    <th width="15%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.result }" var="vehicle" varStatus="st">
						<tr target="sid_vehicle" rel="${vehicle.id }">
							<td align="center">${st.index+1+page.pageSize*(page.pageNo-1)}</td>
							<td target="number_vehicle" rel="${vehicle.licenseplatenumber }">${vehicle.licenseplatenumber }</td>
							<td>${vehicle.vehicletype }</td>
			                <td>${vehicle.owners}</td>
			                <td>${vehicle.person_contact}</td>
			                <td>
			                  	<c:if test="${sessionScope.modelflag.delete }">
			                     <a title="删除" href="<%=basepath %>/oa/vehicle/delPersonVehicle?ids=${vehicle.id}&number=${vehicle.licenseplatenumber }" class="btnDel" target="ajaxTodo">删除</a>
			                    </c:if>
			                     <c:if test="${sessionScope.modelflag.editor}">
								 <a title="编辑" href="<%=basepath %>/oa/vehicle/findPersonVehicleById?id=${vehicle.id}" mask="true" width="1000" height="500" class="btnEdit" target="dialog" rel="vehicle" >编辑</a>
								</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span> 
				<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="10" <c:if test="${page.pageSize == 10}">selected</c:if>>10</option>
					<option value="20" <c:if test="${page.pageSize == 20}">selected</c:if>>20</option>
					<option value="50" <c:if test="${page.pageSize == 50}">selected</c:if>>50</option>
					<option value="100" <c:if test="${page.pageSize == 100}">selected</c:if>>100</option>
				</select> 
				<span>条，共${page.totalCount }条</span>
			</div>
			<div class="pagination" targetType="navTab" totalCount="${page.totalCount }" numPerPage="${page.pageSize }" pageNumShown="10" currentPage="${page.pageNo }"></div>
		</div>
	</div>
</body>
</html>