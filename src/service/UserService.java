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

	public void point(int point, int userId) {

		Connection connection = null;
		try {
			connection = getConnection();

			new UserDao().point(connection, point, userId);

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

	public User selectUser(String userId) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			User user = userDao.selectUser(connection, userId);

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

	public User select(int userId) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			User user = userDao.select(connection, userId);

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

	public List<User> getRefinedUser(String freeWord, String selectedLibrary) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userdao = new UserDao();
			List<User> ret = userdao.getRefinedUser(connection, freeWord, selectedLibrary);


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

	public void stoppingUser(int stopping, int num, String time) {

		Connection connection = null;
		try {
			connection = getConnection();

			new UserDao().stoppingUser(connection, stopping, num, time);

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
