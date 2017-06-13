package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Introduction;
//import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class IntroductionDao {

	public List<Introduction> selectAll(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM introductions";
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Introduction> introductionList = toIntroductionList(rs);
			if (introductionList.isEmpty()) {
				return null;
			}else {
				return introductionList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void insert(Connection connection, Introduction favorite) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO introductions ( ");
			sql.append(" user_id");
			sql.append(", book_id");
			sql.append(", registered_date");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
			sql.append(", CURRENT_TIMESTAMP");
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

//	public void update(Connection connection, Introduction favorite) {
//
//		PreparedStatement ps = null;
//		try {
//			StringBuilder sql = new StringBuilder();
//			sql.append("UPDATE favorites SET");
//			sql.append("  user_id = ?");
//			sql.append(", book_id = ?");
//			sql.append(" WHERE");
//			sql.append(" id = ?");
//
//			ps = connection.prepareStatement(sql.toString());
//
//			ps.setString(1, favorite.getUserId());
//			ps.setString(2, favorite.getBookId());
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

	private List<Introduction> toIntroductionList(ResultSet rs) throws SQLException {

		List<Introduction> ret = new ArrayList<Introduction>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("user_id");
				String bookId = rs.getString("book_id");
				String registeredDate = rs.getString("registered_date");

				Introduction introduction = new Introduction();
				introduction.setId(id);
				introduction.setUserId(userId);
				introduction.setBookId(bookId);
				introduction.setRegisteredDate(registeredDate);

				ret.add(introduction);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
