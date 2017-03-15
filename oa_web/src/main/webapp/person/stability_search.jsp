<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
        String basepath = request.getContextPath();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>综治高级检索模式窗口</title>
</head>
<body>
	<div class="pageContent">
		<form method="post" action="<%=basepath %>/oa/stability/getPersonPage" class="pageForm required-validate" onsubmit="return navTabSearch(this);" >
		  <input type="hidden" name="id" value="${person.stability.id }">
			<div class="pageFormContent" layoutH="60" >
		   	<p>
				<label>社区矫正人员：</label>
				<select name="communitycorrection" class="combox"  id="communitycorrection" onchange="checkstability()">
					<option value="">请选择</option>
					<option value="1" ${person.stability.communitycorrection == '1' ? 'selected = "selected"' : '' } >是</option>
					<option value="0" ${person.stability.communitycorrection == '0' ? 'selected = "selected"' : '' }>否</option>
				</select>
			</p>
			<div id="stabone">
			<p>
				<label>社区矫正人员编号：</label>
				<input type="text" name="rectifyid" id="rectifyid" value="${person.stability.rectifyid }" size="30" />
			 </p>
			 <p>
				<label>原羁押场所：</label>
				<input type="text" name="custodyplace" value="${person.stability.custodyplace }" size="30" />
			 </p>
			 <p>
				<label>矫正类别：</label>
				<select name=rectifytype class="combox" >
					<option value="">请选择</option>
					<option value="1" ${person.stability.rectifytype == 1 ? 'selected = "selected"' : '' }>管制</option>
					<option value="2" ${person.stability.rectifytype == 2 ? 'selected = "selected"' : '' }>缓刑</option>
					<option value="3" ${person.stability.rectifytype == 3 ? 'selected = "selected"' : '' }>假释</option>
					<option value="4" ${person.stability.rectifytype == 4 ? 'selected = "selected"' : '' }>暂予监外执行</option>
					<option value="5" ${person.stability.rectifytype == 5 ? 'selected = "selected"' : '' }>剥夺政治权利</option>
				</select>
			</p>
			<p>
				<label>矫正开始日期：</label>
				<input name="rectifystartdate" class="date" type="text" size="30" value="${person.stability.rectifystartdate }" /><a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>矫正结束日期：</label>
				<input name="rectifyenddate" class="date" type="text" size="30" value="${person.stability.rectifyenddate }" /><a class="inputDateButton" href="javascript:;">选择</a>
			</p>
			<p>
				<label>非法集资人员：</label>
				<select name=illegalfund class="combox" >
					<option value="">请选择</option>
					<option value="1" ${person.stability.illegalfund == 1 ? 'selected = "selected"' : '' } >是</option>
					<option value="0" ${person.stability.illegalfund == 0 ? 'selected = "selected"' : '' }>否</option>
				</select>
			</p>
			<p>
				<label>两劳释放人员：</label>
				<select name=reeducationreform class="combox" id="reeducationreform" onchange="checkstability()">
					<option value="">请选择</option>
					<option value="1" ${person.stability.reeducationreform == 1 ? 'selected = "selected"' : '' } >是</option>
					<option value="0" ${person.stability.reeducationreform == 0 ? 'selected = "selected"' : '' }>否</option>
				</select>
			</p>
			<div id="stabtwo">
			<p>
				<label>累惯犯：</label>
				<select name=recidivist class="combox" >
					<option value="">请选择</option>
					<option value="1" ${person.stability.recidivist == 1 ? 'selected = "selected"' : '' } >是</option>
					<option value="0" ${person.stability.recidivist == 0 ? 'selected = "selected"' : '' }>否</option>
				</select>
			</p>
			<p>
				<label>"四史"情况：</label>
				<select name=foursituation class="combox" >
					<option value="">请选择</option>
					<option value="1" ${person.stability.foursituation == 1 ? 'selected = "selected"' : '' } >吸毒史</option>
					<option value="2" ${person.stability.foursituation == 2 ? 'selected = "selected"' : '' }>逃脱史</option>
					<option value="3" ${person.stability.foursituation == 3 ? 'selected = "selected"' : '' } >自杀史</option>
					<option value="4" ${person.stability.foursituation == 4 ? 'selected = "selected"' : '' }>袭警史</option>
				</select>
			</p>
			<p>
				<label>"三涉"情况：</label>
				<select name=threesituation class="combox" >
					<option value="">请选择</option>
					<option value="1" ${person.stability.threesituation == 1 ? 'selected = "selected"' : '' } >涉毒</option>
					<option value="2" ${person.stability.threesituation == 2 ? 'selected = "selected"' : '' }>涉黑</option>
					<option value="3" ${person.stability.threesituation == 3 ? 'selected = "selected"' : '' } >涉枪</option>
				</select>
			</p>
			<p>
				<label>重点上访人员：</label>
				<select name=focuspetitioners class="combox" >
					<option value="">请选择</option>
					<option value="1" ${person.stability.focuspetitioners == 1 ? 'selected = "selected"' : '' } >是</option>
					<option value="0" ${person.stability.focuspetitioners == 0 ? 'selected = "selected"' : '' }>否</option>
				</select>
			</p>
			<p>
				<label>涉核人员：</label>
				<select name=nuclearpersonnel class="combox" >
					<option value="">请选择</option>
					<option value="1" ${person.stability.nuclearpersonnel == 1 ? 'selected = "selected"' : '' } >是</option>
					<option value="0" ${person.stability.nuclearpersonnel == 0 ? 'selected = "selected"' : '' }>否</option>
				</select>
			</p>
			<p>
				<label>参与邪教组织：</label>
				<select name=involvedInCults class="combox" >
					<option value="">请选择</option>
					<option value="1" ${person.stability.involvedInCults == 1 ? 'selected = "selected"' : '' } >是</option>
					<option value="0" ${person.stability.involvedInCults == 0 ? 'selected = "selected"' : '' }>否</option>
				</select>
			</p>
			</div>
		 </div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">搜索</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
   </div>
</body>
</html>