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

import cn.edu.xmu.dao.StudentHomeDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.StudentHomeDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.StudentHome;

/**
 * Servlet implementation class Sec_ImportStudentHomeServlet
 */
@WebServlet("/Sec_ImportStudentHomeServlet")
public class Sec_ImportStudentHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_ImportStudentHomeServlet() {
        super();
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
		List<StudentHome> shList = new ArrayList<StudentHome>();

		TableListDao tableListDao = new TableListDaoImpl();
		StudentHomeDao shDao = new StudentHomeDaoImpl();

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
			if (tablename.equals("表2-8学生生活用房")) {
				shList = getAllshByExcel(completeFilePath);
				shDao.deleteByCollegeandDeadline(college, null);
				recordcount = shList.size();
				for (int i = 0; i < shList.size(); i++) {
					shDao.addRecord(shList.get(i));
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
	public static List<StudentHome> getAllshByExcel(String file) {
		errorrow = 1;
		List<StudentHome> shList = new ArrayList<StudentHome>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int rows = getRightRows(rs);// 得到所有的行
			for (int i = 2; i < rows; i++) {
				for (int j = 2; j < 3; j++) {
					// 第一个是列数，第二个是行数
					String diningroomarea = rs.getCell(j, i++).getContents();
					float sh_diningroomarea = -999;
					if (diningroomarea!=null && !"".equals(diningroomarea)) {
						sh_diningroomarea = Float.parseFloat(diningroomarea);
					}
					
					String diningrooncount = rs.getCell(j, i++).getContents();
					int sh_diningrooncount = -999;
					if(!diningrooncount.equals(""))
						sh_diningrooncount = Integer.valueOf(diningrooncount);
					
					String dormitoryarea = rs.getCell(j, i++).getContents();
					float sh_dormitoryarea = -999;
					if (dormitoryarea!=null && !"".equals(dormitoryarea)) {
						sh_dormitoryarea = Float.parseFloat(dormitoryarea);
					}
					
					String dormitorycount = rs.getCell(j, i++).getContents();
					int sh_dormitorycount = -999;
					if(!dormitorycount.equals(""))
						sh_dormitorycount = Integer.valueOf(dormitorycount);
					
					//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
					int isnull = 0;
					if(diningroomarea.equals("") || diningrooncount.equals("") || dormitoryarea.equals("") || 
							dormitorycount.equals("")  )
						isnull = 1;
					
					shList.add(new StudentHome(sh_diningroomarea, sh_diningrooncount, sh_dormitoryarea, sh_dormitorycount,college,isnull));
				}
				errorrow++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "导入失败";
		}
		return shList;
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
