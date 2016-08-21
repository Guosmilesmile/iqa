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

import cn.edu.xmu.dao.HighLevelTalentDao;
import cn.edu.xmu.daoimpl.HighLevelTalentDaoImpl;
import cn.edu.xmu.entity.HighLevelTalent;
import cn.edu.xmu.table.HighLevelTalentTable;

/**
 * 
 * @author xiaoping 表3-4-1 高层次人才(时点) date 2015-7-3
 *
 */
@WebServlet("/Sec_AddHighLevelTalent")
public class Sec_AddHighLevelTalent extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_AddHighLevelTalent()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
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
		String college = request.getParameter(HighLevelTalentTable.HLT_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");
		int result = 0;
		try
		{
			JSONObject jsonObj = new JSONObject(data);
			HighLevelTalentDao hltDao = new HighLevelTalentDaoImpl();
			// JSONArray jsonArray = new JSONArray(data);
			// int iSize = jsonArray.length();
			// System.out.println("Size:" + iSize);
			// for (int i = 0; i < iSize; i++)
			// {
			// JSONObject jsonObj = jsonArray.getJSONObject(i);
			String hlt_name = jsonObj.getString(HighLevelTalentTable.HLT_NAME);
			String hlt_worknumber = jsonObj.getString(HighLevelTalentTable.HLT_WORKNUMBER);
			String hlt_type = jsonObj.getString(HighLevelTalentTable.HLT_TYPE);
			String hlt_researchdirection = jsonObj.getString(HighLevelTalentTable.HLT_RESEARCHDIRECTION);
			String acquisitiondate = jsonObj.getString(HighLevelTalentTable.HLT_ACQUISITIONDATE);
			Date hlt_acquisitiondate = Date.valueOf("1800-1-1");
			if (acquisitiondate != null && !"".equals(acquisitiondate))
				hlt_acquisitiondate = Date.valueOf(acquisitiondate);
			int hlt_serialnumber = serialnumber;
			String hlt_college = college;
			String hlt_comments = "";
			int hlt_isnull = 0;
			if ("".equals(hlt_name) || "".equals(hlt_worknumber) || "".equals(hlt_type)
					|| "".equals(hlt_researchdirection) || "".equals(acquisitiondate))
				hlt_isnull = 1;
			if ("".equals(hlt_name) && "".equals(hlt_worknumber) && "".equals(hlt_type)
					&& "".equals(hlt_researchdirection) && "".equals(acquisitiondate))
			{
				result = -1;
				out.print(result);
				return;
			}
			HighLevelTalent highLevelTalent = new HighLevelTalent(0, hlt_name, hlt_worknumber, hlt_type,
					hlt_researchdirection, hlt_acquisitiondate, hlt_serialnumber, null, hlt_college, hlt_comments, hlt_isnull);

			result = hltDao.addHighLevelTalent(highLevelTalent);
			// }
		} catch (JSONException e)
		{
			e.printStackTrace();
		}

		out.print(result);
	}

}
