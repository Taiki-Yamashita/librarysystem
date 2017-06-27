package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.text.ParseException;
import java.util.Date;
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

	public void lending(Circulation circulation) {

		Connection connection = null;
		try {
			connection = getConnection();

			new CirculationDao().lending(connection, circulation);

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


	public void returning(Circulation circulation,String num) {

		Connection connection = null;
		try {
			connection = getConnection();

			new CirculationDao().returning(connection, circulation, num);

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

	public List<Circulation> selectDelayBook() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Circulation> books = new CirculationDao().selectDelayBook(connection);

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

	public List<Circulation> select(Date date) throws ParseException {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Circulation> books = new CirculationDao().select(connection, date);

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

	public void update(Circulation circulation, String string) {
		Connection connection = null;
		try {
			connection = getConnection();

			new CirculationDao().update(connection, circulation, string);

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

	public List<Circulation> selectC(int bookId) {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Circulation> circulations = new CirculationDao().selectC(connection, bookId);

			commit(connection);

			return circulations;
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

	public List<Circulation> selectMypage() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Circulation> contacts = new CirculationDao().selectMypage(connection);

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
	public void lendingFlag(String id) {

		Connection connection = null;
		try {
			connection = getConnection();

			new CirculationDao().lendingFlag(connection,id);

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
	public void returningFlag(String id) {

		Connection connection = null;
		try {
			connection = getConnection();

			new CirculationDao().returningFlag(connection, id);

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
	public List<Circulation> select(int bookId) {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Circulation> contacts = new CirculationDao().select(connection, bookId);

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