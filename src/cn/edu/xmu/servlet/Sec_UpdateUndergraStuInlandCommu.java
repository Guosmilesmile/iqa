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

import cn.edu.xmu.dao.UndergraStuInlandCommuDao;
import cn.edu.xmu.daoimpl.UndergraStuInlandCommuDaoImpl;
import cn.edu.xmu.entity.UndergraStuInlandCommu;
import cn.edu.xmu.table.UndergraStuInlandCommuTable;

/**
 * 
 * @author xiaoping 附表6-2-2-3本科生境内交流情况（学年） date 2015-7-9
 *
 */
@WebServlet("/Sec_UpdateUndergraStuInlandCommu")
public class Sec_UpdateUndergraStuInlandCommu extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_UpdateUndergraStuInlandCommu()
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
			UndergraStuInlandCommuDao usicDao = new UndergraStuInlandCommuDaoImpl();
			if (patter != null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++)
				{
					String usic_id = jsons.getJSONObject(i).getString(UndergraStuInlandCommuTable.USIC_ID);
					String usic_comments = jsons.getJSONObject(i).getString(UndergraStuInlandCommuTable.USIC_COMMENTS);
					
					Map<String, String> params = new HashMap<String, String>();
					params.put(UndergraStuInlandCommuTable.USIC_ID, usic_id);
					params.put(UndergraStuInlandCommuTable.USIC_COMMENTS, usic_comments);
					int result = usicDao.alterUndergraStuInlandCommu(params, usic_id);

					out.print(result);
				}
			} else
			{
				JSONObject json = new JSONObject(data);
				String usic_id = json.getString(UndergraStuInlandCommuTable.USIC_ID);
				String usic_institute = json.getString(UndergraStuInlandCommuTable.USIC_INSTITUTE);
				String usic_outnumber = json.getString(UndergraStuInlandCommuTable.USIC_OUTNUMBER);
				String usic_innumber = json.getString(UndergraStuInlandCommuTable.USIC_INNUMBER);
				String usic_comments = json.getString(UndergraStuInlandCommuTable.USIC_COMMENTS);
				String usic_isnull = "0";
				if("".equals(usic_institute) ||"".equals(usic_outnumber) ||"".equals(usic_innumber))
					usic_isnull = "1";
				if("".equals(usic_outnumber))
					usic_outnumber = "-9";
				if("".equals(usic_innumber))
					usic_innumber = "-9";
				Map<String, String> params = new HashMap<String, String>();
				params.put(UndergraStuInlandCommuTable.USIC_ID, usic_id);
				params.put(UndergraStuInlandCommuTable.USIC_INSTITUTE, usic_institute);
				params.put(UndergraStuInlandCommuTable.USIC_OUTNUMBER, usic_outnumber);
				params.put(UndergraStuInlandCommuTable.USIC_INNUMBER, usic_innumber);
				params.put(UndergraStuInlandCommuTable.USIC_COMMENTS, usic_comments);
				params.put(UndergraStuInlandCommuTable.USIC_ISNULL, usic_isnull);
				int result = usicDao.alterUndergraStuInlandCommu(params, usic_id);
				if(result == 1)
				{
					// 解码
					String college = request.getParameter(UndergraStuInlandCommuTable.USIC_COLLEGE);
					college = URLDecoder.decode(college, "UTF-8");
					Map queryParams = new HashMap();
					Map notEqualParams = new HashMap();
					//获得合计记录
					queryParams.put(UndergraStuInlandCommuTable.USIC_COLLEGE, college);
					notEqualParams.put(UndergraStuInlandCommuTable.USIC_INSTITUTE, "合计");
					//获得所有记录
					List<UndergraStuInlandCommu> sums = usicDao.getEqualUndergraStuInlandCommu(queryParams, notEqualParams);
					queryParams.put(UndergraStuInlandCommuTable.USIC_INSTITUTE, "合计");
					List<UndergraStuInlandCommu> totals = usicDao.getEqualUndergraStuInlandCommu(queryParams, null);
					int outNumber = 0, inNumber = 0;
					if(sums != null && sums.size()>0)
					{
						for (UndergraStuInlandCommu usic : sums)
						{
							outNumber+=(usic.getUsic_outnumber()<0?0:usic.getUsic_outnumber());
							inNumber += (usic.getUsic_innumber()<0?0:usic.getUsic_innumber());
						}
					}
					if (totals != null && totals.size() > 0)
					{
						UndergraStuInlandCommu total = totals.get(0);
						queryParams.clear();
						queryParams.put(UndergraStuInlandCommuTable.USIC_OUTNUMBER, outNumber+"");
						queryParams.put(UndergraStuInlandCommuTable.USIC_INNUMBER, inNumber+"");
						queryParams.put(UndergraStuInlandCommuTable.USIC_SERIALNUMBER, (usicDao.getMaxSerialNum()+1)+"");
						queryParams.put(UndergraStuInlandCommuTable.USIC_ISNULL, "0");
						result = usicDao.alterUndergraStuInlandCommu(queryParams, total.getUsic_id() + "");
					}else {
						UndergraStuInlandCommu undergraStuInlandCommu = new UndergraStuInlandCommu(0, "合计", outNumber, inNumber, 1, null, college, usic_comments,0);
						result = usicDao.addUndergraStuInlandCommu(undergraStuInlandCommu);
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
