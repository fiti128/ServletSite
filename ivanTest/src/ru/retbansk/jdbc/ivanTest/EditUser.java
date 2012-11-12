package ru.retbansk.jdbc.ivanTest;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import ru.retbansk.jdbc.ivanTest.dao.UserDAO;
import ru.retbansk.jdbc.ivanTest.dao.mysql.UserDAOImpl;
import ru.retbansk.jdbc.ivanTest.dto.User;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/edit_user.html")
public class EditUser extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static final String EDIT_USER_JSP = "/WEB-INF/jsp/edit_user.jsp";
	private static final String MESSAGE = "ru.retbansk.jdbc.ivanTest.Message";
	private static final String ERROR = "ru.retbansk.jdbc.ivanTest.Error";
	private static final String UPDATE = "update";
	private static final String ROLE = "role";
	private static final String PASSWORD = "password";
	private static final String LOGIN = "login";
	private static final String EVENT = "event";
	private static final String ID = "id";
	private UserDAO userDAO;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		userDAO = new UserDAOImpl(getDataSource());
	}

	@Override
	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		Integer userId = new Integer(request.getParameter(ID));
		User user = userDAO.getById(userId);
		if(UPDATE.equals(request.getParameter(EVENT))) {
			String theLogin = request.getParameter(LOGIN);
			String thePassword = request.getParameter(PASSWORD);
			Role theRole = Role.lookUp(request.getParameter(ROLE));
			if (!user.getName().equals(theLogin) && userDAO.isExists(theLogin)) {
				request.setAttribute(ERROR, "alert.loginIsOccupied");
			}
			else {
				user.setName(theLogin);
				user.setPassword(thePassword);
				user.setRole(theRole);
				userDAO.updateUser(user);
				request.setAttribute(MESSAGE, "alert.changesApplied");
			}
		}
		request.setAttribute("user",user);
		request.setAttribute("roles", Role.realValues());
		request.getRequestDispatcher(EDIT_USER_JSP).forward(request, response);
		
	}

}
