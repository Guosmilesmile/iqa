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

import cn.edu.xmu.dao.TeachersScientificAchievementsDao;
import cn.edu.xmu.daoimpl.TeachersScientificAchievementsDaoImpl;
import cn.edu.xmu.entity.TeachersScientificAchievements;
import cn.edu.xmu.table.TeachersScientificAchievementsTable;

@WebServlet("/Sec_AddTeachersScientificAchievementsServlet")
public class Sec_AddTeachersScientificAchievementsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddTeachersScientificAchievementsServlet() {
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
		String college = request.getParameter(TeachersScientificAchievementsTable.TSA_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		System.out.println("college: "+college);
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		int result = 0;
		try {
			JSONObject json = new JSONObject(data);
			
			String total = json.getString(TeachersScientificAchievementsTable.TSA_TOTAL);
			Integer tsa_total = -1;
			if(!"".equals(total)) tsa_total = Integer.parseInt(total);
			String  nationallevel = json.getString(TeachersScientificAchievementsTable.TSA_NATIONALLEVEL); 
			Integer tsa_nationallevel = -1;
			if(!"".equals(nationallevel)) tsa_nationallevel = Integer.parseInt(nationallevel);
			String provinciallevel = json.getString(TeachersScientificAchievementsTable.TSA_PROVINCIALLEVEL); 
			Integer tsa_provinciallevel = -1;
			if(!"".equals(provinciallevel)) tsa_provinciallevel = Integer.parseInt(provinciallevel);
				
			int tsa_isnull = 0;
			if("".equals(total)||"".equals(nationallevel)||"".equals(provinciallevel))
			{
				tsa_isnull = 1;
			}
			if ("".equals(total) && "".equals(nationallevel) && "".equals(provinciallevel))
			{
				result = -1;
				out.println(result);
				return;
			}
			
			TeachersScientificAchievements teachersScientificAchievements = new TeachersScientificAchievements(
					tsa_total, tsa_nationallevel, tsa_provinciallevel, college, serialnumber, tsa_isnull);
			
			TeachersScientificAchievementsDao teachersScientificAchievementsDao = new TeachersScientificAchievementsDaoImpl();
			result = teachersScientificAchievementsDao.addTeachersScientificAchievements(teachersScientificAchievements);
			out.print(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}

