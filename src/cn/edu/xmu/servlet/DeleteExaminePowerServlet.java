package cn.edu.xmu.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.RoleExamineDao;
import cn.edu.xmu.daoimpl.RoleExamineDaoImpl;

/**
 * Servlet implementation class DeleteExaminePowerServlet
 */
@WebServlet("/DeleteExaminePowerServlet")
public class DeleteExaminePowerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteExaminePowerServlet() {
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
		String examineid = request.getParameter("deleexamineid");
		RoleExamineDao roleExamineDao = new RoleExamineDaoImpl();
		Map<String, String> params = new HashMap<String, String>();
		params.put("re_id", examineid);
		int flag = roleExamineDao.deleteRecord("re_roleexamine", params);
		if(flag==1){
			request.getRequestDispatcher("/admin/success.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/admin/fail.jsp").forward(request, response);
		}
	}

}
