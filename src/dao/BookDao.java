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
			String sql = "SELECT * FROM books ORDER BY published_date DESC";
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

	public List<Book> selectShelfId(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM library_system.books GROUP BY shelf_id";
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
	public List<Book> selectRefinedBook(Connection connection, String selectBox, String freeWord, String condition,
			String selectedLibrary, String selectedShelfId, String isReserving, String delay, String bookStatus){

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM books WHERE ");

			if(!selectBox.isEmpty()) sql.append(selectBox + " LIKE ?");
			else{
				if(condition.equals("4")) sql.append("name = ? or author = ? or publisher = ? or category = ? or isbn_id = ?");
				else if(condition.equals("3")) sql.append("name LIKE ? or author LIKE ? or publisher LIKE ? or category LIKE ? or isbn_id LIKE ?");
				else sql.append("CONCAT(name, author, publisher, category, isbn_id) LIKE ?");
			}

			if(bookStatus.equals("2")) sql.append(" AND keeping = 1");
			if(bookStatus.equals("3")) sql.append(" AND lending = 1");
			if(bookStatus.equals("4")) sql.append(" AND disposing = 1");

			if(isReserving.equals("2")) sql.append(" AND reserving = 1");
			if(isReserving.equals("3")) sql.append(" AND reserving = 0");

			if(!selectedShelfId.equals("0")) sql.append(" AND shelf_id = ?");

			if(!selectedLibrary.equals("0")) sql.append(" AND library_id = ?");

			sql.append(" ORDER BY published_date DESC");

			ps = connection.prepareStatement(sql.toString());

			if(!selectBox.isEmpty()){
				if(condition.equals("1")) ps.setString(1, "%" + freeWord + "%");
				if(condition.equals("2")) ps.setString(1, freeWord + "%");
				if(condition.equals("3")) ps.setString(1, "%" + freeWord);
				if(condition.equals("4")) ps.setString(1, freeWord);

				int cnt = 2;
				if(!selectedShelfId.equals("0")) ps.setString(cnt++, selectedShelfId);
				if(!selectedLibrary.equals("0")) ps.setString(cnt, selectedLibrary);
			}else{
				if(condition.equals("4")){
					ps.setString(1, freeWord);
					ps.setString(2, freeWord);
					ps.setString(3, freeWord);
					ps.setString(4, freeWord);
					ps.setString(5, freeWord);

					int cnt = 6;
					if(!selectedShelfId.equals("0")) ps.setString(cnt++, selectedShelfId);
					if(!selectedLibrary.equals("0")) ps.setString(cnt, selectedLibrary);
				}
				else if(condition.equals("3")){
					ps.setString(1, "%" + freeWord);
					ps.setString(2, "%" + freeWord);
					ps.setString(3, "%" + freeWord);
					ps.setString(4, "%" + freeWord);
					ps.setString(5, "%" + freeWord);

					int cnt = 6;
					if(!selectedShelfId.equals("0")) ps.setString(cnt++, selectedShelfId);
					if(!selectedLibrary.equals("0")) ps.setString(cnt, selectedLibrary);
				}
				else{
					if(condition.equals("1")) ps.setString(1, "%" + freeWord + "%");
					if(condition.equals("2")) ps.setString(1, freeWord + "%");

					int cnt = 2;
					if(!selectedShelfId.equals("0")) ps.setString(cnt++, selectedShelfId);
					if(!selectedLibrary.equals("0")) ps.setString(cnt, selectedLibrary);
				}
			}

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

		//System.out.println(category);
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
			ps.setString(10, "1");
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

	public void updateReserving(Connection connection, String bookId) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE books SET");
			sql.append(" reserving = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, "0");
			ps.setString(2, bookId);

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

	public List<Book> getSelectedBooks(Connection connection, String selectBox,
			String freeWord, String condition, String sort, String bookStatus,
			List<String> newBooks, List<String> libraries, List<String> categories, List<String> types){

		PreparedStatement ps = null;
		try {

			StringBuilder sql = new StringBuilder();

			sql.append("SELECT * FROM books WHERE ");
			if(bookStatus.equals("2")) sql.append("lending = ? AND ");
			if(bookStatus.equals("3")) sql.append("lending = ? AND ");
			if(!selectBox.isEmpty()) sql.append(selectBox + " LIKE ?");
			else{
				if(condition.equals("4")) sql.append("name = ? or author = ? or publisher = ? or category = ? or isbn_id = ?");
				else if(condition.equals("3")) sql.append("name LIKE ? or author LIKE ? or publisher LIKE ? or category LIKE ? or isbn_id LIKE ?");
				else sql.append("CONCAT(name, author, publisher, category, isbn_id) LIKE ?");
			}
			sql.append(" AND published_date >= ? "); //change
			if(!libraries.isEmpty()){
				sql.append("AND (");
				for(int i = 0; i < libraries.size(); i++){
					if(i == libraries.size()-1) sql.append("library_id = ?)");
					else sql.append("library_id = ? or ");
				}
			}
			if(!categories.isEmpty()){
				sql.append("AND (");
				for(int i = 0; i < categories.size(); i++){
					if(i == categories.size()-1) sql.append("category = ?)");
					else sql.append("category = ? or ");
				}
			}
			if(!types.isEmpty()){
				sql.append("AND (");
				for(int i = 0; i < types.size(); i++){
					if(i == types.size()-1) sql.append("type = ?)");
					else sql.append("type = ? or ");
				}
			}

			if(sort.equals("1")) sql.append(" ORDER BY published_date DESC");
			if(sort.equals("2")) sql.append(" ORDER BY published_date ASC");
			if(sort.equals("3")) sql.append(" ORDER BY name ASC");
			if(sort.equals("4")) sql.append(" ORDER BY author");
			if(sort.equals("5")) sql.append(" ORDER BY category");
			if(sort.equals("6")) sql.append(" ORDER BY publisher");
			if(sort.equals("0")) sql.append(" ORDER BY published_date DESC");

			int cnt = 0;
			ps = connection.prepareStatement(sql.toString());
			if(!bookStatus.equals("1")){
				if(bookStatus.equals("2")) ps.setString(1, "0");
				if(bookStatus.equals("3")) ps.setString(1, "1");

				if(!selectBox.isEmpty()){
					if(condition.equals("1")) ps.setString(2, "%" + freeWord + "%");
					if(condition.equals("2")) ps.setString(2, freeWord + "%");
					if(condition.equals("3")) ps.setString(2, "%" + freeWord);
					if(condition.equals("4")) ps.setString(2, freeWord);
					ps.setString(3, newBooks.get(0));

					cnt = 4;
					if(!libraries.isEmpty()){
						for(int i = 0; i < libraries.size(); i++){
							ps.setString(cnt++, libraries.get(i));
						}
					}
					if(!categories.isEmpty()){
						for(int i = 0; i < categories.size(); i++){
							ps.setString(cnt++, categories.get(i));
						}
					}
					if(!types.isEmpty()){
						for(int i = 0; i < types.size(); i++){
							ps.setString(cnt++, types.get(i));
						}
					}
				}
				else{
					if(condition.equals("4")){
						ps.setString(2, freeWord);
						ps.setString(3, freeWord);
						ps.setString(4, freeWord);
						ps.setString(5, freeWord);
						ps.setString(6, freeWord);
						ps.setString(7, newBooks.get(0));

						cnt = 8;
						if(!libraries.isEmpty()){
							for(int i = 0; i < libraries.size(); i++){
								ps.setString(cnt++, libraries.get(i));
							}
						}
						if(!categories.isEmpty()){
							for(int i = 0; i < categories.size(); i++){
								ps.setString(cnt++, categories.get(i));
							}
						}
						if(!types.isEmpty()){
							for(int i = 0; i < types.size(); i++){
								ps.setString(cnt++, types.get(i));
							}
						}
					}
					else if(condition.equals("3")){
						ps.setString(2, "%" + freeWord);
						ps.setString(3, "%" + freeWord);
						ps.setString(4, "%" + freeWord);
						ps.setString(5, "%" + freeWord);
						ps.setString(6, "%" + freeWord);
						ps.setString(7, newBooks.get(0));

						cnt = 8;
						if(!libraries.isEmpty()){
							for(int i = 0; i < libraries.size(); i++){
								ps.setString(cnt++, libraries.get(i));
							}
						}
						if(!categories.isEmpty()){
							for(int i = 0; i < categories.size(); i++){
								ps.setString(cnt++, categories.get(i));
							}
						}
						if(!types.isEmpty()){
							for(int i = 0; i < types.size(); i++){
								ps.setString(cnt++, types.get(i));
							}
						}
					}
					else{
						if(condition.equals("1")) ps.setString(2, "%" + freeWord + "%");
						if(condition.equals("2")) ps.setString(2, freeWord + "%");
						ps.setString(3, newBooks.get(0));

						cnt = 4;
						if(!libraries.isEmpty()){
							for(int i = 0; i < libraries.size(); i++){
								ps.setString(cnt++, libraries.get(i));
							}
						}
						if(!categories.isEmpty()){
							for(int i = 0; i < categories.size(); i++){
								ps.setString(cnt++, categories.get(i));
							}
						}
						if(!types.isEmpty()){
							for(int i = 0; i < types.size(); i++){
								ps.setString(cnt++, types.get(i));
							}
						}
					}
				}
			}
			else {
				if(!selectBox.isEmpty()){
					if(condition.equals("1")) ps.setString(1, "%" + freeWord + "%");
					if(condition.equals("2")) ps.setString(1, freeWord + "%");
					if(condition.equals("3")) ps.setString(1, "%" + freeWord);
					if(condition.equals("4")) ps.setString(1, freeWord);
					ps.setString(2, newBooks.get(0));

					cnt = 3;
					if(!libraries.isEmpty()){
						for(int i = 0; i < libraries.size(); i++){
							ps.setString(cnt++, libraries.get(i));
						}
					}
					if(!categories.isEmpty()){
						for(int i = 0; i < categories.size(); i++){
							ps.setString(cnt++, categories.get(i));
						}
					}
					if(!types.isEmpty()){
						for(int i = 0; i < types.size(); i++){
							ps.setString(cnt++, types.get(i));
						}
					}
				}
				else{
					if(condition.equals("4")){
						ps.setString(1, freeWord);
						ps.setString(2, freeWord);
						ps.setString(3, freeWord);
						ps.setString(4, freeWord);
						ps.setString(5, freeWord);
						ps.setString(6, newBooks.get(0));
						cnt = 7;
						if(!libraries.isEmpty()){
							for(int i = 0; i < libraries.size(); i++){
								ps.setString(cnt++, libraries.get(i));
							}
						}
						if(!categories.isEmpty()){
							for(int i = 0; i < categories.size(); i++){
								ps.setString(cnt++, categories.get(i));
							}
						}
						if(!types.isEmpty()){
							for(int i = 0; i < types.size(); i++){
								ps.setString(cnt++, types.get(i));
							}
						}
					}
					else if(condition.equals("3")){
						ps.setString(1, "%" + freeWord);
						ps.setString(2, "%" + freeWord);
						ps.setString(3, "%" + freeWord);
						ps.setString(4, "%" + freeWord);
						ps.setString(5, "%" + freeWord);
						ps.setString(6, newBooks.get(0));
						cnt = 7;
						if(!libraries.isEmpty()){
							for(int i = 0; i < libraries.size(); i++){
								ps.setString(cnt++, libraries.get(i));
							}
						}
						if(!categories.isEmpty()){
							for(int i = 0; i < categories.size(); i++){
								ps.setString(cnt++, categories.get(i));
							}
						}
						if(!types.isEmpty()){
							for(int i = 0; i < types.size(); i++){
								ps.setString(cnt++, types.get(i));
							}
						}
					}
					else{
						if(condition.equals("1")) ps.setString(1, "%" + freeWord + "%");
						if(condition.equals("2")) ps.setString(1, freeWord + "%");
						ps.setString(2, newBooks.get(0));
						cnt = 3;
						if(!libraries.isEmpty()){
							for(int i = 0; i < libraries.size(); i++){
								ps.setString(cnt++, libraries.get(i));
							}
						}
						if(!categories.isEmpty()){
							for(int i = 0; i < categories.size(); i++){
								ps.setString(cnt++, categories.get(i));
							}
						}
						if(!types.isEmpty()){
							for(int i = 0; i < types.size(); i++){
								ps.setString(cnt++, types.get(i));
							}
						}
					}
				}
			}
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

	public void lendingBook(Connection connection, String bookId) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE books SET");
			sql.append(" lending = ?");

			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, "1");
			ps.setString(2, bookId);

			ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

	public void returningBook(Connection connection, String lending) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE books SET");
			sql.append(" lending = ?");

			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, "0");
			ps.setString(2, lending);

			ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}


	public void deliveringBook(Connection connection, int bookId, int num, String time) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE reservations SET");
			sql.append(" reserved_date =?");
			sql.append(", delivering = ?");
			if(num ==1){
				sql.append(" ,canceling = ?");
			}
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, time);
			ps.setInt(2, num);
			if(num == 1) {
				ps.setString(3, "0");
				ps.setInt(4, bookId);
			} else {
				ps.setInt(3, bookId);
			}


			ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}
	public void cancelingBook(Connection connection, int bookId, int num, String time) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE reservations SET");
			sql.append(" reserved_date = ?");
			sql.append(", canceling = ?");
			if(num ==1){
				sql.append(" ,delivering = ?");
			}
			sql.append(" WHERE");
			sql.append(" book_id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, time);
			ps.setInt(2, num);
			if(num == 1) {
				ps.setString(3, "0");
				ps.setInt(4, bookId);
			} else {
				ps.setInt(3, bookId);
			}


			ps.executeUpdate();
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}
	}

	public List<Book> selectAdmin(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM book_admin";
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			List<Book> bookList = toAdminList(rs);
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


	private List<Book> toAdminList(ResultSet rs) throws SQLException {
	List<Book> ret = new ArrayList<Book>();
	try {
		while (rs.next()) {
			int id = rs.getInt("book_id");
			String name = rs.getString("book_name");
			String author = rs.getString("book_author");
			String publisher = rs.getString("book_publisher");
			String category = rs.getString("book_category");
			String type = rs.getString("book_type");
			String libraryId = rs.getString("book_libraryId");
			String shelfId = rs.getString("shelf_id");
			String isbnId = rs.getString("isbn_id");
			String publishedDate = rs.getString("published_date");
			String keeping = rs.getString("keeping");
			String lending = rs.getString("lending");
			String reserving = rs.getString("reserving");
			String disposing = rs.getString("disposing");
			String userName = rs.getString("user_name");
			int userId = rs.getInt("user_id");
			String reservedDate = rs.getString("reserved_date");
			String limitedDate = rs.getString("limited_date");
			String returning = rs.getString("returning");


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
			book.setUserName(userName);
			book.setUserId(userId);
			book.setReservedDate(reservedDate);
			book.setLimitedDate(limitedDate);
			book.setReturning(returning);


			ret.add(book);
		}
		return ret;
	} finally {
		close(rs);
	}
	}

	public void status(Connection connection,int bookId) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE books SET");
			sql.append(" reserving = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, 0);
			ps.setInt(2, bookId);


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
}
