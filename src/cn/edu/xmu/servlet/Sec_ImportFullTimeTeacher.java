package cn.edu.xmu.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Date;
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
import cn.edu.xmu.dao.FullTimeTeacherInfoDao;
import cn.edu.xmu.dao.OtherTeacherInfoDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.FullTimeTeacherInfoDaoImpl;
import cn.edu.xmu.daoimpl.OtherTeacherInfoDaoImpl;
import cn.edu.xmu.entity.FullTimeTeacherInfo;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * @author xiaoping 数据报表3-1-1 专任教师基本信息表 date 2015-7-11
 *
 */
@WebServlet("/Sec_ImportFullTimeTeacher")
public class Sec_ImportFullTimeTeacher extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	public Sec_ImportFullTimeTeacher()
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
		List<FullTimeTeacherInfo> fttiList = new ArrayList<FullTimeTeacherInfo>();

		TableListDao tableListDao = new TableListDaoImpl();
		FullTimeTeacherInfoDao fullTimeTeacherInfoDao = new FullTimeTeacherInfoDaoImpl();
		OtherTeacherInfoDao otiDao = new OtherTeacherInfoDaoImpl();
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
			if (tablename.equals("表3-1-1专任教师基本信息"))
			{
				fttiList = getAlltsByExcel(completeFilePath);
				fullTimeTeacherInfoDao.deleteByCollegeandDeadline(college, null);
				recordcount = fttiList.size();
				for (int i = 0; i < fttiList.size(); i++)
				{
					if (fullTimeTeacherInfoDao.getCountByWorkNumber(fttiList.get(i).getFtti_worknumber(), null) > 0
							|| otiDao.getOtherTeacherInfoCountByWorknumber(fttiList.get(i).getFtti_worknumber(), null)>0)
						continue;
					fullTimeTeacherInfoDao.addFullTimeTeacher(fttiList.get(i));
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
	public static List<FullTimeTeacherInfo> getAlltsByExcel(String file)
	{
		errorrow = 1;
		List<FullTimeTeacherInfo> fttiList = new ArrayList<FullTimeTeacherInfo>();
		FullTimeTeacherInfoDao fttiDao = new FullTimeTeacherInfoDaoImpl();
		
		try
		{
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			Cell cell = null;
			System.out.println(clos + " rows:" + rows);
			for (int i = 2; i < rows; i++)
			{
				for (int j = 0; j < 17; j++)
				{
					// 第一个是列数，第二个是行数
					String ftti_name = rs.getCell(j++, i).getContents();// 默认最左边编号也算一列,所以这里得j++
					String ftti_worknumber = rs.getCell(j++, i).getContents();
					
					String ftti_gender = rs.getCell(j++, i).getContents();
					cell = rs.getCell(j++, i);
					Date ftti_birthday = dateCellToSql(cell);
					cell = rs.getCell(j++, i);
					Date ftti_inschooldate= dateCellToSql(cell);
					String ftti_workstate = rs.getCell(j++, i).getContents();
					String ftti_departmentnumber = rs.getCell(j++, i).getContents();
					String ftti_departmentname = rs.getCell(j++, i).getContents();
					String ftti_education = rs.getCell(j++, i).getContents();
					String ftti_degree = rs.getCell(j++, i).getContents();
					String ftti_educationsource = rs.getCell(j++, i).getContents();
					String ftti_professionaltitle = rs.getCell(j++, i).getContents();
					String ftti_subjectcategory = rs.getCell(j++, i).getContents();
					String ftti_ifdoublequalifiedteacher = rs.getCell(j++, i).getContents();
					String ftti_ifengineeringbackground = rs.getCell(j++, i).getContents();
					String ftti_ifindustrybackground = rs.getCell(j++, i).getContents();
					String ftti_tutortype = rs.getCell(j++, i).getContents();
					int ftti_isnull = 0;
					Date temp = Date.valueOf("1800-1-1");
					if ("".equals(ftti_name) || "".equals(ftti_worknumber) || "".equals(ftti_gender) || temp.equals(ftti_birthday)
							|| temp.equals(ftti_inschooldate) || "".equals(ftti_workstate) || "".equals(ftti_departmentnumber)
							|| "".equals(ftti_departmentname) || "".equals(ftti_education) || "".equals(ftti_degree)
							|| "".equals(ftti_educationsource) || "".equals(ftti_professionaltitle)
							|| "".equals(ftti_subjectcategory) || "".equals(ftti_ifdoublequalifiedteacher)
							|| "".equals(ftti_ifengineeringbackground) || "".equals(ftti_ifindustrybackground)
							|| "".equals(ftti_tutortype))
						ftti_isnull = 1;
					if ("".equals(ftti_name) && "".equals(ftti_worknumber) && "".equals(ftti_gender) && temp.equals(ftti_birthday)
							&& temp.equals(ftti_inschooldate) && "".equals(ftti_workstate) && "".equals(ftti_departmentnumber)
							&& "".equals(ftti_departmentname) && "".equals(ftti_education) && "".equals(ftti_degree)
							&& "".equals(ftti_educationsource) && "".equals(ftti_professionaltitle)
							&& "".equals(ftti_subjectcategory) && "".equals(ftti_ifdoublequalifiedteacher)
							&& "".equals(ftti_ifengineeringbackground) && "".equals(ftti_ifindustrybackground)
							&& "".equals(ftti_tutortype))
						break;
					fttiList.add(new FullTimeTeacherInfo(0, ftti_name, ftti_worknumber, ftti_gender, ftti_birthday,
							ftti_inschooldate, ftti_workstate, ftti_departmentnumber, ftti_departmentname,
							ftti_education, ftti_degree, ftti_educationsource, ftti_professionaltitle,
							ftti_subjectcategory, ftti_ifdoublequalifiedteacher, ftti_ifengineeringbackground,
							ftti_ifindustrybackground, ftti_tutortype, 1, null, college, "", ftti_isnull));
				}
				errorrow++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			result = "导入失败";
		}
		return fttiList;
	}

	// 返回去掉空行的记录数
	private static int getRightRows(Sheet sheet)
	{
		int rsCols = sheet.getColumns(); // 列数
		int rsRows = sheet.getRows(); // 行数
		int nullCellNum;
		int afterRows = rsRows;
		for (int i = 2; i < rsRows; i++)
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

	/**
	 * 将excel日期型单元格转成sql.Date
	 * 
	 * @param cell
	 *            excel单元格
	 * @return
	 */
	private static Date dateCellToSql(Cell cell)
	{
		Date date = Date.valueOf("1800-1-1");
		if (cell.getType() == CellType.DATE)
		{
			DateCell dc = (DateCell) (cell);
			date = new Date(dc.getDate().getTime());
		} else
		{
			try
			{
				String[] temp = cell.getContents().split("-");
				if (temp.length == 2)
				{
					date = Date.valueOf(temp[0] + "-" + temp[1] + "-01");
				} else if (temp.length == 3)
				{
					date = Date.valueOf(temp[0] + "-" + temp[1] + "-" + temp[2]);
				}
			} catch (Exception e)
			{
				date = Date.valueOf("1800-1-1");
			}
		}
		System.out.println("date" + date);
		return date;
	}
}
