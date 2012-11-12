<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="css/commons.css"></link>
<link rel="stylesheet" type="text/css" href="css/extend.css"></link>
<title><fmt:message key="title.registration"></fmt:message></title>
</head>
<body>
	<jsp:include page="/WEB-INF/include/menu.jsp"/>
	<c:url var="action" value="./registration.html">
		<c:param name="event" value="registration"/>
	</c:url>
	<form action="${action}" method="post">
		<div class="centerBlock">
			<div class="head">
				<b><fmt:message key="header.registration"/></b>
			</div>
			<div class="body">
				<table class="infoTable">
					<tr>
						<td><fmt:message key="label.login"/></td>
						<td><input name="login" placeholder="login"></input></td>
					</tr>
					<tr>
						<td><fmt:message key="label.password"/></td>
						<td><input name="password" type="password" placeholder="password"></input></td>
					</tr>
					<tr>
						<td><fmt:message key="label.rePassword"/></td>
						<td><input name="rePassword" type="password" placeholder="rePassword"></input></td>
					</tr>
					<tr>
						<td></td>
						<td class="section"><input type="submit" value="<fmt:message key="button.submit"/>"></input></td>
					</tr>
				</table>
			</div>
		</div>
	</form>
</body>
</html>