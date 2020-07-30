package example.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.management.Query;

import struct.List;
import struct.User;

public class Lists implements SqlImpl {
	Connection connection = SqlManager.getSqlManager().connection;
	Statement statement = SqlManager.getSqlManager().statement;

	private Lists() {
	}

	private static Lists lists = null;

	public static Lists getLists() {
		if (lists == null) {
			lists = new Lists();
		}
		return lists;
	}

	@Override
	public boolean insert(Object o) {
		List list = (List) o;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("insert into Lists values(?,?,?,?)");
			preparedStatement.setString(1, list.getTitle());
			preparedStatement.setString(2, list.getDescribe());
			preparedStatement.setBoolean(3, list.isSuper());
			preparedStatement.setInt(4, list.getAchievement());

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
		List list = (List) o;
		String sql = "delete from Lists where Describe=" + list.getDescribe();
		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 热度增加
	 * 
	 * @return
	 */
	public boolean tempAdd(List list, int Atemp) {
		int temp = (list.getAchievement() + Atemp);
		String sql = "update Lists set achievement=" + temp + " where Describe="
				+ list.getDescribe();
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ArrayList<List> query() {
		ArrayList<List> lists = new ArrayList<List>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from Lists");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				List list = new List(resultSet.getString(1), resultSet.getString(2), resultSet.getBoolean(3),
						resultSet.getInt(4));
				lists.add(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lists;
	}

	@Override
	public boolean update(Object old, Object nw) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public Object query(Object o) {
		// TODO 自动生成的方法存根
		return null;
	}

}
