package dao;


import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Book;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

//import exception.NoRowsUpdatedRuntimeException;
//import exception.SQLRuntimeException;

public class BookDao {

	public List<Book> selectAll(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM books";
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			List<Book> bookList = toBookList(rs);
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


	public void insert(Connection connection, Book book) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO books ( ");
			sql.append(" name");
			sql.append(", author");
			sql.append(", publisher");
			sql.append(", category");
			sql.append(", type");
			sql.append(", library_id");
			sql.append(", shelf_id");
			sql.append(", isbn_id");
			sql.append(", published_date");
			sql.append(", keeping");
			sql.append(", lending");
			sql.append(", reserving");
			sql.append(", disposing");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, book.getName());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getPublisher());
			ps.setString(4, book.getCategory());
			ps.setString(5, book.getType());
			ps.setString(6, book.getLibraryId());
			ps.setString(7, book.getShelfId());
			ps.setString(8, book.getIsbnId());
			ps.setString(9, book.getPublishedDate());
			ps.setString(10, "0");
			ps.setString(11, "0");
			ps.setString(12, "0");
			ps.setString(13, "0");

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, Book book) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE books SET");
			sql.append("  name = ?");
			sql.append(", author = ?");
			sql.append(", publisher = ?");
			sql.append(", category = ?");
			sql.append(", type = ?");
			sql.append(", library_id = ?");
			sql.append(", shelf_id = ?");
			sql.append(", isbn_id = ?");
			sql.append(", published_date = ?");
			sql.append(", keeping = ?");
			sql.append(", lending = ?");
			sql.append(", reserving = ?");
			sql.append(", disposing = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, book.getName());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getPublisher());
			ps.setString(4, book.getCategory());
			ps.setString(5, book.getType());
			ps.setString(6, book.getLibraryId());
			ps.setString(7, book.getShelfId());
			ps.setString(8, book.getIsbnId());
			ps.setString(9, book.getPublishedDate());
			ps.setString(10, book.getKeeping());
			ps.setString(11, book.getLending());
			ps.setString(12, book.getReserving());
			ps.setString(13, book.getDisposing());
			ps.setInt(14, book.getId());

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	public Book selectBook(Connection connection, int book_id){
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM books WHERE id = ? ";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, book_id);

			ResultSet rs =ps.executeQuery();
			List<Book> bookList = toBookList(rs);
			if(bookList.isEmpty() == true) {
				return null;
			} else if(2<= bookList.size()) {
				throw new IllegalStateException("2<= bookList.size()");
			} else {
				return bookList.get(0);

			}
		}catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}finally {
			close(ps);
		}
	}

	public List<Book> getSelectedBooks(Connection connection, String selectBox, String freeWord){

		PreparedStatement ps = null;
		try {

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM books WHERE ");
			if(!selectBox.isEmpty()) sql.append(selectBox + " LIKE ?");
			else sql.append("CONCAT(name, author, publisher, category, isbn_id) LIKE ?");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, "%" + freeWord + "%");

			ResultSet rs = ps.executeQuery();

			List<Book> bookList = toBookList(rs);
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


	private List<Book> toBookList(ResultSet rs) throws SQLException {

		List<Book> ret = new ArrayList<Book>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");
				String category = rs.getString("category");
				String type = rs.getString("type");
				String libraryId = rs.getString("library_id");
				String shelfId = rs.getString("shelf_id");
				String isbnId = rs.getString("isbn_id");
				String publishedDate = rs.getString("published_date");
				String keeping = rs.getString("keeping");
				String lending = rs.getString("lending");
				String reserving = rs.getString("reserving");
				String disposing = rs.getString("disposing");

				Book book = new Book();
				book.setId(id);
				book.setName(name);
				book.setAuthor(author);
				book.setPublisher(publisher);
				book.setCategory(category);
				book.setType(type);
				book.setLibraryId(libraryId);
				book.setShelfId(shelfId);
				book.setIsbnId(isbnId);
				book.setPublishedDate(publishedDate);
				book.setKeeping(keeping);
				book.setLending(lending);
				book.setReserving(reserving);
				book.setDisposing(disposing);

				ret.add(book);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void lendingBook(Connection connection, int lending, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE books SET");
			sql.append(" lending = ?");

			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, num);
			ps.setInt(2, lending);

			ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
	public void reservingBook(Connection connection, int reserving, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE books SET");
			sql.append(" reserving = ?");
			if(num ==1) {
			sql.append(" keeping = ?");
			sql.append(" lending = ?");
			sql.append(" disposing = ?");}

			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, num);
			if(num == 1) {
			ps.setString(2, "0");
			ps.setString(3, "0");
			ps.setString(4, "0");
			ps.setInt(5, reserving);
			} else {
				ps.setInt(2, reserving);
			}

			System.out.println(ps);


			ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
	public void resercingBook(Connection connection, int reserving, int num, int reset) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE books SET");
			sql.append(" reserving = ?");
			sql.append(" keeping = ?");
			sql.append(" lending = ?");
			sql.append(" disposing = ?");

			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, num);
			ps.setInt(2, reset);
			ps.setInt(3, reset);
			ps.setInt(4, reset);
			ps.setInt(5, reserving);

			ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
}
