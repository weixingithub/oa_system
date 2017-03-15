<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String basePath = request.getContextPath();   
    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function subMethod(){
	document.getElementById("freshmodel").value="fresh";
}
</script>
</head>
<style type="text/css">
	ul.rightTools {float:right; display:block;}
	ul.rightTools li{float:left; display:block; margin-left:5px}
</style>
<body onl>
<div class="pageContent" style="padding:5px">
	<div class="panel" defH="40">
		<h1>操作</h1>
		<div>
		           当前模块名称：<strong>${oaModel.modelName }</strong>
			<ul class="rightTools">
			<c:if test="${userflag==1 }">
					  <c:if test="${sessionScope.modelflag.add }">
						<li><a class="button" target="dialog"  rel="modeltree" href="<%=basePath %>/oa/model/gotoAddModel?parentId=${oaModel.parentId}&nodeId=${oaModel.nodeId}&userflag=${userflag}" mask="true" title="创建同级菜单" width="750" height="400" ><span>创建同级菜单</span></a></li>
						<li><a class="button" target="dialog"  rel="modeltree" href="<%=basePath %>/oa/model/gotoAddModel?nodeId=${oaModel.nodeId}&userflag=${userflag}" mask="true" title="创建下级菜单" width="750" height="400" ><span>创建下级菜单</span></a></li>
					  </c:if>
					  <c:if test="${sessionScope.modelflag.delete }">
						<li><a class="button" href="<%=basePath %>/oa/model/delOaModel?id=${oaModel.modelId }&parentId=${oaModel.parentId}&nodeId=${oaModel.nodeId}&modelname=${oaModel.modelName}&misparent=${oaModel.isParent}&pAuto=${oaModel.pAuto}" target="ajaxTodo" title="删除后所有角色将失去此模块，确定删除?"><span>删除</span></a></li>
					  </c:if>
			</c:if>
			<c:if test="${userflag==0 }">
			   <c:if test="${oaModel.isAuto==1 }">
					  <c:if test="${sessionScope.modelflag.add }">
					    <li><a class="button" target="dialog"  rel="modeltree" href="<%=basePath %>/oa/model/gotoAddModel?parentId=${oaModel.parentId}&nodeId=${oaModel.nodeId}&userflag=${userflag}" mask="true" title="创建同级菜单" width="750" height="400" ><span>创建同级菜单</span></a></li>
						<li><a class="button" target="dialog" rel="modeltree" href="<%=basePath %>/oa/model/gotoAddModel?nodeId=${oaModel.nodeId}&userflag=${userflag}" mask="true" title="创建下级菜单" width="750" height="400" ><span>创建下级菜单</span></a></li>
					  </c:if>
					  <c:if test="${sessionScope.modelflag.delete }">
						<li><a class="button" href="<%=basePath %>/oa/model/delOaModel?id=${oaModel.modelId }&parentId=${oaModel.parentId}&nodeId=${oaModel.nodeId}&modelname=${oaModel.modelName}&misparent=${oaModel.isParent}&pAuto=${oaModel.pAuto}" target="ajaxTodo" title="删除后所有角色将失去此模块，确定删除?"><span>删除</span></a></li>
					  </c:if>
			  </c:if>
			</c:if>
			</ul>
		</div>
	</div>
	<div class="divider"></div>
	<div class="tabs">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>模块基本信息</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<div style="background:#fff">
			<form method="post" action="<%=basePath %>/oa/model/addOaModel" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
			 <input name="modelId" value="${oaModel.modelId }" type="hidden"/>
		    <input name="isParent" value="${oaModel.isParent }" type="hidden"/>
		    <input name="nodeId" value="${oaModel.nodeId }" type="hidden"/>
		    <input name="parentId" value="${oaModel.parentId }" type="hidden"/>
		    <input name="userId" value="${oaModel.userId }" type="hidden"/>
		    <input name="pAuto" value="${oaModel.pAuto }" type="hidden"/>
		    <input name="isAuto" value="${oaModel.isAuto }" type="hidden"/>
		    <input id="freshmodel" type="hidden"  name="freshValue" />
		    <input id="optype" type="hidden"  name="optype"  value="update"/>
		    <div class="pageFormContent" >
				<p>
				    <dl class="nowrap">
	 				 <dt>模块名称：</dt>
					<dd><input name="modelName" value="${oaModel.modelName }" class="required" type="text" size="30" alt="请输入模块名称"/></dd>
					</dl>
				</p>
				<c:if test="${userflag==0 }">
				<div style="display: none">
				<p>
				  <dl class="nowrap">
					  <dt>模块链接：</dt>
					  <dd><input name="modelUrl" value="${oaModel.modelUrl }" type="text" size="60"  /></dd>
			      </dl>
				</p>
				<p>
				  <dl class="nowrap">
					  <dt>前台链接：</dt>
					  <dd><input name="stageUrl" value="${oaModel.stageUrl }" type="text" size="60"  /></dd>
			      </dl>
				</p>
				<p>
				  <dl class="nowrap">
				  <dt>模块自动化：</dt>
				  <dd>
					<select class="combox" name="isAuto">
						<option <c:if test="${oaModel.isAuto == 0 }">selected</c:if> value="0">否</option>
						<option <c:if test="${oaModel.isAuto == 1 }">selected</c:if> value="1">是</option>
					</select>
				 </dd>
				 </dl>
				 <p>
				  <dl class="nowrap">
				  <dt>审核模块：</dt>
				  <dd>
					<select class="combox" name="isCheckModel">
						<option <c:if test="${oaModel.isCheckModel == 0 }">selected</c:if> value="0">否</option>
						<option <c:if test="${oaModel.isCheckModel == 1 }">selected</c:if> value="1">是</option>
					</select>
			       </dd>
				 </dl>
				</p>
				</p>
				<p>
				  <dl class="nowrap">
				  <dt>frame跳转：</dt>
				  <dd>
					<select class="combox" name="modelIframe">
						<option <c:if test="${oaModel.modelIframe == 'false' }">selected</c:if> value="false">否</option>
						<option <c:if test="${oaModel.modelIframe == 'true' }">selected</c:if> value="true">是</option>
					</select>
				 </dd>
				 </dl>
				</p>
				</div>
				</c:if>
				<c:if test="${userflag==1 }">
				<p>
				  <dl class="nowrap">
					  <dt>模块链接：</dt>
					  <dd><input name="modelUrl" value="${oaModel.modelUrl }" type="text" size="60"  /></dd>
			      </dl>
				</p>
				<p>
				  <dl class="nowrap">
					  <dt>前台链接：</dt>
					  <dd><input name="stageUrl" value="${oaModel.stageUrl }" type="text" size="60"  /></dd>
			      </dl>
				</p>
				<p>
				  <dl class="nowrap">
				  <dt>frame跳转：</dt>
				  <dd>
					<select class="combox" name="modelIframe">
						<option <c:if test="${oaModel.modelIframe == 'false' }">selected</c:if> value="false">否</option>
						<option <c:if test="${oaModel.modelIframe == 'true' }">selected</c:if> value="true">是</option>
					</select>
				 </dd>
				 </dl>
				</p>
				
				<c:if test="${oaModel.isAuto == 0}">
				<p>
				  <dl class="nowrap">
				  <dt>审核模块：</dt>
				  <dd>
					<select class="combox" name="isCheckModel">
						<option <c:if test="${oaModel.isCheckModel == 0 }">selected</c:if> value="0">否</option>
						<option <c:if test="${oaModel.isCheckModel == 1 }">selected</c:if> value="1">是</option>
					</select>
			       </dd>
				 </dl>
				</p>
				</c:if>
				
				</c:if>
				<p>
				  <dl class="nowrap">
					  <dt>模块备注：</dt>
					  <c:if test="${userflag==0 }">
					  <dd><textarea name="remark"  cols="80" rows="2" readonly="readonly">${oaModel.remark }</textarea></dd>
					  </c:if>
					   <c:if test="${userflag==1 }">
					  <dd><textarea name="remark"  cols="80" rows="2" >${oaModel.remark }</textarea></dd>
					  </c:if>
			      </dl>
				</p>
				<c:if test="${not empty oaModel.checkRoleId}">
				<p>
				  <dl class="nowrap">
					  <dt>审核角色：</dt>
					  <dd>
					  <input type="hidden"name=checkRoleId value="${oaModel.checkRoleId }" />
			          <input type="text" class="required"  name="parentName" maxlength="20" value="${poaRole.roleName }" /> 
			          <a class="btnLook" href="<%=basePath %>/oa/role/getIntoRole?id=${oaRole.roleId }&keyName=checkRoleId" lookupGroup="" width="300" height="500">权限树</a>
					  </dd>
			      </dl>
				</p>
				</c:if>
			</div>
		<div class="formBar">
			<ul>
			<c:if test="${sessionScope.modelflag.editor }">
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">修改</button></div></div></li>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" onclick="subMethod();">修改并刷新</button></div></div></li>
			</c:if>
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