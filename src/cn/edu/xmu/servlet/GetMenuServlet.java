package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.entity.Menu;
import cn.edu.xmu.service.MenuService;
import cn.edu.xmu.serviceimpl.MenuServiceImpl;
import cn.edu.xmu.table.MenuTable;
import cn.edu.xmu.util.FastJsonTool;

@SuppressWarnings("serial")
@WebServlet("/GetMenuServlet")
public class GetMenuServlet extends HttpServlet {
	private MenuService menuservice = new MenuServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();

		Map<String, String> queryParams = new HashMap<String, String>();
		// 菜单栏目设置为显示
		queryParams.put(MenuTable.MB_SHOW, "1");

		List<Menu> menuList = menuservice.getAllMenu(queryParams);
		String headerMenu = (String) request.getParameter("headerMenu");
		request.setAttribute("menuList", menuList);
		System.out.println("#################header:" + headerMenu);
		request.setAttribute("menuNum", menuList.size());
		if (headerMenu != null && headerMenu.equals("1")) {// 前台获取栏目信息

			out.print(FastJsonTool.createJsonString(menuList));
		} else {
			request.getRequestDispatcher("./admin/editMenu.jsp").forward(
					request, response);
		}
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
		doGet(request, response);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

	}

}
