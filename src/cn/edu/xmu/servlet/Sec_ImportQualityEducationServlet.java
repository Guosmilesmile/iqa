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

import jxl.Sheet;
import jxl.Workbook;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import cn.edu.xmu.dao.QualityEducationDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.QualityEducationDaoimpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.QualityEducation;

/**
 * Servlet implementation class Sec_ImportQualityEducationServlet
 */
@WebServlet("/Sec_ImportQualityEducationServlet")
public class Sec_ImportQualityEducationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_ImportQualityEducationServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		String tableid = request.getParameter("tableid");
	    college = request.getParameter("college");
		college = URLDecoder.decode(college, "utf-8");
		int recordcount = 0;
		List<QualityEducation> tsList = new ArrayList<QualityEducation>();

		TableListDao tableListDao = new TableListDaoImpl();
		QualityEducationDao qeDao = new QualityEducationDaoimpl();

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
			if (tablename.equals("表2-11素质教育基地职业资质培训等情况")) {
				tsList = getAllqeByExcel(completeFilePath);
				qeDao.deleteByCollegeandDeadline(college, null);
				recordcount = tsList.size();
				for (int i = 0; i < tsList.size(); i++) {
					qeDao.addRecord(tsList.get(i));
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
	public static List<QualityEducation> getAllqeByExcel(String file) {
		errorrow = 1;
		List<QualityEducation> tsList = new ArrayList<QualityEducation>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int rows = getRightRows(rs);// 得到所有的行
			for (int i = 2; i < rows; i++) {
				for (int j = 1; j < 2; j++) {
					// 第一个是列数，第二个是行数
					String diathesisdeveloping = rs.getCell(j, i++).getContents();
					int qe_diathesisdeveloping = -999;
					if(!diathesisdeveloping.equals(""))
						qe_diathesisdeveloping = Integer.valueOf(diathesisdeveloping);
					
					String qualificationtraining = rs.getCell(j, i++).getContents();
					int qe_qualificationtraining = -999;
					if(!qualificationtraining.equals(""))
						qe_qualificationtraining = Integer.valueOf(qualificationtraining);
					
					String course = rs.getCell(j, i++).getContents();
					int qe_course = -999;
					if(!course.equals(""))
						qe_course = Integer.valueOf(course);
					
					String basecount = rs.getCell(j, i++).getContents();
					int qe_basecount = -999;
					if(!basecount.equals(""))
						qe_basecount = Integer.valueOf(basecount);
					
					int isnull = 0;
					if(diathesisdeveloping.equals("") || qualificationtraining.equals("") || course.equals("") || 
							basecount.equals("")  )
						isnull = 1;
					
					tsList.add(new QualityEducation(qe_diathesisdeveloping, qe_qualificationtraining, qe_course, qe_basecount,college,isnull));
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
