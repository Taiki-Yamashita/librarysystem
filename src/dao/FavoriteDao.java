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
				String publisher = rs.getString("book_publisher");
				String category = rs.getString("book_category");
				String type = rs.getString("book_type");
				String libraryId = rs.getString("library_id");
				String libraryName = rs.getString("library_name");
				//String shelfId = rs.getString("shelf_id");
				String isbnId = rs.getString("book_isbnId");
				//String publishedDate = rs.getString("published_date");
				String keeping = rs.getString("book_keeping");
				String lending = rs.getString("book_lending");
				String reserving = rs.getString("book_reserving");
				String disposing = rs.getString("book_disposing");


				Favorite favorite = new Favorite();
				//favorite.setId(id);
				favorite.setUserName(userName);
				favorite.setUserId(userId);
				favorite.setBookName(bookName);
				favorite.setBookId(bookId);
				favorite.setAuthor(author);
				favorite.setPublisher(publisher);
				favorite.setCategory(category);
				favorite.setType(type);
				favorite.setLibraryId(libraryId);
				favorite.setLibraryName(libraryName);
				//favorite.setShelfId(shelfId);
				favorite.setIsbnId(isbnId);
				//favorite.setPublishedDate(publishedDate);
				favorite.setKeeping(keeping);
				favorite.setLending(lending);
				favorite.setReserving(reserving);
				favorite.setDisposing(disposing);


				ret.add(favorite);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void delete(Connection connection, Favorite favorite, String userId, String bookId) {
		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM favorites where user_id = ? and book_id = ? ";

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, userId);
			ps.setString(2, bookId);

			ps.executeUpdate();
		} catch (SQLException e){
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
