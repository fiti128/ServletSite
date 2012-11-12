package ru.retbansk.jdbc.ivanTest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import ru.retbansk.jdbc.ivanTest.dao.UserDAO;
import ru.retbansk.jdbc.ivanTest.dao.mysql.UserDAOImpl;
import ru.retbansk.jdbc.ivanTest.dto.User;

/**
 * Servlet implementation class UserList
 */
@WebServlet(urlPatterns="/user_list.html")
public class UserList extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_LIST_JSP= "/WEB-INF/jsp/user-list.jsp";
	private UserDAO userDAO;
	

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		userDAO = new UserDAOImpl((DataSource)config.getServletContext().getAttribute(Initialiser.DATA_SOURCE));
//		userDAO = new UserDAOImpl(getDataSource());
	}

	@Override
	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		List<User> theUserList = userDAO.getUsersOrderByName();
		request.setAttribute("users", theUserList);
		request.getRequestDispatcher(USER_LIST_JSP).forward(request, response);
		
	}

}
