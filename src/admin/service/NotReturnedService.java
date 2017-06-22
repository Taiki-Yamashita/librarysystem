package admin.service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import admin.beans.NotReturned;
import admin.dao.NotReturnedDao;

public class NotReturnedService {
	public List<NotReturned> select(int bookId) {

		Connection connection = null;
		try {
			connection = getConnection();

			NotReturnedDao notReturneddao = new NotReturnedDao();
			List<NotReturned> ret = notReturneddao.getSelectAllNotReturned(connection, bookId);


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
