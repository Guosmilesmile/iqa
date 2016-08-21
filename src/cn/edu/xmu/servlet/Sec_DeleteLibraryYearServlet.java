package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.LibraryYearDao;
import cn.edu.xmu.dao.NewBooksthatYearDao;
import cn.edu.xmu.daoimpl.LibraryYearDaoImpl;
import cn.edu.xmu.daoimpl.NewBooksthatYearDaoImpl;

@WebServlet("/Sec_DeleteLibraryYearServlet")
public class Sec_DeleteLibraryYearServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_DeleteLibraryYearServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();
		String ly_id = request.getParameter("lyids");
		
		String lyid[] = ly_id.split(",");
		
		LibraryYearDao lyDao = new LibraryYearDaoImpl();
		boolean result = false;
		try {
			result = lyDao.batchDelete(lyid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);

	}

}
