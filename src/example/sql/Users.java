package example.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import struct.User;

public class Users implements SqlImpl {
	Connection connection = SqlManager.getSqlManager().connection;
	Statement statement = SqlManager.getSqlManager().statement;

	private static Users users = null;

	private Users() {
	}

	public static Users getUser() {
		if (users == null) {
			users = new Users();
		}
		return users;
	}

	@Override
	public boolean insert(Object o) {
		User user = (User) o;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("insert into Users values(?,?,?,?)");
			preparedStatement.setLong(1, user.getAccount());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setBoolean(4, user.isAdmin());

			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Object o) {
		User user = (User) o;
		String sql = "delete from Users where Account=" + user.getAccount();
		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean update(Object old, Object nw) {
		return false;
	}

	@Override
	public Object query(Object o) {
		long account = (Long) o;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from Users where Account=" + account);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				User user = new User(resultSet.getLong(1), resultSet.getString(3), resultSet.getString(2),
						resultSet.getBoolean(4));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
