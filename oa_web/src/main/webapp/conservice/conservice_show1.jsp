<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
   String basePath = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript">
var url = "<%=basePath%>/oa/commentInfo/getPraiseAndNegativeNumber";
function zanSum(commentInfoId){
	$.ajax({ 
		dataType:"json",
		type:"post",
		url:url,
		data:{"commentInfoId":commentInfoId,"type":"zan"},
		success:function(json){
			var zanNum = Number($("#zanNum").text()) + 1;
			$("#zanNum").text(zanNum);
		}
	});
}

function caiSum(commentInfoId){
	$.ajax({ 
		dataType:"json",
		type:"post",
		url:url,
		data:{"commentInfoId":commentInfoId,"type":"cai"},
		success:function(json){
			var caiNum = Number($("#caiNum").text()) + 1;
			$("#caiNum").text(caiNum);
		}
	});
}
</script>
<title>Insert title here</title>
</head>
<body>

</head >
<body>
<div class="pageContent">
		<div class="pageFormContent" >
			<div class="unit">
			  <dl class="nowrap">
				<label>标题：</label>
				 <dd>
				   <a href="${personWelfare.commentInfo.url}" target="navTab">${personWelfare.commentInfo.title}</a>
				  </dd>
			  </dl>
			</div>
			<div class="unit">
			  <dl class="nowrap">
				<label>文章作者：</label>
				 <dd>
				   ${personWelfare.author}
				  </dd>
			  </dl>
			</div>

			<div class="unit">
			  <dl class="nowrap">
				 <label>发布时间：</label>
				 <dd> ${personWelfare.creattime}</dd>
			  </dl>
			</div>
			
			<div class="unit">
			  <dl class="nowrap">
				 <label>阅读量：</label>
				 <dd> ${personWelfare.commentInfo.browseNumber}</dd>
			  </dl>
				
			</div>
			<div class="unit">
			  <dl class="nowrap">
				 <dt>内容：</dt>
				 <dd>
					<div width="100%" height="1000px">
						${personWelfare.personWelfareContent.content }
					</div>
				 </dd>
			  </dl>
			</div>
		
			</div>
			<c:if test="${personWelfare.commentSwitch == 1}">
		     <button type="button" onclick="zanSum(${personWelfare.commentInfo.commentInfoId})">赞一个</button><span>(<span id="zanNum">${personWelfare.commentInfo.praiseNumber}</span>)</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		     <button type="button" onclick="caiSum(${personWelfare.commentInfo.commentInfoId})">踩一个</button><span>(<span id="caiNum">${personWelfare.commentInfo.negativeNumber}</span>)</span>

			<!-- 留言列表开始 -->
				<table class="list" width="100%">
						<thead>
							<tr>
								<th width="3%">序号</th>
								<th width="4%">留言者</th>
								<th width="35%">留言内容</th>
								<th width="10%" orderField="creattime" <c:if test="${orderField == 'creattime'}">  class="${orderDirection}" </c:if>>留言时间</th>
							</tr>
						</thead>
						<tbody>
						<!-- 遍历所有评论，如果有回复就在下方显示 -->
							<c:forEach items="${personWelfare.commentInfo.commentDetails}" var="commentDetails"  varStatus="status">
								<c:if test="${commentDetails.isRec == 0}">
									<tr target="recipient" rel="${commentDetails.recipient}">
										<td align="center">${status.count}楼</td>
										<td>${commentDetails.sender}</td>
										<td>${commentDetails.content}</td>
										<td>${commentDetails.createTime}</td>
									</tr>
									<c:if test="${commentDetails.commentDetails != null}">
									<tr>
										<td colspan="4">
										<strong>${commentDetails.commentDetails.sender}</strong>在${commentDetails.commentDetails.createTime}回复<b><strong>${commentDetails.commentDetails.recipient}</strong>：</b>
										${commentDetails.commentDetails.content}
										</td>
									</tr>
									</c:if>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
			<!-- 留言列表结束 -->
				<a href="<%=basePath %>/oa/commentDetails/toAddComment?commentInfoId=${personWelfare.commentInfo.commentInfoId}&pId=${personWelfare.id}" target="dialog" mask="true" title="新增留言" width="550" height="360"><div class="button"><div class="buttonContent">我也说两句</div></div></a>

			</c:if>
</div>
</body>

</html>