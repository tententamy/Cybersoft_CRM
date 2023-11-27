package crm_project_02.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import crm_project_02.entity.Job;
import crm_project_02.entity.Project;
import crm_project_02.entity.Status;
import crm_project_02.entity.User;
import crm_project_02.service.JobService;
import crm_project_02.service.ProjectService;
import crm_project_02.service.StatusService;
import crm_project_02.service.UserService;

@WebServlet(name = "taskController", urlPatterns = { "/task", "/task-add", "/task-edit" })
public class TaskController extends HttpServlet {

	private StatusService statusS = new StatusService();
	private JobService jobS = new JobService();
	private UserService userS = new UserService();
	private ProjectService projectS = new ProjectService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();

		if (path.equals("/task")) {
			List<Job> listJob = new ArrayList<>();
			try {
				listJob = jobS.getAllJob();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("listJob", listJob);
			req.getRequestDispatcher("task.jsp").forward(req, resp);
		} else if (path.equals("/task-add")) {
			List<User> listUser = new ArrayList<>();
			List<Project> listProject = new ArrayList<>();

			try {
				listUser = userS.getAllUsers();
				listProject = projectS.getAllProject();
			} catch (Exception e) {
				// TODO: handle exception
			}
			req.setAttribute("listUser", listUser);
			req.setAttribute("listProject", listProject);
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
		} else if (path.equals("/task-edit")) {
			List<Status> list = new ArrayList<>();
			list = statusS.getAllStatus();
			int id = Integer.parseInt(req.getParameter("id_job"));
			Job job = jobS.searchJob(id);
			req.setAttribute("job", job);
			req.setAttribute("listStatus", list);
			req.getRequestDispatcher("task-edit.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getServletPath();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("UTF-8");
		if (path.equals("/task-add")) {
			String taskName = req.getParameter("taskName");
			int id_project = Integer.parseInt(req.getParameter("projectName"));
			int id_user = Integer.parseInt(req.getParameter("userName"));
			String dateS = req.getParameter("startDate");
			String dateE = req.getParameter("endDate");
			boolean isSuccess = false;
			Date startDate = null;
			Date endDate = null;
			int id_job = 0;

			if (taskName.isEmpty()) {
				req.setAttribute("taskError", "phải có tên công việc");
			}

			if (dateS.isEmpty()) {
				req.setAttribute("errorDateS", "Định dạng ngày không đúng");
			} else {
				startDate = Date.valueOf(dateS);
			}

			if (dateE.isEmpty()) {
				req.setAttribute("errorDateE", "Định dạng ngày không đúng");
			} else {
				endDate = Date.valueOf(dateE);
			}

			if (taskName.isEmpty() || dateS.isEmpty() || dateE.isEmpty()) {
				req.setAttribute("error", "Vui lòng nhập lại form");
			} else if (jobS.checkJob(taskName, id_user, id_project)) {
				req.setAttribute("errorJob", "Không thể trao cùng 1 việc 2 lần cho 1 người trong cùng 1 dự án");
			} else if (startDate.after(endDate)) {
				req.setAttribute("errSetDateTime", "Ngày kết thúc phải sau ngày bắt đầu");
			} else {
				try {
					jobS.createNewJob(id_project, taskName, id_user, id_job, startDate, endDate);
				} catch (SQLException e) {

				}
			}

			List<User> listUser = new ArrayList<>();
			List<Project> listProject = new ArrayList<>();

			try {
				listUser = userS.getAllUsers();
				listProject = projectS.getAllProject();
			} catch (Exception e) {
				// TODO: handle exception
			}
			req.setAttribute("listUser", listUser);
			req.setAttribute("listProject", listProject);
			req.getRequestDispatcher("task-add.jsp").forward(req, resp);
		} else if (path.equals("/task-edit")) {
			List<Status> list = new ArrayList<>();
			list = statusS.getAllStatus();
			int id_job = Integer.parseInt(req.getParameter("id_job"));
			int id_status = Integer.parseInt(req.getParameter("newStatus"));

			boolean isSuccess = jobS.updateJob(id_job, id_status);

			if (isSuccess) {
				req.setAttribute("isSuccess", "cập nhật thành công");
			}
			Job job = jobS.searchJob(id_job);
			req.setAttribute("job", job);
			req.setAttribute("listStatus", list);
			req.getRequestDispatcher("task-edit.jsp").forward(req, resp);
		}

	}
}
