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
//	private static final String PASSWORD = "9244Tatsuya";
//	private static final String PASSWORD = "H06-m10-d31";
	private static final String PASSWORD = "H06-m10-d31";
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
//	private static final String PASSWORD = "9244Tatsuya";
//	private static final String PASSWORD = "H06-m10-d31";
	private static final String PASSWORD = "H06-m10-d31";
=======
<<<<<<< HEAD
	private static final String PASSWORD = "9244Tatsuya";
=======
>>>>>>> 9863cc860de969a50f12766315a1bdd0eedf04ec
//	private static final String PASSWORD = "9244Tatsuya";
//	private static final String PASSWORD = "H06-m10-d31";
	private static final String PASSWORD = "Okada0121washo";

>>>>>>> 617b10691af0d549733bc2e88450069bd78bed83
>>>>>>> ed6371ae3b582a1164daf27f9b7ac542fbeff351
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
