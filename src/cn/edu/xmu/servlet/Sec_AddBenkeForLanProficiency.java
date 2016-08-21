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

import cn.edu.xmu.dao.BenkeForLanProficiencyDao;
import cn.edu.xmu.daoimpl.BenkeForLanProficiencyDaoImpl;
import cn.edu.xmu.entity.BenkeForLanProficiency;
import cn.edu.xmu.table.BenkeForLanProficiencyTable;

/**
 * Servlet implementation class Sec_AddBenkeForLanProficiency
 */
@WebServlet("/Sec_AddBenkeForLanProficiency")
public class Sec_AddBenkeForLanProficiency extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddBenkeForLanProficiency() {
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
		PrintWriter out = response.getWriter();

		String data = request.getParameter("rowdata");
		int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
		//解码
		String college = request.getParameter(BenkeForLanProficiencyTable.BFLP_COLLEGE);
		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			JSONObject json = new JSONObject(data);
			
			
			String bflp_stunum = json.getString(BenkeForLanProficiencyTable.BFLP_STUNUM);
			String bflp_college1 = json.getString(BenkeForLanProficiencyTable.BFLP_COLLEGE1);
			
			String bflp_name = json.getString(BenkeForLanProficiencyTable.BFLP_NAME);
			String bflp_major = json.getString(BenkeForLanProficiencyTable.BFLP_MAJOR);
			String bflp_grade = json.getString(BenkeForLanProficiencyTable.BFLP_GRADE);
			String bflp_degree = json.getString(BenkeForLanProficiencyTable.BFLP_DEGREE);
			String bflp_level = json.getString(BenkeForLanProficiencyTable.BFLP_LEVEL);
			String bflp_gpa = json.getString(BenkeForLanProficiencyTable.BFLP_GPA);
			String bflp_rank = json.getString(BenkeForLanProficiencyTable.BFLP_RANK);
			String bflp_ispush = json.getString(BenkeForLanProficiencyTable.BFLP_ISPUSH);
			
			int bflp_serialnumber = serialnumber;
			String bflp_college =  college;
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(bflp_college1.equals("")||bflp_college1.equals("")||bflp_name.equals("")||bflp_major.equals("")||bflp_grade.equals("")||
					bflp_degree.equals("")||bflp_level.equals("")||bflp_gpa.equals("")||bflp_rank.equals("")||bflp_ispush.equals(""))
				isnull = 1;
			
			
			BenkeForLanProficiency bflp = new BenkeForLanProficiency(bflp_stunum, bflp_college1, bflp_name, bflp_major, bflp_grade,
					bflp_degree, bflp_level, bflp_gpa, bflp_rank, bflp_ispush, bflp_serialnumber, bflp_college, isnull);
			
			BenkeForLanProficiencyDao bflpDao = new BenkeForLanProficiencyDaoImpl();
			bflpDao.addRecord(bflp);
			
			out.print(true);
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}

}
