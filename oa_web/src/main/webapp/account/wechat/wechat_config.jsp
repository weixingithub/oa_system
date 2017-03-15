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
<link rel="stylesheet" href="../themes/css/zTreeStyle.css" type="text/css">
<style type="text/css">
ul.rightTools {
	float: right;
	display: block;
}

ul.rightTools li {
	float: left;
	display: block;
	margin-left: 5px
}
</style>
<body>
	
<style type="text/css">
	ul.rightTools {float:right; display:block;}
	ul.rightTools li{float:left; display:block; margin-left:5px}
</style>

<div class="pageContent" style="padding:5px">
	
	<div class="divider"></div>
	<div class="tabs">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>创建菜单</span></a></li>
					<li><a href="javascript:;"><span>设置LOGO</span></a></li>
					<li><a href="javascript:;"><span>查看群组</span></a></li>
					<li><a href="javascript:;"><span>编辑群组</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<div>
			
				<div class="panel" defH="40">
					<h1>创建菜单</h1>
					<div>
						<ul class="rightTools">
						<li><a class="button" id="addParent" href="javascript:;" onclick="return false;"><span>增加父节点</span></a></li>
						<li><a class="button" id="addLeaf" href="javascript:;" onclick="return false;"><span>增加叶子节点</span></a></li>
						<li><a class="button" id="edit" href="javascript:;" onclick="return false;"><span>编辑名称</span></a></li>
						<!-- 
						 <li><a class="button" id="remove" href="javascript:;" onclick="return false;"><span>删除节点</span></a></li>
						 -->
						<li><a class="button" id="" href="javascript:;" onclick="submitMenu()"><span>提交</span></a></li>
						</ul>
					</div>
					<div>
					    提交<input name="ztreeJson" id="ztreeJson" value="" size="60"><br/>
					</div>
				</div>
				
				<div layoutH="146" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
				   <ul id="treeWechatMenu" class="ztree"></ul>
				</div>
				
				<div id="jbsxBoxWechatMenu" class="unitBox" style="margin-left:246px;">
					
				</div>
	
			</div>
			
			<div>设置LOGO</div>
			
			<div>查看群组</div>
			
			<div>编辑群组</div>
			
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
	
