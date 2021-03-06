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

import cn.edu.xmu.dao.SchoolExecutiveUnitDao;
import cn.edu.xmu.daoimpl.SchoolExecutiveUnitDaoImpl;
import cn.edu.xmu.table.SchoolExecutiveUnitTable;

/**
 * Servlet implementation class Sec_UpdateSchoolExecutiveUnit
 */
@WebServlet("/Sec_UpdateSchoolExecutiveUnit")
public class Sec_UpdateSchoolExecutiveUnit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateSchoolExecutiveUnit() {
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
		//int serialnumber  = Integer.parseInt(request.getParameter("serialnumber"));
		//解码
//		String college = request.getParameter(SchoolExecutiveUnitTable.SEU_COLLEGE);
//		college = URLDecoder.decode(college,"UTF-8");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		try {
			if (patter != null && "batch".equals(patter)) {
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray json = new JSONArray(data);
				for (int i = 0; i < json.length(); i++) {
					String seu_id = json.getJSONObject(i).getString(
							SchoolExecutiveUnitTable.SEU_ID);
					String seu_comments = json.getJSONObject(i).getString(
							SchoolExecutiveUnitTable.SEU_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					params.put(SchoolExecutiveUnitTable.SEU_COMMENTS, seu_comments);
					SchoolExecutiveUnitDao seuDao = new SchoolExecutiveUnitDaoImpl();
					int result = seuDao.alterSchoolExecutiveUnit(params, seu_id);
					
					out.print(result);
				}
			}
			else {
				JSONObject json = new JSONObject(data);
				
				String seu_id = json.getString(SchoolExecutiveUnitTable.SEU_ID);
				String seu_departmentname = json.getString(SchoolExecutiveUnitTable.SEU_DEPARTMENTNAME);
				String seu_departmentnumber = json.getString(SchoolExecutiveUnitTable.SEU_DEPARTMENTNUMBER);
				String seu_function = json.getString(SchoolExecutiveUnitTable.SEU_FUNCTION);
				String seu_bosshead = json.getString(SchoolExecutiveUnitTable.SEU_BOSSHEAD);
				String seu_comments = json.getString(SchoolExecutiveUnitTable.SEU_COMMENTS);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(seu_departmentname.equals("")||seu_departmentnumber.equals("")||seu_function.equals("")||seu_bosshead.equals(""))
					isnull = 1;
				
				Map<String,String> params= new HashMap<String, String>();
				params.put(SchoolExecutiveUnitTable.SEU_DEPARTMENTNAME, seu_departmentname);
				params.put(SchoolExecutiveUnitTable.SEU_DEPARTMENTNUMBER, seu_departmentnumber);
				params.put(SchoolExecutiveUnitTable.SEU_FUNCTION, seu_function);
				params.put(SchoolExecutiveUnitTable.SEU_BOSSHEAD, seu_bosshead);
				params.put(SchoolExecutiveUnitTable.SEU_COMMENTS, seu_comments);
				params.put(SchoolExecutiveUnitTable.ISNULL, isnull+"");
				//params.put(SchoolExecutiveUnitTable.SEU_COLLEGE, college);
				
				SchoolExecutiveUnitDao seuDao = new SchoolExecutiveUnitDaoImpl();
				int result = seuDao.alterSchoolExecutiveUnit(params, seu_id);
				
				out.print(result);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}

}
