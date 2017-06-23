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
	//private static final String PASSWORD = "9244Tatsuya";
=======
<<<<<<< HEAD

=======
<<<<<<< HEAD
	//private static final String PASSWORD = "9244Tatsuya";
=======
<<<<<<< HEAD
>>>>>>> 6fba65a4cafcfc3a615d52c5f0dfa3808881319a
>>>>>>> f26cccebf1bd4a801e2013e190cbff0f1daebf25

//	private static final String PASSWORD = "9244Tatsuya";

>>>>>>> 80b801692565f0d2dd673f63ea183434b93d3649
	//private static final String PASSWORD = "9244Tatsuya";




	//private static final String PASSWORD = "9244Tatsuya";
	//private static final String PASSWORD = "9244Tatsuya";
	//private static final String PASSWORD = "H06-m10-d31";
	private static final String PASSWORD = "Okada0121washo";
	//private static final String PASSWORD = "Okada0121washo";
	//private static final String PASSWORD = "9244Tatsuya";
	//private static final String PASSWORD = "Okada0121washo";
<<<<<<< HEAD

=======
<<<<<<< HEAD
	//private static final String PASSWORD = "9244Tatsuya";
=======
<<<<<<< HEAD
=======

<<<<<<< HEAD

=======
=======
	private static final String PASSWORD = "9244Tatsuya";
>>>>>>> 1de708b2dfb111a5c42e59839d25a778616f8e9a
>>>>>>> 67626ea0e3695a27be1ad8051843f38cd73e22dc
>>>>>>> 6fba65a4cafcfc3a615d52c5f0dfa3808881319a
>>>>>>> f26cccebf1bd4a801e2013e190cbff0f1daebf25
>>>>>>> 80b801692565f0d2dd673f63ea183434b93d3649

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
