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
<style type="text/css">
	ul.rightTools {float:right; display:block;}
	ul.rightTools li{float:left; display:block; margin-left:5px}
</style>
<style type="text/css">
	.ztree li span.button.pIcon01_ico_open{margin-right:2px; background: url(../../themes/css/img/diy/1_open.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
	.ztree li span.button.pIcon01_ico_close{margin-right:2px; background: url(../../themes/css/img/diy/1_close.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
	.ztree li span.button.pIcon02_ico_open, .ztree li span.button.pIcon02_ico_close{margin-right:2px; background: url(../../themes/css/img/diy/2.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
	.ztree li span.button.icon01_ico_docu{margin-right:2px; background: url(../../themes/css/img/diy/3.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
	.ztree li span.button.icon02_ico_docu{margin-right:2px; background: url(../../themes/css/img/diy/4.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
	.ztree li span.button.icon03_ico_docu{margin-right:2px; background: url(../../themes/css/img/diy/5.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
	.ztree li span.button.icon04_ico_docu{margin-right:2px; background: url(../../themes/css/img/diy/6.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
	.ztree li span.button.icon05_ico_docu{margin-right:2px; background: url(../../themes/css/img/diy/7.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
	.ztree li span.button.icon06_ico_docu{margin-right:2px; background: url(../../themes/css/img/diy/8.png) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
</style>

<script type="text/javascript">
var websiteId=${website.id};
var layoutTreesetting = {
	data: {
		simpleData: {
			enable: true
		}
	},
	callback: {
		 onExpand:synchZTreeEventTarget
	}
};
var layoutTreezNodes =[
	{ id:1, pId:0, name:"网站配置", open:true, iconSkin:"pIcon01",target:"none",url:""},
	{ id:11, pId:1, name:"选择主题",rel:"layoutPage", iconSkin:"icon01",target:"ajax",url:"/oa/themes/findThemesPagejbsxBox?websiteId="+websiteId},
	{ id:12, pId:1, name:"选择导航",rel:"layoutPage", iconSkin:"icon02",target:"ajax",url:"/oa/menu/findMenuPagejbsxBox?websiteId="+websiteId},
/* 	{ id:13, pId:1, name:"选择页面",rel:"layoutPage", iconSkin:"icon03",target:"ajax",url:"/oa/layout/findLayoutPagejbsxBox?websiteId="+websiteId},
	{ id:14, pId:1, name:"配置插件",rel:"layoutPage", iconSkin:"icon04",target:"ajax",url:"/oa/website/websiteByLayout?configType=plugin&websiteId="+websiteId},
	{ id:15, pId:1, name:"配置模块",rel:"layoutPage", iconSkin:"icon05",target:"ajax",url:"/oa/website/websiteByLayout?configType=module&websiteId="+websiteId}, */
	{ id:16, pId:1, name:"预览效果",rel:"layoutPage", iconSkin:"icon06",target:"none",url:""}
];
$(document).ready(function(){
	$.fn.zTree.init($("#layoutTree"), layoutTreesetting, layoutTreezNodes);
});
</script>
</head>
<body>
<div class="pageContent" style="padding:5px">
	<div class="tabs">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>网站配置</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<div>
				<div class="pageFormContent" layoutH="70" >
					<div layoutH="110" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
					    <ul id="layoutTree" name="layoutTree" class="ztree"></ul>
					</div>
					<div id="layoutPage" class="unitBox" style="margin-left:246px;">
						<!--#include virtual="list1.html" -->
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