</div>
</body>
<script type="text/javascript">
var sub_button = new Object();
var sub_buttonArray = [];
function sub_button(id,pId,name,type,key,url){
	this.id = id;
	this.pId = pId;
	this.name = name;
	this.type = type;
	this.key= key;
	this.url = url;
}
var setting = {
		view: {
			selectedMulti: false
		},
		edit: {
			enable: true,
			showRemoveBtn: false,
			showRenameBtn: false
		},
		data: {
			keep: {
				parent:true,
				leaf:true
			},
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeDrag: beforeDrag,
			beforeRemove: beforeRemove,
			beforeRename: beforeRename,
			onRemove: onRemove,
			onClick: onClick
		}
	};

	var zNodes =[{ id:0, pId:-1, name:"临时节点", open:false,isParent:true,target:"none",url:""}];
	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	function beforeRemove(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
	}
	function onRemove(e, treeId, treeNode) {
		showLog("[ "+getTime()+" onRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
	}
	function beforeRename(treeId, treeNode, newName) {
		if (newName.length == 0) {
			alert("节点名称不能为空.");
			var zTree = $.fn.zTree.getZTreeObj("treeWechatMenu");
			setTimeout(function(){zTree.editName(treeNode)}, 10);
			return false;
		}
		return true;
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

	var newCount = 1;
	function add(e) {
		var zTree = $.fn.zTree.getZTreeObj("treeWechatMenu"),
		isParent = e.data.isParent,
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		if (treeNode) {
			treeNode = zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, isParent:isParent, name:"new node" + (newCount++)});
		} else {
			treeNode = zTree.addNodes(null, {id:(100 + newCount), pId:0, isParent:isParent, name:"new node" + (newCount++)});
		}
		if (treeNode) {
			zTree.editName(treeNode[0]);
		} else {
			alert("叶子节点被锁定，无法增加子节点");
		}
	};
	function edit() {
		var zTree = $.fn.zTree.getZTreeObj("treeWechatMenu"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		if (nodes.length == 0) {
			alert("请先选择一个节点");
			return;
		}
		zTree.editName(treeNode);
	};
	function remove(e) {
		var zTree = $.fn.zTree.getZTreeObj("treeWechatMenu"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		if (nodes.length == 0) {
			alert("请先选择一个节点");
			return;
		}
		var callbackFlag = $("#callbackTrigger").attr("checked");
		zTree.removeNode(treeNode, callbackFlag);
	};
	function clearChildren(e) {
		var zTree = $.fn.zTree.getZTreeObj("treeWechatMenu"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		if (nodes.length == 0 || !nodes[0].isParent) {
			alert("请先选择一个父节点");
			return;
		}
		zTree.removeChildNodes(treeNode);
	};
	
	$(document).ready(function(){
		$.fn.zTree.init($("#treeWechatMenu"), setting, zNodes);
		$("#addParent").bind("click", {isParent:true}, add);
		$("#addLeaf").bind("click", {isParent:false}, add);
		$("#edit").bind("click", edit);
		//$("#remove").bind("click", remove);
		$("#clearChildren").bind("click", clearChildren);
	});
	function onClick(event, treeId, treeNode, clickFlag) {
		//alert(JSON.stringify(treeNode));
		//alert(treeNode.id + ", "+treeNode.pId+"," + treeNode.name+","+treeNode.isParent);
		if(!treeNode.isParent){
		 $("#jbsxBoxWechatMenu").loadUrl('<%=basePath%>/account/wechat/wechat_menu.jsp',{'isParent':treeNode.isParent,nId:treeNode.id,pId:treeNode.pId,name:treeNode.name},null);
		}
	}
	function submitMenu(){
		var jsonMenu= {button:[]};
		var buttonArray = [];
		var treeObj = $.fn.zTree.getZTreeObj("treeWechatMenu");
	    var node = treeObj.getNodes();
	    var nodes = treeObj.transformToArray(node);
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].isParent == true && nodes[i].pId !== null && nodes[i].pId !== ''){
				buttonArray.push({name:nodes[i].name,id:nodes[i].id,pId:nodes[i].pId,sub_button:[]});  //获取每个节点的id
			}
		 }
		if(buttonArray.length>0){
			for(var i=0;i<buttonArray.length;i++){
				var sub_buttonJson = [];
				for(var j=0;j<sub_buttonArray.length;j++){	
					if(buttonArray[i].id==sub_buttonArray[j].pId){
						if(sub_buttonArray[j].type=="click"){
							sub_buttonJson.push({name:sub_buttonArray[j].name,type:sub_buttonArray[j].type,key:sub_buttonArray[j].key});
						}else if(sub_buttonArray[j].type=="view"){
							sub_buttonJson.push({name:sub_buttonArray[j].name,type:sub_buttonArray[j].type,url:sub_buttonArray[j].url});
						}
					}
				}
				jsonMenu.button.push({name:buttonArray[i].name,sub_button:sub_buttonJson});
			 }
		}else{
			for(var j=0;j<sub_buttonArray.length;j++){
				if(sub_buttonArray[j].type=="click"){
					jsonMenu.button.push({name:sub_buttonArray[j].name,type:sub_buttonArray[j].type,key:sub_buttonArray[j].key});
				}else if(sub_buttonArray[j].type=="view"){
					jsonMenu.button.push({name:sub_buttonArray[j].name,type:sub_buttonArray[j].type,url:sub_buttonArray[j].url});
				}
			}
		}
		document.getElementById("ztreeJson").value=JSON.stringify(jsonMenu);
	}
</script>
</html>