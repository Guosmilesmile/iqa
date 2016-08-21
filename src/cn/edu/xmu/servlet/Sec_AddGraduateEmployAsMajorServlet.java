package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.GraduateEmployAsMajorDao;
import cn.edu.xmu.daoimpl.GraduateEmployAsMajorDaoImpl;
import cn.edu.xmu.entity.GraduateEmployAsMajor;
import cn.edu.xmu.table.GraduateEmployAsMajorTable;

/**
 * Servlet implementation class AddGraduateEmployAsMajorServlet
 */
@WebServlet("/Sec_AddGraduateEmployAsMajorServlet")
public class Sec_AddGraduateEmployAsMajorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddGraduateEmployAsMajorServlet() {
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
		PrintWriter out = response.getWriter();

		String data = request.getParameter("rowdata");
		int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		int result = 0;
		
		try {
			JSONObject json = new JSONObject(data);
			
			String geam_majorcodeinschool = json.getString(GraduateEmployAsMajorTable.GEAM_MAJORCODEINSCHOOL);
			String geam_majornameinschool = json.getString(GraduateEmployAsMajorTable.GEAM_MAJORNAMEINSCHOOL);				
			String graduatenum = json.getString(GraduateEmployAsMajorTable.GEAM_GRADUATENUM);
			Integer geam_graduatenum = -1;
			if(!"".equals(graduatenum)) geam_graduatenum = Integer.parseInt(graduatenum);
			
			String geam_college = request.getParameter(GraduateEmployAsMajorTable.GEAM_COLLEGE);
			geam_college = URLDecoder.decode(geam_college,"UTF-8");
			
			int geam_isnull = 0;
			if("".equals(geam_majorcodeinschool)||"".equals(geam_majornameinschool)||"".equals(graduatenum))
			{
				geam_isnull = 1;
			}
			if ("".equals(geam_majorcodeinschool)&&"".equals(geam_majornameinschool)&&"".equals(graduatenum))
			{
				result = -1;
				out.println(result);
				return;
			}
			GraduateEmployAsMajor graduateEmployAsMajor = new GraduateEmployAsMajor(
					geam_majorcodeinschool, geam_majornameinschool, geam_graduatenum, geam_college, serialnumber, geam_isnull);
			
			GraduateEmployAsMajorDao graduateEmployAsMajorDao = new GraduateEmployAsMajorDaoImpl();
			result = graduateEmployAsMajorDao.addGraduateEmployAsMajor(graduateEmployAsMajor);
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
