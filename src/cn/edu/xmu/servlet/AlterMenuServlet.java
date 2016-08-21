package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.service.MenuService;
import cn.edu.xmu.service.SubMenuService;
import cn.edu.xmu.serviceimpl.MenuServiceImpl;
import cn.edu.xmu.serviceimpl.SubMenuServiceImpl;
import cn.edu.xmu.table.MenuTable;
import cn.edu.xmu.table.SubMenuTable;

@SuppressWarnings("serial")
@WebServlet("/AlterMenu")
public class AlterMenuServlet extends HttpServlet {
	private MenuService menuService = new MenuServiceImpl();
	private SubMenuService subMenuService = new SubMenuServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");

		PrintWriter out = response.getWriter();

		int id = Integer.parseInt(request.getParameter("menuId"));
		String ch_name = new String(request.getParameter("ch_name").getBytes(
				"iso8859-1"), "utf-8");
		String en_name = request.getParameter("en_name");
		String type = request.getParameter("type");
		if (type.equals("main")) {
			Map<String, String> valueMap = new HashMap<String, String>();
			valueMap.put(MenuTable.MB_CH_NAME, ch_name);
			valueMap.put(MenuTable.MB_EN_NAME, en_name);
			menuService.alterMenuRecord(valueMap, id);
			out.print("ok");
		} else if (type.equals("sub")) {
			Map<String, String> valueMap = new HashMap<String, String>();
			valueMap.put(SubMenuTable.SMB_CH_NAME, ch_name);
			valueMap.put(SubMenuTable.SMB_EN_NAME, en_name);
			subMenuService.alterSubMenu(valueMap, id);
			out.print("ok");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}

}
