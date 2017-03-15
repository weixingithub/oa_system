<link href="<%=urlpath %>/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=urlpath %>/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<!--  <link href="themes/css/tree.css" rel="stylesheet" type="text/css" media="screen"/>-->

<link rel="stylesheet" href="<%=urlpath %>/themes/css/zTreeStyle.css" type="text/css" media="screen">

<link href="<%=urlpath %>/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="<%=urlpath %>/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>

<script src="<%=urlpath %>/js/jquery.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="<%=urlpath %>/xheditor/xheditor-1.2.2.min.js" type="text/javascript"></script>
<script src="<%=urlpath %>/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="<%=urlpath %>/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>


<!-- EChart 图表  -->
<script type="text/javascript" src="<%=urlpath %>/js/echarts.js"></script>


<script src="<%=urlpath %>/js/dwz.core.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.util.date.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.drag.js" type="text/javascript"></script>
<!-- <script src="js/dwz.tree.js" type="text/javascript"></script> -->


<!-- ztree -->
<script type="text/javascript" src="<%=urlpath %>/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="<%=urlpath %>/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="<%=urlpath %>/ztree/js/jquery.ztree.exedit-3.5.js"></script>

<script src="<%=urlpath %>/js/dwz.accordion.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.ui.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.theme.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.navTab.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.tab.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.resize.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.dialog.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.stable.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.ajax.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.pagination.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.database.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.effects.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.panel.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.history.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.combox.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.print.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/dwz.regional.zh.js" type="text/javascript"></script>

<!-- video -->
<link href="<%=urlpath %>/video/video-js.css" rel="stylesheet" type="text/css">
<script src="<%=urlpath %>/video/video.js"></script>
<!-- activiti bpmn -->
<link href="<%=urlpath %>/js/common/plugins/qtip/jquery.qtip.min.css" type="text/css" rel="stylesheet" />
<script src="<%=urlpath %>/js/common/plugins/qtip/jquery.qtip.pack.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/common/plugins/html/jquery.outerhtml.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/workflow.js" type="text/javascript"></script>

<!-- 自定义css -->
<link href="<%=urlpath %>/oacss/configure.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=urlpath %>/themes/css/customStyle.css" rel="stylesheet" type="text/css" media="screen"/>
<!-- 自定义js -->
<script src="<%=urlpath %>/js/mycheckbox.js" type="text/javascript"></script>
<script src="<%=urlpath %>/js/oaplatform.js" type="text/javascript"></script>



<script type="text/javascript">
var checklist="";
var checkall=false;
$(function(){
	var baseurl= '<%=urlpath %>';
	var urlfile = baseurl+"/dwz.frag.xml";
	var themes = baseurl+"/themes";
	var tologin = baseurl+"/login.jsp"
	DWZ.init(urlfile, {
		loginUrl:tologin, 
		//loginTitle:"登录",	 弹出登录对话框
        //loginUrl:"login.html",	// 跳到登录页面
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		keys: {statusCode:"statusCode", message:"message"}, //【可选】
		ui:{hideMode:'offsets'}, //【可选】hideMode:navTab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:themes}); // themeBase 相对于index页面的主题base路径
		}
	});
	
});
</script>