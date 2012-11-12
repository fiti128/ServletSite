package ru.retbansk.jdbc.ivanTest.filters;

import java.io.File;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ru.retbansk.jdbc.ivanTest.Role;
import ru.retbansk.jdbc.ivanTest.dto.User;
import ru.retbansk.jdbc.ivanTest.security.XMLAuthorizationMap;

/**
 * Servlet Filter implementation class AuthFilter
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

    private static final String AUTHORIZATION_MAP = "ru.retbansk.jdbc.ivanTest.security.AuthorizationMap";
	private XMLAuthorizationMap authorizationMap;

	/**
     * Default constructor. 
     */
    public AuthFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest theRequest = (HttpServletRequest) request;
		HttpServletResponse theResponse = (HttpServletResponse) response;
		String path = theRequest.getServletPath();
		Role role = null;
		HttpSession session = theRequest.getSession();
		User user = null;
		if (session != null) {
			user = (User) session.getAttribute("ru.retbansk.jdbc.ivanTest.dto.User");
			role = (user == null) ? null : user.getRole();
		}
		if (!authorizationMap.isAuthorize(path,role)) {
			// отправляем его на соответствующую страницу
			if (user == null) {
				// если пользователь не авторизован (GUEST).
				theResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				return;
			} else {
				// если пользователь авторизован
				theResponse.sendError(HttpServletResponse.SC_FORBIDDEN);
			}
			
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String pathname= fConfig.getServletContext().getRealPath("/WEB-INF/security-config.xml");
		try {
			authorizationMap = new XMLAuthorizationMap(new File(pathname));
			fConfig.getServletContext().setAttribute(AUTHORIZATION_MAP, authorizationMap);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("init authorization failed.",e);
		}
	}

}
