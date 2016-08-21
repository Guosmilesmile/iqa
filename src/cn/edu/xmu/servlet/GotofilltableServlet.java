package cn.edu.xmu.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * Servlet implementation class GotofilltableServlet
 */
@WebServlet("/GotofilltableServlet")
public class GotofilltableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GotofilltableServlet() {
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
		String tableid = request.getParameter("tableid");
		System.out.println("filltableid------------"+tableid);
		Properties prop = new Properties();
		prop.load(JdbcUtils_DBCP.class.getClassLoader().getResourceAsStream(
				"TableList.properties"));
		String tablename ;
		tablename = prop.getProperty(tableid);
		Cookie cookie = new Cookie("tableid",tableid);
		response.addCookie(cookie);
		TableListDao tableListDao = new TableListDaoImpl();
		Date date = tableListDao.getTableDate(tableid);
		if (date != null) {
			cookie = new Cookie("deadline",date.toString());
		}
		else {
			cookie = new Cookie("deadline","");
		}
		response.addCookie(cookie);
		System.out.println("*********"+tablename);
		request.getRequestDispatcher("admin/manage"+tablename).forward(request, response);
	}
}
