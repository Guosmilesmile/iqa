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

import cn.edu.xmu.dao.HighLevelTalentDao;
import cn.edu.xmu.daoimpl.HighLevelTalentDaoImpl;
import cn.edu.xmu.table.HighLevelTalentTable;

/**
 * 
 * @author xiaoping 表3-4-1 高层次人才(时点) date 2015-7-3
 *
 */
@WebServlet("/Sec_UpdateHighLevelTalent")
public class Sec_UpdateHighLevelTalent extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_UpdateHighLevelTalent()
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
			HighLevelTalentDao highLevelTalentDao = new HighLevelTalentDaoImpl();
			if (patter != null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++)
				{
					String hlt_id = jsons.getJSONObject(i).getString(HighLevelTalentTable.HLT_ID);
					String hlt_comments = jsons.getJSONObject(i).getString(HighLevelTalentTable.HLT_COMMENTS);
					
					Map<String, String> params = new HashMap<String, String>();
					params.put(HighLevelTalentTable.HLT_ID, hlt_id);
					params.put(HighLevelTalentTable.HLT_COMMENTS, hlt_comments);
					int result = highLevelTalentDao.alterHighLevelTalent(params, hlt_id);

					out.print(result);
				}
			} else
			{
				JSONObject json = new JSONObject(data);
				String hlt_id = json.getString(HighLevelTalentTable.HLT_ID);
				String hlt_name = json.getString(HighLevelTalentTable.HLT_NAME);
				String hlt_worknumber = json.getString(HighLevelTalentTable.HLT_WORKNUMBER);
				String hlt_type = json.getString(HighLevelTalentTable.HLT_TYPE);
				String hlt_researchdirection = json.getString(HighLevelTalentTable.HLT_RESEARCHDIRECTION);
				String hlt_acquisitiondate = json.getString(HighLevelTalentTable.HLT_ACQUISITIONDATE);
				String hlt_comments = json.getString(HighLevelTalentTable.HLT_COMMENTS);
				int hlt_isnull = 0;
				if ("".equals(hlt_name) || "".equals(hlt_worknumber) || "".equals(hlt_type)
						|| "".equals(hlt_researchdirection) || "".equals(hlt_acquisitiondate))
					hlt_isnull = 1;

				if(hlt_acquisitiondate.equals(""))
					hlt_acquisitiondate = "1800-1-1";
				Map<String, String> params = new HashMap<String, String>();
				params.put(HighLevelTalentTable.HLT_ID, hlt_id);
				params.put(HighLevelTalentTable.HLT_NAME, hlt_name);
				params.put(HighLevelTalentTable.HLT_WORKNUMBER, hlt_worknumber);
				params.put(HighLevelTalentTable.HLT_TYPE, hlt_type);
				params.put(HighLevelTalentTable.HLT_RESEARCHDIRECTION, hlt_researchdirection);
				params.put(HighLevelTalentTable.HLT_ACQUISITIONDATE, hlt_acquisitiondate);
				params.put(HighLevelTalentTable.HLT_COMMENTS, hlt_comments);
				params.put(HighLevelTalentTable.HLT_ISNULL, hlt_isnull+"");
				int result = highLevelTalentDao.alterHighLevelTalent(params, hlt_id);

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
