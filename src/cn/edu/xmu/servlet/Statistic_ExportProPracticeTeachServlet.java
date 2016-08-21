package cn.edu.xmu.servlet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import cn.edu.xmu.entity.ProPracticeTeach;
import cn.edu.xmu.service.ProPracticeTeachService;
import cn.edu.xmu.serviceimpl.ProPracticeTeachServiceImpl;
import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;

/**
 * 
 * @author xiaoping 附表10 各专业实践教学情况 date 2015-8-3
 *
 */
@WebServlet("/Statistic_ExportProPracticeTeachServlet")
public class Statistic_ExportProPracticeTeachServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	public Statistic_ExportProPracticeTeachServlet()
	{
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
		ProPracticeTeachService pptService = new ProPracticeTeachServiceImpl();
		String college = request.getParameter("college");
		String deadline = request.getParameter("deadline");

		Map queryParams = new HashMap();
		if (deadline != null)
		{
			deadline = new String(deadline.getBytes("ISO-8859-1"), "UTF-8");
			queryParams.put("deadline", deadline);
		}
		if (college != null)
			college = new String(college.getBytes("ISO-8859-1"), "UTF-8");
		if (college != null && !"".equals(college))
		{
			if (college.contains("学院"))
			{// 具体的学院才过滤
				queryParams.put("college", college);
			}
		} else
		{
			queryParams = null;
		}
		String tablename = request.getParameter("tablename");
		tablename = new String(tablename.getBytes("ISO-8859-1"), "UTF-8");
		// if(tablename.equals("1"))
		// tablename = "附表10各专业实践教学情况";
		// else
		// tablename = "3.7各专业实践教学情况";
		// data = URLDecoder.decode(data,"UTF-8");

		List<ProPracticeTeach> data = new ArrayList<ProPracticeTeach>();
		if (tablename.equals("附表10各专业实践教学情况"))
			data = pptService.getProPracticeTeach(false, queryParams);
		else
		{
			data = pptService.getProPracticeTeach(true, queryParams);
		}
		try
		{
			/*-------------- 1.准备数据--------------*/
			List<Object> topList = new ArrayList<Object>();
			List<Object> lowList = new ArrayList<Object>();
			for (int i = 0; i < data.size(); i++)
			{
				List<Object> uaCountList = new ArrayList<Object>();// 行数据
				ProPracticeTeach ppt = data.get(i);
				String rowTitle = ppt.getRowTitle();
				uaCountList.add(ppt.getSerialNumber());
				uaCountList.add(ppt.getMajor());
				uaCountList.add(ppt.getPracticeCredit());
				uaCountList.add(ppt.getPracticeRate());
				uaCountList.add(ppt.getExpriCredit());
				uaCountList.add(ppt.getExpriRate());
				uaCountList.add(ppt.getHaveExpriCourse());
				uaCountList.add(ppt.getIndepenExperiCourse());
				uaCountList.add(ppt.getPercentage());
				uaCountList.add(ppt.getDesignExperiment());
				if (rowTitle.equals("实践教学学分占总学分比例最高的5个专业："))
					topList.add(uaCountList);
				else
					lowList.add(uaCountList);
			}
			System.out.println(topList);
			ExcelUtils.addValue("typename", tablename);// typename是用在表格模板里面的表名
			ExcelUtils.addValue("topList", topList);// topList是用在表格模板里面用于显示数据的
			ExcelUtils.addValue("lowList", lowList);// topList是用在表格模板里面用于显示数据的
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
