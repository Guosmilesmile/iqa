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
import cn.edu.xmu.dao.BenkeForLanProficiencyDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.BenkeForLanProficiencyDaoImpl;
import cn.edu.xmu.entity.BenkeForLanProficiency;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * @author zhantu 
 * Servlet implementation class Sec_ImportBenkeForLanProficiency 
 * date 2015-07-10
 */
@WebServlet("/ImportBenkeForLanProficiency")
public class Sec_ImportBenkeForLanProficiency extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ImportBenkeForLanProficiency() {
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
		List<BenkeForLanProficiency> tsList = new ArrayList<BenkeForLanProficiency>();

		TableListDao tableListDao = new TableListDaoImpl();
		BenkeForLanProficiencyDao bflpDao = new BenkeForLanProficiencyDaoImpl();

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
		smartUpload.setAllowedFilesList("txt,jbflp,png,gif,doc,xlsx,xls");
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
			if (tablename.equals("附表6-2-1-12本科生外语水平及学习情况调查表")) {
				tsList = getAlltsByExcel(completeFilePath);
				bflpDao.deleteByCollegeandDeadline(college);
				recordcount = tsList.size();
				for (int i = 0; i < tsList.size(); i++) {
					bflpDao.addRecord(tsList.get(i));
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
	public static List<BenkeForLanProficiency> getAlltsByExcel(String file) {
		errorrow = 1;
		List<BenkeForLanProficiency> etList = new ArrayList<BenkeForLanProficiency>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			//int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(16 + " rows:" + rows);
			Date tempDate = Date.valueOf("1800-1-1");
			for (int i = 15; i < rows; i++) {
				for (int j = 1; j < 11; j++) {
					// 第一个是列数，第二个是行数
					String bflp_stunum = rs.getCell(j++, i).getContents();// 默认最左边编号也算一列,所以这里得j++
					String bflp_college1 = rs.getCell(j++, i).getContents();
					
					String bflp_name = rs.getCell(j++, i).getContents();
					String bflp_major = rs.getCell(j++, i).getContents();
					String bflp_grade = rs.getCell(j++, i).getContents();
					String bflp_degree = rs.getCell(j++, i).getContents();
					String bflp_level = rs.getCell(j++, i).getContents();
					String bflp_gpa = rs.getCell(j++, i).getContents();
					String bflp_rank = rs.getCell(j++, i).getContents();
					String bflp_ispush = rs.getCell(j++, i).getContents();
					
					//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
					int isnull = 0;
					if(bflp_stunum.equals("")||bflp_college1.equals("")||bflp_name.equals("")||bflp_major.equals("")||bflp_grade.equals("")||
							bflp_degree.equals("")||bflp_level.equals("")||bflp_gpa.equals("")||bflp_rank.equals("")||bflp_ispush.equals(""))
						isnull = 1;
					
					etList.add(new BenkeForLanProficiency( bflp_stunum, bflp_college1, bflp_name, bflp_major,
							bflp_grade, bflp_degree, bflp_level, bflp_gpa, bflp_rank, bflp_ispush, college, isnull));
					
				}
				errorrow++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "导入失败";
		}
		return etList;
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
		}
		else{
			try{
				String[] temp = cell.getContents().split("-");
				if (temp.length == 2){
					date = Date.valueOf(temp[0] + "-" + temp[1] + "-01");
				} else if (temp.length == 3){
					date = Date.valueOf(temp[0] + "-" + temp[1] + "-" + temp[2]);
				}
			}catch (Exception e){
				date = Date.valueOf("1800-1-1");
			}
		}
		System.out.println("date" + date);
		return date;
	}
}
