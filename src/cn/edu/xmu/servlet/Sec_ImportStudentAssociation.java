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
import cn.edu.xmu.dao.StudentAssociationDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.StudentAssociationDaoImpl;
import cn.edu.xmu.entity.StudentAssociation;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * @author zhantu 
 * Servlet implementation class Sec_ImportStudentAssociation 
 * date 2015-07-12
 */
@WebServlet("/ImportStudentAssociation")
public class Sec_ImportStudentAssociation extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ImportStudentAssociation() {
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
		List<StudentAssociation> saList = new ArrayList<StudentAssociation>();

		TableListDao tableListDao = new TableListDaoImpl();
		StudentAssociationDao saDao = new StudentAssociationDaoImpl();

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
			if (tablename.equals("表6-3学生社团")) {
				saList = getAllsaByExcel(completeFilePath);
				saDao.deleteByCollegeandDeadline(college, null);
				recordcount = saList.size();
				for (int i = 0; i < saList.size(); i++) {
					saDao.addRecord(saList.get(i));
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
	public static List<StudentAssociation> getAllsaByExcel(String file) {
		errorrow = 1;
		List<StudentAssociation> saList = new ArrayList<StudentAssociation>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			//int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(" rows:" + rows);
			for (int i = 2; i < rows; i++) {
				for (int j = 2; j < 3; j++) {
					// 第一个是列数，第二个是行数
					String sa_sciencecounttemp = rs.getCell(j, ++i).getContents();
					String sa_humanisticcounttemp = rs.getCell(j, ++i).getContents();
					String sa_sportscounttemp = rs.getCell(j, ++i).getContents();
					String sa_literatureartcounttemp = rs.getCell(j, ++i).getContents();
					String sa_othercounttemp = rs.getCell(j, ++i).getContents();
					
					i++;//跳过总计
					String sa_sciencepersontemp = rs.getCell(j, ++i).getContents();
					String sa_humanisticpersontemp = rs.getCell(j, ++i).getContents();
					String sa_sportspersontemp = rs.getCell(j, ++i).getContents();
					String sa_literatureartpersontemp = rs.getCell(j, ++i).getContents();
					String sa_otherpersontemp = rs.getCell(j, ++i).getContents();
					
					Integer sa_sciencecount = null;
					Integer sa_humanisticcount = null;
					Integer sa_sportscount = null;
					Integer sa_literatureartcount = null;
					Integer sa_othercount = null;
					
					Integer sa_scienceperson = null;
					Integer sa_humanisticperson = null;
					Integer sa_sportsperson = null;
					Integer sa_literatureartperson = null;
					Integer sa_otherperson = null;
					
					if(!sa_sciencecounttemp.equals(""))
						sa_sciencecount = Integer.valueOf(sa_sciencecounttemp);
					if(!sa_humanisticcounttemp.equals(""))
						sa_humanisticcount = Integer.valueOf(sa_humanisticcounttemp);
					if(!sa_sportscounttemp.equals(""))
						sa_sportscount = Integer.valueOf(sa_sportscounttemp);
					if(!sa_literatureartcounttemp.equals(""))
						sa_literatureartcount = Integer.valueOf(sa_literatureartcounttemp);
					if(!sa_othercounttemp.equals(""))
						sa_othercount = Integer.valueOf(sa_othercounttemp);
					
					if(!sa_sciencepersontemp.equals(""))
						sa_scienceperson = Integer.valueOf(sa_sciencepersontemp);
					if(!sa_humanisticpersontemp.equals(""))
						sa_humanisticperson = Integer.valueOf(sa_humanisticpersontemp);
					if(!sa_sportspersontemp.equals(""))
						sa_sportsperson = Integer.valueOf(sa_sportspersontemp);
					if(!sa_literatureartpersontemp.equals(""))
						sa_literatureartperson = Integer.valueOf(sa_literatureartpersontemp);
					if(!sa_otherpersontemp.equals(""))
						sa_otherperson = Integer.valueOf(sa_otherpersontemp);
					//如果有一个为空,总数记为空
					Integer sa_sumcount = null;
					
					if(sa_sciencecount!=null && sa_humanisticcount!=null && sa_sportscount!=null && 
							sa_literatureartcount!=null && sa_othercount!=null)
						sa_sumcount = sa_sciencecount+sa_humanisticcount+sa_sportscount+sa_literatureartcount+sa_othercount;
					
					Integer sa_sumperson = null;
					
					if(sa_scienceperson!=null && sa_humanisticperson!=null && sa_sportsperson!=null && 
							sa_literatureartperson!=null && sa_otherperson!=null)
						sa_sumperson = sa_scienceperson+sa_humanisticperson+sa_sportsperson+sa_literatureartperson+sa_otherperson;

					int isnull = 0;
					if(sa_sciencecount==null || sa_humanisticcount==null || sa_sportscount==null || sa_literatureartcount==null || 
							sa_othercount==null || sa_scienceperson==null || sa_humanisticperson==null || 
							sa_sportsperson==null || sa_literatureartperson==null || sa_otherperson==null)
						isnull = 1;
					
					saList.add(new StudentAssociation(sa_sumcount, sa_sciencecount,
							sa_humanisticcount, sa_sportscount,
							sa_literatureartcount, sa_othercount, sa_sumperson,
							sa_scienceperson, sa_humanisticperson, sa_sportsperson,
							sa_literatureartperson, sa_otherperson, college, isnull));
				}
				errorrow++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "导入失败";
		}
		return saList;
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
