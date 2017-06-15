package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import beans.Contact;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;
public class ContactDao {

	public List<Contact> selectAll(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM contacts";
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Contact> contactList = toContactList(rs);
			if (contactList.isEmpty()) {
				return null;
			}else {
				return contactList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	public void insert(Connection connection, Contact contact) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO contacts ( ");
			sql.append(" user_id");
			sql.append(", book_id");
			sql.append(", contact_date");
			sql.append(", limited_date");
			sql.append(", finishing");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(", ?"); // 登録時刻から2日後
			sql.append(", ?");

			sql.append(")");

			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, 2);//2日加算

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, contact.getUserId());
			ps.setString(2, contact.getBookId());
			ps.setString(3, contact.getContactDate());
			ps.setString(4, calendar.get(Calendar.YEAR)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.DATE));//登録時刻から2日後
			ps.setString(5, "0");

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, Contact contact) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE contacts SET");
			sql.append("  user_id = ?");
			sql.append(", book_id = ?");
			sql.append(", contact_date = ?");
			sql.append(", limited_date = ?");
			sql.append(", finishing = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, contact.getUserId());
			ps.setString(2, contact.getBookId());
			ps.setString(3, contact.getContactDate());
			ps.setString(4, contact.getLimitedDate());
			ps.setString(5, contact.getFinishing());
			ps.setInt(6, contact.getId());

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

	private List<Contact> toContactList(ResultSet rs) throws SQLException {

		List<Contact> ret = new ArrayList<Contact>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("user_id");
				String bookId = rs.getString("book_id");
				String contactDate = rs.getString("contact_date");
				String limitedDate = rs.getString("limtied_date");
				String finishing = rs.getString("finishing");

				Contact contact = new Contact();
				contact.setId(id);
				contact.setUserId(userId);
				contact.setBookId(bookId);
				contact.setContactDate(contactDate);
				contact.setLimitedDate(limitedDate);
				contact.setFinishing(finishing);

				ret.add(contact);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
