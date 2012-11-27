package sql.connection;

import java.sql.*;

public class Connect {

	private final static String url = "jdbc:mysql://localhost/bazadoprojektu";
	private final static String userName = "root";
	private final static String pass = "";

	private Connection connection = null;

	public Connection getConnection() {
		if (connection == null) {
			try {
				connection = DriverManager.getConnection(url, userName, pass);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}

}