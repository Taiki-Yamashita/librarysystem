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
	private static final String PASSWORD = "9244Tatsuya";
=======
	//private static final String PASSWORD = "9244Tatsuya";
	//private static final String PASSWORD = "Okada0121washo";

	private static final String PASSWORD = "H06-m10-d31";
<<<<<<< HEAD
=======
>>>>>>> 74cc4540eec5c613129ef4b49dec5e59e6a1b3b8
>>>>>>> fbd29c7a82db2ec6952181223a63b8e6f5fedce1
>>>>>>> 8d774768a022a9b884960e743f3b2fef65424927
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
