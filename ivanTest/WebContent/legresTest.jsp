<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="javax.naming.Context" import="javax.naming.InitialContext" 
    import="javax.sql.DataSource" import="java.sql.Connection" import="java.sql.Statement"
    import="java.sql.ResultSet" import="ru.retbansk.jdbc.ivanTest.dao.mysql.UserDAOImpl"
    import="ru.retbansk.jdbc.ivanTest.dto.User" import="ru.retbansk.jdbc.ivanTest.dao.UserDAO"
    import="ru.retbansk.jdbc.ivanTest.Role"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ivanTest</title>
</head>
<body>
<%--
public static final String poolNameJndi="ivanTest";
public static final String prefix = "java:comp/env";

private static final String USER = "ru.retbansk.jdbc.ivanTest.dto.User";
private static final String DATA_SOURCE = "dataSource";
public void init(ServletConfig config) throws ServletException {
	super.init(config);
	DataSource dataSource = (DataSource) config.getServletContext().getAttribute(DATA_SOURCE);
}
--%>
<%
// получаем JNDI-контекст

Context initContext = new InitialContext();
Context envContext = (Context)initContext.lookup("java:comp/env");
//из контекста получаем пул соединений
DataSource ds =(DataSource)envContext.lookup("ivanTest");
//а из пула  - соединение
UserDAO userDaoImpl = new UserDAOImpl(ds);
User user = userDaoImpl.getById(1);
user.setName("GoogleUser1");
user.setPassword("killUser1");
user.setRole(Role.lookUp("user"));

userDaoImpl.saveUser(user);
request.setAttribute("user", user);
%>
Username:<%=user.getName() %> 
 Username by EL: ${user.name}<br>
<%=user.getPassword() %><br>
<%=user.getRole() %><br>
<%=user.getUser_id() %><br>


</body>
</html>