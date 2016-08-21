package cn.edu.xmu.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.TeachingPlanImplDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.TeachingPlanImplDaoImpl;
import cn.edu.xmu.entity.TeachingPlanImpl;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * @author xiaoping 数据报表 附表4-2-2-2教学计划执行情况 date 2015-7-12
 *
 */
@WebServlet("/Sec_ImportTeachingPlanImpl")
public class Sec_ImportTeachingPlanImpl extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	public Sec_ImportTeachingPlanImpl()
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
		response.setContentType("text/html;Charset=UTF-8");
		String tableid = request.getParameter("tableid");
		college = request.getParameter("college");
		college = URLDecoder.decode(college, "utf-8");
		int recordcount = 0;
		List<TeachingPlanImpl> tpiList = new ArrayList<TeachingPlanImpl>();

		TableListDao tableListDao = new TableListDaoImpl();
		TeachingPlanImplDao tpiDao = new TeachingPlanImplDaoImpl();

		String tablename = tableListDao.getTablename(tableid);
		System.out.println(tablename);
		String filePath = getServletContext().getRealPath("/") + "/uploadModelTable/";
		String completeFilePath;// excel文件的完整路径
		File file = new File(filePath);
		if (!file.exists())
		{
			file.mkdir();
		}
		result = "导入成功";
		SmartUpload smartUpload = new SmartUpload();
		smartUpload.initialize(getServletConfig(), request, response);
		smartUpload.setMaxFileSize(1024 * 1024 * 10);
		smartUpload.setTotalMaxFileSize(1024 * 1024 * 100);
		smartUpload.setAllowedFilesList("txt,jpg,png,gif,doc,xlsx,xls");
		try
		{
			smartUpload.setDeniedFilesList("rar,jsp,html");
		} catch (SQLException e)
		{
			e.printStackTrace();
			result = "上传失败";
		}
		try
		{
			smartUpload.upload();
			int count = 0;
			count = smartUpload.save(filePath);
			com.jspsmart.upload.File myFile = smartUpload.getFiles().getFile(0);
			completeFilePath = filePath + "\\" + myFile.getFileName();
			System.out.println(completeFilePath);
			if (tablename.equals("附表4-2-2-2教学计划执行情况"))
			{
				tpiList = getAlltsByExcel(completeFilePath);
				tpiDao.deleteByCollegeandDeadline(college, null);
				recordcount = tpiList.size();
				for (int i = 0; i < tpiList.size(); i++)
				{
					tpiDao.addTeachingPlanImpl(tpiList.get(i));
				}
			}
		} catch (SmartUploadException e)
		{
			e.printStackTrace();
			result = "上传失败";
		} catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("上传失败");
		}

		if (result.equals("导入成功"))
		{
			request.setAttribute("result", result);
			request.setAttribute("count", recordcount);
			request.getRequestDispatcher("upTest/uploadtest.jsp").forward(request, response);
		} else
		{
			request.setAttribute("result", result);
			request.setAttribute("errorrow", errorrow);
			request.getRequestDispatcher("upTest/error.jsp").forward(request, response);
		}
	}

	/**
	 * 得到Excel表格里面的数据
	 * 
	 * @param file
	 * @return
	 */
	public static List<TeachingPlanImpl> getAlltsByExcel(String file)
	{
		errorrow = 1;
		List<TeachingPlanImpl> tpiList = new ArrayList<TeachingPlanImpl>();
		try
		{
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(clos + " rows:" + rows);
			for (int i = 3; i < rows; i++)
			{
				for (int j = 0; j < clos; j++)
				{
					// 第一个是列数，第二个是行数
					String tpi_institute = rs.getCell(j++, i).getContents();// 默认最左边编号也算一列,所以这里得j++
					String tpi_major = rs.getCell(j++, i).getContents();
					String tpi_grade = rs.getCell(j++, i).getContents();
					String plancoursenum = rs.getCell(j++, i).getContents();
					String plancoursecredit = rs.getCell(j++, i).getContents();
					String actualcoursenum = rs.getCell(j++, i).getContents();
					String actualcoursecredit = rs.getCell(j++, i).getContents();
					String newcoursenum = rs.getCell(j++, i).getContents();
					String newcoursecredit = rs.getCell(j++, i).getContents();
					String stopcoursenum = rs.getCell(j++, i).getContents();
					String stopcoursecredit = rs.getCell(j++, i).getContents();
					String advancelatercoursenum = rs.getCell(j++, i).getContents();
					String advancelatercoursecredit = rs.getCell(j++, i).getContents();
					int tpi_isnull = 0;
					if ("".equals(tpi_institute) || "".equals(tpi_major) || "".equals(tpi_grade) || "".equals(plancoursenum)
							|| "".equals(plancoursecredit) || "".equals(actualcoursenum) || "".equals(actualcoursecredit)
							|| "".equals(newcoursenum) || "".equals(newcoursecredit) || "".equals(stopcoursenum)
							|| "".equals(stopcoursecredit) || "".equals(advancelatercoursenum) || "".equals(advancelatercoursecredit))
						tpi_isnull = 1;
					if ("".equals(tpi_institute) && "".equals(tpi_major) && "".equals(tpi_grade) && "".equals(plancoursenum)
							&& "".equals(plancoursecredit) && "".equals(actualcoursenum) && "".equals(actualcoursecredit)
							&& "".equals(newcoursenum) && "".equals(newcoursecredit) && "".equals(stopcoursenum)
							&& "".equals(stopcoursecredit) && "".equals(advancelatercoursenum) && "".equals(advancelatercoursecredit))
						break;
					int tpi_plancoursenum = -9;
					float tpi_plancoursecredit = -9;
					int tpi_actualcoursenum = -9;
					float tpi_actualcoursecredit = -9;
					int tpi_newcoursenum = -9;
					float tpi_newcoursecredit = -9;
					int tpi_stopcoursenum = -9;
					float tpi_stopcoursecredit = -9;
					float tpi_advancelatercoursecredit = -9;
					if(!"".equals(plancoursenum))
						tpi_plancoursenum = Integer.parseInt(plancoursenum);
					if(!"".equals(plancoursecredit))
						tpi_plancoursecredit = Float.parseFloat(plancoursecredit);
					if(!"".equals(actualcoursenum))
						tpi_actualcoursenum = Integer.parseInt(actualcoursenum);
					if(!"".equals(actualcoursecredit))
						tpi_actualcoursecredit = Float.parseFloat(actualcoursecredit);
					if(!"".equals(newcoursenum))
						tpi_newcoursenum = Integer.parseInt(newcoursenum);
					if(!"".equals(newcoursecredit))
						tpi_newcoursecredit = Float.parseFloat(newcoursecredit);
					if(!"".equals(stopcoursenum))
						tpi_stopcoursenum = Integer.parseInt(stopcoursenum);
					if(!"".equals(stopcoursecredit))
						tpi_stopcoursecredit = Float.parseFloat(stopcoursecredit);
					if(!"".equals(advancelatercoursecredit))
						tpi_advancelatercoursecredit = Float.parseFloat(advancelatercoursecredit);
					tpiList.add(new TeachingPlanImpl(0, tpi_institute, tpi_major, tpi_grade, tpi_plancoursenum,
							tpi_plancoursecredit, tpi_actualcoursenum, tpi_actualcoursecredit, tpi_newcoursenum,
							tpi_newcoursecredit, tpi_stopcoursenum, tpi_stopcoursecredit, advancelatercoursenum,
							tpi_advancelatercoursecredit, 1, null, college, "", tpi_isnull));
				}
				errorrow++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			result = "导入失败";
		}
		return tpiList;
	}

	// 返回去掉空行的记录数
	private static int getRightRows(Sheet sheet)
	{
		int rsCols = sheet.getColumns(); // 列数
		int rsRows = sheet.getRows(); // 行数
		int nullCellNum;
		int afterRows = rsRows;
		for (int i = 1; i < rsRows; i++)
		{ // 统计行中为空的单元格数
			nullCellNum = 0;
			for (int j = 0; j < rsCols; j++)
			{
				String val = sheet.getCell(j, i).getContents();
				val = StringUtils.trimToEmpty(val);
				if (StringUtils.isBlank(val))
					nullCellNum++;
			}
			if (nullCellNum >= rsCols)
			{ // 如果nullCellNum大于或等于总的列数
				afterRows--; // 行数减一
			}
		}
		return afterRows;
	}
}
