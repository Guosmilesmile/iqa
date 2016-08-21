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

import cn.edu.xmu.dao.CoverBuildingAreaDao;
import cn.edu.xmu.daoimpl.CoverBuildingAreaDaoImpl;
import cn.edu.xmu.table.CoverBuildingAreaTable;

/**
 * 
 * @author xiaoping 数据报表2-1 占地与建筑面积(时点) date 2015-6-30
 *
 */
@WebServlet("/UpdateCoverBuildingArea")
public class Sec_UpdateCoverBuildingArea extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Sec_UpdateCoverBuildingArea()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
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
		String patter = request.getParameter("patter");
		// 解码
		data = URLDecoder.decode(data, "UTF-8");
		data = data.substring(1, data.length() - 1);
		try
		{
			CoverBuildingAreaDao coverBuildingAreaDao = new CoverBuildingAreaDaoImpl();
			if (patter != null && "batch".equals(patter))
			{
				System.out.println("批量更新");
				// 审核部分更新，可以批量
				data = "[" + data + "]";
				JSONArray jsons = new JSONArray(data);
				for (int i = 0; i < jsons.length(); i++)
				{
					String cba_id = jsons.getJSONObject(i).getString(CoverBuildingAreaTable.CBA_ID);
					String cba_comments = jsons.getJSONObject(i).getString(CoverBuildingAreaTable.CBA_COMMENTS);
					
					Map<String, String> params = new HashMap<String, String>();
					params.put(CoverBuildingAreaTable.CBA_ID, cba_id);
					params.put(CoverBuildingAreaTable.CBA_COMMENTS, cba_comments);
					int result = coverBuildingAreaDao.alterCoverBuildingArea(params, cba_id);

					out.print(result);
				}
			} else
			{
				JSONObject json = new JSONObject(data);
				String cba_id = json.getString(CoverBuildingAreaTable.CBA_ID);
				String cba_statisticaltime = json.getString(CoverBuildingAreaTable.CBA_STATISTICALTIME);
				String cba_fillschool = json.getString(CoverBuildingAreaTable.CBA_FILLSCHOOL);
				String cba_totalcoverarea = json.getString(CoverBuildingAreaTable.CBA_TOTALCOVERAREA);
				String cba_propertycov = json.getString(CoverBuildingAreaTable.CBA_PROPERTYCOV);
				String cba_propertyafforestcov = json.getString(CoverBuildingAreaTable.CBA_PROPERTYAFFORESTCOV);
				String cba_nonpropertycov = json.getString(CoverBuildingAreaTable.CBA_NONPROPERTYCOV);
				String cba_nonpropertyafforestcov = json
						.getString(CoverBuildingAreaTable.CBA_NONPROPERTYAFFORESTCOV);
				String cba_nonproindepusecov = json.getString(CoverBuildingAreaTable.CBA_NONPROINDEPUSECOV);
				String cba_nonprocommonusecov = json.getString(CoverBuildingAreaTable.CBA_NONPROCOMMONUSECOV);
				String cba_totalbuildingarea = json.getString(CoverBuildingAreaTable.CBA_TOTALBUILDINGAREA);
				String cba_propertybui = json.getString(CoverBuildingAreaTable.CBA_PROPERTYBUI);
				String cba_nonpropertybui = json.getString(CoverBuildingAreaTable.CBA_NONPROPERTYBUI);
				String cba_nonproindepusebui = json.getString(CoverBuildingAreaTable.CBA_NONPROINDEPUSEBUI);
				String cba_nonprocommonusebui = json.getString(CoverBuildingAreaTable.CBA_NONPROCOMMONUSEBUI);
				String cba_comments = json.getString(CoverBuildingAreaTable.CBA_COMMENTS);
				int cba_isnull = 0;
				if ("".equals(cba_statisticaltime) || "".equals(cba_fillschool) || "".equals(cba_totalcoverarea)
						|| "".equals(cba_propertycov) || "".equals(cba_propertyafforestcov) || "".equals(cba_nonpropertycov)
						|| "".equals(cba_nonpropertyafforestcov) || "".equals(cba_nonproindepusecov) || "".equals(cba_nonprocommonusecov)
						|| "".equals(cba_totalbuildingarea) || "".equals(cba_propertybui) || "".equals(cba_nonpropertybui)
						|| "".equals(cba_nonproindepusebui) || "".equals(cba_nonprocommonusebui))
					cba_isnull = 1;
				if(cba_statisticaltime.equals(""))
					cba_statisticaltime = "1800-1-1";
				if("".equals(cba_totalcoverarea))
					cba_totalcoverarea="-9";
				if("".equals(cba_propertycov))
					cba_propertycov="-9";
				if("".equals(cba_propertyafforestcov))
					cba_propertyafforestcov="-9";
				if("".equals(cba_nonpropertycov))
					cba_nonpropertycov="-9";
				if("".equals(cba_nonpropertyafforestcov))
					cba_nonpropertyafforestcov="-9";
				if("".equals(cba_nonproindepusecov))
					cba_nonproindepusecov="-9";
				if("".equals(cba_nonprocommonusecov))
					cba_nonprocommonusecov="-9";
				if("".equals(cba_totalbuildingarea))
					cba_totalbuildingarea="-9";
				if("".equals(cba_propertybui))
					cba_propertybui="-9";
				if("".equals(cba_nonpropertybui))
					cba_nonpropertybui="-9";
				if("".equals(cba_nonproindepusebui))
					cba_nonproindepusebui="-9";
				if("".equals(cba_nonprocommonusebui))
					cba_nonprocommonusebui="-9";
				Map<String, String> params = new HashMap<String, String>();
				params.put(CoverBuildingAreaTable.CBA_ID, cba_id);
				params.put(CoverBuildingAreaTable.CBA_STATISTICALTIME, cba_statisticaltime);
				params.put(CoverBuildingAreaTable.CBA_FILLSCHOOL, cba_fillschool);
				params.put(CoverBuildingAreaTable.CBA_TOTALCOVERAREA, cba_totalcoverarea);
				params.put(CoverBuildingAreaTable.CBA_PROPERTYCOV, cba_propertycov);
				params.put(CoverBuildingAreaTable.CBA_PROPERTYAFFORESTCOV, cba_propertyafforestcov);
				params.put(CoverBuildingAreaTable.CBA_NONPROPERTYCOV, cba_nonpropertycov);
				params.put(CoverBuildingAreaTable.CBA_NONPROPERTYAFFORESTCOV, cba_nonpropertyafforestcov);
				params.put(CoverBuildingAreaTable.CBA_NONPROINDEPUSECOV, cba_nonproindepusecov);
				params.put(CoverBuildingAreaTable.CBA_NONPROCOMMONUSECOV, cba_nonprocommonusecov);
				params.put(CoverBuildingAreaTable.CBA_TOTALBUILDINGAREA, cba_totalbuildingarea);
				params.put(CoverBuildingAreaTable.CBA_PROPERTYBUI, cba_propertybui);
				params.put(CoverBuildingAreaTable.CBA_NONPROPERTYBUI, cba_nonpropertybui);
				params.put(CoverBuildingAreaTable.CBA_NONPROINDEPUSEBUI, cba_nonproindepusebui);
				params.put(CoverBuildingAreaTable.CBA_NONPROCOMMONUSEBUI, cba_nonprocommonusebui);
				params.put(CoverBuildingAreaTable.CBA_COMMENTS, cba_comments);
				params.put(CoverBuildingAreaTable.CBA_ISNULL, cba_isnull+"");
				int result = coverBuildingAreaDao.alterCoverBuildingArea(params, cba_id);

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
