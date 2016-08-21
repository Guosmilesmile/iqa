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

import cn.edu.xmu.dao.RecruitExceedScoreDao;
import cn.edu.xmu.daoimpl.RecruitExceedScoreDaoImpl;
import cn.edu.xmu.entity.RecruitExceedScore;
import cn.edu.xmu.table.RecruitExceedScoreTable;

/**
 * 
 * @author xiaoping 附表6-1-5-1厦门大学在全国各省（市、自治区）招生出档分数情况（时点） date 2015-7-11
 *
 */
@WebServlet("/Sec_AddRecruitExceedScore")
public class Sec_AddRecruitExceedScore extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_AddRecruitExceedScore()
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
		String college = request.getParameter(RecruitExceedScoreTable.RES_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");
		int result = 0;
		try
		{
			JSONObject jsonObj = new JSONObject(data);
			RecruitExceedScoreDao resDao = new RecruitExceedScoreDaoImpl();
			String res_year = jsonObj.getString(RecruitExceedScoreTable.RES_YEAR);
			String libexctwentyproportion = jsonObj.getString(RecruitExceedScoreTable.RES_LIBEXCTWENTYPROPORTION);
			String libexcthirtyproportion = jsonObj.getString(RecruitExceedScoreTable.RES_LIBEXCTHIRTYPROPORTION);
			String libexclineave = jsonObj.getString(RecruitExceedScoreTable.RES_LIBEXCLINEAVE);
			String sciexcthirtyproportion = jsonObj.getString(RecruitExceedScoreTable.RES_SCIEXCTHIRTYPROPORTION);
			String sciexcfortyproportion = jsonObj.getString(RecruitExceedScoreTable.RES_SCIEXCFORTYPROPORTION);
			String sciexclineave = jsonObj.getString(RecruitExceedScoreTable.RES_SCIEXCLINEAVE);
			int res_serialnumber = serialnumber;
			String res_college = college;
			String res_comments = "";
			int res_isnulll = 0;
			if ("".equals(res_year) || "".equals(libexctwentyproportion) || "".equals(libexcthirtyproportion)
					|| "".equals(libexclineave) || "".equals(sciexcthirtyproportion) || "".equals(sciexcfortyproportion)
					|| "".equals(sciexclineave))
				res_isnulll = 1;
			if ("".equals(res_year) && "".equals(libexctwentyproportion) && "".equals(libexcthirtyproportion)
					&& "".equals(libexclineave) && "".equals(sciexcthirtyproportion) && "".equals(sciexcfortyproportion)
					&& "".equals(sciexclineave))
			{
				out.print(-1);
				return;
			}
			float res_libexctwentyproportion = -9;
			float res_libexcthirtyproportion = -9;
			float res_libexclineave = -9;
			float res_sciexcthirtyproportion = -9;
			float res_sciexcfortyproportion = -9;
			float res_sciexclineave = -9;
			if(!"".equals(libexctwentyproportion))
				res_libexctwentyproportion = Float.parseFloat(libexctwentyproportion);
			if(!"".equals(libexcthirtyproportion))
				res_libexcthirtyproportion = Float.parseFloat(libexcthirtyproportion);
			if(!"".equals(libexclineave))
				res_libexclineave = Float.parseFloat(libexclineave);
			if(!"".equals(sciexcthirtyproportion))
				res_sciexcthirtyproportion = Float.parseFloat(sciexcthirtyproportion);
			if(!"".equals(sciexcfortyproportion))
				res_sciexcfortyproportion = Float.parseFloat(sciexcfortyproportion);
			if(!"".equals(sciexclineave))
				res_sciexclineave = Float.parseFloat(sciexclineave);
			RecruitExceedScore recruitExceedScore = new RecruitExceedScore(0, res_year, res_libexctwentyproportion,
					res_libexcthirtyproportion, res_libexclineave, res_sciexcthirtyproportion,
					res_sciexcfortyproportion, res_sciexclineave, res_serialnumber, null, res_college, res_comments,
					res_isnulll);

			result = resDao.addRecruitExceedScore(recruitExceedScore);
		} catch (JSONException e)
		{
			e.printStackTrace();
			result = -1;
		}
		out.print(result);
	}
}
