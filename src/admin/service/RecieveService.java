package admin.service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import admin.dao.RecieveDao;
import beans.Require;

public class RecieveService {
	public List<Require> select(int num) {

		Connection connection = null;
		try {
			connection = getConnection();

			RecieveDao receivedao = new RecieveDao();
			List<Require> ret = receivedao.getSelectAllRecieve(connection, num);


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

	public List<Require> select() {

		Connection connection = null;
		try {
			connection = getConnection();

			RecieveDao receivedao = new RecieveDao();
			List<Require> ret = receivedao.getSelectAllRecieve(connection);


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


	public void update(int flag, int id) {
		Connection connection = null;
		try {
			connection = getConnection();

		new RecieveDao().update(connection, flag, id);


			commit(connection);
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
