package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Ranking;
import exception.SQLRuntimeException;

public class RankingDao {

	public List<Ranking> selectAll(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "select distinct book_name from library_system.book_rankings; ";
			ps = connection.prepareStatement(sql);


			ResultSet rs = ps.executeQuery();
			List<Ranking> bookList = toBookList(rs);
			if (bookList.isEmpty()) {
				return null;
			}else {
				return bookList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<Ranking> toBookList(ResultSet rs) throws SQLException {

		List<Ranking> ret = new ArrayList<Ranking>();
		try {
			while (rs.next()) {
				//int id = rs.getInt("id");

				String bookName = rs.getString("book_name");


				Ranking bookList = new Ranking();
				bookList.setBookName(bookName);



				ret.add(bookList);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
	public List<Ranking> circulationAll(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT *, COUNT(*) as count FROM library_system.circulations where lending = 1 GROUP BY book_id ORDER BY count(*) DESC,book_id ";
			ps = connection.prepareStatement(sql);


			ResultSet rs = ps.executeQuery();
			List<Ranking> circulations = toCirculations(rs);
			if (circulations.isEmpty()) {
				return null;
			}else {
				return circulations;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<Ranking> toCirculations(ResultSet rs) throws SQLException {

		List<Ranking> ret = new ArrayList<Ranking>();
		try {
			while (rs.next()) {
				String bookId = rs.getString("book_id");
				String count = rs.getString("count");

				Ranking circulations = new Ranking();
				circulations.setBookId(bookId);
				circulations.setCount(count);


				ret.add(circulations);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<Ranking> reservationAll(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT *, COUNT(*) as count FROM library_system.reservations where canceling=0  GROUP BY book_id ORDER BY count(*) DESC,book_id; ";
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Ranking> reservations = toReservations(rs);
			if (reservations.isEmpty()) {
				return null;
			}else {
				return reservations;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<Ranking> toReservations(ResultSet rs) throws SQLException {

		List<Ranking> ret = new ArrayList<Ranking>();
		try {
			while (rs.next()) {

				String bookName = rs.getString("book_name");
				String bookId = rs.getString("book_id");
				String count = rs.getString("count");

				Ranking reservations = new Ranking();
				reservations.setBookName(bookName);
				reservations.setBookId(bookId);
				reservations.setCount(count);


				ret.add(reservations);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<Ranking> managementAll(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT *, COUNT(*) as count FROM library_system.reservations where canceling=0  GROUP BY book_id ";
			ps = connection.prepareStatement(sql);


			ResultSet rs = ps.executeQuery();
			List<Ranking> reservations = toManagements(rs);
			if (reservations.isEmpty()) {
				return null;
			}else {
				return reservations;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<Ranking> toManagements(ResultSet rs) throws SQLException {

		List<Ranking> ret = new ArrayList<Ranking>();
		try {
			while (rs.next()) {

				String bookName = rs.getString("book_name");
				String bookId = rs.getString("book_id");
				String count = rs.getString("count");

				Ranking reservations = new Ranking();
				reservations.setBookName(bookName);
				reservations.setBookId(bookId);
				reservations.setCount(count);


				ret.add(reservations);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}