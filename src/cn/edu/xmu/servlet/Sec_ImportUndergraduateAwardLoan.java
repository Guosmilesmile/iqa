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
import cn.edu.xmu.dao.UndergraduateAwardLoanDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.UndergraduateAwardLoanDaoImpl;
import cn.edu.xmu.entity.UndergraduateAwardLoan;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * @author zhantu 
 * Servlet implementation class Sec_ImportUndergraduateAwardLoan 
 * date 2015-07-11
 */
@WebServlet("/ImportUndergraduateAwardLoan")
public class Sec_ImportUndergraduateAwardLoan extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ImportUndergraduateAwardLoan() {
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
		List<UndergraduateAwardLoan> tsList = new ArrayList<UndergraduateAwardLoan>();

		TableListDao tableListDao = new TableListDaoImpl();
		UndergraduateAwardLoanDao teachScientificDao = new UndergraduateAwardLoanDaoImpl();

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
			if (tablename.equals("表6-1-7本科生奖贷补")) {
				tsList = getAlltsByExcel(completeFilePath);
				teachScientificDao.deleteByCollegeandDeadline(college, null);
				recordcount = tsList.size();
				for (int i = 0; i < tsList.size(); i++) {
					teachScientificDao.addRecord(tsList.get(i));
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
	public static List<UndergraduateAwardLoan> getAlltsByExcel(String file) {
		errorrow = 1;
		List<UndergraduateAwardLoan> ualList = new ArrayList<UndergraduateAwardLoan>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			//int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(" rows:" + rows);
			String temp = "";
			for (int i = 2; i < rows; i++) {
				for (int j = 1; j < 4; j++) {//j = 1 第1列是编号,第2列是标题
					// 第一个是列数，第二个是行数
					j = 2;//换行
					temp = rs.getCell(j, ++i).getContents();//换行!,++i!
					float ual_govcost = -999;
					if(!temp.equals(""))
						ual_govcost = Float.valueOf(temp);
					
					temp = rs.getCell(j++, i).getContents();
					int ual_govcount = -999;
					if(!temp.equals(""))
						ual_govcount = Integer.valueOf(temp);
					
					j = 2;//换行
					temp = rs.getCell(j, ++i).getContents();//换行!,++i!
					float ual_societycost = -999;
					if(!temp.equals(""))
						ual_societycost = Float.valueOf(temp);
					
					temp = rs.getCell(j++, i).getContents();
					int ual_societycount = -999;
					if(!temp.equals(""))
						ual_societycount = Integer.valueOf(temp);
					j = 2;//换行
					temp = rs.getCell(j, ++i).getContents();//换行!,++i!
					float ual_schoolcost = -999;
					if(!temp.equals(""))
						ual_schoolcost = Float.valueOf(temp);
					
					temp = rs.getCell(j++, i).getContents();
					int ual_schoolcount = -999;
					if(!temp.equals(""))
						ual_schoolcount = Integer.valueOf(temp);
					
					j = 2;//换行
					temp = rs.getCell(j, ++i).getContents();//换行!,++i!
					float ual_countrycost = -999;
					if(!temp.equals(""))
						ual_countrycost = Float.valueOf(temp);
					
					temp = rs.getCell(j++, i).getContents();
					int ual_countrycount = -999;
					if(!temp.equals(""))
						ual_countrycount = Integer.valueOf(temp);
					
					j = 2;//换行
					temp = rs.getCell(j, ++i).getContents();//换行!,++i!
					float ual_workstudycost = -999;
					if(!temp.equals(""))
						ual_workstudycost = Float.valueOf(temp);
					
					temp = rs.getCell(j++, i).getContents();
					int ual_workstudycount = -999;
					if(!temp.equals(""))
						ual_workstudycount = Integer.valueOf(temp);
					j = 2;//换行
					temp = rs.getCell(j, ++i).getContents();//换行!,++i!
					float ual_deratecost = -999;
					if(!temp.equals(""))
						ual_deratecost = Float.valueOf(temp);
					
					temp = rs.getCell(j++, i).getContents();
					int ual_deratecount = -999;
					if(!temp.equals(""))
						ual_deratecount = Integer.valueOf(temp);
					
					j = 2;//换行
					temp = rs.getCell(j, ++i).getContents();//换行!,++i!
					float ual_troubleaidcost = -999;
					if(!temp.equals(""))
						ual_troubleaidcost =Float.valueOf(temp);
					
					temp = rs.getCell(j++, i).getContents();
					int ual_troubleaidcount = -999;
					if(!temp.equals(""))
						ual_troubleaidcount = Integer.valueOf(temp);
					i++;
					float ual_sumcost = -999;
					if(ual_govcost!=-999 && ual_societycost!=-999 && ual_schoolcost!=-999 && ual_countrycost!=-999 && 
							ual_workstudycost!=-999 && ual_deratecost!=-999 && ual_troubleaidcost!=-999)
						ual_sumcost = ual_govcost+ual_societycost+ual_schoolcost+ual_countrycost+
										ual_workstudycost+ual_deratecost+ual_troubleaidcost;
					
					int ual_sumcount = -999;
					if(ual_govcount!=-999 && ual_societycount!=-999 && ual_schoolcount!=-999 && ual_countrycount!=-999 && 
							ual_workstudycount!=-999 && ual_deratecount!=-999 && ual_troubleaidcount!=-999)
						ual_sumcount = ual_govcount+ual_societycount+ual_schoolcount+ual_countrycount+
										ual_workstudycount+ual_deratecount+ual_troubleaidcount;
					
					//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
					int isnull = 0;
					if(ual_govcost==-999 || ual_societycost==-999 || ual_schoolcost==-999 || ual_countrycost==-999 || 
							ual_workstudycost==-999 || ual_deratecost==-999 || ual_troubleaidcost==-999 || ual_govcount==-999 || 
							ual_societycount==-999 || ual_schoolcount==-999 || ual_countrycount==-999 || 
							ual_workstudycount==-999 || ual_deratecount==-999 || ual_troubleaidcount==-999)
						isnull = 1;
					
					ualList.add(new UndergraduateAwardLoan(ual_sumcost, ual_govcost,
							ual_societycost, ual_schoolcost, ual_countrycost,
							ual_workstudycost, ual_deratecost,
							ual_troubleaidcost, ual_sumcount, ual_govcount,
							ual_societycount, ual_schoolcount, ual_countrycount,
							ual_workstudycount, ual_deratecount,
							ual_troubleaidcount, college, isnull));
				}
				errorrow++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "导入失败";
		}
		return ualList;
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
