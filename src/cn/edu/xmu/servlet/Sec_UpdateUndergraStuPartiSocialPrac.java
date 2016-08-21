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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.UndergraStuPartiSocialPracDao;
import cn.edu.xmu.daoimpl.UndergraStuPartiSocialPracDaoImpl;
import cn.edu.xmu.entity.UndergraStuPartiSocialPrac;
import cn.edu.xmu.table.UndergraStuPartiSocialPracTable;
import cn.edu.xmu.table.UndergraStuPartiSocialPracTable;

/**
 * 
 * @author xiaoping 附表5-4-3 本科生参与暑期社会实践情况 date 2015-7-10
 *
 */
@WebServlet("/Sec_UpdateUndergraStuPartiSocialPrac")
public class Sec_UpdateUndergraStuPartiSocialPrac extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_UpdateUndergraStuPartiSocialPrac()
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
			UndergraStuPartiSocialPracDao uspspDao = new UndergraStuPartiSocialPracDaoImpl();
			if (patter != null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++)
				{
					String uspsp_id = jsons.getJSONObject(i).getString(UndergraStuPartiSocialPracTable.USPSP_ID);
					String uspsp_comments = jsons.getJSONObject(i)
							.getString(UndergraStuPartiSocialPracTable.USPSP_COMMENTS);

					Map<String, String> params = new HashMap<String, String>();
					params.put(UndergraStuPartiSocialPracTable.USPSP_ID, uspsp_id);
					params.put(UndergraStuPartiSocialPracTable.USPSP_COMMENTS, uspsp_comments);
					int result = uspspDao.alterUndergraStuPartiSocialPrac(params, uspsp_id);

					out.print(result);
				}
			} else
			{
				JSONObject json = new JSONObject(data);
				String uspsp_id = json.getString(UndergraStuPartiSocialPracTable.USPSP_ID);
				String uspsp_department = json.getString(UndergraStuPartiSocialPracTable.USPSP_DEPARTMENT);
				String uspsp_focuspracnum = json.getString(UndergraStuPartiSocialPracTable.USPSP_FOCUSPRACNUM);
				String uspsp_scatterpracnum = json.getString(UndergraStuPartiSocialPracTable.USPSP_SCATTERPRACNUM);
				int uspsp_subtotal = 0;
				String uspsp_comments = json.getString(UndergraStuPartiSocialPracTable.USPSP_COMMENTS);
				String uspsp_isnull = "0";
				if ("".equals(uspsp_department) || "".equals(uspsp_focuspracnum) || "".equals(uspsp_scatterpracnum))
					uspsp_isnull = "1";
				if (!"".equals(uspsp_focuspracnum))
					uspsp_subtotal += Integer.parseInt(uspsp_focuspracnum);
				else
					uspsp_focuspracnum="-9";
				if (!"".equals(uspsp_scatterpracnum))
					uspsp_subtotal += Integer.parseInt(uspsp_scatterpracnum);
				else
					uspsp_scatterpracnum="-9";
				Map<String, String> params = new HashMap<String, String>();
				params.put(UndergraStuPartiSocialPracTable.USPSP_ID, uspsp_id);
				params.put(UndergraStuPartiSocialPracTable.USPSP_DEPARTMENT, uspsp_department);
				params.put(UndergraStuPartiSocialPracTable.USPSP_FOCUSPRACNUM, uspsp_focuspracnum);
				params.put(UndergraStuPartiSocialPracTable.USPSP_SCATTERPRACNUM, uspsp_scatterpracnum);
				params.put(UndergraStuPartiSocialPracTable.USPSP_SUBTOTAL, uspsp_subtotal+"");
				params.put(UndergraStuPartiSocialPracTable.USPSP_COMMENTS, uspsp_comments);
				params.put(UndergraStuPartiSocialPracTable.USPSP_ISNULL, uspsp_isnull);
				int result = uspspDao.alterUndergraStuPartiSocialPrac(params, uspsp_id);
				if (result == 1)
				{
					// 解码
					String college = request.getParameter(UndergraStuPartiSocialPracTable.USPSP_COLLEGE);
					college = URLDecoder.decode(college, "UTF-8");
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
						UndergraStuPartiSocialPrac undergraStuPartiSocialPrac = new UndergraStuPartiSocialPrac(0, "合计",
								focuspracnum, scatterpracnum, subtotal, 1, null, college, uspsp_comments, 0);
						result = uspspDao.addUndergraStuPartiSocialPrac(undergraStuPartiSocialPrac);
					}
				}
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
