<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% response.setHeader("Cache-Control","no-store");%>
 <%
    String urlpath = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>oa系统</title>
<%@ include file="source.jsp"%>
<script type="text/javascript">
var tmpValue="meunvalue";
var baseurl= '<%=urlpath %>';
var checkclean="";
var setting = {
		async: {
				enable: true,
					url:baseurl+"/oa/model/meun",
					autoParam:["id=parentId"],
					otherParam:{"otherParam":"zTreeAsync"},
					dataFilter: filter //异步返回后经过Filter
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback:{
		    onExpand:zTreeEventTarget,
		    onAsyncSuccess:initCompelete,
		    onMouseDown: onMouseDown
		}
};
function onMouseDown(event, treeId, treeNode){
	checkclean="1";
}
function filter(treeId, parentNode, childNodes) {
	return childNodes;
}
function initCompelete(event, treeId, treeNode, msg){
	 initUI("#"+treeId);
}
function zTreeEventTarget(event, treeId, node){
  }
function synchZTreeEventTarget(event, treeId, node){
	initUI("#"+treeId);
}
$(document).ready(function(){
	$.fn.zTree.init($("#treeMenu"), setting);
	$.ajax({
		url:'<%=urlpath %>/oa/reminder/task',
		type:'GET',
		global:false,
		dataType:'Json',
		success: function(data){
			var msg = data.warn;
			 if(msg != 'nothing'){
				alertMsg.info(msg, {
					//okName:'不再提醒',
					okCall: function(){
						/* $.ajax({
								dataType:"json",
								type:"post",
								url:url,
								data:{ modelId:modelId,state:state},
								success:function(json){


								}
						}); */
					}
				});
			}
		}
	});
});
setInterval("taskInterval()",60000*3);//1000为1秒钟

function taskInterval()
{
	$.ajax({
		url:'<%=urlpath %>/oa/reminder/task',
		type:'GET',
		global:false,
		dataType:'Json',
		success: function(data){
			var msg = data.warn;
			 if(msg != 'nothing'){
				 alertMsg.info(msg, {
					//okName:'不再提醒',
					okCall: function(){
					/* $.ajax({
							dataType:"json",
							type:"post",
							url:url,
							data:{ modelId:modelId,state:state},
							success:function(json){


							}
					}); */
					}
				});
			}
		}
	});
}
</script>
      <script>
		(function(){
			$(function () {
				var oLi = $(".ProcessCenter > ul > li");
				var aUl = $(".ProcessCenterContent > div");

				for (var i = 0; i < oLi.length; i++) {
					oLi[i].index = i;
					oLi[i].onmouseover = function () {
						for (var i = 0; i < oLi.length; i++) {
							oLi[i].className = "";
							aUl[i].style.display = "none";
						}
							this.className = "active";
							aUl[this.index].style.display = "block";
					}
				}
			});

			$(function () {
				var oLi = $(".list_title ul li");
				var aUl = $(".list_content");

				for (var i = 0; i < oLi.length; i++) {
					oLi[i].index = i;
					oLi[i].onmouseover = function () {
						for (var i = 0; i < oLi.length; i++) {
							oLi[i].className = "";
							aUl[i].style.display = "none";
						}
							this.className = "active";
							aUl[this.index].style.display = "block";
					}
				}
			});
			
			
		})(jQuery)

      </script>
</head>
<body>
<div id="layout">
	<div id="header">
		<div class="headerNav">
			<a class="" href="<%=urlpath %>/oa/sysuser/main"></a>
			<ul class="nav">
				<li><a href="<%=urlpath %>/login/cancel">退出</a></li>
			</ul>
			<ul class="themeList" id="themeList">
				<li theme="default"><div class="selected">蓝色</div></li>
				<li theme="green"><div>绿色</div></li>
				<li theme="purple"><div>紫色</div></li>
				<li theme="silver"><div>银色</div></li>
				<li theme="azure"><div>天蓝</div></li>
			</ul>
		</div>
	</div>
	<div id="leftside">
		<div id="sidebar_s">
			<div class="collapse">
				<div class="toggleCollapse"><div></div></div>
			</div>
		</div>
		<div id="sidebar">
			<div class="toggleCollapse"><h2>管理菜单</h2><div>收缩</div></div>

			<div class="accordion" fillSpace="sidebar">
				<div class="accordionContent">
					<ul id="treeMenu" class="ztree"></ul>
			   </div>

			</div>
		</div>
	</div>
	<div id="container">
		<div id="navTab" class="tabsPage">
			<div class="tabsPageHeader">
				<div class="tabsPageHeaderContent">
					<ul class="navTab-tab">
						<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
					</ul>
				</div>
				<div class="tabsLeft">left</div>
				<div class="tabsRight">right</div>
				<div class="tabsMore">more</div>
			</div>
			<ul class="tabsMoreList">
				<li><a href="javascript:;">我的主页</a></li>
			</ul>
			<div class="navTab-panel tabsPageContent layoutBox">
				<div class="page unitBox">
						<div class="accountInfo">
							<div class="alertInfo">
								<p><span>日历</span></p>
							</div>
							<div class="left">
								<p><span>我的工作台</span></p>
							</div>
						</div>
						<div class="pageFormContent" layoutH="80" style="margin-right:335px">
							 <div class="ProcessCenter">
								<h3>待办事务</h3>
								<ul>
									 <li class="active">待办</li>
									 <li>已办</li>
								</ul>
								<div class="ProcessCenterContent">
									<div style="display:block;">
										  <ul>
												<li><a href="">贫困捐款</a><span>2016-11-15</span></li>
												<li><a href="">扩建加工厂</a><span>2016-11-17</span></li>
												<li><a href="">差费报销</a><span>2016-11-18</span></li>
												<li><a href="">文章审核</a><span>2016-12-15</span></li>
												<li><a href="">通知审核</a><span>2016-12-01</span></li>
												<li><a href="">奖金批准</a><span>2016-12-10</span></li>
												<li><a href="">合同签收</a><span>2017-01-15</span></li>
										  </ul>
									</div>
									<div>
										<ul>
											<li><a href ="">节日福利</a><span>2016-10-01</span></li>
										</ul>
									</div>
								</div>
							 </div>
							 <div class="newsTitle">
								<h3>快速通道</h3>
								<div class="newsContent">
									<ul>
										<li><a href=""><img src = "<%=urlpath %>/themes/default/images/u1.png" /><span>通讯录</span></a></li>
										<li><a href=""><img src = "<%=urlpath %>/themes/default/images/u11.png" /><span>会议</span></a></li>
										<li><a href=""><img src = "<%=urlpath %>/themes/default/images/u16.png" /><span>收藏夹</span></a></li>
										<li><a href=""><img src = "<%=urlpath %>/themes/default/images/u26.png" /><span>日程</span></a></li>
									</ul>
								</div>
							 </div>
						</div>
						<div style="width:330px;position: absolute;top:62px;right:10px; border-left:solid #b8d0d6 1px;" layoutH="62">
							<iframe width="100%" height="350"  frameborder="0"  src="<%=urlpath %>/date_index.html"></iframe>
							<div class = "phoneBook">
								<h3>通讯录</h3>
								<div class = "search">
									<form action = "" >
										  <input type = "search" placeholder = "搜索姓名/首字母/手机号" >
									</form >
									<img src = "<%=urlpath %>/themes/default/images/search-input_wev8.png" alt = "" >
								</div>
								<div class = "phoneBook_list">
									<div class = "list_title">
										<ul>
											<li class = "active">最近</li>
											<li>联系人</li>
										</ul>
									</div>
									<div class = "list_content" style = "display:block;">
										<ul>
											<li ><a href = "">李强 </a></li>
											<li ><a href = "">王青松 </a></li>
										</ul>
									</div>
									<div class = "list_content">
										<ul>
											<li ><a href = "">李强  </a></li>
											<li ><a href = "">王青松  </a></li>
											<li ><a href = "">刘姣姣</a></li>
											<li ><a href = "">庞兰 </a></li>
											<li ><a href = "">江斌 </a></li>
											<li ><a href = "">潘苗苗</a></li>
											<li ><a href = "">李克强 </a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="footer">oa平台</div>
</body>
</html>