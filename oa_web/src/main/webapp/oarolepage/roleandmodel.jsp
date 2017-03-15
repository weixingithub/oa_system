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
<script type="text/javascript">
function clearAuthority(){
	document.getElementById("settype").value="clean";
}

</script>
</head>
<body>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li class="line">line</li>
		</ul>
	</div>
	<form  method="post" action="<%=basepath %>/oa/role/setRoleModelInfo" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
	<input  type="hidden"   name="id"  value="${rm.id }"  />
	<input  type="hidden"   name="modelId"  value="${modelId }"  />
	<input  type="hidden"   name="roleId"  value="${roleId }"  />
	<input  type="hidden"   name="parentflag"  value="${rm.isParent }"  />
	<input   type="hidden"   name="mparentId"  value="${mparentId }" />
	<input  id="settype"  type="hidden"   name="settype"  />
	<div layoutH="60">
	<table class="list" width="100%" >
		<tbody>
		
		     <tr>
			     <td>名称</td>
			     <td>操作</td>
			     <td>备注</td>
			</tr>
			 <tr>
			     <td>操作权限</td>
			     <td>
			     <label><input type="checkbox" <c:if test="${rm.dataflag.contains('2') }">checked</c:if> name="dataflag" value="2" />访问</label>
				 <label><input type="checkbox" <c:if test="${rm.dataflag.contains('3') }">checked</c:if> name="dataflag" value="3" />添加</label>
				 <label><input type="checkbox" <c:if test="${rm.dataflag.contains('5') }">checked</c:if> name="dataflag" value="5" />编辑</label>
				 <label><input type="checkbox" <c:if test="${rm.dataflag.contains('7') }">checked</c:if> name="dataflag" value="7" />删除</label>
				 <label><input type="checkbox" <c:if test="${rm.dataflag.contains('11') }">checked</c:if> name="dataflag" value="11" />查看详情</label>
				 <label><input type="checkbox" <c:if test="${rm.dataflag.contains('41') }">checked</c:if> name="dataflag" value="41" />配置</label>
				 <label>
				   <div class="buttonActive">
				       <div class="buttonContent">
				           <button type="button" class="checkboxCtrl" group="dataflag" selectType="invert">反选</button>
				       </div>
				  </div>
				 </label>
			     </td>
			     <td>配置对此模块的相关操作权限</td>
			</tr>
			 <tr>
			     <td>访问类型</td>
			     <td>
				     <label><input type="radio" name="datatype" <c:if test="${rm.datatype.contains('g') }">checked</c:if>  value="g" />所属机构</label>
			         <label><input type="radio" name="datatype" <c:if test="${rm.datatype.contains('u') }">checked</c:if>  value="u"/>所属个人</label>
			     </td>
			     <td>配置对此模块的数据的访问类型<br/>
			                          所属机构:可查看管理员所属机构的，以及其下级机构的数据
			                          所属个人:只能查看此人自己添加的数据
			     </td>
			</tr>
		     <c:if test="${isCheckModel == 1}">
			 <tr>
			     <td>审核角色</td>
			     <td>
				     <label><input type="radio" name="isCheckRole" <c:if test="${rm.isCheckRole==0 }">checked</c:if>  value="0" />否</label>
			         <label><input type="radio" name="isCheckRole" <c:if test="${rm.isCheckRole==1 }">checked</c:if>  value="1"/>是</label>
			     </td>
			     <td>
			                          是否为审核角色
			     </td>
			</tr>
			</c:if>
		</tbody>
	</table>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit">提交</button></div></div></li>
			<li><div class="buttonActive"><div class="buttonContent"><button type="submit"  onclick="clearAuthority()">撤销</button></div></div></li>
		</ul>
	</div>
	</form>
</div>
</body>
</html>