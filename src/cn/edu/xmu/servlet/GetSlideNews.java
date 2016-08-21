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
import cn.edu.xmu.entity.SlideNews;
import cn.edu.xmu.table.NewsTable;
import cn.edu.xmu.table.SlideNewsTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * Servlet implementation class GetSlideImage
 */
/**
 * 
 * @author Lee 获取滑动图片的显示
 *
 */
@WebServlet("/GetSlideImage")
public class GetSlideNews extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetSlideNews() {
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

		Map<String, String> queryParams = new HashMap<String, String>();
		SlideNewsDao slidenewsDao = new SlideNewsDaoImpl();
		GridDataModel<SlideNews> model = new GridDataModel<SlideNews>();

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
			sortStr = SlideNewsTable.SN_ID;
		// orderStr排序的规则
		String orderStr = request.getParameter("order");
		if (null == orderStr)
			orderStr = "desc";
		// 相关的条件限制

		String n_subclass = request.getParameter("n_subclass");
		String nameStr = null;
		String pagination = request.getParameter("pagination");

		if (null == pagination)
			pagination = "true";

		/* 控制中英文版本 */
		String version = request.getParameter("version");
		System.out.println("version" + version);
		String n_version = "中文";
		if (version != null) {
			if (version.equals("en")) {
				n_version = "English";
			}
		}
		queryParams.put(SlideNewsTable.SN_VERSION, n_version);

		int total = slidenewsDao.getSlideNewsCount(queryParams);
		int page = Integer.parseInt(pageStr);
		int rows = Integer.parseInt(rowsStr);
		int start = (page - 1) * rows;
		int end = rows;
		int pages = total % rows == 0 ? total / rows : total / rows + 1;
		end = end > total ? total : end;
		model.setTotal(total);
		model.setRows(slidenewsDao.findForPage(start, end, sortStr, orderStr,
				queryParams));
		request.setAttribute("slidenewslist", model);
		if (pagination.equals("true")) {
			if (n_version.equals("English")) {
				request.getRequestDispatcher("./en/more_news.jsp").forward(
						request, response);
			} else {
				request.getRequestDispatcher("./ch/more_news.jsp").forward(
						request, response);
			}
			return;
		} else {
			out.print(FastJsonTool.createJsonString(model.getRows()));
			System.out.println(FastJsonTool.createJsonString(model.getRows()));
		}
	}

}
