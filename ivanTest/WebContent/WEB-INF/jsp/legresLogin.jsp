<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="javax.naming.Context" import="javax.naming.InitialContext" 
    import="javax.sql.DataSource" import="java.sql.Connection" import="java.sql.Statement" import="java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ivanTest</title>
</head>
<body>
<table border=1>
<%
// получаем JNDI-контекст
Context initContext = new InitialContext();
Context envContext = (Context)initContext.lookup("java:/comp/env");
//из контекста получаем пул соединений
DataSource ds =(DataSource)envContext.lookup("ivanTest");
//а из пула  - соединение
try {
	Connection conn = ds.getConnection();

//создаем запрос
Statement statement = conn.createStatement();
//выполняем его
ResultSet rs = statement.executeQuery("select `role_id`, `name` from `role` order by `role_id`");

// выводим результат
while(rs.next()){
	%>
	<tr>
		<td><%=rs.getInt("role_id") %></td>
		<td><%=rs.getString("name") %></td>
	</tr>
	<%
}
//закрываем объекты - освобождаем ресурсы
rs.close();
statement.close();
//возращаем соединение в пул
conn.close();
} catch (Exception e) {
	System.out.println("In connection");
}

%>
</table>
</body>
</html>