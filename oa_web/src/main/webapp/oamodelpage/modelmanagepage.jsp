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
</head>
<script type="text/javascript">
var settingmodel = {
		async: {
			    enable: true,
				url:baseurl+"/oa/model/getModelUserMeun",
				autoParam:["id=parentId"],
				otherParam:{"modeltype":"oamodel"},
				dataFilter: filter //异步返回后经过Filter 
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback:{
		    onAsyncSuccess:initCompelete
		}
};
$(document).ready(function(){
	$.fn.zTree.init($("#treeModelConfigure"), settingmodel);
});
</script>
<body>
<div class="pageContent" style="padding:5px">
    <div class="divider"></div>
	<div class="tabs">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>模块菜单</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<div>
	
				<div layoutH="60" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
				   <div class="accordionContent">
					    <ul id="treeModelConfigure" class="ztree"></ul>
				   </div>
				</div>
				
				<div id="modelBox" class="unitBox" layoutH="60" style="margin-left:246px;">
				   <div class="tabs">
				   <c:if test="${sessionScope.modelflag.add }">
						<div class="tabsHeader">
							<div class="tabsHeaderContent">
								<ul>
									<li><a href="javascript:;"><span>模块基本信息</span></a></li>
								</ul>
							</div>
						</div>
						<div class="tabsContent">
							<div style="background:#fff">
									<form method="post" action="<%=basepath %>/oa/model/addOaModel" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
									<input  type="hidden"  name="freshValue"  value="noclose" />
									<input id="optype" type="hidden"  name="optype"  value="add"/>
								    <div class="pageFormContent" >
									 <div class="unit">
										<label>模块名称：</label>
										  <input name="modelName" class="required" type="text" size="30" alt="请输入模块名称"/>
										</div>
										
										
										
									 <c:if test="${userflag == 1 }">
									 <div id="modelurldiv">
										  <div class="unit" >
											  <label>模块链接：</label>
											  <input name="modelUrl"  id="modelurl"  type="text" size="60"  />
									      </div>
									      <div class="unit" >
											  <label>前台链接：</label>
											  <input name="stageUrl"  id="stageUrl"  type="text" size="60"  />
									      </div>
									 </div>
									  <div class="unit"  id="isAutoselectdiv">
									  <label>模块自动化：</label>
										<select class="combox"  onchange="AutoChose();" id="autoselect" name="isAuto">
											<option value="0">否</option>
											<option value="1">是</option>
										</select>
									 </div>
									  <div class="unit" id="pAutoselectdiv" style="display:none;">
									  <label>信息自动化：</label>
										<select class="combox" id="pAuto" onchange="changeCheck();"   name="pAuto">
											<option value="false">否</option>
											<option value="true">是</option>
										</select>
									 </div>
									 <div class="unit" id="autocheckdiv" style="display:none;">
									  <label>是否审核：</label>
										<select class="combox" id="pAutoCheck" name="autoIsCheckModel" onchange="autoChange();">
											<option value="0">否</option>
											<option value="1">是</option>
										</select>
									 </div>
									</c:if>
									
									
									
									
									 <c:if test="${userflag == 0 }">
								      <input name="isAuto" type="hidden" value="1" />
								       <div class="unit" id="pAutoselectdiv">
									  <label>信息自动化：</label>
										<select class="combox" id="pAuto" onchange="changeCheck();"   name="pAuto">
											<option value="false">否</option>
											<option value="true">是</option>
										</select>
									 </div>
									 <div class="unit" id="autocheckdiv" style="display:none;">
									  <label>是否审核：</label>
										<select class="combox" id="pAutoCheck" onchange="autoChange();" name="autoIsCheckModel">
											<option value="0">否</option>
											<option value="1">是</option>
										</select>
									 </div> 
									 </c:if>
									 
									
									 <div  class="unit"  id="checkRoleIddiv" style="display:none;">
									  <label>审核角色：</label>
										<input type="hidden" name="checkRoleId" id="checkRoleId" value="" />
									    <input type="text"  id="checkRoleName"  name="parentName" maxlength="20" value="" /> 
									    <a class="btnLook" href="<%=basepath %>/oa/role/getIntoRole?id=${oaRole.roleId }&keyName=checkRoleId" lookupGroup="" width="300" height="500">权限树</a>
									  </div>
									 
									 
									 
									  <c:if test="${userflag == 1 }">
									   <div  class="unit">
									  <label>frame跳转：</label>
										<select class="combox" name="modelIframe">
											<option value="false">否</option>
											<option value="true">是</option>
										</select>
									  </div>
									   <div  class="unit" id="developcheckdiv">
									  <label>审核模块：</label>
										<select class="combox" name="isCheckModel">
											<option value="0">否</option>
											<option value="1">是</option>
										</select>
									  </div>
									  </c:if>
									  
									  
									  
									  <div class="unit">
									  <dl class="nowrap">
										  <dt>模块备注：</dt>
										  <dd><textarea name="remark" cols="80" rows="2" ></textarea></dd>
								      </dl>
									 </div>
								</div>
								<div class="formBar">
									<ul>
										<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
									</ul>
								</div>
							    </form>
							</div>
						 </div>
		             </c:if>
					<div class="tabsFooter">
						<div class="tabsFooterContent"></div>
					</div>
	               </div>
              </div>
         </div>
     </div>
	  <div class="tabsFooter">
		   <div class="tabsFooterContent"></div>
	  </div>
</div>
	
</div>
</body>
</html>