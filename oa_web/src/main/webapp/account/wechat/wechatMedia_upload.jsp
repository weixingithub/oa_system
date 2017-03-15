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
<title>微博</title>
</head>
<script type="text/javascript">
	function addimage(url){
		$("#imgSrcId").attr("src",url);
		$("#imgSrc").attr("value",url);
	}
</script>
<body>
<div class="pageContent" style="padding:5px">
	<div class="tabs">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>添加微博信息</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<!-- 微博基本信息 -->
			<div>
				<form method="post" action="<%=basePath%>/oa/weiboMedia/uploadWeiboMedia"
					class="pageForm required-validate"
					onsubmit="return validateCallback(this, dialogAjaxDone)">
						<input type="hidden" name="imgSrc" id="imgSrc" value="" />
					<div class="pageFormContent" layoutH="120">
						<fieldset>
							<legend>内容信息</legend>
							<p>
								<label>标题:</label> 
								<input class="required" name="title" type="text" size="35" value="" />
							</p>
							<p>
								<label>选择应用</label> 
								<select class="combox" name="weiboId">
									<c:forEach items="${weiboList }" var="weibo" >
										<option value="${weibo.id}">${weibo.appName}</option>
									</c:forEach>
								</select>
							</p>
							 <p>
								<label>图片:</label> 
								<span class="info">点击图片上传</span>
								<a class="btnAttach" href="<%=basePath%>/filemanager/file_singleUploadPicture.jsp?fileID=weibo" lookupGroup="attachment" width="560" height="300" title="附件"></a>
							</p>
							<img  id="imgSrcId" alt="" src="" width="300" height="150">
						</fieldset>
						<fieldset>
							<legend>正文:</legend>
							<textarea name="text" maxlength="140" cols="80" rows="6" ></textarea>
						</fieldset>
					</div>
					<div class="formBar">
						<ul>
							<li><div class="buttonActive"><div class="buttonContent"><button type="submit" >提交</button></div></div></li>
							<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
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