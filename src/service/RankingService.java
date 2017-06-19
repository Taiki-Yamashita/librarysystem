package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Ranking;
import dao.RankingDao;

public class RankingService {
	public List<Ranking> selectAll() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Ranking> bookList = new RankingDao().selectAll(connection);

			commit(connection);

			return bookList;
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

	public List<Ranking> countAll() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Ranking> countList = new RankingDao().countAll(connection);

			commit(connection);

			return countList;
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
