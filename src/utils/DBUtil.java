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
<<<<<<< HEAD
=======

<<<<<<< HEAD
>>>>>>> 85ea834ea3c3122dc1fe849b868824a23a3aa302

	private static final String PASSWORD = "Okada0121washo";
	//private static final String PASSWORD = "9244kuniyoshi";


	//private static final String PASSWORD = "9244Tatsuya";

=======
	//private static final String PASSWORD = "Okada0121washo";
	private static final String PASSWORD = "H06-m10-d31";
<<<<<<< HEAD
=======
=======
	private static final String PASSWORD = "9244Tatsuya";

>>>>>>> 7d2a20f18b72bf8b452bbcc3fd560b622929f43e
>>>>>>> 274942a3566c1b80a24e4a79e688c8baf656d19f
>>>>>>> 85ea834ea3c3122dc1fe849b868824a23a3aa302
>>>>>>> 9a3a4cd5ffbf1f353ac1802d1b398491e55eb93a
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
