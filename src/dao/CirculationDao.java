package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import beans.Circulation;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class CirculationDao {

	public List<Circulation> selectAll(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users_circulations";
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Circulation> circulationList = toCirculationList(rs);
			if (circulationList.isEmpty()) {
				return null;
			}else {
				return circulationList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void insert(Connection connection, Circulation circulation) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO circulations ( ");
			sql.append(" user_id");
			sql.append(", book_id");
			sql.append(", library_id");
			sql.append(", lent_date");
			sql.append(", limited_date");
			sql.append(", returning");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 14);//14日加算

			ps.setString(1, circulation.getUserId());
			ps.setString(2, circulation.getBookId());
			ps.setString(3, circulation.getLibraryId());
			//ps.setString(4, circulation.getLentDate());
			ps.setString(4, String.valueOf(cal));//登録時刻から14日後
			ps.setString(5, circulation.getReturning());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, Circulation circulation) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE circulations SET");
			sql.append(" user_id = ?");
			sql.append(", book_id = ?");
			sql.append(", library_id = ?");
			sql.append(", lent_date = ?");
			sql.append(", limited_date = ?");
			sql.append(", returning = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());


			ps.setString(1, circulation.getUserId());
			ps.setString(2, circulation.getBookId());
			ps.setString(3, circulation.getLibraryId());
			ps.setString(4, circulation.getLentDate());
			ps.setString(5, circulation.getLimitedDate());
			ps.setString(6, circulation.getReturning());

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

	private List<Circulation> toCirculationList(ResultSet rs) throws SQLException {

		List<Circulation> ret = new ArrayList<Circulation>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");

				String userId = rs.getString("user_id");
				String userName = rs.getString("user_name");
				String bookId = rs.getString("book_id");
				String bookName = rs.getString("book_name");
				String libraryId = rs.getString("library_id");
				String libraryName = rs.getString("library_name");
				String lentDate = rs.getString("lent_date");
				String limitedDate = rs.getString("limited_date");
				String returning = rs.getString("returning");

				Circulation circulation = new Circulation();
				circulation.setId(id);
				circulation.setUserId(userId);
				circulation.setUserName(userName);
				circulation.setBookId(bookId);
				circulation.setBookName(bookName);
				circulation.setLibraryId(libraryId);
				circulation.setLibraryName(libraryName);
				circulation.setLentDate(lentDate);
				circulation.setLimitedDate(limitedDate);
				circulation.setReturning(returning);

				ret.add(circulation);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
