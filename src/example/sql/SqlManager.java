package example.sql;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.filechooser.FileSystemView;

public class SqlManager {

	private String[] tableNames = { "Users", "Lists" };
	private String[] tableFormat = {
			"Account bigint,Password nvarchar(30),Name nvarchar(30),isAdmin boolean,PRIMARY KEY(Account)",
			"Title nvarchar(30),Describe nvarchar(300),isSuper boolean,achievement int,PRIMARY KEY(Describe)" };

	protected Connection connection;
	protected Statement statement;
	protected ResultSet resultSet;

	private static SqlManager sqlManager = null;

	public static SqlManager getSqlManager() {
		if (sqlManager == null) {
			sqlManager = new SqlManager();
		}
		return sqlManager;
	}

	private SqlManager() {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:sqlite:" + getUserFilePath() + "\\Data.db";
		System.out.println(url);
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < tableNames.length; i++) {
			if (!isExistForm(tableNames[i])) {
				try {
					PreparedStatement preparedStatement = connection
							.prepareStatement("create table " + tableNames[i] + "(" + tableFormat[i] + ")");
					preparedStatement.execute();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 验证表格是否已存在
	 * 
	 * @param fromName
	 * @return
	 */
	private boolean isExistForm(String fromName) {
		try {
			resultSet = connection.getMetaData().getTables(null, null, fromName, null);
			if (!resultSet.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * 得到数据存储目录
	 * 
	 * @return
	 */
	private File getUserFilePath() {
		File rootPath = null;
		FileSystemView fileSystemView = FileSystemView.getFileSystemView();
		rootPath = fileSystemView.getDefaultDirectory();
		rootPath = new File(rootPath, "热搜排行榜");
		if (!rootPath.exists()) {
			rootPath.mkdir();
		}
		return rootPath;
	}
}
