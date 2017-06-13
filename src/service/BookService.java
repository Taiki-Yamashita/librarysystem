package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Book;
import dao.BookDao;

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

	public Book selectBook(int book_id) {

		Connection connection = null;
		try {
			connection = getConnection();

			BookDao bookDao = new BookDao();
			Book book = bookDao.selectBook(connection, book_id);

			commit(connection);

			return book;
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