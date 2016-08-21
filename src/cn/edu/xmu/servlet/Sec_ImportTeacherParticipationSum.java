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
import cn.edu.xmu.dao.TeacherParticipationSumDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.TeacherParticipationSumDaoImpl;
import cn.edu.xmu.entity.TeacherParticipationSum;
import jxl.Sheet;
import jxl.Workbook;

/**
 * 
 * @author xiaoping 数据报表 附表3-5-1-3 教师参加院级及以上教学竞赛情况汇总表（自然年） date 2015-7-12
 *
 */
@WebServlet("/Sec_ImportTeacherParticipationSum")
public class Sec_ImportTeacherParticipationSum extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	public Sec_ImportTeacherParticipationSum()
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
		List<TeacherParticipationSum> tpsList = new ArrayList<TeacherParticipationSum>();

		TableListDao tableListDao = new TableListDaoImpl();
		TeacherParticipationSumDao tpsDao = new TeacherParticipationSumDaoImpl();

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
			if (tablename.equals("附表3-5-1-3教师参加院级及以上教学竞赛情况"))
			{
				tpsList = getAlltsByExcel(completeFilePath);
				tpsDao.deleteByCollegeandDeadline(college, null);
				recordcount = tpsList.size();
				for (int i = 0; i < tpsList.size(); i++)
				{
					tpsDao.addTeacherParticipationSum(tpsList.get(i));
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
	public static List<TeacherParticipationSum> getAlltsByExcel(String file)
	{
		errorrow = 1;
		List<TeacherParticipationSum> tpsList = new ArrayList<TeacherParticipationSum>();
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
					String tps_particollege = rs.getCell(j++, i).getContents();// 默认最左边编号也算一列,所以这里得j++
					String schskillcompecourtyardnum = rs.getCell(j++, i).getContents();
					String schskillcompeschoolnum = rs.getCell(j++, i).getContents();
					String schskillcompespecialnum = rs.getCell(j++, i).getContents();
					String schskillcompefirstnum = rs.getCell(j++, i).getContents();
					String schskillcompesecnum = rs.getCell(j++, i).getContents();
					String provinskillcompepartinum = rs.getCell(j++, i).getContents();
					String provinskillcompespecialnum = rs.getCell(j++, i).getContents();
					String provinskillcompefirstnum = rs.getCell(j++, i).getContents();
					String provinskillcompesecnum = rs.getCell(j++, i).getContents();
					String countryskillcompepartinum = rs.getCell(j++, i).getContents();
					String countryskillcompespecialnum = rs.getCell(j++, i).getContents();
					String countryskillcompefirstnum = rs.getCell(j++, i).getContents();
					String countryskillcompesecnum = rs.getCell(j++, i).getContents();
					String countrymicrocompepartinum = rs.getCell(j++, i).getContents();
					String countrymicrocompespecialnum = rs.getCell(j++, i).getContents();
					String countrymicrocompefirstnum = rs.getCell(j++, i).getContents();
					String countrymicrocompesecnum = rs.getCell(j++, i).getContents();
					String tps_preparer = "";// rs.getCell(j++,
												// i).getContents();
					int tps_isnull = 0;
					if ("".equals(tps_particollege) || "".equals(schskillcompecourtyardnum)
							|| "".equals(schskillcompeschoolnum) || "".equals(schskillcompespecialnum)
							|| "".equals(schskillcompefirstnum) || "".equals(schskillcompesecnum)
							|| "".equals(provinskillcompepartinum) || "".equals(provinskillcompespecialnum)
							|| "".equals(provinskillcompefirstnum) || "".equals(provinskillcompesecnum)
							|| "".equals(countryskillcompepartinum) || "".equals(countryskillcompespecialnum)
							|| "".equals(countryskillcompefirstnum) || "".equals(countryskillcompesecnum)
							|| "".equals(countrymicrocompepartinum) || "".equals(countrymicrocompespecialnum)
							|| "".equals(countrymicrocompefirstnum) || "".equals(countrymicrocompesecnum))
						tps_isnull = 1;
					if ("".equals(tps_particollege) && "".equals(schskillcompecourtyardnum)
							&& "".equals(schskillcompeschoolnum) && "".equals(schskillcompespecialnum)
							&& "".equals(schskillcompefirstnum) && "".equals(schskillcompesecnum)
							&& "".equals(provinskillcompepartinum) && "".equals(provinskillcompespecialnum)
							&& "".equals(provinskillcompefirstnum) && "".equals(provinskillcompesecnum)
							&& "".equals(countryskillcompepartinum) && "".equals(countryskillcompespecialnum)
							&& "".equals(countryskillcompefirstnum) && "".equals(countryskillcompesecnum)
							&& "".equals(countrymicrocompepartinum) && "".equals(countrymicrocompespecialnum)
							&& "".equals(countrymicrocompefirstnum) && "".equals(countrymicrocompesecnum))
						break;
					int tps_schskillcompecourtyardnum = -9;
					int tps_schskillcompeschoolnum = -9;
					int tps_schskillcompespecialnum = -9;
					int tps_schskillcompefirstnum = -9;
					int tps_schskillcompesecnum = -9;
					int tps_provinskillcompepartinum = -9;
					int tps_provinskillcompespecialnum = -9;
					int tps_provinskillcompefirstnum = -9;
					int tps_provinskillcompesecnum = -9;
					int tps_countryskillcompepartinum = -9;
					int tps_countryskillcompespecialnum = -9;
					int tps_countryskillcompefirstnum = -9;
					int tps_countryskillcompesecnum = -9;
					int tps_countrymicrocompepartinum = -9;
					int tps_countrymicrocompespecialnum = -9;
					int tps_countrymicrocompefirstnum = -9;
					int tps_countrymicrocompesecnum = -9;
					if (!"".equals(schskillcompecourtyardnum))
						tps_schskillcompecourtyardnum = Integer.parseInt(schskillcompecourtyardnum);
					if (!"".equals(schskillcompeschoolnum))
						tps_schskillcompeschoolnum = Integer.parseInt(schskillcompeschoolnum);
					if (!"".equals(schskillcompespecialnum))
						tps_schskillcompespecialnum = Integer.parseInt(schskillcompespecialnum);
					if (!"".equals(schskillcompefirstnum))
						tps_schskillcompefirstnum = Integer.parseInt(schskillcompefirstnum);
					if (!"".equals(schskillcompesecnum))
						tps_schskillcompesecnum = Integer.parseInt(schskillcompesecnum);
					if (!"".equals(provinskillcompepartinum))
						tps_provinskillcompepartinum = Integer.parseInt(provinskillcompepartinum);
					if (!"".equals(provinskillcompespecialnum))
						tps_provinskillcompespecialnum = Integer.parseInt(provinskillcompespecialnum);
					if (!"".equals(provinskillcompefirstnum))
						tps_provinskillcompefirstnum = Integer.parseInt(provinskillcompefirstnum);
					if (!"".equals(provinskillcompesecnum))
						tps_provinskillcompesecnum = Integer.parseInt(provinskillcompesecnum);
					if (!"".equals(countryskillcompepartinum))
						tps_countryskillcompepartinum = Integer.parseInt(countryskillcompepartinum);
					if (!"".equals(countryskillcompespecialnum))
						tps_countryskillcompespecialnum = Integer.parseInt(countryskillcompespecialnum);
					if (!"".equals(countryskillcompefirstnum))
						tps_countryskillcompefirstnum = Integer.parseInt(countryskillcompefirstnum);
					if (!"".equals(countryskillcompesecnum))
						tps_countryskillcompesecnum = Integer.parseInt(countryskillcompesecnum);
					if (!"".equals(countrymicrocompepartinum))
						tps_countrymicrocompepartinum = Integer.parseInt(countrymicrocompepartinum);
					if (!"".equals(countrymicrocompespecialnum))
						tps_countrymicrocompespecialnum = Integer.parseInt(countrymicrocompespecialnum);
					if (!"".equals(countrymicrocompefirstnum))
						tps_countrymicrocompefirstnum = Integer.parseInt(countrymicrocompefirstnum);
					if (!"".equals(countrymicrocompesecnum))
						tps_countrymicrocompesecnum = Integer.parseInt(countrymicrocompesecnum);
					tpsList.add(new TeacherParticipationSum(0, tps_particollege, tps_schskillcompecourtyardnum,
							tps_schskillcompeschoolnum, tps_schskillcompespecialnum, tps_schskillcompefirstnum,
							tps_schskillcompesecnum, tps_provinskillcompepartinum, tps_provinskillcompespecialnum,
							tps_provinskillcompefirstnum, tps_provinskillcompesecnum, tps_countryskillcompepartinum,
							tps_countryskillcompespecialnum, tps_countryskillcompefirstnum, tps_countryskillcompesecnum,
							tps_countrymicrocompepartinum, tps_countrymicrocompespecialnum,
							tps_countrymicrocompefirstnum, tps_countrymicrocompesecnum, tps_preparer, 1, null, college,
							"", tps_isnull));
				}
				errorrow++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			result = "导入失败";
		}
		return tpsList;
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
