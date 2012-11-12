<%@page pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="/WEB-INF/taglib/auth.tld" prefix="a" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="./css/commons.css" />
<link rel="stylesheet" type="text/css" href="./css/extend.css" />
<title><fmt:message key="title.userList" /></title>
</head>
<body>
<jsp:include page="/WEB-INF/include/menu.jsp" />
<table class="list">
	<thead>
		<tr>
			<a:auth path="admin">
				<td><fmt:message key="label.id" /></td>
			</a:auth>
			<td><fmt:message key="label.name" /></td>
			<a:auth path="admin">
				<td><fmt:message key="label.role" /></td>
			</a:auth>
			<a:auth path="/edit_user.html">
				<td />
			</a:auth>
			<td class="total" />
		</tr>
	</thead>
	<c:set var="switcher" value="even" />
	<c:forEach var="user" items="${users}" varStatus="switcher">
		<tr class="${switcher.count%2==0 ? 'even' : 'odd'}">
			<a:auth path="admin">
				<td>${user.user_id}</td>
			</a:auth>
			<c:url var="href" value="./user_info.html" ><c:param name="id" value="${user.user_id}" /></c:url>
			<td><a href="<c:out value="${href}" />"><c:out value="${user.name}" /></a></td>
			<a:auth path="admin">
				<td><c:out value="${user.role.name}" /></td>
			</a:auth>
			<a:auth path="/edit_user.html">
				<c:url var="href" value="./edit_user.html" ><c:param name="id" value="${user.user_id}" /></c:url>
				<td><a href="<c:out value="${href}" />"><fmt:message key="link.edit" /></a></td>
			</a:auth>
			</td>
		</tr>
	</c:forEach>
</table>
</body>
</html>