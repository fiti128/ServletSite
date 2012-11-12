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
 * Servlet implementation class UserInfo
 */
@WebServlet(urlPatterns="/user_info.html")
public class UserInfo extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER_INFO_JSP="/WEB-INF/jsp/user-info.jsp";
	private static final String ID = "id";
	private UserDAO userDAO;
       
    /**
     * @see BaseServlet#BaseServlet()
     */
    public UserInfo() {
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
		Integer userId = new Integer(request.getParameter(ID));
		if (userId != null) {
			User user = userDAO.getById(userId);
			request.setAttribute("user", user);
		}
		request.getRequestDispatcher(USER_INFO_JSP).forward(request, response);
		
	}

}
