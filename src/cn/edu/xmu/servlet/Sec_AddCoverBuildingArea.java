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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.edu.xmu.dao.CoverBuildingAreaDao;
import cn.edu.xmu.daoimpl.CoverBuildingAreaDaoImpl;
import cn.edu.xmu.entity.CoverBuildingArea;
import cn.edu.xmu.table.CoverBuildingAreaTable;

/**
 * 
 * @author xiaoping 数据报表2-1 占地与建筑面积(时点) date 2015-6-30
 *
 */
@WebServlet("/AddCoverBuildingArea")
public class Sec_AddCoverBuildingArea extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_AddCoverBuildingArea()
	{
		super();
	}

	/**
	 * @see HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		this.doPost(request, response);
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		PrintWriter out = response.getWriter();

		String data = request.getParameter("rowdata");
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length()-1);
		System.out.println(data);
		int serialnumber = Integer.parseInt(request.getParameter("serialnumber"));
		// 解码
		String college = request.getParameter(CoverBuildingAreaTable.CBA_COLLEGE);
		college = URLDecoder.decode(college, "UTF-8");
		int result = 0;
		try
		{
			JSONObject jsonObj = new JSONObject(data);

			CoverBuildingAreaDao cbaDao = new CoverBuildingAreaDaoImpl();
			String statisticaltime = jsonObj.getString(CoverBuildingAreaTable.CBA_STATISTICALTIME);
			Date cba_statisticaltime = Date.valueOf("1800-1-1");
			if(statisticaltime!=null && !"".equals(statisticaltime))
				cba_statisticaltime = Date.valueOf(statisticaltime);
			String cba_fillschool = jsonObj.getString(CoverBuildingAreaTable.CBA_FILLSCHOOL);
			String totalcoverarea = jsonObj.getString(CoverBuildingAreaTable.CBA_TOTALCOVERAREA);
			String propertycov = jsonObj.getString(CoverBuildingAreaTable.CBA_PROPERTYCOV);
			String propertyafforestcov = jsonObj.getString(CoverBuildingAreaTable.CBA_PROPERTYAFFORESTCOV);
			String nonpropertycov = jsonObj.getString(CoverBuildingAreaTable.CBA_NONPROPERTYCOV);
			String nonpropertyafforestcov = jsonObj.getString(CoverBuildingAreaTable.CBA_NONPROPERTYAFFORESTCOV);
			String nonproindepusecov = jsonObj.getString(CoverBuildingAreaTable.CBA_NONPROINDEPUSECOV);
			String nonprocommonusecov = jsonObj.getString(CoverBuildingAreaTable.CBA_NONPROCOMMONUSECOV);
			String totalbuildingarea = jsonObj.getString(CoverBuildingAreaTable.CBA_TOTALBUILDINGAREA);
			String propertybui = jsonObj.getString(CoverBuildingAreaTable.CBA_PROPERTYBUI);
			String nonpropertybui = jsonObj.getString(CoverBuildingAreaTable.CBA_NONPROPERTYBUI);
			String nonproindepusebui = jsonObj.getString(CoverBuildingAreaTable.CBA_NONPROINDEPUSEBUI);
			String nonprocommonusebui = jsonObj.getString(CoverBuildingAreaTable.CBA_NONPROCOMMONUSEBUI);
			float cba_totalcoverarea = -9;
			float cba_propertycov = -9;
			float cba_propertyafforestcov = -9;
			float cba_nonpropertycov = -9;
			float cba_nonpropertyafforestcov = -9;
			float cba_nonproindepusecov = -9;
			float cba_nonprocommonusecov = -9;
			float cba_totalbuildingarea = -9;
			float cba_propertybui = -9;
			float cba_nonpropertybui = -9;
			float cba_nonproindepusebui = -9;
			float cba_nonprocommonusebui = -9;
			if(!"".equals(totalcoverarea))
				cba_totalcoverarea = Float.parseFloat(totalcoverarea);
			if(!"".equals(propertycov))
				cba_propertycov = Float.parseFloat(propertycov);
			if(!"".equals(propertyafforestcov))
				cba_propertyafforestcov = Float.parseFloat(propertyafforestcov);
			if(!"".equals(nonpropertycov))
				cba_nonpropertycov = Float.parseFloat(nonpropertycov);
			if(!"".equals(nonpropertyafforestcov))
				cba_nonpropertyafforestcov = Float.parseFloat(nonpropertyafforestcov);
			if(!"".equals(nonproindepusecov))
				cba_nonproindepusecov = Float.parseFloat(nonproindepusecov);
			if(!"".equals(nonprocommonusecov))
				cba_nonprocommonusecov = Float.parseFloat(nonprocommonusecov);
			if(!"".equals(totalbuildingarea))
				cba_totalbuildingarea = Float.parseFloat(totalbuildingarea);
			if(!"".equals(propertybui))
				cba_propertybui = Float.parseFloat(propertybui);
			if(!"".equals(nonpropertybui))
				cba_nonpropertybui = Float.parseFloat(nonpropertybui);
			if(!"".equals(nonproindepusebui))
				cba_nonproindepusebui = Float.parseFloat(nonproindepusebui);
			if(!"".equals(nonprocommonusebui))
				cba_nonprocommonusebui = Float.parseFloat(nonprocommonusebui);
			int cba_isnull = 0;
			if ("".equals(statisticaltime) || "".equals(cba_fillschool) || "".equals(totalcoverarea)
					|| "".equals(propertycov) || "".equals(propertyafforestcov) || "".equals(nonpropertycov)
					|| "".equals(nonpropertyafforestcov) || "".equals(nonproindepusecov) || "".equals(nonprocommonusecov)
					|| "".equals(totalbuildingarea) || "".equals(propertybui) || "".equals(nonpropertybui)
					|| "".equals(nonproindepusebui) || "".equals(nonprocommonusebui))
				cba_isnull = 1;
			if ("".equals(statisticaltime) && "".equals(cba_fillschool) && "".equals(totalcoverarea)
					&& "".equals(propertycov) && "".equals(propertyafforestcov) && "".equals(nonpropertycov)
					&& "".equals(nonpropertyafforestcov) && "".equals(nonproindepusecov) && "".equals(nonprocommonusecov)
					&& "".equals(totalbuildingarea) && "".equals(propertybui) && "".equals(nonpropertybui)
					&& "".equals(nonproindepusebui) && "".equals(nonprocommonusebui))
			{
				result = -1;
				out.println(result);
				return;
			}
			int cba_serialnumber = serialnumber;
			String cba_college = college;
			String cba_comments = "";

			CoverBuildingArea coverBuildingArea = new CoverBuildingArea(cba_statisticaltime, cba_fillschool,
					cba_totalcoverarea, cba_propertycov, cba_propertyafforestcov, cba_nonpropertycov,
					cba_nonpropertyafforestcov, cba_nonproindepusecov, cba_nonprocommonusecov, cba_totalbuildingarea,
					cba_propertybui, cba_nonpropertybui, cba_nonproindepusebui, cba_nonprocommonusebui,
					cba_serialnumber, null, cba_college, cba_comments, cba_isnull);

			result = cbaDao.addCoverBuildingArea(coverBuildingArea);
		} catch (JSONException e)
		{
			e.printStackTrace();
		}

		out.print(result);
	}
}
