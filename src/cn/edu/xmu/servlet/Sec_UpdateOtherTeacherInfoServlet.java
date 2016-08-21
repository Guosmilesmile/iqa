package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ietf.jgss.Oid;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.FullTimeTeacherInfoDao;
import cn.edu.xmu.dao.OtherTeacherInfoDao;
import cn.edu.xmu.daoimpl.FullTimeTeacherInfoDaoImpl;
import cn.edu.xmu.daoimpl.OtherTeacherInfoDaoImpl;
import cn.edu.xmu.table.OtherTeacherInfoTable;
import cn.edu.xmu.util.FastJsonTool;

/*
 * 3-1-3 其他师资情况
 */
@WebServlet("/Sec_UpdateOtherTeacherInfoServlet")
public class Sec_UpdateOtherTeacherInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateOtherTeacherInfoServlet() {
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
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		
		String worknumberError  = "";
		Map resultMap = new HashMap();
		int result = -1;
		
		
		
		try {
			OtherTeacherInfoDao otid = new OtherTeacherInfoDaoImpl();
			if (patter != null && "batch".equals(patter)) {
				System.out.println("批量更新");
				data="["+data+"]";
				JSONArray jsons = new JSONArray(data);
				
				for (int i = 0; i < jsons.length(); i++) {
					String oti_id = jsons.getJSONObject(i).getString(OtherTeacherInfoTable.OTI_ID);
					String oti_comments = jsons.getJSONObject(i).getString( OtherTeacherInfoTable.OTI_COMMENTS);
					Map<String, String> params = new HashMap<String, String>();
					params.put(OtherTeacherInfoTable.OTI_ID,oti_id);
					params.put(OtherTeacherInfoTable.OTI_COMMENTS, oti_comments);
					result = otid.alterOtherTeacherInfo(params, oti_id);
					
				}
			}
				
			else {
				JSONObject json = new JSONObject(data);
				String oti_id = json.getString(OtherTeacherInfoTable.OTI_ID);
				String oti_worknumber = json.getString(OtherTeacherInfoTable.OTI_WORKNUMBER);
				worknumberError = chargeWorkNumber(oti_id, oti_worknumber, otid, resultMap);
				
				if (!"".equals(worknumberError)) {
					out.println(FastJsonTool.createJsonString(resultMap));
					return;
				}
				
				String oti_name = json.getString(OtherTeacherInfoTable.OTI_NAME);
				String oti_sex = json.getString( OtherTeacherInfoTable.OTI_SEX);
				String oti_birthday = json.getString( OtherTeacherInfoTable.OTI_BIRTHDAY);
				String oti_inschooldate = json.getString( OtherTeacherInfoTable.OTI_INSCHOOLDATE);
				String oti_workstate = json.getString( OtherTeacherInfoTable.OTI_WORKSTATE);
				String oti_teachertype = json.getString( OtherTeacherInfoTable.OTI_TEACHERTYPE);
				String oti_departmentnumber = json.getString( OtherTeacherInfoTable.OTI_DEPARTMENTNUMBER);
				String oti_departmentname = json.getString( OtherTeacherInfoTable.OTI_DEPARTMENTNAME);
				String oti_education = json.getString( OtherTeacherInfoTable.OTI_EDUCATION);
				String oti_degree = json.getString( OtherTeacherInfoTable.OTI_DEGREE);
				String oti_professionaltitle = json.getString( OtherTeacherInfoTable.OTI_PROFESSIONALTITLE);
				String oti_subjectcategory = json.getString( OtherTeacherInfoTable.OTI_SUBJECTCATEGORY);
				String oti_tutorcategory = json.getString( OtherTeacherInfoTable.OTI_TUTORCATEGORY);
				//String oti_commments = json.getString(OtherTeacherInfoTable.OTI_COMMENTS);
				int oti_isnull = 0;
				if ("".equals(oti_name) || "".equals(oti_worknumber) || "".equals(oti_sex) || "".equals(oti_birthday)
						|| "".equals(oti_inschooldate) || "".equals(oti_workstate) || "".equals(oti_departmentnumber)
						|| "".equals(oti_departmentname) || "".equals(oti_education) || "".equals(oti_degree)
						|| "".equals(oti_professionaltitle)
						|| "".equals(oti_subjectcategory) 
						|| "".equals(oti_workstate)
						|| "".equals(oti_tutorcategory))
						
					oti_isnull = 1;
				if(oti_birthday == null || "".equals(oti_birthday))
					oti_birthday = "1800-1-1";
				if(oti_inschooldate == null || "".equals(oti_inschooldate))
					oti_inschooldate = "1800-1-1";
			
				Map<String, String> params = new HashMap<>();
				params.put(OtherTeacherInfoTable.OTI_ID, oti_id);
				params.put(OtherTeacherInfoTable.OTI_NAME,oti_name);
				params.put(OtherTeacherInfoTable.OTI_WORKNUMBER,oti_worknumber);
				params.put(OtherTeacherInfoTable.OTI_SEX,oti_sex);
				params.put(OtherTeacherInfoTable.OTI_BIRTHDAY,oti_birthday);
				params.put(OtherTeacherInfoTable.OTI_INSCHOOLDATE,oti_inschooldate);
				params.put(OtherTeacherInfoTable.OTI_WORKSTATE,oti_workstate);
				params.put(OtherTeacherInfoTable.OTI_TEACHERTYPE,oti_teachertype);
				params.put(OtherTeacherInfoTable.OTI_DEPARTMENTNUMBER,oti_departmentnumber);
				params.put(OtherTeacherInfoTable.OTI_DEPARTMENTNAME,oti_departmentname);
				params.put(OtherTeacherInfoTable.OTI_EDUCATION,oti_education);
				params.put(OtherTeacherInfoTable.OTI_DEGREE,oti_degree);
				params.put(OtherTeacherInfoTable.OTI_PROFESSIONALTITLE,oti_professionaltitle);
				params.put(OtherTeacherInfoTable.OTI_SUBJECTCATEGORY,oti_subjectcategory);
				params.put(OtherTeacherInfoTable.OTI_TUTORCATEGORY,oti_tutorcategory);
				//params.put(OtherTeacherInfoTable.OTI_COMMENTS, oti_commments);
				params.put(OtherTeacherInfoTable.OTI_ISNULL, oti_isnull + "");
				
				result = otid.alterOtherTeacherInfo(params, oti_id);
				
			}
					
					

		} catch (JSONException e) {
			e.printStackTrace();
			result = -1;
			
		}

		resultMap.clear();
		resultMap.put("result", result);
		if (result <= 0) {
			resultMap.put("error", "数据修改失败，请查看数据是否符合规范");
		}
		out.print(FastJsonTool.createJsonString(resultMap));
		out.close();
	}
	
	protected String chargeWorkNumber(String id, String worknumber, OtherTeacherInfoDao otid, Map resultMap) {
		
		String error = "";
		int result = -1;
		FullTimeTeacherInfoDao fttd = new FullTimeTeacherInfoDaoImpl();
		
		if (worknumber != null && !"".equals(worknumber)) {
			result = otid.getOtherTeacherInfoCountByWorknumber(worknumber, id);
			if (result > 0) {
				result = -1;
				resultMap.put("result", result);
				error = "工号" + worknumber + "与其他教师表中冲突";
				resultMap.put("error", error);
				return error;
			}
			result = fttd.getCountByWorkNumber(worknumber, null);
			resultMap.clear();
			if (result > 0) {
				result = -1;
				resultMap.put("result", result);
				error = "工号" + worknumber + "与专任教师表中冲突";
				resultMap.put("error", error);
			}
		}
		return error;
	}

}
