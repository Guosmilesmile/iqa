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
import cn.edu.xmu.dao.EducationMoneyDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.EducationMoneyDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.EducationMoney;
import cn.edu.xmu.table.EducationMoneyTable;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * @author luo Servlet implementation class Sec_ImportServlet date 2015-07-09
 */
@WebServlet("/Sec_ImportEducationMoney")
public class Sec_ImportEducationMoney extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ImportEducationMoney() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		String tableid = request.getParameter("tableid");
		college = request.getParameter("college");
		college = URLDecoder.decode(college, "utf-8");
		int recordcount = 0;
		List<EducationMoney> tsList = new ArrayList<EducationMoney>();

		TableListDao tableListDao = new TableListDaoImpl();
		EducationMoneyDao EducationMoneyDao = new EducationMoneyDaoImpl();

		String tablename = tableListDao.getTablename(tableid);
		System.out.println(tablename);
		String filePath = getServletContext().getRealPath("/") + "/uploadModelTable/";
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
			if (tablename.equals("附表2-10-2-1教育经费收支情况")) {
				tsList = getAlltsByExcel(completeFilePath);
				EducationMoneyDao.deleteByCollegeandDeadline(college, null);
				recordcount = tsList.size();
				for (int i = 0; i < tsList.size(); i++) {
					EducationMoneyDao.addRecord(tsList.get(i));
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
			request.getRequestDispatcher("upTest/uploadtest.jsp").forward(request, response);
		} else {
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
	public static List<EducationMoney> getAlltsByExcel(String file) {
		errorrow = 1;
		List<EducationMoney> tsList = new ArrayList<EducationMoney>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(clos + " rows:" + rows);
			for (int i = 4; i < rows; i++) {
				for (int j = 0; j < clos; j++) {
					// 第一个是列数，第二个是行数

					String em_colleges = rs.getCell(j++, i).getContents();
					String intotal = rs.getCell(j++, i).getContents();
					String inschoolfunds = rs.getCell(j++, i).getContents();
					String inchange = rs.getCell(j++, i).getContents();
					String instudentactivity = rs.getCell(j++, i).getContents();
					String inbuy = rs.getCell(j++, i).getContents();
					String inselfraise = rs.getCell(j++, i).getContents();
					String indonations = rs.getCell(j++, i).getContents();
					String outtotal = rs.getCell(j++, i).getContents();
					String outdaily = rs.getCell(j++, i).getContents();
					String outchange = rs.getCell(j++, i).getContents();
					String outbuild = rs.getCell(j++, i).getContents();
					String outpractice = rs.getCell(j++, i).getContents();
					String outpracticeexperiment = rs.getCell(j++, i).getContents();
					String outpracticeinter = rs.getCell(j++, i).getContents();
					String outother = rs.getCell(j++, i).getContents();
					String outstudentactivity = rs.getCell(j++, i).getContents();
					String outteacher = rs.getCell(j++, i).getContents();
					String em_comments = "";
					int isnull = 0;
					if ("".equals(intotal) || "".equals(inschoolfunds) || "".equals(inchange)
							|| "".equals(instudentactivity) || "".equals(inbuy)
							|| "".equals(inselfraise) || "".equals(indonations)
							|| "".equals(outtotal) || "".equals(outdaily) || "".equals(outchange)
							|| "".equals(outbuild) || "".equals(outpractice)
							|| "".equals(outpracticeexperiment) || "".equals(outpracticeinter)
							|| "".equals(outother) || "".equals(outstudentactivity)
							|| "".equals(outteacher)) {
						isnull = 1;
					}
					if ("".equals(intotal) && "".equals(inschoolfunds) && "".equals(inchange)
							&& "".equals(instudentactivity) && "".equals(inbuy)
							&& "".equals(inselfraise) && "".equals(indonations)
							&& "".equals(outtotal) && "".equals(outdaily) && "".equals(outchange)
							&& "".equals(outbuild) && "".equals(outpractice)
							&& "".equals(outpracticeexperiment) && "".equals(outpracticeinter)
							&& "".equals(outother) && "".equals(outstudentactivity)
							&& "".equals(outteacher)) {
						break;
					}
					float em_intotal = -999;
					if (!"".equals(intotal))
						em_intotal = Float.parseFloat(intotal);
					float em_inschoolfunds = -999;
					if (!"".equals(inschoolfunds))
						em_inschoolfunds = Float.parseFloat(inschoolfunds);
					float em_inchange = -999;
					if (!"".equals(inchange))
						em_inchange = Float.parseFloat(inchange);

					float em_instudentactivity = -999;
					if (!"".equals(instudentactivity))
						em_instudentactivity = Float.parseFloat(instudentactivity);

					float em_inbuy = -999;
					if (!"".equals(inbuy))
						em_inbuy = Float.parseFloat(inbuy);

					float em_inselfraise = -999;
					if (!"".equals(inselfraise))
						em_inselfraise = Float.parseFloat(inselfraise);

					float em_indonations = -999;
					if (!"".equals(indonations))
						em_indonations = Float.parseFloat(indonations);

					float em_outother = -999;
					if (!"".equals(outother))
						em_outother = Float.parseFloat(outother);

					float em_outstudentactivity = -999;
					if (!"".equals(outstudentactivity))
						em_outstudentactivity = Float.parseFloat(outstudentactivity);

					float em_outtotal = -999;
					if (!"".equals(outtotal))
						em_outtotal = Float.parseFloat(outtotal);

					float em_outdaily = -999;
					if (!"".equals(outdaily))
						em_outdaily = Float.parseFloat(outdaily);

					float em_outchange = -999;
					if (!"".equals(outchange))
						em_outchange = Float.parseFloat(outchange);

					float em_outbuild = -999;
					if (!"".equals(outbuild))
						em_outbuild = Float.parseFloat(outbuild);

					float em_outpractice = -999;
					if (!"".equals(outpractice))
						em_outpractice = Float.parseFloat(outpractice);

					float em_outpracticeexperiment = -999;
					if (!"".equals(outpracticeexperiment))
						em_outpracticeexperiment = Float.parseFloat(outpracticeexperiment);

					float em_outpracticeinter = -999;
					if (!"".equals(outpracticeinter))
						em_outpracticeinter = Float.parseFloat(outpracticeinter);

					float em_outteacher = -999;
					if (!"".equals(outteacher))
						em_outteacher = Float.parseFloat(outteacher);
					tsList.add(new EducationMoney(em_colleges, em_intotal, em_inschoolfunds,
							em_inchange, em_instudentactivity, em_inbuy, em_inselfraise,
							em_indonations, em_outtotal, em_outdaily, em_outchange, em_outbuild,
							em_outpractice, em_outpracticeexperiment, em_outpracticeinter,
							em_outother, em_outstudentactivity, em_outteacher, college, isnull));
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
