package admin.service;

import static utils.Closeableutil.*;
import static utils.DButil.*;

import java.sql.Connection;
import java.util.List;

import admin.beans.User;
import admin.dao.UserDao;

public class UserService {

	public void insert(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			//String encPassword = Cipherutil.encrypt(user.getPassword());
			//user.setPassword(encPassword);

			UserDao userDao = new UserDao();
			userDao.insert(connection, user);

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

	public void update(User user, String password) {

		Connection connection = null;
		try {
			connection = getConnection();

			//String encPassword = Cipherutil.encrypt(user.getPassword());
			//user.setPassword(encPassword);

			UserDao userDao = new UserDao();
			//userDao.update(connection, user, password);

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

//	public User getSelectUser(int id) {
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//
//			UserDao userDao = new UserDao();
//			User user = userDao.getSelectUser(connection, id);
//
//			commit(connection);
//
//			return user;
//		} catch (RuntimeException e) {
//			rollback(connection);
//			throw e;
//		} catch (Error e) {
//			rollback(connection);
//			throw e;
//		} finally {
//			close(connection);
//		}
//	}
//
	public List<User> getSelectAllUser() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userdao = new UserDao();
			List<User> ret = userdao.getSelectAllUser(connection);


			commit(connection);
			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch(Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}
