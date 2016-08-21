package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.UndergraStuPartiSocialPracDao;
import cn.edu.xmu.daoimpl.UndergraStuPartiSocialPracDaoImpl;
import cn.edu.xmu.entity.UndergraStuPartiSocialPrac;
import cn.edu.xmu.entity.UndergraStuPartiSocialPrac;
import cn.edu.xmu.table.UndergraStuPartiSocialPracTable;
import cn.edu.xmu.table.UndergraStuPartiSocialPracTable;

/**
 * 
 * @author xiaoping 附表5-4-3 本科生参与暑期社会实践情况 date 2015-7-10
 *
 */
@WebServlet("/Sec_AddUndergraStuPartiSocialPrac")
public class Sec_AddUndergraStuPartiSocialPrac extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_AddUndergraStuPartiSocialPrac()
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
		String college = request.getParameter(UndergraStuPartiSocialPracTable.USPSP_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");
		int result = 0;
		try
		{
			JSONObject jsonObj = new JSONObject(data);
			UndergraStuPartiSocialPracDao uspspDao = new UndergraStuPartiSocialPracDaoImpl();
			String uspsp_department = jsonObj.getString(UndergraStuPartiSocialPracTable.USPSP_DEPARTMENT);
			String temp_focuspracnum = jsonObj.getString(UndergraStuPartiSocialPracTable.USPSP_FOCUSPRACNUM);
			String temp_scatterpracnum = jsonObj.getString(UndergraStuPartiSocialPracTable.USPSP_SCATTERPRACNUM);
			int uspsp_subtotal = 0;
			int uspsp_serialnumber = serialnumber;
			String uspsp_college = college;
			String uspsp_comments = "";
			int uspsp_isnull = 0;
			if ("".equals(uspsp_department) || "".equals(temp_focuspracnum) || "".equals(temp_scatterpracnum))
				uspsp_isnull = 1;
			if ("".equals(uspsp_department) && "".equals(temp_focuspracnum) && "".equals(temp_scatterpracnum))
			{
				out.println(-1);
				return;
			}
			int uspsp_focuspracnum = -9;
			int uspsp_scatterpracnum = -9;
			if (!"".equals(temp_focuspracnum))
			{
				uspsp_focuspracnum = Integer.parseInt(temp_focuspracnum);
				uspsp_subtotal += uspsp_focuspracnum;
			}
			if (!"".equals(temp_scatterpracnum))
			{
				uspsp_scatterpracnum = Integer.parseInt(temp_scatterpracnum);
				uspsp_subtotal += uspsp_scatterpracnum;
			}
			UndergraStuPartiSocialPrac undergraStuPartiSocialPrac = new UndergraStuPartiSocialPrac(0, uspsp_department,
					uspsp_focuspracnum, uspsp_scatterpracnum, uspsp_subtotal, uspsp_serialnumber, null, uspsp_college,
					uspsp_comments, uspsp_isnull);

			result = uspspDao.addUndergraStuPartiSocialPrac(undergraStuPartiSocialPrac);
			if (result == 1)
			{
				Map queryParams = new HashMap();
				Map notEqualParams = new HashMap();
				// 获得合计记录
				queryParams.put(UndergraStuPartiSocialPracTable.USPSP_COLLEGE, college);
				notEqualParams.put(UndergraStuPartiSocialPracTable.USPSP_DEPARTMENT, "合计");
				// 获得所有记录
				List<UndergraStuPartiSocialPrac> sums = uspspDao.getEqualUndergraStuPartiSocialPrac(queryParams,
						notEqualParams);
				queryParams.put(UndergraStuPartiSocialPracTable.USPSP_DEPARTMENT, "合计");
				List<UndergraStuPartiSocialPrac> totals = uspspDao.getEqualUndergraStuPartiSocialPrac(queryParams,
						null);
				int focuspracnum = 0, scatterpracnum = 0, subtotal = 0;
				if (sums != null && sums.size() > 0)
				{
					for (UndergraStuPartiSocialPrac uspsp : sums)
					{
						focuspracnum += (uspsp.getUspsp_focuspracnum() < 0 ? 0 : uspsp.getUspsp_focuspracnum());
						scatterpracnum += (uspsp.getUspsp_scatterpracnum() < 0 ? 0 : uspsp.getUspsp_scatterpracnum());
						subtotal += uspsp.getUspsp_subtotal();
					}
				}
				if (totals != null && totals.size() > 0)
				{
					UndergraStuPartiSocialPrac total = totals.get(0);
					queryParams.clear();
					queryParams.put(UndergraStuPartiSocialPracTable.USPSP_FOCUSPRACNUM, focuspracnum + "");
					queryParams.put(UndergraStuPartiSocialPracTable.USPSP_SCATTERPRACNUM, scatterpracnum + "");
					queryParams.put(UndergraStuPartiSocialPracTable.USPSP_SUBTOTAL, subtotal + "");
					queryParams.put(UndergraStuPartiSocialPracTable.USPSP_SERIALNUMBER,
							(uspspDao.getMaxSerialNum() + 1) + "");
					queryParams.put(UndergraStuPartiSocialPracTable.USPSP_ISNULL, "0");
					result = uspspDao.alterUndergraStuPartiSocialPrac(queryParams, total.getUspsp_id() + "");
				} else
				{
					undergraStuPartiSocialPrac = new UndergraStuPartiSocialPrac(0, "合计", focuspracnum, scatterpracnum,
							subtotal, uspsp_serialnumber, null, uspsp_college, "", 0);
					result = uspspDao.addUndergraStuPartiSocialPrac(undergraStuPartiSocialPrac);
				}
			}
		} catch (JSONException e)
		{
			e.printStackTrace();
			result = -1;
		}
		out.print(result);
	}

}
