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

import cn.edu.xmu.dao.ForeignStudentInfoDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.ForeignStudentInfoDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.ForeignStudentInfo;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

@WebServlet("/Sec_ImportForeignStudentInfoServlet")
public class Sec_ImportForeignStudentInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ImportForeignStudentInfoServlet() {
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
		List<ForeignStudentInfo> tsList = new ArrayList<ForeignStudentInfo>();

		TableListDao tableListDao = new TableListDaoImpl();
		ForeignStudentInfoDao asDao = new ForeignStudentInfoDaoImpl();

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
			if (tablename.equals("表6-1-4国外及港澳台学生情况（时点）")) {
				tsList = getAlltsByExcel(completeFilePath);
				asDao.deleteByCollegeandDeadline(college, null);
				recordcount = tsList.size();
				for (int i = 0; i < tsList.size(); i++) {
					asDao.addForeignStudentInfoRecord(tsList.get(i));
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
	public static List<ForeignStudentInfo> getAlltsByExcel(String file) {
		errorrow = 1;
		List<ForeignStudentInfo> asList = new ArrayList<ForeignStudentInfo>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			//int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(4 + " rows:" + rows);
			for (int i = 2; i < rows; i++) {
				Integer fsi_allgraduatenumber=0;
				Integer fsi_alldegreenumber=0;
				Integer fsi_allenrollnumber=0;
				Integer fsi_allcurrentstudentnumber=0;
				Integer fsi_foreigngraduatenumber=0;
				Integer fsi_foreigndegreenumber=0;
				Integer fsi_foreignenrollnumber=0;
				Integer fsi_foreigncurrentstudentnumber=0;
				Integer fsi_hkgraduatenumber=0;
				Integer fsi_hkdegreenumber=0;
				Integer fsi_hkenrollnumber = 0;
				Integer fsi_hkcurrentstudentnumber = 0;
				Integer fsi_macgraduatenumber=0;
				Integer fsi_macdegreenumber=0;
				Integer fsi_macenrollnumber=0;
				Integer fsi_maccurrentstudentnumber=0;
				Integer fsi_twngraduatenumber=0;
				Integer fsi_twndegreenumber=0;
				Integer fsi_twnenrollnumber=0;
				Integer fsi_twncurrentstudentnumber=0;
				
				int j=2;
					// 第一个是列数，第二个是行数
					String allgraduatenumber = rs.getCell(j++, i).getContents();
					fsi_allgraduatenumber = -999;
					if(!allgraduatenumber.equals(""))
						fsi_allgraduatenumber = Integer.valueOf(allgraduatenumber);
					String alldegreenumber = rs.getCell(j++, i).getContents();
					fsi_alldegreenumber = -999;
					if(!alldegreenumber.equals(""))
						fsi_alldegreenumber = Integer.valueOf(alldegreenumber);
					String allenrollnumber = rs.getCell(j++, i).getContents();
					fsi_allenrollnumber = -999;
					if(!allenrollnumber.equals(""))
						fsi_allenrollnumber = Integer.valueOf(allenrollnumber);
					String allcurrentstudentnumber = rs.getCell(j++, i).getContents();
					fsi_allcurrentstudentnumber = -999;
					if(!allcurrentstudentnumber.equals(""))
						fsi_allcurrentstudentnumber = Integer.valueOf(allcurrentstudentnumber);
				j=2;	
					String foreigngraduatenumber  = rs.getCell(j++, ++i).getContents();
					fsi_foreigngraduatenumber = -999;
					if(!foreigngraduatenumber.equals(""))
						fsi_foreigngraduatenumber = Integer.valueOf(foreigngraduatenumber);
					String foreigndegreenumber = rs.getCell(j++, i).getContents();
					fsi_foreigndegreenumber = -999;
					if(!foreigndegreenumber.equals(""))
						fsi_foreigndegreenumber = Integer.valueOf(foreigndegreenumber);
					String foreignenrollnumber = rs.getCell(j++, i).getContents();
					fsi_foreignenrollnumber = -999;
					if(!foreignenrollnumber.equals(""))
						fsi_foreignenrollnumber = Integer.valueOf(foreignenrollnumber);
					String foreigncurrentstudentnumber = rs.getCell(j++, i).getContents();
					fsi_foreigncurrentstudentnumber = -999;
					if(!foreigncurrentstudentnumber.equals(""))
						fsi_foreigncurrentstudentnumber = Integer.valueOf(foreigncurrentstudentnumber);
//					 fsi_foreigngraduatenumber = Integer.parseInt(rs.getCell(j++, i++).getContents());// 默认最左边编号也算一列,所以这里得j++
//					 fsi_foreigndegreenumber = Integer.parseInt(rs.getCell(j++, i).getContents());
//					 fsi_foreignenrollnumber = Integer.parseInt(rs.getCell(j++, i).getContents());
//					 fsi_foreigncurrentstudentnumber = Integer.parseInt(rs.getCell(j++, i).getContents());
					j=2;
					String hkgraduatenumber = rs.getCell(j++, ++i).getContents();
					fsi_hkgraduatenumber = -999;
					if(!hkgraduatenumber.equals(""))
						fsi_hkgraduatenumber = Integer.valueOf(hkgraduatenumber);
					String hkdegreenumber = rs.getCell(j++, i).getContents();
					fsi_hkdegreenumber = -999;
					if(!hkdegreenumber.equals(""))
						fsi_hkdegreenumber = Integer.valueOf(hkdegreenumber);
					String hkenrollnumber = rs.getCell(j++, i).getContents();
					fsi_hkenrollnumber = -999;
					if(!hkenrollnumber.equals(""))
						fsi_hkenrollnumber = Integer.valueOf(hkenrollnumber);
					String hkcurrentstudentnumber = rs.getCell(j++, i).getContents();
					fsi_hkcurrentstudentnumber = -999;
					if(!hkcurrentstudentnumber.equals(""))
						fsi_hkcurrentstudentnumber = Integer.valueOf(hkcurrentstudentnumber);
//					 fsi_hkgraduatenumber = Integer.parseInt(rs.getCell(j++, i++).getContents());// 默认最左边编号也算一列,所以这里得j++
//					 fsi_hkdegreenumber = Integer.parseInt(rs.getCell(j++, i).getContents());
//					 fsi_hkenrollnumber = Integer.parseInt(rs.getCell(j++, i).getContents());
//					 fsi_hkcurrentstudentnumber = Integer.parseInt(rs.getCell(j++, i).getContents());
				j=2;
					String macgraduatenumber = rs.getCell(j++, ++i).getContents();
					fsi_macgraduatenumber = -999;
					if(!macgraduatenumber.equals(""))
						fsi_macgraduatenumber = Integer.valueOf(macgraduatenumber);
					String macdegreenumber = rs.getCell(j++, i).getContents();
					fsi_macdegreenumber = -999;
					if(!macdegreenumber.equals(""))
						fsi_macdegreenumber = Integer.valueOf(macdegreenumber);
					String macenrollnumber = rs.getCell(j++, i).getContents();
					fsi_macenrollnumber = -999;
					if(!macenrollnumber.equals(""))
						fsi_macenrollnumber = Integer.valueOf(macenrollnumber);
					String maccurrentstudentnumber = rs.getCell(j++, i).getContents();
					fsi_maccurrentstudentnumber = -999;
					if(!maccurrentstudentnumber.equals(""))
						fsi_maccurrentstudentnumber = Integer.valueOf(maccurrentstudentnumber);
//					 fsi_macgraduatenumber = Integer.parseInt(rs.getCell(j++, i++).getContents());// 默认最左边编号也算一列,所以这里得j++
//					 fsi_macdegreenumber = Integer.parseInt(rs.getCell(j++, i).getContents());
//					 fsi_macenrollnumber = Integer.parseInt(rs.getCell(j++, i).getContents());
//					 fsi_maccurrentstudentnumber = Integer.parseInt(rs.getCell(j++, i).getContents());
				j=2;
					String twngraduatenumber = rs.getCell(j++, ++i).getContents();
					fsi_twngraduatenumber = -999;
					if(!twngraduatenumber.equals(""))
						fsi_twngraduatenumber = Integer.valueOf(twngraduatenumber);
					String twndegreenumber = rs.getCell(j++, i).getContents();
					fsi_twndegreenumber = -999;
					if(!twndegreenumber.equals(""))
						fsi_twndegreenumber = Integer.valueOf(twndegreenumber);
					String twnenrollnumber = rs.getCell(j++, i).getContents();
					fsi_twnenrollnumber = -999;
					if(!twnenrollnumber.equals(""))
						fsi_twnenrollnumber = Integer.valueOf(twnenrollnumber);
					String twncurrentstudentnumber = rs.getCell(j++, i).getContents();
					fsi_twncurrentstudentnumber = -999;
					if(!twncurrentstudentnumber.equals(""))
						fsi_twncurrentstudentnumber = Integer.valueOf(twncurrentstudentnumber);
//					 fsi_twngraduatenumber = Integer.parseInt(rs.getCell(j++, i++).getContents());// 默认最左边编号也算一列,所以这里得j++
//					 fsi_twndegreenumber = Integer.parseInt(rs.getCell(j++, i).getContents());
//					 fsi_twnenrollnumber = Integer.parseInt(rs.getCell(j++, i).getContents());
//					 fsi_twncurrentstudentnumber = Integer.parseInt(rs.getCell(j++, i).getContents());
				
				//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
				int isnull = 0;
				if(allgraduatenumber.equals("")||alldegreenumber.equals("")||allenrollnumber.equals("")||allcurrentstudentnumber.equals("")||
						foreigngraduatenumber.equals("")||foreigndegreenumber.equals("")||foreignenrollnumber.equals("")||
						foreigncurrentstudentnumber.equals("")||hkgraduatenumber.equals("")||hkdegreenumber.equals("")||
						hkenrollnumber.equals("")||hkcurrentstudentnumber.equals("")||macgraduatenumber.equals("")||
						macdegreenumber.equals("")||macenrollnumber.equals("")||maccurrentstudentnumber.equals("")||
						twngraduatenumber.equals("")||twndegreenumber.equals("")||twnenrollnumber.equals("")||
						twncurrentstudentnumber.equals(""))
					isnull = 1;
				
					asList.add(new ForeignStudentInfo(fsi_allgraduatenumber, fsi_alldegreenumber,
							fsi_allenrollnumber, fsi_allcurrentstudentnumber, fsi_foreigngraduatenumber, 
							fsi_foreigndegreenumber, fsi_foreignenrollnumber, fsi_foreigncurrentstudentnumber,
							fsi_hkgraduatenumber, fsi_hkdegreenumber, fsi_hkenrollnumber, 
							fsi_hkcurrentstudentnumber, fsi_macgraduatenumber, fsi_macdegreenumber, 
							fsi_macenrollnumber, fsi_maccurrentstudentnumber, fsi_twngraduatenumber, 
							fsi_twndegreenumber, fsi_twnenrollnumber, fsi_twncurrentstudentnumber, college,isnull));

				errorrow++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "导入失败";
		}
		return asList;
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
