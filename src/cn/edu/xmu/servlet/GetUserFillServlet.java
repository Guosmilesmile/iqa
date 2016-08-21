package cn.edu.xmu.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.UserFillDao;
import cn.edu.xmu.daoimpl.UserFillDaoImpl;
import cn.edu.xmu.entity.UserFill;
import cn.edu.xmu.util.FastJsonTool;

/**
 * Servlet implementation class GetUserFillServlet
 */
@WebServlet("/GetUserFillServlet")
public class GetUserFillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserFillServlet() {
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
		String userid = request.getParameter("userid");
		UserFillDao userFillDao = new UserFillDaoImpl();
		List<UserFill> userFills =null;
		userFills =  userFillDao.getUserFills(userid);
		List<UserFill> userFills2 =new ArrayList<UserFill>();
		int count = 0;
		
		for(int i=0;i<userFills.size();i++){
			UserFill userFill = userFills.get(i);
			int sonid = userFill.getSonid();
			userFills2.add(count, userFill);
			count++;
			for(int j=i+1;j<userFills.size();j++){
				UserFill userFill2  = userFills.get(j);
				if(sonid==userFill2.getTableid()){
					userFills2.add(count, userFill2);
					userFills.remove(userFill2);
					count++;
				}
			}
		}
		System.out.println(FastJsonTool.createJsonString(userFills2));
		response.getWriter().print(FastJsonTool.createJsonString(userFills2));
	}
}
