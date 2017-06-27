package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Circulation;
import beans.Reservation;
import dao.ReservationDao;

public class ReservationService {

	public void insert(Reservation reservation) {

		Connection connection = null;
		try {
			connection = getConnection();

			new ReservationDao().insert(connection, reservation);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void update(Reservation reservation) {

		Connection connection = null;
		try {
			connection = getConnection();

			new ReservationDao().update(connection, reservation);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void updateDeliveringStatus(int id) {

		Connection connection = null;
		try {
			connection = getConnection();

			new ReservationDao().updateDeliveringStatus(connection, id);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void delete(Circulation circulation) {

		Connection connection = null;
		try {
			connection = getConnection();

			new ReservationDao().delete(connection, circulation);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Reservation> selectAll() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Reservation> contacts = new ReservationDao().selectAll(connection);

			commit(connection);

			return contacts;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public Reservation selectUserReserving(String userId) {

		Connection connection = null;
		try {
			connection = getConnection();

			Reservation reservation = new ReservationDao().selectUserReserving(connection, userId);

			commit(connection);

			return reservation;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Reservation> selectAllView() {

		Connection connection = null;
		try {
			connection = getConnection();
			List<Reservation> contacts = new ReservationDao().selectAllView(connection);
			commit(connection);

			return contacts;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
	public List<Reservation> reservingCheck(int bookId, String userId) {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Reservation> contacts = new ReservationDao().reservingCheck(connection, bookId, userId);

			commit(connection);

			return contacts;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Reservation> select(int bookId) {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Reservation> contacts = new ReservationDao().select(connection, bookId);

			commit(connection);

			return contacts;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Reservation> selectMypage() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Reservation> contacts = new ReservationDao().selectMypage(connection);

			commit(connection);

			return contacts;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
	public void updateStatus(int bookId,int num) {

		Connection connection = null;
		try {
			connection = getConnection();

			new ReservationDao().updateStatus(connection,bookId, num);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
	public List<Reservation> selectReserving(int bookId) {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Reservation> contacts = new ReservationDao().selectReserving(connection, bookId);

			commit(connection);

			return contacts;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}