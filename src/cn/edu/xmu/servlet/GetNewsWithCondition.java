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
import cn.edu.xmu.entity.News;
import cn.edu.xmu.table.NewsTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

@WebServlet("/GetNewsWithCondition")
public class GetNewsWithCondition extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetNewsWithCondition() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		Map queryParams = new HashMap();

		NewsDao newsDao = new NewsDaoImpl();
		GridDataModel<News> model = new GridDataModel<News>();

		// pageStr页码
		String pageStr = request.getParameter("page");
		if (null == pageStr)
			pageStr = "1";
		// rowsStr页显示多少条纪录
		String rowsStr = request.getParameter("rows");
		if (null == rowsStr)
			rowsStr = "10";
		// sortStr排序的字段
		String sortStr = request.getParameter("sort");
		if (null == sortStr)
			sortStr = NewsTable.N_PUBLISHTIME;
		// orderStr排序的规则
		String orderStr = request.getParameter("order");
		if (null == orderStr)
			orderStr = "desc";
		// 相关的条件限制
		
		String pagination = request.getParameter("pagination");
		if (null == pagination)
			pagination = "true";

		String key = request.getParameter("key");
		if (null != pagination)
			queryParams.put(NewsTable.N_TITLES, key);
			
		/* 控制中英文版本 */
		String version = request.getParameter("version");
		System.out.println("version" + version);
		String n_version = "中文";
		if (version != null) {
			if (version.equals("en")) {
				n_version = "English";
			}
		}
		queryParams.put(NewsTable.N_VERSION, n_version);

		int total = newsDao.getNewsCountCH2Query(queryParams);
		int page = Integer.parseInt(pageStr);
		int rows = Integer.parseInt(rowsStr);
		int start = (page - 1) * rows;
		int end = rows;
		int pages = total % rows == 0 ? total / rows : total / rows + 1;
		end = end > total ? total : end;
		model.setTotal(total);
		model.setRows(newsDao.findForPageCH2Query(start, end, sortStr, orderStr, queryParams));
		request.setAttribute("newslist", model);
		request.setAttribute("currentPage", page);
		request.setAttribute("pages", pages);
		model.setTotal(total);
		out.print(FastJsonTool.createJsonString(model));
		System.out.println(FastJsonTool.createJsonString(model));
	}
}
