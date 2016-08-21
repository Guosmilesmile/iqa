package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.DevAgencyTrainInfoDao;
import cn.edu.xmu.daoimpl.DevAgencyTrainInfoDaoImpl;


/**
 * 附表3-5-1-1教师教学发展机构培训情况（学年）
 * @author yue
 *
 */
@WebServlet("/Sec_DeleteDevAgencyTrainInfoServlet")
public class Sec_DeleteDevAgencyTrainInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteDevAgencyTrainInfoServlet() {
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
		String dati_id = request.getParameter("datiids");
		
		String datiid[] = dati_id.split(",");
		
		DevAgencyTrainInfoDao datiDao = new DevAgencyTrainInfoDaoImpl();
		boolean result = false;
		try {
			result = datiDao.batchDelete(datiid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
	}
}
