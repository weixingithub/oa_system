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
<title>微信</title>
</head>
<body>
<div class="pageContent" style="padding:5px">
	<div class="tabs">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>微信基本信息</span></a></li>
					<c:if test="${wechat.id!='' && wechat.id!=null}">
						<li><a href="javascript:;"><span>配置菜单</span></a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<!-- 微信基本信息 -->
			<div>
				<form method="post" action="<%=basePath%>/oa/wechat/addOrUpdateWechat"
					class="pageForm required-validate"
					onsubmit="return validateCallback(this, dialogAjaxDone)">
						<input type="hidden" name="id" value="${wechat.id}" />
						<input type="hidden" name="tokenId" value="${wechat.wechatToken.id}" />
					<div class="pageFormContent" layoutH="120">
						<dl>
							<dt>微信账号：</dt>
							<dd><input class="required" name="name" type="text" size="35" value="${wechat.name}" /></dd>
						</dl>
						<dl>
							<dt>微信密码：</dt>
							<dd><input class="required" name="password" type="text" size="35" value="${wechat.password}" /></dd>
						</dl>
						<dl>
							<dt>开发者微信号：</dt>
							<dd><input class="required" name="wechatID" type="text" size="35" value="${wechat.wechatID}" /></dd>
						</dl>
						<dl>
							<dt>唯一凭证AppId：</dt>
							<dd><input class="required" name="appId" type="text" size="35" value="${wechat.appId}" /></dd>
						</dl>
						<dl>
							<dt>凭证密钥：</dt>
							<dd><input class="required" name="appSecret" type="text" size="35"  value="${wechat.appSecret}" /></dd>
						</dl>
						<dl>
							<dt>访问的接口地址：</dt>
							<dd><input class="required" name="websiteUrl" type="text" size="35" value="${wechat.websiteUrl}" /></dd>
						</dl>
						<dl>
							<dt>所属部门：</dt>
							<dd><input name="orgId" value="${wechat.orgId }" type="hidden"/>
								<input name="orgPid" value="${wechat.orgPid }" type="hidden"/>
								<input class="required" name="orgName" type="text" value="${wechat.orgName}" readonly/>
								<a  class="btnLook" href="<%=basePath %>/oaorgpage/orgtree.jsp" lookupGroup="">选择部门</a>	</dd>
						</dl>
					</div>
					<div class="formBar">
						<ul>
							<li><div class="buttonActive"><div class="buttonContent"><button type="submit" >提交</button></div></div></li>
							<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
						</ul>
					</div>
				</form>
			</div>	
			<!-- 配置菜单 -->
			<div>
				<form method="post" action="<%=basePath%>/oa/wechat/updateWechatMenu"
					class="pageForm required-validate"
					onsubmit="return validateCallback(this, dialogAjaxDone)">
					<input type="hidden" name="id" value=" " />
					<input type="hidden" name="moduleJson"  id="moduleJson" value=""/>
					<div class="pageFormContent" layoutH="110">
						<div layoutH="146" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
				  			<ul id="treeWechatMenu" name="treeWechatMenu" class="ztree"></ul>
						</div>
						<div id="clickKey" style="display: none">
							<dl>
								<dt>模块名称：</dt>
								<dd>
									<input class="required" name="modelName" id="modelName" type="text"  readonly/>
									<a  href="<%=basePath %>/oa/model/findOaModelPageSelect?select=radio&modelType=auto" lookupGroup="">选择模块</a>		
								</dd>
							</dl>
							<dl>
								<dt>模块ID：</dt>
								<dd>
									<input class="required" name="modelId" id="modelId" type="text" readonly />
								</dd>
							</dl>
							<dl>
								<dt>模块Url：</dt>
								<dd>
									<input  name="stageUrl" id="stageUrl" type="text"  readonly/>
								</dd>
							</dl>
							<div class="formBar">
								<ul>
									<li><div class="buttonActive"><div class="buttonContent"><button type="button" onclick="update_button()">保存</button></div></div></li>
								</ul>
							</div>
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
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
var button = [];
function sub_button(id,modelName,modelId,stageUrl){
	this.id = id;
	this.modelName = modelName;
	this.modelId = modelId;
	this.stageUrl= stageUrl;
}
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
			onRename: onRename,
			onClick: onClick
		}
	};

	var zNodes =[
		{ id:1, pId:0, name:"菜单目录",open:true,target:"none",url:""}
	];
	var log, className = "dark";
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	function beforeEditName(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeEditName ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var zTree = $.fn.zTree.getZTreeObj("treeWechatMenu");
		zTree.selectNode(treeNode);
		return confirm("进入节点 -- " + treeNode.name + " 的编辑状态吗？");
	}
	function beforeRemove(treeId, treeNode) {
		className = (className === "dark" ? "":"dark");
		showLog("[ "+getTime()+" beforeRemove ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name);
		var zTree = $.fn.zTree.getZTreeObj("treeWechatMenu");
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
			var zTree = $.fn.zTree.getZTreeObj("treeWechatMenu");
			setTimeout(function(){zTree.editName(treeNode)}, 10);
			return false;
		}
		return true;
	}
	function onRename(e, treeId, treeNode, isCancel) {
		showLog((isCancel ? "<span style='color:red'>":"") + "[ "+getTime()+" onRename ]&nbsp;&nbsp;&nbsp;&nbsp; " + treeNode.name + (isCancel ? "</span>":""));
	}
	function showRemoveBtn(treeId, treeNode) {
		if(treeNode.id=="1"){
			return false;
		}else{
			return true;
		}
	}
	function showRenameBtn(treeId, treeNode) {
		if(!treeNode.isParent){
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

	var newCount = 1;
	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
			+ "' title='add node' onfocus='this.blur();'></span>";
		sObj.after(addStr);
		var btn = $("#addBtn_"+treeNode.tId);
		if (btn) btn.bind("click", function(){
			var zTree = $.fn.zTree.getZTreeObj("treeWechatMenu");
			zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"模块" + (newCount++)});
			return false;
		});
	};
	function removeHoverDom(treeId, treeNode) {
		$("#addBtn_"+treeNode.tId).unbind().remove();
	};
	function selectAll() {
		var zTree = $.fn.zTree.getZTreeObj("treeWechatMenu");
		zTree.setting.edit.editNameSelectAll =  $("#selectAll").attr("checked");
	}
	function onClick(event, treeId, treeNode, clickFlag) {
		var clickKey=document.getElementById("clickKey");  
		//alert(JSON.stringify(treeNode));
		//alert(treeNode.id + ", "+treeNode.pId+"," + treeNode.name+","+treeNode.isParent);
		
		if(treeNode.isParent || treeNode.id=="1"){
			clickKey.style.display="none"; 
		}else {
			clickKey.style.display=""; 
			document.getElementById("modelName").value ="";
			document.getElementById("modelId").value = "";
			document.getElementById("stageUrl").value = "";
			for(var i=0;i<button.length;i++){
				if(button[i].id==treeNode.id){
				    document.getElementById("modelName").value = button[i].modelName;
					document.getElementById("modelId").value = button[i].modelId;
					document.getElementById("stageUrl").value = button[i].stageUrl;
				}
			}
		}
	}
	function addMenu(){
		var treeObj = $.fn.zTree.getZTreeObj("treeWechatMenu");
	    var node = treeObj.getNodes();
	    var nodes = treeObj.transformToArray(node);
	    var menu = {button:[{id:1,pId:0,modelName:"首页",isParent:false,stageUrl:"",modelId:""}]};
	   
	    for(var i=0; i<nodes.length;i++){
	    	if(nodes[i].isParent == true && nodes[i].id !==0 && nodes[i].id !==1){
	    		menu.button.push({id:nodes[i].id,pId:nodes[i].pId,modelName:nodes[i].name,isParent:true,stageUrl:"",modelId:""});
	    	}else{
	    		for(var j=0;j<button.length;j++){
	    			if(button[j].id == nodes[i].id ){
	    				menu.button.push({id:nodes[i].id,pId:nodes[i].pId,modelName:button[j].modelName,isParent:false,stageUrl:button[j].stageUrl,modelId:button[j].modelId});
	    			}
	    		}
	    	}
	    }
		document.getElementById("moduleJson").value = JSON.stringify(menu);
	}
	function update_button( ){
		var modelId = $('#modelId').val();
		var modelName = $('#modelName').val();
		var stageUrl = $('#modelName').val();
		if(modelName ==""){
				alert("请选择模块");
				return false;
		}
		
		var zTree = $.fn.zTree.getZTreeObj("treeWechatMenu"),
		nodes = zTree.getSelectedNodes(),
		treeNode = nodes[0];
		var id= treeNode.id;
		for (var i=0, l=nodes.length; i<l; i++) {
			nodes[i].name = modelName;
			zTree.updateNode(nodes[i]);
		}
		
		if(button.length>0){
			for(var i=0;i<button.length;i++){
				if(button[i].id==id){
					sub_button = new Object();
					sub_button.id = id;
					sub_button.modelId=$('#modelId').val();
				    sub_button.modelName=$('#modelName').val();
					sub_button.stageUrl=$('#stageUrl').val();
					button.splice(i,1);
					button.splice(i,1,sub_button);
				}else{
					sub_button = new Object();
					sub_button.id = id;
					sub_button.modelId=$('#modelId').val();
				    sub_button.modelName=$('#modelName').val();
					sub_button.stageUrl=$('#stageUrl').val();
					button.push(sub_button);
				}
			}
		}else{
			sub_button = new Object();
			sub_button.id = id;
			sub_button.modelId=$('#modelId').val();
		    sub_button.modelName=$('#modelName').val();
			sub_button.stageUrl=$('#stageUrl').val();
			button.push(sub_button);
		}
	}
	$(document).ready(function(){
		$.fn.zTree.init($("#treeWechatMenu"), setting, zNodes);
		$("#selectAll").bind("click", selectAll);
	});
</script>
</html>