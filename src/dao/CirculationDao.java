package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Circulation;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class CirculationDao {

	public List<Circulation> selectAll(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM circulations";
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
			sql.append(" lent_date");
			sql.append(", limited_date");
			sql.append(", user_id");
			sql.append(", book_id");
			sql.append(", library_id");
			sql.append(", returning");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, circulation.getLentDate());
			ps.setString(2, circulation.getLimitedDate());
			ps.setString(3, circulation.getUserId());
			ps.setString(4, circulation.getBookId());
			ps.setString(5, circulation.getLibraryId());
			ps.setString(6, circulation.getReturning());

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
			sql.append("  lent_date = ?");
			sql.append(", limited_date = ?");
			sql.append(", user_id = ?");
			sql.append(", book_id = ?");
			sql.append(", library_id = ?");
			sql.append(", returning = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, circulation.getLentDate());
			ps.setString(2, circulation.getLimitedDate());
			ps.setString(3, circulation.getUserId());
			ps.setString(4, circulation.getBookId());
			ps.setString(5, circulation.getLibraryId());
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
				String lentDate = rs.getString("lent_date");
				String limitedDate = rs.getString("limited_date");
				String userId = rs.getString("user_id");
				String bookId = rs.getString("book_id");
				String libraryId = rs.getString("library_id");
				String returning = rs.getString("returning");

				Circulation circulation = new Circulation();
				circulation.setId(id);
				circulation.setLentDate(lentDate);
				circulation.setLimitedDate(limitedDate);
				circulation.setUserId(userId);
				circulation.setBookId(bookId);
				circulation.setLibraryId(libraryId);
				circulation.setReturning(returning);

				ret.add(circulation);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
