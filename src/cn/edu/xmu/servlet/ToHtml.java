package cn.edu.xmu.servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class ToHtml extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// private static final String CONTENT_TYPE = "text/html; charset=UTF-8";

	// Initialize global variables
	public void init() throws ServletException {
	}

	// Process the HTTP Get request
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		service(request, response);
	}

	// Process the HTTP Post request
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public void destroy() {
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");

		String serve_url = this.getServletContext().getRealPath("/");
		System.out.println(serve_url);
		File file = new File(serve_url);// 获取某个目录下的所有文件名
		String test[];
		test = file.list();
		List<String> jspList = new ArrayList<String>();
		for (String f : test) {
			// 分隔
			String str[] = f.split("[.]");
			if (str.length == 2) {
				if (str[1].equals("jsp")) {
					jspList.add(str[0]);
				}
			}

		}

		// ================================
		String url = "";
		String name = "";
		ServletContext sc = getServletContext();
		for (int i = 0; i < jspList.size(); i++) {
			url = "/" + jspList.get(i) + ".jsp";
			name = serve_url + jspList.get(i) + ".html";
			System.out.println("file_name=" + jspList.get(i));
			System.out.println("name=" + name);
			System.out.println("url=" + url);

			RequestDispatcher rd = sc.getRequestDispatcher(url);
			final ByteArrayOutputStream os = new ByteArrayOutputStream();
			final ServletOutputStream stream = new ServletOutputStream() {
				public void write(byte[] data, int offset, int length) {
					os.write(data, offset, length);
				}

				public void write(int b) throws IOException {
					os.write(b);
				}
			};
			final PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));
			HttpServletResponse rep = new HttpServletResponseWrapper(response) {
				public ServletOutputStream getOutputStream() {
					return stream;
				}

				public PrintWriter getWriter() {
					return pw;
				}
			};
			pw.flush();
			FileOutputStream fos = new FileOutputStream(name);
			os.writeTo(fos);
			fos.close();
			os.close();
			PrintWriter out = response.getWriter();
			out.print("<p align=center><font size=3 color=red>首页生产成功</font></p>");
		}

	}
}
