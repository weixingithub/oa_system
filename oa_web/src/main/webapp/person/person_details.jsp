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
<title>Insert title here</title>
</head>
<body>
	<div class="pageFormContent" layoutH="70">
		<fieldset>
			<legend>基本信息</legend>
			<div style="width: 70%; float: left;">
				<p>姓名：${person.name }</p>
				<p>曾用名：${person.oldname }</p>
				<p>
					性别：
					<c:if test="${person.sex == 0}">男</c:if>
					<c:if test="${person.sex == 1}">女</c:if>
				</p>
				<p>身份证号：${person.idnumber}</p>
				<p>出生日期：${person.birthdate }</p>
				<p>
					民族：
					<c:if test="${person.nation == 1}">汉族</c:if>
					<c:if test="${person.nation == 2}">少数民族</c:if>
				</p>
				<p>
					婚姻状况：
					<c:if test="${person.marital_status == 1 }">未婚</c:if>
					<c:if test="${person.marital_status == 2}">已婚</c:if>
					<c:if test="${person.marital_status == 3 }">离异</c:if>
					<c:if test="${person.marital_status == 4}">丧偶</c:if>
				</p>
				<p>
					血型 ：
					<c:if test="${person.bloodtype==1}">A型</c:if>
					<c:if test="${person.bloodtype==2}">B型</c:if>
					<c:if test="${person.bloodtype==3}">AB型</c:if>
					<c:if test="${person.bloodtype==4}">O型</c:if>
					<c:if test="${person.bloodtype==5}">其它</c:if>
				</p>
				<p>身高：${person.tall }</p>
				<p>联系方式：${person.contact }</p>
				<p>
					户口性质：
					<c:if test="${person.residenttype == 1 }">农户</c:if>
					<c:if test="${person.residenttype == 2 }">非农户</c:if>
				</p>
				<p>
					政治面貌：
					<c:if test="${person.political==1}">党员</c:if>
					<c:if test="${person.political==2}">团员</c:if>
					<c:if test="${person.political==3}">群众</c:if>
					<c:if test="${person.political==4}">其它</c:if>
				</p>
				<p>户籍地： ${provice.proName }&nbsp;${city.cityName }&nbsp;${dis.disName }
				</p>
				<p>户籍详细地址：${person.registry_place }</p>
				<p>现居详细地址：${person.current_address }</p>
				<p>所属社区：${person.orgName}</p>
			</div>
			<div
				style="width: 150px; float: right; margin-right: 15px; height: 240px;">
				<div style="width: 150px; height: 200px;">
					<c:if test="${person.personUrl==null || person.personUrl ==''}">
						<img id="portraitimageId" alt=""
							src="<%=basepath%>/themes/default/images/unllpic.png" width="150"
							height="200">
					</c:if>
					<c:if test="${person.personUrl!=null && person.personUrl !=''}">
						<img id="portraitimageId" alt="" src="${person.personUrl }"
							width="150" height="200">
					</c:if>
				</div>
			</div>
		</fieldset>
		<fieldset>
			<legend>特殊信息</legend>
			<p>
				是否扶贫对象：
				<c:if test="${person.poverty == 1 }">是</c:if>
				<c:if test="${person.poverty == 0 }">否</c:if>
			</p>
			<p>
				人员类别：
				<c:if test="${person.category == 0 }">低收入</c:if>
				<c:if test="${person.category == 1 }">六十年代精简</c:if>
				<c:if test="${person.category == 2 }">城市困难群众</c:if>
				<c:if test="${person.category == 3 }">农村困难群众</c:if>
				<c:if test="${person.category == 4 }">农村劳模</c:if>
				<c:if test="${person.category == 5 }">重点优抚对象</c:if>
				<c:if test="${person.category == 6 }">因病致残对象</c:if>
				<c:if test="${person.category == 7 }">见义勇为人员</c:if>
			</p>
			<p>
				人口一致标识：
				<c:if test="${person.uniformIdentification == 1 }">是</c:if>
				<c:if test="${person.uniformIdentification == 0 }">否</c:if>
			</p>
			<p>
				本村户籍：
				<c:if test="${person.village_household == 1 }">是</c:if>
				<c:if test="${person.village_household == 0 }">否</c:if>
			</p>
			<p>
				辖区内：
				<c:if test="${person.jurisdiction == 1 }">是</c:if>
				<c:if test="${person.jurisdiction == 0 }">否</c:if>
			</p>

			<p>
				村集体经济分配：
				<c:if test="${person.economicdistribution == 1 }">是</c:if>
				<c:if test="${person.economicdistribution == 0 }">否</c:if>
			</p>
			<p>
				嫁城女：
				<c:if test="${person.citywoman == 1 }">是</c:if>
				<c:if test="${person.citywoman == 2 }">否</c:if>
			</p>
			<p>职业：${person.occupation }</p>
			<p>宗教信仰：${person.religion }</p>
			<p>网格：${person.gridid }</p>
		</fieldset>
	</div>
	<div class="formBar">
		<ul>
			<li><div class="button">
					<div class="buttonContent">
						<button type="button" class="close">关闭</button>
					</div>
				</div></li>
		</ul>
	</div>
	</form>
	</div>
</body>
</html>