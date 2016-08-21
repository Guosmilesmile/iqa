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
import cn.edu.xmu.dao.CoverBuildingAreaDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.CoverBuildingAreaDaoImpl;
import cn.edu.xmu.entity.CoverBuildingArea;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * @author xiaoping 数据报表2-1 占地与建筑面积(时点) date 2015-7-11
 *
 */
@WebServlet("/Sec_ImportCoverBuildingArea")
public class Sec_ImportCoverBuildingArea extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	public Sec_ImportCoverBuildingArea()
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
		List<CoverBuildingArea> cbaList = new ArrayList<CoverBuildingArea>();

		TableListDao tableListDao = new TableListDaoImpl();
		CoverBuildingAreaDao cbaDao = new CoverBuildingAreaDaoImpl();

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
			if (tablename.equals("表2-1占地与建筑面积（时点）"))
			{
				cbaList = getAlltsByExcel(completeFilePath);
				cbaDao.deleteByCollegeandDeadline(college, null);
				recordcount = cbaList.size();
				for (int i = 0; i < cbaList.size(); i++)
				{
					cbaDao.addCoverBuildingArea(cbaList.get(i));
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
	public static List<CoverBuildingArea> getAlltsByExcel(String file)
	{
		errorrow = 1;
		List<CoverBuildingArea> cbaList = new ArrayList<CoverBuildingArea>();
		try
		{
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(clos + " rows:" + rows);
			Date temp = Date.valueOf("1800-1-1");
			for (int i = 2; i < rows; i++)
			{
				for (int j = 0; j < clos; j++)
				{
					// 第一个是列数，第二个是行数
					Date cba_statisticaltime = dateCellToSql(rs.getCell(j++, i));
					String cba_fillschool = rs.getCell(j++, i).getContents();
					String totalcoverarea = rs.getCell(j++, i).getContents();
					String propertycov = rs.getCell(j++, i).getContents();
					String propertyafforestcov = rs.getCell(j++, i).getContents();
					String nonpropertycov = rs.getCell(j++, i).getContents();
					String nonpropertyafforestcov = rs.getCell(j++, i).getContents();
					String nonproindepusecov = rs.getCell(j++, i).getContents();
					String nonprocommonusecov = rs.getCell(j++, i).getContents();
					String totalbuildingarea = rs.getCell(j++, i).getContents();
					String propertybui = rs.getCell(j++, i).getContents();
					String nonpropertybui = rs.getCell(j++, i).getContents();
					String nonproindepusebui = rs.getCell(j++, i).getContents();
					String nonprocommonusebui = rs.getCell(j++, i).getContents(); 
					float cba_totalcoverarea = -9;
					float cba_propertycov = -9;
					float cba_propertyafforestcov = -9;
					float cba_nonpropertycov = -9;
					float cba_nonpropertyafforestcov = -9;
					float cba_nonproindepusecov = -9;
					float cba_nonprocommonusecov = -9;
					float cba_totalbuildingarea = -9;
					float cba_propertybui = -9;
					float cba_nonpropertybui = -9;
					float cba_nonproindepusebui = -9;
					float cba_nonprocommonusebui = -9;
					if(!"".equals(totalcoverarea))
						cba_totalcoverarea = Float.parseFloat(totalcoverarea);
					if(!"".equals(propertycov))
						cba_propertycov = Float.parseFloat(propertycov);
					if(!"".equals(propertyafforestcov))
						cba_propertyafforestcov = Float.parseFloat(propertyafforestcov);
					if(!"".equals(nonpropertycov))
						cba_nonpropertycov = Float.parseFloat(nonpropertycov);
					if(!"".equals(nonpropertyafforestcov))
						cba_nonpropertyafforestcov = Float.parseFloat(nonpropertyafforestcov);
					if(!"".equals(nonproindepusecov))
						cba_nonproindepusecov = Float.parseFloat(nonproindepusecov);
					if(!"".equals(nonprocommonusecov))
						cba_nonprocommonusecov = Float.parseFloat(nonprocommonusecov);
					if(!"".equals(totalbuildingarea))
						cba_totalbuildingarea = Float.parseFloat(totalbuildingarea);
					if(!"".equals(propertybui))
						cba_propertybui = Float.parseFloat(propertybui);
					if(!"".equals(nonpropertybui))
						cba_nonpropertybui = Float.parseFloat(nonpropertybui);
					if(!"".equals(nonproindepusebui))
						cba_nonproindepusebui = Float.parseFloat(nonproindepusebui);
					if(!"".equals(nonprocommonusebui))
						cba_nonprocommonusebui = Float.parseFloat(nonprocommonusebui);
					int cba_isnull = 0;
				
					if (temp.equals(cba_statisticaltime) || "".equals(cba_fillschool) || "".equals(totalcoverarea)
							|| "".equals(propertycov) || "".equals(propertyafforestcov) || "".equals(nonpropertycov)
							|| "".equals(nonpropertyafforestcov) || "".equals(nonproindepusecov) || "".equals(nonprocommonusecov)
							|| "".equals(totalbuildingarea) || "".equals(propertybui) || "".equals(nonpropertybui)
							|| "".equals(nonproindepusebui) || "".equals(nonprocommonusebui))
						cba_isnull = 1;
					if (temp.equals(cba_statisticaltime) && "".equals(cba_fillschool) && "".equals(totalcoverarea)
							&& "".equals(propertycov) && "".equals(propertyafforestcov) && "".equals(nonpropertycov)
							&& "".equals(nonpropertyafforestcov) && "".equals(nonproindepusecov) && "".equals(nonprocommonusecov)
							&& "".equals(totalbuildingarea) && "".equals(propertybui) && "".equals(nonpropertybui)
							&& "".equals(nonproindepusebui) && "".equals(nonprocommonusebui))
						break;
					cbaList.add(new CoverBuildingArea(cba_statisticaltime, cba_fillschool, cba_totalcoverarea,
							cba_propertycov, cba_propertyafforestcov, cba_nonpropertycov, cba_nonpropertyafforestcov,
							cba_nonproindepusecov, cba_nonprocommonusecov, cba_totalbuildingarea, cba_propertybui,
							cba_nonpropertybui, cba_nonproindepusebui, cba_nonprocommonusebui, 1, null, college, "", cba_isnull));
				}
				errorrow++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			result = "导入失败";
		}
		return cbaList;
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
