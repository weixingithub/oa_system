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
</head>
<body>
<div class="pageContent" style="padding:5px">
	<div class="tabs">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>页面布局</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<!--首页页面布局  style="border:0px red solid;"-->
			<div>
					<div class="pageFormContent" layoutH="120"  >
						<div  style="border:0px yellow solid; width: 90%; height:auto; float: left;">
							<div style="border:0px red solid; width: 55%; height:150px; float: left;">
								<!-- 轮播 -->
								<div class="tabs" currentIndex="0" eventType="click" style="width:100%;">
									<div class="tabsHeader">
										<div class="tabsHeaderContent">
											<ul>
												<li><a href="javascript:;"><span>图片播放</span></a></li>
											</ul>
										</div>
									</div>
									<div class="tabsContent" style="height:110px;">
										<div >
											<h5>模块属性{轮播图:rotate}</h5>
											<h5>位置属性{左上:1-1}</h5>
											<input  name="rotate" id="rotate" type="hidden" value="rotate,1-1" />
										</div>
									</div>
									<div class="tabsFooter">
										<div class="tabsFooterContent"></div>
									</div>
								</div>
							</div>
							<div style="border:0px red solid; width: 30%; height:150px; float: left; margin-left: 15px;">
								<!-- 实时新闻 -->
								<div class="tabs" currentIndex="0" eventType="click" style="width:100%;">
									<div class="tabsHeader">
										<div class="tabsHeaderContent">
											<ul>
												<li><a href="javascript:;"><span>新闻栏目</span></a></li>
											</ul>
										</div>
									</div>
									<div class="tabsContent" style="height:110px;">
										<div>
											<h5>模块属性{新闻:new}</h5>
											<h5>位置属性{右上:1-2}</h5>
											<input  name="new" id="new" type="hidden" value="new"/>
										</div>
									</div>
									<div class="tabsFooter">
										<div class="tabsFooterContent"></div>
									</div>
								</div>
							</div>
						</div>
						<div  style="border:0px green solid; width: 90%; height:auto; float: left; margin: 10px 0 0 0">
							<div style="border:0px red solid; width: 55%; height:220px; float: left;">
								<div style="border:0px red solid; width: 49%; height:220px; float: left;">
									<div  style="border:0px red solid; width: 100%; height:100px;">
										<div class="tabs" currentIndex="0" eventType="click" style="width:100%;">
											<div class="tabsHeader">
												<div class="tabsHeaderContent">
													<ul>
														<li><a href="javascript:;"><span>图片栏目</span></a></li>
													</ul>
												</div>
											</div>
											<div class="tabsContent" style="height:70px;">
												<div>
													<h5>模块属性{图片:picture}</h5>
													<h5>位置属性{中左:2-1}</h5>
													<input  name="picture" id="picture" type="hidden" value=""/>
												</div>
											</div>
											<div class="tabsFooter">
												<div class="tabsFooterContent"></div>
											</div>
										</div>
									</div>
									<div  style="border:0px red solid; width: 100%; height:100px; margin: 10px 0 0 0">
									<!-- 网上办事 -->
										<div class="tabs" currentIndex="0" eventType="click" style="width:100%;">
											<div class="tabsHeader">
												<div class="tabsHeaderContent">
													<ul>
														<li><a href="javascript:;"><span>查询栏目</span></a></li>
													</ul>
												</div>
											</div>
											<div class="tabsContent" style="height:70px;">
												<div>
													<h5>模块属性{查询:query}</h5>
													<h5>位置属性{左下:3-1}</h5>
													<input  name="query" id="query" type="hidden" value="" />
												</div>
											</div>
											<div class="tabsFooter">
												<div class="tabsFooterContent"></div>
											</div>
										</div>
									</div>
								</div>
								<div style="border:0px red solid; width: 49%; height:220px; float: right;">
									<div  style="border:0px red solid; width: 100%; height:100px;">
										<!-- 视频模块 -->
										<div class="tabs" currentIndex="0" eventType="click" style="width:100%;">
											<div class="tabsHeader">
												<div class="tabsHeaderContent">
													<ul>
														<li><a href="javascript:;"><span>视频栏目</span></a></li>
													</ul>
												</div>
											</div>
											<div class="tabsContent" style="height:70px;">
												<div>
													<h5>模块属性{视频:video}</h5>
													<h5>位置属性{正中:2-2}</h5>
													<input  name="video" id="video" type="hidden"  value=""/>
												</div>
											</div>
											<div class="tabsFooter">
												<div class="tabsFooterContent"></div>
											</div>
										</div>
									</div>
									<div  style="border:0px red solid; width: 100%; height:100px; margin: 10px 0 0 0">
										<!-- 公共服务 -->
										<div class="tabs" currentIndex="0" eventType="click" style="width:100%;">
											<div class="tabsHeader">
												<div class="tabsHeaderContent">
													<ul>
														<li><a href="javascript:;"><span>服务栏目</span></a></li>
													</ul>
												</div>
											</div>
											<div class="tabsContent" style="height:70px;">
												<div>
													<h5>模块属性{服务:server}</h5>
													<h5>位置属性{中下:3-2}</h5>
													<input name="server" id="server" type="hidden" value="" />
												</div>
											</div>
											<div class="tabsFooter">
												<div class="tabsFooterContent"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div style="border:0px red solid; width: 30%; height:220px; float: left; margin-left: 15px;">
								<div  style="border:0px red solid; width: 100%; height:100px;">
									<!-- 通知公告 -->
									<div class="tabs" currentIndex="0" eventType="click" style="width:100%;">
										<div class="tabsHeader">
											<div class="tabsHeaderContent">
												<ul>
													<li><a href="javascript:;"><span>公告栏目</span></a></li>
												</ul>
											</div>
										</div>
										<div class="tabsContent" style="height:70px;">
											<div>
												<h5>模块属性{公告:notice}</h5>
												<h5>位置属性{中右:2-3}</h5>
												<input name="notice" id="notice" type="hidden" value="" />
											</div>
										</div>
										<div class="tabsFooter">
											<div class="tabsFooterContent"></div>
										</div>
									</div>
								</div>
								<div  style="border:0px red solid; width: 100%; height:100px; margin: 10px 0 0 0">
									<!-- 友情链接 -->
									<div class="tabs" currentIndex="0" eventType="click" style="width:100%;">
										<div class="tabsHeader">
											<div class="tabsHeaderContent">
												<ul>
													<li><a href="javascript:;"><span>链接栏目</span></a></li>
												</ul>
											</div>
										</div>
										<div class="tabsContent" style="height:70px;">
											<div>
												<h5>模块属性{链接:hyperlink}</h5>
												<h5>位置属性{右下:3-3}</h5>
												<input  name="hyperlink" id="hyperlink" type="hidden" value="" />
											</div>
										</div>
										<div class="tabsFooter">
											<div class="tabsFooterContent"></div>
										</div>
									</div>
								</div>
							</div>
						</div>  
					</div>
					<div class="formBar">
						<ul>
						<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
						</ul>
					</div>
			</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
</div>
</body>
<script type="text/javascript">
</script>
</html>