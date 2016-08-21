package cn.edu.xmu.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.BaseDao;
import cn.edu.xmu.dao.MenuDao;
import cn.edu.xmu.daoimpl.BaseDaoImpl;
import cn.edu.xmu.daoimpl.MenuDaoImpl;
import cn.edu.xmu.entity.News;
import cn.edu.xmu.table.NewsTable;

/**
 * Servlet implementation class SqlTest
 */
@WebServlet("/SqlTest")
public class SqlTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SqlTest() {
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

		Map<String, String> queryParams1 = new HashMap<String, String>();
		Map<String, String> queryParams2 = new HashMap<String, String>();
		queryParams1.put(NewsTable.N_AUTHOR, "asdasdasd");
		queryParams2.put(NewsTable.N_TITLES, "sdlfjskld");
		queryParams2.put(NewsTable.N_CLASS, "0");

		MenuDao menuDao = new MenuDaoImpl();
		menuDao.addRecord(NewsTable.TABLE_NAME, queryParams1);

		// baseDao.addRecord(NewsTable.TABLE_NAME, queryParams);
		// baseDao.deleteRecord(NewsTable.TABLE_NAME, queryParams);
		// baseDao.updateRecord(NewsTable.TABLE_NAME, queryParams1,
		// queryParams2);
		// baseDao.findAll(NewsTable.TABLE_NAME, 0, 10, NewsTable.N_AUTHOR,
		// "desc", queryParams1, queryParams2);
	}

}
