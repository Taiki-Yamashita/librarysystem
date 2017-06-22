package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Reservation;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class ReservationDao {

	public List<Reservation> selectAll(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM reservations";
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Reservation> reservationList = toReservationList(rs);
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

	public List<Reservation> selectAllView(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM book_reservations";
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Reservation> reservationList = toBookReservationList(rs);
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


	public void insert(Connection connection, Reservation reservation) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO reservations ( ");
			sql.append(" user_id");
			sql.append(", book_id");
			sql.append(", book_name");
			sql.append(", library_id");
			sql.append(", delivering");
			sql.append(", canceling");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, reservation.getUserId());
			ps.setString(2, reservation.getBookId());
			ps.setString(3, reservation.getBookName());
			ps.setString(4, reservation.getLibraryId());
			ps.setString(5, "0");
			ps.setString(6, "0");


			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, Reservation reservation) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE reservations SET");
			sql.append("  user_id = ?");
			sql.append(", book_id = ?");
			sql.append(", book_name = ?");
			sql.append(", library_id = ?");
			sql.append(", delivering = ?");
			sql.append(", canceling = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, reservation.getUserId());
			ps.setString(2, reservation.getBookId());
			ps.setString(3, reservation.getBookName());
			ps.setString(4, reservation.getLibraryId());
			ps.setString(5, reservation.getDelivering());
			ps.setString(6, reservation.getCanceling());

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

	private List<Reservation> toReservationList(ResultSet rs) throws SQLException {

		List<Reservation> ret = new ArrayList<Reservation>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("user_id");
				String bookId = rs.getString("book_id");
				String libraryId = rs.getString("library_id");
				String delivering = rs.getString("delivering");
				String canceling = rs.getString("canceling");

				Reservation reservation = new Reservation();
				reservation.setId(id);
				reservation.setUserId(userId);
				reservation.setBookId(bookId);
				reservation.setLibraryId(libraryId);
				reservation.setDelivering(delivering);
				reservation.setCanceling(canceling);

				ret.add(reservation);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	private List<Reservation> toBookReservationList(ResultSet rs) throws SQLException {

		List<Reservation> ret = new ArrayList<Reservation>();
		try {
			while (rs.next()) {
				String userId = rs.getString("user_id");
				String bookId = rs.getString("book_id");
				String bookName = rs.getString("book_name");
				String libraryId = rs.getString("library_id");
				String reservedDate = rs.getString("reserved_date");
				String delivering = rs.getString("delivering");
				String canceling = rs.getString("canceling");

				Reservation reservation = new Reservation();
				reservation.setUserId(userId);
				reservation.setBookId(bookId);
				reservation.setBookName(bookName);
				reservation.setLibraryId(libraryId);
				reservation.setReservedDate(reservedDate);
				reservation.setDelivering(delivering);
				reservation.setCanceling(canceling);

				ret.add(reservation);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
	public List<Reservation> reservingCheck(Connection connection, int bookId, String userId){

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM reservations WHERE");
			sql.append(" book_id = ? AND");
			sql.append(" user_id = ? AND (delivering = 0 AND canceling =0)");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, bookId);
			ps.setString(2, userId);


			ResultSet rs = ps.executeQuery();
			List<Reservation> reservationList = toBookReservationList(rs);
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

	public List<Reservation> select(Connection connection, int bookId){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM reservations where book_id = ?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, bookId);

			ResultSet rs = ps.executeQuery();
			List<Reservation> reservationList = toSelectList(rs);
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
	private List<Reservation> toSelectList(ResultSet rs) throws SQLException {

		List<Reservation> ret = new ArrayList<Reservation>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("user_id");
				String bookId = rs.getString("book_id");
				String libraryId = rs.getString("library_id");
				String delivering = rs.getString("delivering");
				String canceling = rs.getString("canceling");

				Reservation reservation = new Reservation();
				reservation.setId(id);
				reservation.setUserId(userId);
				reservation.setBookId(bookId);
				reservation.setLibraryId(libraryId);
				reservation.setDelivering(delivering);
				reservation.setCanceling(canceling);

				ret.add(reservation);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
