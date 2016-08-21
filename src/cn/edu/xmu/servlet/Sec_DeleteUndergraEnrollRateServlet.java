package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.UndergraEnrollRateDao;
import cn.edu.xmu.daoimpl.UndergraEnrollRateDaoImpl;

/**
 * 附表6-1-5-3本科生招生志愿满足率（时点）
 * @author yue
 *
 */
@WebServlet("/Sec_DeleteUndergraEnrollRateServlet")
public class Sec_DeleteUndergraEnrollRateServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteUndergraEnrollRateServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String uer_id = request.getParameter("uerids");
		
		String uerid[] = uer_id.split(",");
		
		UndergraEnrollRateDao uerDao = new UndergraEnrollRateDaoImpl();
		boolean result = false;
		try {
			result = uerDao.batchDelete(uerid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
	}
}
