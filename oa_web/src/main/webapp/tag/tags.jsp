<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String basepath = request.getContextPath();
	String articleTag =request.getParameter("articleTag");
	String[] tags;
	if(articleTag != null && !"".equals(articleTag)){
		tags = articleTag.split(",");
	}else{
		tags = null;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>标签列表</title>
</head>
<body>
	<div class="pageContent">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<dl class="nowrap">
					 <dt>提示：</dt>
					 <dt>
						最多只可添加5个标签，多余无效！
			  		 </dt>
				</dl>
			</div>	
			
			<div class="unit">
				<dl class="nowrap">
					 <dt>标签</dt>
					 <dt>
						<table id="tagTableID" class="list nowrap itemDetail" addButton="新增标签" width="100%">
							<thead>
								<tr>
									<th type="text" size="12"  fieldClass="required">标签名称</th>
									<th type="del" width="30%">操作</th>
								</tr>
							</thead>
							<tbody>
			 					<c:forEach items="<%=tags%>" var="tag">
									<tr>
										<td>
											<input type="text" size="12"  value="${tag}" />
										</td>
										<td>
											<a href="javascript:void(0)" class="btnDel" >删除</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
			  		 </dt>
				</dl>
			</div>			 
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" onclick="tagsdata()">保存</button>
						</div>
					</div>
				</li>
				<li><div class="button">
						<div class="buttonContent">
							<button class="close" type="button">关闭</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
</body>
<script type="text/javascript">
	function tagsdata(){
		var trobjs=document.getElementById("tagTableID").getElementsByTagName("tbody")[0].getElementsByTagName("tr");
		var tagName = "";
	    if(trobjs.length > 0){
		    for(var i=0;i<trobjs.length && i < 5;i++)
		    {
		    	var tdobjs = trobjs[i].getElementsByTagName("td");
		    	var inputobj = tdobjs[0].getElementsByTagName("input");
		    	if(inputobj[0].value != ""){
				    tagName += inputobj[0].value+",";
		    	}
		    }
		    tagName = tagName.substr(0,tagName.length -1); 
		    //请求后台查询标签是否已存在
		    if(tagName != ""){
		    	$.ajax({
		    		dataType:"json",
		    		type:"post",
		    		url:"/oa/tagCategory/equalsTags",
		    		global:false,
		    		data:{tagName:tagName},
		    		success:function(json){
		    			if(json.statusCode ==200){
		    				alertMsg.correct(json.message);
		    				$.bringBack({articleTag:tagName});
		    			}else{
		    				alertMsg.correct(json.message); 
		    				$.bringBack({articleTag:""});
		    			}
		    		}
		    	});
		    }
	    }
		
	}
	
	//异步删除文件
	function deletefile(trobjs){
		var falg = false;
		var tdobjs = trobjs[0].getElementsByTagName("td");
		var inputobj = tdobjs[0].getElementsByTagName("input");
		if(inputobj[0].name.indexOf("fileNewName")!=-1){
			falg = false;
		}else{
			falg = true;
		}
		return falg;
	}
	
/* 	//动态追加表格
	function add(){ 
		var tagNames = getTagName().split(",");
		alert(tagNames);
			for(var i = 0;i< tagNames.length;i++){
				$("#tagTableID tbody").append('<tr><td <input type="text" value="+getTagName[i]+"></td><td><a href="javascript:void(0)" class="btnDel " >删除</a></td></tr>');
			}
	} */
</script>
</html>