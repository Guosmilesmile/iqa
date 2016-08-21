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

import cn.edu.xmu.dao.ForeignStudentInfoDao;
import cn.edu.xmu.dao.TableListDao;
import cn.edu.xmu.daoimpl.ForeignStudentInfoDaoImpl;
import cn.edu.xmu.daoimpl.TableListDaoImpl;
import cn.edu.xmu.entity.ForeignStudentInfo;
import cn.edu.xmu.table.ForeignStudentInfoTable;
import cn.edu.xmu.table.StuPhysicalHealthInfoTable;
import net.sf.excelutils.ExcelException;
import net.sf.excelutils.ExcelUtils;



@WebServlet("/Sec_ExportForeignStudentInfoServlet")
public class Sec_ExportForeignStudentInfoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Sec_ExportForeignStudentInfoServlet() {
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
		String tableid = request.getParameter("tableid");
		System.out.println("tableid===========" + tableid);
		// 根据tableid得到tablename
		TableListDao tableListDao = new TableListDaoImpl();
		String tablename = tableListDao.getTablename(tableid);

		String college = request.getParameter("college");
		college = URLDecoder.decode(college, "UTF-8");
		Date deadLine = tableListDao.getTableDate(tableid);
		Map queryParams = new HashMap();
		if (deadLine != null)
			queryParams.put(ForeignStudentInfoTable.FSI_DEADLINE, deadLine);
		if (college != null && !"".equals(college))
		{
			if (college.contains("学院"))
			{// 具体的学院才过滤
				queryParams.put(ForeignStudentInfoTable.FSI_COLLEGE, college);
			}
		}
		
		if (tablename.endsWith("表6-1-4国外及港澳台学生情况（时点）")) {
			ForeignStudentInfoDao dao = new ForeignStudentInfoDaoImpl();
			List<ForeignStudentInfo> fsiList = dao.getForeignStudentInfo(0, dao.getForeignStudentInfoCount(queryParams), ForeignStudentInfoTable.FSI_ID, "asc", queryParams, null);
			/*-------------- 1.准备数据--------s------*/
			List<Object> tsResultList = new ArrayList<Object>();
			for (int i = 0; i < fsiList.size(); i++) {
				List<Object> fsiCountList = new ArrayList<Object>();// 行数据
				fsiCountList.add(fsiList.get(i).getFsi_allgraduatenumber());
				fsiCountList.add(fsiList.get(i).getFsi_alldegreenumber());
				fsiCountList.add(fsiList.get(i).getFsi_allenrollnumber());
				fsiCountList.add(fsiList.get(i).getFsi_allcurrentstudentnumber());
				fsiCountList.add(fsiList.get(i).getFsi_foreigngraduatenumber());
				fsiCountList.add(fsiList.get(i).getFsi_foreigndegreenumber());
				fsiCountList.add(fsiList.get(i).getFsi_foreignenrollnumber());
				fsiCountList.add(fsiList.get(i).getFsi_foreigncurrentstudentnumber());
				fsiCountList.add(fsiList.get(i).getFsi_hkgraduatenumber());
				fsiCountList.add(fsiList.get(i).getFsi_hkdegreenumber());
				fsiCountList.add(fsiList.get(i).getFsi_hkenrollnumber());
				fsiCountList.add(fsiList.get(i).getFsi_hkcurrentstudentnumber());
				fsiCountList.add(fsiList.get(i).getFsi_macgraduatenumber());
				fsiCountList.add(fsiList.get(i).getFsi_macdegreenumber());
				fsiCountList.add(fsiList.get(i).getFsi_macenrollnumber());
				fsiCountList.add(fsiList.get(i).getFsi_maccurrentstudentnumber());
				fsiCountList.add(fsiList.get(i).getFsi_twngraduatenumber());
				fsiCountList.add(fsiList.get(i).getFsi_twndegreenumber());
				fsiCountList.add(fsiList.get(i).getFsi_twnenrollnumber());
				fsiCountList.add(fsiList.get(i).getFsi_twncurrentstudentnumber());
				
				tsResultList.add(fsiCountList);
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
