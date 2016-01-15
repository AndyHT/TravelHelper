package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

@WebServlet("/adminLogin")
public class adminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public adminLogin() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");
		PrintWriter out = response.getWriter();
		JsonObject obj = new JsonObject();
		String inputEmail = request.getParameter("inputEmail");
		String password = request.getParameter("inputPassword");
		
		if (null == inputEmail || "".equals(inputEmail)) {
			obj.addProperty("result", false);
		} else {
			if (!"3456789@qq.com".equals(inputEmail)) {
				obj.addProperty("result", false);
			} else {
				obj.addProperty("result", true);
			}
		}
		
		if (null == password || "".equals(password)) {
			obj.addProperty("result", false);
		} else {
			if (!"123456".equals(password)) {
				obj.addProperty("result", false);
			} else {
				obj.addProperty("result", true);
			}
		}

		out.println(obj.toString());
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
