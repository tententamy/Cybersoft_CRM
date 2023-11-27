package crm_project_02.controller;

import java.io.IOException;
import java.net.URLDecoder;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "demoCookieController", urlPatterns = {"/demo-cookie"})
public class DemoCookieController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// lấy giá trị đã trữ trong  cookies
		Cookie[] arrayCookie = req.getCookies();
		for(Cookie cookie : arrayCookie) {
			if(cookie.equals("hoten")) {
				String value = cookie.getValue();
				System.out.println("Kiểm tra MAN " + URLDecoder.decode(value,"UTF-8"));
			}
		}
	}
	
}
