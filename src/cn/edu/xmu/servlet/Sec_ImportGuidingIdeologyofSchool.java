package cn.edu.xmu.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
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
import cn.edu.xmu.dao.GuidingIdeologyofSchoolDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.GuidingIdeologyofSchoolDaoImpl;
import cn.edu.xmu.entity.GuidingIdeologyofSchool;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * @author zhantu 
 * Servlet implementation class Sec_ImportGuidingIdeologyofSchool 
 * date 2015-07-12
 */
@WebServlet("/ImportGuidingIdeologyofSchool")
public class Sec_ImportGuidingIdeologyofSchool extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ImportGuidingIdeologyofSchool() {
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
		List<GuidingIdeologyofSchool> gisList = new ArrayList<GuidingIdeologyofSchool>();

		TableListDao tableListDao = new TableListDaoImpl();
		GuidingIdeologyofSchoolDao gisDao = new GuidingIdeologyofSchoolDaoImpl();

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
			if (tablename.equals("表1-6办学指导思想")) {
				gisList = getAllgisByExcel(completeFilePath);
				gisDao.deleteByCollegeandDeadline(college, null);
				recordcount = gisList.size();
				for (int i = 0; i < gisList.size(); i++) {
					gisDao.addRecord(gisList.get(i));
				}
			}
		} catch (SmartUploadException e) {
			e.printStackTrace();
			result = "上传失败";
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("上传失败");
		}
		
		if(result.equals("导入成功"))
		{
			request.setAttribute("result", result);
		    request.setAttribute("count", recordcount);
		    request.getRequestDispatcher("upTest/uploadtest.jsp").forward(request,
				response);
		}else{
			request.setAttribute("result", result);
			request.setAttribute("errorrow", errorrow);
		    request.getRequestDispatcher("upTest/error.jsp").forward(request,
				response);
		}
	}
	/**
	 * 得到Excel表格里面的数据
	 * @param file
	 * @return
	 */
	public static List<GuidingIdeologyofSchool> getAllgisByExcel(String file) {
		errorrow = 1;
		List<GuidingIdeologyofSchool> gisList = new ArrayList<GuidingIdeologyofSchool>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			//int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(" rows:" + rows);
			for (int i = 2; i < rows; i++) {
				for (int j = 1; j < 3; j++) {
					// 第一个是列数，第二个是行数
					String gis_mottocontent = rs.getCell(j++, i).getContents();// 默认最左边编号也算一列,所以这里得j++
					String gis_mottoremark = rs.getCell(j, i).getContents();
					j = 1;
					String gis_positiongoalcontent = rs.getCell(j++, ++i).getContents();
					String gis_positiongoalremark = rs.getCell(j, i++).getContents();
					
					String gis_strategy = rs.getCell(j, i).getContents();
					String gis_discipline = rs.getCell(j, i+=2).getContents();
					String gis_professional = rs.getCell(j, i+=2).getContents();
					String gis_teacher = rs.getCell(j, i+=2).getContents();
					i++;
					int isnull = 0;
					if(gis_mottocontent.equals("") || gis_mottoremark.equals("") || gis_positiongoalcontent.equals("") || 
							gis_positiongoalremark.equals("") || gis_strategy.equals("") || gis_discipline.equals("") || 
							gis_professional.equals("") || gis_teacher.equals(""))
						isnull = 1;
					
					gisList.add(new GuidingIdeologyofSchool(gis_mottocontent,
							gis_mottoremark, gis_positiongoalcontent,
							gis_positiongoalremark, gis_strategy,
							gis_discipline, gis_professional, gis_teacher,
							college, isnull));
				}
				errorrow++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "导入失败";
		}
		return gisList;
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
