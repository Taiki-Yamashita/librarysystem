package admin.dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.beans.NotReturned;
import exception.SQLRuntimeException;

public class NotReturnedDao {
	public List<NotReturned> getSelectAllNotReturned(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_circulations");
			//sql.append("ORDER BY  DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<NotReturned> ret = toNotReturnedList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<NotReturned> toNotReturnedList(ResultSet rs)
			throws SQLException {

		List<NotReturned> ret = new ArrayList<NotReturned>();
		try {
			while (rs.next()) {
				String userName = rs.getString("user_name");
				String bookName = rs.getString("book_name");
				String libraryName = rs.getString("library_name");

				NotReturned notReturned = new NotReturned();

				notReturned.setUserName(userName);
				notReturned.setBookName(bookName);
				notReturned.setLibraryName(libraryName);

				ret.add(notReturned);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
