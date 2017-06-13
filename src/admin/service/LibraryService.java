package admin.service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import admin.beans.Library;
import admin.dao.LibraryDao;

public class LibraryService {

	public void insert(Library library) {

		Connection connection = null;
		try {
			connection = getConnection();

			new LibraryDao().insert(connection, library);

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

	public void update(Library library) {

		Connection connection = null;
		try {
			connection = getConnection();

			new LibraryDao().update(connection, library);

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

	public List<Library> selectAll() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Library> contacts = new LibraryDao().selectAll(connection);

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