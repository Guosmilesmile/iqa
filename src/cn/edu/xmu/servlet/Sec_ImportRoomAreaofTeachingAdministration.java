package cn.edu.xmu.servlet;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import cn.edu.xmu.dao.RoomAreaofTeachingAdministrationDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.RoomAreaofTeachingAdministrationDaoImpl;
import cn.edu.xmu.entity.RoomAreaofTeachingAdministration;
import cn.edu.xmu.table.RoomAreaofTeachingAdministrationTable;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

/**
 * @author zhantu 
 * Servlet implementation class Sec_ImportRoomAreaofTeachingAdministration 
 * date 2015-07-11
 */
@WebServlet("/ImportRoomAreaofTeachingAdministration")
public class Sec_ImportRoomAreaofTeachingAdministration extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private static String result;
	private static int errorrow = 1;
	private static String college;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ImportRoomAreaofTeachingAdministration() {
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
		List<RoomAreaofTeachingAdministration> tsList = new ArrayList<RoomAreaofTeachingAdministration>();

		TableListDao tableListDao = new TableListDaoImpl();
		RoomAreaofTeachingAdministrationDao rataDao = new RoomAreaofTeachingAdministrationDaoImpl();

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
			if (tablename.equals("附表2-2各单位教学行政用房面积")) {
				tsList = getAlltsByExcel(completeFilePath);
				rataDao.deleteByCollegeandDeadline(college, null);
				recordcount = tsList.size();
				for (int i = 0; i < tsList.size(); i++) {
					rataDao.addRecord(tsList.get(i));
				}
				//更新总计的记录：
				//找当前记录中college为当前用户college且deadline为空，学院名为“总计”的记录
				List<RoomAreaofTeachingAdministration> sums= rataDao.getRATASumorNoSum(college, true);
				//找当前记录中college为当前用户college且deadline为空，学院名不为“总计”的记录
				List<RoomAreaofTeachingAdministration> nosums= rataDao.getRATASumorNoSum(college, false);
				//算出总计的各个值
				float sum_rata_sum = 0,sum_rata_teachresearchauxiliary = 0,sum_rata_classroom = 0,sum_rata_library = 0,
						sum_rata_lab = 0, sum_rata_privatescienresearch = 0, sum_rata_gym = 0, sum_rata_hall = 0, 
						sum_rata_administrationoffice = 0;
				if(nosums.size()==0)
					return;//当前没有数据
				int sum_rata_serialnumber = nosums.get(0).getRata_serialnumber();//显示当前这个学院数据的最上面
				for(RoomAreaofTeachingAdministration roomAreaofTeachingAdministration:nosums)
				{
					if(roomAreaofTeachingAdministration.getRata_sum()!=null)
						sum_rata_sum += roomAreaofTeachingAdministration.getRata_sum();
					if(roomAreaofTeachingAdministration.getRata_teachresearchauxiliary()!=null)
						sum_rata_teachresearchauxiliary += roomAreaofTeachingAdministration.getRata_teachresearchauxiliary();
					if(roomAreaofTeachingAdministration.getRata_classroom()!=null)
						sum_rata_classroom += roomAreaofTeachingAdministration.getRata_classroom();
					if(roomAreaofTeachingAdministration.getRata_library()!=null)
						sum_rata_library += roomAreaofTeachingAdministration.getRata_library();
					if(roomAreaofTeachingAdministration.getRata_lab()!=null)
						sum_rata_lab += roomAreaofTeachingAdministration.getRata_lab();
					if(roomAreaofTeachingAdministration.getRata_privatescienresearch()!=null)
						sum_rata_privatescienresearch += roomAreaofTeachingAdministration.getRata_privatescienresearch();
					if(roomAreaofTeachingAdministration.getRata_gym()!=null)
						sum_rata_gym += roomAreaofTeachingAdministration.getRata_gym();
					if(roomAreaofTeachingAdministration.getRata_hall()!=null)
						sum_rata_hall += roomAreaofTeachingAdministration.getRata_hall();
					if(roomAreaofTeachingAdministration.getRata_administrationoffice()!=null)
						sum_rata_administrationoffice += roomAreaofTeachingAdministration.getRata_administrationoffice();
					if(sum_rata_serialnumber>roomAreaofTeachingAdministration.getRata_serialnumber())
						sum_rata_serialnumber = roomAreaofTeachingAdministration.getRata_serialnumber();
				}
				sum_rata_serialnumber--;
				//记录当中还没有总计的记录需要新建一条学院叫总计的记录
				if(sums.size()==0)
				{
					RoomAreaofTeachingAdministration sumRoomAreaofTeachingAdministration = new RoomAreaofTeachingAdministration("总计", sum_rata_sum, sum_rata_teachresearchauxiliary,
							sum_rata_classroom, sum_rata_library, sum_rata_lab, sum_rata_privatescienresearch, sum_rata_gym, sum_rata_hall,
							sum_rata_administrationoffice, sum_rata_serialnumber, college, "", 0);
					rataDao.addRecord(sumRoomAreaofTeachingAdministration);
				}
				//记录当中已经存在一条学院叫总计的记录，对他做更新操作
				else
				{
					int id = sums.get(0).getRata_id();
					Map<String,String> params= new HashMap<String, String>();
					params.put(RoomAreaofTeachingAdministrationTable.RATA_ID, Integer.toString(id));
					params.put(RoomAreaofTeachingAdministrationTable.RATA_COLLEGENAME, "总计");
					params.put(RoomAreaofTeachingAdministrationTable.RATA_SUM, sum_rata_sum+"");
					params.put(RoomAreaofTeachingAdministrationTable.RATA_TEACHRESEARCHAUXILIARY, sum_rata_teachresearchauxiliary+"");
					params.put(RoomAreaofTeachingAdministrationTable.RATA_CLASSROOM, sum_rata_classroom+"");
					params.put(RoomAreaofTeachingAdministrationTable.RATA_LIBRARY, sum_rata_library+"");
					params.put(RoomAreaofTeachingAdministrationTable.RATA_LAB, sum_rata_lab+"");
					params.put(RoomAreaofTeachingAdministrationTable.RATA_PRIVATESCIENRESEARCH, sum_rata_privatescienresearch+"");
					params.put(RoomAreaofTeachingAdministrationTable.RATA_GYM, sum_rata_gym+"");
					params.put(RoomAreaofTeachingAdministrationTable.RATA_HALL, sum_rata_hall+"");
					params.put(RoomAreaofTeachingAdministrationTable.RATA_ADMINISTRATIONOFFICE, sum_rata_administrationoffice+"");
					params.put(RoomAreaofTeachingAdministrationTable.RATA_SERIALNUMBER, sum_rata_serialnumber+"");
					if(sums.get(0).getRata_comments()==null || sums.get(0).getRata_comments().equals(""))
						params.put(RoomAreaofTeachingAdministrationTable.RATA_COMMENTS, "");
					else
						params.put(RoomAreaofTeachingAdministrationTable.RATA_COMMENTS, sums.get(0).getRata_comments());
					params.put(RoomAreaofTeachingAdministrationTable.ISNULL, "0");
				
					int result = rataDao.alterRoomAreaofTeachingAdministration(params, Integer.toString(id));
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
	public static List<RoomAreaofTeachingAdministration> getAlltsByExcel(String file) {
		errorrow = 1;
		List<RoomAreaofTeachingAdministration> rataList = new ArrayList<RoomAreaofTeachingAdministration>();
		try {
			Workbook rwb = Workbook.getWorkbook(new File(file));
			Sheet rs = rwb.getSheet(0);// 或者rwb.getSheet(0)
			//int clos = rs.getColumns();// 得到所有的列
			int rows = getRightRows(rs);// 得到所有的行
			System.out.println(" rows:" + rows);
			String tempString = "";
			for (int i = 3; i < rows; i++) {
				for (int j = 0; j < 10; j++) {
					// 第一个是列数，第二个是行数

					String rata_collegename = rs.getCell(j++, i).getContents();
					j++;
					tempString = rs.getCell(j++, i).getContents();
					float rata_teachresearchauxiliary = -999;
					if(!tempString.equals(""))
						rata_teachresearchauxiliary = Float.valueOf(tempString);
					
					tempString = rs.getCell(j++, i).getContents();
					float rata_classroom = -999;
					if(!tempString.equals(""))
						rata_classroom = Float.valueOf(tempString);
					
					tempString = rs.getCell(j++, i).getContents();
					float rata_library = -999;
					if(!tempString.equals(""))
						rata_library = Float.valueOf(tempString);
					
					tempString = rs.getCell(j++, i).getContents();
					float rata_lab = -999;
					if(!tempString.equals(""))
						rata_lab = Float.valueOf(tempString);

					tempString = rs.getCell(j++, i).getContents();
					float rata_gym = -999;
					if(!tempString.equals(""))
						rata_gym = Float.valueOf(tempString);
					
					tempString = rs.getCell(j++, i).getContents();
					float rata_privatescienresearch = -999;
					if(!tempString.equals(""))
						rata_privatescienresearch = Float.valueOf(tempString);
					
					
					tempString = rs.getCell(j++, i).getContents();
					float rata_hall = -999;
					if(!tempString.equals(""))
						rata_hall = Float.valueOf(tempString);
					
					tempString = rs.getCell(j++, i).getContents();
					float rata_administrationoffice = -999;
					if(!tempString.equals(""))
						rata_administrationoffice = Float.valueOf(tempString);
					
					float rata_sum = -999;
					if(rata_teachresearchauxiliary!=-999 && rata_classroom!=-999 && rata_library!=-999 && rata_lab!=-999 && 
							rata_privatescienresearch!=-999 && rata_gym!=-999 && rata_hall!=-999 && rata_administrationoffice!=-999)
						rata_sum = rata_teachresearchauxiliary+rata_classroom+rata_library+rata_lab+
							rata_privatescienresearch+rata_gym+rata_hall+rata_administrationoffice;
					
					//添加修改点击保存的时候需要判断,0表示完整，1表示缺失
					int isnull = 0;
					if(rata_sum==-999 || rata_collegename.equals(""))
						isnull = 1;
					
					
					rataList.add(new RoomAreaofTeachingAdministration(rata_collegename,
							rata_sum, rata_teachresearchauxiliary,
							rata_classroom, rata_library, rata_lab,
							rata_privatescienresearch, rata_gym, rata_hall,
							rata_administrationoffice, college, isnull));
				}
				errorrow++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "导入失败";
		}
		return rataList;
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
