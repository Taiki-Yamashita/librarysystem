package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.User;
import dao.UserDao;

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

	public void update(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			new UserDao().update(connection, user);

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

	public User selectUser(int user_id) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			User user = userDao.selectUser(connection, user_id);

			commit(connection);

			return user;
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



	public List<User> selectAll() {

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

	public User getLoginUser(String loginId, String password) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userdao = new UserDao();
			User ret = userdao.getLoginUser(connection, loginId, password);


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
