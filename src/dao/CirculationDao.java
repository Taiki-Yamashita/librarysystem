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

	public List<Circulation> selectDelayBook(Connection connection){

		String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM library_system.circulations where limited_date <= ? and returning = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, currentDate);
			ps.setString(2, "1");


			ResultSet rs = ps.executeQuery();
			List<Circulation> circulationList = toMypageList(rs);
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

	public void lending(Connection connection, Circulation circulation) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE circulations SET");
			sql.append(" lending = ?");
			sql.append(" WHERE");
			sql.append(" book_id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, "1");
			ps.setString(2, circulation.getBookId());


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

	public void returning(Connection connection, Circulation circulation, String num) {

			PreparedStatement ps = null;
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE circulations SET");
				sql.append(" lending = ?");
				sql.append(" WHERE");
				sql.append(" book_id = ?");

				ps = connection.prepareStatement(sql.toString());

				ps.setString(1, num);
				ps.setString(2, circulation.getBookId());

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
				int id = rs.getInt("id");

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
				circulation.setId(id);
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

	public List<Circulation> selectC(Connection connection, int bookId){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users_circulations where book_id =?";
			ps = connection.prepareStatement(sql);

			ps.setInt(1, bookId);

			ResultSet rs = ps.executeQuery();
			List<Circulation> reservationList = toSelectCList(rs);
			if (reservationList.isEmpty()) {
				return null;
			}else {
				return reservationList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<Circulation> toSelectCList(ResultSet rs) throws SQLException {

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
	public List<Circulation> selectMypage(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM circulations ";
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Circulation> reservationList = toMypageList(rs);
			if (reservationList.isEmpty()) {
				return null;
			}else {
				return reservationList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	private List<Circulation> toMypageList(ResultSet rs) throws SQLException {

		List<Circulation> ret = new ArrayList<Circulation>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String bookId = rs.getString("book_id");
				String libraryId = rs.getString("library_id");
				String userId = rs.getString("user_id");
				String lentDate = rs.getString("lent_date");
				String limitedDate = rs.getString("limited_date");
				String lending = rs.getString("lending");
				String returning = rs.getString("returning");


				Circulation reservation = new Circulation();
				reservation.setId(id);
				reservation.setBookId(bookId);
				reservation.setLibraryId(libraryId);
				reservation.setUserId(userId);
				reservation.setLentDate(lentDate);
				reservation.setLimitedDate(limitedDate);
				reservation.setLending(lending);
				reservation.setReturning(returning);


				ret.add(reservation);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void lendingFlag(Connection connection,String id) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE books SET");
			sql.append(" lending = ?");
			sql.append(" ,keeping = ?");
			sql.append(" ,disposing = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, "1");
			ps.setString(2, "0");
			ps.setString(3, "0");
			ps.setString(4, id);


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
	public void returningFlag(Connection connection,String id) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE books SET");
			sql.append(" lending = ?");
			sql.append(" ,keeping = ?");
			sql.append(" ,disposing = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, "0");
			ps.setString(2, "1");
			ps.setString(3, "0");
			ps.setString(4, id);


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
	public List<Circulation> select(Connection connection, int bookId){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT *, COUNT(*) as count FROM library_system.circulations where book_id =? and lending = 1 GROUP BY book_id ORDER BY count(*) DESC,book_id";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, bookId);

			ResultSet rs = ps.executeQuery();
			List<Circulation> circulationList = toSelectList(rs);
			if (circulationList.isEmpty()) {
				return null;
			}
				return circulationList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<Circulation> toSelectList(ResultSet rs) throws SQLException {

		List<Circulation> ret = new ArrayList<Circulation>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("user_id");
				String bookId = rs.getString("book_id");
				String libraryId = rs.getString("library_id");
				String lending = rs.getString("lending");

				Circulation circulation = new Circulation();
				circulation.setId(id);
				circulation.setUserId(userId);
				circulation.setBookId(bookId);
				circulation.setLibraryId(libraryId);
				circulation.setLending(lending);

				ret.add(circulation);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
