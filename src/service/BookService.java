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

	public List<Book> getSelectedBooks(String selectBox, String freeWord, String condition) {

		Map<String, String> columnMap = getMapData();
		Connection connection = null;

		try {
			connection = getConnection();
			List<Book> books = new BookDao().getSelectedBooks(connection, columnMap.get(selectBox), freeWord, condition);
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

	public List<Book> getRefinedBooks(List<String> newBooks, List<String> libraries,
			List<String> categories, List<String> types) {

		Connection connection = null;

		try {
			connection = getConnection();
			List<Book> books = new BookDao().getRefinedBooks(connection, newBooks, libraries, categories, types);
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

	public Map<String, String> getMapData(){

		Map<String, String> map = new HashMap<>();
		map.put("1", "");
		map.put("2", "name");
		map.put("3", "author");
		map.put("4", "publisher");
		map.put("5", "category");
		map.put("6", "isbn_id");

		return map;
	}

	public Map<String, String> getMapCategory(){

		Map<String, String> map = new HashMap<>();
		map.put("1", "全て");
		map.put("2", "本");
		map.put("3", "著者");
		map.put("4", "出版社");
		map.put("5", "カテゴリ");
		map.put("6", "ISBN番号");

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


	public void lendingBook(int lending, int num) {

		Connection connection = null;
		try {
			connection = getConnection();

			new BookDao().lendingBook(connection, lending, num);

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

	public void reservingBook(int bookId, int num) {

		Connection connection = null;
		try {
			connection = getConnection();

			new BookDao().reservingBook(connection, bookId, num);

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

	public void deliveringBook(int bookId, int num) {

		Connection connection = null;
		try {
			connection = getConnection();

			new BookDao().deliveringBook(connection, bookId, num);

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
}