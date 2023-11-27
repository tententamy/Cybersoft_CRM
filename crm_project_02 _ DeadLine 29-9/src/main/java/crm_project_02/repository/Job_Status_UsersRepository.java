package crm_project_02.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import crm_project_02.controller.MysqlConfig;

public class Job_Status_UsersRepository {
	public int insert(int id_user, int id_job) throws SQLException {
		int count = 0;
		String query = "INSERT INTO Job_Status_Users (id_user,id_job,id_status) VALUES (?,?,2)";
		Connection con = MysqlConfig.getConnect();
		PreparedStatement stm = con.prepareStatement(query);
		stm.setInt(1, id_user);
		stm.setInt(2, id_job);
		count = stm.executeUpdate();
		return count;
	}
	
	public int deleteJob_Status_UserById( int id_job) {
		int count = 0;
		String query = "DELETE FROM Job_Status_Users WHERE id_job=?";
		Connection con = MysqlConfig.getConnect();
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id_job);
			count = stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public int updateJob_Status_UserById( int id_job, int id_status) {
		int count = 0;
		String query = "UPDATE Job_Status_Users SET id_status = ? WHERE id_job  = ?;";
		Connection con = MysqlConfig.getConnect();
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id_status);
			stm.setInt(2, id_job);
			count = stm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
}
