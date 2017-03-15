
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
 <%
        String basepath = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配置页面</title>

</head>
<script type="text/javascript">
/**
 * 同步加载机构树
 */
var settingConfigureOrg = {
		check: {
			enable: true,
			chkboxType:{ "Y" : "s", "N" : "s" }
		},
		data : {
			simpleData : {
				enable : true,
			},
		}
	};
/***
 * 同步加载角色树
 */
var settingConfigureRole = {
		check: {
			enable: true,
			chkboxType:{ "Y" : "", "N" : "" }
		},
		data : {
			simpleData : {
				enable : true
			}
		}
	};
	//生成机构和角色树
	function createTree() {
		 var id='${id}';
		var zNodes;
		$.ajax({
			url:"<%=basepath%>/oa/sysuser/configureSysUser",
			dataType:"json",
			data:{
				id:id
			},
			type:"post",
			global : false,
			success : function(data) {
				//生成角色树，并将该用户的角色信息显示在页面上
				document.getElementById('roleid').value=data.roleid;
				 var str= '<table>';
				for(var i=0;i<data.rolelist.length;i++){
					 str += "<tr style='height:20px'>";
	    	         var tds = '<td>' + data.rolelist[i].roleName + '</td>';
	    	         str += tds;
	    	     str += '</tr>';
	             }
	    		 str+="</table>";
	    		 $('#roleId').html(str);
				$.fn.zTree.init($("#treeUserRole"), settingConfigureRole, data.roletree);
				//生成机构树，并将该用户的机构信息显示到页面上
				document.getElementById('orgid').value=data.orgid;
				 var str= '<table>';
					for(var i=0;i<data.orglist.length;i++){
						 str += "<tr style='height:20px'>";
		    	         var tds = '<td>' + data.orglist[i].orgName + '</td>';
		    	         str += tds;
		    	     str += '</tr>';
		             }
		    		 str+="</table>";
		    		 $('#orgId').html(str);
				$.fn.zTree.init($("#treeUserOrg"), settingConfigureOrg, data.orgtree);
			},
			error : function(msg) {
				alert("加载失败!");
			}
		});
	}
//获取(取消)机构名称
function checkOrg(value){
	 var id='${id}';
	 var treeObj=$.fn.zTree.getZTreeObj("treeUserOrg"),
     nodes=treeObj.getCheckedNodes(true),
	 orgId="";//所选生效的结构id
	 orgName="";//所选生效的结构名称
	 var arr=new Array();//所选中id的数组
	 if(value=='0'){ 
		 for(var i=0;i<nodes.length;i++){
		        if(!nodes[i].getCheckStatus().half){
		        	 arr.push(nodes[i].id);
		        }
		 }
		 var str= '<table >';
		 for(var j=0;j<nodes.length;j++){ 
			 if(!nodes[j].getCheckStatus().half){
		         if(arr.indexOf(nodes[j].pId)==-1){
		        		 orgName=nodes[j].name;
			        	 orgId+=nodes[j].id + ",";
			        	 str += "<tr style='height:20px'>";
			        	         var tds = '<td>' + orgName + '</td>';
			        	         str += tds;
			        	     str += '</tr>';
		        	 }
			   }
		     }
		 str+="</table>";
		 $('#orgId').html(str);
	     document.getElementById('orgid').value=orgId.substring(0,orgId.length-1);
	 }else if(value=='1'){
		     treeObj.checkAllNodes(false);
		     document.getElementById('orgId').innerHTML='';  
		     document.getElementById('orgid').value='';
	 }
}
    //选中（取消）角色名称
    function checkRole(value){
    	 var treeObj=$.fn.zTree.getZTreeObj("treeUserRole"),
         nodes=treeObj.getCheckedNodes(true),
         roleName="";
    	 roleId="";
    	 if(value=='0'){
    		 var str= '<table>';
    		 for(var i=0;i<nodes.length;i++){
    			 roleName=nodes[i].name;
    			 roleId+=nodes[i].id+",";
    	         str += "<tr style='height:20px'>";
    	         var tds = '<td>' + roleName + '</td>';
    	         str += tds;
    	     str += '</tr>';
             }
    		 str+="</table>";
    		 $('#roleId').html(str);
             document.getElementById('roleid').value=roleId.substring(0,roleId.length-1);
    	 }else if(value=='1'){
    		 treeObj.checkAllNodes(false);
    		 document.getElementById('roleId').innerHTML='';  
   		     document.getElementById('roleid').value='';
    	 }
       }
$(document).ready(function(){
	createTree();//创建配置角色机构树
});
</script>
<body>
<div style="height:45%; margin-top:5px; margin-bottom:6px;">
	<div class="toggleCollapse" style="width:50%"><h2>配置角色</h2></div>
	<div class="accordions" fillSpace="sidebar">
	 <ul id="treeUserRole" class="ztree"></ul>
	
	</div>
	<dl class="accordions btn_div">
		<dd style="width: 82%; height:25px; margin:45% 0px 8% 18%;"><div class="button"><div class="buttonContent"><button  onclick="checkRole(this.value)" value="0"> 配置</button></div></div></dd>
		<dd style="width: 82%; height:25px; margin:45% 0px 8% 18%;"><div class="button"><div class="buttonContent"><button  onclick="checkRole(this.value)" value="1"> 取消</button></div></div></dd>
	</dl>

	<div class="accordions role_div" fillSpace="sidebar">
		<div class="toggleCollapse role_top" ><h2>角色</h2></div>
		<div class="accordions role_add" fillSpace="sidebar" id="roleId">
		</div>
	</div>
</div>	
<div style="height:45%; margin-top:5px; margin-bottom:6px; ">
	<div class="toggleCollapse" style="width:50%"><h2>配置机构</h2></div>
	<div class="accordions" fillSpace="sidebar">
	 <ul id="treeUserOrg" class="ztree"></ul>
	</div>
	<dl class="accordions btn_div">
		<dd style="width: 82%; height:25px; margin:45% 0px 8% 18%;"><div class="button"><div class="buttonContent"><button  type="button" onclick="checkOrg(this.value)" value="0">配置</button></div></div></dd>
		<dd style="width: 82%; height:25px; margin:45% 0px 8% 18%;"><div class="button"><div class="buttonContent"><button  type="button" onclick="checkOrg(this.value)" value="1">取消</button></div></div></dd>
	</dl>
	<div class="accordions role_div" fillSpace="sidebar">
		<div class="toggleCollapse role_top"><h2>机构</h2></div>
		<div class="accordions role_add" fillSpace="sidebar" id="orgId">
		</div>
	</div>
</div>
	<div class="formBar"  >
		<ul>
			<li><div class="buttonActive"><div class="buttonContent">
			<form action="<%=basepath%>/oa/sysuser/configureUser" class="pageForm required-validate" method="post" onsubmit="return validateCallback(this, dialogAjaxDone);">
			 <input type="hidden" name="id" id="id" value="${id }" />
			 <input type="hidden" name="roleid"  id="roleid" />
			 <input type="hidden" name="orgid" id="orgid" / >
			 <button type="submit" value="保存">保存</button>
			</form>
			</div></div></li>
			<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
		</ul>
	</div>
	
</body>
</html>