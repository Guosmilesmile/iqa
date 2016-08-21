package cn.edu.xmu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.TableList;
import cn.edu.xmu.util.FastJsonTool;

/**
 * Servlet implementation class GetWatchTableServlet
 */
@WebServlet("/GetWatchTableServlet")
public class GetWatchTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetWatchTableServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		
		
		String userid = request.getParameter("userid");
		TableListDao tableListDao = new TableListDaoImpl();
		List<TableList> tableLists = tableListDao.getWatchTable(userid);
		response.getWriter().print(FastJsonTool.createJsonString(tableLists));
		System.out.println(FastJsonTool.createJsonString(tableLists));
	}

}
