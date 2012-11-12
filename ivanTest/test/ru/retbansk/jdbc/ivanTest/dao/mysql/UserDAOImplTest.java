package ru.retbansk.jdbc.ivanTest.dao.mysql;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.sql.DataSource;
import junit.framework.TestCase;

import org.junit.Test;

import ru.retbansk.jdbc.ivanTest.Role;
import ru.retbansk.jdbc.ivanTest.dao.mysql.UserDAOImpl;
import ru.retbansk.jdbc.ivanTest.dto.User;
import test.MyDataSource;

public class UserDAOImplTest extends TestCase{
	public static final int ID = 7;
	public static final String NAME = "Cypher";
	public static final String PASSWORD = "kill";
	public static final Role ROLE = Role.ADMIN;
	DataSource dataSource = new MyDataSource();
	UserDAOImpl userDaoImpl = new UserDAOImpl(dataSource);
	User userExpected = null;
	List userList = null;
	public static final User userActual = new User();
	static {
		userActual.setName(NAME);
		userActual.setPassword(PASSWORD);
		userActual.setRole(ROLE);
		userActual.setUser_id(ID);
		
	}
	
	@Test
	public void testGetByNameAndPassword() throws NamingException {
	


		try {
			userExpected = null;
			userExpected = userDaoImpl.getByNameAndPassword(NAME, PASSWORD);
			assertEquals(userExpected.getName(), userActual.getName());
			assertEquals(userExpected.getPassword(), userActual.getPassword());
			assertEquals(userExpected.getRole(), userActual.getRole());
			assertEquals(userExpected.getUser_id(), userActual.getUser_id());
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetById() throws SQLException {
		userExpected = null;
		userExpected = userDaoImpl.getById(ID);
		assertEquals(userExpected.getName(), userActual.getName());
		assertEquals(userExpected.getPassword(), userActual.getPassword());
		assertEquals(userExpected.getRole(), userActual.getRole());
		assertEquals(userExpected.getUser_id(), userActual.getUser_id());
	}

	@Test
	public void testSaveUser() throws SQLException {
		userExpected = null;
		userExpected = new User();
		userExpected.setName("testUser");
		userExpected.setPassword("testPassword");
		userExpected.setRole(Role.USER);
		userDaoImpl.saveUser(userExpected);
		User userTemp = userDaoImpl.getByNameAndPassword("testUser", "testPassword");
		assertEquals(userExpected.getName(), userTemp.getName());
		assertEquals(userExpected.getPassword(), userTemp.getPassword());
		assertEquals(userExpected.getRole(), userTemp.getRole());
		
	}

	@Test
	public void testGetUsersOrderByName() throws SQLException {
		if (userList != null) userList = null;
		List<User> userList = new ArrayList<User>();
		userList = (List<User>)userDaoImpl.getUsersOrderByName();
		assertNotNull(userList);
		assertTrue(userList.size() > 2);
		

	}

	@Test
	public void testUpdateUser() throws SQLException {
		userExpected = null;
		userExpected = (User)userDaoImpl.getByNameAndPassword("testUser", "testPassword");
		userExpected.setPassword("testPassword");
		userExpected.setRole(Role.ADMIN);
		userDaoImpl.updateUser(userExpected);
		User userTemp = null;
		userTemp = (User)userDaoImpl.getById(userExpected.getUser_id());
		assertEquals(userExpected.getName(), userTemp.getName());
		assertEquals(userExpected.getPassword(), userTemp.getPassword());
		assertEquals(userExpected.getRole(), userTemp.getRole());
	}

	@Test
	public void testIsExists() throws SQLException {
		assertTrue(userDaoImpl.isExists(userActual.getName()));
	}
	@Test
	public void testDeleteUser() throws SQLException {
		User user = userDaoImpl.getByNameAndPassword("testUser", "testPassword");
		if (user != null) {
			userDaoImpl.deleteUser(user);
		}
		assertNull(userDaoImpl.getById(user.getUser_id()));
	}
}
