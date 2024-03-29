<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./css/commons.css"/>
<link rel="stylesheet" type="text/css" href="./css/extend.css"/>
<title><fmt:message key="title.login"></fmt:message></title>
</head>
<body>
	<jsp:include page="/WEB-INF/include/menu.jsp"></jsp:include>
	<c:url var="action" value="login.html">
		<c:param name="event" value="login"></c:param>
	</c:url>
<form action="${action }" method="post">
	<div class="centerBlock">
		<div class="head">
			<b><fmt:message key="header.identification"/></b>
		</div>
		<div class="body">
			<table class="infoTable">
				<tr>
					<td><fmt:message key="label.login"></fmt:message>:</td>
					<td><input name="login"></input></td>
				</tr>
				<tr>
					<td><fmt:message key="label.password"></fmt:message>:</td>
					<td><input name="password" type="password"></input></td>
				</tr>
				<tr>
					<td></td>
					<td class="section"><input type = "submit" value="<fmt:message key="button.submit"/>"></input></td>
				<tr>
			</table>
		</div>
	</div>
	
</form>
</body>
</html>