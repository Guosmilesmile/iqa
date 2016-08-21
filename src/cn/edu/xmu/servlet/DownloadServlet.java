package cn.edu.xmu.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DownloadServlet
 */
@WebServlet("/DownloadServlet")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String path = getServletContext().getRealPath("/") + "/uploadModel/";
		String filename = request.getParameter("filename");
		filename=URLDecoder.decode(filename,"utf-8");
		System.out.println(filename);
		File file = new File(path + filename);
		System.out.println(path + filename);
		if (file.exists()) {
			// 设置相应类型application/octet-stream
			response.setContentType("application/x-msdownload");
			// 设置头信息
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ java.net.URLEncoder.encode(filename, "UTF-8") + "\"");
			InputStream inputStream = new FileInputStream(file);
			ServletOutputStream ouputStream = response.getOutputStream();
			byte b[] = new byte[1024];
			int n;
			while ((n = inputStream.read(b)) != -1) {
				ouputStream.write(b, 0, n);
			}
			// 关闭流、释放资源
			ouputStream.close();
			inputStream.close();

		} else {
			request.setAttribute("successResult", "删除失败");
			request.getRequestDispatcher("admin/success.jsp").forward(
					request, response);
		}
	}
}
