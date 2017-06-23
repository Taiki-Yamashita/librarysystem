package admin.dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Require;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class RecieveDao {

	public List<Require> getSelectAllRecieve(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM requires where showing = ?");
			//sql.append("ORDER BY  DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, num);
			ResultSet rs = ps.executeQuery();
			List<Require> ret = toRecieveList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<Require> getSelectAllRecieve(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM requires");

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
				String requiredDate = rs.getString("required_date");
				String showing = rs.getString("showing");

				Require receive = new Require();

				receive.setUserName(userName);
				receive.setBookName(bookName);
				receive.setId(id);
				receive.setAuthor(author);
				receive.setPublisher(publisher);
				receive.setRequiredDate(requiredDate);
				receive.setShowing(showing);

				ret.add(receive);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void update(Connection connection, int flag, int id) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE requires SET");
			sql.append(" showing = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, flag);
			ps.setInt(2, id);

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
