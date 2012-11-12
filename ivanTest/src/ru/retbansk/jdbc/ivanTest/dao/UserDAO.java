package ru.retbansk.jdbc.ivanTest.dao;

import java.sql.SQLException;
import java.util.List;

import ru.retbansk.jdbc.ivanTest.dto.User;

public interface UserDAO {

		User getByNameAndPassword(String name, String password) throws SQLException;
		User getById(Integer id) throws SQLException;
		int saveUser(User user) throws SQLException;
		List<User> getUsersOrderByName() throws SQLException;
		int updateUser(User user) throws SQLException;
		boolean isExists(String name) throws SQLException;
		public void deleteUser(User user) throws SQLException;
}
