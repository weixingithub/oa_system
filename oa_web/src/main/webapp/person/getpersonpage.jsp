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
<title>人口基本信息</title>
<script type="text/javascript">
var basepath='<%=basepath %>';
$(function(){
	 if(checkclean =="1"){
			removeCheck();
			checkclean="";
		}else{
			var checkboxesall = $("input[name='checkall']");
			var checkboxes = $("input[name='ids']");
			if(checkall){
				for(var i=0;i<checkboxes.length;i++){
					checkboxes[i].checked = true;
				}
			}else{
				for(var i=0;i<checkboxes.length;i++){
					if(checklist.indexOf(checkboxes[i].value)>=0)
					checkboxes[i].checked = true;
				}
			}
		}
});
  //获取选中的人的手机号码
function getContact(id,contact){
	var contact = checklist;
	alert(contact);
	var id =id ;
	var url ="<%=basepath %>/oa/person/personExcel"; 
	$.ajax({
		dataType:"json",
		type:"post",
		url:url,
		data:{ contact:contact},
		success:function(json){
			 
		}	
	});
}
  /**
  *删除是户主验证
  *
  */
	function verifyRemove(ids,idnumber,ralation){
		var url ="<%=basepath %>/oa/person/delPerson";
		if(ralation=="1"){
			alertMsg.error('此人为户主，请先删除户籍否则无法删除！')
		}else{
			alertMsg.confirm("是否进行删除，请确认您的操作!", {
				okCall: function(){
				$.ajax({
						dataType:"json",
						type:"post",
						url:url,
						data:{ids:ids,idnumber:idnumber},
						success:function(json){
							 alertMsg.correct(json.message);
							 removeCheck();
							 navTabPageBreak();
						}	
					});
				}
			});
		}
	}
  
</script>
</head>
<body>
	<form id="pagerForm" method="post" action="<%=basepath %>/oa/person/getPersonPage">
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
		<form onsubmit="return navTabSearch(this);" action="<%=basepath %>/oa/person/getPersonPage" method="post">
		<input type="hidden" name="pageNum" value="${page.pageNo }" />
    	<input type="hidden" name="numPerPage" value="${page.pageSize }" /> 
    	<input type="hidden" name="orderField" value="${orderField}" />
    	<input type="hidden" name="orderDirection" value="${orderDirection}" />
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>
							<input name="orgId" value="" type="hidden"/>
							<input class="required" name="orgName" type="text" readonly/>
							<a  href="<%=basepath %>/oaorgpage/orgtree.jsp" lookupGroup="">选择部门</a>	
						</td>
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
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<c:if test="${sessionScope.modelflag.add }">
					<li><a class="add" href="<%=basepath %>/oa/person/personAdd"  target="navTab" ><span>添加</span></a></li>
				</c:if>
				<!-- 
				<c:if test="${sessionScope.modelflag.delete }">
					<li><a class="delete" href="<%=basepath %>/oa/person/delPerson?ids={sid_person}&idnumber={idnumber_person}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
				</c:if>
				 -->
				<c:if test="${sessionScope.modelflag.editor }">
					<li><a class="edit" href="<%=basepath %>/oa/person/findPersonById?id={sid_person}" target="navTab"><span>修改</span></a></li>
					<li><a class="icon" href="<%=basepath %>/filemanager/file_excel.jsp?excelType=person" target="dialog" mask="true" title="导入人口信息" width="500" height="300"><span>导入EXCEL</span></a></li>
				</c:if>
				<li class="line">line</li>
				<li><a class="icon" title="确实要导出这些记录吗?" href="javascript:;"  onclick="getContact()"  ><span>选中发送</span></a></li>
			</ul>
		</div>
		<div layoutH="120">
			<table class="list" width="100%">
				<thead>
					<tr>
					    <th width="2%"><input type="checkbox" onclick="checkAll(this);" name="checkall"   group="contacts" class="checkboxCtrl"></th>
						<th width="3%">序号</th>
						<th width="6%">姓名</th>
						<th width="5%">曾用名</th>
						<th width="5%">性别</th>
						<th width="10%">出生日期</th>
						<th width="3%">民族</th>
						<th width="15%">身份证号</th>
						<th width="5%">血型</th>
						<th width="12%">联系方式</th>
						<th width="10%">所属部门</th>
						<th width="15%" orderField="creatime" <c:if test="${orderField == 'creatime'}">  class='${orderDirection}' </c:if>>创建时间</th> 
						<th width="7%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.result }" var="person" varStatus="st">
						<tr target="sid_person" rel="${person.id }">
						    <td><input name="contacts" onclick="addCheck(this);" id="${person.contact}"  value="${person.contact }" type="checkbox"></td>
							<td align="center">${st.index+1+page.pageSize*(page.pageNo-1)}</td>
							<td>${person.name }
							    <a title="详情" href="<%=basepath %>/oa/person/findPersonEditor?id=${person.id}" class="btnLook"  target="dialog" mask="true" width="900" height="600">详情</a>
							</td>
							<td>${person.oldname }</td>
			                <td><c:if test="${person.sex==0 }">男</c:if> <c:if test="${person.sex==1 }">女</c:if>  </td>
			                 <td>${person.birthdate}</td>
			                <td><c:if test="${person.nation==1 }">汉族</c:if><c:if test="${person.nation==2 }">少数民族</c:if></td>
			                <td target="idnumber_person" rel="${person.idnumber }">${person.idnumber}</td>
		                    <td>
		                    <c:if test="${person.bloodtype==1}">A型</c:if>
		                    <c:if test="${person.bloodtype==2}">B型</c:if>
		                    <c:if test="${person.bloodtype==3}">AB型</c:if>
		                    <c:if test="${person.bloodtype==4}">O型</c:if>
		                    <c:if test="${person.bloodtype==5}">其它</c:if>
		                    </td>
	                        <td target="contact_person" rel="${person.contact }">${person.contact}</td>
	                         <td>${person.orgName}</td>
			                <td>${person.creatime}</td>
			                <td>
			                  	<c:if test="${sessionScope.modelflag.delete }">
			                     <a title="删除"  href="javascript:;" onclick="verifyRemove('${person.id}','${person.idnumber}','${person.ralation }');"  class="btnDel">删除</a>
			                    </c:if>
			                     <c:if test="${sessionScope.modelflag.editor}">
								 <a title="编辑" href="<%=basepath %>/oa/person/findPersonById?id=${person.id}" class="btnEdit" target="navTab">编辑</a>
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