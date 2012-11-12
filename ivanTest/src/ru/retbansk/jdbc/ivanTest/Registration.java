package ru.retbansk.jdbc.ivanTest;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import ru.retbansk.jdbc.ivanTest.dao.UserDAO;
import ru.retbansk.jdbc.ivanTest.dao.mysql.UserDAOImpl;
import ru.retbansk.jdbc.ivanTest.dto.User;

/**
 * Servlet implementation class Registration
 */
@WebServlet(
		description = "register  here", 
		urlPatterns = "/registration.html", 
		initParams = { 
					@WebInitParam(name = "registration", value = "noevent", description = "no event now")
		})
public class Registration extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static final String REGISTRATION_SUCCESS_JSP = "/WEB-INF/jsp/registration-success.jsp";
	private static final String REGISTRATION_JSP = "/WEB-INF/jsp/registration.jsp";
	private static final String ERROR = "ru.retbansk.jdbc.ivanTest.Error";
	private static final String USER = "ru.retbansk.jdbc.ivanTest.dto.User";
	private static final String EVENT = "event";
	private static final String LOGIN = "login";
	private static final String PASSWORD = "password";
	private static final String REGISTRATION = "registration";
	private static final String REPASSWORD = "rePassword";
	
			
	private UserDAO userDAO;
       
    /**
     * @see BaseServlet#BaseServlet()
     */
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		User theUser = (User) request.getSession(true).getAttribute(USER);
		if(theUser != null) {
			request.getRequestDispatcher(REGISTRATION_SUCCESS_JSP).forward(request, response);
			return;
		}
		if (REGISTRATION.equals(request.getParameter(EVENT))) {
			String theLogin = request.getParameter(LOGIN);
			String thePassword = request.getParameter(PASSWORD);
			String theRePassword = request.getParameter(REPASSWORD);
			
			if (theLogin == null || theLogin.trim().length() == 0) {
				request.setAttribute(ERROR, "alert.loginIsEmpty");
				request.getRequestDispatcher(REGISTRATION_JSP).forward(request, response);
				return;
			}
			if (thePassword == null || thePassword.trim().length() == 0) {
				request.setAttribute(ERROR, "alert.passwordIsEmty");
				request.getRequestDispatcher(REGISTRATION_JSP).forward(request, response);
				return;
			}
			if (!theRePassword.equals(thePassword)) {
				request.setAttribute(ERROR, "alert.passwordIsntRepassword");
				request.getRequestDispatcher(REGISTRATION_JSP).forward(request, response);
				return;
			}
			if (userDAO.isExists(theLogin)) {
				request.setAttribute(ERROR, "alert.loginIsOccupied");
				request.getRequestDispatcher(REGISTRATION_JSP).forward(request, response);
				return;
			}
			
			User user = new User();
			user.setName(theLogin);
			user.setPassword(thePassword);
			user.setRole(Role.USER);
			userDAO.saveUser(user);
			
			request.getSession(true).setAttribute(USER, user);
			request.getRequestDispatcher(REGISTRATION_SUCCESS_JSP).forward(request, response);
		}
		else {	
			request.getRequestDispatcher(REGISTRATION_JSP).forward(request, response);}
	}

}
