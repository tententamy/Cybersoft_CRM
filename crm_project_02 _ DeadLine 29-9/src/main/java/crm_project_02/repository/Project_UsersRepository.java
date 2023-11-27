package crm_project_02.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import crm_project_02.controller.MysqlConfig;

public class Project_UsersRepository {
	public int insert(int id_user, int id_project, Date createDate) throws SQLException {
		int count = 0;
		String query = "INSERT INTO Project_Users (id_user,id_project,createDate) VALUES (?,?,?)";
		Connection con = MysqlConfig.getConnect();
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, id_user);
		stm.setInt(2, id_project);
		stm.setDate(3, createDate);
		count = stm.executeUpdate();
		return count;
	}
	
	public int deleteProjectById(int id_project) {
		int count = 0;
		String query = "DELETE FROM Project_Users WHERE id_project=?";
		Connection con = MysqlConfig.getConnect();
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id_project);
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
}
