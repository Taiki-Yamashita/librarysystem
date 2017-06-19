package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import beans.Circulation;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;
import service.CirculationService;

public class CirculationDao {

	public List<Circulation> selectAll(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users_circulations where returning = 0";
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

	public List<Circulation> select(Connection connection, Date date) throws ParseException{

		PreparedStatement ps = null;

		try {
			String sql = "SELECT * FROM users_circulations where returning = 0";
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			List<Circulation> circulationList = toCirculationList(rs, date);
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
			sql.append(", lending");
			sql.append(", returning");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, 14);//14日加算

			ps.setString(1, circulation.getUserId());
			ps.setString(2, circulation.getBookId());
			ps.setString(3, circulation.getLibraryId());
			ps.setString(4, calendar.get(Calendar.YEAR)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.DATE));//登録時刻から14日後
			ps.setString(5, "1");
			ps.setString(6, "0");

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
			sql.append(" lending = ?");
			sql.append(" , returning = ?");
			sql.append(" WHERE");
			sql.append(" book_id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, "0");
			ps.setString(2, "0");
			ps.setString(3, circulation.getBookId());


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

	public void update(Connection connection, Circulation circulation, String string) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE circulations SET");
			sql.append(" returning = ?");
			sql.append(" WHERE");
			sql.append(" limited_date = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, "1");
			ps.setString(2, string);


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
				//int id = rs.getInt("id");

				String userId = rs.getString("user_id");
				String userName = rs.getString("user_name");
				String bookId = rs.getString("book_id");
				String bookName = rs.getString("book_name");
				String libraryId = rs.getString("library_id");
				String libraryName = rs.getString("library_name");
				String lentDate = rs.getString("lent_date");
				String limitedDate = rs.getString("limited_date");
				//String returning = rs.getString("returning");

				Circulation circulation = new Circulation();
				//circulation.setId(id);
				circulation.setUserId(userId);
				circulation.setUserName(userName);
				circulation.setBookId(bookId);
				circulation.setBookName(bookName);
				circulation.setLibraryId(libraryId);
				circulation.setLibraryName(libraryName);
				circulation.setLentDate(lentDate);
				circulation.setLimitedDate(limitedDate);
				//circulation.setReturning(returning);

				ret.add(circulation);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	private List<Circulation> toCirculationList(ResultSet rs, Date date) throws SQLException, ParseException {

		List<Circulation> ret = new ArrayList<Circulation>();
		try {
			while (rs.next()) {
				String limitedDate = rs.getString("limited_date");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:s");

		        Date formatDate = sdf.parse(limitedDate);

		        int diff = date.compareTo(formatDate);

		        if (diff == 0) {
		        } else if (diff > 0) {
		        	Circulation circulation = new Circulation();
		        	new CirculationService().update(circulation, limitedDate);
		        } else {
		        }
				Circulation circulation = new Circulation();

				circulation.setLimitedDate(limitedDate);
				ret.add(circulation);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
