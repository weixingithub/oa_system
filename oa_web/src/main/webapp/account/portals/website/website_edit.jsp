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
<title></title>
 
</head>
<body>
<script type="text/javascript">
	function addimage(url){
		$("#websiteimageId").attr("src",url);
		$("#logoUrl").attr("value",url);
	}
</script>
<div class="pageContent" style="padding:5px">
	<div class="tabs">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>基本信息</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<!-- 基本信息 -->
			<div>
				<form method="post" action="<%=basePath%>/oa/website/addAndUpdateWebsite"
					class="pageForm required-validate"
					onsubmit="return validateCallback(this, dialogAjaxDone)">
					<input type="hidden" name="id" value="${website.id}" />
					<input type="hidden" name="menuId" value="${website.menuId}" />
					<input type="hidden" name="themeId" value="${website.themeId}" />
					<input type="hidden" name="logoUrl" id="logoUrl" value="${website.logoUrl}" />
					<div class="pageFormContent" layoutH="110">
						<fieldset>
						<legend>基本信息</legend>
							<p>
								<label>网站名称：</label> 
								<input class="required" name="name" type="text" size="35" value="${website.name}" />
							</p>
							<p>
								<label>网站域名：</label> 
								<input class="required" name="wDomain" type="text" size="35" value="${website.wDomain}" />
							</p>
							<!-- <p>
								<label>网站类型：</label> 
								<select class="combox" name="type">
									<option value="1">政务</option>
									<option value="2">电商</option>
									<option value="3">公益</option>
									<option value="4">学校</option>
								</select>
							</p> -->
							<p>
								<label>所属部门：</label>
								<input name="orgId" value="${website.orgId }" type="hidden"/>
								<input name="orgPid" value="${website.orgPid }" type="hidden"/>
								<input class="required" name="orgName" type="text" value="${website.orgName}" readonly/>
								<a  class="btnLook" href="<%=basePath %>/oaorgpage/orgtree.jsp" lookupGroup="">选择部门</a>	
							</p>
							<p>
								<label>详细地址：</label> 
								<input class="required" name="address" type="text" size="35" value="${website.address}" />
							</p>
							<p>
								<label>LOGO图片：</label> 
								<span class="info">点击图标上传</span>
								<a class="btnAttach" href="<%=basePath%>/filemanager/file_singleUploadPicture.jsp?fileID=website" lookupGroup="attachment" width="560" height="300" title="附件"></a>		
								
							</p>
						</fieldset>
						<fieldset>
							<legend>LOGO预览</legend>
							 <img  id="websiteimageId" alt="" src="${website.logoUrl }" >
						</fieldset>
						<fieldset>
							<legend>简介：</legend>
							<textarea name="synopsis" cols="60" rows="8">${website.synopsis}</textarea>
						</fieldset>
					</div>
					<div class="formBar">
						<ul>
						<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
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