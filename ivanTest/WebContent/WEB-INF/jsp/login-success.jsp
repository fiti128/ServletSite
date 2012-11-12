<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="./css/commons.css"></link>
<link rel="stylesheet" type="text/css" href="./css/extend.css"></link>
<title><fmt:message key="title.loginSuccess"></fmt:message></title>
</head>
<body>
	<jsp:include page="/WEB-INF/include/menu.jsp"/>
	<div class="centerBlock">
		<div class="head">
			<b><fmt:message key="header.identification"/></b>
		</div>
		<div class="body" style="text-align:center;">
			<fmt:message key="msg.loginSuccess">
				<fmt:param>
					<b><a href="./user_room.html">
						<c:out value="${sessionScope[\"ru.retbansk.jdbc.ivanTest.dto.User\"].name }"/>
					</a></b>
				</fmt:param>
			</fmt:message>
		</div>
	</div>
	
</body>
</html>