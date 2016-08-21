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
import cn.edu.xmu.dao.FourSixDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.FourSixDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.FourSix;
import cn.edu.xmu.table.FourSixTable;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 */
@WebServlet("/Sec_ImportFourSix")
public class Sec_ImportFourSix extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ImportFourSix() {
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
		List<FourSix> tsList = new ArrayList<FourSix>();

		TableListDao tableListDao = new TableListDaoImpl();
		FourSixDao FourSixDao = new FourSixDaoImpl();

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
			if (tablename.equals("附表6-2-1-7本科毕业生大学英语四六级考试累计通过率")) {
				tsList = getAlltsByExcel(completeFilePath);
				FourSixDao.deleteByCollegeandDeadline(college, null);
				recordcount = tsList.size();
				for (int i = 0; i < tsList.size(); i++) {
					FourSixDao.addRecord(tsList.get(i));
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
	public static List<FourSix> getAlltsByExcel(String file) {
		errorrow = 1;
		List<FourSix> tsList = new ArrayList<FourSix>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(clos + " rows:" + rows);
			for (int i = 2; i < rows; i++) {
				for (int j = 0; j < clos; j++) {
					// 第一个是列数，第二个是行数
					
					
					
					/*int fx_total= Integer.parseInt(rs.getCell(j++, i).getContents());
					int fx_attend= Integer.parseInt(rs.getCell(j++, i).getContents());
					int fx_attendcount= Integer.parseInt(rs.getCell(j++, i).getContents());
					int fx_pass= Integer.parseInt(rs.getCell(j++, i).getContents());
					int fx_passpercent= Integer.parseInt(rs.getCell(j++, i).getContents());
					int fx_good= Integer.parseInt(rs.getCell(j++, i).getContents());
					int fx_goodpercent= Integer.parseInt(rs.getCell(j++, i).getContents());
					*/
					String fx_colleges= rs.getCell(j++, i).getContents();
					String fx_department= rs.getCell(j++, i).getContents();
					String fx_major= rs.getCell(j++, i).getContents();
					String fx_grade= rs.getCell(j++, i).getContents();
					String fx_level= rs.getCell(j++, i).getContents();
					int fx_total = 0;
					int fx_attend = 0;
					int fx_attendcount = 0;
					int fx_pass = 0;
					int fx_passpercent = 0;
					int fx_good = 0;
					int fx_goodpercent = 0;
					String total = rs.getCell(j++, i).getContents();
					if (total.equals(""))
						fx_total = 0;

					String attend = rs.getCell(j++, i).getContents();
					if (attend.equals(""))
						fx_attend = 0;

					String attendcount = rs.getCell(j++, i).getContents();
					if (attendcount.equals(""))
						fx_attendcount = 0;

					String pass = rs.getCell(j++, i).getContents();
					if (pass.equals(""))
						fx_pass = 0;

					String passpercent = rs.getCell(j++, i).getContents();
					if (passpercent.equals(""))
						fx_passpercent = 0;

					String good = rs.getCell(j++, i).getContents();
					if (good.equals(""))
						fx_good = 0;

					String goodpercent = rs.getCell(j++, i).getContents();
					if (goodpercent.equals(""))
						fx_goodpercent = 0;

					int isnull = 0;
					if ("".equals(fx_colleges) || "".equals(fx_department)
							|| "".equals(fx_major) || "".equals(fx_grade)
							|| "".equals(fx_level) || "".equals(total)
							|| "".equals(attend) || "".equals(attendcount) || "".equals(pass)
							|| "".equals(passpercent) || "".equals(good) || "".equals(goodpercent) )
						isnull = 1;
					tsList.add(new FourSix(fx_colleges, fx_department,
							fx_major, fx_grade, fx_level, fx_total, fx_attend,
							fx_attendcount, fx_pass, fx_passpercent, fx_good,
							fx_goodpercent, college,isnull));
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
