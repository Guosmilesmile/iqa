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
import cn.edu.xmu.dao.DisciplineConstructionDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.DisciplineConstructionDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.DisciplineConstruction;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * Servlet implementation class Sec_ImportServlet date 2015-07-09
 */
@WebServlet("/Sec_ImportDisciplineConstruction")
public class Sec_ImportDisciplineConstruction extends HttpServlet implements
		Servlet {
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ImportDisciplineConstruction() {
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
		List<DisciplineConstruction> tsList = new ArrayList<DisciplineConstruction>();

		TableListDao tableListDao = new TableListDaoImpl();
		DisciplineConstructionDao DisciplineConstructionDao = new DisciplineConstructionDaoImpl();

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
			if (tablename.equals("表4-1-1学科建设")) {
				tsList = getAlltsByExcel(completeFilePath);
				DisciplineConstructionDao.deleteByCollegeandDeadline(college,
						null);
				recordcount = tsList.size();
				for (int i = 0; i < tsList.size(); i++) {
					DisciplineConstructionDao.addRecord(tsList.get(i));
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
	public static List<DisciplineConstruction> getAlltsByExcel(String file) {
		errorrow = 1;
		List<DisciplineConstruction> tsList = new ArrayList<DisciplineConstruction>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(clos + " rows:" + rows);
			for (int i = 3; i < rows; i++) {
				for (int j = 0; j < clos; j++) {
					// 第一个是列数，第二个是行数
					int dc_doctorstation = 0;
					int dc_docgrantone = 0;
					int dc_docgranttwo = 0;
					int dc_masgrantone = 0;
					int dc_masgranttwo = 0;
					int dc_undertotal = 0;
					int dc_undernew = 0;
					int dc_junior = 0;
					String doctorstation = rs.getCell(j++, i).getContents();
					String docgrantone = rs.getCell(j++, i).getContents();
					String docgranttwo = rs.getCell(j++, i).getContents();
					String masgrantone = rs.getCell(j++, i).getContents();
					String masgranttwo = rs.getCell(j++, i).getContents();
					String undertotal = rs.getCell(j++, i).getContents();
					String undernew = rs.getCell(j++, i).getContents();
					String junior = rs.getCell(j++, i).getContents();
					int isnull = 0;

					if ("".equals(doctorstation) || "".equals(docgrantone) || "".equals(docgranttwo)
							|| "".equals(masgrantone) || "".equals(masgranttwo) || "".equals(undertotal)
							|| "".equals(undernew) || "".equals(junior) ) {
						isnull = 1;
					}

					if(!"".equals(doctorstation))
						dc_doctorstation = Integer.parseInt(doctorstation);
					
					if(!"".equals(docgrantone))
						dc_docgrantone = Integer.parseInt(docgrantone);
					
					if(!"".equals(docgranttwo))
						dc_docgranttwo = Integer.parseInt(docgranttwo);
					
					if(!"".equals(masgrantone))
						dc_masgrantone = Integer.parseInt(masgrantone);
					
					if(!"".equals(masgranttwo))
						dc_masgranttwo = Integer.parseInt(masgranttwo);
					
					if(!"".equals(undertotal))
						dc_undertotal = Integer.parseInt(undertotal);
					
					if(!"".equals(undernew))
						dc_undernew = Integer.parseInt(undernew);
					
					if(!"".equals(junior))
						dc_junior = Integer.parseInt(junior);
					
					
					
					tsList.add(new DisciplineConstruction(dc_doctorstation,
							dc_docgrantone, dc_docgranttwo, dc_masgrantone,
							dc_masgranttwo, dc_undertotal, dc_undernew,
							dc_junior, college,isnull));
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
