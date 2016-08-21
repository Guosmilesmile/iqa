package cn.edu.xmu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.TableList;
import cn.edu.xmu.util.FastJsonTool;

/**
 * Servlet implementation class GetTableListLikeServlet
 */
@WebServlet("/GetTableListLikeServlet")
public class GetTableListLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTableListLikeServlet() {
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
		String like = request.getParameter("like");
		if(like==null){
			like="";
		}
		TableListDao tableListDao = new TableListDaoImpl();
		List<TableList> tableLists = tableListDao.getTableListsLike(like);
		response.getWriter().print(FastJsonTool.createJsonString(tableLists));
	}

}
