<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
var settingOrgUser = {
		async: {
			    enable: true,
				url:baseurl+"/oa/org/getOrgUserMeun",
				autoParam:["id=parentId"],
				otherParam:{"modeltype":"sysuser","reqUrl":"/oa/sysuser/findSysUserPage","reqRel":"sysUserBox"},
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
	$.fn.zTree.init($("#treeOrg"), settingOrgUser);
});
</script>

<style type="text/css">
	ul.rightTools {float:right; display:block;}
	ul.rightTools li{float:left; display:block; margin-left:5px}
</style>
<body>
<div class="pageContent" style="padding:5px">
    <div class="divider"></div>
	<div class="tabs">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>机构菜单</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<div>
	
				<div layoutH="60" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
				   <div class="accordionContent">
					    <ul id="treeOrg" class="ztree"></ul>
				   </div>
				</div>
				
				<div id="sysUserBox" class="unitBox" style="margin-left:246px;">
					<!--#include virtual="list1.html" -->
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