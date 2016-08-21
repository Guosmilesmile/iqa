package cn.edu.xmu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.edu.xmu.dao.UndergraStuInlandCommuDao;
import cn.edu.xmu.daoimpl.UndergraStuInlandCommuDaoImpl;
import cn.edu.xmu.entity.UndergraStuInlandCommu;
import cn.edu.xmu.table.UndergraStuInlandCommuTable;

/**
 * 
 * @author xiaoping 附表6-2-2-2本科生境内交流情况（学年） date 2015-7-9
 *
 */
@WebServlet("/Sec_DeleteUndergraStuInlandCommu")
public class Sec_DeleteUndergraStuInlandCommu extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_DeleteUndergraStuInlandCommu()
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
		String usic_id = request.getParameter("usicids");
		
		String usicid[] = usic_id.split(",");
		
		UndergraStuInlandCommuDao usicDao = new UndergraStuInlandCommuDaoImpl();
		boolean result = false;
		try {
			result = usicDao.batchDelete(usicid);
			if(result)
			{
				result = false;
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
					if(usicDao.alterUndergraStuInlandCommu(queryParams, total.getUsic_id() + "") == 1)
						result = true;
				}else {
					UndergraStuInlandCommu undergraStuInlandCommu = new UndergraStuInlandCommu(0, "合计", outNumber, inNumber, 1, null, college, "",0);
					if(usicDao.addUndergraStuInlandCommu(undergraStuInlandCommu) == 1)
						result = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("删除纪录的结果"+result);
		
		out.print(result);
	}
}
