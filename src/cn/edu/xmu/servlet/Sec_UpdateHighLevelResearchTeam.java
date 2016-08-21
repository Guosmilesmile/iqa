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

import cn.edu.xmu.dao.HighLevelResearchTeamDao;
import cn.edu.xmu.daoimpl.HighLevelResearchTeamDaoImpl;
import cn.edu.xmu.table.HighLevelResearchTeamTable;

/**
 * 
 * @author xiaoping 表3-4-2 高层次研究团队 (时点) date 2015-7-9
 *
 */
@WebServlet("/Sec_UpdateHighLevelResearchTeam")
public class Sec_UpdateHighLevelResearchTeam extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_UpdateHighLevelResearchTeam()
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
			HighLevelResearchTeamDao highLevelResearchTeamDao = new HighLevelResearchTeamDaoImpl();
			if (patter != null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++)
				{
					String hlrt_id = jsons.getJSONObject(i).getString(HighLevelResearchTeamTable.HLRT_ID);
					String hlrt_comments = jsons.getJSONObject(i).getString(HighLevelResearchTeamTable.HLRT_COMMENTS);

					Map<String, String> params = new HashMap<String, String>();
					params.put(HighLevelResearchTeamTable.HLRT_ID, hlrt_id);
					params.put(HighLevelResearchTeamTable.HLRT_COMMENTS, hlrt_comments);
					int result = highLevelResearchTeamDao.alterHighLevelResearchTeam(params, hlrt_id);

					out.print(result);
				}
			} else
			{
				JSONObject json = new JSONObject(data);
				String hlrt_id = json.getString(HighLevelResearchTeamTable.HLRT_ID);
				String hlrt_researchdirection = json.getString(HighLevelResearchTeamTable.HLRT_RESEARCHDIRECTION);
				String hlrt_head = json.getString(HighLevelResearchTeamTable.HLRT_HEAD);
				String hlrt_headnumber = json.getString(HighLevelResearchTeamTable.HLRT_HEADNUMBER);
				String hlrt_type = json.getString(HighLevelResearchTeamTable.HLRT_TYPE);
				String hlrt_acquisitiondate = json.getString(HighLevelResearchTeamTable.HLRT_ACQUISITIONDATE);
				String hlrt_comments = json.getString(HighLevelResearchTeamTable.HLRT_COMMENTS);
				String hlrt_isnull = "0";
				if ("".equals(hlrt_researchdirection) || "".equals(hlrt_head) || "".equals(hlrt_headnumber)
						|| "".equals(hlrt_type) || "".equals(hlrt_acquisitiondate))
					hlrt_isnull = "1";
				if("".equals(hlrt_acquisitiondate))
					hlrt_acquisitiondate = "1800-1-1";
				Map<String, String> params = new HashMap<String, String>();
				params.put(HighLevelResearchTeamTable.HLRT_ID, hlrt_id);
				params.put(HighLevelResearchTeamTable.HLRT_RESEARCHDIRECTION, hlrt_researchdirection);
				params.put(HighLevelResearchTeamTable.HLRT_HEAD, hlrt_head);
				params.put(HighLevelResearchTeamTable.HLRT_HEADNUMBER, hlrt_headnumber);
				params.put(HighLevelResearchTeamTable.HLRT_TYPE, hlrt_type);
				params.put(HighLevelResearchTeamTable.HLRT_ACQUISITIONDATE, hlrt_acquisitiondate);
				params.put(HighLevelResearchTeamTable.HLRT_COMMENTS, hlrt_comments);
				params.put(HighLevelResearchTeamTable.HLRT_ISNULL, hlrt_isnull);
				int result = highLevelResearchTeamDao.alterHighLevelResearchTeam(params, hlrt_id);

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
