package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.service.MenuService;
import cn.edu.xmu.service.SubMenuService;
import cn.edu.xmu.serviceimpl.MenuServiceImpl;
import cn.edu.xmu.serviceimpl.SubMenuServiceImpl;
import cn.edu.xmu.util.FastJsonTool;

@SuppressWarnings("serial")
@WebServlet("/DeleteMenu")
public class DeleteMenuServlet extends HttpServlet {
	private MenuService menuService = new MenuServiceImpl();
	private SubMenuService subMenuService = new SubMenuServiceImpl();

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("%delete%");
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("get");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();

		int id = Integer.parseInt(request.getParameter("menuId"));
		String type = request.getParameter("type");
		if (type.equals("main")) {
			menuService.deleteMenuById(id);
			out.print("ok");
		} else if (type.equals("sub")) {
			subMenuService.deleteSubMenuById(id);
			out.print("ok");
		}

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("post");

	}

}
