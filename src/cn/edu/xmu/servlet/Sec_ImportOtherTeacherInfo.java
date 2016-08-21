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

import cn.edu.xmu.dao.OtherTeacherInfoDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.OtherTeacherInfoDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.OtherTeacherInfo;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

@WebServlet("/Sec_ImportOtherTeacherInfo")
public class Sec_ImportOtherTeacherInfo extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;
	
	public Sec_ImportOtherTeacherInfo() {
		// TODO Auto-generated constructor stub
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		String tableid = request.getParameter("tableid");
		college = request.getParameter("college");
		college = URLDecoder.decode(college, "utf-8");
		int recordcount = 0;
		
		List<OtherTeacherInfo> otiList = new ArrayList<>();
		TableListDao tableListDao = new TableListDaoImpl();
		OtherTeacherInfoDao otid = new OtherTeacherInfoDaoImpl();
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
			if (tablename.equals("表3-1-3其他师资信息"))
			{
				otiList = getAlltsByExcel(completeFilePath);
				otid.deleteByCollegeandDeadline(college, null);
				recordcount = otiList.size();
				for (int i = 0; i < otiList.size(); i++)
				{
					otid.addRecord(otiList.get(i));
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
	
	public static List<OtherTeacherInfo> getAlltsByExcel(String file){
		errorrow = 1;
		List<OtherTeacherInfo> otiList = new ArrayList<>();
		OtherTeacherInfoDao otid = new OtherTeacherInfoDaoImpl();
		
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			Cell cell = null;
			System.out.println(clos + " rows:" + rows);
			
			for (int i = 2; i < rows; i++) {
				for (int j = 0; j < 14; j++) {
					String oti_name = rs.getCell(j++, i).getContents();
					String oti_worknumber = rs.getCell(j++, i).getContents();
					String oti_sex =  rs.getCell(j++, i).getContents();
					cell = rs.getCell(j++, i);
					Date oti_birthday = dateCellToSql(cell);
					Date oti_inschooldate = dateCellToSql(rs.getCell(j++, i));
					String oti_workstate = rs.getCell(j++, i).getContents();
					String oti_teachertype = rs.getCell(j++, i).getContents();
					String oti_departmentnumber = rs.getCell(j++, i).getContents();
					String oti_departmentname = rs.getCell(j++, i).getContents();
					String oti_education = rs.getCell(j++, i).getContents();
					String oti_degree = rs.getCell(j++, i).getContents();
					String oti_professionaltitle = rs.getCell(j++, i).getContents();
					String oti_subjectcategory = rs.getCell(j++, i).getContents();
					String oti_tutorcategory = rs.getCell(j++, i).getContents();
					int oti_isnull = 0;
					Date temp = Date.valueOf("1800-1-1");
					if ("".equals(oti_name) || "".equals(oti_worknumber) || "".equals(oti_sex) || temp.equals(oti_birthday)
							|| temp.equals(oti_inschooldate) || "".equals(oti_workstate) || "".equals(oti_departmentnumber)
							|| "".equals(oti_departmentname) || "".equals(oti_education) || "".equals(oti_degree)
							|| "".equals(oti_professionaltitle)
							|| "".equals(oti_subjectcategory) 
							|| "".equals(oti_workstate)
							|| "".equals(oti_tutorcategory)) oti_isnull = 1;
					if ("".equals(oti_name) && "".equals(oti_worknumber) && "".equals(oti_sex) && temp.equals(oti_birthday)
							&& temp.equals(oti_inschooldate) && "".equals(oti_workstate) && "".equals(oti_departmentnumber)
							&& "".equals(oti_departmentname) && "".equals(oti_education) && "".equals(oti_degree)
							&& "".equals(oti_professionaltitle)
							&& "".equals(oti_subjectcategory) 
							&& "".equals(oti_workstate)
							&& "".equals(oti_tutorcategory)) break;
						otiList.add(new OtherTeacherInfo(0, oti_name, oti_worknumber, oti_sex, oti_birthday, oti_inschooldate, oti_workstate, oti_teachertype, oti_departmentnumber, oti_departmentname, oti_education, oti_degree, oti_professionaltitle, oti_subjectcategory, oti_tutorcategory, college, null, 1, "", oti_isnull));
				}
				
				errorrow++;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "导入失败";
		}
		
		return otiList;
	}
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
