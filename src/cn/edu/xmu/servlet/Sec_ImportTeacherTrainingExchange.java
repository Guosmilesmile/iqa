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
import cn.edu.xmu.dao.TeacherTrainingExchangeDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.TeacherTrainingExchangeDaoImpl;
import cn.edu.xmu.entity.TeacherTrainingExchange;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * @author zhantu 
 * Servlet implementation class Sec_ImportTeacherTrainingExchange 
 * date 2015-07-10
 */
@WebServlet("/ImportTeacherTrainingExchange")
public class Sec_ImportTeacherTrainingExchange extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ImportTeacherTrainingExchange() {
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
		List<TeacherTrainingExchange> tteList = new ArrayList<TeacherTrainingExchange>();

		TableListDao tableListDao = new TableListDaoImpl();
		TeacherTrainingExchangeDao teachScientificDao = new TeacherTrainingExchangeDaoImpl();

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
			if (tablename.equals("表3-5-2教师培训进修、交流情况")) {
				tteList = getAlltteByExcel(completeFilePath);
				teachScientificDao.deleteByCollegeandDeadline(college, null);
				recordcount = tteList.size();
				for (int i = 0; i < tteList.size(); i++) {
					teachScientificDao.addRecord(tteList.get(i));
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
	public static List<TeacherTrainingExchange> getAlltteByExcel(String file) {
		errorrow = 1;
		List<TeacherTrainingExchange> tteList = new ArrayList<TeacherTrainingExchange>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(clos + " rows:" + rows);
			String temp = "";
			for (int i = 4; i < rows; i++) {
				for (int j = 0; j < clos; j++) {
					// 第一个是列数，第二个是行数
					String tte_departmentname = rs.getCell(j++, i).getContents();// 默认最左边编号也算一列,所以这里得j++
					String tte_departmentnumber = rs.getCell(j++, i).getContents();
					
					temp = rs.getCell(j++, i).getContents();
					int tte_trainchurchyard = -999;
					if(!temp.equals(""))
						tte_trainchurchyard = Integer.valueOf(temp);
					
					temp = rs.getCell(j++, i).getContents();
					int tte_trainoverseassum = -999;
					if(!temp.equals(""))
						tte_trainoverseassum = Integer.valueOf(temp);
					
					temp = rs.getCell(j++, i).getContents();
					int tte_trainoverseasover3 = -999;
					if(!temp.equals(""))
						tte_trainoverseasover3 = Integer.valueOf(temp);
					
					temp = rs.getCell(j++, i).getContents();
					int tte_traintrade = -999;
					if(!temp.equals(""))
						tte_traintrade = Integer.valueOf(temp);
					j++;
					
					temp = rs.getCell(j++, i).getContents();
					int tte_trainfordoctor = -999;
					if(!temp.equals(""))
						tte_trainfordoctor = Integer.valueOf(temp);
					
					temp = rs.getCell(j++, i).getContents();
					int tte_trainformaster = -999;
					if(!temp.equals(""))
						tte_trainformaster = Integer.valueOf(temp);
					
					temp = rs.getCell(j++, i).getContents();
					int tte_exchangecomechurchyard = -999;
					if(!temp.equals(""))
						tte_exchangecomechurchyard = Integer.valueOf(temp);
					
					temp = rs.getCell(j++, i).getContents();
					int tte_exchangecomeoversea = -999;
					if(!temp.equals(""))
						tte_exchangecomeoversea = Integer.valueOf(temp);
					
					temp = rs.getCell(j++, i).getContents();
					int tte_exchangevisitchurchyard = -999;
					if(!temp.equals(""))
						tte_exchangevisitchurchyard = Integer.valueOf(temp);
					
					temp = rs.getCell(j++, i).getContents();
					int tte_exchangevisitoversea = -999;
					if(!temp.equals(""))
						tte_exchangevisitoversea = Integer.valueOf(temp);
					
					int tte_trainfordegreesum = -999;
					if(tte_trainfordoctor!=-999 && tte_trainformaster!=-999)
						tte_trainfordegreesum = tte_trainfordoctor+tte_trainformaster;
					//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
					int isnull = 0;
					if(tte_departmentname==null || tte_departmentnumber==null || tte_trainchurchyard==-999 ||
							tte_trainoverseassum==-999 || tte_trainoverseasover3==-999 || tte_traintrade==-999 ||
							tte_trainfordoctor==-999 || tte_trainformaster==-999 || tte_exchangecomechurchyard==-999 ||
							tte_exchangecomeoversea==-999 || tte_exchangevisitchurchyard==-999 || tte_exchangevisitoversea==-999)
						isnull = 1;
					
					if(!temp.equals(""))
					tteList.add(new TeacherTrainingExchange(tte_departmentname,
							tte_departmentnumber, tte_trainchurchyard,
							tte_trainoverseassum, tte_trainoverseasover3,
							tte_traintrade, tte_trainfordegreesum,
							tte_trainfordoctor, tte_trainformaster,
							tte_exchangecomechurchyard, tte_exchangecomeoversea,
							tte_exchangevisitchurchyard, tte_exchangevisitoversea,
							college,isnull));
				}
				errorrow++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "导入失败";
		}
		return tteList;
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
