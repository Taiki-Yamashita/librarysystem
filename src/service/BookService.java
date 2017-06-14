package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public Book selectBook(int bookId) {

		Connection connection = null;
		try {
			connection = getConnection();

			BookDao bookDao = new BookDao();
			Book book = bookDao.selectBook(connection, bookId);

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

	public List<Book> getSelectedBooks(int selectBox, String freeWord) {

		Map<Integer, String> columnMap = getMapData();
		Connection connection = null;

		try {
			connection = getConnection();
			List<Book> books = new BookDao().getSelectedBooks(connection, columnMap.get(selectBox), freeWord);
			commit(connection);

			if(books == null) return getDefaultValue();
			else return books;
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

	public Map<Integer, String> getMapData(){

		Map<Integer, String> map = new HashMap<>();
		map.put(1, "");
		map.put(2, "name");
		map.put(3, "author");
		map.put(4, "publisher");
		map.put(5, "category");
		map.put(6, "isbn_id");

		return map;
	}

	public List<Book> getDefaultValue(){

		List<Book> defaultBooks = new ArrayList<>();
		Book defaultBook = new Book();

		defaultBook.setId(0);
		defaultBook.setAuthor("");
		defaultBook.setCategory("");
		defaultBook.setDisposing("");
		defaultBook.setIsbnId("");
		defaultBook.setKeeping("");
		defaultBook.setLending("");
		defaultBook.setLibraryId("");
		defaultBook.setName("");
		defaultBook.setPublishedDate("");
		defaultBook.setPublisher("");
		defaultBook.setReserving("");
		defaultBook.setShelfId("");
		defaultBook.setType("");

		defaultBooks.add(defaultBook);

		return defaultBooks;
	}
}