/*package cn.edu.xmu.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.UserReviewDao;
import cn.edu.xmu.entity.UserReview;
import cn.edu.xmu.util.FastJsonTool;

*//**
 * Servlet implementation class GetReviewByidServlet
 *//*
@WebServlet("/GetReviewByidServlet")
public class GetReviewByidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    *//**
     * @see HttpServlet#HttpServlet()
     *//*
    public GetReviewByidServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	*//**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 *//*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		String userid = request.getParameter("userid");
		UserReviewDao userReviewDao = new UserReviewDaoImpl();
		List<UserReview> lists = null;
		lists = userReviewDao.getUserReviews(userid);
		response.getWriter().print(FastJsonTool.createJsonString(lists));
	}

}
*/