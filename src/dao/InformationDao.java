package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Information;
import exception.SQLRuntimeException;

public class InformationDao {

	public List<Information> selectAll(Connection connection){

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM informations";
			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Information> informationList = toInformationList(rs);
			if (informationList.isEmpty()) {
				return null;
			}else {
				return informationList;
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Information> toInformationList(ResultSet rs) throws SQLException {

		List<Information> ret = new ArrayList<Information>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String libraryId = rs.getString("library_id");
				String registeredDate = rs.getString("registered_date");
				String message = rs.getString("message");

				Information information = new Information();
				information.setId(id);
				information.setLibraryId(libraryId);
				information.setRegisteredDate(registeredDate);
				information.setMessage(message);


				ret.add(information);
			}
			return ret;
		} finally {
			close(rs);
		}
	}




	public void insert(Connection connection, Information information) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO informations ( ");
			sql.append("  message");
			sql.append(", library_id");
			sql.append(", registered_date");
			sql.append(") VALUES (");
			sql.append(" ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, information.getMessage());
			ps.setString(2, information.getLibraryId());
			ps.setString(3, information.getRegisteredDate());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
