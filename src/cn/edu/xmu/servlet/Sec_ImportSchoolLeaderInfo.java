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
import org.apache.poi.hssf.record.formula.functions.Datevalue;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.SchoolLeaderInfoDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.SchoolLeaderInfoDaoImpl;
import cn.edu.xmu.entity.SchoolLeaderInfo;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * @author xiaoping 表3-2 校领导基本信息(时点) date 2015-7-11
 *
 */
@WebServlet("/Sec_ImportSchoolLeaderInfo")
public class Sec_ImportSchoolLeaderInfo extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	public Sec_ImportSchoolLeaderInfo()
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
		List<SchoolLeaderInfo> sliList = new ArrayList<SchoolLeaderInfo>();

		TableListDao tableListDao = new TableListDaoImpl();
		SchoolLeaderInfoDao sliDao = new SchoolLeaderInfoDaoImpl();

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
			if (tablename.equals("表3-2校领导基本信息（时点）"))
			{
				sliList = getAlltsByExcel(completeFilePath);
				sliDao.deleteByCollegeandDeadline(college, null);
				recordcount = sliList.size();
				for (int i = 0; i < sliList.size(); i++)
				{
					sliDao.addSchoolLeaderInfo(sliList.get(i));
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
	public static List<SchoolLeaderInfo> getAlltsByExcel(String file)
	{
		errorrow = 1;
		List<SchoolLeaderInfo> sliList = new ArrayList<SchoolLeaderInfo>();
		try
		{
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(clos + " rows:" + rows);
			for (int i = 2; i < rows; i++)
			{
				for (int j = 0; j < clos; j++)
				{
					// 第一个是列数，第二个是行数
					String sequencenumber = rs.getCell(j++, i).getContents();// 默认最左边编号也算一列,所以这里得j++
					int sli_sequencenumber = -9;
					if(!"".equals(sequencenumber))
						sli_sequencenumber = Integer.parseInt(sequencenumber);
					String sli_name = rs.getCell(j++, i).getContents();
					String sli_worknumber = rs.getCell(j++, i).getContents();
					String sli_position = rs.getCell(j++, i).getContents();
					String sli_gender = rs.getCell(j++, i).getContents();
					Date sli_birthday = dateCellToSql(rs.getCell(j++, i));
					Date sli_inschooldate = dateCellToSql(rs.getCell(j++, i));
					String sli_education = rs.getCell(j++, i).getContents();
					String sli_degree = rs.getCell(j++, i).getContents();
					String sli_professionaltitle = rs.getCell(j++, i).getContents();
					String sli_responsibility = rs.getCell(j++, i).getContents();
					String sli_studyworkresume = rs.getCell(j++, i).getContents();
					int sli_isnull = 0;
					Date temp = Date.valueOf("1800-1-1");
					if ("".equals(sequencenumber) || "".equals(sli_name) || "".equals(sli_worknumber)
							|| "".equals(sli_position) || "".equals(sli_gender) || temp.equals(sli_birthday)
							|| temp.equals(sli_inschooldate) || "".equals(sli_education) || "".equals(sli_degree)
							|| "".equals(sli_professionaltitle) || "".equals(sli_responsibility) || "".equals(sli_studyworkresume))
						sli_isnull = 1;
					sliList.add(new SchoolLeaderInfo(0, sli_sequencenumber, sli_name, sli_worknumber, sli_position,
							sli_gender, sli_birthday, sli_inschooldate, sli_education, sli_degree,
							sli_professionaltitle, sli_responsibility, sli_studyworkresume, 1, null, college, "", sli_isnull));
				}
				errorrow++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			result = "导入失败";
		}
		return sliList;
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
