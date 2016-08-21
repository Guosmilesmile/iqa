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

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.HighLevelResearchTeamDao;
import cn.edu.xmu.daoimpl.HighLevelResearchTeamDaoImpl;
import cn.edu.xmu.entity.HighLevelResearchTeam;
import cn.edu.xmu.table.HighLevelResearchTeamTable;

/**
 * 
 * @author xiaoping 表3-4-2 高层次研究团队 (时点) date 2015-7-9
 *
 */
@WebServlet("/Sec_AddHighLevelResearchTeam")
public class Sec_AddHighLevelResearchTeam extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_AddHighLevelResearchTeam()
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
		String college = request.getParameter(HighLevelResearchTeamTable.HLRT_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");
		int result = 0;
		try
		{
			JSONObject jsonObj = new JSONObject(data);
			HighLevelResearchTeamDao hlrtDao = new HighLevelResearchTeamDaoImpl();
			String hlrt_researchdirection = jsonObj.getString(HighLevelResearchTeamTable.HLRT_RESEARCHDIRECTION);
			String hlrt_head = jsonObj.getString(HighLevelResearchTeamTable.HLRT_HEAD);
			String hlrt_headnumber = jsonObj.getString(HighLevelResearchTeamTable.HLRT_HEADNUMBER);
			String hlrt_type = jsonObj.getString(HighLevelResearchTeamTable.HLRT_TYPE);
			String acquisitiondate = jsonObj.getString(HighLevelResearchTeamTable.HLRT_ACQUISITIONDATE);
			int hlrt_serialnumber = serialnumber;
			String hlrt_college = college;
			String hlrt_comments = "";
			int hlrt_isnull = 0;
			if ("".equals(hlrt_researchdirection) || "".equals(hlrt_head) || "".equals(hlrt_headnumber)
					|| "".equals(hlrt_type) || "".equals(acquisitiondate))
				hlrt_isnull = 1;
			if ("".equals(hlrt_researchdirection) && "".equals(hlrt_head) && "".equals(hlrt_headnumber)
					&& "".equals(hlrt_type) && "".equals(acquisitiondate))
			{
				out.print(-1);
				return;
			}
			Date hlrt_acquisitiondate = Date.valueOf("1800-1-1");
			if(!"".equals(acquisitiondate))
				hlrt_acquisitiondate = Date.valueOf(acquisitiondate);
			HighLevelResearchTeam highLevelResearchTeam = new HighLevelResearchTeam(0, hlrt_researchdirection,
					hlrt_head, hlrt_headnumber, hlrt_type, hlrt_acquisitiondate, hlrt_serialnumber, null, hlrt_college,
					hlrt_comments, hlrt_isnull);

			result = hlrtDao.addHighLevelResearchTeam(highLevelResearchTeam);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}

		out.print(result);
	}
}
