package cn.edu.xmu.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.RoleTablePowerOneDao;
import cn.edu.xmu.daoimpl.RoleTablePowerOneDaoImpl;
import cn.edu.xmu.entity.RoleTablePowerOne;

/**
 * Servlet implementation class AddRoleFillServlet
 */
@WebServlet("/AddRoleFillServlet")
public class AddRoleFillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRoleFillServlet() {
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
		String tableid = request.getParameter("select1");
		String roleid = request.getParameter("roleid");
		Map<String, String> params = new HashMap<String, String>();
		params.put("rr_roleid", roleid);
		params.put("rr_tableid", tableid);
		RoleTablePowerOneDao roleFillDao = new RoleTablePowerOneDaoImpl();
		int flag = roleFillDao.addRecord("rr_rolefill", params);
		if(flag==1){
			request.getRequestDispatcher("/admin/success.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/admin/fail.jsp").forward(request, response);
		}
	}

}
