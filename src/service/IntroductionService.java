package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Introduction;
import dao.IntroductionDao;

public class IntroductionService {

	public void insert(Introduction introduction) {

		Connection connection = null;
		try {
			connection = getConnection();

			new IntroductionDao().insert(connection, introduction);

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

//	public void update(Introduction introduction) {
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//
//			new IntroductionDao().update(connection, introduction);
//
//			commit(connection);
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

	public List<Introduction> selectAll() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Introduction> introductions = new IntroductionDao().selectAll(connection);

			commit(connection);

			return introductions;
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