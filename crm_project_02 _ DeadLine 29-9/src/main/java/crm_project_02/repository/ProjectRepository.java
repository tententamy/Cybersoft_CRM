package crm_project_02.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.controller.MysqlConfig;
import crm_project_02.entity.Project;

public class ProjectRepository {
	public List<Project> Show() throws SQLException {
		List<Project> list = new ArrayList<>();

		String query = "SELECT * FROM Project p ";
		Connection con = MysqlConfig.getConnect();
		PreparedStatement stm = con.prepareStatement(query);
		ResultSet rs = stm.executeQuery();
		while (rs.next()) {
			Project project = new Project();
			project.setId(rs.getInt("id"));
			project.setName(rs.getString("name"));
			project.setStartDate(rs.getDate("startDate"));
			project.setEndDate(rs.getDate("endDate"));
			list.add(project);
		}

		return list;
	}

	public int deleteProjectById(int id) {
		int count = 0;
		String query = "DELETE FROM Project WHERE id=?";
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

	public Project search(int id_project) {
		String query = "SELECT * FROM Project p WHERE id = ?";
		Connection con = MysqlConfig.getConnect();

		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setInt(1, id_project);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				Project project = new Project();
				project.setId(rs.getInt("id"));
				project.setName(rs.getString("name"));
				project.setStartDate(rs.getDate("startDate"));
				project.setEndDate(rs.getDate("endDate"));
				return project;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public int insert(String name, Date dateStart, Date dateEnd) {
		int count = 0;
		String query = "INSERT INTO Project (name,startDate,endDate) VALUES (?,?,?)";
		Connection con = MysqlConfig.getConnect();

		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, name);
			stm.setDate(2, dateStart);
			stm.setDate(3, dateEnd);
			count = stm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return count;
	}

	public int update(String name, Date dateStart, Date dateEnd, int id_project) {
		int count = 0;
		String query = "UPDATE Project  SET name=?  ,startDate=? ,endDate=? WHERE id = ?";
		Connection con = MysqlConfig.getConnect();

		try {
			PreparedStatement stm = con.prepareStatement(query);
			stm.setString(1, name);
			stm.setDate(2, dateStart);
			stm.setDate(3, dateEnd);
			stm.setInt(4, id_project);
			count = stm.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}

		return count;
	}
}
