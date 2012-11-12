package ru.retbansk.jdbc.ivanTest.security;

import ru.retbansk.jdbc.ivanTest.Role;

public interface AuthorizationMap {
	/**
	 * ¬озвращает <code>true</code>, если <code>role</code> соответствует
	 * <code>path</code>.
	 * @param path - экземл€р {@link String}. <code>null</code> не может быть
	 * использован. Ѕудет получено {@link NullPointerException}.
	 * @param role - экземл€р {@link Role}. <code>null</code> соответствует {@link Role#GUEST}.
	 * @return
	 */
	public boolean isAuthorize(String path,Role role);
}
