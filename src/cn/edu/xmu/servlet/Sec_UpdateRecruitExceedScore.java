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

import cn.edu.xmu.dao.RecruitExceedScoreDao;
import cn.edu.xmu.daoimpl.RecruitExceedScoreDaoImpl;
import cn.edu.xmu.table.RecruitExceedScoreTable;

/**
 * 
 * @author xiaoping 附表6-1-5-1厦门大学在全国各省（市、自治区）招生出档分数情况（时点） date 2015-7-11
 *
 */
@WebServlet("/Sec_UpdateRecruitExceedScore")
public class Sec_UpdateRecruitExceedScore extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public Sec_UpdateRecruitExceedScore()
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
		String patter = request.getParameter("patter");
		// 解码
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try
		{
			RecruitExceedScoreDao resDao = new RecruitExceedScoreDaoImpl();
			if (patter != null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++)
				{
					String res_id = jsons.getJSONObject(i).getString(RecruitExceedScoreTable.RES_ID);
					String res_comments = jsons.getJSONObject(i).getString(RecruitExceedScoreTable.RES_COMMENTS);
					
					Map<String, String> params = new HashMap<String, String>();
					params.put(RecruitExceedScoreTable.RES_ID, res_id);
					params.put(RecruitExceedScoreTable.RES_COMMENTS, res_comments);
					int result = resDao.alterRecruitExceedScore(params, res_id);

					out.print(result);
				}
			} else
			{
				JSONObject json = new JSONObject(data);
				String res_id = json.getString(RecruitExceedScoreTable.RES_ID);
				String res_year = json.getString(RecruitExceedScoreTable.RES_YEAR);
				String res_libexctwentyproportion = json.getString(RecruitExceedScoreTable.RES_LIBEXCTWENTYPROPORTION);
				String res_libexcthirtyproportion = json.getString(RecruitExceedScoreTable.RES_LIBEXCTHIRTYPROPORTION);
				String res_libexclineave = json.getString(RecruitExceedScoreTable.RES_LIBEXCLINEAVE);
				String res_sciexcthirtyproportion = json.getString(RecruitExceedScoreTable.RES_SCIEXCTHIRTYPROPORTION);
				String res_sciexcfortyproportion = json.getString(RecruitExceedScoreTable.RES_SCIEXCFORTYPROPORTION);
				String res_sciexclineave = json.getString(RecruitExceedScoreTable.RES_SCIEXCLINEAVE);
				String res_comments = json.getString(RecruitExceedScoreTable.RES_COMMENTS);
				String res_isnulll = "0";
				if ("".equals(res_year) || "".equals(res_libexctwentyproportion) || "".equals(res_libexcthirtyproportion)
						|| "".equals(res_libexclineave) || "".equals(res_sciexcthirtyproportion) || "".equals(res_sciexcfortyproportion)
						|| "".equals(res_sciexclineave))
					res_isnulll = "1";
				if("".equals(res_libexctwentyproportion))
					res_libexctwentyproportion = "-9";
				if("".equals(res_libexcthirtyproportion))
					res_libexcthirtyproportion = "-9";
				if("".equals(res_libexclineave))
					res_libexclineave = "-9";
				if("".equals(res_sciexcthirtyproportion))
					res_sciexcthirtyproportion = "-9";
				if("".equals(res_sciexcfortyproportion))
					res_sciexcfortyproportion = "-9";
				if("".equals(res_sciexclineave))
					res_sciexclineave = "-9";
				Map<String, String> params = new HashMap<String, String>();
				params.put(RecruitExceedScoreTable.RES_ID, res_id);
				params.put(RecruitExceedScoreTable.RES_YEAR, res_year);
				params.put(RecruitExceedScoreTable.RES_LIBEXCTWENTYPROPORTION, res_libexctwentyproportion);
				params.put(RecruitExceedScoreTable.RES_LIBEXCTHIRTYPROPORTION, res_libexcthirtyproportion);
				params.put(RecruitExceedScoreTable.RES_LIBEXCLINEAVE, res_libexclineave);
				params.put(RecruitExceedScoreTable.RES_SCIEXCTHIRTYPROPORTION, res_sciexcthirtyproportion);
				params.put(RecruitExceedScoreTable.RES_SCIEXCFORTYPROPORTION, res_sciexcfortyproportion);
				params.put(RecruitExceedScoreTable.RES_SCIEXCLINEAVE, res_sciexclineave);
				params.put(RecruitExceedScoreTable.RES_COMMENTS, res_comments);
				params.put(RecruitExceedScoreTable.RES_ISNULL, res_isnulll);
				int result = resDao.alterRecruitExceedScore(params, res_id);
				out.print(result);
			}
		} catch (JSONException e)
		{
			e.printStackTrace();
		} finally
		{
			out.close();
		}
	}
}
