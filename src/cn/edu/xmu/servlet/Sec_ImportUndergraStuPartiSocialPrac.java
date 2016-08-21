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

import org.apache.catalina.valves.ErrorReportValve;
import org.apache.commons.lang.StringUtils;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.UndergraStuPartiSocialPracDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.UndergraStuPartiSocialPracDaoImpl;
import cn.edu.xmu.entity.UndergraStuPartiSocialPrac;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * @author xiaoping 附表5-4-3 本科生参与暑期社会实践情况 date 2015-7-12
 *
 */
@WebServlet("/Sec_ImportUndergraStuPartiSocialPrac")
public class Sec_ImportUndergraStuPartiSocialPrac extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	public Sec_ImportUndergraStuPartiSocialPrac()
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
		List<UndergraStuPartiSocialPrac> uspspList = new ArrayList<UndergraStuPartiSocialPrac>();

		TableListDao tableListDao = new TableListDaoImpl();
		UndergraStuPartiSocialPracDao uspspDao = new UndergraStuPartiSocialPracDaoImpl();

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
			if (tablename.equals("附表5-4-3本科生参与暑期社会实践情况"))
			{
				uspspList = getAlltsByExcel(completeFilePath);
				uspspDao.deleteByCollegeandDeadline(college, null);
				recordcount = uspspList.size();
				for (int i = 0; i < uspspList.size(); i++)
				{
					uspspDao.addUndergraStuPartiSocialPrac(uspspList.get(i));
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
	public static List<UndergraStuPartiSocialPrac> getAlltsByExcel(String file)
	{
		errorrow = 1;
		List<UndergraStuPartiSocialPrac> uspspList = new ArrayList<UndergraStuPartiSocialPrac>();
		try
		{
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(clos + " rows:" + rows);

			int focuspranum = 0, scatterpracnum = 0, subtotal = 0;// 用于计算合计
			for (int i = 2; i < rows; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					// 第一个是列数，第二个是行数
					String uspsp_department = rs.getCell(j++, i).getContents();// 默认最左边编号也算一列,所以这里得j++
					if (uspsp_department.equals("合计"))
						break;
					String temp_focuspracnum = rs.getCell(j++, i).getContents();
					String temp_scatterpracnum = rs.getCell(j++, i).getContents();
					int uspsp_subtotal = 0;// 小计等于两者之和
					int uspsp_isnull = 0;
					if ("".equals(uspsp_department) || "".equals(temp_focuspracnum) || "".equals(temp_scatterpracnum))
						uspsp_isnull = 1;
					if ("".equals(uspsp_department) && "".equals(temp_focuspracnum) && "".equals(temp_scatterpracnum))
						break;
					int uspsp_focuspracnum = -9;
					int uspsp_scatterpracnum = -9;
					if (!"".equals(temp_focuspracnum))
					{
						uspsp_focuspracnum = Integer.parseInt(temp_focuspracnum);
						uspsp_subtotal += uspsp_focuspracnum;
					}
					if (!"".equals(temp_scatterpracnum))
					{
						uspsp_scatterpracnum = Integer.parseInt(temp_scatterpracnum);
						uspsp_subtotal += uspsp_scatterpracnum;
					}

					// 计算合计
					focuspranum += (uspsp_focuspracnum<0?0:uspsp_focuspracnum);
					scatterpracnum += (uspsp_scatterpracnum<0?0:uspsp_scatterpracnum);
					subtotal += uspsp_subtotal;

					uspspList.add(new UndergraStuPartiSocialPrac(0, uspsp_department, uspsp_focuspracnum,
							uspsp_scatterpracnum, uspsp_subtotal, 1, null, college, "", uspsp_isnull));
				}
				errorrow++;
			}
			UndergraStuPartiSocialPrac total = new UndergraStuPartiSocialPrac(0, "合计", focuspranum, scatterpracnum,
					subtotal, 1, null, college, "", 0);
			uspspList.add(0, total);
			errorrow++;
		} catch (Exception e)
		{
			e.printStackTrace();
			result = "导入失败";
		}
		return uspspList;
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
