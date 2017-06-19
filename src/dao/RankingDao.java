package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Ranking;
import exception.SQLRuntimeException;

public class RankingDao {

	public List<Ranking> selectAll(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "select distinct book_name from library_system.book_rankings; ";
			ps = connection.prepareStatement(sql);


			ResultSet rs = ps.executeQuery();
			List<Ranking> bookList = toBookList(rs);
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
	private List<Ranking> toBookList(ResultSet rs) throws SQLException {

		List<Ranking> ret = new ArrayList<Ranking>();
		try {
			while (rs.next()) {
				//int id = rs.getInt("id");

				String bookName = rs.getString("book_name");


				Ranking bookList = new Ranking();
				bookList.setBookName(bookName);



				ret.add(bookList);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
	public List<Ranking> countAll(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT *, COUNT(*) as count FROM library_system.book_rankings group by book_id; ";
			ps = connection.prepareStatement(sql);


			ResultSet rs = ps.executeQuery();
			List<Ranking> countList = toCountList(rs);
			if (countList.isEmpty()) {
				return null;
			}else {
				return countList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<Ranking> toCountList(ResultSet rs) throws SQLException {

		List<Ranking> ret = new ArrayList<Ranking>();
		try {
			while (rs.next()) {

				String bookName = rs.getString("book_name");
				String bookId = rs.getString("book_id");
				String count = rs.getString("count");

				Ranking countList = new Ranking();
				countList.setBookName(bookName);
				countList.setBookId(bookId);
				countList.setCount(count);


				ret.add(countList);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}