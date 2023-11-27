package crm_project_02.controller;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.tomcat.dbcp.dbcp2.DriverManagerConnectionFactory;

// cấu hình JDBC kết nối tới server MYSQL
public class MysqlConfig {
	public static Connection getConnect() {
		// khai báo Driver tương ứng vs csdl cần kết nối
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//khai báo Driver sẽ mở kết nối tới csdl nào
			return DriverManager.getConnection("jdbc:mysql://localhost:3307/crm", "root", "admin123");
		} catch (Exception e) {
			// Lối xảy ra sẽ chạy vào
			System.out.println("Lỗi kết nối database " + e.getLocalizedMessage());
			return null;
		}
		
	}
}
