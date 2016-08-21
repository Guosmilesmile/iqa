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
import cn.edu.xmu.dao.MajorInfoDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.MajorInfoDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.MajorInfo;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * @author zsj
 * 
 */
@WebServlet("/ImportMajorInfoServlet")
public class ImportMajorInfoServlet extends HttpServlet implements Servlet {
	static final long serialVersionUID = 1L;
	static String result;
	static int errorrow = 1;
	static String college;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImportMajorInfoServlet() {
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
		List<MajorInfo> tsList = new ArrayList<MajorInfo>();

		TableListDao tableListDao = new TableListDaoImpl();
		MajorInfoDao majorInfoDao = new MajorInfoDaoImpl();

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
			if (tablename.equals("表4-2-2-1专业基本情况")) {
				tsList = getAlltsByExcel(completeFilePath);
				majorInfoDao.deleteByCollegeandDeadline(college);
				recordcount = tsList.size();
				for (int i = 0; i < tsList.size(); i++) {
					majorInfoDao.addMajorInfo(tsList.get(i));
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
	public static List<MajorInfo> getAlltsByExcel(String file) {
		errorrow = 1;
		List<MajorInfo> etList = new ArrayList<MajorInfo>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			//int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(16 + " rows:" + rows);
			for (int i = 4; i < rows; i++) {
				for (int j = 0; j < 29; j++) {
					// 第一个是列数，第二个是行数
					// 默认最左边编号也算一列,所以这里得j++;
					String mi_majorcodeinschool = rs.getCell(j++, i).getContents(); //校内专业代码
					String mi_majornameinschool = rs.getCell(j++, i).getContents(); //校内专业名称
					String mi_codeversion = rs.getCell(j++, i).getContents(); //代码版本
					String mi_majorcode = rs.getCell(j++, i).getContents(); //专业代码
					String mi_majorname = rs.getCell(j++, i).getContents(); //专业名称
					String mi_majorfieldnum = rs.getCell(j++, i).getContents(); //专业方向号
					String mi_majorfieldname = rs.getCell(j++, i).getContents();  //专业方向名称
					String mi_departmentnumber = rs.getCell(j++, i).getContents(); //所属单位号
					String mi_departmentname = rs.getCell(j++, i).getContents();//所属单位名称
					String mi_leaderid = rs.getCell(j++, i).getContents();   //专业带头人工号
					String mi_leadername = rs.getCell(j++, i).getContents();   //专业带头人姓名
					String mi_admissionstate = rs.getCell(j++, i).getContents();  //招生状态
					String mi_majorspecialty = rs.getCell(j++, i).getContents();  //专业特色
					String mi_traininggoal = rs.getCell(j++, i).getContents();   //专业培养目标
					int    mi_schoolingyear = -999;
					String schoolingyear = rs.getCell(j++, i).getContents();   //学制
					if (!"".equals(schoolingyear)) {
						mi_schoolingyear = Integer.parseInt(schoolingyear);
					}
					String mi_setupyear = rs.getCell(j++, i).getContents();   //专业设置年份
					String mi_isnew = rs.getCell(j++, i).getContents();   //是否新专业
					int    mi_allhours = -999;
					String allhours = rs.getCell(j++, i).getContents();  //学时总数
					if (!"".equals(allhours)) {
						mi_allhours = Integer.parseInt(allhours);
					}
					int    mi_musthours = -999;
					String musthoursString = rs.getCell(j++, i).getContents();  //必修课学时
					if (!"".equals(musthoursString)) {
						mi_musthours = Integer.parseInt(musthoursString);
					}
					int    mi_selectedhours = -999;
					String selectedhoursString = rs.getCell(j++, i).getContents();  //选修课学时
					if (!"".equals(selectedhoursString)) {
						mi_selectedhours = Integer.parseInt(selectedhoursString);
					}
					int    mi_inclasshours = -999;
					String inclasshoursString = rs.getCell(j++, i).getContents();  //课内教学学时
					if (!"".equals(inclasshoursString)) {
						mi_inclasshours = Integer.parseInt(inclasshoursString);
					}
					int    mi_experimenthours = -999;
					String experimethoursString = rs.getCell(j++, i).getContents();  //实验教学学时
					if (!"".equals(experimethoursString)) {
						mi_experimenthours = Integer.parseInt(experimethoursString);
					}
					float  mi_allcredits = -999;
					String allcreditString = rs.getCell(j++, i).getContents();  //学分总数
					if (!"".equals(allcreditString)) {
						mi_allcredits = Float.parseFloat(allcreditString);
					}
					float  mi_mustcredits = -999;
					String mustcreditsString = rs.getCell(j++, i).getContents();  //必修课学分
					if (!"".equals(mustcreditsString)) {
						mi_mustcredits = Float.parseFloat(mustcreditsString);
					}
					float  mi_selectedcredits = -999;
					String selectedcreditsString = rs.getCell(j++, i).getContents();  //选修课学分
					if (!"".equals(selectedcreditsString)) {
						mi_selectedcredits = Float.parseFloat(selectedcreditsString);
					}
					float  mi_concentratedpracticecredits = -999;
					String concen = rs.getCell(j++, i).getContents();   //集中性实践教学环节学分
					if (!"".equals(concen)) {
						mi_concentratedpracticecredits = Float.parseFloat(concen);
					}
					float  mi_inclasscredits = -999;
					String inclasscreString = rs.getCell(j++, i).getContents();  //课内教学学分
					if (!"".equals(inclasscreString)) {
						mi_inclasscredits = Float.parseFloat(inclasscreString);
					}
					float  mi_experimentcredits = -999;
					String experString = rs.getCell(j++, i).getContents();  //实验教学学分
					if (!"".equals(experString)) {
						mi_experimentcredits = Float.parseFloat(experString);
					}
					float  mi_outclassactivitycredits = -999;
					String outclassaString = rs.getCell(j++, i).getContents(); 
					if (!"".equals(outclassaString)) {
						mi_outclassactivitycredits = Float.parseFloat(outclassaString);
					}
					
					
					//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
					int isnull = 0;
					if(mi_majorcodeinschool.equals("") || mi_majornameinschool.equals("") || mi_majorname.equals("") || 
							mi_majorcode.equals("") || mi_codeversion.equals("") || mi_majorfieldnum.equals("") ||
							mi_majorfieldname.equals("") || mi_departmentnumber.equals("") || mi_leaderid.equals("") ||
							mi_leadername.equals("") ||mi_admissionstate.equals("") || mi_majorspecialty.equals("") || mi_traininggoal.equals("") ||
							schoolingyear.equals("") || mi_setupyear.equals("") || mi_isnew.equals("") ||
							allhours.equals("") || musthoursString.equals("")||selectedhoursString.equals("") || inclasshoursString.equals("") || 
							experimethoursString.equals("") || allcreditString.equals("") || mustcreditsString.equals("") || selectedcreditsString.equals("") ||
							concen.equals("") || inclasscreString.equals("") || experString.equals("") ||
							outclassaString.equals(""))
						isnull = 1;
					
					MajorInfo majorInfo = new MajorInfo(mi_majorcodeinschool, mi_majornameinschool, mi_codeversion, mi_majorcode, mi_majorname, mi_majorfieldnum, mi_majorfieldname, mi_departmentnumber, mi_departmentname, mi_leaderid, mi_leadername, mi_admissionstate, mi_majorspecialty, mi_traininggoal, mi_schoolingyear, mi_setupyear, mi_isnew, mi_allhours, mi_musthours, mi_selectedhours, mi_inclasshours, mi_experimenthours, mi_allcredits, mi_mustcredits, mi_selectedcredits, mi_concentratedpracticecredits, mi_inclasscredits, mi_experimentcredits, mi_outclassactivitycredits, college, isnull);
					etList.add(majorInfo);
					
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
	static int getRightRows(Sheet sheet) {
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
