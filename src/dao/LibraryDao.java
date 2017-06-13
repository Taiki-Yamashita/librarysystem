package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Library;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class LibraryDao {

	public List<Library> selectAll(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM libraries";
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Library> libraryList = toLibraryList(rs);
			if (libraryList.isEmpty()) {
				return null;
			}else {
				return libraryList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}


	public void insert(Connection connection, Library library) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO libraries ( ");
			sql.append(" name");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, library.getName());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, Library library) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE libraries SET");
			sql.append(" name = ?");
			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, library.getName());
			ps.setInt(2, library.getId());

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}

	}

	private List<Library> toLibraryList(ResultSet rs) throws SQLException {

		List<Library> ret = new ArrayList<Library>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String userId = rs.getString("name");

				Library library = new Library();
				library.setId(id);
				library.setName(userId);

				ret.add(library);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
