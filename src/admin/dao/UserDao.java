package admin.dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import exception.SQLRuntimeException;

public class UserDao {

	public List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();

		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String tel = rs.getString("tel");
				String mail = rs.getString("mail");
				String point = rs.getString("point");
				String libraryId = rs.getString("library_id");
				String registerDate = rs.getString("register_date");
				String stopping = rs.getString("stopping");

				User user = new User();
				user.setId(id);
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setName(name);
				user.setAddress(address);
				user.setTel(tel);
				user.setMail(mail);
				user.setPoint(point);
				user.setLibraryId(libraryId);
				user.setRegisterDate(registerDate);
				user.setStopping(stopping);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();

			sql.append("INSERT INTO users(");
			sql.append(" name");
			sql.append(", login_id");
			sql.append(", password");
			sql.append(", address");
			sql.append(", tel");
			sql.append(", mail");
			sql.append(", point");
			sql.append(", library_id");
			sql.append(", register_date");
			sql.append(", stopping");

			sql.append(")VALUES(");
			sql.append("?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", ?");
			sql.append(", CURRENT_TIMESTAMP");
			sql.append(", ?)");

			ps = connection.prepareStatement(sql.toString());


			ps.setString(1, user.getName());
			ps.setString(2, user.getLoginId());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getAddress());
			ps.setString(5, user.getTel());
			ps.setString(6, user.getMail());
			ps.setString(7, "0");
			ps.setString(8, user.getLibraryId());


			ps.executeUpdate();
		} catch (SQLException e){
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

//	public void update(Connection connection, User user ) {
//
//		PreparedStatement ps = null;
//		try {
//			StringBuilder sql = new StringBuilder();
//
//			sql.append("UPDATE INTO users(");
//			sql.append(" name");
//			sql.append(", address");
//			sql.append(", tel");
//			sql.append(", mail");
//			sql.append(", point");
//			sql.append(", register_date");
//			sql.append(", library_id");
//			sql.append(", stopping");
//
//			sql.append(")VALUES(");
//			sql.append("?");
//			sql.append(", ?");
//			sql.append(", ?");
//			sql.append(", ?");
//			sql.append(", ?");
//			sql.append(", ?");
//			sql.append(", ?");
//			sql.append(", ?)");
//
//			ps = connection.prepareStatement(sql.toString());
//
//			ps.setString(1, user.getName());
//			ps.setString(2, user.getAddress());
//			ps.setString(3, user.getTel());
//			ps.setString(4, user.getMail());
//			ps.setString(5, user.getPoint());
//			ps.setString(6, user.getRegisterDate());
//			ps.setString(7, user.getLibraryId());
//			ps.setBoolean(8, user.isStopping());
//
//			ps.executeUpdate();
//		} catch (SQLException e){
//			throw new SQLRuntimeException(e);
//		} finally {
//			close(ps);
//		}
//	}

	public List<User> getSelectAllUser(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users");

			//sql.append("SELECT * FROM users order by branch_id asc, department_id asc ");

			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<User> ret = toUserList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

//	public User getSelectUser(Connection connection, int id) {
//
//		PreparedStatement ps = null;
//		try {
//			String sql = "SELECT * FROM users WHERE id = ?";
//
//			ps = connection.prepareStatement(sql);
//			ps.setInt(1, id);
//
//			ResultSet rs = ps.executeQuery();
//			List<User> userList = toUserList(rs);
//			if (userList.isEmpty() == true) {
//				return null;
//			} else if (2 <= userList.size()) {
//				throw new IllegalStateException("2 <= userList.size()");
//			} else {
//				return userList.get(0);
//			}
//		} catch (SQLException e) {
//			throw new SQLRuntimeException(e);
//		} finally {
//			close(ps);
//		}
//	}


}
