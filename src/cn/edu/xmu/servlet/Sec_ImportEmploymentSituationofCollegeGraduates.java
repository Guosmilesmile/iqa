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
import cn.edu.xmu.dao.EmploymentSituationofCollegeGraduatesDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.EmploymentSituationofCollegeGraduatesDaoImpl;
import cn.edu.xmu.entity.EmploymentSituationofCollegeGraduates;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * @author zhantu 
 * Servlet implementation class Sec_ImportEmploymentSituationofCollegeGraduates 
 * date 2015-07-11
 */
@WebServlet("/ImportEmploymentSituationofCollegeGraduates")
public class Sec_ImportEmploymentSituationofCollegeGraduates extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ImportEmploymentSituationofCollegeGraduates() {
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
		List<EmploymentSituationofCollegeGraduates> escgList = new ArrayList<EmploymentSituationofCollegeGraduates>();

		TableListDao tableListDao = new TableListDaoImpl();
		EmploymentSituationofCollegeGraduatesDao escgDao = new EmploymentSituationofCollegeGraduatesDaoImpl();

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
			if (tablename.equals("表6-1-8应届本科毕业生就业情况")) {
				escgList = getAlltsByExcel(completeFilePath);
				escgDao.deleteByCollegeandDeadline(college, null);
				recordcount = escgList.size();
				for (int i = 0; i < escgList.size(); i++) {
					escgDao.addRecord(escgList.get(i));
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
	public static List<EmploymentSituationofCollegeGraduates> getAlltsByExcel(String file) {
		errorrow = 1;
		List<EmploymentSituationofCollegeGraduates> escgList = new ArrayList<EmploymentSituationofCollegeGraduates>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			//int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(" rows:" + rows);
			String temp = "";
			for (int i = 1; i < rows; i++) {
				for (int j = 3; j < 4; j++) {
					// 第一个是列数，第二个是行数
					// 默认最左边编号也算一列,所以这里得j++
					i++;//跳过表头
					temp = rs.getCell(j, i).getContents();
					int escg_recommendgraduate = -999;
					if(!temp.equals(""))
						escg_recommendgraduate = Integer.valueOf(temp);
					
					i++;
					temp = rs.getCell(j, ++i).getContents();
					int escg_postgraduateexamin = -999;
					if(!temp.equals(""))
						escg_postgraduateexamin = Integer.valueOf(temp);
					
					temp = rs.getCell(j, ++i).getContents();
					int escg_postgraduateexamout = -999;
					if(!temp.equals(""))
						escg_postgraduateexamout = Integer.valueOf(temp);
					
					int escg_postgraduateexamsum = -999;
					if(escg_postgraduateexamin!=-999 && escg_postgraduateexamout!=-999)
						escg_postgraduateexamsum = escg_postgraduateexamin+escg_postgraduateexamout;
					
					temp = rs.getCell(j, ++i).getContents();
					int escg_studyabroad = -999;
					if(!temp.equals(""))
						escg_studyabroad = Integer.valueOf(temp);
					
					i++;
					temp = rs.getCell(j, ++i).getContents();
					int escg_gov = -999;
					if(!temp.equals(""))
						escg_gov = Integer.valueOf(temp);
					
					temp = rs.getCell(j, ++i).getContents();
					int escg_institution = -999;
					if(!temp.equals(""))
						escg_institution = Integer.valueOf(temp);
					
					temp = rs.getCell(j, ++i).getContents();
					int escg_enterprise = -999;
					if(!temp.equals(""))
						escg_enterprise = Integer.valueOf(temp);
					
					j = 3;
					temp = rs.getCell(j, ++i).getContents();
					int escg_troops = -999;
					if(!temp.equals(""))
						escg_troops = Integer.valueOf(temp);
					
					j = 3;
					temp = rs.getCell(j, ++i).getContents();
					int escg_flexibleemployment = -999;
					if(!temp.equals(""))
						escg_flexibleemployment = Integer.valueOf(temp);
					
					j = 3;
					temp = rs.getCell(j, ++i).getContents();
					int esce_entrance = -999;
					if(!temp.equals(""))
						esce_entrance = Integer.valueOf(temp);
					
					j = 3;
					temp = rs.getCell(j, ++i).getContents();
					int escg_nationallocalprojectemployment = -999;
					if(!temp.equals(""))
						escg_nationallocalprojectemployment = Integer.valueOf(temp);
					
					j = 3;
					temp = rs.getCell(j, ++i).getContents();
					int escg_others = -999;
					if(!temp.equals(""))
						escg_others = Integer.valueOf(temp);
					
					int escg_employmentsum = -999;
					if(escg_gov!=-999 && escg_institution!=-999 && escg_enterprise!=-999 && escg_troops!=-999 && escg_flexibleemployment!=-999 && esce_entrance!=-999 && escg_nationallocalprojectemployment!=-999 && escg_others!=-999)
						escg_employmentsum = escg_gov+escg_institution+escg_enterprise+escg_troops+escg_flexibleemployment+esce_entrance+escg_nationallocalprojectemployment+escg_others;
					
					int isnull = 0;
					if(escg_postgraduateexamin==-999 || escg_postgraduateexamout==-999|| escg_gov==-999 || escg_institution==-999 || escg_enterprise==-999 || escg_troops==-999 || escg_flexibleemployment==-999 || esce_entrance==-999 || escg_nationallocalprojectemployment==-999 || escg_others==-999 || escg_studyabroad==-999 || escg_recommendgraduate==-999)
						isnull = 1;
					
					escgList.add(new EmploymentSituationofCollegeGraduates(escg_recommendgraduate,
							escg_postgraduateexamsum, escg_postgraduateexamin,
							escg_postgraduateexamout, escg_studyabroad,
							escg_employmentsum, escg_gov, escg_institution,
							escg_enterprise, escg_troops, escg_flexibleemployment,
							esce_entrance, escg_nationallocalprojectemployment,
							escg_others, college, isnull));
				}
				errorrow++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "导入失败";
		}
		return escgList;
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
