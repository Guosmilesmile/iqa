package cn.edu.xmu.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import cn.edu.xmu.dao.EnrollCategoryInfoDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.EnrollCategoryInfoDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.EnrollCategoryInfo;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;


@WebServlet("/Sec_ImportEnrollCategoryInfoservlet")
public class Sec_ImportEnrollCategoryInfoservlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ImportEnrollCategoryInfoservlet() {
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
		List<EnrollCategoryInfo> tsList = new ArrayList<EnrollCategoryInfo>();

		TableListDao tableListDao = new TableListDaoImpl();
		EnrollCategoryInfoDao eciDao = new EnrollCategoryInfoDaoImpl();

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
			if (tablename.equals("表6-1-3近一届本科生招生类别情况（时点）")) {
			    tsList = getAlltsByExcel(completeFilePath);
			    eciDao.deleteByCollegeandDeadline(college, null);
				recordcount = tsList.size();
				for (int i = 0; i < tsList.size(); i++) {
					eciDao.addEnrollCategoryInfoRecord(tsList.get(i));
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
	public static List<EnrollCategoryInfo> getAlltsByExcel(String file) {
		errorrow = 1;
		List<EnrollCategoryInfo> eciList = new ArrayList<EnrollCategoryInfo>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			//int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println("clos:" +7 + " rows:" + rows);
			for (int i = 2; i < rows; i++) {
				for (int j = 0; j < 7; j++) {
					// 第一个是列数，第二个是行数
					String plannumber = rs.getCell(j++, i).getContents();// 默认最左边编号也算一列,所以这里得j++
					int eci_plannumber = -999;
					if(!plannumber.equals(""))
						eci_plannumber = Integer.valueOf(plannumber);
					String enrollnumber = rs.getCell(j++, i).getContents();
					Integer eci_enrollnumber = -999;
					if(!enrollnumber.equals(""))
						eci_enrollnumber = Integer.valueOf(enrollnumber);
					String registernumber =  rs.getCell(j++, i).getContents();
					Integer eci_registernumber = -999;
					if(!registernumber.equals(""))
						eci_registernumber = Integer.valueOf(registernumber);
					String indrecruitmentnumber =  rs.getCell(j++, i).getContents();
					Integer eci_indrecruitmentnumber = -999;
					if(!indrecruitmentnumber.equals(""))
						eci_indrecruitmentnumber = Integer.valueOf(indrecruitmentnumber);
					String specialnumber=  rs.getCell(j++, i).getContents();
					Integer eci_specialnumber = -999;
					if(!specialnumber.equals(""))
						eci_specialnumber = Integer.valueOf(specialnumber);
					String provincenumber=  rs.getCell(j++, i).getContents();
					Integer eci_provincenumber = -999;
					if(!provincenumber.equals(""))
						eci_provincenumber = Integer.valueOf(provincenumber);
					String newspecialitynumber =  rs.getCell(j++, i).getContents();
					Integer eci_newspecialitynumber = -999;
					if(!newspecialitynumber.equals(""))
						eci_newspecialitynumber = Integer.valueOf(newspecialitynumber);
					
					//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
					int isnull = 0;
					if(plannumber.equals("")||enrollnumber.equals("")||registernumber.equals("")||indrecruitmentnumber.equals("")||
							specialnumber.equals("")||provincenumber.equals("")||newspecialitynumber.equals(""))
						isnull = 1;
					
					eciList.add(new EnrollCategoryInfo(eci_plannumber, eci_enrollnumber, eci_registernumber,
							eci_indrecruitmentnumber, eci_specialnumber, eci_provincenumber, eci_newspecialitynumber,
							college,isnull));
				}
				errorrow++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "导入失败";
		}
		return eciList;
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
	/**
	 * 将excel日期型单元格转成sql.Date
	 * 
	 * @param cell
	 *            excel单元格
	 * @return
	 */
	private static Date dateCellToSql(Cell cell)
	{
		Date date = new Date(0);
		if (cell.getType() == CellType.DATE)
		{
			DateCell dc = (DateCell) (cell);
			date = new Date(dc.getDate().getTime());
		} else
		{
			try
			{
				String[] temp = cell.getContents().split("-");
				if (temp.length == 2)
				{
					date = Date.valueOf(temp[0] + "-" + temp[1] + "-01");
				} else if (temp.length == 3)
				{
					date = Date.valueOf(temp[0] + "-" + temp[1] + "-" + temp[2]);
				}
			} catch (Exception e)
			{
				date = new Date(0);
			}
		}
		System.out.println("date" + date);
		return date;
	}
}
