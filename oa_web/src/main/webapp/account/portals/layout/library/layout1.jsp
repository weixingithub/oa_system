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
				<div style="border: 0px red solid; width: 40%; height: 100px; float: left;">
					<fieldset style="height:80px">
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
							<input name="module1.modelUrl"   type="hidden" value="${columnMap.columnDiv1.modelUrl }" />
							<input name="module1.moduleName" type="text"   value="${columnMap.columnDiv1.moduleNames }"   readonly />
							<a class="btnLook" href="<%=basePath %>/oa/model/findOaModelPageSelect?select=checkbox" lookupGroup="module1"  rel="module_checkbox">查找带回</a>
						</p>
						<input name="plugin1.columnId" 	type="hidden"   value="${columnMap.columnDiv1.columnId }" />
						<input name="plugin1.columnDiv" type="hidden"  value="columnDiv1" />
					</fieldset>
				</div>
				<div style="border: 0px red solid; width: 52%; height: 100px; float: left; margin-left: 15px;">
					 <fieldset style="height:80px">
						<legend>栏目二</legend>
						<p>
							<label>选择插件：</label> 
							<input name="plugin2.pluginId"   type="hidden" value="${columnMap.columnDiv2.plugin.pluginId }" />
							<input name="plugin2.pluginName" type="text"   value="${columnMap.columnDiv2.plugin.pluginName }"  class="required"  readonly />
							<a class="btnAdd" href="<%=basePath %>/oa/plugin/findPluginPagejbsxBox" lookupGroup="plugin2"  rel="plugin_radio">查找带回</a>
						</p>
						<p>
							<label>选择数据源：</label> 
							<input name="module2.moduleId"   type="hidden" value="${columnMap.columnDiv2.moduleIds }" />
							<input name="module2.modelUrl"   type="hidden" value="${columnMap.columnDiv2.modelUrl }" />
							<input name="module2.moduleName" type="text"   value="${columnMap.columnDiv2.moduleNames }"     readonly />
							<a class="btnLook" href="<%=basePath %>/oa/model/findOaModelPageSelect?select=checkbox" lookupGroup="module2"  rel="module_checkbox">查找带回</a>
						</p>
						<input name="plugin2.columnId" 	type="hidden"   value="${columnMap.columnDiv2.columnId }" />
						<input name="plugin2.columnDiv" type="hidden"  value="columnDiv2" />
					</fieldset>
				</div>
			</div>
			<div style="border: 0px yellow solid; width: 100%; height: auto; float: left;">
				<div style="border: 0px red solid; width: 62%; height: 100px; float: left;">
					<fieldset style="height:80px">
						<legend>栏目三</legend>
						<p>
							<label>选择插件：</label> 
							<input name="plugin3.pluginId"   type="hidden" value="${columnMap.columnDiv3.plugin.pluginId }" />
							<input name="plugin3.pluginName" type="text"   value="${columnMap.columnDiv3.plugin.pluginName }"  class="required"  readonly />
							<a class="btnAdd" href="<%=basePath %>/oa/plugin/findPluginPagejbsxBox" lookupGroup="plugin3"  rel="plugin_radio">查找带回</a>
						</p>
						<p>
							<label>选择数据源：</label> 
							<input name="module3.moduleId"   type="hidden" value="${columnMap.columnDiv3.moduleIds }" />
							<input name="module3.modelUrl"   type="hidden" value="${columnMap.columnDiv3.modelUrl }" />
							<input name="module3.moduleName" type="text"   value="${columnMap.columnDiv3.moduleNames }"    readonly />
							<a class="btnLook" href="<%=basePath %>/oa/model/findOaModelPageSelect?select=checkbox" lookupGroup="module3"  rel="module_checkbox">查找带回</a>
						</p>
						<input name="plugin3.columnId" 	type="hidden"   value="${columnMap.columnDiv3.columnId }" />
						<input name="plugin3.columnDiv" type="hidden"  value="columnDiv3" />
					</fieldset>
				</div>
				<div style="border: 0px red solid; width: 30%; height: 100px; float: left; margin-left: 15px;">
					 <fieldset style="height:80px">
						<legend>栏目四</legend>
						<p>
							<label>选择插件：</label> 
							<input name="plugin4.pluginId"   type="hidden" value="${columnMap.columnDiv4.plugin.pluginId }" />
							<input name="plugin4.pluginName" type="text"   value="${columnMap.columnDiv4.plugin.pluginName }"  class="required"  readonly />
							<a class="btnAdd" href="<%=basePath %>/oa/plugin/findPluginPagejbsxBox" lookupGroup="plugin4"  rel="plugin_radio">查找带回</a>
						</p>
						<p>
							<label>选择数据源：</label> 
							<input name="module4.moduleId"   type="hidden" value="${columnMap.columnDiv4.moduleIds }" />
							<input name="module4.modelUrl"   type="hidden" value="${columnMap.columnDiv4.modelUrl }" />
							<input name="module4.moduleName" type="text"   value="${columnMap.columnDiv4.moduleNames }"    readonly />
							<a class="btnLook" href="<%=basePath %>/oa/model/findOaModelPageSelect?select=checkbox" lookupGroup="module4"  rel="module_checkbox">查找带回</a>
						</p>
						<input name="plugin4.columnId" 	type="hidden"   value="${columnMap.columnDiv4.columnId }" />
						<input name="plugin4.columnDiv" type="hidden"  value="columnDiv4" />
					</fieldset>
				</div>
			</div>
			<div style="border: 0px yellow solid; width: 100%; height: auto; float: left;">
				<div style="border: 0px red solid; width: 30%; height: 100px; float: left;">
					<fieldset style="height:80px">
						<legend>栏目五</legend>
						<p>
							<label>选择插件：</label> 
							<input name="plugin5.pluginId"   type="hidden" value="${columnMap.columnDiv5.plugin.pluginId }" />
							<input name="plugin5.pluginName" type="text"   value="${columnMap.columnDiv5.plugin.pluginName }"  class="required"  readonly />
							<a class="btnAdd" href="<%=basePath %>/oa/plugin/findPluginPagejbsxBox" lookupGroup="plugin5"  rel="plugin_radio">查找带回</a>
						</p>
						<p>
							<label>选择数据源：</label> 
							<input name="module5.moduleId"   type="hidden" value="${columnMap.columnDiv5.moduleIds }" />
							<input name="module5.modelUrl"   type="hidden" value="${columnMap.columnDiv5.modelUrl }" />
							<input name="module5.moduleName" type="text"   value="${columnMap.columnDiv5.moduleNames }"   readonly />
							<a class="btnLook" href="<%=basePath %>/oa/model/findOaModelPageSelect?select=checkbox" lookupGroup="module5"  rel="module_checkbox">查找带回</a>
						</p>
						<input name="plugin5.columnId" 	type="hidden"   value="${columnMap.columnDiv5.columnId }" />
						<input name="plugin5.columnDiv" type="hidden"  value="columnDiv5" />
					</fieldset>
				</div>
				<div style="border: 0px red solid; width: 30%; height: 100px; float: left; margin-left: 15px;">
					 <fieldset style="height:80px">
						<legend>栏目六</legend>
						<p>
							<label>选择插件：</label> 
							<input name="plugin6.pluginId"   type="hidden" value="${columnMap.columnDiv6.plugin.pluginId }" />
							<input name="plugin6.pluginName" type="text"   value="${columnMap.columnDiv6.plugin.pluginName }"  class="required"  readonly />
							<a class="btnAdd" href="<%=basePath %>/oa/plugin/findPluginPagejbsxBox" lookupGroup="plugin6"  rel="plugin_radio">查找带回</a>
						</p>
						<p>
							<label>选择数据源：</label> 
							<input name="module6.moduleId"   type="hidden" value="${columnMap.columnDiv6.moduleIds }" />
							<input name="module6.modelUrl"   type="hidden" value="${columnMap.columnDiv6.modelUrl }" />
							<input name="module6.moduleName" type="text"   value="${columnMap.columnDiv6.moduleNames }"  readonly />
							<a class="btnLook" href="<%=basePath %>/oa/model/findOaModelPageSelect?select=checkbox" lookupGroup="module6"  rel="module_checkbox">查找带回</a>
						</p>
						<input name="plugin6.columnId" 	type="hidden"   value="${columnMap.columnDiv6.columnId }" />
						<input name="plugin6.columnDiv" type="hidden"  value="columnDiv6" />
					</fieldset>
				</div>
				<div style="border: 0px red solid; width: 30%; height: 100px; float: left; margin-left: 15px;">
					 <fieldset style="height:80px">
						<legend>栏目七</legend>
						<p>
							<label>选择插件：</label> 
							<input name="plugin7.pluginId"   type="hidden" value="${columnMap.columnDiv7.plugin.pluginId }" />
							<input name="plugin7.pluginName" type="text"   value="${columnMap.columnDiv7.plugin.pluginName }"  class="required"  readonly />
							<a class="btnAdd" href="<%=basePath %>/oa/plugin/findPluginPagejbsxBox" lookupGroup="plugin7"  rel="plugin_radio">查找带回</a>
						</p>
						<p>
							<label>选择数据源：</label> 
							<input name="module7.moduleId"   type="hidden" value="${columnMap.columnDiv7.moduleIds }" />
							<input name="module7.modelUrl"   type="hidden" value="${columnMap.columnDiv7.modelUrl }" />
							<input name="module7.moduleName" type="text"   value="${columnMap.columnDiv7.moduleNames }"    readonly />
							<a class="btnLook" href="<%=basePath %>/oa/model/findOaModelPageSelect?select=checkbox" lookupGroup="module7"  rel="module_checkbox">查找带回</a>
						</p>
						<input name="plugin7.columnId" 	type="hidden"   value="${columnMap.columnDiv7.columnId }" />
						<input name="plugin7.columnDiv" type="hidden"  value="columnDiv7" />
					</fieldset>
				</div>
			</div>
			<div style="border: 0px yellow solid; width: 100%; height: auto; float: left;">
				<div style="border: 0px red solid; width: 95%; height: 100px; float: left;">
					<fieldset style="height:80px">
						<legend>栏目八</legend>
						<p>
							<label>选择插件：</label> 
							<input name="plugin8.pluginId"   type="hidden" value="${columnMap.columnDiv8.plugin.pluginId }" />
							<input name="plugin8.pluginName" type="text"   value="${columnMap.columnDiv8.plugin.pluginName }"  class="required"  readonly />
							<a class="btnAdd" href="<%=basePath %>/oa/plugin/findPluginPagejbsxBox" lookupGroup="plugin8"  rel="plugin_radio">查找带回</a>
						</p>
						<p>
							<label>选择数据源：</label> 
							<input name="module8.moduleId"   type="hidden" value="${columnMap.columnDiv8.moduleIds }" />
							<input name="module8.modelUrl"   type="hidden" value="${columnMap.columnDiv8.modelUrl }" />
							<input name="module8.moduleName" type="text"   value="${columnMap.columnDiv8.moduleNames }"    readonly />
							<a class="btnLook" href="<%=basePath %>/oa/model/findOaModelPageSelect?select=checkbox" lookupGroup="module8"  rel="module_checkbox">查找带回</a>
						</p>
						<input name="plugin8.columnId" 	type="hidden"   value="${columnMap.columnDiv8.columnId }" />
						<input name="plugin8.columnDiv" type="hidden"  value="columnDiv8" />
					</fieldset>
				</div>
			</div>
			<div style="border: 0px yellow solid; width: 100%; height: auto; float: left;">
				<div style="border: 0px red solid; width: 30%; height: 100px; float: left;">
					<fieldset style="height:80px">
						<legend>栏目九</legend>
						<p>
							<label>选择插件：</label> 
							<input name="plugin9.pluginId"   type="hidden" value="${columnMap.columnDiv9.plugin.pluginId }" />
							<input name="plugin9.pluginName" type="text"   value="${columnMap.columnDiv9.plugin.pluginName }"  class="required"  readonly />
							<a class="btnAdd" href="<%=basePath %>/oa/plugin/findPluginPagejbsxBox" lookupGroup="plugin9"  rel="plugin_radio">查找带回</a>
						</p>
						<p>
							<label>选择数据源：</label> 
							<input name="module9.moduleId"   type="hidden" value="${columnMap.columnDiv9.moduleIds }" />
							<input name="module9.modelUrl"   type="hidden" value="${columnMap.columnDiv9.modelUrl }" />
							<input name="module9.moduleName" type="text"   value="${columnMap.columnDiv9.moduleNames }"    readonly />
							<a class="btnLook" href="<%=basePath %>/oa/model/findOaModelPageSelect?select=checkbox" lookupGroup="module9"  rel="module_checkbox">查找带回</a>
						</p>
						<input name="plugin9.columnId" 	type="hidden"   value="${columnMap.columnDiv9.columnId }" />
						<input name="plugin9.columnDiv" type="hidden"  value="columnDiv9" />
					</fieldset>
				</div>
				<div style="border: 0px red solid; width: 30%; height: 100px; float: left; margin-left: 15px;">
					 <fieldset style="height:80px">
						<legend>栏目十</legend>
						<p>
							<label>选择插件：</label> 
							<input name="plugin10.pluginId"   type="hidden" value="${columnMap.columnDiv10.plugin.pluginId }" />
							<input name="plugin10.pluginName" type="text"   value="${columnMap.columnDiv10.plugin.pluginName }"  class="required"  readonly />
							<a class="btnAdd" href="<%=basePath %>/oa/plugin/findPluginPagejbsxBox" lookupGroup="plugin10"  rel="plugin_radio">查找带回</a>
						</p>
						<p>
							<label>选择数据源：</label> 
							<input name="module10.moduleId"   type="hidden" value="${columnMap.columnDiv10.moduleIds }" />
							<input name="module10.modelUrl"   type="hidden" value="${columnMap.columnDiv10.modelUrl }" />
							<input name="module10.moduleName" type="text"   value="${columnMap.columnDiv10.moduleNames }"  readonly />
							<a class="btnLook" href="<%=basePath %>/oa/model/findOaModelPageSelect?select=checkbox" lookupGroup="module10"  rel="module_checkbox">查找带回</a>
						</p>
						<input name="plugin10.columnId" 	type="hidden"   value="${columnMap.columnDiv10.columnId }" />
						<input name="plugin10.columnDiv" type="hidden"  value="columnDiv10" />
					</fieldset>
				</div>
				<div style="border: 0px red solid; width: 30%; height: 100px; float: left; margin-left: 15px;">
					 <fieldset style="height:80px">
						<legend>栏目十一</legend>
						<p>
							<label>选择插件：</label> 
							<input name="plugin11.pluginId"   type="hidden" value="${columnMap.columnDiv11.plugin.pluginId }" />
							<input name="plugin11.pluginName" type="text"   value="${columnMap.columnDiv11.plugin.pluginName }"  class="required"  readonly />
							<a class="btnAdd" href="<%=basePath %>/oa/plugin/findPluginPagejbsxBox" lookupGroup="plugin11"  rel="plugin_radio">查找带回</a>
						</p>
						<p>
							<label>选择数据源：</label> 
							<input name="module11.moduleId"   type="hidden" value="${columnMap.columnDiv11.moduleIds }" />
							<input name="module11.modelUrl"   type="hidden" value="${columnMap.columnDiv11.modelUrl }" />
							<input name="module11.moduleName" type="text"   value="${columnMap.columnDiv11.moduleNames }"    readonly />
							<a class="btnLook" href="<%=basePath %>/oa/model/findOaModelPageSelect?select=checkbox" lookupGroup="module11"  rel="module_checkbox">查找带回</a>
						</p>
						<input name="plugin11.columnId" 	type="hidden"   value="${columnMap.columnDiv11.columnId }" />
						<input name="plugin11.columnDiv" type="hidden"  value="columnDiv11" />
					</fieldset>
				</div>
			</div>
			<div style="border: 0px yellow solid; width: 100%; height: auto; float: left;">
				<div style="border: 0px red solid; width: 62%; height: 100px; float: left;">
					<fieldset style="height:80px">
						<legend>栏目十二</legend>
						<p>
							<label>选择插件：</label> 
							<input name="plugin12.pluginId"   type="hidden" value="${columnMap.columnDiv12.plugin.pluginId }" />
							<input name="plugin12.pluginName" type="text"   value="${columnMap.columnDiv12.plugin.pluginName }"  class="required"  readonly />
							<a class="btnAdd" href="<%=basePath %>/oa/plugin/findPluginPagejbsxBox" lookupGroup="plugin12"  rel="plugin_radio">查找带回</a>
						</p>
						<p>
							<label>选择数据源：</label> 
							<input name="module12.moduleId"   type="hidden" value="${columnMap.columnDiv12.moduleIds }" />
							<input name="module12.modelUrl"   type="hidden" value="${columnMap.columnDiv12.modelUrl }" />
							<input name="module12.moduleName" type="text"   value="${columnMap.columnDiv12.moduleNames }"    readonly />
							<a class="btnLook" href="<%=basePath %>/oa/model/findOaModelPageSelect?select=checkbox" lookupGroup="module12"  rel="module_checkbox">查找带回</a>
						</p>
						<input name="plugin12.columnId" 	type="hidden"   value="${columnMap.columnDiv12.columnId }" />
						<input name="plugin12.columnDiv" type="hidden"  value="columnDiv12" />
					</fieldset>
				</div>
				<div style="border: 0px red solid; width: 30%; height: 100px; float: left; margin-left: 15px;">
					 <fieldset style="height:80px">
						<legend>栏目十三</legend>
						<p>
							<label>选择插件：</label> 
							<input name="plugin13.pluginId"   type="hidden" value="${columnMap.columnDiv13.plugin.pluginId }" />
							<input name="plugin13.pluginName" type="text"   value="${columnMap.columnDiv13.plugin.pluginName }"  class="required"  readonly />
							<a class="btnAdd" href="<%=basePath %>/oa/plugin/findPluginPagejbsxBox" lookupGroup="plugin13"  rel="plugin_radio">查找带回</a>
						</p>
						<p>
							<label>选择数据源：</label> 
							<input name="module13.moduleId"   type="hidden" value="${columnMap.columnDiv13.moduleIds }" />
							<input name="module13.modelUrl"   type="hidden" value="${columnMap.columnDiv13.modelUrl }" />
							<input name="module13.moduleName" type="text"   value="${columnMap.columnDiv13.moduleNames }"   readonly />
							<a class="btnLook" href="<%=basePath %>/oa/model/findOaModelPageSelect?select=checkbox" lookupGroup="module13"  rel="module_checkbox">查找带回</a>
						</p>
						<input name="plugin13.columnId" 	type="hidden"   value="${columnMap.columnDiv13.columnId }" />
						<input name="plugin13.columnDiv" type="hidden"  value="columnDiv13" />
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
		var modelUrlArray=[];
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
		$("input[name$='modelUrl']").each(
				function(){
					modelUrlArray.push($(this).val());
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
				ColumnObj.push({pluginId:pluginIdArray[i],columnDiv:columnDivArray[i],columnId:columnIdArray[i],moduleIds:moduleIdArray[i],moduleNames:moduleNameArray[i],modelUrl:modelUrlArray[i]})
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