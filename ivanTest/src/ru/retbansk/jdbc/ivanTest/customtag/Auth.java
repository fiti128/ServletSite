package ru.retbansk.jdbc.ivanTest.customtag;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.jstl.core.ConditionalTagSupport;

import ru.retbansk.jdbc.ivanTest.Role;
import ru.retbansk.jdbc.ivanTest.dto.User;
import ru.retbansk.jdbc.ivanTest.security.AuthorizationMap;

public class Auth extends ConditionalTagSupport {

	/**
	 * МЕТОД condition()  возвращает true, если пользователь зарегистрирован в системе
	 */
	private String path;
	
	private static final long serialVersionUID = 1L;
	private static final String AUTHORIZATION_MAP="ru.retbansk.jdbc.ivanTest.security.AuthorizationMap";
	private static final String USER = "ru.retbansk.jdbc.ivanTest.dto.User";

	public void setPath(String path) {
		this.path = path;
	}
	@Override
	protected boolean condition() throws JspTagException {
		AuthorizationMap map = (AuthorizationMap) pageContext.getServletContext().getAttribute(AUTHORIZATION_MAP);
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) (session != null ? session.getAttribute(USER) : null);
		Role role = (user == null ? null : user.getRole());
		
		return map.isAuthorize(path, role);
	}

}
