package cn.edu.xmu.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import cn.edu.xmu.dao.StuPhysicalHealthInfoDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.StuPhysicalHealthInfoDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.StuPhysicalHealthInfo;
import cn.edu.xmu.table.StuPhysicalHealthInfoTable;
import jxl.Cell;
import jxl.CellType;
import jxl.DateCell;
import jxl.Sheet;
import jxl.Workbook;

@WebServlet("/Sec_ImportStuPhysicalHealthInfoServlet")
public class Sec_ImportStuPhysicalHealthInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ImportStuPhysicalHealthInfoServlet() {
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
		List<StuPhysicalHealthInfo> tsList = new ArrayList<StuPhysicalHealthInfo>();

		TableListDao tableListDao = new TableListDaoImpl();
		StuPhysicalHealthInfoDao sphiDao = new StuPhysicalHealthInfoDaoImpl();

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
			if (tablename.equals("附表6-2-1-8厦门大学学生体质健康情况（学年）")) {
			    tsList = getAlltsByExcel(completeFilePath);
			    sphiDao.deleteByCollegeandDeadline(college, null);
				recordcount = tsList.size();
				for (int i = 0; i < tsList.size(); i++) {
					sphiDao.addStuPhysicalHealthInfoRecord(tsList.get(i));
				}
				//更新总计的记录：
				//找当前记录中college为当前用户college且deadline为空，学院名为“合计”的记录
				List<StuPhysicalHealthInfo> sums= sphiDao.getSPHISumorNoSum(college, true);
				//找当前记录中college为当前用户college且deadline为空，学院名不为“合计”的记录
				List<StuPhysicalHealthInfo> nosums= sphiDao.getSPHISumorNoSum(college, false);
				//算出总计的各个值
				int sum_sphi_totalnumber = 0,sum_sphi_freetestnumber = 0,sum_sphi_testnumber = 0,sum_sphi_passnumber = 0,
						sum_sphi_goodnumber = 0, sum_sphi_excellentnumber = 0;
				int sum_sphi_serialnumber = nosums.get(0).getSphi_serialnumber();//显示当前这个学院数据的最上面
				for(StuPhysicalHealthInfo sphi1:nosums)
				{
					if(sphi1.getSphi_totalnumber() != null && !"".equals(sphi1.getSphi_totalnumber()))
					{
						if(sphi1.getSphi_totalnumber()>0)
							sum_sphi_totalnumber += sphi1.getSphi_totalnumber();
					}
					if(sphi1.getSphi_freetestnumber() != null && !"".equals(sphi1.getSphi_freetestnumber()))
					{
						if(sphi1.getSphi_freetestnumber()>0)
							sum_sphi_freetestnumber += sphi1.getSphi_freetestnumber();
					}
					if(sphi1.getSphi_testnumber() != null && !"".equals(sphi1.getSphi_testnumber()))
					{
						if(sphi1.getSphi_testnumber()>0)
							sum_sphi_testnumber += sphi1.getSphi_testnumber();
					}
					if(sphi1.getSphi_passnumber() != null && !"".equals(sphi1.getSphi_passnumber()))
					{
						if(sphi1.getSphi_passnumber()>0)
							sum_sphi_passnumber += sphi1.getSphi_passnumber();
					}
					if(sphi1.getSphi_goodnumber() != null && !"".equals(sphi1.getSphi_goodnumber()))
					{
						if(sphi1.getSphi_goodnumber()>0)
							sum_sphi_goodnumber += sphi1.getSphi_goodnumber();
					}
					if(sphi1.getSphi_excellentnumber() != null && !"".equals(sphi1.getSphi_excellentnumber()))
					{
						if(sphi1.getSphi_excellentnumber()>0)
							sum_sphi_excellentnumber += sphi1.getSphi_excellentnumber();
					}
					if(sum_sphi_serialnumber>sphi1.getSphi_serialnumber())
						sum_sphi_serialnumber = sphi1.getSphi_serialnumber();
				}
				sum_sphi_serialnumber--;
				//记录当中还没有总计的记录需要新建一条学院叫合计的记录
				if(sums.size()==0)
				{
					StuPhysicalHealthInfo sphi1 = new StuPhysicalHealthInfo("合计", sum_sphi_totalnumber, 
							sum_sphi_freetestnumber, sum_sphi_testnumber, sum_sphi_passnumber, sum_sphi_goodnumber,
							sum_sphi_excellentnumber, sum_sphi_serialnumber, college, "",0);
					sphiDao.addStuPhysicalHealthInfoRecord(sphi1);
				}
				//记录当中已经存在一条学院叫总计的记录，对他做更新操作
				else
				{
					int id = sums.get(0).getSphi_id();
					Map<String,String> params= new HashMap<String, String>();
					params.put(StuPhysicalHealthInfoTable.SPHI_ID, Integer.toString(id));
					params.put(StuPhysicalHealthInfoTable.SPHI_GRADE, "合计");
					params.put(StuPhysicalHealthInfoTable.SPHI_TOTALNUMBER, sum_sphi_totalnumber+"");
					params.put(StuPhysicalHealthInfoTable.SPHI_FREETESTNUMBER, sum_sphi_freetestnumber+"");
					params.put(StuPhysicalHealthInfoTable.SPHI_TESTNUMBER, sum_sphi_testnumber+"");
					params.put(StuPhysicalHealthInfoTable.SPHI_PASSNUMBER, sum_sphi_passnumber+"");
					params.put(StuPhysicalHealthInfoTable.SPHI_GOODNUMBER, sum_sphi_goodnumber+"");
					params.put(StuPhysicalHealthInfoTable.SPHI_EXCELLENTNUMBER, sum_sphi_excellentnumber+"");
					if(sums.get(0).getSphi_comments()==null || sums.get(0).getSphi_comments().equals(""))
						params.put(StuPhysicalHealthInfoTable.SPHI_COMMENTS, "");
					else
						params.put(StuPhysicalHealthInfoTable.SPHI_COMMENTS, sums.get(0).getSphi_comments());
				
					int result = sphiDao.alterStuPhysicalHealthInfoInfo(params, Integer.toString(id));
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
	public static List<StuPhysicalHealthInfo> getAlltsByExcel(String file) {
		errorrow = 1;
		List<StuPhysicalHealthInfo> mriList = new ArrayList<StuPhysicalHealthInfo>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			//int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println("clos:" +7 + " rows:" + rows);
			for (int i = 2; i < rows; i++) {
				for (int j = 0; j < 7; j++) {
					// 第一个是列数，第二个是行数
					String sphi_grade = rs.getCell(j++, i).getContents();// 默认最左边编号也算一列,所以这里得j++
					String totalnumber = rs.getCell(j++, i).getContents();
					Integer sphi_totalnumber = -999;
					if(!totalnumber.equals(""))
						sphi_totalnumber = Integer.valueOf(totalnumber);
					String freetestnumber = rs.getCell(j++, i).getContents();
					Integer sphi_freetestnumber = -999;
					if(!freetestnumber.equals(""))
						sphi_freetestnumber = Integer.valueOf(freetestnumber);
					String testnumber = rs.getCell(j++, i).getContents();
					Integer sphi_testnumber = -999;
					if(!testnumber.equals(""))
						sphi_testnumber = Integer.valueOf(testnumber);
					String passsnumber = rs.getCell(j++, i).getContents();
					Integer sphi_passnumber = -999;
					if(!passsnumber.equals(""))
						sphi_passnumber = Integer.valueOf(passsnumber);
					String goodnumber= rs.getCell(j++, i).getContents();
					Integer sphi_goodnumber = -999;
					if(!goodnumber.equals(""))
						sphi_goodnumber = Integer.valueOf(goodnumber);
					String excellentnumber = rs.getCell(j++, i).getContents();
					Integer sphi_excellentnumber = -999;
					if(!excellentnumber.equals(""))
						sphi_excellentnumber = Integer.valueOf(excellentnumber);
//					int sphi_totalnumber = Integer.parseInt( rs.getCell(j++, i).getContents());
//					int sphi_freetestnumber = Integer.parseInt( rs.getCell(j++, i).getContents());
//					int sphi_testnumber = Integer.parseInt( rs.getCell(j++, i).getContents());
//					int sphi_passnumber = Integer.parseInt( rs.getCell(j++, i).getContents());
//					int sphi_goodnumber = Integer.parseInt( rs.getCell(j++, i).getContents());
//					int sphi_excellentnumber = Integer.parseInt( rs.getCell(j++, i).getContents());
					//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
					int isnull = 0;
					if(sphi_grade.equals("")||freetestnumber.equals("")||testnumber.equals("")||passsnumber.equals("")||
							goodnumber.equals("")||excellentnumber.equals("")||sphi_totalnumber.equals(""))

						isnull = 1;
					
					mriList.add(new StuPhysicalHealthInfo(sphi_grade, sphi_totalnumber, 
							sphi_freetestnumber, sphi_testnumber, sphi_passnumber,
							sphi_goodnumber, sphi_excellentnumber,college,isnull));
				}
				errorrow++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "导入失败";
		}
		return mriList;
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
