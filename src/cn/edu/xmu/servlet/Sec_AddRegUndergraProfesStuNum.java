package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.RegUndergraProfesStuNumDao;
import cn.edu.xmu.daoimpl.RegUndergraProfesStuNumDaoImpl;
import cn.edu.xmu.entity.RegUndergraProfesStuNum;
import cn.edu.xmu.table.RegUndergraProfesStuNumTable;

/**
 * 
 * @author xiaoping 数据报表6-1-2 普通本科分专业（大类）学生数（时点） date 2015-7-5
 *
 */
@WebServlet("/Sec_AddRegUndergraProfesStuNum")
public class Sec_AddRegUndergraProfesStuNum extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_AddRegUndergraProfesStuNum()
	{
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();

		String data = request.getParameter("rowdata");
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		System.out.println(data);
		int serialnumber = Integer.parseInt(request.getParameter("serialnumber"));
		// 解码
		String college = request.getParameter(RegUndergraProfesStuNumTable.RUPSN_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");
		int result = 0;
		try
		{
			JSONObject jsonObj = new JSONObject(data);
			RegUndergraProfesStuNumDao rupsnDao = new RegUndergraProfesStuNumDaoImpl();
			// JSONArray jsonArray = new JSONArray(data);
			// int iSize = jsonArray.length();
			// System.out.println("Size:" + iSize);
			// for (int i = 0; i < iSize; i++)
			// {
			// JSONObject jsonObj = jsonArray.getJSONObject(i);

			String rupsn_schprofescode = jsonObj.getString(RegUndergraProfesStuNumTable.RUPSN_SCHPROFESCODE);
			String rupsn_schprofesname = jsonObj.getString(RegUndergraProfesStuNumTable.RUPSN_SCHPROFESNAME);
			String edusystem = jsonObj.getString(RegUndergraProfesStuNumTable.RUPSN_EDUSYSTEM);
			String gradeone = jsonObj.getString(RegUndergraProfesStuNumTable.RUPSN_GRADEONE);
			String gradetwo = jsonObj.getString(RegUndergraProfesStuNumTable.RUPSN_GRADETWO);
			String gradethree = jsonObj.getString(RegUndergraProfesStuNumTable.RUPSN_GRADETHREE);
			String gradefour = jsonObj.getString(RegUndergraProfesStuNumTable.RUPSN_GRADEFOUR);
			String abgradefive = jsonObj.getString(RegUndergraProfesStuNumTable.RUPSN_ABGRADEFIVE);
			String minor = jsonObj.getString(RegUndergraProfesStuNumTable.RUPSN_MINOR);
			String doubledegree = jsonObj.getString(RegUndergraProfesStuNumTable.RUPSN_DOUBLEDEGREE);
			String intonumber = jsonObj.getString(RegUndergraProfesStuNumTable.RUPSN_INTONUMBER);
			String outnumber = jsonObj.getString(RegUndergraProfesStuNumTable.RUPSN_OUTNUMBER);
			int rupsn_edusystem = -9;
			int rupsn_atschtotal = 0;
			int rupsn_gradeone = -9;
			int rupsn_gradetwo = -9;
			int rupsn_gradethree = -9;
			int rupsn_gradefour = -9;
			int rupsn_abgradefive = -9;
			int rupsn_minor = -9;
			int rupsn_doubledegree = -9;
			int rupsn_intonumber = -9;
			int rupsn_outnumber = -9;
			if (edusystem != null && !"".equals(edusystem))
				rupsn_edusystem = Integer.parseInt(edusystem);
			if (gradeone != null && !"".equals(gradeone))
			{
				rupsn_gradeone = Integer.parseInt(gradeone);
				rupsn_atschtotal += rupsn_gradeone;
			}
			if (gradetwo != null && !"".equals(gradetwo))
			{
				rupsn_gradetwo = Integer.parseInt(gradetwo);
				rupsn_atschtotal += rupsn_gradetwo;
			}
			if (gradethree != null && !"".equals(gradethree))
			{
				rupsn_gradethree = Integer.parseInt(gradethree);
				rupsn_atschtotal += rupsn_gradethree;
			}
			if (gradefour != null && !"".equals(gradefour))
			{
				rupsn_gradefour = Integer.parseInt(gradefour);
				rupsn_atschtotal += rupsn_gradefour;
			}
			if (abgradefive != null && !"".equals(abgradefive))
			{
				rupsn_abgradefive = Integer.parseInt(abgradefive);
				rupsn_atschtotal += rupsn_abgradefive;
			}
			if (minor != null && !"".equals(minor))
				rupsn_minor = Integer.parseInt(minor);
			if (doubledegree != null && !"".equals(doubledegree))
				rupsn_doubledegree = Integer.parseInt(doubledegree);
			if (intonumber != null && !"".equals(intonumber))
				rupsn_intonumber = Integer.parseInt(intonumber);
			if (outnumber != null && !"".equals(outnumber))
				rupsn_outnumber = Integer.parseInt(outnumber);
			int rupsn_isnull = 0;
			if ("".equals(rupsn_schprofescode) || "".equals(rupsn_schprofesname) || "".equals(edusystem)
					|| "".equals(gradeone) || "".equals(gradetwo) || "".equals(gradethree) || "".equals(gradefour)
					|| "".equals(abgradefive) || "".equals(minor) || "".equals(doubledegree) || "".equals(intonumber)
					|| "".equals(outnumber))
				rupsn_isnull = 1;
			int rupsn_serialnumber = serialnumber;
			String rupsn_college = college;
			String rupsn_comments = "";
			// 如果插入所有字段全为空，则不允许插入
			if ("".equals(rupsn_schprofescode) && "".equals(rupsn_schprofesname) && "".equals(edusystem)
					&& "".equals(gradeone) && "".equals(gradetwo) && "".equals(gradethree) && "".equals(gradefour)
					&& "".equals(abgradefive) && "".equals(minor) && "".equals(doubledegree) && "".equals(intonumber)
					&& "".equals(outnumber))
			{
				out.print(-1);
				return;
			}
			RegUndergraProfesStuNum regUndergraProfesStuNum = new RegUndergraProfesStuNum(0, rupsn_schprofescode,
					rupsn_schprofesname, rupsn_edusystem, rupsn_atschtotal, rupsn_gradeone, rupsn_gradetwo,
					rupsn_gradethree, rupsn_gradefour, rupsn_abgradefive, rupsn_minor, rupsn_doubledegree,
					rupsn_intonumber, rupsn_outnumber, rupsn_serialnumber, null, rupsn_college, rupsn_comments,
					rupsn_isnull);

			result = rupsnDao.addRegUndergraProfesStuNum(regUndergraProfesStuNum);
			// }
		} catch (JSONException e)
		{
			e.printStackTrace();
		}

		out.print(result);
	}

}
