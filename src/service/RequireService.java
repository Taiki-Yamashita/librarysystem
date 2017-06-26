package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;

import beans.Require;
import dao.RequireDao;

public class RequireService {

	public void insert(Require require) {

		Connection connection = null;
		try {
			connection = getConnection();

			new RequireDao().insert(connection, require);

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
	public void delete( String id) {
		Connection connection = null;
		try {
			connection = getConnection();

			new RequireDao().delete(connection, id);

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
