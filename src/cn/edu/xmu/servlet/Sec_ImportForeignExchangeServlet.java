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

import cn.edu.xmu.dao.ForeignExchangeDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.ForeignExchangeDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.ForeignExchange;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 表6-2-2-1本科生境外交流情况
 * @author chunfeng
 *
 */
@WebServlet("/Sec_ImportForeignExchangeServlet")
public class Sec_ImportForeignExchangeServlet extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	public Sec_ImportForeignExchangeServlet()
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
		List<ForeignExchange> feList = new ArrayList<ForeignExchange>();

		TableListDao tableListDao = new TableListDaoImpl();
		ForeignExchangeDao feDao = new ForeignExchangeDaoImpl();

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
			if (tablename.equals("表6-2-2-1本科生境外交流情况"))
			{
				feList = getAlltsByExcel(completeFilePath);
				feDao.deleteByCollegeandDeadline(college, null);
				recordcount = feList.size();
				for (int i = 0; i < feList.size(); i++)
				{
					feDao.addForeignExchange(feList.get(i));
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
	public static List<ForeignExchange> getAlltsByExcel(String file)
	{
		errorrow = 1;
		List<ForeignExchange> feList = new ArrayList<ForeignExchange>();
		try
		{
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			//int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			//System.out.println(clos + " rows:" + rows);
			for (int i = 2; i < rows; i++)
			{
				for (int j = 0; j < 9; j++)
				{
					// 第一个是列数，第二个是行数
					String fe_collegename = rs.getCell(j++, i).getContents();
					
					String fe_projectname = rs.getCell(j++, i).getContents();			
					String fe_iscsc = rs.getCell(j++, i).getContents();
					String fe_country  = rs.getCell(j++, i).getContents();
					String fe_school = rs.getCell(j++, i).getContents();
					String fe_level = rs.getCell(j++, i).getContents();
					String fe_time = rs.getCell(j++, i).getContents();		
					String selftoforeign = rs.getCell(j++, i).getContents();
					Integer fe_selftoforeign = -1;
					if(!selftoforeign.equals("")) fe_selftoforeign = Integer.parseInt(selftoforeign);
					String foreigntoself = rs.getCell(j++, i).getContents();
					Integer fe_foreigntoself = -1;
					if(!foreigntoself.equals("")) fe_foreigntoself = Integer.parseInt(foreigntoself);
					
					int fe_isnull = 0;
					if("".equals(fe_collegename)||"".equals(fe_projectname)||"".equals(fe_iscsc)||"".equals(fe_country)
							|| "".equals(fe_school) || "".equals(fe_level) || "".equals(fe_time) || "".equals(selftoforeign) ||"".equals(foreigntoself))
					{
						fe_isnull = 1;
					}
					if ("".equals(fe_collegename) && "".equals(fe_projectname) && "".equals(fe_iscsc) && "".equals(fe_country)
							&&  "".equals(fe_school) && "".equals(fe_level) && "".equals(fe_time) && "".equals(selftoforeign) && "".equals(foreigntoself))
					{
						break;
					}
					
					
					ForeignExchange foreignExchange = new ForeignExchange(fe_collegename,fe_projectname, fe_iscsc, fe_country, fe_school, fe_level, fe_time,fe_selftoforeign,fe_foreigntoself,college,1,fe_isnull);
					feList.add(foreignExchange);
					
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
