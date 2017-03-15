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
	<form method="post" action="<%=basePath%>/oa/menu/addAndUpdateMenu"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<input type="hidden" name="menuId" value="${menu.menuId}" />
		<input type="hidden" name="menuTree" id="menuTree" value="${menu.menuTree}" />
		<div class="pageFormContent" layoutH="60" >
			<div style="border: 0px red solid; width: 25%;  float: left;">
				<fieldset  >
					<legend>导航菜单</legend>
					 <ul id="treeWebsiteMenu" name="treeWebsiteMenu" class="ztree"></ul>
				</fieldset>
			</div>
			<div style="border: 0px red solid; width: 70%;  float: left; margin-left: 30px;">
				 <fieldset >
					<legend>基本信息</legend>
					<p>
						<label>菜单名称：</label> 
						<input class="required" name="menuName" type="text" size="35" value="${menu.menuName}" />
					</p>
					<p>
						<label>菜单类型：</label> 
						<select class="combox" name="menuType">
							<option value="3" <c:if test="${menu.menuType==3}">selected</c:if>>网站</option>
							<option value="5" <c:if test="${menu.menuType==5}">selected</c:if>>微信</option>
							<option value="7" <c:if test="${menu.menuType==7}">selected</c:if>>微博</option>
						</select>
					</p>
					<fieldset>
					<legend>介绍说明</legend>
						<textarea name="menuIntro" maxlength="300" cols="40" rows="3" >${menu.menuIntro}</textarea>
					</fieldset>
				</fieldset>
			</div>
		</div>
		<div class="formBar">
			<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit" onclick="addMenu()">提交</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
</body>
<script type="text/javascript">
var listztree = ${listztree};
var setting = {
		view: {
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			selectedMulti: false
		},
		edit: {
			enable: true,
			editNameSelectAll: true,
			showRemoveBtn: showRemoveBtn,
			showRenameBtn: showRenameBtn
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeDrag: beforeDrag,
			beforeEditName: beforeEditName,
			beforeRemove: beforeRemove,
			beforeRename: beforeRename,
			onRemove: onRemove,
			onRename: onRename
		}
	};

	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	function beforeEditName(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var zTree = $.fn.zTree.getZTreeObj("treeWebsiteMenu");
		zTree.selectNode(treeNode);
		return confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？");
	}
	function beforeRemove(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var zTree = $.fn.zTree.getZTreeObj("treeWebsiteMenu");
		zTree.selectNode(treeNode);
		return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
	}
	function onRemove(e, treeId, treeNode) {
		showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
	}
	function beforeRename(treeId, treeNode, newName, isCancel) {
		className = (className === "dark" ? "":"dark");
		showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" beforeRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
		if (newName.length == 0) {
			alert("节点名称不能为空.");
			var zTree = $.fn.zTree.getZTreeObj("treeWebsiteMenu");
			setTimeout(function(){zTree.editName(treeNode)}, 10);
			return false;
		}
		return true;
	}
	function onRename(e, treeId, treeNode, isCancel) {
		showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
	}
	function showRemoveBtn(treeId, treeNode) {
		if(treeNode.id=="0" || treeNode.id=="1" ){
			return false;
		}else{
			return true;
		}
	}
	function showRenameBtn(treeId, treeNode) {
		if( treeNode.id=="0" || treeNode.id=="1"){
			return false;
		}else{
			return true;
		}
	}
	function showLog(str) {
		if (!log) log = $("#log");
		log.append("<li class='"+className+"'>"+str+"</li>");
		if(log.children("li").length > 8) {
			log.get(0).removeChild(log.children("li")[0]);
		}
	}
	function getTime() {
		var now= new Date(),
		h=now.getHours(),
		m=now.getMinutes(),
		s=now.getSeconds(),
		ms=now.getMilliseconds();
		return (h+":"+m+":"+s+ " " +ms);
	}
	var newCount =  ${modelNid};
	function addHoverDom(treeId, treeNode) {
		if(treeNode.id=="1"){
			return false;
		}else{
			var sObj = $("#" + treeNode.tId + "_span");
			if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
				+ "' title='add node' onfocus='this.blur();'></span>";
			sObj.after(addStr);
			var btn = $("#addBtn_"+treeNode.tId);
			if (btn) btn.bind("click", function(){
				var zTree = $.fn.zTree.getZTreeObj("treeWebsiteMenu");
				zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"模块" + (newCount++)});
				return false;
			});
		}
	};
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
	};
	function selectAll() {
		var zTree = $.fn.zTree.getZTreeObj("treeWebsiteMenu");
		zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
	}
	$(document).ready(function(){
		$.fn.zTree.init($("#treeWebsiteMenu"), setting, listztree);
		$("#selectAll").bind("click", selectAll);
	});
	function addMenu(){
		var treeObj = $.fn.zTree.getZTreeObj("treeWebsiteMenu");
	    var node = treeObj.getNodes();
	    var nodes = treeObj.transformToArray(node);
	    var menu =[];
	    for(var i=0; i<nodes.length;i++){
	    	if(nodes[i].id !=0 ){
	    		//menu.push({modelNid:nodes[i].id,modelPid:nodes[i].pId,modelName:nodes[i].name,isParent:nodes[i].isParent});
	    		var menuJson=[];
    			var module = new Object();
    			module.modelNid = nodes[i].id;
    			module.modelPid = nodes[i].pId;
    			module.modelName = nodes[i].name;
    			module.isParent = nodes[i].isParent;
    			menu.push(module);
    			 
	    	} 
	    }
	    if(menu.length>0){
			document.getElementById("menuTree").value = JSON.stringify(menu);
	    }else{
	    	document.getElementById("menuTree").value = "";
	    }
	}
	function addModule(){
		
	}
	function updateModule(){
		
	}
	function deleteModule(){
		
	}
</script>
</html>