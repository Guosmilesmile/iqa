package cn.edu.xmu.servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.GetValueUtilDao;
import cn.edu.xmu.dao.RoleTablePowerDao;
import cn.edu.xmu.daoimpl.GetValueUtilDaoImpl;
import cn.edu.xmu.daoimpl.RoleTablePowerDaoImpl;
import cn.edu.xmu.daoimpl.RoleTablePowerOneDaoImpl;
import cn.edu.xmu.util.JdbcUtils_DBCP;

/**
 * Servlet implementation class GoToWatchTableServlet
 */
@WebServlet("/GoToWatchTableServlet")
public class GoToWatchTableServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoToWatchTableServlet() {
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
		String tableid = request.getParameter("tableid");
		String roleid = request.getParameter("roleid");
		String showtrue  = request.getParameter("showtrue");
		String reviewsituation = request.getParameter("reviewsituation");
		String fillsituation = request.getParameter("fillsituation");
		if(null==reviewsituation)
			reviewsituation="";
		if(null==fillsituation)
			fillsituation="";
		System.out.println("=======================fillsituation"+fillsituation);
		System.out.println("=======================show"+showtrue);
		String userid  ="";
		
		Cookie[]   cookies=request.getCookies();  
		if(cookies!=null)       
		{       
			for (int i = 0; i < cookies.length; i++){
				if("userid".equals(cookies[i].getName())){
					userid = cookies[i].getValue();
				}
			}
		}   
		
		
		//根据填报人的roleid来获取学院名称，进行数据表数据的过滤显示
		GetValueUtilDao gvuDao = new GetValueUtilDaoImpl();
		String deptxi = gvuDao.getDeptxiByRoleId(roleid);
		
		//当页面是单纯查看的时候，令deptxi等于填报人的deptxi
		if(showtrue != null && "0".equals(showtrue))
		{
	        if(cookies != null){
	            for(int i=0;i<cookies.length;i++){
	            	if("deptxi".equals(cookies[i].getName())){
						deptxi = cookies[i].getValue();
						deptxi = URLDecoder.decode(URLDecoder.decode(deptxi, "UTF-8"));
					}
	            }
	        }
		}
		
		
		System.out.println("roleid=="+roleid);
		System.out.println("deptxi=="+deptxi);
		request.setAttribute("deptxi", deptxi);

		
		
		RoleTablePowerDao roleTablePowerDao = new RoleTablePowerDaoImpl();
		int  powerid = roleTablePowerDao.getPowerByUserid(userid, tableid);
		boolean conpcom =true;
		
		//如果一级权限  并且状态为已提交,二级审核未通过，则可以显示
		if(powerid==3&&fillsituation.equals("1") && !reviewsituation.equals("3"))
			conpcom = false;
		//如果二级权限  并且一级审核已经通过，则可以显示
		else if(powerid==4&&(reviewsituation.equals("2")||reviewsituation.equals("3")))
			conpcom =false;
		
		//未提交，不可以显示
		if(fillsituation.equals("0"))
			conpcom =true;
		
		if(conpcom)
		request.setAttribute("conpcom", "true");
		else{
			request.setAttribute("conpcom", "false");
		}
		Cookie cookie = new Cookie("tableid",tableid);
		response.addCookie(cookie);
		cookie = new Cookie("watchroleid", roleid);
		response.addCookie(cookie);
		//cookie = new Cookie("showtrue", showtrue);
		//response.addCookie(cookie);
		
		request.setAttribute("showtrue", showtrue);
		Properties prop = new Properties();
		prop.load(JdbcUtils_DBCP.class.getClassLoader().getResourceAsStream(
				"TableList.properties"));
		String tablename ;
		tablename = prop.getProperty(tableid);
		
		System.out.println("==================="+tablename);
		System.out.println("==================="+tableid);
		request.getRequestDispatcher("admin/view"+tablename).forward(request, response);
	}

}
