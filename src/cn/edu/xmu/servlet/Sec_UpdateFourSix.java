package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;
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

import cn.edu.xmu.dao.FourSixDao;
import cn.edu.xmu.daoimpl.FourSixDaoImpl;
import cn.edu.xmu.entity.FourSix;
import cn.edu.xmu.table.FourSixTable;




/**
 * Servlet implementation class Sec_UpdateSchoolExecutiveUnit
 */
@WebServlet("/Sec_UpdateFourSix")
public class Sec_UpdateFourSix extends HttpServlet {
private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateFourSix() {
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
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		
		
		try {
			if (patter!=null && "batch".equals(patter)) {
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";
				JSONArray jsons = new JSONArray(data);
				
				for (int i = 0; i < jsons.length(); i++) {
					JSONObject json = jsons.getJSONObject(i);
					Map<String,String> params= new HashMap<String, String>();
					String fx_id = json.getString(FourSixTable.FX_ID);
					String fx_colleges = json.getString(FourSixTable.FX_COLLEGES);
					String fx_department = json.getString(FourSixTable.FX_DEAPERMENT);
					String fx_major = json.getString(FourSixTable.FX_MAJOR);
					String fx_grade =json.getString(FourSixTable.FX_GRADE);
					String fx_level = json.getString(FourSixTable.FX_LEVEL);
					String fx_total = json.getString(FourSixTable.FX_TOTAL);
					String fx_attend = json.getString(FourSixTable.FX_ATTEND);
					String fx_attendcount = json.getString(FourSixTable.FX_ATTENDCOUNT);
					String fx_pass = json.getString(FourSixTable.FX_PASS);
					String fx_passpercent = json.getString(FourSixTable.FX_PASSPERCENT);
					String fx_good = json.getString(FourSixTable.FX_GOOD);
					String fx_goodpercent = json.getString(FourSixTable.FX_GOODPERCENT);
					int fx_serialnumber = json.getInt(FourSixTable.FX_SERIALNUMBER);
					String fx_college = json.getString(FourSixTable.FX_COLLEGE);
					String fx_comments = json.getString(FourSixTable.FX_COMMENTS);
					
					
					
					params.put(FourSixTable.FX_COLLEGES, fx_colleges);
					params.put(FourSixTable.FX_DEAPERMENT, fx_department );
					params.put(FourSixTable.FX_GRADE, fx_grade );
					params.put(FourSixTable.FX_MAJOR,fx_major );
					params.put(FourSixTable.FX_GRADE,fx_grade);
					params.put(FourSixTable.FX_LEVEL, fx_level);
					params.put(FourSixTable.FX_TOTAL,fx_total );
					params.put(FourSixTable.FX_ATTEND, fx_attend);
					params.put(FourSixTable.FX_ATTENDCOUNT, fx_attendcount );
					params.put(FourSixTable.FX_PASS, fx_pass);
					params.put(FourSixTable.FX_PASSPERCENT, fx_passpercent);
					params.put(FourSixTable.FX_GOOD, fx_good);
					params.put(FourSixTable.FX_GOODPERCENT, fx_goodpercent);
					
					params.put(FourSixTable.FX_SERIALNUMBER, fx_serialnumber+"");
					params.put(FourSixTable.FX_COLLEGE, fx_college);
					params.put(FourSixTable.FX_COMMENTS, fx_comments );
					
					FourSixDao apdao = new FourSixDaoImpl();
					int result = apdao.alterFourSix(params, fx_id);
					
					out.print(result);
				}
			}
			else{
				JSONObject json = new JSONObject(data);
				Map<String,String> params= new HashMap<String, String>();
				String fx_id = json.getString(FourSixTable.FX_ID);
				String fx_colleges = json.getString(FourSixTable.FX_COLLEGES);
				String fx_department = json.getString(FourSixTable.FX_DEAPERMENT);
				String fx_major = json.getString(FourSixTable.FX_MAJOR);
				String fx_grade =json.getString(FourSixTable.FX_GRADE);
				String fx_level = json.getString(FourSixTable.FX_LEVEL);
				String fx_total = json.getString(FourSixTable.FX_TOTAL);
				String fx_attend = json.getString(FourSixTable.FX_ATTEND);
				String fx_attendcount = json.getString(FourSixTable.FX_ATTENDCOUNT);
				String fx_pass = json.getString(FourSixTable.FX_PASS);
				String fx_passpercent = json.getString(FourSixTable.FX_PASSPERCENT);
				String fx_good = json.getString(FourSixTable.FX_GOOD);
				String fx_goodpercent = json.getString(FourSixTable.FX_GOODPERCENT);
				int fx_serialnumber = json.getInt(FourSixTable.FX_SERIALNUMBER);
				String fx_college = json.getString(FourSixTable.FX_COLLEGE);
				String fx_comments = json.getString(FourSixTable.FX_COMMENTS);
				
				
				int isnull = 0;
				if ("".equals(fx_colleges) || "".equals(fx_department)
						|| "".equals(fx_major) || "".equals(fx_grade)
						|| "".equals(fx_level) || "".equals(fx_total)
						|| "".equals(fx_attend) || "".equals(fx_attendcount) || "".equals(fx_pass)
						|| "".equals(fx_passpercent) || "".equals(fx_good) || "".equals(fx_goodpercent) )
					isnull = 1;
				params.put(FourSixTable.FX_COLLEGES, fx_colleges);
				params.put(FourSixTable.FX_DEAPERMENT, fx_department );
				params.put(FourSixTable.FX_GRADE, fx_grade );
				params.put(FourSixTable.FX_MAJOR,fx_major );
				params.put(FourSixTable.FX_GRADE,fx_grade);
				params.put(FourSixTable.FX_LEVEL, fx_level);
				params.put(FourSixTable.FX_TOTAL,fx_total );
				params.put(FourSixTable.FX_ATTEND, fx_attend);
				params.put(FourSixTable.FX_ATTENDCOUNT, fx_attendcount );
				params.put(FourSixTable.FX_PASS, fx_pass);
				params.put(FourSixTable.FX_PASSPERCENT, fx_passpercent);
				params.put(FourSixTable.FX_GOOD, fx_good);
				params.put(FourSixTable.FX_GOODPERCENT, fx_goodpercent);
				
				params.put(FourSixTable.FX_SERIALNUMBER, fx_serialnumber+"");
				params.put(FourSixTable.FX_COLLEGE, fx_college);
				params.put(FourSixTable.FX_COMMENTS, fx_comments );
				params.put("isnull",isnull+"");
				FourSixDao apdao = new FourSixDaoImpl();
				int result = apdao.alterFourSix(params, fx_id);
				
				out.print(result);
			}
			
		} catch (JSONException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
		
		
	}


}
