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
import cn.edu.xmu.dao.GeneralHighEnrollmentfileLineDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.GeneralHighEnrollmentfileLineDaoImpl;
import cn.edu.xmu.entity.GeneralHighEnrollmentfileLine;
import cn.edu.xmu.table.GeneralHighEnrollmentfileLineTable;

/**
 * @author zhantu Servlet implementation class Sec_ExportGeneralHighEnrollmentfileLine date 2015-07-13
 */

@WebServlet("/ExportGeneralHighEnrollmentfileLine")
public class Sec_ExportGeneralHighEnrollmentfileLine extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ExportGeneralHighEnrollmentfileLine() {
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

		String college = request.getParameter("ghel_college");
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
			queryParams.put(GeneralHighEnrollmentfileLineTable.GHEL_DEADLINE, deadLine);
		}
		if(college != null && !"".equals(college)){
			if (college.contains("院")) {//具体的学院才过滤
				queryParams.put(GeneralHighEnrollmentfileLineTable.GHEL_COLLEGE, college);
			}
		}
		if (tablename.endsWith("附表6-1-5-2厦门大学普高招生各省份出档线高出本一线分值")) {
			GeneralHighEnrollmentfileLineDao dao = new GeneralHighEnrollmentfileLineDaoImpl();
			List<GeneralHighEnrollmentfileLine> ghelList = dao.getAllGeneralHighEnrollmentfileLine(0, dao.getGeneralHighEnrollmentfileLineCount(queryParams), "'ghel_serialnumber'", "asc", queryParams);
			/*-------------- 1.准备数据--------------*/
			List<Object> ghelResultList = new ArrayList<Object>();
			for (int i = 0; i < ghelList.size(); i++) {
				List<Object> ghelCountList = new ArrayList<Object>();// 行数据
				ghelCountList.add(ghelList.get(i).getGhel_type());

				ghelCountList.add(ghelList.get(i).getGhel_hainan());
				ghelCountList.add(ghelList.get(i).getGhel_xinjiang());
				ghelCountList.add(ghelList.get(i).getGhel_xizangshao());
				ghelCountList.add(ghelList.get(i).getGhel_yunnan());

				ghelCountList.add(ghelList.get(i).getGhel_shanxishan());
				ghelCountList.add(ghelList.get(i).getGhel_tianjin());
				ghelCountList.add(ghelList.get(i).getGhel_ningxia());
				ghelCountList.add(ghelList.get(i).getGhel_guizhou());

				ghelCountList.add(ghelList.get(i).getGhel_liaoning());
				ghelCountList.add(ghelList.get(i).getGhel_xizanghan());
				ghelCountList.add(ghelList.get(i).getGhel_jilin());
				ghelCountList.add(ghelList.get(i).getGhel_guangxi());

				ghelCountList.add(ghelList.get(i).getGhel_zhejiang());
				ghelCountList.add(ghelList.get(i).getGhel_chongqing());
				ghelCountList.add(ghelList.get(i).getGhel_anhui());
				ghelCountList.add(ghelList.get(i).getGhel_heilongjiang());

				ghelCountList.add(ghelList.get(i).getGhel_jiangxi());
				ghelCountList.add(ghelList.get(i).getGhel_sichuan());
				ghelCountList.add(ghelList.get(i).getGhel_beijing());
				ghelCountList.add(ghelList.get(i).getGhel_henan());

				ghelCountList.add(ghelList.get(i).getGhel_hunan());
				ghelCountList.add(ghelList.get(i).getGhel_shanghai());
				ghelCountList.add(ghelList.get(i).getGhel_fujian());
				ghelCountList.add(ghelList.get(i).getGhel_shandong());

				ghelCountList.add(ghelList.get(i).getGhel_hebei());
				ghelCountList.add(ghelList.get(i).getGhel_hubei());
				ghelCountList.add(ghelList.get(i).getGhel_guangdong());
				ghelCountList.add(ghelList.get(i).getGhel_jiangsu());

				ghelCountList.add(ghelList.get(i).getGhel_shanxijin());
				ghelCountList.add(ghelList.get(i).getGhel_neimenggu());
				ghelCountList.add(ghelList.get(i).getGhel_qinghai());
				ghelCountList.add(ghelList.get(i).getGhel_gansu());
				ghelResultList.add(ghelCountList);
			}
			ExcelUtils.addValue("typename", tablename);//typename是用在表格模板里面的表名
			ExcelUtils.addValue("resultList", ghelResultList);//resultList是用在表格模板里面用于显示数据的
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
