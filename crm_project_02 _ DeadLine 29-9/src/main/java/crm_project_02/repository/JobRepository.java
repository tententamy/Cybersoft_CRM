package crm_project_02.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.controller.MysqlConfig;
import crm_project_02.entity.Job;
import crm_project_02.entity.Project;
import crm_project_02.entity.Status;
import crm_project_02.entity.User;

public class JobRepository {
	public List<Job> showJob() throws SQLException {
		List<Job> listJ = new ArrayList<>();

		String query = "SELECT j.id,  j.name as jobName, p.name as projectName , us.fullName, j.startDate, j.endDate, st.name as status \r\n"
				+ "FROM Job j JOIN Job_Status_Users jsu ON j.id = jsu.id_job \r\n"
				+ "JOIN Status st ON st.id = jsu.id_status \r\n" + "JOIN Users us ON us.id = jsu.id_user \r\n"
				+ "JOIN Project_Users pu ON pu.id_user = us.id\r\n" + "JOiN Project p ON p.id = pu.id_project\r\n"
				+ "Where p.id = j.id_project";
		Connection con = MysqlConfig.getConnect();
		PreparedStatement stm = con.prepareStatement(query);
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			Job job = new Job();
			job.setId(rs.getInt("id"));
			job.setJobName(rs.getString("jobName"));
			job.setStartDate(rs.getDate("startDate"));
			job.setEndDate(rs.getDate("endDate"));

			Status status = new Status();
			status.setName(rs.getString("status"));
			job.setStatus(status);

			Project project = new Project();
			project.setName(rs.getString("projectName"));
			job.setProject(project);

			User user = new User();
			user.setFullName(rs.getString("fullName"));
			job.setUser(user);

			listJ.add(job);

		}

		return listJ;
	}

	public int insert(String name, Date startDate, Date endDate, int id_project) {
		int count = 0;
		String query = "INSERT INTO  Job (name, startDate , endDate , id_project) VALUES (?,?,?,?)";
		Connection con = MysqlConfig.getConnect();
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, name);
			stm.setDate(2, startDate);
			stm.setDate(3, endDate);
			stm.setInt(4, id_project);
			count = stm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}

		return count;
	}

	public int getNewId() throws SQLException {
		String query = "SELECT id FROM Job j  ORDER BY id DESC LIMIT 1";
		Connection con = MysqlConfig.getConnect();
		PreparedStatement stm = con.prepareStatement(query);
		ResultSet rs = stm.executeQuery();
		int id_job = 0;
		while (rs.next()) {
			id_job = rs.getInt(1);
		}
		return id_job;
	}

	public int checkJobIsExist(String jobName, int id_user, int id_project) {
		String query = "SELECT COUNT(*)  FROM Job j \r\n" + "JOIN Job_Status_Users jsu  ON j.id = jsu.id_job \r\n"
				+ "JOIN Project_Users pu ON jsu.id_user = pu.id_user AND pu.id_project = j.id_project\r\n"
				+ "JOIN Project p ON pu.id_project = p.id \r\n" + "WHERE j.name = ? AND jsu.id_user = ? AND p.id = ?";
		Connection con = MysqlConfig.getConnect();

		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, jobName);
			stm.setInt(2, id_user);
			stm.setInt(3, id_project);
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	public int deleteJobById(int id_job) {
		int count = 0;
		String query = "DELETE FROM Job WHERE id = ?";
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

	public Job searchById(int id_job) {
		
		String query = "SELECT st.id as idStatus, j.name as jobName, p.name as projectName , us.fullName, j.startDate, j.endDate, st.name as status \r\n"
				+ "FROM Job j JOIN Job_Status_Users jsu ON j.id = jsu.id_job \r\n"
				+ "JOIN Status st ON st.id = jsu.id_status \r\n" + "JOIN Users us ON us.id = jsu.id_user \r\n"
				+ "JOIN Project_Users pu ON pu.id_user = us.id\r\n" + "JOiN Project p ON p.id = pu.id_project\r\n"
				+ "AND p.id = j.id_project  \r\n" + "WHERE j.id = ?";

		Connection con = MysqlConfig.getConnect();
		
		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1,id_job);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Job job = new Job();
				job.setId(id_job);
				job.setJobName(rs.getString("jobName"));
				job.setStartDate(rs.getDate("startDate"));
				job.setEndDate(rs.getDate("endDate"));

				Status status = new Status();
				status.setId(rs.getInt("idStatus"));
				status.setName(rs.getString("status"));
				job.setStatus(status);

				Project project = new Project();
				project.setName(rs.getString("projectName"));
				job.setProject(project);

				User user = new User();
				user.setFullName(rs.getString("fullName"));
				job.setUser(user);
				
				return job;

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

//	public static void main(String[] args) {
//		JobRepository j = new JobRepository();
//		List<Job> l = new ArrayList<>();
//		for(Job jo : l) {
//			System.out.println(jo.getStartDate());
//		}
//		
//	}
}
