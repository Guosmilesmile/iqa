package cn.edu.xmu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;

import cn.edu.xmu.entity.Menu;
import cn.edu.xmu.entity.SubMenu;
import cn.edu.xmu.enums.MenuType;
import cn.edu.xmu.service.TableService;
import cn.edu.xmu.serviceimpl.TableServiceImpl;

@SuppressWarnings("serial")
@WebServlet("/ReadTableServlet")
public class ReadTableServlet extends HttpServlet{
	private TableService tableService = new TableServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		// PrintWriter out = response.getWriter();

		String tableId = request.getParameter("tableId");
		String tableName = tableService.getTableNameById(tableId);
		
		JSONArray jsonArray = new JSONArray();
	}
}
