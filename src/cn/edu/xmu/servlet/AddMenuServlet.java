package cn.edu.xmu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.MenuDao;
import cn.edu.xmu.daoimpl.MenuDaoImpl;
import cn.edu.xmu.entity.Menu;
import cn.edu.xmu.entity.SubMenu;
import cn.edu.xmu.enums.MenuType;
import cn.edu.xmu.service.MenuService;
import cn.edu.xmu.service.SubMenuService;
import cn.edu.xmu.serviceimpl.MenuServiceImpl;
import cn.edu.xmu.serviceimpl.SubMenuServiceImpl;

/**
 * 
 * @author Shijing Zhang
 *
 */
@SuppressWarnings("serial")
@WebServlet("/AddMenu")
public class AddMenuServlet extends HttpServlet {
	private MenuService menuService = new MenuServiceImpl();
	private SubMenuService subMenuService = new SubMenuServiceImpl();

	// private MenuDao menuDao = new MenuDaoImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		// PrintWriter out = response.getWriter();

		int type = Integer.parseInt(request.getParameter("type"));
		String belongMain = request.getParameter("belongMain");

		String ch_name = request.getParameter("mb_ch_name");
		String en_name = request.getParameter("mb_en_name");

		int result = 0;
		if (type == MenuType.MAIN.getTypeValue()) {// 主栏目
			/* 是否需要检查name的唯一性 */

			Menu menu = new Menu(ch_name, en_name);
			result = menuService.addMenuRecord(menu);
		} else {// 子栏目
				// 获取主栏目id
			int mainId = menuService.getIdMenuByName(belongMain);
			SubMenu subMenu = new SubMenu(mainId, ch_name, en_name);
			result = subMenuService.addSubMenu(subMenu);
		}
		/**
		 * 添加成功应该有信息提示
		 */
		if (result == 1) {
			response.sendRedirect("admin/addMenu.jsp");
		}
	}

}
