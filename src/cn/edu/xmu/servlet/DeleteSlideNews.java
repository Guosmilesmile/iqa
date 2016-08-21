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

import cn.edu.xmu.dao.SlideNewsDao;
import cn.edu.xmu.daoimpl.SlideNewsDaoImpl;
import cn.edu.xmu.table.SlideNewsTable;

/**
 * Servlet implementation class DeleteSlideNews
 */
@WebServlet("/DeleteSlideNews")
public class DeleteSlideNews extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteSlideNews() {
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
		String slidenewsid = request.getParameter("slidenewsid");

		System.out.println("sssssss");

		if (null != slidenewsid) {
			SlideNewsDao slidenewsDao = new SlideNewsDaoImpl();
			Map<String, String> params = new HashMap<String, String>();
			params.put(SlideNewsTable.SN_ID, slidenewsid);
			int result = slidenewsDao.deleteRecord(SlideNewsTable.TABLE_NAME,
					params);
			out.print(result);
			System.out.println("删除滑动新闻纪录的结果" + result);
		} else {
			out.print("-1");
			System.out.println("删除滑动新闻纪录的ID为空");
		}
	}

}
