package cn.edu.xmu.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import jxl.Sheet;
import jxl.Workbook;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import cn.edu.xmu.dao.ListenSituationDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.ListenSituationDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.ListenSituation;

/**
 * Servlet implementation class Sec_ImportListenSituationServlet
 */
@WebServlet("/Sec_ImportListenSituationServlet")
public class Sec_ImportListenSituationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_ImportListenSituationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;Charset=UTF-8");
		String tableid = request.getParameter("tableid");
	    college = request.getParameter("college");
		college = URLDecoder.decode(college, "utf-8");
		int recordcount = 0;
		List<ListenSituation> tsList = new ArrayList<ListenSituation>();

		TableListDao tableListDao = new TableListDaoImpl();
		ListenSituationDao lsDao = new ListenSituationDaoImpl();

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
			if (tablename.equals("附表7-1-3党政管理干部听课情况")) {
				tsList = getAlllsByExcel(completeFilePath);
				lsDao.deleteByCollegeandDeadline(college, null);
				recordcount = tsList.size();
				for (int i = 0; i < tsList.size(); i++) {
					lsDao.addRecord(tsList.get(i));
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
	public static List<ListenSituation> getAlllsByExcel(String file) {
		errorrow = 1;
		List<ListenSituation> tsList = new ArrayList<ListenSituation>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int rows = getRightRows(rs);// 得到所有的行
			for (int i = 3; i < rows; i++) {
				for (int j = 0; j < 9; j++) {
					// 第一个是列数，第二个是行数
					String ls_importcollege = rs.getCell(j++, i).getContents();
					
					String personnumber = rs.getCell(j++, i).getContents();
					int ls_personnumber = -999;
					if(!personnumber.equals(""))
						ls_personnumber = Integer.valueOf(personnumber);
					
					String lessonnumber = rs.getCell(j++, i).getContents();
					int ls_lessonnumber = -999;
					if(!lessonnumber.equals(""))
						ls_lessonnumber = Integer.valueOf(lessonnumber);
					
					String personnumber2 = rs.getCell(j++, i).getContents();
					int ls_personnumber2 = -999;
					if(!personnumber2.equals(""))
						ls_personnumber2 = Integer.valueOf(personnumber2);
					
					String lessonnumber2 = rs.getCell(j++, i).getContents();
					int ls_lessonnumber2 = -999;
					if(!lessonnumber2.equals(""))
						ls_lessonnumber2 = Integer.valueOf(lessonnumber2);
					
					String excellent = rs.getCell(j++, i).getContents();
					int ls_excellent = -999;
					if(!excellent.equals(""))
						ls_excellent = Integer.valueOf(excellent);
					
					String good = rs.getCell(j++, i).getContents();
					int ls_good = -999;
					if(!good.equals(""))
						ls_good = Integer.valueOf(good);
					
					String medium = rs.getCell(j++, i).getContents();
					int ls_medium = -999;
					if(!medium.equals(""))
						ls_medium = Integer.valueOf(medium);
					
					String bad = rs.getCell(j++, i).getContents();
					int ls_bad = -999;
					if(!bad.equals(""))
						ls_bad = Integer.valueOf(bad);
					
					//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
					int isnull = 0;
					if(ls_importcollege.equals("") || personnumber.equals("") || lessonnumber.equals("") || 
							personnumber2.equals("") || lessonnumber2.equals("") || excellent.equals("") ||
							good.equals("") || medium.equals("") || bad.equals("") )
						isnull = 1;
					
					tsList.add(new ListenSituation(ls_importcollege, ls_personnumber, ls_lessonnumber,
							ls_personnumber2,ls_lessonnumber2,ls_excellent,ls_good,ls_medium,ls_bad,college,isnull));
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
