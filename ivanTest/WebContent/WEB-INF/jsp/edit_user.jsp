<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="/WEB-INF/taglib/auth.tld" prefix="a" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="./css/commons.css"></link>
<link rel="stylesheet" type="text/css" href="./css/extend.css"></link>
<title><fmt:message key="title.editUser"></fmt:message></title>
</head>
<body>
	<jsp:include page="/WEB-INF/include/menu.jsp"/>
	<c:url var="action" value="./edit_user.html">
		<c:param name="event" value="update"/>
	</c:url>
	<form action="${action}" method="post">
		<div class="block">
			<div class="head">
				<b><fmt:message key="label.user"/>N${user.user_id }</b>
			</div>
			<div class="body">
				<input type="hidden" name="id" value="${user.user_id }"></input>
				<table class="infoTable">
					<tr>
						<td><fmt:message key="label.id"/></td>
						<td>${user.user_id }</td>
					</tr>
					<tr>
						<td><fmt:message key="label.login"/></td>
						<td><input name="login" type="text" value="${user.name }"></input></td>
					</tr>
					<tr>
						<td><fmt:message key="label.password"/></td>
						<td><input name="password" value="<c:out value="${user.password }"/>"></input></td>
					</tr>
					<tr>
						<td><fmt:message key="label.role"/></td>
						<td><select name="role">
								<c:forEach var="role" items="${roles }">
									<option value="${role.name }"
										<c:if test="${role == user.role} }">selected="selected"
										</c:if>>
										${role.name }
									</option>
								</c:forEach>
						</select></td>
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