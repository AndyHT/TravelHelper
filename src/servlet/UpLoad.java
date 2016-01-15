package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.JsonObject;

import entity.Picture;

/**
 * Servlet implementation class UpLoad
 */
@WebServlet(name = "UpLoad.do", urlPatterns = { "/UpLoad.do" })
public class UpLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpLoad() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type");
		response.setHeader("Access-Control-Max-Age", "86400");

		/**
		 * �������������ֶΣ� ��������: content Ŀ�ĵ�: destination �Ƽ�����: type �Ƽ�ʱ��: time ͼƬ:
		 * picture ����: title
		 */

		String fileName = null;
		// Ϊ�������ṩ������Ϣ
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// �����������ʵ��
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// ��ʼ����
		sfu.setFileSizeMax(1024 * 400);

		// String picture = request.getParameter("picture");
		String picture = "http://10.0.1.32:8088/travel_helper/img/" + fileName;

		// ÿ�����������ݻ��װ��һ����Ӧ��FileItem������
		try {
			List<FileItem> items = sfu.parseRequest(request);
			// ���ֱ���
			for (int i = 0; i < items.size(); i++) {
				FileItem item = items.get(i);
				// isFormFieldΪtrue����ʾ�ⲻ���ļ��ϴ�����
				if (!item.isFormField()) {
					ServletContext sctx = getServletContext();
					// ��ô���ļ�������·��
					// upload�µ�ĳ���ļ��� �õ���ǰ���ߵ��û� �ҵ���Ӧ���ļ���

					// String path = sctx.getRealPath();
					String path = "E:\\eclipse\\travel_helper\\WebContent\\img";
					// System.out.println(path);
					// ����ļ���
					fileName = item.getName();
					// System.out.println(fileName);
					// �÷�����ĳЩƽ̨(����ϵͳ),�᷵��·��+�ļ���
					fileName = fileName
							.substring(fileName.lastIndexOf("/") + 1);
					File file = new File(path + "\\" + fileName);
					if (!file.exists()) {
						item.write(file);
						response.sendRedirect("push.jsp");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
