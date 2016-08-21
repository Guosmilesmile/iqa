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

import jxl.Sheet;
import jxl.Workbook;

import org.apache.commons.lang.StringUtils;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.TeachingQualityDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.TeachingQualityDaoImpl;
import cn.edu.xmu.entity.TeachingQuality;

/**
 * Servlet implementation class Sec_ImportTeachingQualityServlet
 */
@WebServlet("/Sec_ImportTeachingQualityServlet")
public class Sec_ImportTeachingQualityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sec_ImportTeachingQualityServlet() {
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
		List<TeachingQuality> tqList = new ArrayList<TeachingQuality>();

		TableListDao tableListDao = new TableListDaoImpl();
		TeachingQualityDao tqDao = new TeachingQualityDaoImpl();

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
			if (tablename.equals("表5-1-3课堂教学质量评估统计表")) {
				tqList = getAlltqByExcel(completeFilePath);
				tqDao.deleteByCollegeandDeadline(college, null);
				recordcount = tqList.size();
				for (int i = 0; i < tqList.size(); i++) {
					tqDao.addRecord(tqList.get(i));
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
	public static List<TeachingQuality> getAlltqByExcel(String file) {
		errorrow = 1;
		List<TeachingQuality> tqList = new ArrayList<TeachingQuality>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			int rows = getRightRows(rs);// 得到所有的行
			for (int i = 3; i < rows; i++) {
				for (int j = 0; j < 6; j++) {
					// 第一个是列数，第二个是行数
					String tq_project = rs.getCell(j++, i).getContents();// 默认最左边编号也算一列,所以这里得j++
					
					String coverpercent = rs.getCell(j++, i).getContents();
					float tq_coverpercent = 0;
					if (coverpercent!=null && !"".equals(coverpercent)) {
						tq_coverpercent = Float.parseFloat(coverpercent);
					}
					
					String excellent = rs.getCell(j++, i).getContents();
					float tq_excellent = 0;
					if (excellent!=null && !"".equals(excellent)) {
						tq_excellent = Float.parseFloat(excellent);
					}
					
					String good = rs.getCell(j++, i).getContents();
					float tq_good = 0;
					if (good!=null && !"".equals(good)) {
						tq_good = Float.parseFloat(good);
					}
					
					String medium = rs.getCell(j++, i).getContents();
					float tq_medium = 0;
					if (medium!=null && !"".equals(medium)) {
						tq_medium = Float.parseFloat(medium);
					}
					
					String poor = rs.getCell(j++, i).getContents();
					float tq_poor = 0;
					if (poor!=null && !"".equals(poor)) {
						tq_poor = Float.parseFloat(poor);
					}
					
					//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
					int isnull = 0;
					if(tq_project.equals("") || coverpercent.equals("") || excellent.equals("") || 
							good.equals("") || medium.equals("") || poor.equals("") )
						isnull = 1;
					
					tqList.add(new TeachingQuality(tq_project, tq_coverpercent, tq_excellent,tq_good,
							tq_medium,tq_poor,college,isnull));
				}
				errorrow++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "导入失败";
		}
		return tqList;
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
