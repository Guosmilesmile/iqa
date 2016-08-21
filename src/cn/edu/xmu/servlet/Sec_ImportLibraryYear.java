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

import cn.edu.xmu.dao.LibraryYearDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.LibraryYearDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.LibraryYear;
import cn.edu.xmu.entity.NewBooksthatYear;
import jxl.Sheet;
import jxl.Workbook;

@WebServlet("/Sec_ImportLibraryYear")
public class Sec_ImportLibraryYear extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	public Sec_ImportLibraryYear()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		String tableid = request.getParameter("tableid");
		college = request.getParameter("college");
		college = URLDecoder.decode(college, "utf-8");
		int recordcount = 0;

		List<LibraryYear> lyList = new ArrayList<>();

		TableListDao tableListDao = new TableListDaoImpl();
		LibraryYearDao lyd = new LibraryYearDaoImpl();
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
			if (tablename.equals("表2-5-1图书馆（自然年）"))
			{
				lyList = getAllnbyByExcel(completeFilePath);
				lyd.deleteByCollegeandDeadline(college, null);
				recordcount = lyList.size();
				for (int i = 0; i < lyList.size(); i++)
				{
					lyd.addRecord(lyList.get(i));
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

	public static List<LibraryYear> getAllnbyByExcel(String file)
	{

		errorrow = 1;
		List<LibraryYear> lyList = new ArrayList<>();
		try
		{
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			//int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(" rows:" + rows);

			for (int i = 2; i < rows;)
			{
				int j = 2;
				String number = rs.getCell(j, i++).getContents();
				String seatnumber = rs.getCell(j, i++).getContents();
				String paperbooknumber = rs.getCell(j, i++).getContents();
				String paperjournalnumber = rs.getCell(j, i++).getContents();
				String paperjournaltype = rs.getCell(j, i++).getContents();
				String ebooknumber = rs.getCell(j, i++).getContents();
				String ebookchnnumber = rs.getCell(j, i++).getContents();
				String ebookforeignnumber = rs.getCell(j, i++).getContents();
				String ejournaltype = rs.getCell(j, i++).getContents();
				String databasenumber = rs.getCell(j, i++).getContents();
				i++;//如果有第二条数据要跳过表头
				int ly_number = -9;
				int ly_seatnumber = -9;
				int ly_paperbooknumber = -9;
				int ly_paperjournalnumber = -9;
				int ly_paperjournaltype = -9;
				int ly_ebooknumber = -9;
				int ly_ebookchnnumber = -9;
				int ly_ebookforeignnumber = -9;
				int ly_ejournaltype = -9;
				int ly_databasenumber = -9;
				int isnull = 0;

				if ("".equals(number) || "".equals(seatnumber) || "".equals(paperbooknumber)
						|| "".equals(paperjournalnumber) || "".equals(paperjournaltype) || "".equals(ebooknumber)
						|| "".equals(ebookchnnumber) || "".equals(ebookforeignnumber) || "".equals(ejournaltype)
						|| "".equals(databasenumber))
					isnull = 1;

				if ("".equals(number) && "".equals(seatnumber) && "".equals(paperbooknumber)
						&& "".equals(paperjournalnumber) && "".equals(paperjournaltype) && "".equals(ebooknumber)
						&& "".equals(ebookchnnumber) && "".equals(ebookforeignnumber) && "".equals(ejournaltype)
						&& "".equals(databasenumber))
					break;
				if (number != null && !"".equals(number))
					ly_number = Integer.parseInt(number);
				if (seatnumber != null && !"".equals(seatnumber))
					ly_seatnumber = Integer.parseInt(seatnumber);
				if (paperbooknumber != null && !"".equals(paperbooknumber))
					ly_paperbooknumber = Integer.parseInt(paperbooknumber);
				if (paperjournalnumber != null && !"".equals(paperjournalnumber))
					ly_paperjournalnumber = Integer.parseInt(paperjournalnumber);
				if (paperjournaltype != null && !"".equals(paperjournaltype))
					ly_paperjournaltype = Integer.parseInt(paperjournaltype);
				if (ebooknumber != null && !"".equals(ebooknumber))
					ly_ebooknumber = Integer.parseInt(ebooknumber);
				if (ebookchnnumber != null && !"".equals(ebookchnnumber))
					ly_ebookchnnumber = Integer.parseInt(ebookchnnumber);
				if (ebookforeignnumber != null && !"".equals(ebookforeignnumber))
					ly_ebookforeignnumber = Integer.parseInt(ebookforeignnumber);
				if (ejournaltype != null && !"".equals(ejournaltype))
					ly_ejournaltype = Integer.parseInt(ejournaltype);
				if (databasenumber != null && !"".equals(databasenumber))
					ly_databasenumber = Integer.parseInt(databasenumber);

				lyList.add(new LibraryYear(ly_number, ly_seatnumber, ly_paperbooknumber, ly_paperjournalnumber,
						ly_paperjournaltype, ly_ebooknumber, ly_ebookchnnumber, ly_ebookforeignnumber, ly_ejournaltype,
						ly_databasenumber, 1, college, "", isnull));

				errorrow++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			result = "导入失败";
		}
		return lyList;
	}

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
