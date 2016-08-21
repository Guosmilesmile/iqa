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
import cn.edu.xmu.dao.UndergraduateAwardLoanDao;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.daoimpl.UndergraduateAwardLoanDaoImpl;
import cn.edu.xmu.entity.UndergraduateAwardLoan;
import cn.edu.xmu.table.UndergraduateAwardLoanTable;

/**
 * @author zhantu Servlet implementation class Sec_ExportUndergraduateAwardLoan date 2015-07-11
 */

@WebServlet("/ExportUndergraduateAwardLoan")
public class Sec_ExportUndergraduateAwardLoan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ExportUndergraduateAwardLoan() {
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

		String college = request.getParameter("ual_college");
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
			queryParams.put(UndergraduateAwardLoanTable.UAL_DEADLINE, deadLine);
		}
		if(college != null && !"".equals(college)){
			if (college.contains("院")) {//具体的学院才过滤
				queryParams.put(UndergraduateAwardLoanTable.UAL_COLLEGE, college);
			}
		}
		
		if (tablename.endsWith("表6-1-7本科生奖贷补")) {
			UndergraduateAwardLoanDao dao = new UndergraduateAwardLoanDaoImpl();
			List<UndergraduateAwardLoan> tsList = dao.getAllUndergraduateAwardLoan(0, dao.getUndergraduateAwardLoanCount(queryParams), "'ual_serialnumber'", "asc", queryParams);
			/*-------------- 1.准备数据--------------*/
			List<Object> tsResultList = new ArrayList<Object>();
			for (int i = 0; i < tsList.size(); i++) {
				List<Object> tsCountList = new ArrayList<Object>();// 行数据
				tsCountList.add(tsList.get(i).getUal_sumcost());
				tsCountList.add(tsList.get(i).getUal_sumcount());
				tsCountList.add(tsList.get(i).getUal_govcost());
				tsCountList.add(tsList.get(i).getUal_govcount());
				tsCountList.add(tsList.get(i).getUal_societycost());
				tsCountList.add(tsList.get(i).getUal_societycount());
				tsCountList.add(tsList.get(i).getUal_schoolcost());
				tsCountList.add(tsList.get(i).getUal_schoolcount());
				tsCountList.add(tsList.get(i).getUal_countrycost());
				tsCountList.add(tsList.get(i).getUal_countrycount());
				tsCountList.add(tsList.get(i).getUal_workstudycost());
				tsCountList.add(tsList.get(i).getUal_workstudycount());
				tsCountList.add(tsList.get(i).getUal_deratecost());
				tsCountList.add(tsList.get(i).getUal_deratecount());
				tsCountList.add(tsList.get(i).getUal_troubleaidcost());
				tsCountList.add(tsList.get(i).getUal_troubleaidcount());

				tsResultList.add(tsCountList);
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
