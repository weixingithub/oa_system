<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getContextPath();
	String isParent = request.getParameter("isParent");
	String nId = request.getParameter("nId");
	String pId = request.getParameter("pId");
	String name = request.getParameter("name");
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
		<div class="pageFormContent" layoutH="200">
		<input type="hidden" name="id"  id="id" value="<%=nId%>" />
		<input type="hidden" name="pId" id="pId" value="<%=pId%>" />
		<input type="hidden" name="name" id="name" value="<%=name%>" />
		<input type="hidden" name="isParent" id="isParent" value="<%=isParent%>" />
		<div>
			<div>
				<dl>
					<dt>模块名称：</dt>
					<dd>
						<input class="required" name="sub_buttonName" id="sub_buttonName" type="text" readonly />
					</dd>
				</dl>
				<dl>
					<dt>模块类型：</dt>
					<dd>
						<select name="buttonType" id="buttonType" class="required combox" onchange="buttonType()">
							<option value="">请选择</option>
							<option value="click">点击类型</option>
							<option value="view" >链接类型</option>
						</select>
					</dd>
				</dl>
				
			</div>
			<div id="clickKey" style="display: none">
				<dl>
					<dt>Key值：</dt>
					<dd>
						<input class="required" name="modelId" id="modelId" type="text" />
					</dd>
				</dl>
				<dl>
					<dt>选择模块：</dt>
					<dd>
						<input name="modelName" id="modelName" type="text" readonly/>
						<a  href="<%=basePath %>/oamodelpage/automodeltree.jsp" lookupGroup="" width="300" height="500" ral="model_tree">选择模块</a>		
					</dd>
				</dl>
			</div>
			<div id="viewUrl" style="display: none">
				<dl>
					<dt>跳转地址：</dt>
					<dd><input class="required" name="url" id="url" type="text" size="40"  value="" /></dd>
				</dl>
			</div>
		</div>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="updateSub_button()">保存</button></div></div></li>
		</ul>
	</div>
</div>
</body>
<script type="text/javascript">
var id = $('#id').val();  
var pId =$('#pId').val();  
var name =$('#name').val(); 
$(document).ready(function(){
	document.getElementById("sub_buttonName").value =name;
	for(var i=0;i<sub_buttonArray.length;i++){
		if(sub_buttonArray[i].id==id){
			$("#buttonType").val(sub_buttonArray[i].type); 
			if(sub_buttonArray[i].type=='click'){
			    document.getElementById("modelName").value = sub_buttonArray[i].name;
				document.getElementById("modelId").value = sub_buttonArray[i].key;
			}else if(sub_buttonArray[i].type=='view'){
				document.getElementById("url").value = sub_buttonArray[i].url;
			}
		}
	}
});
$(function(){
    $('#buttonType').change(function(e){
    	buttonType();
    })
});
function buttonType(){
	var buttonType=$('#buttonType').val();
	var viewUrl=document.getElementById("viewUrl");  
	var clickKey=document.getElementById("clickKey");  
	if(buttonType=='click'){
		clickKey.style.display=""; 
		viewUrl.style.display="none"; 
	}else if(buttonType=='view'){
		clickKey.style.display="none"; 
		viewUrl.style.display=""; 
	}
}
function updateSub_button(){
	var buttonType=$('#buttonType').val();
	if(buttonType==''){
		alert("请选择类型");
		return ;
	}else{
		if(sub_buttonArray.length>0){
			for(var i=0;i<sub_buttonArray.length;i++){
				if(sub_buttonArray[i].id==id){
					if(buttonType=='click'){
						sub_button.name=$('#modelName').val();
						sub_button.type='click';
						sub_button.key=$('#modelId').val();
						sub_button.url="";
					}else if(buttonType=='view'){
					    sub_button.name=$('#sub_buttonName').val();
						sub_button.type='view';
						sub_button.url=$('#url').val();
						sub_button.key="";
					} 
					sub_buttonArray.splice(i,1,sub_button);
				}else{
					sub_button =  new Object();
					sub_button.id=id;
					sub_button.pId = pId;
					if(buttonType=='click'){
						sub_button.name=$('#modelName').val();
						sub_button.type='click';
						sub_button.key=$('#modelId').val();
						sub_button.url="";
					}else if(buttonType=='view'){
					    sub_button.name=$('#sub_buttonName').val();
						sub_button.type='view';
						sub_button.url=$('#url').val();
						sub_button.key="";
					} 
					sub_buttonArray.push(sub_button);
				}
			}
		}else{
			sub_button =  new Object();
			sub_button.id=id;
			sub_button.pId = pId;
			if(buttonType=='click'){
				sub_button.name=$('#modelName').val();
				sub_button.type='click';
				sub_button.key=$('#modelId').val();
				sub_button.url="";
			}else if(buttonType=='view'){
			    sub_button.name=$('#sub_buttonName').val();
				sub_button.type='view';
				sub_button.url=$('#url').val();
				sub_button.key="";
			} 
			sub_buttonArray.push(sub_button);
		}
		
	}
}
</script>
</html>