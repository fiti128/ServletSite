package ru.retbansk.jdbc.ivanTest;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Logout
 */
@WebServlet(urlPatterns="/logout.html")
public class Logout extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static final String MAIN_URI = "/main.html";

	@Override
	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		request.getSession(true).invalidate();
		response.sendRedirect(request.getContextPath()+MAIN_URI);
		
	}

}
