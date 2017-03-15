<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
function batchRemove(modelId){
	var ids = checklist;
	var modelId =modelId ;
	var state = "";
	var url ="<%=basepath %>/oa/commentDetails/batchDelComment";
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
						data:{ids:ids},
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
<div class="pageHeader" style="border:1px #B8D0D6 solid">
<form id="pagerForm" method="post" action="<%=basepath %>/oa/commentDetails/findCommentPage" onsubmit="return navTabSearch(this);">
	<input type="hidden" name="sequence" value="${sequence }">
	<input type="hidden" name="pageNum" value="${page.pageNo }"/>
	<input type="hidden" name="numPerPage" value="${page.pageSize }"/>
	<input type="hidden" name="orderField"      value="${orderField }" />  
    <input type="hidden" name="orderDirection"  value="${orderDirection }" />  
	<div class="searchBar">
		<table class="searchContent">
			<tr>
				<td>
					留言关键字：<input type="text" name="commentKey"/>
				</td>
				<td>
					留言日期：<input type="text" name="startTime" class="date" readonly="true" value="${startTime}"/> - <input type="text" name="endTime" class="date" readonly="true" value="${endTime}"/>
				</td>
				<td>
					<label>留言来源：</label>
					<input type="radio" name="recFlag" value="0"/>所有留言&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="recFlag" value="1"/>已回复&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="recFlag" value="2"/>未回复
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		 <%--	<c:if test="${sessionScope.modelflag.editor }">
			<li><a class="edit" href="<%=basepath %>/oa/commentDetails/toRecEditor?commentId={c_id}" target="dialog" mask="true" title="回复" width="800" height="480"><span>回复</span></a></li>
			</c:if>--%>
			 <c:if test="${sessionScope.modelflag.delete }">
			<li><a class="delete" title="确定要删除这些留言吗?" href="javascript:;" onclick="batchRemove()"><span>批量删除</span></a></li>
			</c:if>
			<li class="line">line</li>
		</ul> 
	</div>
	<div layoutH="120">
	<table class="list" width="100%">
		<thead>
			<tr>
				<th width="2%"><input type="checkbox" onclick="checkAll(this);" name="checkall"   group="ids" class="checkboxCtrl"></th>
				<th width="2%" >序号</th>
				<th width="5%">留言者</th>
				<th width="25%">留言内容</th>
				<th width="8%" orderField="createTime" <c:if test="${orderField == 'createTime'}">  class="${orderDirection}" </c:if>>留言时间</th>
				<th width="3%" >状态</th>
				<th width="5%">操作</th>
			</tr>
		</thead>
		<tbody>
		    <c:forEach  items="${page.result}"  var="commentDetails" varStatus="status">
			 <tr target="c_id" rel="${commentDetails.contentId}">
			 	 <td><input name="ids" onclick="addCheck(this);" id="${commentDetails.contentId}"  value="${commentDetails.contentId }" type="checkbox"></td>
			     <td align="center">${status.index+1+(page.pageNo-1)*page.pageSize }</td>
                 <td>${commentDetails.sender}</td>
                 <td>${commentDetails.content}</td>
                 <td>${commentDetails.createTime}</td>
                 <td>
                 	<c:if test="${commentDetails.recFlag == 1}">已回复</c:if>
                 	<c:if test="${commentDetails.recFlag == 2}">未回复</c:if>
                 </td>  
                 <td>
                    <c:if test="${sessionScope.modelflag.editor}">
	                   	<c:if test="${commentDetails.recFlag == 1}">
						 <a title="查看/编辑回复" href="<%=basepath %>/oa/commentDetails/toRecEditor?commentId=${commentDetails.contentId}&type=show" class="btnLook" target="dialog" mask="true" width="550" height="430">查看回复</a>
						</c:if>
					</c:if>
                    <c:if test="${sessionScope.modelflag.editor}">
	                   	<c:if test="${commentDetails.recFlag == 2}">
						 <a title="回复" href="<%=basepath %>/oa/commentDetails/toRecEditor?commentId=${commentDetails.contentId}&type=rec" class="btnEdit" target="dialog"  mask="true" width="550" height="430">回复留言</a>
						</c:if>
					</c:if>
                  	<c:if test="${sessionScope.modelflag.delete }">
                     <a title="删除" href="<%=basepath %>/oa/commentDetails/delComment?commentId=${commentDetails.contentId}&recFlag=${commentDetails.recFlag}" class="btnDel" target="ajaxTodo">删除</a>
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
				<div class="pagination" targetType="navTab" totalCount="${page.totalCount }" numPerPage="${page.pageSize }" pageNumShown="10" currentPage="${page.pageNo }">
				</div>
		</div>
</div>
</body>
</html>