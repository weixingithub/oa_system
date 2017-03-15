<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
var settingorg = {
		async: {
			    enable: true,
				url:baseurl+"/oa/org/getOrgUserMeun",
				autoParam:["id=parentId"],
				otherParam:{"modeltype":"oaorg","reqUrl":"/oa/org/getOrgDetail","reqRel":"orgBox"},
				type:"post",
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
	$.fn.zTree.init($("#treeOrgModel"), settingorg);
});
</script>
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
	
				<div layoutH="70" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
				   <div class="accordionContent">
					    <ul id="treeOrgModel" class="ztree"></ul>
				   </div>
				</div>
				
				<div id="orgBox" class="unitBox" style="margin-left:246px;">
				  <div class="tabs">
						<c:if test="${sessionScope.modelflag.add }">
						<div class="tabsHeader">
							<div class="tabsHeaderContent">
								<ul>
									<li><a href="javascript:;"><span>机构基本信息</span></a></li>
								</ul>
							</div>
						</div>
						 <form method="post" action="<%=basepath %>/oa/org/addOaOrg" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
						<div class="tabsContent">
							<div style="background:#fff">
									<input  type="hidden"  name="freshValue"  value="freshnoc" />
									<input id="optype" type="hidden"  name="optype"  value="add"/>
								    <div  style="height:100%;" >
									<div class="pageFormContent"  layoutH="153" >

									<p>
									   <dl class="nowrap">
										<dt>机构编号：</dt>
										<dd>
										<input name="orgNum" type="text" size="30" />
										</dd>
										</dl>
									</p>
									<p>
									  <dl class="nowrap">
										<dt>机构名称：</dt>
										<dd>
										<input name="orgName" class="required" type="text" size="30"  alt="请输入机构名称"/>
										</dd>
										</dl>
									</p>
									<p>
										<dl class="nowrap">
										  <dt>机构地址：</dt>
										  <dd>
											<select class="combox" name="province" ref="w_combox_city" refUrl="<%=basepath %>/oa/area/city?pvalue={value}">
												<option value="0">所有省市</option>
												<c:forEach items="${plist }"  var="province">
												  <option value="${province.proID }">${province.proName }</option>
												</c:forEach>
											</select>
											<select class="combox" name="city" id="w_combox_city" ref="w_combox_region" refUrl="<%=basepath %>/oa/area/region?cvalue={value}">
												<option value="0">所有城市</option>
											</select>
											<select class="combox" name="region" id="w_combox_region">
												<option value="0">所有区县</option>
											</select>
											</dd>
										</dl>
									</p>
									<p>
									   <dl class="nowrap">
										<dt>详细地址：</dt>
										<dd>
										<input name="orgAddress" type="text" size="60"  />
									     </dl>
									   </dl>
									</p>
									<p>
									  <dl class="nowrap">
										  <dt>机构备注：</dt>
										  <dd><textarea name="orgRemark" cols="80" rows="2" ></textarea></dd>
								      </dl>
									</p>
								</div>
								<div class="formBar">
									<ul>
										<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
									</ul>
								</div>
							</div>
						 </div>
						 </div>
						  </form>
						</c:if>
						<div class="tabsFooter">
							<div class="tabsFooterContent"></div>
						</div>
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