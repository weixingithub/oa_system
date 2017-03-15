<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
        String basepath = request.getContextPath();
 %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色添加</title>
</head>
<body>
	<div class="pageContent">
		<form method="post" action="<%=basepath %>/oa/role/addRole"
			class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
			<input name="roleId" value="${oaRole.roleId }" type="hidden" />
			<input name="nodeId" value="${oaRole.nodeId }" type="hidden" />
			<input name="userId" value="${oaRole.userId }" type="hidden" />
			<input name="isParent" value="${oaRole.isParent }" type="hidden" />
			<input name="createTime" value="${oaRole.createTime }" type="hidden" />
			<input name="oldParentId" value="${oldParentId }" type="hidden" />
			<div class="pageFormContent nowrap" layoutH="90">
				<dl>
					<dt>角色名称：</dt>
					<dd>
						<input type="text" name="roleName" maxlength="20" class="required"
							value="${oaRole.roleName }" /> <span class="info"></span>
					</dd>
				</dl>
				<dl>
					<dt>上级角色：</dt>
					<dd>
						<input type="hidden"name="parentId" value="${oaRole.parentId }" />
						<input type="text"  name="parentName" maxlength="20" value="${poaRole.roleName }" /> 
						<a class="btnLook" href="<%=basepath %>/oa/role/getIntoRole?id=${oaRole.roleId }&keyName=parentId" lookupGroup="" width="300" height="500">权限树</a>
					</dd>
				</dl>
				<dl>
					<dt>所属机构：</dt>
					<dd>
					    <input type="hidden"name="orgId" value="${oaRole.orgId }" />
						<input type="text"  name="orgName" maxlength="20" value="${orgName }" /> 
						<a class="btnLook" href="<%=basepath %>/oaorgpage/orgtree.jsp" lookupGroup="" width="300" height="500">机构树</a>
					</dd>
				</dl>
				<dl class="nowrap">
					<dt>角色备注：</dt>
					<dd>
						<textarea name="remark" class="required" cols="30" rows="5">${oaRole.remark }</textarea>
					</dd>
				</dl>
			</div>
			<div class="formBar">
				<ul>
					<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">保存</button>
							</div>
						</div>
					</li>
					<li>
						<div class="button">
							<div class="buttonContent">
								<button type="button" class="close">取消</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</form>
	</div>
</body>
</html>