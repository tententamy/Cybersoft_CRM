package crm_project_02.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import crm_project_02.payload.respone.BaseRespone;
import crm_project_02.service.JobService;

@WebServlet(name = "apiJobController", urlPatterns = { "/api/task/delete" })
public class ApiJobController extends HttpServlet {

	private JobService jobS = new JobService();
	private Gson gson = new Gson();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id_job = Integer.parseInt(req.getParameter("id"));
		boolean isSuccess = jobS.deleteJob(id_job);

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
