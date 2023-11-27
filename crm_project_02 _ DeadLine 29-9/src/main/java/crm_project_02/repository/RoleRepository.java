package crm_project_02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.controller.MysqlConfig;
import crm_project_02.entity.Role;

//RoleRepository: chứa toàn bộ câu truy vấn liên quan tới role

public class RoleRepository {

	public int insert(String name, String desc) {
		String query = "INSERT INTO Role (name,description) VALUES (?,?)";
		Connection con = MysqlConfig.getConnect();
		int count = 0;
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, name);
			stm.setString(2, desc);
			count = stm.executeUpdate();
		} catch (Exception e) {
			e.getLocalizedMessage();
		} finally {
			try {
				con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return count;

	}

	// đối với câu select tên hàm sẽ bắt đầu bằng chữ find
	// nếu có đk where : by

	public List<Role> findAll() throws SQLException {
		// chuẩn bị câu querry
		String query = "SELECT * FROM Role";

		// Mở kết nối csdl
		Connection con = MysqlConfig.getConnect();
		// chuyền câu query vào connection
		PreparedStatement stm = con.prepareStatement(query);
		// thực thi câu truy vấn và đc 1 ds
		ResultSet rs = stm.executeQuery();
		List<Role> listrole = new ArrayList<>();
		// duỵêt qua từng dog dữ liệu
		while (rs.next()) {
			Role role = new Role();
			role.setId(rs.getInt("id"));
			role.setName(rs.getString("name"));
			role.setDescription(rs.getString("description"));
			listrole.add(role);
		}
		return listrole;
	}

	public int deleteRoleById(int id) {
		int count = 0;
		String query = "DELETE FROM Role WHERE id=?";
		Connection con = MysqlConfig.getConnect();
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id);
			count = stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	public Role searchById(int id_role) {
		String query = "SELECT * FROM Role WHERE id = ?";

		// Mở kết nối csdl
		Connection con = MysqlConfig.getConnect();
		// chuyền câu query vào connection

		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id_role);
			ResultSet rs = stm.executeQuery();
			List<Role> listrole = new ArrayList<>();
			// duỵêt qua từng dog dữ liệu
			while (rs.next()) {
				Role role = new Role();
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name"));
				role.setDescription(rs.getString("description"));
				return role;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// thực thi câu truy vấn và đc 1 ds

		return null;

	}
	
	
	public int update(String name, String desc, int id_role) {
		String query = "UPDATE Role SET name = ? , description = ? WHERE id = ?";
		Connection con = MysqlConfig.getConnect();
		int count = 0;
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, name);
			stm.setString(2, desc);
			stm.setInt(3, id_role);
			count = stm.executeUpdate();
		} catch (Exception e) {
			e.getLocalizedMessage();
		} finally {
			try {
				con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return count;

	}

}
