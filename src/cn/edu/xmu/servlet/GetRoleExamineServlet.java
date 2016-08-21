package cn.edu.xmu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.RoleExamineDao;
import cn.edu.xmu.dao.RoleTablePowerOneDao;
import cn.edu.xmu.daoimpl.RoleExamineDaoImpl;
import cn.edu.xmu.daoimpl.RoleTablePowerOneDaoImpl;
import cn.edu.xmu.entity.RoleExamine;
import cn.edu.xmu.entity.RoleTablePowerOne;
import cn.edu.xmu.util.FastJsonTool;

/**
 * Servlet implementation class GetRoleExamineServlet
 */
@WebServlet("/GetRoleExamineServlet")
public class GetRoleExamineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetRoleExamineServlet() {
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
		RoleExamineDao roleExamineDao = new RoleExamineDaoImpl();
		List<RoleExamine> roleExamines = roleExamineDao.getRoleExamines();
		response.getWriter().print(FastJsonTool.createJsonString(roleExamines));
	}

}
