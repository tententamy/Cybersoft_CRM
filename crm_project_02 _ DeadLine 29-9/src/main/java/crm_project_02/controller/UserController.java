package crm_project_02.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.protocol.Resultset;

import crm_project_02.entity.Role;
import crm_project_02.entity.User;
import crm_project_02.service.UserService;

@WebServlet(name = "userController", urlPatterns = { "/user-add", "/user", "/user-edit" })
public class UserController extends HttpServlet {

	private UserService userS = new UserService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		if (path.equals("/user")) {
			List<User> listU = new ArrayList<>();
			try {
				listU = userS.getAllUsers();
			} catch (SQLException e) {
				System.out.println("Lỗi getAllRole " + e.getLocalizedMessage());
			}
			req.setAttribute("listUser", listU);
			req.getRequestDispatcher("user-table.jsp").forward(req, resp);
		} else if (path.equals("/user-add")) {

			List<Role> list = new ArrayList<>();
			try {
				list = userS.getRole();
			} catch (SQLException e) {
				System.out.println("Lỗi getAllRole " + e.getLocalizedMessage());
			}
			req.setAttribute("listRole", list);
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
		} else if (path.equals("/user-edit")) {
			int id_user = Integer.parseInt(req.getParameter("id_user"));
			List<Role> list = new ArrayList<>();
			try {
				list = userS.getRole();
			} catch (SQLException e) {
				System.out.println("Lỗi getAllRole " + e.getLocalizedMessage());
			}
			User user = userS.getUserById(id_user);
			req.setAttribute("user", user);
			req.setAttribute("listRole", list);
			req.getRequestDispatcher("user-edit.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("UTF-8");
		// Lấy tham số từ thẻ form truyền qua khi ngừ dùng click button
		String fullName = req.getParameter("fullName");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		int id_role = Integer.parseInt(req.getParameter("role"));

		if (path.equals("/user-add")) {
			userS.addUser(fullName, password, email, phone, id_role);

			List<Role> list = new ArrayList<>();
			try {
				list = userS.getRole();
			} catch (SQLException e) {
				System.out.println("Lỗi getAllRole " + e.getLocalizedMessage());
			}
			req.setAttribute("listRole", list);
			req.getRequestDispatcher("user-add.jsp").forward(req, resp);
		} else if (path.equals("/user-edit")) {
			int id_user = Integer.parseInt(req.getParameter("id_user"));
			boolean isSuccess =userS.updateUser(fullName, password, email, phone, id_role, id_user);
			List<Role> list = new ArrayList<>();
			try {
				list = userS.getRole();
			} catch (SQLException e) {
				System.out.println("Lỗi getAllRole " + e.getLocalizedMessage());
			}
			if (isSuccess) {
				req.setAttribute("isSuccess", "cập nhật thành công");
			}
			User user = userS.getUserById(id_user);
			req.setAttribute("user", user);
			req.setAttribute("listRole", list);
			req.getRequestDispatcher("user-edit.jsp").forward(req, resp);
		}

	}

}
