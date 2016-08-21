package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import cn.edu.xmu.dao.RoleTablePowerDao;
import cn.edu.xmu.dao.UserFillDao;
import cn.edu.xmu.daoimpl.RoleTablePowerDaoImpl;
import cn.edu.xmu.daoimpl.UserFillDaoImpl;
import cn.edu.xmu.entity.RoleTablePower;

/**
 * Servlet implementation class AddRoleTablePowersServlet
 */
@WebServlet("/AddRoleTablePowersServlet")
public class AddRoleTablePowersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRoleTablePowersServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String roleid = request.getParameter("roleid");
		String watchs = request.getParameter("watch");
		String fills = request.getParameter("fill");
		String examones = request.getParameter("examone");
		String examtwos = request.getParameter("examtwo");
		
		
		System.out.println("roleid---"+roleid);
		System.out.println("watch----"+watchs);
		System.out.println("fill---"+fills);
		System.out.println("examone---"+examones);
		System.out.println("examtwo----"+examtwos);
		
		
		RoleTablePowerDao roleTablePowerDao = new RoleTablePowerDaoImpl();
		roleTablePowerDao.AddRoleTablePowers(Integer.parseInt(roleid), watchs, fills, examones, examtwos);
		
		
		//RoleTablePowerDao roleTablePowerDao = new RoleTablePowerDaoImpl();
		//roleTablePowerDao.AddRoleTablePowers(Integer.parseInt(roleid), powers);
		//request.getRequestDispatcher("admin/tablepower.jsp").forward(request, response);
	}
}
