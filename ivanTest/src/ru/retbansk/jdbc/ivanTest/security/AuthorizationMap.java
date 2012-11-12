package ru.retbansk.jdbc.ivanTest.security;

import ru.retbansk.jdbc.ivanTest.Role;

public interface AuthorizationMap {
	/**
	 * ���������� <code>true</code>, ���� <code>role</code> �������������
	 * <code>path</code>.
	 * @param path - �������� {@link String}. <code>null</code> �� ����� ����
	 * �����������. ����� �������� {@link NullPointerException}.
	 * @param role - �������� {@link Role}. <code>null</code> ������������� {@link Role#GUEST}.
	 * @return
	 */
	public boolean isAuthorize(String path,Role role);
}
