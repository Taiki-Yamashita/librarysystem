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
			sql.append(", published_date");
			sql.append(", library_id");
			sql.append(", shelf_id");
			sql.append(", isbn_id");
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
			ps.setString(6, book.getPublishedDate());
			ps.setString(7, book.getLibraryId());
			ps.setString(8, book.getShelfId());
			ps.setString(9, book.getIsbnId());
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
			sql.append(", published_date = ?");
			sql.append(", library_id = ?");
			sql.append(", shelf_id = ?");
			sql.append(", isbn_id = ?");
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
			ps.setString(6, book.getPublishedDate());
			ps.setString(7, book.getLibraryId());
			ps.setString(8, book.getShelfId());
			ps.setString(9, book.getIsbnId());
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
				String published_date = rs.getString("published_date");
				String libraryId = rs.getString("library_id");
				String shelfId = rs.getString("shelf_id");
				String isbnId = rs.getString("isbn_id");
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
				book.setPublishedDate(published_date);
				book.setLibraryId(libraryId);
				book.setShelfId(shelfId);
				book.setIsbnId(isbnId);
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

}
