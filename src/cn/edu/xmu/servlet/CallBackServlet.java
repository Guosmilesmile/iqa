package cn.edu.xmu.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.UserDao;
import cn.edu.xmu.daoimpl.UserDaoImpl;
import cn.edu.xmu.util.FastJsonTool;

/**
 * Servlet implementation class CallBackServlet
 */
@WebServlet("/CallBackServlet")
public class CallBackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CallBackServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		String code = request.getParameter("code");
		System.out.println(code);
		String strurl = "http://open.xmu.edu.cn/oauth2/token?client_id=1044&client_secret=78ef71c2647746c686fee320366eb9c1&grant_type=authorization_code&code="+code;
		URL url = new URL(strurl);
		HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
		InputStreamReader input = new InputStreamReader(httpConnection.getInputStream(),"utf-8");
		BufferedReader bufferedReader = new BufferedReader(input);
		String string ="";
		String info = null;
		while((info=bufferedReader.readLine())!=null){
			string+=info;
		}
		String token = "";
		try {
			JSONObject jsonObject = new JSONObject(string);
			 token = (String) jsonObject.get("access_token");
			 strurl="http://open.xmu.edu.cn/oauth2/get_token_info?access_token="+token;
			 url = new URL(strurl);
			 httpConnection = (HttpURLConnection) url.openConnection();
			 input = new InputStreamReader(httpConnection.getInputStream(),"utf-8");
			 bufferedReader = new BufferedReader(input);
				string ="";
				info = null;
				while((info=bufferedReader.readLine())!=null){
					string+=info;
				}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		String stringg ="";
		try {
			String strurll = "http://open.xmu.edu.cn/v1/user/userinfo?access_token="+token;
			URL url2 = new URL(strurll);
			HttpURLConnection httpURLConnection = (HttpURLConnection) url2.openConnection();
			InputStreamReader inputt = new InputStreamReader(httpURLConnection.getInputStream(),"utf-8");
			BufferedReader bufferedReaderr = new BufferedReader(inputt);
			stringg ="";
			String infoo = null;
			while((infoo=bufferedReaderr.readLine())!=null){
				stringg+=infoo;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String 	userid="";
		String  username="";
		String  deptxi="";
		
		try {
			JSONObject jsonObject = new JSONObject(stringg);
			userid = (String) jsonObject.get("username");
			username = (String) jsonObject.get("realname");
			deptxi = (String) jsonObject.get("deptxi");
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		
		UserDao userDao =new UserDaoImpl();
		int[] power = new int[4];
		power= userDao.getAllPower(userid);
		Cookie cookie ;
		for(int i=0;i<4;i++){
			System.out.println(power[i]);
		}
		if(power[1]==1){
			cookie = new Cookie("manageuser", "1");
			response.addCookie(cookie);
			//request.getSession().setAttribute("manageuser", "1");
		}
		else {
			cookie = new Cookie("manageuser", "0");
			response.addCookie(cookie);
			//request.getSession().setAttribute("manageuser", "0");
		}
		if(power[2]==1){
			cookie = new Cookie("managerole", "1");
			response.addCookie(cookie);
			//request.getSession().setAttribute("managerole", "1");
		}else{
			cookie = new Cookie("managerole", "0");
			response.addCookie(cookie);
			//request.getSession().setAttribute("managerole", "0");
		}
		if(power[3]==1){
			cookie = new Cookie("managetable", "1");
			response.addCookie(cookie);
			//request.getSession().setAttribute("managetable", "1");
		}else{
			cookie = new Cookie("managetable", "0");
			response.addCookie(cookie);
			//request.getSession().setAttribute("managetable", "0");
		}
		cookie = new Cookie("userid", userid); 
		response.addCookie(cookie);
		cookie = new Cookie("username", URLEncoder.encode(URLEncoder.encode(username, "UTF-8"))); 
		response.addCookie(cookie);
		cookie = new Cookie("deptxi", URLEncoder.encode(URLEncoder.encode(deptxi, "UTF-8"))); 
		response.addCookie(cookie);
		//request.getSession().setAttribute("userid", userid);
		//request.getSession().setAttribute("username", username);
		response.sendRedirect("admin/main.jsp");
		//request.getRequestDispatcher("admin/main.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
