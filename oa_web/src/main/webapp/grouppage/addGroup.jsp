<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
        String basepath = request.getContextPath();
 %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<SCRIPT type="text/javascript">
		var setting = {
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				//beforeCheck: beforeCheck,
				onCheck: onCheck
			}
		};
		var zNodes;
		var treeids='${modeId}';
		$(document).ready(function(){
			$.ajax({
				url:'<%=basepath %>/oa/model/addgroupmeun?modeId=${modeId}',
				type:'GET',
				global:false,
				dataType:'Json',
				success: function(data){
					zNodes = eval("("+data+")");
					$.fn.zTree.init($("#groupTree"), setting, zNodes);
					var zTree = $.fn.zTree.getZTreeObj("groupTree");
					var type = { "Y":"ps", "N":"ps"};
					zTree.setting.check.chkboxType = type;
					zTree.setting.check.autoCheckTrigger =true;
				}
			});
		});
		/*  function beforeCheck(treeId, treeNode){
			
		}  */
		function onCheck(e, treeId, treeNode) {
			var treeObj=$.fn.zTree.getZTreeObj("groupTree"),
            nodes=treeObj.getCheckedNodes(true);
			treeids ="";
            for(var i=0;i<nodes.length;i++){
            	treeids+=nodes[i].id + ",";
            }
            treeids = treeids.substring(0, treeids.length-1);
		}	
		function setCheckValue(){
			document.getElementById("modelIds").value=treeids;
			var userId = document.getElementsByName("userId");
			var ids="";
			for(var i=0;i<userId.length;i++){
				ids=ids+userId[i].value+",";
			}
			ids = ids.substring(0, ids.length-1);
			document.getElementById("userIds").value = ids;
			return true;
		}
	</SCRIPT>
<body>
<div class="pageContent">
	<form method="post" action="<%=basepath %>/oa/group/addgroup" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
	    <input name="modelIds" type="hidden" id="modelIds"/>
	    <input name="id" type="hidden" value="${groupInfo.id }"/>
	    <input name="userIds" type="hidden" id="userIds"/>
	    <c:forEach  items="${groupInfo.users }"  var="user">
	       <input  name="userId" type="hidden"  value="${user.id }" />
	    </c:forEach>
		<div class="pageFormContent nowrap" layoutH="56">
			<dl>
				<dt>群组编号：</dt>
				<dd>
				<input name="groupNum" type="text" size="30" value="${groupInfo.groupNum }" class="required"  alt="请输入群组编号"/>
				</dd>
			</dl>
			<dl>
				<dt>群组名称：</dt>
				<dd>
				<input name="groupName" type="text" size="30" value="${groupInfo.groupName }" class="required"  alt="请输入群组名称"/>
				</dd>
			</dl>
			<dl>
			<dt>权限配置：</dt>
			<dd>
			 <div class="content_wrap">
				<div class="zTreeDemoBackground left">
					<ul id="groupTree" class="ztree"></ul>
				</div>
		    </div>
		    <dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button  onclick="return setCheckValue();" type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
</body>
</html>