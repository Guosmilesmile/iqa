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

import cn.edu.xmu.dao.OutsidePracticePlaceDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.OutsidePracticePlaceDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.OutsidePracticePlace;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 附表2-6-2校外实习、实践基地
 * @author chunfeng
 *
 */
@WebServlet("/Sec_ImportOutsidePracticePlaceServlet")
public class Sec_ImportOutsidePracticePlaceServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	public Sec_ImportOutsidePracticePlaceServlet()
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
		List<OutsidePracticePlace> oppList = new ArrayList<OutsidePracticePlace>();

		TableListDao tableListDao = new TableListDaoImpl();
		OutsidePracticePlaceDao oppDao = new OutsidePracticePlaceDaoImpl();

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
			if (tablename.equals("附表2-6-2校外实习、实践基地"))
			{
				oppList = getAlltsByExcel(completeFilePath);
				oppDao.deleteByCollegeandDeadline(college, null);
				recordcount = oppList.size();
				for (int i = 0; i < oppList.size(); i++)
				{
					oppDao.addOutsidePracticePlace(oppList.get(i));
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
	public static List<OutsidePracticePlace> getAlltsByExcel(String file)
	{
		errorrow = 1;
		List<OutsidePracticePlace> feList = new ArrayList<OutsidePracticePlace>();
		try
		{
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(clos + " rows:" + rows);
			for (int i = 2; i < rows; i++)
			{
				for (int j = 0; j < 11; j++)
				{
					// 第一个是列数，第二个是行数
					String opp_placename = rs.getCell(j++, i).getContents();
					String opp_collegename  = rs.getCell(j++, i).getContents();
				
					String opp_address = rs.getCell(j++, i).getContents();
				 	String opp_majors = rs.getCell(j++, i).getContents();
					String studentspertime = rs.getCell(j++, i).getContents(); 
					Integer opp_studentspertime = -1;
					if(!"".equals(studentspertime)) opp_studentspertime = Integer.parseInt(studentspertime);
					String studentsthisyear = rs.getCell(j++, i).getContents(); 
					Integer opp_studentsthisyear = -1;
					if(!"".equals(studentsthisyear)) opp_studentsthisyear = Integer.parseInt(studentsthisyear);
					String opp_month = rs.getCell(j++, i).getContents();
					String opp_cooperator = rs.getCell(j++, i).getContents(); 
					String opp_setupdate = rs.getCell(j++, i).getContents();
					String opp_level = rs.getCell(j++, i).getContents();
					String accumulatedstudents = rs.getCell(j++, i).getContents();
				    Integer opp_accumulatedstudents = -1;
					if(!"".equals(accumulatedstudents)) opp_accumulatedstudents = Integer.parseInt(accumulatedstudents);
						
					int opp_isnull = 0;
					if("".equals(opp_placename)||"".equals(opp_collegename)||"".equals(opp_address)||"".equals(opp_majors)
							|| "".equals(studentspertime) || "".equals(studentsthisyear) || "".equals(opp_month) 
							|| "".equals(opp_cooperator) ||"".equals(opp_setupdate)|| "".equals(opp_level) ||"".equals(accumulatedstudents))
					{
						opp_isnull = 1;
					}
					if ("".equals(opp_placename)&&"".equals(opp_collegename)&&"".equals(opp_address)&&"".equals(opp_majors)
							&& "".equals(studentspertime) && "".equals(studentsthisyear) && "".equals(opp_month) 
							&& "".equals(opp_cooperator) && "".equals(opp_setupdate)&& "".equals(opp_level) &&"".equals(accumulatedstudents))
					{
						break;
					}
					
					
					OutsidePracticePlace outsidePracticePlace = new OutsidePracticePlace( opp_placename, opp_collegename, opp_address, opp_majors, 
				            opp_studentspertime, opp_studentsthisyear, opp_month, opp_cooperator, opp_setupdate, opp_level, 
				            opp_accumulatedstudents, college, 1, opp_isnull);
					
					feList.add(outsidePracticePlace);
					
					
					}
				errorrow++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			result = "导入失败";
		}
		return feList;
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
