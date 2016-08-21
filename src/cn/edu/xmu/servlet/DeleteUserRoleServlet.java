package cn.edu.xmu.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.UserRoleDao;
import cn.edu.xmu.daoimpl.UserRoleDaoImpl;
import cn.edu.xmu.entity.UserRole;
import cn.edu.xmu.table.UserRoleTable;

/**
 * Servlet implementation class DeleteUserRoleServlet
 */
@WebServlet("/DeleteUserRoleServlet")
public class DeleteUserRoleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteUserRoleServlet() {
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
		String id = request.getParameter("deleid");
		UserRoleDao userRoleDao = new UserRoleDaoImpl();
		Map<String, String> params = new HashMap<String, String>();
		params.put("ru_id", id);
		int flag = userRoleDao.deleteRecord(UserRoleTable.TABLE_NAME, params);
		if(flag==1){
			request.getRequestDispatcher("/admin/success.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/admin/fail.jsp").forward(request, response);
		}
	}

}
