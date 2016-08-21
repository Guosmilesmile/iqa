package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.UnfinishedFormDao;
import cn.edu.xmu.daoimpl.UnfinishedFormDaoImpl;


/**
 * 未填表格说明
 * @author chunfeng
 *
 */

@WebServlet("/Sec_DeleteUnfinishedFormServlet")
public class Sec_DeleteUnfinishedFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteUnfinishedFormServlet() {
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
		PrintWriter out = response.getWriter();
		String uf_id = request.getParameter("ufids");
		
		if(uf_id == null ) return;
		String ufid[] = uf_id.split(",");
		UnfinishedFormDao unfinishedFormDao = new UnfinishedFormDaoImpl();
		boolean result = false;
		try {
			result = unfinishedFormDao.batchDelete(ufid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
		
	}

}

