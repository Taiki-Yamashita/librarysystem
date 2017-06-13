//package dao;
//
////import static utils.CloseableUtil.*;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import beans.Favorite;
//
////import exception.NoRowsUpdatedRuntimeException;
////import exception.SQLRuntimeException;
//
//public class FavoriteDao {
//
//	public List<Favorite> selectAll(Connection connection){
//
//		PreparedStatement ps = null;
//		try {
//			String sql = "SELECT * FROM favorites";
//			ps = connection.prepareStatement(sql);
//
//			ResultSet rs = ps.executeQuery();
//			List<Favorite> favoriteList = toFavoriteList(rs);
//			if (favoriteList.isEmpty()) {
//				return null;
//			}else {
//				return favoriteList;
//			}
//		} catch (SQLException e) {
//			throw new SQLRuntimeException(e);
//		} finally {
//			close(ps);
//		}
//	}
//
//	public void insert(Connection connection, Favorite favorite) {
//
//		PreparedStatement ps = null;
//		try {
//			StringBuilder sql = new StringBuilder();
//			sql.append("INSERT INTO favorites ( ");
//			sql.append(" lent_date");
//			sql.append(", limited_date");
//			sql.append(", user_id");
//			sql.append(", book_id");
//			sql.append(", library_id");
//			sql.append(", returning");
//			sql.append(") VALUES (");
//			sql.append(" ?");
//			sql.append(", ?");
//			sql.append(", ?");
//			sql.append(", ?");
//			sql.append(", ?");
//			sql.append(", ?");
//			sql.append(")");
//
//			ps = connection.prepareStatement(sql.toString());
//
//			ps.setString(1, favorite.getLentDate());
//			ps.setString(2, favorite.getLimitedDate());
//			ps.setString(3, favorite.getUserId());
//			ps.setString(4, favorite.getBookId());
//			ps.setString(5, favorite.getLibraryId());
//			ps.setString(6, favorite.getReturning());
//
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			throw new SQLRuntimeException(e);
//		} finally {
//			close(ps);
//		}
//	}
//
//	public void update(Connection connection, Favorite book) {
//
//		PreparedStatement ps = null;
//		try {
//			StringBuilder sql = new StringBuilder();
//			sql.append("UPDATE circulations SET");
//			sql.append("  lent_date = ?");
//			sql.append(", limited_date = ?");
//			sql.append(", user_id = ?");
//			sql.append(", book_id = ?");
//			sql.append(", library_id = ?");
//			sql.append(", returning = ?");
//			sql.append(" WHERE");
//			sql.append(" id = ?");
//
//			ps = connection.prepareStatement(sql.toString());
//
//			ps.setString(1, book.getLentDate());
//			ps.setString(2, book.getLimitedDate());
//			ps.setString(3, book.getUserId());
//			ps.setString(4, book.getBookId());
//			ps.setString(5, book.getLibraryId());
//			ps.setString(6, book.getReturning());
//
//			int count = ps.executeUpdate();
//			if (count == 0) {
//				throw new NoRowsUpdatedRuntimeException();
//			}
//		} catch (SQLException e) {
//			throw new SQLRuntimeException(e);
//		} finally {
//			close(ps);
//		}
//
//	}
//
//	private List<Favorite> toFavoriteList(ResultSet rs) throws SQLException {
//
//		List<Favorite> ret = new ArrayList<Favorite>();
//		try {
//			while (rs.next()) {
//				int id = rs.getInt("id");
//				String lentDate = rs.getString("lent_date");
//				String limitedDate = rs.getString("limited_date");
//				String userId = rs.getString("user_id");
//				String bookId = rs.getString("book_id");
//				String libraryId = rs.getString("library_id");
//				String returning = rs.getString("returning");
//
//				Favorite circulation = new Favorite();
//				circulation.setId(id);
//				circulation.setLentDate(lentDate);
//				circulation.setLimitedDate(limitedDate);
//				circulation.setUserId(userId);
//				circulation.setBookId(bookId);
//				circulation.setLibraryId(libraryId);
//				circulation.setReturning(returning);
//
//				ret.add(circulation);
//			}
//			return ret;
//		} finally {
//			close(rs);
//		}
//	}
//
//}
