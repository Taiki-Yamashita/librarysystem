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
	private static final String PASSWORD = "9244Tatsuya";
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
	private static final String PASSWORD = "9244Tatsuya";
=======
<<<<<<< HEAD
>>>>>>> 793c11cf15fdf5e3f5593f37b9ddb0307f5be1ef
	private static final String PASSWORD = "Okada0121washo";

//	private static final String PASSWORD = "9244Tatsuya";
//	private static final String PASSWORD = "H06-m10-d31";
//	private static final String PASSWORD = "H06-m10-d31";
//	private static final String PASSWORD = "9244Tatsuya";
//	private static final String PASSWORD = "H06-m10-d31";
//	private static final String PASSWORD = "H06-m10-d31";

//	private static final String PASSWORD = "9244Tatsuya";
//	private static final String PASSWORD = "9244Tatsuya";
//	private static final String PASSWORD = "H06-m10-d31";

<<<<<<< HEAD
=======
>>>>>>> 617b10691af0d549733bc2e88450069bd78bed83
>>>>>>> ed6371ae3b582a1164daf27f9b7ac542fbeff351
>>>>>>> 1e57d02fedbcb999aeb66e4243f30adc92f230e3
>>>>>>> 8ce9a2cf36e4fc81b525ffcc7f777f4c956d1073
>>>>>>> 793c11cf15fdf5e3f5593f37b9ddb0307f5be1ef
>>>>>>> f3f8d2d7f853f9d4d59284567c95784e8b85b534
>>>>>>> 71de557a6d547798b9f761f782c97536f5e10d28
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
