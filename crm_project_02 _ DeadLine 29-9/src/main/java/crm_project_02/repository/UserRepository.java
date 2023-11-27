package crm_project_02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.controller.MysqlConfig;
import crm_project_02.entity.Role;
import crm_project_02.entity.User;

public class UserRepository {
	public int insert(String fullName, String password, String email, String phone, int id_role) {
		int count = 0;
		// tạo câu query
		String query = "INSERT INTO Users (fullName,email,pwd,phone,id_role)" + "VALUES (?,?,?,?,?)";
		// Mở kết nối tới csdl
		Connection con = MysqlConfig.getConnect();
		// truyền câu query vào data đã đc kết nối
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, fullName);
			stm.setString(2, email);
			stm.setString(3, password);
			stm.setString(4, phone);
			stm.setInt(5, id_role);
			count = stm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;
	}

	public List<User> userList() throws SQLException {
		List<User> list = new ArrayList<>();
		String query = "SELECT u.id, u.firstName , u.fullName , u.lastName, r.name  FROM Users u JOIN `Role` r WHERE u.id_role = r.id ";
		Connection con = MysqlConfig.getConnect();
		PreparedStatement stm = con.prepareStatement(query);
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setFirstName(rs.getString("firstName"));
			user.setFullName(rs.getString("fullName"));
			user.setLastName(rs.getString("lastName"));
			Role role = new Role();
			role.setName(rs.getString("name"));
			user.setRole_id(role);
			list.add(user);
		}
		return list;
	}

	public int deleteById(int id) {
		int count = 0;
		String query = "DELETE FROM Users WHERE id = ? ";
		Connection con = MysqlConfig.getConnect();

		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			count = stm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;
	}

	public User searchById(int id_user) {
		User user = new User();
		String query = "SELECT  u.phone, u.id, u.email , u.fullName , u.pwd , r.name, u.id_role  FROM Users u JOIN `Role` r WHERE u.id_role = r.id AND u.id = ?";
		Connection con = MysqlConfig.getConnect();
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id_user);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {

				user.setId(rs.getInt("id"));
				user.setPhone(rs.getString("phone"));
				user.setEmail(rs.getString("email"));
				user.setFullName(rs.getString("fullName"));
				user.setPassword(rs.getString("pwd"));
				Role role = new Role();
				role.setId(rs.getInt("id_role"));
				role.setName(rs.getString("name"));
				user.setRole_id(role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
	}

	public int update(String fullName, String password, String email, String phone, int id_role, int id_user) {
		int count = 0;
		// tạo câu query
		String query = "UPDATE Users SET email = ?, pwd = ?,fullName =?, phone = ?,id_role = ? WHERE id = ? ";
		// Mở kết nối tới csdl
		Connection con = MysqlConfig.getConnect();
		// truyền câu query vào data đã đc kết nối
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, email);
			stm.setString(2, password);
			stm.setString(3, fullName);
			stm.setString(4, phone);
			stm.setInt(5, id_role);
			stm.setInt(6, id_user);
			count = stm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;
	}

}
