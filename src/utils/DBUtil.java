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
=======
<<<<<<< HEAD
//	private static final String PASSWORD = "9244Tatsuya";
//	private static final String PASSWORD = "H06-m10-d31";
	private static final String PASSWORD = "Okada0121washo";
=======
<<<<<<< HEAD
>>>>>>> 4d7b136298342ecddbb3c8b34cbbb59b29e5c230
	//private static final String PASSWORD = "9244Tatsuya";
	private static final String PASSWORD = "H06-m10-d31";

>>>>>>> 60a76bafe9aec0c10608ecb0be4948377e9ce1ea
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
