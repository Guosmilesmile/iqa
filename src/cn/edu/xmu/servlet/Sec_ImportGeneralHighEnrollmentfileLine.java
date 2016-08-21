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
import cn.edu.xmu.dao.GeneralHighEnrollmentfileLineDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.GeneralHighEnrollmentfileLineDaoImpl;
import cn.edu.xmu.entity.GeneralHighEnrollmentfileLine;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * @author zhantu 
 * Servlet implementation class Sec_ImportGeneralHighEnrollmentfileLine 
 * date 2015-07-13
 */
@WebServlet("/ImportGeneralHighEnrollmentfileLine")
public class Sec_ImportGeneralHighEnrollmentfileLine extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ImportGeneralHighEnrollmentfileLine() {
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
		List<GeneralHighEnrollmentfileLine> ghelList = new ArrayList<GeneralHighEnrollmentfileLine>();

		TableListDao tableListDao = new TableListDaoImpl();
		GeneralHighEnrollmentfileLineDao ghelDao = new GeneralHighEnrollmentfileLineDaoImpl();

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
			if (tablename.equals("附表6-1-5-2厦门大学普高招生各省份出档线高出本一线分值")) {
				ghelList = getAllghelByExcel(completeFilePath);
				ghelDao.deleteByCollegeandDeadline(college, null);
				recordcount = ghelList.size();
				for (int i = 0; i < ghelList.size(); i++) {
					ghelDao.addRecord(ghelList.get(i));
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
	public static List<GeneralHighEnrollmentfileLine> getAllghelByExcel(String file) {
		errorrow = 1;
		List<GeneralHighEnrollmentfileLine> ghelList = new ArrayList<GeneralHighEnrollmentfileLine>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			//int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println("rows:" + rows);
			for (int i = 1; i < rows; i++) {
				for (int j = 0; j < 8; j++) {
					// 第一个是列数，第二个是行数
					String tempString = rs.getCell(j++, i++).getContents();
					String ghel_type = null;
					if(tempString.contains("文史"))
						ghel_type = "文史";
					else if(tempString.contains("理工")){
						ghel_type = "理工";
					}
					tempString = rs.getCell(j, ++i).getContents();
					float ghel_hainan = -999;
					if(!tempString.equals(""))
						ghel_hainan = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_xinjiang = -999;
					if(!tempString.equals(""))
						ghel_xinjiang = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_xizangshao = -999;
					if(!tempString.equals(""))
						ghel_xizangshao = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_yunnan = -999;
					if(!tempString.equals(""))
						ghel_yunnan = Float.valueOf(tempString);
					
					j = 1;i++;
					tempString = rs.getCell(j, i).getContents();
					float ghel_shanxishan = -999;
					if(!tempString.equals(""))
						ghel_shanxishan = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_tianjin = -999;
					if(!tempString.equals(""))
						ghel_tianjin = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_ningxia = -999;
					if(!tempString.equals(""))
						ghel_ningxia = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_guizhou = -999;
					if(!tempString.equals(""))
						ghel_guizhou = Float.valueOf(tempString);
					
					j = 1;i++;
					tempString = rs.getCell(j, i).getContents();
					float ghel_liaoning = -999;
					if(!tempString.equals(""))
						ghel_liaoning = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_xizanghan = -999;
					if(!tempString.equals(""))
						ghel_xizanghan = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_jilin = -999;
					if(!tempString.equals(""))
						ghel_jilin = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_guangxi = -999;
					if(!tempString.equals(""))
						ghel_guangxi = Float.valueOf(tempString);
					
					j = 1;i++;
					tempString = rs.getCell(j, i).getContents();
					float ghel_zhejiang = -999;
					if(!tempString.equals(""))
						ghel_zhejiang = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_chongqing = -999;
					if(!tempString.equals(""))
						ghel_chongqing = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_anhui = -999;
					if(!tempString.equals(""))
						ghel_anhui = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_heilongjiang = -999;
					if(!tempString.equals(""))
						ghel_heilongjiang = Float.valueOf(tempString);
					
					j = 1;i++;
					tempString = rs.getCell(j, i).getContents();
					float ghel_jiangxi = -999;
					if(!tempString.equals(""))
						ghel_jiangxi = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_sichuan = -999;
					if(!tempString.equals(""))
						ghel_sichuan = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_beijing = -999;
					if(!tempString.equals(""))
						ghel_beijing = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_henan = -999;
					if(!tempString.equals(""))
						ghel_henan = Float.valueOf(tempString);
					
					j = 1;i++;
					tempString = rs.getCell(j, i).getContents();
					float ghel_hunan = -999;
					if(!tempString.equals(""))
						ghel_hunan = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_shanghai = -999;
					if(!tempString.equals(""))
						ghel_shanghai = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_fujian = -999;
					if(!tempString.equals(""))
						ghel_fujian = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_shandong = -999;
					if(!tempString.equals(""))
						ghel_shandong = Float.valueOf(tempString);
					
					j = 1;i++;
					tempString = rs.getCell(j, i).getContents();
					float ghel_hebei = -999;
					if(!tempString.equals(""))
						ghel_hebei = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_hubei = -999;
					if(!tempString.equals(""))
						ghel_hubei = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_guangdong = -999;
					if(!tempString.equals(""))
						ghel_guangdong = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_jiangsu = -999;
					if(!tempString.equals(""))
						ghel_jiangsu = Float.valueOf(tempString);
					
					j = 1;i++;
					tempString = rs.getCell(j, i).getContents();
					float ghel_shanxijin = -999;
					if(!tempString.equals(""))
						ghel_shanxijin = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_neimenggu = -999;
					if(!tempString.equals(""))
						ghel_neimenggu = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_qinghai = -999;
					if(!tempString.equals(""))
						ghel_qinghai = Float.valueOf(tempString);
					tempString = rs.getCell((j+=2), i).getContents();
					float ghel_gansu = -999;
					if(!tempString.equals(""))
						ghel_gansu = Float.valueOf(tempString);
					i++;
					
					//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
					int isnull = 0;
					if(ghel_type.equals("") || ghel_hainan==-999 || ghel_xinjiang==-999 || ghel_xizangshao==-999 || 
							ghel_yunnan==-999 || ghel_shanxishan==-999 || ghel_tianjin==-999 || 
							ghel_ningxia==-999 || ghel_guizhou==-999 || ghel_liaoning==-999 || 
							ghel_xizanghan==-999 || ghel_jilin==-999 || ghel_guangxi==-999 || 
							ghel_zhejiang==-999 || ghel_chongqing==-999 || ghel_anhui==-999 || 
							ghel_heilongjiang==-999 || ghel_jiangxi==-999 || ghel_sichuan==-999 || 
							ghel_beijing==-999 || ghel_henan==-999 || ghel_hunan==-999 || 
							ghel_shanghai==-999 || ghel_fujian==-999 || ghel_shandong==-999 || 
							ghel_hebei==-999 || ghel_hubei==-999 || ghel_guangdong==-999 || 
							ghel_jiangsu==-999 || ghel_shanxijin==-999 || ghel_neimenggu==-999 || 
							ghel_qinghai==-999 || ghel_gansu==-999)
						isnull = 1;
					
					ghelList.add(new GeneralHighEnrollmentfileLine(ghel_type, ghel_hainan,
							ghel_xinjiang, ghel_xizangshao, ghel_yunnan,
							ghel_shanxishan, ghel_tianjin, ghel_ningxia,
							ghel_guizhou, ghel_liaoning, ghel_xizanghan,
							ghel_jilin, ghel_guangxi, ghel_zhejiang,
							ghel_chongqing, ghel_anhui, ghel_heilongjiang,
							ghel_jiangxi, ghel_sichuan, ghel_beijing,
							ghel_henan, ghel_hunan, ghel_shanghai,
							ghel_fujian, ghel_shandong, ghel_hebei,
							ghel_hubei, ghel_guangdong, ghel_jiangsu,
							ghel_shanxijin, ghel_neimenggu, ghel_qinghai,
							ghel_gansu, college, isnull));
				}
				errorrow++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "导入失败";
		}
		return ghelList;
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
