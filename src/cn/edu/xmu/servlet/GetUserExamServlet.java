package cn.edu.xmu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hdf.extractor.data.DOP;

import cn.edu.xmu.dao.UserExamDao;
import cn.edu.xmu.dao.UserFillDao;
import cn.edu.xmu.daoimpl.UserExamDaoImpl;
import cn.edu.xmu.daoimpl.UserFillDaoImpl;
import cn.edu.xmu.entity.UserExam;
import cn.edu.xmu.entity.UserFill;
import cn.edu.xmu.util.FastJsonTool;

/**
 * Servlet implementation class GetUserExamServlet
 */
@WebServlet("/GetUserExamServlet")
public class GetUserExamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserExamServlet() {
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
		String userid = request.getParameter("userid");
		String college = request.getParameter("college");
		System.out.println("===================college="+college);
		/*UserExamDao userExamDao = new UserExamDaoImpl();
		List<UserExam> userExams = userExamDao.getUserExams(userid);*/
		UserFillDao userFillDao = new UserFillDaoImpl();
		List<UserFill> userFills = userFillDao.getUserExam(userid,college);
		
		/*//获取当前审核用户所属的学院
		String college = "";
		Cookie[] cookies = request.getCookies();  
		if (cookies != null && cookies.length > 0) {  
		    for (Cookie cookie : cookies) {
		    	if(cookie.getName().equals("deptxi")){
		    		college = cookie.getValue();
		    		break;
		    	}
		    }  
		}  
		if (college.contains("学院")) {//学院级别的审核
			for (UserFill userFill:userFills) {
				int fillRoleId = userFill.getRoleid();
				根据fillRoleId获取填报人所属学院
				
				//首先根据角色获取用户列表，再通过用户列表中的用户获取学院信息
				
				
				//根据userId获取填报人所属学院
			}
		}
		*/
		System.out.println(FastJsonTool.createJsonString(userFills));
		response.getWriter().print(FastJsonTool.createJsonString(userFills));;
	}

}
