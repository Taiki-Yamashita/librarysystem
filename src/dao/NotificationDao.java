package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Notification;
import exception.SQLRuntimeException;

public class NotificationDao {

	public List<Notification> selectAll(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM notifications";
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Notification> notificationList = toNotificationList(rs);
			if (notificationList.isEmpty()) {
				return null;
			}else {
				return notificationList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Notification> toNotificationList(ResultSet rs) throws SQLException {

		List<Notification> ret = new ArrayList<Notification>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String libraryId = rs.getString("library_id");
				String registeredDate = rs.getString("registered_date");
				String message = rs.getString("message");

				Notification notification = new Notification();
				notification.setId(id);
				notification.setLibraryId(libraryId);
				notification.setRegisteredDate(registeredDate);
				notification.setMessage(message);


				ret.add(notification);
			}
			return ret;
		} finally {
			close(rs);
		}
	}




	public void insert(Connection connection, Notification notification) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO informations ( ");
			sql.append("  message");
			sql.append(", library_id");
			sql.append(", registered_date");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, notification.getMessage());
			ps.setString(2, notification.getLibraryId());
			ps.setString(3, notification.getRegisteredDate());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
