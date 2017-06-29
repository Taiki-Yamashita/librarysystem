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


	public List<Notification> selectRefinedInformation(Connection connection, String library){

		PreparedStatement ps = null;
		try {

			String sql = "SELECT * FROM notifications WHERE library_id = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, library);

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
				String title = rs.getString("title");

				Notification notification = new Notification();
				notification.setId(id);
				notification.setLibraryId(libraryId);
				notification.setRegisteredDate(registeredDate);
				notification.setMessage(message);
				notification.setTitle(title);

				ret.add(notification);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public Notification select(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM notifications where id = ?";
			ps = connection.prepareStatement(sql);

			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<Notification> notification= toNotification(rs);
				return notification.get(0);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Notification> toNotification(ResultSet rs) throws SQLException {

		List<Notification> ret = new ArrayList<Notification>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String message = rs.getString("message");
				String libraryId = rs.getString("library_id");
				String registeredDate = rs.getString("registered_date");

				Notification notification = new Notification();
				notification.setId(id);
				notification.setTitle(title);
				notification.setMessage(message);
				notification.setLibraryId(libraryId);
				notification.setRegisteredDate(registeredDate);

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
			sql.append("INSERT INTO notifications ( ");
			sql.append(" title");
			sql.append(", message");
			sql.append(", library_id");
			sql.append(", registered_date");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, notification.getTitle());
			ps.setString(2, notification.getMessage());
			ps.setString(3, notification.getLibraryId());
			ps.setString(4, notification.getRegisteredDate());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


}
