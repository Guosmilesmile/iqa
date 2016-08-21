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

import cn.edu.xmu.dao.MenuDao;
import cn.edu.xmu.dao.NewsDao;
import cn.edu.xmu.daoimpl.MenuDaoImpl;
import cn.edu.xmu.daoimpl.NewsDaoImpl;
import cn.edu.xmu.entity.Menu;
import cn.edu.xmu.entity.News;
import cn.edu.xmu.table.NewsTable;
import cn.edu.xmu.util.FastJsonTool;
import cn.edu.xmu.util.GridDataModel;

/**
 * ch2获取新闻列表
 * 
 * @author xiaoping 2015-10-2
 * 
 */
@WebServlet("/GetCH2NewsList")
public class GetCH2NewsList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MenuDao menuDao = new MenuDaoImpl();

	public GetCH2NewsList() {
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

		String n_subclass = request.getParameter("n_subclass");
		String nameStr = null;
		String pagination = request.getParameter("pagination");
		String menuIdString = request.getParameter("menuId");
		int menuId = 0;
		Menu menu = null;
		String n_class = null;
		if (menuIdString != null && !menuIdString.equals("")) {
			menuId = Integer.parseInt(menuIdString);
		}
		if (menuId != 0) {
			menu = menuDao.getMenuById(menuId);
			n_class = menu.getMb_ch_name();
		}
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
		queryParams.put(NewsTable.N_VERSION, n_version);

		if (n_subclass != null && !"".equals(n_subclass)) {
			if (n_subclass.equals("5")) {
				nameStr = "概况";
			}
			if (n_subclass.equals("1")) {
				nameStr = "新闻";
			}
			if (n_subclass.equals("2")) {
				nameStr = "文件";
			}
			if (n_subclass.equals("3")) {
				nameStr = "报告";
			}
			if (n_subclass.equals("4")) {
				nameStr = "数据";
			}
			if (n_subclass.equals("13")) {
				nameStr = "组织保障";
			}
			if (n_subclass.equals("14")) {
				nameStr = "质量评估框架";
			}
			if (n_subclass.equals("15")) {
				nameStr = "教育质量系统框架";
			}
			if (n_subclass.equals("291")) {
				nameStr = "教学成果奖";
			}
			if (n_subclass.equals("292")) {
				nameStr = "研究论文";
			}
			if (n_subclass.equals("293")) {
				nameStr = "研究课题";
			}
			if (n_subclass.equals("294")) {
				nameStr = "项目评审";
			}
			if (n_subclass.equals("30")) {
				nameStr = "问卷";
			}
			if (n_subclass.equals("62")) {
				nameStr = "上级文件";
			}
			if (n_subclass.equals("63")) {
				nameStr = "厦门大学本科教学管理制度";
			}
			if (n_subclass.equals("64")) {
				nameStr = "厦门大学学生手册";
			}
			if (n_subclass.equals("65")) {
				nameStr = "厦门大学新生学业导航";
			}
			if (n_subclass.equals("66")) {
				nameStr = "厦门大学教师手册";
			}
			if (n_subclass.equals("67")) {
				nameStr = "自评报告";
			}
			if (n_subclass.equals("68")) {
				nameStr = "上级文件";
			}
			if (n_subclass.equals("69")) {
				nameStr = "通知公告";
			}if (n_subclass.equals("70")) {
				nameStr = "通知公告";
			}if (n_subclass.equals("71")) {
				nameStr = "热点新闻";
			}
			if (n_subclass.equals("72")) {
				nameStr = "图片新闻";
			}
			queryParams.put(NewsTable.N_SUB_CLASS, nameStr);
		}
		if (n_class != null) {
			queryParams.put(NewsTable.N_CLASS, n_class);
		}

		int total = newsDao.getNewsCount(queryParams);
		int page = Integer.parseInt(pageStr);
		int rows = Integer.parseInt(rowsStr);
		int start = (page - 1) * rows;
		int end = rows;
		int pages = total % rows == 0 ? total / rows : total / rows + 1;
		end = end > total ? total : end;
		model.setTotal(total);
		model.setRows(newsDao.findForPage(start, end, sortStr, orderStr, queryParams));
		request.setAttribute("newslist", model);
		request.setAttribute("currentPage", page);
		request.setAttribute("pages", pages);
		request.setAttribute("n_subclass", n_subclass);
		request.setAttribute("menuId", menuId);
		model.setTotal(total);
//		String redirect = "./ch/Index.jsp";
//		if (n_version.equals("English")) {
//			System.out.println("english====");
//			switch (menuId) {
//			case 35:
//				redirect = "./en/iqa_imform.jsp";
//				break;
//			case 36:
//				redirect = "./en/iqa_uniqa.jsp";
//				break;
//			default:
//				break;
//			}
//		} else {
//			switch (menuId) {
//			case 35:
//				redirect = "./ch2/iqa_imform.jsp";
//				break;
//			case 36:
//				redirect = "./ch2/iqa_uniqa.jsp";
//				break;
//			default:
//				break;
//			}
//		}
		out.print(FastJsonTool.createJsonString(model));
		System.out.println(FastJsonTool.createJsonString(model));
	}
}
