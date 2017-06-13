package admin.service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import admin.dao.RecieveDao;
import beans.Require;

public class RecieveService {
	public List<Require> select() {

		Connection connection = null;
		try {
			connection = getConnection();

			RecieveDao recievedao = new RecieveDao();
			List<Require> ret = recievedao.getSelectAllRecieve(connection);


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