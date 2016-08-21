package cn.edu.xmu.servlet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;

/**
 * 
 * @author xiaoping
 * 3.2.1 教学行政用房情况
 */
@WebServlet("/Statistic_ExportTeachingHouseServlet")
public class Statistic_ExportTeachingHouseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public Statistic_ExportTeachingHouseServlet(){
		super();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String data = request.getParameter("rowdata");
		data = new String(data.getBytes("ISO-8859-1"), "UTF-8");
		String tablename = "3.2.1教学行政用房情况";
		data = data.substring(1, data.length() - 1);

		data = "[" + data + "]";
		System.out.println(data);
		try
		{
			JSONArray jsons = new JSONArray(data);
			System.out.println(jsons);
			/*-------------- 1.准备数据--------------*/
			List<Object> thResults = new ArrayList<Object>();
			for (int i = 0; i < jsons.length(); i++)
			{
				List<Object> thCountList = new ArrayList<Object>();
				thCountList.add(jsons.getJSONObject(i).getString("rowTitle"));
				thCountList.add(jsons.getJSONObject(i).getString("areaTotal"));
				thCountList.add(jsons.getJSONObject(i).getString("asistHouse"));
				thCountList.add(jsons.getJSONObject(i).getString("classroomArea"));
				thCountList.add(jsons.getJSONObject(i).getString("libraryArea"));
				thCountList.add(jsons.getJSONObject(i).getString("labArea"));
				thCountList.add(jsons.getJSONObject(i).getString("researchArea"));
				thCountList.add(jsons.getJSONObject(i).getString("gymArea"));
				thCountList.add(jsons.getJSONObject(i).getString("hallArea"));
				thCountList.add(jsons.getJSONObject(i).getString("adminArea"));
				thCountList.add(jsons.getJSONObject(i).getString("avgArea"));
				thCountList.add(jsons.getJSONObject(i).getString("classroomAmount"));
				thCountList.add(jsons.getJSONObject(i).getString("englishComputerAmount"));
				thCountList.add(jsons.getJSONObject(i).getString("multimediaAmount"));
				thCountList.add(jsons.getJSONObject(i).getString("seatNumber"));
				thCountList.add(jsons.getJSONObject(i).getString("englishComputerNumber"));
				thCountList.add(jsons.getJSONObject(i).getString("multimediaNumber"));
				thCountList.add(jsons.getJSONObject(i).getString("perHundredNumber"));
				thCountList.add(jsons.getJSONObject(i).getString("sportsArea"));
				thCountList.add(jsons.getJSONObject(i).getString("sportsNumber"));
				thCountList.add(jsons.getJSONObject(i).getString("studentsActivityArea"));
				thCountList.add(jsons.getJSONObject(i).getString("studentsActitvityAmount"));
				
				thResults.add(thCountList);
			}
			System.out.println(thResults);
			ExcelUtils.addValue("typename", tablename);// typename是用在表格模板里面的表名
			ExcelUtils.addValue("resultList", thResults);// resultList是用在表格模板里面用于显示数据的
			/*-------------- 2.写出excel文件--------------*/
			String dirs = request.getSession().getServletContext().getRealPath("") + "/template/";
			String templateFileName = tablename;// 模版名称（不含扩展名），用于导出的模板
			String templateFilePath = dirs + templateFileName + ".xls";// 导出的模板路径
			String destFilePath = dirs + templateFileName + "-out.xls";// 导出的文件路径
			try
			{
				System.out.println("templateFilePath=" + templateFilePath);
				OutputStream out = new FileOutputStream(destFilePath);
				ExcelUtils.export(templateFilePath, out);
				response.setContentType("application/x-download");
				System.out.println("destFilePath=" + destFilePath);
				String filenamedisplay = tablename + "-out.xls";
				filenamedisplay = URLEncoder.encode(filenamedisplay, "UTF-8");
				response.addHeader("Content-Disposition", "attachment;filename=" + filenamedisplay);
			} catch (ExcelException e)
			{
				e.printStackTrace();
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}
			try
			{
				java.io.OutputStream os = response.getOutputStream();
				java.io.FileInputStream fis = new java.io.FileInputStream(destFilePath);
				byte[] b = new byte[1024];
				int i = 0;
				while ((i = fis.read(b)) > 0)
				{
					os.write(b, 0, i);
				}
				fis.close();
				os.flush();
				os.close();
			} catch (Exception e)
			{
				System.out.println("error");
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}
