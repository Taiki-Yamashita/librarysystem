package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Require;
import exception.SQLRuntimeException;

public class RequireDao {
	public void insert(Connection connection, Require require) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO requires ( ");
			sql.append(" user_name");
			sql.append(", book_name");
			sql.append(", author");
			sql.append(", publisher");
			sql.append(", comment");
			sql.append(", required_date");
			sql.append(", showing");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(", ?");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, require.getUserName());
			ps.setString(2, require.getBookName());
			ps.setString(3, require.getAuthor());
			ps.setString(4, require.getPublisher());
			if(require.getComment().isEmpty()) ps.setString(5, "特になし");
			else ps.setString(5, require.getComment());
			ps.setString(6, "0");

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	public void delete(Connection connection, String id) {
		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM requires where id = ? ";

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, id);

			ps.executeUpdate();
		} catch (SQLException e){
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
