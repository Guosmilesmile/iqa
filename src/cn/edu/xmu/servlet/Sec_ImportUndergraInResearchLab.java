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
import cn.edu.xmu.dao.UndergraInResearchLabDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.UndergraInResearchLabDaoImpl;
import cn.edu.xmu.entity.UndergraInResearchLab;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * @author xiaoping 附表5-4-4 本科生进入科研实验室情况 date 2015-7-11
 *
 */
@WebServlet("/Sec_ImportUndergraInResearchLab")
public class Sec_ImportUndergraInResearchLab extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	public Sec_ImportUndergraInResearchLab()
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
		List<UndergraInResearchLab> uirlList = new ArrayList<UndergraInResearchLab>();

		TableListDao tableListDao = new TableListDaoImpl();
		UndergraInResearchLabDao uirlDao = new UndergraInResearchLabDaoImpl();

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
			if (tablename.equals("附表5-4-4本科生进入科研实验室情况"))
			{
				uirlList = getAlltsByExcel(completeFilePath);
				uirlDao.deleteByCollegeandDeadline(college, null);
				recordcount = uirlList.size();
				for (int i = 0; i < uirlList.size(); i++)
				{
					uirlDao.addUndergraInResearchLab(uirlList.get(i));
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
	public static List<UndergraInResearchLab> getAlltsByExcel(String file)
	{
		errorrow = 1;
		List<UndergraInResearchLab> uirlList = new ArrayList<UndergraInResearchLab>();
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
					String uirl_institute = rs.getCell(j++, i).getContents();// 默认最左边编号也算一列,所以这里得j++
					String uirl_major = rs.getCell(j++, i).getContents();
					String uirl_grade = rs.getCell(j++, i).getContents();
					String uirl_name = rs.getCell(j++, i).getContents();
					String days = rs.getCell(j++, i).getContents();
					String times = rs.getCell(j++, i).getContents();
					String totalhours = rs.getCell(j++, i).getContents();
					String uirl_laboratoryname = rs.getCell(j++, i).getContents();
					String uirl_laboratorydirector = rs.getCell(j++, i).getContents();
					String uirl_tutor = rs.getCell(j++, i).getContents();
					String uirl_tutortitle = rs.getCell(j++, i).getContents();
					String uirl_ispartiresearchproject = rs.getCell(j++, i).getContents();
					String uirl_researchprojectname = rs.getCell(j++, i).getContents();
					String uirl_researchprojectlevel = rs.getCell(j++, i).getContents();
					String uirl_createprojectname = rs.getCell(j++, i).getContents();
					String uirl_createprojecttype = rs.getCell(j++, i).getContents();
					String uirl_createprojectlevel = rs.getCell(j++, i).getContents();
					String uirl_paper = rs.getCell(j++, i).getContents();
					String uirl_patent = rs.getCell(j++, i).getContents();
					String uirl_note = rs.getCell(j++, i).getContents();
					int uirl_isnull = 0;
					if ("".equals(uirl_institute) || "".equals(uirl_major) || "".equals(uirl_grade)
							|| "".equals(uirl_name) || "".equals(days) || "".equals(times) || "".equals(totalhours)
							|| "".equals(uirl_laboratoryname) || "".equals(uirl_laboratorydirector)
							|| "".equals(uirl_tutor) || "".equals(uirl_tutortitle)
							|| "".equals(uirl_ispartiresearchproject) || "".equals(uirl_researchprojectname)
							|| "".equals(uirl_researchprojectlevel) || "".equals(uirl_createprojectname)
							|| "".equals(uirl_createprojecttype) || "".equals(uirl_createprojectlevel)
							|| "".equals(uirl_paper) || "".equals(uirl_patent))
						uirl_isnull = 1;
					if ("".equals(uirl_institute) && "".equals(uirl_major) && "".equals(uirl_grade)
							&& "".equals(uirl_name) && "".equals(days) && "".equals(times) && "".equals(totalhours)
							&& "".equals(uirl_laboratoryname) && "".equals(uirl_laboratorydirector)
							&& "".equals(uirl_tutor) && "".equals(uirl_tutortitle)
							&& "".equals(uirl_ispartiresearchproject) && "".equals(uirl_researchprojectname)
							&& "".equals(uirl_researchprojectlevel) && "".equals(uirl_createprojectname)
							&& "".equals(uirl_createprojecttype) && "".equals(uirl_createprojectlevel)
							&& "".equals(uirl_paper) && "".equals(uirl_patent) && "".equals(uirl_note))
						break;
					int uirl_days = -9;
					int uirl_times = -9;
					float uirl_totalhours = -9;
					if (!"".equals(days))
						uirl_days = Integer.parseInt(days);
					if (!"".equals(times))
						uirl_times = Integer.parseInt(times);
					if (!"".equals(totalhours))
						uirl_totalhours = Float.parseFloat(totalhours);
					uirlList.add(new UndergraInResearchLab(0, uirl_institute, uirl_major, uirl_grade, uirl_name,
							uirl_days, uirl_times, uirl_totalhours, uirl_laboratoryname, uirl_laboratorydirector,
							uirl_tutor, uirl_tutortitle, uirl_ispartiresearchproject, uirl_researchprojectname,
							uirl_researchprojectlevel, uirl_createprojectname, uirl_createprojecttype,
							uirl_createprojectlevel, uirl_paper, uirl_patent, uirl_note, 1, null, college, "", uirl_isnull));
				}
				errorrow++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			result = "导入失败";
		}
		return uirlList;
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
