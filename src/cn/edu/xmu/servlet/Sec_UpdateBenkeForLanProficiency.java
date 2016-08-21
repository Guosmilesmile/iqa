package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.BenkeForLanProficiencyDao;
import cn.edu.xmu.daoimpl.BenkeForLanProficiencyDaoImpl;
import cn.edu.xmu.table.BenkeForLanProficiencyTable;


/**
 * Servlet implementation class Sec_UpdateBenkeForLanProficiency
 */
@WebServlet("/Sec_UpdateBenkeForLanProficiency")
public class Sec_UpdateBenkeForLanProficiency extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateBenkeForLanProficiency() {
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
		String patter = request.getParameter("patter");
		//解码
		//String college = request.getParameter(BenkeForLanProficiencyTable.BFLP_COLLEGE);
		//college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		
		try {
			if (patter != null && "batch".equals(patter)) {
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray json = new JSONArray(data);
				for (int i = 0; i < json.length(); i++) {
					String bflp_id = json.getJSONObject(i).getString(
							BenkeForLanProficiencyTable.BFLP_ID);
					String bflp_comments = json.getJSONObject(i).getString(
							BenkeForLanProficiencyTable.BFLP_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					params.put(BenkeForLanProficiencyTable.BFLP_COMMENTS, bflp_comments);
					BenkeForLanProficiencyDao bflpDao = new BenkeForLanProficiencyDaoImpl();
					int result = bflpDao.alterBenkeForLanProficiency(params, bflp_id);
					
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
				
				String bflp_id = json.getString(BenkeForLanProficiencyTable.BFLP_ID);
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
				
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(bflp_stunum.equals("")||bflp_college1.equals("")||bflp_name.equals("")||bflp_major.equals("")||
						bflp_grade.equals("")||bflp_degree.equals("")||bflp_level.equals("")||bflp_gpa.equals("")
						||bflp_rank.equals("")||bflp_ispush.equals(""))
					isnull = 1;
				
				
				Map<String,String> params= new HashMap<String, String>();
				
				if(json.has(BenkeForLanProficiencyTable.BFLP_COMMENTS)){
					String bflp_comments = json.getString(BenkeForLanProficiencyTable.BFLP_COMMENTS);
					params.put(BenkeForLanProficiencyTable.BFLP_COMMENTS, bflp_comments);
				}
				if(json.has(BenkeForLanProficiencyTable.BFLP_DEADLINE)){
					String bflp_deadline = json.getString(BenkeForLanProficiencyTable.BFLP_DEADLINE);
					params.put(BenkeForLanProficiencyTable.BFLP_DEADLINE, bflp_deadline);
				}
				params.put(BenkeForLanProficiencyTable.BFLP_STUNUM, bflp_stunum);
				params.put(BenkeForLanProficiencyTable.BFLP_COLLEGE1, bflp_college1);
				params.put(BenkeForLanProficiencyTable.BFLP_NAME, bflp_name);
				params.put(BenkeForLanProficiencyTable.BFLP_MAJOR, bflp_major);
				params.put(BenkeForLanProficiencyTable.BFLP_GRADE, bflp_grade);
				params.put(BenkeForLanProficiencyTable.BFLP_DEGREE, bflp_degree);
				params.put(BenkeForLanProficiencyTable.BFLP_LEVEL, bflp_level);
				params.put(BenkeForLanProficiencyTable.BFLP_GPA, bflp_gpa);
				params.put(BenkeForLanProficiencyTable.BFLP_RANK, bflp_rank);
				params.put(BenkeForLanProficiencyTable.BFLP_ISPUSH, bflp_ispush);
				params.put(BenkeForLanProficiencyTable.ISNULL, isnull+"");
				
				BenkeForLanProficiencyDao bflpDao = new BenkeForLanProficiencyDaoImpl();
				
				int result = bflpDao.alterBenkeForLanProficiency(params, bflp_id);
				
				out.print(result);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
