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
<title>计生管理信息显示</title>
</head>
<body>
	<form id="pagerForm" method="post" action="<%=basepath %>/oa/planning/getPersonPage">
		<input type="hidden" name="pageNum" value="${page.pageNo }" />
	    <input type="hidden" name="numPerPage" value="${page.pageSize }" /> 
	    <input type="hidden" name="orderField" value="${orderField}" />
	    <input type="hidden" name="orderDirection" value="${orderDirection}" />
	    <input type="hidden" name="startTime" value="${startTime}" />
	    <input type="hidden" name="endTime" value="${endTime}" />
	    <input type="hidden" name="name" value="${person.name }" />
	    <input type="hidden" name="idnumber" value="${person.idnumber }" />
	</form>
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="<%=basepath %>/oa/planning/getPersonPage" method="post">
		<input type="hidden" name="pageNum" value="${page.pageNo }" />
	    	<input type="hidden" name="numPerPage" value="${page.pageSize }" /> 
	    	<input type="hidden" name="orderField" value="${orderField}" />
	    	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	        <input type="hidden" name="marriagestatus" value="${person.familyPlanning.marriagestatus}">
	        <input type="hidden" name="contraceptive" value="${person.familyPlanning.contraceptive}">
	        <input type="hidden" name="onlychildstatus" value="${person.familyPlanning.onlychildstatus}">
	        <input type="hidden" name="twotires" value="${person.familyPlanning.twotires}">
	        <input type="hidden" name="childgrants" value="${person.familyPlanning.childgrants}">
	        <input type="hidden" name="birthregistration" value="${person.familyPlanning.birthregistration}">
	        <input type="hidden" name="richardIII" value="${person.familyPlanning.richardIII}">
	        <input type="hidden" name="obstetricalcard" value="${person.familyPlanning.obstetricalcard}">
	        <input type="hidden" name="subsidiesstate" value="${person.familyPlanning.subsidiesstate}">
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>姓名：<input type="text" name="name"
							value="${person.name }" />
						</td>
						<td>身份证号：<input type="text" name="idnumber"
							value="${person.idnumber }" />
						</td>
						<td>出生日期：
						<input name="startTime" type="text" class="date" readonly="true" value="${startTime}"/>
						- 
						<input name="endTime" type="text" class="date" readonly="true" value="${endTime}"  />
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
					    <li><a class="button" href="<%=basepath %>/oa/planning/findByIdSearch" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<c:if test="${sessionScope.modelflag.editor }">
					<li><a class="edit" href="<%=basepath %>/oa/planning/findPersonById?id={sid_person}" target="navTab"><span>修改</span></a></li>
					<li><a class="icon" href="<%=basepath %>/filemanager/file_excel.jsp?excelType=familyplanning" target="dialog" mask="true" title="导入计生信息" width="500" height="300"><span>导入EXCEL</span></a></li>
				</c:if>
				<li class="line">line</li>
			</ul>
		</div>
		<div layoutH="120">
			<table class="list" width="100%">
				<thead>
					<tr>
						<th>序号</th>
						<th>姓名</th>
						<th>曾用名</th>
						<th>性别</th>
						<!-- <th>所属区域</th>-->						
						<th>出生日期</th>
						<th>民族</th>
						<th>身份证号</th>
						<th>身高</th>
						<th>血型</th>
						<th>政治面貌</th>
						<!-- <th>是否嫁城女</th>
						<th>关联户主</th>
						<th>与户主关系</th>
						<th>出生日期</th>
						<th>民族</th>
						<th>户口性质</th>
						<th>身份证号</th>
						<th>身高</th>
						<th>血型</th>
						<th>政治面貌</th>
						<th>户籍地</th>
						<th>是否辖区内</th>
						<th>人口一致标识</th>
						<th>现居地</th>
						<th>是否本村户籍</th>
						<th>参加村集体经济分配</th>
						<th>职业</th>
						<th>宗教信仰</th>
						<th>婚姻状况</th>
						<th>联系方式</th>
						<th>回归时间</th>-->
						<th orderField="creatime" <c:if test="${orderField == 'creatime'}">  class='${orderDirection}' </c:if>>创建时间</th> 
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.result }" var="person" varStatus="st">
						<tr target="sid_person" rel="${person.id }">
							<td align="center">${st.index+1+page.pageSize*(page.pageNo-1)}</td>
							<td>${person.name }</td>
							<td>${person.oldname }</td>
			                <td><c:if test="${person.sex==0 }">男</c:if> <c:if test="${person.sex==1 }">女</c:if>  </td>
			                 <td>${person.birthdate}</td>
			                <td><c:if test="${person.nation==1 }">汉族</c:if><c:if test="${person.nation==2 }">少数民族</c:if></td>
			                <td target="idnumber_person" rel="${person.idnumber }">${person.idnumber}</td>
	                        <td>${person.tall}</td>
		                    <td><c:if test="${person.bloodtype==1}">A型</c:if>
		                    <c:if test="${person.bloodtype==2}">B型</c:if>
		                    <c:if test="${person.bloodtype==3}">AB型</c:if>
		                    <c:if test="${person.bloodtype==4}">O型</c:if>
		                    <c:if test="${person.bloodtype==5}">其它</c:if>
		                    </td>
	                        <td>
	                        <c:if test="${person.political==1}">党员</c:if>
	                        <c:if test="${person.political==2}">团员</c:if>
	                        <c:if test="${person.political==3}">群众</c:if>
	                        <c:if test="${person.political==4}">其它</c:if>
	                        </td>
			               <%--  <td>${person.orgid }</td>
			                <td>${person.citywoman } </td>
			                <td>${person.householdid}</td>
			                <td>${person.ralation}</td>
			                <td>${person.birthdate}</td>
			                <td>${person.nation}</td>
			                <td>${person.residenttype}</td>
			                <td>${person.idnumber}</td>
	                        <td>${person.tall}</td>
		                    <td>${person.bloodtype}</td>
	                        <td>${person.political}</td>
	                        <td>${person.registry_place}</td>
			                <td>${person.jurisdiction}</td>
			                <td>${person.uniformIdentification}</td>
			                <td>${person.current_address}</td>
			                <td>${person.village_household}</td>
			                <td>${person.economicdistribution}</td>
			                <td>${person.occupation}</td>
			                <td>${person.religion}</td>
			                <td>${person.marital_status}</td>
			                <td>${person.contact}</td>
			                <td>${person.backtime}</td> --%>
			                <td>${person.creatime}</td>
			                <td>
			                     <c:if test="${sessionScope.modelflag.editor}">
								 <a title="编辑" href="<%=basepath %>/oa/planning/findPersonById?id=${person.id}" class="btnEdit" target="navTab">编辑</a>
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