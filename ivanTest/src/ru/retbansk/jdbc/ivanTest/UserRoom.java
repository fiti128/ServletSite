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
@WebServlet(urlPatterns="/user_room.html")
public class UserRoom extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_ROOM_JSP = "/WEB-INF/jsp/user-room.jsp";
	private static final String MESSAGE = "ru.retbansk.jdbc.ivanTest.Message";
	private static final String ERROR = "ru.retbansk.jdbc.ivanTest.Error";
	private static final String USER = "ru.retbansk.jdbc.ivanTest.dto.User";
	private static final String EVENT = "event";
	private static final String UPDATE = "update";
	private static final String OLD_PASSWORD = "oldPassword";
	private static final String PASSWORD = "password";

	private static final String REPASSWORD = "rePassword";
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
		if (UPDATE.equals(request.getParameter(EVENT))){
			String oldPassword = request.getParameter(OLD_PASSWORD);
			String password = request.getParameter(PASSWORD);
			String rePassword = request.getParameter(REPASSWORD);
			if (checkPasswords(oldPassword,password,rePassword,request)) {
				userDAO.saveUser((User)request.getSession().getAttribute(USER));
				return;
			}
			return;
		}
		request.getRequestDispatcher(USER_ROOM_JSP).forward(request, response);
		
	}
	private boolean checkPass(String pass) {
		if(pass == null) {
			return false;
		}
		if (pass.trim().length() == 0) {
			return false;
		}
		return true;
	}
	private boolean checkPasswords(String oldPassword, String password, String rePassword,HttpServletRequest request) {
		if (oldPassword.equals(((User)request.getSession().getAttribute(USER)).getPassword()) && password.equals(rePassword) && checkPass(password)) {
			return true;
		}
		return false;
	}
	
}
