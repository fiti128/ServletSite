package ru.retbansk.jdbc.ivanTest;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import ru.retbansk.jdbc.ivanTest.dto.User;

/**
 * Servlet implementation class BaseServlet
 */
/**
 * @author user
 *
 */
@WebServlet("/BaseServlet")
public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER = "ru.retbansk.jdbc.ivanTest.dto.User";
	private static DataSource dataSource;
	
       
    public static DataSource getDataSource() {
		return dataSource;
	}

	public static void setDataSource(DataSource dataSource) {
		BaseServlet.dataSource = dataSource;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public BaseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			beforeExecute(request,response);
		}
		catch(SQLException e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			beforeExecute(req,resp);
		}
		catch(SQLException e) {
			throw new ServletException(e);
		}
	}
	
	private void beforeExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
				IOException,	SQLException {
				execute(request,response);
	}
	
	protected abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
				IOException,	SQLException;

		protected User getUser(HttpServletRequest request) {
			HttpSession theSession = request.getSession();
			return (User) (theSession == null ? null : theSession.getAttribute(USER));
		}
}