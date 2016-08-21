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
import cn.edu.xmu.dao.RegUndergraProfesStuNumDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.RegUndergraProfesStuNumDaoImpl;
import cn.edu.xmu.entity.RegUndergraProfesStuNum;
import jxl.Sheet;
import jxl.Workbook;
/**
 * 
 * @author xiaoping 数据报表6-1-2 普通本科分专业（大类）学生数（时点） date 2015-7-12
 *
 */
@WebServlet("/Sec_ImportRegUndergraProfesStuNum")
public class Sec_ImportRegUndergraProfesStuNum extends HttpServlet
{

	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	public Sec_ImportRegUndergraProfesStuNum()
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
		List<RegUndergraProfesStuNum> rupsnList = new ArrayList<RegUndergraProfesStuNum>();

		TableListDao tableListDao = new TableListDaoImpl();
		RegUndergraProfesStuNumDao rupsnDao = new RegUndergraProfesStuNumDaoImpl();

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
			if (tablename.equals("表6-1-2普通本科分专业（大类）学生数（时点）"))
			{
				rupsnList = getAlltsByExcel(completeFilePath);
				rupsnDao.deleteByCollegeandDeadline(college, null);
				recordcount = rupsnList.size();
				for (int i = 0; i < rupsnList.size(); i++)
				{
					rupsnDao.addRegUndergraProfesStuNum(rupsnList.get(i));
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
	public static List<RegUndergraProfesStuNum> getAlltsByExcel(String file)
	{
		errorrow = 1;
		List<RegUndergraProfesStuNum> rupsnList = new ArrayList<RegUndergraProfesStuNum>();
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
					String rupsn_schprofescode = rs.getCell(j++, i).getContents();// 默认最左边编号也算一列,所以这里得j++
					String rupsn_schprofesname = rs.getCell(j++, i).getContents();
					String edusystem = rs.getCell(j++, i).getContents();
					j++;
					//int rupsn_atschtotal = Integer.parseInt(rs.getCell(j++, i).getContents());
					String gradeone = rs.getCell(j++, i).getContents();
					String gradetwo = rs.getCell(j++, i).getContents();
					String gradethree = rs.getCell(j++, i).getContents();
					String gradefour = rs.getCell(j++, i).getContents();
					String abgradefive = rs.getCell(j++, i).getContents();
					String minor = rs.getCell(j++, i).getContents();
					String doubledegree = rs.getCell(j++, i).getContents();
					String intonumber = rs.getCell(j++, i).getContents();
					String outnumber = rs.getCell(j++, i).getContents();
					int rupsn_edusystem = -9;
					int rupsn_atschtotal = 0;
					int rupsn_gradeone = -9;
					int rupsn_gradetwo = -9;
					int rupsn_gradethree = -9;
					int rupsn_gradefour = -9;
					int rupsn_abgradefive = -9;
					int rupsn_minor = -9;
					int rupsn_doubledegree = -9;
					int rupsn_intonumber = -9;
					int rupsn_outnumber = -9;
					if (edusystem != null && !"".equals(edusystem))
						rupsn_edusystem = Integer.parseInt(edusystem);
					if (gradeone != null && !"".equals(gradeone))
					{
						rupsn_gradeone = Integer.parseInt(gradeone);
						rupsn_atschtotal += rupsn_gradeone;
					}
					if (gradetwo != null && !"".equals(gradetwo))
					{
						rupsn_gradetwo = Integer.parseInt(gradetwo);
						rupsn_atschtotal += rupsn_gradetwo;
					}
					if (gradethree != null && !"".equals(gradethree))
					{
						rupsn_gradethree = Integer.parseInt(gradethree);
						rupsn_atschtotal += rupsn_gradethree;
					}
					if (gradefour != null && !"".equals(gradefour))
					{
						rupsn_gradefour = Integer.parseInt(gradefour);
						rupsn_atschtotal += rupsn_gradefour;
					}
					if (abgradefive != null && !"".equals(abgradefive))
					{
						rupsn_abgradefive = Integer.parseInt(abgradefive);
						rupsn_atschtotal += rupsn_abgradefive;
					}
					if (minor != null && !"".equals(minor))
						rupsn_minor = Integer.parseInt(minor);
					if (doubledegree != null && !"".equals(doubledegree))
						rupsn_doubledegree = Integer.parseInt(doubledegree);
					if (intonumber != null && !"".equals(intonumber))
						rupsn_intonumber = Integer.parseInt(intonumber);
					if (outnumber != null && !"".equals(outnumber))
						rupsn_outnumber = Integer.parseInt(outnumber);
					int rupsn_isnull = 0;
					if ("".equals(rupsn_schprofescode) || "".equals(rupsn_schprofesname) || "".equals(edusystem)
							|| "".equals(gradeone) || "".equals(gradetwo) || "".equals(gradethree)
							|| "".equals(gradefour) || "".equals(abgradefive) || "".equals(minor) || "".equals(doubledegree)
							|| "".equals(intonumber) || "".equals(outnumber))
						rupsn_isnull = 1;
					rupsnList.add(new RegUndergraProfesStuNum(0, rupsn_schprofescode, rupsn_schprofesname, rupsn_edusystem, rupsn_atschtotal, rupsn_gradeone, rupsn_gradetwo, rupsn_gradethree, rupsn_gradefour, rupsn_abgradefive, rupsn_minor, rupsn_doubledegree, rupsn_intonumber, rupsn_outnumber, 1, null, college, "", rupsn_isnull));
				}
				errorrow++;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			result = "导入失败";
		}
		return rupsnList;
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
