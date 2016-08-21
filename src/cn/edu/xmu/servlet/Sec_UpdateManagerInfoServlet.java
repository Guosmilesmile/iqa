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

import cn.edu.xmu.dao.ManagerInfoDao;
import cn.edu.xmu.daoimpl.ManagerInfoDaoImpl;
import cn.edu.xmu.table.MajorInfoTable;
import cn.edu.xmu.table.ManagerInfoTable;
import cn.edu.xmu.util.FastJsonTool;

/*
 * 3-3 管理人员基本信息
 */
@WebServlet("/Sec_UpdateManagerInfoServlet")
public class Sec_UpdateManagerInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_UpdateManagerInfoServlet() {
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
		//String serialnumber  = request.getParameter("serialnumber");
		
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		// 暂存冲突的教师工号
		String workNumberError = "";
		Map resultMap = new HashMap();
		int result = 0;

		try {
			if (patter!=null && "batch".equals(patter)) {
				System.out.println("批量更新");
				//审核部分更新，可以批量
				data="["+data+"]";
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++) {
					Map<String,String> params= new HashMap<String, String>();
					String mi_id = jsons.getJSONObject(i).getString(ManagerInfoTable.MI_ID);
					String mi_comments = jsons.getJSONObject(i).getString(MajorInfoTable.MI_COMMENTS);		
					params.put(MajorInfoTable.MI_COMMENTS,mi_comments);
					ManagerInfoDao mid = new ManagerInfoDaoImpl();
					result = mid.alterManagerInfo(params, mi_id);
					
					out.print(result);
				}
			}
			else{
				JSONObject jsonObj = new JSONObject(data);
				Map<String,String> params= new HashMap<String, String>();
				ManagerInfoDao mid = new ManagerInfoDaoImpl();

				
				String mi_id = jsonObj.getString(ManagerInfoTable.MI_ID);
				String mi_name = jsonObj.getString( ManagerInfoTable.MI_NAME);
				String mi_worknumber = jsonObj.getString( ManagerInfoTable.MI_WORKNUMBER);
				if(mi_worknumber != null && !"".equals(mi_worknumber))
				{
					result = mid.getCountByWorkNumber(mi_worknumber, mi_id);
					if(result > 0)
					{
						result = -1;
						workNumberError = "工号" + mi_worknumber + "与其他管理人员工号冲突";
					}
				}
				if(result<0 && !"".equals(workNumberError))
				{
					resultMap.clear();
					resultMap.put("result", result);
					resultMap.put("error", workNumberError);
					String json = FastJsonTool.createJsonString(resultMap);
					out.print(json);
					return;
				}
				String mi_sex = jsonObj.getString( ManagerInfoTable.MI_SEX);
				String mi_birthday = jsonObj.getString( ManagerInfoTable.MI_BIRTHDAY);				
				String mi_inschooldate = jsonObj.getString( ManagerInfoTable.MI_INSCHOOLDATE);
				String mi_managertype = jsonObj.getString( ManagerInfoTable.MI_MANAGERTYPE);
				String mi_departmentnumber = jsonObj.getString( ManagerInfoTable.MI_DEPARTMENTNUMBER);
				String mi_departmentname = jsonObj.getString( ManagerInfoTable.MI_DEPARTMENTNAME);
				String mi_education = jsonObj.getString( ManagerInfoTable.MI_EDUCATION);
				String mi_degrees = jsonObj.getString( ManagerInfoTable.MI_DEGREES);
				String mi_professionaltitle = jsonObj.getString( ManagerInfoTable.MI_PROFESSIONALTITLE);
				String mi_duty = jsonObj.getString(ManagerInfoTable.MI_DUTY);
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(mi_name.equals("")||mi_worknumber.equals("")||mi_sex.equals("")||mi_birthday.equals("")||
						mi_inschooldate.equals("")||mi_managertype.equals("")||mi_departmentnumber.equals("")||
						mi_departmentnumber.equals("")||mi_departmentname.equals("")||mi_education.equals("")||
						mi_degrees.equals("")||mi_professionaltitle.equals("")||mi_duty.equals(""))
					isnull = 1;
				
				
				if(jsonObj.has(ManagerInfoTable.MI_COMMENTS))
				{
					String mi_comments =jsonObj.getString(MajorInfoTable.MI_COMMENTS);		
					params.put(MajorInfoTable.MI_COMMENTS,mi_comments);
				}
				if(jsonObj.has(ManagerInfoTable.MI_DEADLINE)){
					String mi_deadline = jsonObj.getString(ManagerInfoTable.MI_DEADLINE);
					params.put(ManagerInfoTable.MI_DEADLINE, mi_deadline);
				}
				
				if(mi_inschooldate.equals("") || mi_inschooldate == null)
					mi_inschooldate = "1800-01-01";
				if(mi_birthday.equals("") || mi_birthday == null)
					mi_birthday = "1800-01-01";//为空默认
				
				params.put(ManagerInfoTable.MI_NAME,mi_name);
				params.put(ManagerInfoTable.MI_WORKNUMBER,mi_worknumber);
				params.put(ManagerInfoTable.MI_SEX,mi_sex);
				params.put(ManagerInfoTable.MI_BIRTHDAY,mi_birthday);
				params.put(ManagerInfoTable.MI_INSCHOOLDATE,mi_inschooldate);
				params.put(ManagerInfoTable.MI_MANAGERTYPE,mi_managertype);
				params.put(ManagerInfoTable.MI_DEPARTMENTNUMBER,mi_departmentnumber);
				params.put(ManagerInfoTable.MI_DEPARTMENTNAME,mi_departmentname);
				params.put(ManagerInfoTable.MI_EDUCATION,mi_education);
				params.put(ManagerInfoTable.MI_DEGREES,mi_degrees);
				params.put(ManagerInfoTable.MI_PROFESSIONALTITLE,mi_professionaltitle);
				params.put(ManagerInfoTable.MI_DUTY, mi_duty);
				params.put(ManagerInfoTable.ISNULL, isnull+"");
				
				result = mid.alterManagerInfo(params, mi_id);
			}
		} catch (JSONException e) {
			e.printStackTrace();
			result = -1;
		}
		resultMap.put("result", result);
		if (result <= 0)
		{
			resultMap.clear();
			resultMap.put("error", "数据修改失败，请查看数据是否符合规范");
		}
		out.print(FastJsonTool.createJsonString(resultMap));
		out.close();
		
	}

}

