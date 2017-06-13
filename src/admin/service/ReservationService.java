package admin.service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import admin.beans.Reservation;
import admin.dao.ReservationDao;

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
}