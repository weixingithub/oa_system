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
<script type="text/javascript">
var basepath='<%=basepath %>';
$(function(){
	$('.trace').click(graphTrace);
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
function batchRemove(modelId){
	var ids = checklist;
	var modelId =modelId ;
	var state = "";
	var url ="<%=basepath %>/oa/pwelfare/delPwelfare";
	if(checkall){ 
		alertMsg.confirm("您选择的是全选删除，请确认您的操作!", {
			okCall: function(){
				state = "all";
				$.ajax({
					dataType:"json",
					type:"post",
					url:url,
					data:{ modelId:modelId,state:state},
					success:function(json){
						 alertMsg.correct(json.message);
						 removeCheck();
						 navTabPageBreak();
						 
					}	
				});
			}
		});
	}else{
		if(ids==""){
			alertMsg.error('请选择信息！')
		}else{
			alertMsg.confirm("是否进行删除，请确认您的操作!", {
				okCall: function(){
				$.ajax({
						dataType:"json",
						type:"post",
						url:url,
						data:{ids:ids, modelId:modelId},
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
}

</script>
</head>
<body>
	<form id="pagerForm" method="post" action="<%=basepath %>/oa/pwelfare/modelvariable" >
		<input type="hidden" name="modelId"  value="${personWelfare.modelId }" /> 
		<input type="hidden" name="pageNum" value="${page.pageNo }" /> 
		<input type="hidden" name="numPerPage" value="${page.pageSize }" /> 
		<input type="hidden" name="orderField" value="${orderField }" />  
    	<input type="hidden" name="orderDirection"  value="${orderDirection }" />
    	
	</form>
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="<%=basepath %>/oa/pwelfare/modelvariable" method="post">
		<input type="hidden" name="modelId" value="${personWelfare.modelId }" /> 
		<input type="hidden" name="pageNum" value="${page.pageNo }" /> 
		<input type="hidden" name="numPerPage" value="${page.pageSize }" /> 
		<input type="hidden" name="orderField" value="${orderField }" />  
    	<input type="hidden" name="orderDirection"  value="${orderDirection }" />  
			<div class="searchBar">
				<table class="searchContent">
					<tr>
						<td>文章标题：<input type="text" name="title"
							value="${personWelfare.title }" />
						</td>
						
						<td>文章内容：<input type="text" name="content"
							value="${content }" />
						</td>
					</tr>
				</table>
				<div class="subBar">
					<ul>
						<li><div class="buttonActive">
								<div class="buttonContent">
									<button type="submit">检索</button>
								</div>
							</div></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<c:if test="${sysUser.nodeId !='creator'}">
					<c:if test="${sessionScope.modelflag.add }">
						<li><a class="add" href="<%=basepath %>/oa/pwelfare/intoAddPwelfare?modelId=${personWelfare.modelId }&autoCheck=${oaModel.autoIsCheckModel}" target="navTab"><span>添加</span></a></li>
					</c:if>
					<c:if test="${sessionScope.modelflag.delete }">
						<li><a class="delete" title="确实要删除这些记录吗?" href="javascript:;" onclick="batchRemove(${personWelfare.modelId }, ${sysUser.id})"   ><span>批量删除</span></a></li>	
					</c:if>
				</c:if>
				<li class="line">line</li>
			</ul>
		</div>
		<div layoutH="120">
			<table class="list" width="100%">
				<thead>
					<tr>
						<th width="2%"><input type="checkbox" onclick="checkAll(this);" name="checkall"   group="ids" class="checkboxCtrl"></th>
						<th width="3%">序号</th>
						<th width="30%">文章标题</th>
						<th width="8%">创建人</th>
						<th width="10%">所属机构</th>
						<th width="10%">审核状态</th>
						<th width="15%" orderField="creattime" <c:if test="${orderField == 'creattime'}">  class="${orderDirection}" </c:if>>创建时间</th>
						<th width="15%">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.result }" var="personWelfare" varStatus="st">
						<tr target="sid_personWelfare" rel="${personWelfare.id }">
							<c:if test="${personWelfare.flowstatus == 1 }">
							<td></td>
							</c:if>
							<c:if test="${personWelfare.flowstatus != 1 }">
								<c:if test="${personWelfare.userId != sysUser.id}">
								<td></td>
								</c:if>
								<c:if test="${personWelfare.userId == sysUser.id }">
							 	 <td><input name="ids" onclick="addCheck(this);" id="${personWelfare.id}"  value="${personWelfare.id }" type="checkbox"></td>
								</c:if>
							</c:if>
							<td>${st.index+1+page.pageSize*(page.pageNo-1)}</td>
							<td>
							 <c:if test="${sessionScope.modelflag.detail }">
							   <%-- <a title="详情" href="<%=basepath %>/oa/pwelfare/showPwelfare?id=${personWelfare.id}" class="btnLook"   target="navTab" rel="external"external="true">详情</a> --%>
							    <a title="文章详情" href="<%=basepath %>/oa/pwelfare/showPwelfare?id=${personWelfare.id}" class="btnLook"  target="navTab" external="true"></a>
							 </c:if>
							 <c:if test="${fn:length(personWelfare.title)<=20}">${personWelfare.title}</c:if>
							 <c:if test="${fn:length(personWelfare.title)>20}">${fn:substring(personWelfare.title,0,20)}...</c:if>
							</td>
							<td>${personWelfare.sysUser.realName }</td>
							<td>${personWelfare.org.orgName }</td>
							<td>${publishStatusMap[personWelfare.flowstatus+0] }</td>
							<td>${personWelfare.creattime }</td>
							<td>
							<c:if test="${(personWelfare.userId ==sysUser.id) && personWelfare.flowstatus == 0}">
								<c:if test="${sessionScope.modelflag.delete }">
									<a title="删除" target="ajaxTodo" href="<%=basepath %>/oa/pwelfare/delPwelfare?ids=${personWelfare.id}" class="btnDel" callback="navTabAjaxDone">删除</a>
								</c:if>
								<c:if test="${sessionScope.modelflag.editor }">
									<a title="编辑" target="navTab" href="<%=basepath %>/oa/pwelfare/intoUpdatePwelfare?id=${personWelfare.id}&autoCheck=${oaModel.autoIsCheckModel}" class="btnEdit" >编辑</a>
								</c:if>
								<c:if test="${oaModel.autoIsCheckModel == 1 }">
									<a title="申请发布" target="dialog" href="<%=basepath %>/oa/pwelfare/createForm?id=${personWelfare.id}&title=${personWelfare.title}&creattime=${personWelfare.creattime}&modelId=${personWelfare.modelId}"class="btnInfo" >申请发布</a>
								</c:if>
							</c:if>
							<c:if test="${(personWelfare.userId ==sysUser.id) && (personWelfare.flowstatus == 3 || personWelfare.flowstatus == 4)}">
								<c:if test="${sessionScope.modelflag.delete }">
									<a title="删除" target="ajaxTodo" href="<%=basepath %>/oa/pwelfare/delPwelfare?ids=${personWelfare.id}" class="btnDel" callback="navTabAjaxDone">删除</a>
								</c:if>
								<c:if test="${sessionScope.modelflag.editor }">
									<a title="编辑" target="navTab" href="<%=basepath %>/oa/pwelfare/intoUpdatePwelfare?id=${personWelfare.id}&autoCheck=${oaModel.autoIsCheckModel}" class="btnEdit" >编辑</a>
								</c:if>
								<c:if test="${oaModel.autoIsCheckModel == 1 }">
									<a title="申请发布" target="dialog" href="<%=basepath %>/oa/pwelfare/createForm?id=${personWelfare.id}&title=${personWelfare.title}&creattime=${personWelfare.creattime}&modelId=${personWelfare.modelId}"class="btnInfo" >申请发布</a>
								</c:if>
							</c:if>
							<c:if test="${(personWelfare.userId ==sysUser.id) && personWelfare.flowstatus == 2}">
								<c:if test="${sessionScope.modelflag.delete }">
									<a title="删除" target="ajaxTodo" href="<%=basepath %>/oa/pwelfare/delPwelfare?ids=${personWelfare.id}" class="btnDel" callback="navTabAjaxDone">删除</a>
									 <c:if test="${personWelfare.pubPlatform%5==0 || personWelfare.pubPlatform%7==0 }"> 
										<a title="查看结果发送"  target="dialog" href="<%=basepath %>/oa/sendMessage/querySendResult?pubId=${personWelfare.id}" >查看结果发送</a>
									 </c:if>
								</c:if>
							</c:if>
							<c:if test="${personWelfare.flowstatus == 1}">
								  <a class="trace" href='#' pid="${personWelfare.processInstance.id }" pdid="${personWelfare.processInstance.processDefinitionId}" mdid="${personWelfare.modelId}" title="点击查看流程图">查看进度 </a>
							</c:if>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<div class="panelBar">
			<div class="pages">
				<span>显示</span> <select class="combox" name="numPerPage"
					onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="10"
						<c:if test="${page.pageSize == 10}">selected</c:if>>10</option>
					<option value="20"
						<c:if test="${page.pageSize == 20}">selected</c:if>>20</option>
					<option value="50"
						<c:if test="${page.pageSize == 50}">selected</c:if>>50</option>
					<option value="100"
						<c:if test="${page.pageSize == 100}">selected</c:if>>100</option>
				</select> <span>条，共${page.totalCount }条</span>
			</div>
			<div class="pagination" targetType="navTab"
				totalCount="${page.totalCount }" numPerPage="${page.pageSize }"
				pageNumShown="10" currentPage="${page.pageNo }"></div>
		</div>
	</div>
</body>
</html>