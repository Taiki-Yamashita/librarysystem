package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Require;
import exception.SQLRuntimeException;

public class RequireDao {
	public void insert(Connection connection, Require request) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO requires ( ");
			sql.append(" user_name");
			sql.append(", book_name");
			sql.append(", author");
			sql.append(", publisher");
			sql.append(", request_date");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, request.getUserName());
			ps.setString(2, request.getBookName());
			ps.setString(3, request.getAuthor());
			ps.setString(4, request.getPublisher());

			System.out.println(ps.toString());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
