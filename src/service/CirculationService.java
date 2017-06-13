package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Circulation;
import dao.CirculationDao;

public class CirculationService {

	public void insert(Circulation circulation) {

		Connection connection = null;
		try {
			connection = getConnection();

			new CirculationDao().insert(connection, circulation);

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

	public void update(Circulation circulation) {

		Connection connection = null;
		try {
			connection = getConnection();

			new CirculationDao().update(connection, circulation);

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

	public List<Circulation> selectAll() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Circulation> books = new CirculationDao().selectAll(connection);

			commit(connection);

			return books;
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