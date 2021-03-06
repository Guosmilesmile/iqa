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
import cn.edu.xmu.dao.NewBooksthatYearDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.NewBooksthatYearDaoImpl;
import cn.edu.xmu.entity.NewBooksthatYear;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * @author zhantu 
 * Servlet implementation class Sec_ImportNewBooksthatYear
 * date 2015-07-11
 */
@WebServlet("/ImportNewBooksthatYear")
public class Sec_ImportNewBooksthatYear extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ImportNewBooksthatYear() {
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
		List<NewBooksthatYear> nbyList = new ArrayList<NewBooksthatYear>();

		TableListDao tableListDao = new TableListDaoImpl();
		NewBooksthatYearDao nbyDao = new NewBooksthatYearDaoImpl();

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
			if (tablename.equals("表2-5-2图书当年新增情况")) {
				nbyList = getAllnbyByExcel(completeFilePath);
				nbyDao.deleteByCollegeandDeadline(college, null);
				recordcount = nbyList.size();
				for (int i = 0; i < nbyList.size(); i++) {
					nbyDao.addRecord(nbyList.get(i));
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
	public static List<NewBooksthatYear> getAllnbyByExcel(String file) {
		errorrow = 1;
		List<NewBooksthatYear> nbyList = new ArrayList<NewBooksthatYear>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			//int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(" rows:" + rows);
			String temp = "";
			for (int i = 2; i < rows; i++) {
				for (int j = 1; j < 2; j++) {//j = 1 第1列是编号,第2列是标题
					// 第一个是列数，第二个是行数
					// 默认最左边编号也算一列,所以这里得j++
					temp = rs.getCell(j, i++).getContents();
					int nby_paperbooksnumber = -999;
					if(!temp.equals(""))
						nby_paperbooksnumber = Integer.valueOf(temp);
					
					temp = rs.getCell(j, i++).getContents();
					int nby_ebooksnumber = -999;
					if(!temp.equals(""))
						nby_ebooksnumber = Integer.valueOf(temp);
					
					temp = rs.getCell(j, i++).getContents();
					float nby_documentacquisitioncost = -999;
					if(!temp.equals(""))
						nby_documentacquisitioncost = Float.valueOf(temp);
					
					temp = rs.getCell(j, i++).getContents();
					int nby_bookcirculation = -999;
					if(!temp.equals(""))
						nby_bookcirculation = Integer.valueOf(temp);
					
					temp = rs.getCell(j, i++).getContents();
					int nby_electronicresourceaccess = -999;
					if(!temp.equals(""))
						nby_electronicresourceaccess = Integer.valueOf(temp);
					
					//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
					int isnull = 0;
					if(nby_paperbooksnumber==-999 || nby_ebooksnumber==-999 || nby_documentacquisitioncost==-999 ||
							nby_bookcirculation==-999 || nby_electronicresourceaccess==-999)
						isnull = 1;
					
					nbyList.add(new NewBooksthatYear(nby_paperbooksnumber, nby_ebooksnumber,
							nby_documentacquisitioncost, nby_bookcirculation,
							nby_electronicresourceaccess, college, isnull));
				}
				errorrow++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "导入失败";
		}
		return nbyList;
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
