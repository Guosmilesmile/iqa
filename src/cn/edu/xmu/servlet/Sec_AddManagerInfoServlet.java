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

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.ManagerInfoDao;
import cn.edu.xmu.daoimpl.ManagerInfoDaoImpl;
import cn.edu.xmu.entity.ManagerInfo;
import cn.edu.xmu.table.ManagerInfoTable;
import cn.edu.xmu.util.FastJsonTool;


/*
 * 3-3 管理人员基本信息
 */
@WebServlet("/Sec_AddManagerInfoServlet")
public class Sec_AddManagerInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_AddManagerInfoServlet() {
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
		String college = request.getParameter("mi_college");
		//解码
		college = URLDecoder.decode(college,"UTF-8");
		data = URLDecoder.decode(data,"UTF-8");
		data = data.substring(1, data.length()-1);
		// 暂存冲突的教师工号
		int result = 0;
		String workNumberError = "";
		Map queryParams = new HashMap();
		try {
			JSONObject jsonObj = new JSONObject(data);
			ManagerInfoDao mid = new ManagerInfoDaoImpl();

			String mi_name = jsonObj.getString( ManagerInfoTable.MI_NAME);			
			String mi_worknumber = jsonObj.getString( ManagerInfoTable.MI_WORKNUMBER);
			//判断工号是否冲突
			if(mi_worknumber != null && !"".equals(mi_worknumber))
			{
				result = mid.getCountByWorkNumber(mi_worknumber, null);
				if(result > 0)
				{
					result = -1;
					workNumberError = "工号" + mi_worknumber + "与其他管理人员工号冲突";
				}
			}
			//如果工号冲突，进行提示
			if(result<0 && !"".equals(workNumberError))
			{
				queryParams.clear();
				queryParams.put("result", result);
				queryParams.put("error", workNumberError);
				String json = FastJsonTool.createJsonString(queryParams);
				out.print(json);
				return;
			}
			String mi_sex = jsonObj.getString( ManagerInfoTable.MI_SEX);
			String birthday = jsonObj.getString( ManagerInfoTable.MI_BIRTHDAY);
			Date mi_birthday = Date.valueOf("1800-1-1");//不填则默认存1800-1-1
			if(!birthday.equals(""))
				mi_birthday = Date.valueOf(birthday);
			String inschooldate = jsonObj.getString( ManagerInfoTable.MI_INSCHOOLDATE);
			Date mi_inschooldate = Date.valueOf("1800-1-1");
			if(!inschooldate.equals(""))
				mi_inschooldate = Date.valueOf(inschooldate);
			String mi_managertype = jsonObj.getString( ManagerInfoTable.MI_MANAGERTYPE);
			String mi_departmentnumber = jsonObj.getString( ManagerInfoTable.MI_DEPARTMENTNUMBER);
			String mi_departmentname = jsonObj.getString( ManagerInfoTable.MI_DEPARTMENTNAME);
			String mi_education = jsonObj.getString( ManagerInfoTable.MI_EDUCATION);
			String mi_degrees = jsonObj.getString( ManagerInfoTable.MI_DEGREES);			
			String mi_professionaltitle = jsonObj.getString( ManagerInfoTable.MI_PROFESSIONALTITLE);			
			String mi_duty = jsonObj.getString(ManagerInfoTable.MI_DUTY);		
			
			int mi_serialnumber = serialnumber;		
			String mi_college = college;
			String mi_comments = "";
			
			//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
			int isnull = 0;
			if(mi_name.equals("")||mi_worknumber.equals("")||mi_sex.equals("")||birthday.equals("")||
					inschooldate.equals("")||mi_managertype.equals("")||mi_departmentnumber.equals("")||
					mi_departmentnumber.equals("")||mi_departmentname.equals("")||mi_education.equals("")||
					mi_degrees.equals("")||mi_professionaltitle.equals("")||mi_duty.equals(""))
				isnull = 1;
			if(mi_name.equals("")&&mi_worknumber.equals("")&&mi_sex.equals("")&&birthday.equals("")&&
					inschooldate.equals("")&&mi_managertype.equals("")&&mi_departmentnumber.equals("")&&
					mi_departmentnumber.equals("")&&mi_departmentname.equals("")&&mi_education.equals("")&&
					mi_degrees.equals("")&&mi_professionaltitle.equals("")&&mi_duty.equals(""))
			{
				queryParams.clear();
				queryParams.put("result", -1);
				queryParams.put("error", "不能添加空记录");
				String json1 = FastJsonTool.createJsonString(queryParams);
				out.print(json1);
				return;
			}
			
			ManagerInfo mi = new ManagerInfo(mi_name, mi_worknumber, mi_sex, mi_birthday, mi_inschooldate,
					mi_managertype, mi_departmentnumber, mi_departmentname, mi_education, mi_degrees,
					mi_professionaltitle, mi_duty, mi_college, mi_serialnumber, mi_comments,isnull);
			
			result = mid.addManagerInfoRecord(mi);
			
		} catch (JSONException e) {
			e.printStackTrace();
			result = -1;
		}
		queryParams.clear();
		queryParams.put("result", result);
		if (result <= 0)
			queryParams.put("error", "数据添加失败，请查看数据是否符合规范");
		String json = FastJsonTool.createJsonString(queryParams);
		out.print(json);
		
	}


}
