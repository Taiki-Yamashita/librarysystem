package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Favorite;
import exception.NoRowsUpdatedRuntimeException;
//import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class FavoriteDao {

	public List<Favorite> selectAll(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users_favorites";
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Favorite> favoriteList = toFavoriteList(rs);
			if (favoriteList.isEmpty()) {
				return null;
			}else {
				return favoriteList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void insert(Connection connection, Favorite favorite) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO favorites ( ");
			sql.append(" user_id");
			sql.append(", book_id");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, favorite.getUserId());
			ps.setString(2, favorite.getBookId());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, Favorite favorite) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE favorites SET");
			sql.append("  user_id = ?");
			sql.append(", book_id = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, favorite.getUserId());
			ps.setString(2, favorite.getBookId());

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

	private List<Favorite> toFavoriteList(ResultSet rs) throws SQLException {

		List<Favorite> ret = new ArrayList<Favorite>();
		try {
			while (rs.next()) {
				//int id = rs.getInt("id");

				String userName = rs.getString("user_name");
				String userId = rs.getString("user_id");
				String bookName = rs.getString("book_name");
				String bookId = rs.getString("book_id");
				String author = rs.getString("book_author");


				Favorite favorite = new Favorite();
				//favorite.setId(id);
				favorite.setUserName(userName);
				favorite.setUserId(userId);
				favorite.setBookName(bookName);
				favorite.setBookId(bookId);
				favorite.setAuthor(author);

				ret.add(favorite);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
