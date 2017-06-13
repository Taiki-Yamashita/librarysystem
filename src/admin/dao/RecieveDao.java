package admin.dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Require;
import exception.SQLRuntimeException;

public class RecieveDao {

	public List<Require> getSelectAllRecieve(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM requires");
			//sql.append("ORDER BY  DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Require> ret = toRecieveList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Require> toRecieveList(ResultSet rs)
			throws SQLException {

		List<Require> ret = new ArrayList<Require>();
		try {
			while (rs.next()) {
				String userName = rs.getString("user_name");
				String bookName = rs.getString("book_name");
				int id = rs.getInt("id");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				String requestDate = rs.getString("request_date");

				Require recieve = new Require();

				recieve.setUserName(userName);
				recieve.setBookName(bookName);
				recieve.setId(id);
				recieve.setAuthor(author);
				recieve.setPublisher(publisher);
				recieve.setRequestDate(requestDate);

				ret.add(recieve);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
