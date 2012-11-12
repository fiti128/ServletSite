package ru.retbansk.jdbc.ivanTest;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Main
 */
@WebServlet(urlPatterns="/main.html")
public class Main extends BaseServlet {
	private static final long serialVersionUID = 1L;
	private static final String MAIN_JSP = "/WEB-INF/jsp/main.jsp";
       
    /**
     * @see BaseServlet#BaseServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
	protected void execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			SQLException {
		request.getRequestDispatcher(MAIN_JSP).forward(request,response);
		
	}

}
