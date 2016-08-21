package cn.edu.xmu.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Date;
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

import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.UndergraduateActivityProjectDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.UndergraduateActivityProjectDaoImpl;
import cn.edu.xmu.entity.UndergraduateActivityProject;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * @author zhantu 
 * Servlet implementation class Sec_ImportUndergraduateActivityProject 
 * date 2015-07-11
 */
@WebServlet("/ImportUndergraduateActivityProject")
public class Sec_ImportUndergraduateActivityProject extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ImportUndergraduateActivityProject() {
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
		List<UndergraduateActivityProject> uapList = new ArrayList<UndergraduateActivityProject>();

		TableListDao tableListDao = new TableListDaoImpl();
		UndergraduateActivityProjectDao uapDao = new UndergraduateActivityProjectDaoImpl();

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
			if (tablename.equals("附表5-4-2本科生课外科技、文化活动项目")) {
				uapList = getAlluapByExcel(completeFilePath);
				uapDao.deleteByCollegeandDeadline(college, null);
				recordcount = uapList.size();
				for (int i = 0; i < uapList.size(); i++) {
					uapDao.addRecord(uapList.get(i));
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
	public static List<UndergraduateActivityProject> getAlluapByExcel(String file) {
		errorrow = 1;
		List<UndergraduateActivityProject> uapList = new ArrayList<UndergraduateActivityProject>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			//int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(" rows:" + rows);
			Date tempDate = Date.valueOf("1800-1-1");
			String temp = "";
			for (int i = 4; i < rows; i++) {
				for (int j = 0; j < 9; j++) {
					// 第一个是列数，第二个是行数// 默认最左边编号也算一列,所以这里得j++
					String uap_projectname = rs.getCell(j++, i).getContents();
					String uap_unit = rs.getCell(j++, i).getContents();
					String uap_projecttype = rs.getCell(j++, i).getContents();
					temp = rs.getCell(j++, i).getContents();
					int uap_personnumber = -999;
					if(!temp.equals(""))
						uap_personnumber = Integer.valueOf(temp);
					
					temp = rs.getCell(j++, i).getContents();
					int uap_majornumber = -999;
					if(!temp.equals(""))
						uap_majornumber = Integer.valueOf(temp);
					
					temp = rs.getCell(j++, i).getContents();
					int uap_projectnumber = -999;
					if(!temp.equals(""))
						uap_projectnumber = Integer.valueOf(temp);
					
					String uap_awardgrade = rs.getCell(j++, i).getContents();
					String uap_awardtype = rs.getCell(j++, i).getContents();
					
					Date uap_awarddate = dateCellToSql(rs.getCell(j++, i));
					if(uap_awarddate.equals(tempDate))
						uap_awarddate = null;
					//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
					int isnull = 0;
					if(uap_projectname.equals("") || uap_unit.equals("") || uap_projecttype.equals("") ||
							uap_personnumber==-999 || uap_majornumber==-999 || uap_projectnumber==-999 ||
							uap_awardgrade.equals("") || uap_awardtype.equals("") || uap_awarddate==null)
						isnull = 1;
					
					uapList.add(new UndergraduateActivityProject(uap_projectname,
							uap_unit, uap_projecttype, uap_personnumber,
							uap_majornumber, uap_projectnumber, uap_awardgrade,
							uap_awardtype, uap_awarddate, college, isnull));
				}
				errorrow++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "导入失败";
		}
		return uapList;
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
				date = Date.valueOf("1800-1-1");
			}
		}
		System.out.println("date" + date);
		return date;
	}
}
