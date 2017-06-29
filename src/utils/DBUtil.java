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
=======
<<<<<<< HEAD
	private static final String PASSWORD = "9244Tatsuya";

=======
<<<<<<< HEAD
>>>>>>> ae57c21e3be330d9b6d08e32cf61aef760ef7962
	private static final String PASSWORD = "Okada0121washo";
>>>>>>> b1ac5ec702c74a015b4bf46910290e6a8b2f7e30

	//private static final String PASSWORD = "9244Tatsuya";
<<<<<<< HEAD

	//private static final String PASSWORD = "H06-m10-d31";
=======
	private static final String PASSWORD = "H06-m10-d31";
<<<<<<< HEAD
=======
>>>>>>> 70856a610e92146d154502ab94cc2b3d3067bb3d
>>>>>>> c7c4a20b44a51b0641ae616fb9d03a708ae57126
>>>>>>> b1ac5ec702c74a015b4bf46910290e6a8b2f7e30
>>>>>>> ae57c21e3be330d9b6d08e32cf61aef760ef7962
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
