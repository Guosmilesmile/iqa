package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.NewsDao;
import cn.edu.xmu.daoimpl.NewsDaoImpl;
import cn.edu.xmu.table.NewsTable;

/**
 * Servlet implementation class DeleteNews
 */
@WebServlet("/DeleteNews")
public class DeleteNews extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteNews() {
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
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		String newsid = request.getParameter("newsid");

		if (null != newsid) {
			NewsDao newsDao = new NewsDaoImpl();
			Map<String, String> params = new HashMap<String, String>();
			params.put(NewsTable.N_ID, newsid);
			int result = newsDao.deleteRecord(NewsTable.TABLE_NAME, params);
			out.print(result);
			System.out.println("删除新闻纪录的结果" + result);
		} else {
			out.print("-1");
			System.out.println("删除新闻纪录的ID为空");
		}

	}

}
