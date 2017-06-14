package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Information;
import dao.InformationDao;

public class InformationService {

	public List<Information> selectAll() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Information> informations = new InformationDao().selectAll(connection);

			commit(connection);

			return informations;
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

	public void insert(Information information) {

		Connection connection = null;
		try {
			connection = getConnection();

			new InformationDao().insert(connection, information);

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