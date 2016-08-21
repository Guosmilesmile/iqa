package cn.edu.xmu.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import jxl.Sheet;
import jxl.Workbook;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.TrainingInstitutionsDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.TrainingInstitutionsDaoImpl;
import cn.edu.xmu.entity.TrainingInstitutions;
import cn.edu.xmu.table.TrainingInstitutionsTable;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 */
@WebServlet("/Sec_ImportTrainingInstitutions")
public class Sec_ImportTrainingInstitutions extends HttpServlet implements
		Servlet {
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ImportTrainingInstitutions() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		String tableid = request.getParameter("tableid");
		college = request.getParameter("college");
		college = URLDecoder.decode(college, "utf-8");
		int recordcount = 0;
		List<TrainingInstitutions> tsList = new ArrayList<TrainingInstitutions>();

		TableListDao tableListDao = new TableListDaoImpl();
		TrainingInstitutionsDao TrainingInstitutionsDao = new TrainingInstitutionsDaoImpl();

		String tablename = tableListDao.getTablename(tableid);
		System.out.println(tablename);
		String filePath = getServletContext().getRealPath("/")
				+ "/uploadModelTable/";
		String completeFilePath;// excel文件的完整路径
		File file = new File(filePath);
		if (!file.exists()) {
			file.mkdir();
		}
		result = "导入成功";
		SmartUpload smartUpload = new SmartUpload();
		smartUpload.initialize(getServletConfig(), request, response);
		smartUpload.setMaxFileSize(1024 * 1024 * 10);
		smartUpload.setTotalMaxFileSize(1024 * 1024 * 100);
		smartUpload.setAllowedFilesList("txt,jpg,png,gif,doc,xlsx,xls");
		try {
			smartUpload.setDeniedFilesList("rar,jsp,html");
		} catch (SQLException e) {
			e.printStackTrace();
			result = "上传失败";
		}
		try {
			smartUpload.upload();
			int count = 0;
			count = smartUpload.save(filePath);
			com.jspsmart.upload.File myFile = smartUpload.getFiles().getFile(0);
			completeFilePath = filePath + "\\" + myFile.getFileName();
			System.out.println(completeFilePath);
			if (tablename.equals("附表3-5-1-2教师教学发展机构培训情况")) {
				tsList = getAlltsByExcel(completeFilePath);
				TrainingInstitutionsDao.deleteByCollegeandDeadline(college,
						null);
				recordcount = tsList.size();
				for (int i = 0; i < tsList.size(); i++) {
					TrainingInstitutionsDao.addRecord(tsList.get(i));
				}
			}
		} catch (SmartUploadException e) {
			e.printStackTrace();
			result = "上传失败";
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("上传失败");
		}

		if (result.equals("导入成功")) {
			request.setAttribute("result", result);
			request.setAttribute("count", recordcount);
			request.getRequestDispatcher("upTest/uploadtest.jsp").forward(
					request, response);
		} else {
			request.setAttribute("result", result);
			request.setAttribute("errorrow", errorrow);
			request.getRequestDispatcher("upTest/error.jsp").forward(request,
					response);
		}
	}

	/**
	 * 得到Excel表格里面的数据
	 * 
	 * @param file
	 * @return
	 */
	public static List<TrainingInstitutions> getAlltsByExcel(String file) {
		errorrow = 1;
		List<TrainingInstitutions> tsList = new ArrayList<TrainingInstitutions>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(clos + " rows:" + rows);
			for (int i = 2; i < rows; i++) {
				for (int j = 0; j < clos; j++) {
					// 第一个是列数，第二个是行数
				/*	int ti_id= Integer.parseInt(rs.getCell(j++, i).getContents());
					String ti_name= rs.getCell(j++, i).getContents();
					String ti_departmentnumber= rs.getCell(j++, i).getContents();
					String ti_departmentname= rs.getCell(j++, i).getContents();
					String ti_projectname= rs.getCell(j++, i).getContents();
					String ti_object= rs.getCell(j++, i).getContents();
					Date ti_time= java.sql.Date.valueOf(rs.getCell(j++, i).getContents());
					int ti_peoplecount= Integer.parseInt(rs.getCell(j++, i).getContents());
					tsList.add(new TrainingInstitutions(ti_id, ti_name,
							ti_departmentnumber, ti_departmentname,
							ti_projectname, ti_object, ti_time, ti_peoplecount,
							college));*/
					Date ti_time = null;
					int ti_peoplecount = 0;
					int ti_id= Integer.parseInt(rs.getCell(j++, i).getContents());
					String ti_name = rs.getCell(j++, i).getContents();
					String ti_departmentnumber = rs.getCell(j++, i).getContents();
					String ti_departmentname = rs.getCell(j++, i).getContents();
					String ti_projectname =rs.getCell(j++, i).getContents();
					String ti_object = rs.getCell(j++, i).getContents();
					String date = rs.getCell(j++, i).getContents();
					String peoplecount = rs.getCell(j++, i).getContents();
					if (!"".equals(date))
						ti_time = Date.valueOf(date);
					
					if (!"".equals(peoplecount))
						ti_peoplecount = Integer.parseInt(peoplecount);
					int isnull = 0;
					if ("".equals(ti_name) || "".equals(ti_departmentnumber)
							|| "".equals(ti_departmentname)
							|| "".equals(ti_projectname) || "".equals(date)
							|| "".equals(ti_object) || "".equals(ti_projectname))
						isnull = 1;
					TrainingInstitutions ti = new TrainingInstitutions(ti_id,ti_name,
							ti_departmentnumber, ti_departmentname, ti_projectname,
							ti_object, ti_time, ti_peoplecount, college,isnull);
					tsList.add(ti);
				}
				errorrow++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "导入失败";
		}
		return tsList;
	}

	// 返回去掉空行的记录数
	private static int getRightRows(Sheet sheet) {
		int rsCols = sheet.getColumns(); // 列数
		int rsRows = sheet.getRows(); // 行数
		int nullCellNum;
		int afterRows = rsRows;
		for (int i = 1; i < rsRows; i++) { // 统计行中为空的单元格数
			nullCellNum = 0;
			for (int j = 0; j < rsCols; j++) {
				String val = sheet.getCell(j, i).getContents();
				val = StringUtils.trimToEmpty(val);
				if (StringUtils.isBlank(val))
					nullCellNum++;
			}
			if (nullCellNum >= rsCols) { // 如果nullCellNum大于或等于总的列数
				afterRows--; // 行数减一
			}
		}
		return afterRows;
	}
}
