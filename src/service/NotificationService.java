package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Notification;
import dao.NotificationDao;

public class NotificationService {

	public List<Notification> selectAll() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Notification> notifications = new NotificationDao().selectAll(connection);

			commit(connection);

			return notifications;
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

	public void insert(Notification notification) {

		Connection connection = null;
		try {
			connection = getConnection();

			new NotificationDao().insert(connection, notification);

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