package crm_project_02.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.result.SqlDateValueFactory;

import crm_project_02.entity.Project;
import crm_project_02.service.ProjectService;

@WebServlet(name = "groupworkController", urlPatterns = { "/groupwork-add", "/groupwork", "/groupwork-edit" })
public class GroupworkController extends HttpServlet {

	private ProjectService projectS = new ProjectService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		if (path.equals("/groupwork")) {
			List<Project> list = new ArrayList<>();
			try {
				list = projectS.getAllProject();
			} catch (Exception e) {
				// TODO: handle exception
			}
			req.setAttribute("listProject", list);
			req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
		} else if (path.equals("/groupwork-add")) {
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
		} else if (path.equals("/groupwork-edit")) {
			int id_project = Integer.parseInt(req.getParameter("id_project"));
			Project project = projectS.getProjectById(id_project);
			req.setAttribute("project", project);
			req.getRequestDispatcher("groupwork-edit.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("UTF-8");
		String name = req.getParameter("GWName");
		String dateS = req.getParameter("DateStart");
		String dateE = req.getParameter("DateEnd");
		boolean isSuccess = false;
		Date dateStart = null;
		Date dateEnd = null;

		if (path.equals("/groupwork-add")) {

			if (name.isEmpty()) {
				req.setAttribute("errorName", "Bạn chưa nhập tên dự án");
			}

			if (dateS.isEmpty()) {
				req.setAttribute("errorDateS", "Định dạng ngày không đúng");
			} else {
				dateStart = Date.valueOf(dateS);
			}

			if (dateE.isEmpty()) {
				req.setAttribute("errorDateE", "Định dạng ngày không đúng");
			} else {
				dateEnd = Date.valueOf(dateE);
			}

			if (name.isEmpty() || dateS.isEmpty() || dateE.isEmpty()) {
				req.setAttribute("error", "Vui lòng nhập lại form");
			} else if (dateStart.after(dateEnd)) {
				req.setAttribute("errSetDateTime", "Ngày kết thúc phải sau ngày bắt đầu");
			} else {
				isSuccess = projectS.addProject(name, dateStart, dateEnd);
			}
			req.setAttribute("isSuccess", isSuccess);
			req.getRequestDispatcher("groupwork-add.jsp").forward(req, resp);
		} else if (path.equals("/groupwork-edit")) {
			if (name.isEmpty()) {
				req.setAttribute("errorName", "Bạn chưa nhập tên dự án");
			}

			if (dateS.isEmpty()) {
				req.setAttribute("errorDateS", "Định dạng ngày không đúng");
			} else {
				dateStart = Date.valueOf(dateS);
			}

			if (dateE.isEmpty()) {
				req.setAttribute("errorDateE", "Định dạng ngày không đúng");
			} else {
				dateEnd = Date.valueOf(dateE);
			}

			if (name.isEmpty() || dateS.isEmpty() || dateE.isEmpty()) {
				req.setAttribute("error", "Vui lòng nhập lại form");
			} else if (dateStart.after(dateEnd)) {
				req.setAttribute("errSetDateTime", "Ngày kết thúc phải sau ngày bắt đầu");
			} else {
				int id_project = Integer.parseInt(req.getParameter("id_project"));
				isSuccess = projectS.updateProject(name, dateStart, dateEnd, id_project);
				Project project = projectS.getProjectById(id_project);
				req.setAttribute("project", project);

			}
			req.setAttribute("isSuccess", isSuccess);
			req.getRequestDispatcher("groupwork-edit.jsp").forward(req, resp);
		}

	}
}
