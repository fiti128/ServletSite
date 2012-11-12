<%@ page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="/WEB-INF/taglib/auth.tld" prefix="a" %>

<div class="menu">
	<a:auth path="/main.html">
		<a href="./main.html" class="toLeft"> <fmt:message key="link.main"/></a>
	</a:auth>
	<a:auth path="/user_list.html">
		<a href="./user_list.html" class="toLeft"> <fmt:message key="link.list"/></a>
	</a:auth>
	<a:auth path="/registration.html">
		<a href="./registration.html" class="toRight"> <fmt:message key="link.registration"/></a>
	</a:auth>
	<a:auth path="guest">
		<a href="./login.html" class="toRight"> <fmt:message key="link.login"/></a>
	</a:auth>
	<a:auth path="/logout.html">
		<a href="./logout.html" class="toRight"> <fmt:message key="link.logout"/></a>
	</a:auth>
	<a:auth path="/user_room.html">
		<a href="./user_room.html" class="toRight"> <c:out value="${sessionScope[\"ru.retbansk.jdbc.ivanTest.dto.User\"].name }"/></a>
	</a:auth>
	
</div>
<c:if test="${not empty requestScope['ru.retbansk.jdbc.ivanTest.Message']}">
	<div class="message">
		<fmt:message key="${requestScope['ru.retbansk.jdbc.ivanTest.Message'] }"/>
	</div>
</c:if>
<c:if test="${not empty requestScope['ru.retbansk.jdbc.ivanTest.Warning']}">
	<div class="warning">
		<fmt:message key="${requestScope['ru.retbansk.jdbc.ivanTest.Warning']}"/>
	</div>
</c:if>
<c:if test="${not empty requestScope['ru.retbansk.jdbc.ivanTest.Error']}">
	<div class="error">
		<fmt:message key="${requestScope['ru.retbansk.jdbc.ivanTest.Error']}"/>
	</div>
</c:if>