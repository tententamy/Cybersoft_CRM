package crm_project_02.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crm_project_02.controller.MysqlConfig;
import crm_project_02.entity.Status;

public class StatusRepository {
	public List<Status> listStatus(){
		List<Status> list = new ArrayList<>();
		String query = "SELECT * FROM Status";
		Connection con = MysqlConfig.getConnect();
		try {
			PreparedStatement stm = con.prepareStatement(query);
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {
				Status status = new Status();
				status.setId(rs.getInt("id"));
				status.setName(rs.getString("name"));
				list.add(status);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
}
