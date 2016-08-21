package cn.edu.xmu.servlet;

import java.util.List;
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
@WebServlet("/Sec_AddUndergraStuInlandCommu")
public class Sec_AddUndergraStuInlandCommu extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_AddUndergraStuInlandCommu()
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
		String college = request.getParameter(UndergraStuInlandCommuTable.USIC_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");
		int result = 0;
		try
		{
			JSONObject jsonObj = new JSONObject(data);
			UndergraStuInlandCommuDao usicDao = new UndergraStuInlandCommuDaoImpl();
			String usic_institute = jsonObj.getString(UndergraStuInlandCommuTable.USIC_INSTITUTE);
			String outnumber = jsonObj.getString(UndergraStuInlandCommuTable.USIC_OUTNUMBER);
			String innumber = jsonObj.getString(UndergraStuInlandCommuTable.USIC_INNUMBER);
			int usic_serialnumber = serialnumber;
			String usic_college = college;
			String usic_comments = "";
			int usic_isnull = 0;
			if("".equals(usic_institute) ||"".equals(outnumber) ||"".equals(innumber))
				usic_isnull = 1;
			if("".equals(usic_institute) &&"".equals(outnumber) &&"".equals(innumber))
			{
				out.print(-1);
				return;
			}
			int usic_outnumber = -9;
			int usic_innumber = -9;
			if(!"".equals(outnumber))
				usic_outnumber = Integer.parseInt(outnumber);
			if(!"".equals(innumber))
				usic_innumber = Integer.parseInt(innumber);
			UndergraStuInlandCommu undergraStuInlandCommu = new UndergraStuInlandCommu(0, usic_institute,
					usic_outnumber, usic_innumber, usic_serialnumber, null, usic_college, usic_comments,usic_isnull);

			result = usicDao.addUndergraStuInlandCommu(undergraStuInlandCommu);
			if(result == 1)
			{
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
					undergraStuInlandCommu = new UndergraStuInlandCommu(0, "合计", outNumber, inNumber, usic_serialnumber, null, usic_college, usic_comments,0);
					result = usicDao.addUndergraStuInlandCommu(undergraStuInlandCommu);
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
