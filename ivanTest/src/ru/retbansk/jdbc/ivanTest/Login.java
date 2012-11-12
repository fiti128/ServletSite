package ru.retbansk.jdbc.ivanTest;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class Login
 */
@WebServlet(urlPatterns="/login.html")
public class Login extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static final String LOGIN = "login";
	private static final String EVENT = "event";
	private static final String PASSWORD = "password";
	private static final String LOGIN_SUCCESS_JSP="/WEB-INF/jsp/login-success.jsp";
	private static final String LOGIN_JSP="WEB-INF/jsp/login.jsp";
	
	private static final String ERROR = "ru.retbansk.jdbc.ivanTest.Error";
	private static final String USER = "ru.retbansk.jdbc.ivanTest.dto.User";
	private UserDAO userDAO;
	private User theUser;
    /**
     * @see BaseServlet#BaseServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		userDAO = new UserDAOImpl((DataSource)config.getServletContext().getAttribute(Initialiser.DATA_SOURCE));
		// or you can userDAO = new UserDAOImpl(BaseServlet.getDataSource()); The result will be the same
	}

	@Override
	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		if (request.getSession().getAttribute(USER) != null) {
			request.getRequestDispatcher(LOGIN_SUCCESS_JSP).forward(request, response);
			return;
		}
		if(LOGIN.equals(request.getParameter(EVENT))) {
			String theLogin = request.getParameter(LOGIN);
			String thePaswword = request.getParameter(PASSWORD);
			theUser = userDAO.getByNameAndPassword(theLogin, thePaswword);
			if(theUser == null) {
				request.setAttribute(ERROR, "alert.dontLoged");
				request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
				return;
			}
			request.getSession(true).setAttribute(USER, theUser);
			request.getRequestDispatcher(LOGIN_SUCCESS_JSP).forward(request, response);
		}
		else request.getRequestDispatcher(LOGIN_JSP).forward(request, response);
		
	}

}
