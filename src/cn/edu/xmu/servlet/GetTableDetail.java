package cn.edu.xmu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import cn.edu.xmu.dao.UserFillDao;
import cn.edu.xmu.daoimpl.UserFillDaoImpl;
import cn.edu.xmu.entity.UserFill;
import cn.edu.xmu.util.FastJsonTool;

/**
 * Servlet implementation class GetTableDetail
 */
@WebServlet("/gettabledetail")
public class GetTableDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTableDetail() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		String tableid = request.getParameter("tableid");
		UserFillDao userFillDao = new UserFillDaoImpl();
		List<UserFill> userFills = userFillDao.getTableDetail(tableid);
		
		System.out.println(FastJsonTool.createJsonString(userFills));
		response.getWriter().print(FastJsonTool.createJsonString(userFills));;
	}

}
