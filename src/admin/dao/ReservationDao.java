package admin.dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.beans.Reservation;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class ReservationDao {

	public List<Reservation> selectAll(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM book_reservations";
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


	public void insert(Connection connection, Reservation reservation) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO reservations ( ");
			sql.append(" user_id");
			sql.append(", book_id");
			sql.append(", library_id");
			sql.append(", delivering");
			sql.append(", canceling");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, reservation.getUserId());
			ps.setString(2, reservation.getBookId());
			ps.setString(3, reservation.getLibraryId());
			ps.setString(4, reservation.getDelivering());
			ps.setString(5, reservation.getCanceling());

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
			sql.append(", library_id = ?");
			sql.append(", delivering = ?");
			sql.append(", canceling = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, reservation.getUserId());
			ps.setString(2, reservation.getBookId());
			ps.setString(3, reservation.getLibraryId());
			ps.setString(4, reservation.getDelivering());
			ps.setString(5, reservation.getCanceling());

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

}
