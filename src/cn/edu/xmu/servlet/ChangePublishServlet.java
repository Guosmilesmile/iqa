package cn.edu.xmu.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;

/**
 * Servlet implementation class ChangePublishServlet
 */
@WebServlet("/ChangePublishServlet")
public class ChangePublishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePublishServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
		String flag = request.getParameter("flag");
		String tableid = request.getParameter("tableid");
		String datetime = "";
		java.sql.Date sqlDate = null;
		if ("2".equals(flag)|| "0".equals(flag)) {
			datetime = request.getParameter("datetime");
			java.util.Date date = new java.util.Date(datetime);
			sqlDate= new java.sql.Date(date.getTime());
		}
		TableListDao tableListDao = new TableListDaoImpl();
		if ("2".equals(flag) || "3".equals(flag)) {
			tableListDao.changePublishAll(flag, tableid, sqlDate);
		} else if ("4".equals(flag)){
			tableListDao.changePublishPage(flag, tableid);
		}else {
			tableListDao.changePublish(flag, tableid, sqlDate);
		}
		/* boolean b = tableListDao.changePublish(flag, tableid); */
		request.getRequestDispatcher("/admin/publishtable.jsp").forward(
				request, response);
	}

}
