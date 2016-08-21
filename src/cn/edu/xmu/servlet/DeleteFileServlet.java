package cn.edu.xmu.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteFileServlet
 */
@WebServlet("/DeleteFileServlet")
public class DeleteFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String path = getServletContext().getRealPath("/") + "/uploadModel/";
		String filename = request.getParameter("filename");
		//filename = new String(filename.getBytes("GB2312"),"utf-8");
		//filename=URLDecoder.decode(filename,"utf-8");
		filename = new String(filename.getBytes("ISO-8859-1"),"utf-8");
		System.out.println(filename);
		File file = new File(path + filename);
		System.out.println(path + filename);
		if (file.exists()){
			file.delete();
			request.setAttribute("successResult", "删除成功");
			request.getRequestDispatcher("admin/success.jsp").forward(
					request, response);
		}else {
			request.getRequestDispatcher("admin/fail.jsp").forward(
					request, response);
		}
	}

}
