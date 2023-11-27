package crm_project_02.api;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import crm_project_02.entity.Role;
import crm_project_02.entity.User;
import crm_project_02.payload.respone.BaseRespone;
import crm_project_02.service.RoleService;

@WebServlet(name="apiRoleController", urlPatterns = {"/api/role" ,"/api/role/delete"})
public class ApiRoleController extends HttpServlet {
	
	private RoleService roleS = new RoleService();
	private Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		List<Role> listRole;
		if (path.equals("/api/role")) {

			try {
				listRole = roleS.list();

				BaseRespone repone = new BaseRespone();
				repone.setStatusCode(200);
				repone.setMessage("");
				repone.setData(listRole);
				
				// chuyển list về dạng JSON
				String dataJson = gson.toJson(repone);
				
				// Trả dữ liệu JSON
				PrintWriter out = resp.getWriter();
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				out.print(dataJson);
				out.flush();
			} catch (SQLException e) {
				System.out.println("lỗi sql " + e.getLocalizedMessage());
			}
		} else if (path.equals("/api/role/delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			boolean isSuccess = roleS.deleteRole(id);

			BaseRespone repone = new BaseRespone();
			repone.setStatusCode(200);
			repone.setMessage(isSuccess ? "xóa thành công" : "xóa thất bại");
			repone.setData(isSuccess);
			String dataJson = gson.toJson(repone);

			// Trả dữ liệu JSON
			PrintWriter out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");

			out.print(dataJson);
		}
	}
}
