package ru.retbansk.jdbc.ivanTest.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import ru.retbansk.jdbc.ivanTest.Role;
import ru.retbansk.jdbc.ivanTest.dao.UserDAO;
import ru.retbansk.jdbc.ivanTest.dto.User;

public class UserDAOImpl implements UserDAO {
private DataSource dataSource;
public UserDAOImpl(DataSource dataSource) {
	this.dataSource = dataSource;
}
@Override
public User getByNameAndPassword(String name, String password)
		throws SQLException {
	String query = 	"SELECT `user`.`user_id`, `user`.`name`, `user`.`password`, `role`.`name`" +
					"FROM `user` LEFT JOIN `role` ON `user`.`role_id` = `role`.`role_id`" +
					"WHERE `user`.`name` = ? AND `user`.`password` = ?;";
	
	Connection conn = dataSource.getConnection();
	PreparedStatement statement = conn.prepareStatement(query);
	
	statement.setString(1, name);
	statement.setString(2, password);
	
	ResultSet resultSet = statement.executeQuery();
	User user = null;
	if(resultSet.next()) {
		user = new User();
		user.setUser_id(resultSet.getInt(1));
		user.setName(resultSet.getString(2));
		user.setPassword(resultSet.getString(3));
		user.setRole(Role.lookUp(resultSet.getString(4)));
	}
	resultSet.close();
	statement.close();
	conn.close();
	return user;
}
@Override
public User getById(Integer id) throws SQLException {
		String query = "SELECT `user`.`user_id`, `user`.`name`, `user`.`password`, `role`.`name`"
				+ "FROM `user` LEFT JOIN `role` ON `user`.`role_id` = `role`.`role_id`"
				+ "WHERE `user`.`user_id` = ?;";

		Connection conn = dataSource.getConnection();
		PreparedStatement statement = conn.prepareStatement(query);

		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		User user = null;
		if (resultSet.next()) {
			user = new User();
			user.setUser_id(resultSet.getInt(1));
			user.setName(resultSet.getString(2));
			user.setPassword(resultSet.getString(3));
			user.setRole(Role.lookUp(resultSet.getString(4)));
		}
		resultSet.close();
		statement.close();
		conn.close();
		return user;
}
@Override
public int saveUser(User user) throws SQLException {
	String query = "INSERT INTO `user` (`name`,`password`,`role_id`)" +
				" VALUE (?,?,?);";

	Connection conn = dataSource.getConnection();
	PreparedStatement statement = conn.prepareStatement(query);
	statement.setString(1,(String)user.getName());
	statement.setString(2,(String)user.getPassword());
	int role = 3;
	if (user.getRole().getName().equals("user")) role = 1;
	if (user.getRole().getName().equals("admin"))role = 2;
	statement.setInt(3,(int)role);
	statement.executeUpdate();
	
	statement.close();
	conn.close();
	return 0;
}


@Override
public List<User> getUsersOrderByName() throws SQLException {
		String query =	"SELECT `user`.`user_id`,`user`.`name`,`user`.`password`, `role`.`name` role" +
						" FROM `user`" +
						" LEFT JOIN `role` ON `user`.`role_id` = `role`.`role_id`" +
						" ORDER BY `user`.`name`;";

		Connection conn = dataSource.getConnection();
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);
		List<User> list = new ArrayList<User>();
		User user;
		while(rs.next()) {
			user = null;
			user = new User();
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
			user.setUser_id(rs.getInt("user_id"));
			user.setRole(Role.lookUp(rs.getString("role")));
			list.add(user);
		}
		rs.close();
		statement.close();
		conn.close();
		return list;
}
@Override
public int updateUser(User user) throws SQLException {
	String query = 	"UPDATE `user`" +
			" SET `name` = ?," +
			" `password` = ?," +
			" `role_id` = ?" +
			" WHERE `user_id` = ?;";

Connection conn = dataSource.getConnection();
PreparedStatement statement = conn.prepareStatement(query);
int role_id = 3;
String role = user.getRole().getName();
if (role.equals("admin")) {
	role_id = 2;
}
if (role.equals("user")) {
	role_id = 1;
}
statement.setString(1, user.getName());
statement.setString(2, user.getPassword());
statement.setInt(3, role_id);
statement.setInt(4, user.getUser_id());
int counter = statement.executeUpdate();
statement.close();
conn.close();
return counter;
}
@Override
public boolean isExists(String name) throws SQLException {
	String query = "SELECT *"
			+ "FROM `user`"
			+ "WHERE `name` = ?;";

	Connection conn = dataSource.getConnection();
	PreparedStatement statement = conn.prepareStatement(query);

	statement.setString(1, name);
	
	ResultSet resultSet = statement.executeQuery();
	boolean isExist = resultSet.next();

	resultSet.close();
	statement.close();
	conn.close();
	return isExist;
}
public void deleteUser(User user) throws SQLException {
	String query = "DELETE FROM `user` WHERE `user_id` = ?;";
	Connection conn = dataSource.getConnection();
	PreparedStatement statement = conn.prepareStatement(query);
	statement.setInt(1, user.getUser_id());
	statement.execute();
	statement.close();
	conn.close();
	
}
}
