package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.SQLRuntimeException;

/**
 * DB(コネクション関係)のユーティリティー
 */
public class DBUtil {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/library_system";
	private static final String USER = "root";
<<<<<<< HEAD
	//private static final String PASSWORD = "9244Tatsuya";
	private static final String PASSWORD = "H06-m10-d31";
=======
<<<<<<< HEAD
	private static final String PASSWORD = "9244Tatsuya";
=======
<<<<<<< HEAD
	//private static final String PASSWORD = "9244Tatsuya";
	private static final String PASSWORD = "H06-m10-d31";
=======
	private static final String PASSWORD = "Okada0121washo";
>>>>>>> 45f5539c4dece7d83151b61a5146f9fb53e48fbd
>>>>>>> 1460cdbac50efc7e2e0b6cdc1e1d19a58d476734
>>>>>>> 2881c9a75bac7083edaf7ea028b6df315259d33d

	static {

		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * コネクションを取得します。
	 *
	 * @return
	 */

	public static Connection getConnection() {

		try {
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

			connection.setAutoCommit(false);

			return connection;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	/**
	 * コミットします。
	 *
	 * @param connection
	 */

	public static void commit(Connection connection) {

		try {
			connection.commit();
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

	/**
	 * ロールバックします。
	 *
	 * @param connection
	 */

	public static void rollback(Connection connection) {

		if (connection == null) {
			return;
		}

		try {
			connection.rollback();
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}
}
