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
import cn.edu.xmu.dao.RecruitExceedScoreDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.RecruitExceedScoreDaoImpl;
import cn.edu.xmu.entity.RecruitExceedScore;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * @author xiaoping 附表6-1-5-1厦门大学在全国各省（市、自治区）招生出档分数情况（时点） date 2015-7-11
 *
 */
@WebServlet("/Sec_ImportRecruitExceedScore")
public class Sec_ImportRecruitExceedScore extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	public Sec_ImportRecruitExceedScore()
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
		List<RecruitExceedScore> resList = new ArrayList<RecruitExceedScore>();

		TableListDao tableListDao = new TableListDaoImpl();
		RecruitExceedScoreDao resDao = new RecruitExceedScoreDaoImpl();

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
			if (tablename.equals("附表6-1-5-1厦门大学在全国各省（市、自治区）招生出档分数情况"))
			{
				resList = getAlltsByExcel(completeFilePath);
				resDao.deleteByCollegeandDeadline(college, null);
				recordcount = resList.size();
				for (int i = 0; i < resList.size(); i++)
				{
					resDao.addRecruitExceedScore(resList.get(i));
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
	public static List<RecruitExceedScore> getAlltsByExcel(String file)
	{
		errorrow = 1;
		List<RecruitExceedScore> resList = new ArrayList<RecruitExceedScore>();
		try
		{
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(clos + " rows:" + rows);
			for (int i = 5; i < rows; i++)
			{
				for (int j = 0; j < 7; j++)
				{
					// 第一个是列数，第二个是行数
					String res_year = rs.getCell(j++, i).getContents();// 默认最左边编号也算一列,所以这里得j++
					String libexctwentyproportion = rs.getCell(j++, i).getContents().split("%")[0];// 去掉百分号
					String libexcthirtyproportion = rs.getCell(j++, i).getContents().split("%")[0];
					String libexclineave = rs.getCell(j++, i).getContents();
					String sciexcthirtyproportion = rs.getCell(j++, i).getContents().split("%")[0];
					String sciexcfortyproportion = rs.getCell(j++, i).getContents().split("%")[0];
					String sciexclineave = rs.getCell(j++, i).getContents();
					int res_isnulll = 0;
					if ("".equals(res_year) || "".equals(libexctwentyproportion) || "".equals(libexcthirtyproportion)
							|| "".equals(libexclineave) || "".equals(sciexcthirtyproportion)
							|| "".equals(sciexcfortyproportion) || "".equals(sciexclineave))
						res_isnulll = 1;
					if ("".equals(res_year) && "".equals(libexctwentyproportion) && "".equals(libexcthirtyproportion)
							&& "".equals(libexclineave) && "".equals(sciexcthirtyproportion)
							&& "".equals(sciexcfortyproportion) && "".equals(sciexclineave))
						break;
					float res_libexctwentyproportion = -9;
					float res_libexcthirtyproportion = -9;
					float res_libexclineave = -9;
					float res_sciexcthirtyproportion = -9;
					float res_sciexcfortyproportion = -9;
					float res_sciexclineave = -9;
					if (!"".equals(libexctwentyproportion))
						res_libexctwentyproportion = Float.parseFloat(libexctwentyproportion);
					if (!"".equals(libexcthirtyproportion))
						res_libexcthirtyproportion = Float.parseFloat(libexcthirtyproportion);
					if (!"".equals(libexclineave))
						res_libexclineave = Float.parseFloat(libexclineave);
					if (!"".equals(sciexcthirtyproportion))
						res_sciexcthirtyproportion = Float.parseFloat(sciexcthirtyproportion);
					if (!"".equals(sciexcfortyproportion))
						res_sciexcfortyproportion = Float.parseFloat(sciexcfortyproportion);
					if (!"".equals(sciexclineave))
						res_sciexclineave = Float.parseFloat(sciexclineave);
					resList.add(new RecruitExceedScore(0, res_year, res_libexctwentyproportion,
							res_libexcthirtyproportion, res_libexclineave, res_sciexcthirtyproportion,
							res_sciexcfortyproportion, res_sciexclineave, 1, null, college, "", res_isnulll));
				}
				errorrow++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			result = "导入失败";
		}
		return resList;
	}

	// 返回去掉空行的记录数
	private static int getRightRows(Sheet sheet)
	{
		int rsCols = sheet.getColumns(); // 列数
		int rsRows = sheet.getRows(); // 行数
		int nullCellNum;
		int afterRows = rsRows;
		for (int i = 4; i < rsRows; i++)
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
