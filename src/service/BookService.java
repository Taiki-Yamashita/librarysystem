package service;

//import static utils.CloseableUtil.*;
//import static utils.DButil.*;

import java.sql.Connection;
import java.util.List;

import beans.Book;

public class BookService {

	public void insert(Book book) {

		Connection connection = null;
		try {
			connection = getConnection();

			new BookDao().insert(connection, book);

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

	public void update(Book book) {

		Connection connection = null;
		try {
			connection = getConnection();

			new BookDao().update(connection, book);

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

	public List<Book> selectAll() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Book> books = new BookDao().selectAll(connection);

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