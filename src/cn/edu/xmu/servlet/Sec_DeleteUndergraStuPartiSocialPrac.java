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

import cn.edu.xmu.dao.UndergraStuPartiSocialPracDao;
import cn.edu.xmu.daoimpl.UndergraStuPartiSocialPracDaoImpl;
import cn.edu.xmu.entity.UndergraStuPartiSocialPrac;
import cn.edu.xmu.table.UndergraStuPartiSocialPracTable;

/**
 * 
 * @author xiaoping 附表5-4-3 本科生参与暑期社会实践情况 date 2015-7-10
 *
 */
@WebServlet("/Sec_DeleteUndergraStuPartiSocialPrac")
public class Sec_DeleteUndergraStuPartiSocialPrac extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Sec_DeleteUndergraStuPartiSocialPrac()
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
		String uspsp_id = request.getParameter("uspspids");
		
		String uspspid[] = uspsp_id.split(",");
		
		UndergraStuPartiSocialPracDao uspspDao = new UndergraStuPartiSocialPracDaoImpl();
		boolean result = false;
		try {
			result = uspspDao.batchDelete(uspspid);
			if(result)
			{
				result = false;
				// 解码
				String college = request.getParameter(UndergraStuPartiSocialPracTable.USPSP_COLLEGE);
				college = URLDecoder.decode(college, "UTF-8");
				Map queryParams = new HashMap();
				Map notEqualParams = new HashMap();
				//获得合计记录
				queryParams.put(UndergraStuPartiSocialPracTable.USPSP_COLLEGE, college);
				notEqualParams.put(UndergraStuPartiSocialPracTable.USPSP_DEPARTMENT, "合计");
				//获得所有记录
				List<UndergraStuPartiSocialPrac> sums = uspspDao.getEqualUndergraStuPartiSocialPrac(queryParams, notEqualParams);
				queryParams.put(UndergraStuPartiSocialPracTable.USPSP_DEPARTMENT, "合计");
				List<UndergraStuPartiSocialPrac> totals = uspspDao.getEqualUndergraStuPartiSocialPrac(queryParams, null);
				int focuspracnum = 0, scatterpracnum = 0,subtotal = 0;
				if(sums != null && sums.size()>0)
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
					queryParams.put(UndergraStuPartiSocialPracTable.USPSP_FOCUSPRACNUM, focuspracnum+"");
					queryParams.put(UndergraStuPartiSocialPracTable.USPSP_SCATTERPRACNUM, scatterpracnum+"");
					queryParams.put(UndergraStuPartiSocialPracTable.USPSP_SUBTOTAL, subtotal+"");
					queryParams.put(UndergraStuPartiSocialPracTable.USPSP_SERIALNUMBER, (uspspDao.getMaxSerialNum()+1)+"");
					queryParams.put(UndergraStuPartiSocialPracTable.USPSP_ISNULL, "0");
					if(uspspDao.alterUndergraStuPartiSocialPrac(queryParams, total.getUspsp_id() + "") == 1)
						result = true;
				}else {
					UndergraStuPartiSocialPrac undergraStuPartiSocialPrac = new UndergraStuPartiSocialPrac(0, "合计", focuspracnum, scatterpracnum, subtotal, 1, null, college, "",0);
					if(uspspDao.addUndergraStuPartiSocialPrac(undergraStuPartiSocialPrac) == 1)
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
