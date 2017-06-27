package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Contact;
import dao.ContactDao;

public class ContactService {

	public void insert(Contact contact) {

		Connection connection = null;
		try {
			connection = getConnection();

			new ContactDao().insert(connection, contact);

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

	public void update(Contact contact) {

		Connection connection = null;
		try {
			connection = getConnection();

			new ContactDao().update(connection, contact);

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

	public List<Contact> selectAll() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Contact> contacts = new ContactDao().selectAll(connection);

			commit(connection);

			return contacts;
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