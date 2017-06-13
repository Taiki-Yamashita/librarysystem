package service;

//import static utils.CloseableUtil.*;
//import static utils.DButil.*;

import java.sql.Connection;
import java.util.List;

import beans.Favorite;
import dao.FavoriteDao;

public class FavoriteService {

	public void insert(Favorite favorite) {

		Connection connection = null;
		try {
			connection = getConnection();

			new FavoriteDao().insert(connection, favorite);

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

	public void update(Favorite favorite) {

		Connection connection = null;
		try {
			connection = getConnection();

			new FavoriteDao().update(connection, favorite);

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

	public List<Favorite> selectAll() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Favorite> favorites = new FavoriteDao().selectAll(connection);

			commit(connection);

			return favorites;
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