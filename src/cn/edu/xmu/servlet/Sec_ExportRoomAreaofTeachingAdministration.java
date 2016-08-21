package cn.edu.xmu.servlet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.dao.RoomAreaofTeachingAdministrationDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.RoomAreaofTeachingAdministrationDaoImpl;
import cn.edu.xmu.entity.RoomAreaofTeachingAdministration;
import cn.edu.xmu.table.RoomAreaofTeachingAdministrationTable;

/**
 * @author zhantu Servlet implementation class Sec_ExportRoomAreaofTeachingAdministration date 2015-07-11
 */

@WebServlet("/ExportRoomAreaofTeachingAdministration")
public class Sec_ExportRoomAreaofTeachingAdministration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ExportRoomAreaofTeachingAdministration() {
		super();
		// TODO Auto-generated constructor stub
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

		String college = request.getParameter("rata_college");
		college = URLDecoder.decode(college, "utf-8");
		
		String tableid = request.getParameter("tableid");
		System.out.println("tableid===========" + tableid);
		// 根据tableid得到tablename
		TableListDao tableListDao = new TableListDaoImpl();
		String tablename = tableListDao.getTablename(tableid);

		Date deadLine = tableListDao.getTableDate(tableid);//tableList中应当注意发布时清空，关闭时赋值
		System.out.println("deadline:"+deadLine);
	   
		Map queryParams = new HashMap();
		if (deadLine != null) {
			queryParams.put(RoomAreaofTeachingAdministrationTable.RATA_DEADLINE, deadLine);
		}
		if(college != null && !"".equals(college)){
			if (college.contains("院")) {//具体的学院才过滤
				queryParams.put(RoomAreaofTeachingAdministrationTable.RATA_COLLEGE, college);
			}
		}
		
		if (tablename.endsWith("附表2-2各单位教学行政用房面积")) {
			RoomAreaofTeachingAdministrationDao dao = new RoomAreaofTeachingAdministrationDaoImpl();
			List<RoomAreaofTeachingAdministration> rataList = dao.getAllRoomAreaofTeachingAdministration(0, dao.getRoomAreaofTeachingAdministrationCount(queryParams), "'rata_serialnumber'", "asc", queryParams);
			/*-------------- 1.准备数据--------------*/
			List<Object> tsResultList = new ArrayList<Object>();
			for (int i = 0; i < rataList.size(); i++) {
				List<Object> rataCountList = new ArrayList<Object>();// 行数据
				rataCountList.add(rataList.get(i).getRata_collegename());
				rataCountList.add(rataList.get(i).getRata_sum());
				rataCountList.add(rataList.get(i).getRata_teachresearchauxiliary());
				rataCountList.add(rataList.get(i).getRata_classroom());
				rataCountList.add(rataList.get(i).getRata_library());
				rataCountList.add(rataList.get(i).getRata_lab());
				rataCountList.add(rataList.get(i).getRata_privatescienresearch());
				rataCountList.add(rataList.get(i).getRata_gym());
				rataCountList.add(rataList.get(i).getRata_hall());
				rataCountList.add(rataList.get(i).getRata_administrationoffice());
				
				tsResultList.add(rataCountList);
			}
			ExcelUtils.addValue("typename", tablename);//typename是用在表格模板里面的表名
			ExcelUtils.addValue("resultList", tsResultList);//resultList是用在表格模板里面用于显示数据的
			/*-------------- 2.写出excel文件--------------*/
			String dirs = request.getSession().getServletContext()
					.getRealPath("")
					+ "/template/";
			String templateFileName = tablename;// 模版名称（不含扩展名），用于导出的模板
			String templateFilePath = dirs + templateFileName + ".xls";//导出的模板路径
			String destFilePath = dirs + templateFileName + "-out.xls";//导出的文件路径
			try {
				System.out.println("templateFilePath=" + templateFilePath);
				OutputStream out = new FileOutputStream(destFilePath);
				ExcelUtils.export(templateFilePath, out);
				response.setContentType("application/x-download");
				System.out.println("destFilePath=" + destFilePath);
				String filenamedisplay = tablename + "-out.xls";
				filenamedisplay = URLEncoder.encode(filenamedisplay, "UTF-8");
				response.addHeader("Content-Disposition",
						"attachment;filename=" + filenamedisplay);
			} catch (ExcelException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			try {
				java.io.OutputStream os = response.getOutputStream();
				java.io.FileInputStream fis = new java.io.FileInputStream(
						destFilePath);
				byte[] b = new byte[1024];
				int i = 0;
				while ((i = fis.read(b)) > 0) {
					os.write(b, 0, i);
				}
				fis.close();
				os.flush();
				os.close();
			} catch (Exception e) {
				System.out.println("error");
			}
		}
	}

}
