<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div class="pageContent">
		<div class="pageFormContent" layoutH="50">
			<c:if test="${listWechat!= null || fn:length(listWechat) != 0}">
				<fieldset>
					<legend>微信结果</legend>
					<table class="table" width="100%" >
						<thead>
							<tr>
								<th width="3%" >序号</th>
								<th width="20%" >发送时间</th>
								<th width="15%" >状态</th>
							</tr>
						</thead>
						<tbody>
						   <c:forEach  items="${listWechat}"  var="massMsg" varStatus="status">
						   <tr>
							   	<td>${status.index+1 }</td>
							   	<td>${massMsg.sendTime }</td>
							    <td>
							   		<c:if test="${massMsg.errcode ==0}">发送成功</c:if>
							    	<c:if test="${massMsg.errcode !=0}">发送失败</c:if>
							    </td>
						   	</tr>
						   </c:forEach>
						</tbody>
					</table>
				</fieldset>
			</c:if>
			<c:if test="${listWeibo!= null || fn:length(listWeibo) != 0}">
				<fieldset>
					<legend>微博结果</legend>
					<table class="table" width="100%" >
						<thead>
							<tr>
								<th width="3%" >序号</th>
								<th width="20%" >发送时间</th>
								<th width="15%" >状态</th>
							</tr>
						</thead>
						<tbody>
						   <c:forEach  items="${listWeibo}"  var="weiboMedia" varStatus="status">
						   <tr>
							   	<td>${status.index+1 }</td>
							    <td>${weiboMedia.createTime }</td>
							    <td>
							    	<c:if test="${weiboMedia.errcode ==0}">发送成功</c:if>
							    	<c:if test="${weiboMedia.errcode !=0}">发送失败</c:if>
							    </td>
						   	</tr>
						   </c:forEach>
						</tbody>
					</table>
				</fieldset>
			</c:if>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" onclick="sendWecaht()" >重新发送微信</button>
						</div>
					</div></li>
				<li><div class="button">
					<div class="buttonContent">
						<button type="button" onclick="sendWeibo()" >重新发送微博</button>
					</div>
				</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</div>
</body>
<script type="text/javascript">
	var pubId = ${pubId};
	function sendWecaht(){
	 var url="<%=basePath %>/oa/sendMessage/sendWechatMessage";
	 SendAjax(url);	
	}
	function sendWeibo(){
	 var url="<%=basePath %>/oa/sendMessage/sendWeiboMediaMessage";
	 SendAjax(url);
	}
	function SendAjax(url){
		$.ajax({
			dataType:"json",
			type:"post",
			url:url,
			data:{ pubId:pubId},
			success:function(result){
				alertMsg.correct(result.message); 
			}	
		});	
	}
</script>
</html>