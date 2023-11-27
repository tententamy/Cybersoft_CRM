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

import crm_project_02.entity.Role;
import crm_project_02.service.RoleService;

@WebServlet(name = "roleController", urlPatterns = { "/role-add", "/role", "/role-edit" })
public class RoleController extends HttpServlet {

	private RoleService roleS = new RoleService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Lấy ra đường dẫn mà người dùng đang gọi
		String path = req.getServletPath();
		if (path.equals("/role-add")) {
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
		} else if (path.equals("/role")) {
			List<Role> listRole = new ArrayList<>();
			try {
				listRole = roleS.list();
			} catch (Exception e) {
				e.printStackTrace();
			}
			req.setAttribute("list", listRole);
			req.getRequestDispatcher("role-table.jsp").forward(req, resp);
		} else if (path.equals("/role-edit")) {
			int id_role = Integer.parseInt(req.getParameter("id_role"));
			Role role = roleS.getRoleById(id_role);
			req.setAttribute("role", role);
			req.getRequestDispatcher("role-edit.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("UTF-8");
		String roleName = req.getParameter("role-name");
		String desc = req.getParameter("desc");
		String path = req.getServletPath();

		if (path.equals("/role-add")) {
			boolean isSuccess = false;
			if (roleName.isEmpty()) {
				req.setAttribute("errorRoleName", "Không được trống tên quyền");
			}

			if (desc.isEmpty()) {
				req.setAttribute("errorDesc", "Hãy viết chút mô tả");
			}

			if (desc.isEmpty() || roleName.isEmpty()) {
				req.setAttribute("error", "Vui Lòng Nhập Lại");
			} else {
				isSuccess = roleS.addRole(roleName, desc);
			}
			req.setAttribute("isSuccess", isSuccess);
			req.getRequestDispatcher("role-add.jsp").forward(req, resp);
		} else if (path.equals("/role-edit")) {
			int id_role = Integer.parseInt(req.getParameter("id_role"));
			boolean isSuccess = roleS.updateRole(roleName, desc, id_role);
			Role role = roleS.getRoleById(id_role);
			if (isSuccess) {
				req.setAttribute("isSuccess", "cập nhật thành công");
			}
			req.setAttribute("role", role);
			req.getRequestDispatcher("role-edit.jsp").forward(req, resp);
		}

//		String roleName = req.getParameter("role-name");
//		String desc = req.getParameter("desc");
//		boolean isSuccess = false;
//		// Nhận tham số nếu có
//
//		if (roleName.isEmpty()) {
//			req.setAttribute("errorRoleName", "Không được trống tên quyền");
//		}
//
//		if (desc.isEmpty()) {
//			req.setAttribute("errorDesc", "Hãy viết chút mô tả");
//		}
//
//		if (desc.isEmpty() || roleName.isEmpty()) {
//			req.setAttribute("error", "Vui Lòng Nhập Lại");
//		}else {
//			String query = "INSERT INTO Role (name,description) VALUES (?,?)";
//			Connection con = MysqlConfig.getConnect();
//			try {
//
//				PreparedStatement stm = con.prepareStatement(query);
//				stm.setString(1, roleName);
//				stm.setString(2, desc);
//				int count = stm.executeUpdate();
//				if (count > 0) {
//					isSuccess = true;
//					System.out.println("thành công");
//				}
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					if (con != null) {
//						con.close();
//					}
//				} catch (SQLException e2) {
//					e2.printStackTrace();
//				}
//			}
//		}
//
//		req.setAttribute("isSuccess", isSuccess);
//		req.getRequestDispatcher("role-add.jsp").forward(req, resp);
	}

}
