package crm_project_02.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;

import crm_project_02.entity.User;

/*
 * package : 
 * - Controller là nơi chứa toàn bộ file liên quan tới khai báo đường dẫn và xử lý 
 * đường dẫn
 * 
 * */
@WebServlet(name = "loginController", urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int maxAge = 60;
		Cookie cookie = new Cookie("hoten",URLEncoder.encode("Nguyễn Văn A","UTF-8"));
		cookie.setMaxAge(maxAge);
		
		// yêu cầu tạo cookie
		resp.addCookie(cookie);
		
		req.getRequestDispatcher("login.html").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
//		? : đại diện cho tham số sẽ được truyền vào khi sử dụng JDBC
		String querry = "SELECT * FROM Users Where email = ?  AND  pwd =?";
		
		Connection con = MysqlConfig.getConnect();
		try {
			// chuẩn bị câu query cho truyền xuống csdl thông qua PrepareStament
			PreparedStatement stament = con.prepareStatement(querry);
			// gán giá trij cho tham số trong câu query có dấu (?)
			stament.setString(1, email);
			stament.setString(2, password);
			/*
			 * executeQuery(): khi câu query là câu SELECT
			 * executeUpdate(): khi câu query khác câu SELECT => INSERT, UPDATE, DELETE...
			 * */
			ResultSet resultset = stament.executeQuery();
			List<User> listUser = new ArrayList<>();
			// khi nào reresultset còn qua dòng tiép theo đc thì làm
			while(resultset.next()) {
				//Duyệt qua từng dòng dữ liệu truy vấn đc
				User user = new User();
				user.setId(resultset.getInt("id"));
				user.setEmail(resultset.getString("email"));
				
				listUser.add(user);
			}
			
			if(listUser.size()>0) {
				// user tồn tại đăng nhập thành công
				System.out.println("thành công");
			}else {
				// user ko tồn tại đăng nhập thất bại
				System.out.println("thất bại");
			}
			
		} catch (Exception e) {
			System.out.println("Lỗi thực thi " + e.getLocalizedMessage());
		}
		
	}
}
