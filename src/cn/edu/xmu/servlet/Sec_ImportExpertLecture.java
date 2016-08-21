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
import cn.edu.xmu.dao.ExpertLectureDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.ExpertLectureDaoImpl;
import cn.edu.xmu.entity.ExpertLecture;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * @author zhantu 
 * Servlet implementation class Sec_ImportExpertLecture 
 * date 2015-07-11
 */
@WebServlet("/ImportExpertLecture")
public class Sec_ImportExpertLecture extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ImportExpertLecture() {
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
		List<ExpertLecture> elList = new ArrayList<ExpertLecture>();

		TableListDao tableListDao = new TableListDaoImpl();
		ExpertLectureDao elDao = new ExpertLectureDaoImpl();

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
			if (tablename.equals("附表5-4-1校内外专家开设文化、学术讲座情况")) {
				elList = getAllelByExcel(completeFilePath);
				elDao.deleteByCollegeandDeadline(college, null);
				recordcount = elList.size();
				for (int i = 0; i < elList.size(); i++) {
					elDao.addRecord(elList.get(i));
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
	public static List<ExpertLecture> getAllelByExcel(String file) {
		errorrow = 1;
		List<ExpertLecture> elList = new ArrayList<ExpertLecture>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			//int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(" rows:" + rows);
			for (int i = 3; i < rows; i++) {
				for (int j = 0; j < 10; j++) {
					// 第一个是列数，第二个是行数
					String el_collegename = rs.getCell(j++, i).getContents();// 默认最左边编号也算一列,所以这里得j++
					String el_title = rs.getCell(j++, i).getContents();
					String el_grade = rs.getCell(j++, i).getContents();
					String el_term = rs.getCell(j++, i).getContents();
					String el_type = rs.getCell(j++, i).getContents();
					String el_name = rs.getCell(j++, i).getContents();
					String el_prorank = rs.getCell(j++, i).getContents();
					String el_unit = rs.getCell(j++, i).getContents();
					String el_area = rs.getCell(j++, i).getContents();
					String el_remark = rs.getCell(j++, i).getContents();

					//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
					int isnull = 0;
					if(el_collegename.equals("") || el_title.equals("") || el_grade.equals("") || el_term.equals("") ||
							el_type.equals("") || el_name.equals("") || el_prorank.equals("") || el_unit.equals("") ||
							el_area.equals("") || el_remark.equals(""))
						isnull = 1;
					
					elList.add(new ExpertLecture(el_collegename, el_title,
							el_grade, el_term, el_type, el_name,
							el_prorank, el_unit, el_area,
							el_remark, college, isnull));
				}
				errorrow++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "导入失败";
		}
		return elList;
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
