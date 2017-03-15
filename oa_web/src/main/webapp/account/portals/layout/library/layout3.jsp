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
<div class="pageContent">
	<form method="post" action="<%=basePath%>/oa/column/addAndUpdateColumn" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
		<input type="hidden" name="modelId" value="${module.modelId}" />
		<input type="hidden" name="layoutId" value="${layout.layoutId}" />
		<input name="columnJson" type="hidden"  value="" />
		<div class="pageFormContent" layoutH="60" >
			<div style="border: 0px yellow solid; width: 100%; height: auto; float: left;">
				<div style="border: 0px red solid; width: 95%; height: 50px; float: left;">
					<fieldset style="height:30px">
						<legend>页头</legend>
					</fieldset>
				</div>
			</div>
			<div style="border: 0px yellow solid; width: 100%; height: auto; float: left;">
				<div style="border: 0px red solid; width: 95%; height: 320px; float: left;">
					<fieldset style="height:300px">
						<legend>栏目一</legend>
						<p>
							<label>选择插件：</label> 
							<input name="plugin1.pluginId"   type="hidden" value="${columnMap.columnDiv1.plugin.pluginId }" />
							<input name="plugin1.pluginName" type="text"   value="${columnMap.columnDiv1.plugin.pluginName }"  class="required"  readonly />
							<a class="btnAdd" href="<%=basePath %>/oa/plugin/findPluginPagejbsxBox" lookupGroup="plugin1"  rel="plugin_radio">查找带回</a>
						</p>
						<p>
							<label>选择数据源：</label> 
							<input name="module1.moduleId"   type="hidden" value="${columnMap.columnDiv1.moduleIds }" />
							<input name="module1.moduleName" type="text"   value="${columnMap.columnDiv1.moduleNames }"   readonly />
							<a class="btnLook" href="<%=basePath %>/oa/model/findOaModelPageSelect?select=checkbox" lookupGroup="module1"  rel="module_checkbox">查找带回</a>
						</p>
						<input name="plugin1.columnId" 	type="hidden"   value="${columnMap.columnDiv1.columnId }" />
						<input name="plugin1.columnDiv" type="hidden"  value="columnDiv1" />
					</fieldset>
				</div>
			</div>
			<div style="border: 0px yellow solid; width: 100%; height: auto; float: left;">
				<div style="border: 0px red solid; width: 95%; height: 50px; float: left;">
					<fieldset style="height:30px">
						<legend>页尾</legend>
					</fieldset>
				</div>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" onclick="return submintPlugin()">提交</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
</body>
<script type="text/javascript">
function submintPlugin(){
	var pluginIdArray=[];
	var moduleIdArray=[];
	var moduleNameArray=[];
	var columnDivArray=[];
	var columnIdArray=[];
	var flag = true;
	$("input[name$='pluginId']").each(
			function(){
				if ($(this).val() == "") 
				{
					 flag = false;
					return false;
				}else{
					pluginIdArray.push($(this).val());
				}
			}
		);
	$("input[name$='columnDiv']").each(
			function(){
				if ($(this).val() == "") 
				{
					 flag = false;
					return false;
				}else{
					columnDivArray.push($(this).val());
				}
			}
		);
	$("input[name$='moduleId']").each(
			function(){
				moduleIdArray.push($(this).val());
			}
		);
	$("input[name$='moduleName']").each(
			function(){
				moduleNameArray.push($(this).val());
			}
		);
	$("input[name$='columnId']").each(
			function(){
				columnIdArray.push($(this).val());
			}
		);
		 
	if(flag){
		var modelId =  $("input[name='modelId']").val();
		var ColumnObj =[];
		for(var i=0;i<columnDivArray.length;i++){
			ColumnObj.push({pluginId:pluginIdArray[i],columnDiv:columnDivArray[i],columnId:columnIdArray[i],moduleIds:moduleIdArray[i],moduleNames:moduleNameArray[i]})
		}
		 $("input[name='columnJson']").attr("value",JSON.stringify(ColumnObj));
		// alert(JSON.stringify(ColumnObj));
	}else{
		alertMsg.confirm("信息未填完整，无法进行提交!");
	}
	return flag;
}
</script>
</html>