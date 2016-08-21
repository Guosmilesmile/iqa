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
import cn.edu.xmu.dao.TeacherPublishWritingDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.TeacherPublishWritingDaoImpl;
import cn.edu.xmu.entity.TeacherPublishWriting;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 表3-6-5 教师出版著作（自然年）
 * 
 * @author chunfeng
 *
 */
@WebServlet("/Sec_ImportTeacherPublishWritingServlet")
public class Sec_ImportTeacherPublishWritingServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	public Sec_ImportTeacherPublishWritingServlet()
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
		List<TeacherPublishWriting> tpwList = new ArrayList<TeacherPublishWriting>();

		TableListDao tableListDao = new TableListDaoImpl();
		TeacherPublishWritingDao tpwDao = new TeacherPublishWritingDaoImpl();

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
			if (tablename.equals("表3-6-5教师出版著作（自然年）"))
			{
				tpwList = getAlltsByExcel(completeFilePath);
				tpwDao.deleteByCollegeandDeadline(college, null);
				recordcount = tpwList.size();
				for (int i = 0; i < tpwList.size(); i++)
				{
					tpwDao.addTeacherPublishWriting(tpwList.get(i));
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
	public static List<TeacherPublishWriting> getAlltsByExcel(String file)
	{
		errorrow = 1;
		List<TeacherPublishWriting> tpwList = new ArrayList<TeacherPublishWriting>();
		try
		{
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(clos + " rows:" + rows);
			for (int i = 2; i < rows; i++)
			{
				for (int j = 0; j < 5; j++)
				{
					// 第一个是列数，第二个是行数
					String total = rs.getCell(j++, i).getContents();
					String monograph = rs.getCell(j++, i).getContents();
					String translation = rs.getCell(j++, i).getContents();
					String compile = rs.getCell(j++, i).getContents();
					String other = rs.getCell(j++, i).getContents();
					Integer tpw_total = -1;
					if (!total.equals(""))
						tpw_total = Integer.parseInt(total);
					Integer tpw_monograph = -1;
					if (!monograph.equals(""))
						tpw_monograph = Integer.parseInt(monograph);
					Integer tpw_translation = -1;
					if (!translation.equals(""))
						tpw_translation = Integer.parseInt(translation);
					Integer tpw_compile = -1;
					if (!compile.equals(""))
						tpw_compile = Integer.parseInt(compile);
					Integer tpw_other = -1;
					if (!other.equals(""))
						tpw_other = Integer.parseInt(other);

					int tpw_isnull = 0;
					if ("".equals(total) || "".equals(monograph) || "".equals(translation) || "".equals(compile)
							|| "".equals(other))
						tpw_isnull = 1;
					if ("".equals(total) && "".equals(monograph) && "".equals(translation) && "".equals(compile)
							&& "".equals(other))
						break;
					tpwList.add(new TeacherPublishWriting(tpw_total, tpw_monograph, tpw_translation, tpw_compile,
							tpw_other, college, 1, tpw_isnull));
				}
				errorrow++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			result = "导入失败";
		}
		return tpwList;
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
